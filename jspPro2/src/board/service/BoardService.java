package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.model.Article;
import article.service.ArticleNotFoundException;
import article.service.ArticlePage;
import article.service.OurArticleData;
import board.dao.BoardDAO;
import board.model.Board;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//
public class BoardService {

	//필드
	private BoardDAO boardDAO = new BoardDAO();
	int size = 10;//1페이지당 출력할 게시글수
	
	//생성자

	//글삭제
	//파라미터 no - 삭제할  글번호
	//리턴 int 삭제성공시 삭제된글번호리턴, 실패시 0
	public int deleteBoard(int no) {
		Connection conn = null;
		int deletedNo = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//파라미터 no - 삭제할  글번호
			//리턴 int 삭제성공시 삭제된글번호리턴, 실패시 0
			deletedNo = boardDAO.delete(conn,no);
						
			conn.commit();
			return deletedNo;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	//수정하기
	//파라미터 board:글번호,회원id,글수정제목,수정내용,수정첨부파일명
	public void modifyBoard(Board board) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//파라미터 board - board:글번호,회원id,글수정제목,수정내용,수정첨부파일명
			boardDAO.modify(conn,board);
						
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	//글등록하기
	//파라미터 board: 세션의 회원id,글제목,내용,첨부파일명
	//리턴     boardno : 방금전 insert된 글번호
	public int insert(Board board) {
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//파라미터 board   - 회원id,제목,내용,작성일,첨부파일명
			//리턴     int - inserted된 정보 글번호!!!
			Integer savedBoardno = boardDAO.insert(conn,board);
			if(savedBoardno==null) {
				throw new RuntimeException("Fail to insert board");
			}
			
			conn.commit();
			return savedBoardno;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
	
	
	//상세조회
	//파라미터 int no : 상세조회할 글 번호
	//리턴 Board : 글번호,작성자id,제목,작성일,내용,이미지파일명
	public Board getDetail(int no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			Board board = boardDAO.getDetail(conn, no);
			if(board==null) {
				throw new BoardNotFoundException();
			}
			
			return board;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}		
	}
	
	
	//총게시글수+글목록조회 
    //파라미터  int pageNum : 보고싶은 페이지
	public BoardPage  getBoardPage(int pageNum) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = boardDAO.selectCount(conn); //총게시글수
			/*파라미터  int startRow-페이지에 따른 row시작번호 예)1page이면 limit 0,3
						int size    -1페이지당 출력할 게시글 수  */
			List<Board> content = boardDAO.select(conn,(pageNum-1)*size,size);//목록조회-p651 19라인
			
			/*파라미터  int	total				//총게시글수
			 			int pageNum 			//보고싶은 페이지
			 			int size    			//1페이지당 출력할 게시글 수
						List<Board> content;    //board목록 */
			BoardPage bp = new BoardPage(total, pageNum, size, content);
			System.out.println("BrticleService- getBoardPage()의 결과 bp="+bp);
			
			return bp;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
}
