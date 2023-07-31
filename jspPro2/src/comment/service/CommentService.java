package comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import comment.dao.CommentDAO;
import comment.model.Comment;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class CommentService {
	//필드
	private CommentDAO commentDAO = new CommentDAO();
	
	//(원글 번호에 등록된) 댓글목록조회
	//파라미터 no - 원글번호
	//리턴     List<Comment> - 특정원글에 작성된 댓글정보(댓글번호,원글번호,댓글작성자,댓글내용,댓글등록일)
	public List<Comment> getCommentList(int no){
		Connection conn = null;
		List<Comment> commentList = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			//파라미터 no - 원글번호
			//리턴     List<Comment> - 특정원글에 작성된 댓글정보(댓글번호,원글번호,댓글작성자,댓글내용,댓글등록일)
			commentList = commentDAO.getCommentList(conn, no);
			if(commentList==null) {
				throw new RuntimeException("fail to select commentList=>쿼리문,db보삼~");
			}
			return commentList;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(RuntimeException e){
			e.printStackTrace();
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
		return commentList;
	}

	public int modifyComment(Comment comment) {
		Connection conn = null;
		int rowCnt = -1; //기본값은 상황에 따라 다르게..
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			rowCnt  = commentDAO.updateComment(conn,comment);
			if(rowCnt==0) {
				throw new RuntimeException("fail to update commemt");
			}
			conn.commit();
			return rowCnt;
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
}
