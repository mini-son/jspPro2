package comment.model;

import java.util.Date;

//댓글관련  data를 저장,제공 등 클래스이다
//commentno,boardno,cmt_writerid,cmt_content,cmt_writedate
public class Comment {
	private int  commentno; //댓글번호. commentno(pk)
	private int  boardno; //원글번호. boardno
	private String  writerId; //댓글작성자id. cmt_writerid
	private String  content; //댓글내용. cmt_content
	private Date  writedate; //댓글등록일. cmt_writedate
	
	public Comment() {	}

	public Comment(int commentno, int boardno, String writerId, String content, Date writedate) {
		this.commentno = commentno;
		this.boardno = boardno;
		this.writerId = writerId;
		this.content = content;
		this.writedate = writedate;
	}

	public int getCommentno() {
		return commentno;
	}

	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWritedate() {
		return writedate;
	}

	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}

	@Override
	public String toString() {
		return "Comment [commentno=" + commentno + ", boardno=" + boardno + ", writerId=" + writerId + ", content="
				+ content + ", writedate=" + writedate + "]";
	}
	
	
}
