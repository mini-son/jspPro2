package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import article.model.Article;
import article.model.Writer;
import article.service.OurArticleData;
import board.model.Board;
import jdbc.JdbcUtil;

//board 테이블관련 db작업 클래스
public class BoardDAO {
	
	
	//글삭제
	//파라미터 no - 삭제할  글번호
	//리턴 boardno - 삭제된 글번호
	public int delete(Connection conn, int no)  throws SQLException {
		//3.객체준비
		String sql ="delete from board " + 
					"where boardno=?";
		PreparedStatement stmt  = null; //delete용
		try {
			stmt = conn.prepareStatement(sql);
			
			//4.쿼리실행
			//stmt.set데이터타입(?순서,값);
			stmt.setInt(1,no);
		
			int deletedCount = stmt.executeUpdate();
			//삭제성공시 삭제된글번호리턴, 실패시 0리턴
			if(deletedCount>0) {
				return no;				
			}
			
		}finally {
			JdbcUtil.close(stmt);
		}
		return 0; //삭제실패시 0을 리턴
	}

	
	//수정하기
	//파라미터 board - board:글번호,회원id,글수정제목,수정내용,수정첨부파일명
	public void modify(Connection conn, Board board) throws SQLException {
		System.out.println("BoardDAO-modify()진입");
		//3.객체준비
		String sql ="update board " + 
					"set title=?, content=?, imageFileName=? " + 
					"where boardno=?";
		PreparedStatement stmt  = null; //insert용
		
		try {
			stmt = conn.prepareStatement(sql);
			
			//4.쿼리실행
			//stmt.set데이터타입(?순서,값);
			stmt.setString(1,board.getTitle());
			stmt.setString(2,board.getContent());
			stmt.setString(3,board.getImageFileName());
			stmt.setInt(4,board.getNumber());
		
			int updatedCount = stmt.executeUpdate();
			//성공성공시 1리턴, 실패시 0리턴
			System.out.println("dao의 수정레코드수="+updatedCount);

		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
	
	//글등록
	//파라미터 board - 회원id,제목,내용,첨부파일명
	//리턴     int - inserted된 정보 글번호!!!
	public Integer insert(Connection conn, Board board) throws SQLException {
		System.out.println("BoardDAO-insert()진입");
		
		//3.객체준비
		String sql =
		"insert into board(writerid,title,content,writedate,imageFileName) " + 
		"values(?,?,?,now(),?)";
		PreparedStatement stmt  = null; //insert용
		PreparedStatement stmt2 = null; //select용
		ResultSet rs = null;
		
		try {
		stmt = conn.prepareStatement(sql);
		
		//4.쿼리실행
		//stmt.set데이터타입(?순서,값);
		stmt.setString(1,board.getWriterId());
		stmt.setString(2,board.getTitle());
		stmt.setString(3,board.getContent());
		stmt.setString(4,board.getImageFileName());
	
		int insertedCount = stmt.executeUpdate();
		//입력성공시 1리턴, 실패시 0리턴
		if(insertedCount>0) { //p635 31라인
			//방금 직전에 입력된 글번호를 DB에서 가져온다
			//->article_content 테이블에 insert시 글번호로 사용
			stmt2 =	conn.prepareStatement("select last_insert_id() from board");
			rs = stmt2.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		
		return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt2);
			JdbcUtil.close(stmt);
		}
		
	}
	
	
	//상세보기
	//파라미터 int no : 상세조회할 글 번호
	//리턴     Board : 글번호,작성자id,제목,작성일,내용,이미지파일명
	public Board getDetail(Connection conn, int no) throws SQLException {
		String sql="select boardno,writerid,title,content,writedate,imageFileName " + 
				   "from board " + 
				   "where boardno=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			Board board = null;
			if(rs.next()) {
				board = new Board();
				//조회된 각 컬럼의 값을 가져와 Board클래스 객체로 생성한다
				board.setNumber(rs.getInt("boardno"));
				board.setWriterId(rs.getString("writerid"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriteDate(rs.getDate("writedate"));
				board.setImageFileName(rs.getString("imageFileName"));
				
				//콘솔출력
				System.out.println("BoardDAO에서  getDetail() Board board ="+board);
			}
			return board;			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}	
	}
	
	
	//목록조회
	/*0,13; -- 1page페이지의 경우
	-- 1페이지당 게시글수가 3인 경우(여기에서는 총게시물수가 13건) 
	-- =>1page이면 limit 0,3   13 12 11
	-- =>2page이면 limit 3,3   10 9 8
	-- =>3page이면 limit 6,3   7 6 5 
	-- =>4page이면 limit 9,3   4 3 2 
	-- =>5page이면 limit 12,3  1 */
	/*파라미터  int startRow-페이지에 따른 row시작번호
			int size    -1페이지당 출력할 게시글수  */
	public List<Board> select(Connection conn,int startRow, int size) throws SQLException {
		String sql="select boardno, writerid, title, writedate " + 
				   "from board " + 
				   "order by boardno desc limit ?,?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,startRow);
			stmt.setInt(2,size);
			rs = stmt.executeQuery();
		
			List<Board> result = new ArrayList<Board>();
			while(rs.next()) {
				result.add( convertBoard(rs) ); 
			}
			
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	

	//select쿼리를 실행한 결과집합(ResultSet)을 이용하여 Board클래스의 객체를 생성
	private Board convertBoard(ResultSet rs) throws SQLException {
		return  new Board(rs.getInt("boardno"), 
						  rs.getString("writerid"), rs.getString("title"),
						  null, rs.getDate("writedate"), null);	
	}
	
	
	//총 게시물수 조회
	public int selectCount(Connection conn) throws SQLException {
		String sql="select count(*) from board";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int totalCNT = 0; //총 게시물수를 저장하기 위한 변수 선언 및 초기화
			if(rs.next()) {
				totalCNT = rs.getInt(1);
			}
			return totalCNT;			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	
}
