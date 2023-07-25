package board.model;

import java.util.Date;


//board테이블의 값을 저장 및 제공 등의 기능을 가진 클래스이다
/* articleno	 int   AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '글번호',
 writerid   varchar(50)  not null COMMENT '작성자id',
 title       varchar(255) not null COMMENT '제목',
 content	 text 		  not null COMMENT '내용',
 writedate   datetime	  not null COMMENT '작성일',
 imageFileName varchar(100)	 COMMENT '이미지파일명'*/
public class Board {
	
	//필드
	private Integer number;	  //boardno 	글번호.PK
	private String  writerId; //writerid	작성자id
	private String  title;    //title 		제목
	private String  content;  //content		내용
	private Date    writeDate;//writedate	작성일
	private String  imageFileName;//imageFileName   이미지파일명
	
	public Board() {}
	
	public Board(Integer number, String writerId, String title, String content, Date writeDate, String imageFileName) {
		this.number = number;
		this.writerId = writerId;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
		this.imageFileName = imageFileName;
	}
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	@Override
	public String toString() {
		return "Board [number=" + number + ", writerId=" + writerId + ", title=" + title + ", content=" + content
				+ ", writeDate=" + writeDate + ", imageFileName=" + imageFileName + "]";
	}
	
	
	
}












