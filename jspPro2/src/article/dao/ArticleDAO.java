package article.dao;

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
import jdbc.JdbcUtil;

//P646~648
//article테이블관련 DB작업용 클래스이다
public class ArticleDAO {
	//필드
	//생성자

	//update쿼리를 통한 글삭제
	public int deleteUp(Connection conn, int no)   throws SQLException {
		String sql = "update article set isshow='N' where article_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
			return stmt.executeUpdate();
			//update가 성공되면 1리턴, 실패시 0리턴
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
		
	//삭제-delete쿼리를 통한 글삭제
	//우리는 article삭제시 article_content테이블에서도 해당 글번호가 연쇄적으로 삭제하게끔.
	public int delete(Connection conn, int no)  throws SQLException {
		String sql = "delete from article where article_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
			return stmt.executeUpdate();
			//delete가 성공되면 1리턴, 실패시 0리턴
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
	
	//수정 p665
	/*파라미터  int no : update하고자하는 글번호 
	            String title : 수정하고자하는 새 제목 	 
	 *리턴 		int : update가 성공되면 1리턴, 실패시 0리턴
	 */
	public int update(Connection conn, int no, String title)  throws SQLException {
		String sql = "update article " + 
					 "set title=?, regdate=now() " + 
					 "where article_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,title);
			stmt.setInt(2,no);
			return stmt.executeUpdate();
			//update가 성공되면 1리턴, 실패시 0리턴
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
	
	//교재에서는 P655 selectById()이용하여 article테이블의 내용가져오기
	//			 p656 selectById()이용하여 article_content테이블의 내용가져오기
	//2개의 테이블의 내용을 ArticleData클래스(p657)로 묶어서 처리하였다
	
	//여기에서는 2개의 테이블의 내용을 join하여 처리하는 방식으로 진행
	//(article테이블,article_content테이블)상세조회
	//파라미터 int no : 상세조회할 글 번호
	//리턴     OurArticleData : 글번호,작성자id,작성자명,제목,작성일,수정일,조회수,내용
	public OurArticleData getDetail(Connection conn, int no) throws SQLException {
		String sql="select a.article_no, a.writer_id, a.writer_name, a.title, " + 
						  "a.regdate,    a.moddate,   a.read_cnt, " + 
						  "ac.content " + 
					"from  article a,  article_content ac " + 
					"where a.article_no=ac.article_no " + 
					      "and " + 
					      "a.article_no=? and a.isshow='Y'";//
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			OurArticleData ora = null;
			if(rs.next()) {
				ora = new OurArticleData();
				ora.setNumber(rs.getInt("article_no"));
				ora.setWriter_id(rs.getString("writer_id"));
				ora.setWriter_name(rs.getString("writer_name"));
				ora.setTitle(rs.getString("title"));
				ora.setRegDate(rs.getDate("regdate"));
				ora.setModifiedDate(rs.getDate("moddate"));
				ora.setReadCount(rs.getInt("read_cnt"));
				ora.setContent(rs.getString("content"));
				
				//콘솔출력
				System.out.println("ArticleDAO에서  getDetail() OurArticleData ora ="+ora);
			}
			return ora;			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}	
	}
	
	//조회수증가-P656 20라인
	//파라미터 int no : 상세조회할 글 번호
	public void increaseReadCount(Connection conn, int no)  throws SQLException {
		String sql = "update article " + 
					 "set read_cnt=read_cnt+1 " + 
					 "where article_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
			stmt.executeUpdate();
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
	
	
	//(article테이블)목록조회-p647 15라인
	/*0,13; -- 1page페이지의 경우
			-- 1페이지당 게시글수가 3인 경우(여기에서는 총게시물수가 13건) 
			-- =>1page이면 limit 0,3   13 12 11
			-- =>2page이면 limit 3,3   10 9 8
			-- =>3page이면 limit 6,3   7 6 5 
			-- =>4page이면 limit 9,3   4 3 2 
			-- =>5page이면 limit 12,3  1 */
	/*파라미터  int startRow-페이지에 따른 row시작번호
				int size    -1페이지당 출력할 게시글수  */
	public List<Article> select(Connection conn,int startRow, int size) throws SQLException {
		String sql="select article_no, writer_id, writer_name, title," + 
					      "regdate,    moddate,   read_cnt " + 
				    "from article " + 
					"where isshow='Y' "+      
				    "order by article_no desc  limit ?,?";//p647 21라인
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			//p647 22,23라인
			stmt.setInt(1,startRow);
			stmt.setInt(2,size);
			rs = stmt.executeQuery();
		
			List<Article> result = new ArrayList<Article>();//p647 25라인
			//List참조변수.add(값); list에   값을 추가
			//List참조변수.get(int 인덱스번호); list에서 값을 가져오기
			while(rs.next()) {
				//데이터타입 변수명=rs.get데이터타입("컬럼명");
				//데이터타입 변수명=rs.get데이터타입(int 컬럼순서);
				result.add( convertArticle(rs) ); //p647 27라인
			}//while
			
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	//p647 36라인
	//select쿼리를 실행한 결과집합(ResultSet)을 이용하여 Article클래스의 객체를 생성
	private Article convertArticle(ResultSet rs) throws SQLException {
		return  
		new Article(rs.getInt("article_no"),
				    new Writer(rs.getString("writer_id"), rs.getString("writer_name")),
				    rs.getString("title"),
				    rs.getDate("regdate"),
				    rs.getDate("moddate"),
				    rs.getInt("read_cnt")  );
	}
	
	
	
	//총 게시물수 조회-P646
	public int selectCount(Connection conn) throws SQLException {
		String sql="select count(*) from article";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int totalCNT = 0; //총 게시물수를 저장하기 위한 변수 선언 및 초기화
			if(rs.next()) {
	//			데이터타입 변수명=rs.get데이터타입("컬럼명");
	//			데이터타입 변수명=rs.get데이터타입(int 컬럼순서);
				totalCNT = rs.getInt(1);
			}
			return totalCNT;			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	//p634 16라인
	//파라미터 Article - 회원id, 회원name,제목,내용,작성일,수정일,조회수
	//리턴     Article - inserted된 정보 글번호!!!,회원id, 회원name,제목,작성일,수정일,조회수
	public Article insert(Connection conn, Article article) throws SQLException {
		System.out.println("ArticleDAO-insert()진입");
		
		//3.객체준비
		String sql =
		"insert into article(writer_id,writer_name,title,regdate,moddate,read_cnt,isshow) " + 
		"values(?,?,?,?,?,0,'Y')";
		PreparedStatement stmt  = null; //insert용
		PreparedStatement stmt2 = null; //select용
		ResultSet rs = null;
		
		try {
		stmt = conn.prepareStatement(sql);
		
		//4.쿼리실행
		//stmt.set데이터타입(?순서,값);
		stmt.setString(1,article.getWriter().getId());
		stmt.setString(2,article.getWriter().getName());
		stmt.setString(3,article.getTitle());
		stmt.setTimestamp(4,toTimestamp( article.getRegDate() ));
		stmt.setTimestamp(5,toTimestamp( article.getModifiedDate()));
		int insertedCount = stmt.executeUpdate();
		//입력성공시 1리턴, 실패시 0리턴
		if(insertedCount>0) { //p635 31라인
			//방금 직전에 입력된 글번호를 DB에서 가져온다
			//->article_content 테이블에 insert시 글번호로 사용
			stmt2 =	conn.prepareStatement("select last_insert_id() from article");
			rs = stmt2.executeQuery();
			if(rs.next()) {
				Integer newNum = rs.getInt(1);
				return new Article(newNum,article.getWriter(),article.getTitle(),
						article.getRegDate(), article.getModifiedDate(),0);
			}
		}
		
		return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt2);
			JdbcUtil.close(stmt);
		}
		
	}

	//p635 52라인 - Date타입을 Timestamp타입으로 변환 
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}


	
	
	
	
}









