package article.model;

import java.util.Date;

//p631 - article테이블의 값을 저장 및 제공 등의 기능을 가진 클래스이다
/*article_no	 int   AUTO_INCREMENT NOT NULL PRIMARY KEY,
  writer_id   varchar(50) 
 writer_name varchar(50) 
 title       varchar(255)
 regdate     datetime
 moddate     datetime	
 read_cnt    int*/
public class Article {
	//필드 -p631 7라인
	private Integer number;	//article_no 글번호.PK
	private Writer  writer; //writer_id, writer_name의 값을 저장하는 별도의 Writer클래스
	private String  title;  //title 제목
	private Date    regDate; 		//regdate 작성일
	private Date    modifiedDate; 	//moddate  수정일
	private int     readCount;	 	//read_cnt 조회수
	
	//생성자-p631 14라인
	public Article(Integer number, Writer writer, String title, Date regDate, Date modifiedDate, int readCount) {
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.readCount = readCount;
	}

	//메서드-p631 24라인
	public Integer getNumber() {
		return number;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public Date getRegDate() {
		return regDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public int getReadCount() {
		return readCount;
	}

	@Override
	public String toString() {
		return "Article [number=" + number + ", title=" + title + ", regDate=" + regDate + ", modifiedDate="
				+ modifiedDate + ", readCount=" + readCount + "]";
	}
	
}





