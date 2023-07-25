package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//삭제처리하는 서비스클래스이다
public class DeleteArticleService {
	
	ArticleDAO articleDAO = new ArticleDAO();
	
	//update쿼리를 통한 글삭제
	public void deleteUp(int no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//1.article테이블에 delete하기전 해당글번호 가져오기
			OurArticleData article = articleDAO.getDetail(conn, no);
			if(article==null) {
				throw new ArticleNotFoundException();
			}
			
			//2.article테이블에 update하는 메서드호출
			articleDAO.deleteUp(conn, no);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}		
		
	}
	
	
	
	//delete쿼리를 통한 글삭제
	public void delete(int no) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//1.article테이블에 delete하기전 해당글번호 가져오기
			OurArticleData article = articleDAO.getDetail(conn, no);
			if(article==null) {
				throw new ArticleNotFoundException();
			}
			
			//2.article테이블에 delete하는 메서드호출
			articleDAO.delete(conn, no);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
	}


}
