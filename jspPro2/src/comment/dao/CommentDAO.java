package comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import comment.model.Comment;

public class CommentDAO {

	//파라미터 no - 원글번호
	//리턴     List<Comment> - 특정원글에 작성된 댓글정보(댓글번호,원글번호,댓글작성자,댓글내용,댓글등록일)
	public List<Comment> getCommentList(Connection conn, int no) throws SQLException {
		String sql = "select commentno,boardno,cmt_writerid,cmt_content,cmt_writedate " + 
				"from comment " + 
				"where boardno=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Comment> commnetList = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Comment comment = new Comment( rs.getInt("commentno"), rs.getInt("boardno"), 
					rs.getString("cmt_writerid"), rs.getString("cmt_content"),
					rs.getDate("writedate"));
				commnetList.add(comment);
			}
		}finally {
			rs.close();
			stmt.close();
		}
		return commnetList;
	}

	public int updateComment(Connection conn, Comment comment) {
		String sql = "update comment " + 
								"set  cmt_content=? " + 
								"where commentno=? and  cmt_writerid=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Comment> commnetList = new ArrayList<>();
		int rowCnt=0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, commemt.getContent());
			stmt.setInt(2, commemt.getcommentno);
			stmt.setString(3, commemt.getWriterId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				Comment comment = new Comment( rs.getInt("commentno"), rs.getInt("boardno"), 
					rs.getString("cmt_writerid"), rs.getString("cmt_content"),
					rs.getDate("writedate"));
				commnetList.add(comment);
			}
		}finally {
			rs.close();
			stmt.close();
		}
		return commnetList;

}
}












