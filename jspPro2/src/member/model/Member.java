package member.model;

import java.util.Date;

//p591 member 테이블관련 data저장,제공 등의 기능을 제공 클래스
public class Member {
	//필드
	private int		no;			//회원번호.pk
	private String  memberid;	//회원id
	private String  password;	//비번
	private String  name;		//회원명
	private Date	regDate;	//가입일
	private String  isshow;	    //노출여부.isshow  기본값'Y', 삭제시 'N'	
	
	//생성자
	public Member(int no, String memberid, String password, String name, Date regDate) {
		this.no = no;
		this.memberid = memberid;
		this.password = password;
		this.name = name;
		this.regDate = regDate;
	}
	
	public Member(int no, String memberid, String password, String name, Date regDate, String isshow) {
		this.no = no;
		this.memberid = memberid;
		this.password = password;
		this.name = name;
		this.regDate = regDate;
		this.isshow = isshow;
	}

	public Member(String memberid, String password, String name, Date regDate) {
		this.memberid = memberid;
		this.password = password;
		this.name = name;
		this.regDate = regDate;
	}

	//메서드
	public int getNo() {
		return no;
	}

	public String getMemberid() {
		return memberid;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public Date getRegDate() {
		return regDate;
	}
	
	//p592 - 필드의 비번과  매개변수pwd가 일치하면 true리턴, 불일치시 false리턴
	//=> member테이블의 비번컬럼의 값과 매개변수pwd가 일치하면 true리턴, 불일치시 false리턴
	public boolean matchPassword(String pwd) {
		return this.password.equals(pwd);
	}
	
	public String getIsshow() {
		return isshow;
	}

	
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	//p619 20라인
	//password필드의 값을 새 비번으로 변경=>여기에서는 비번변경시 호출
	public void changePassword(String newPwd) {
		this.password=newPwd;
	}
	
	@Override
	public String toString() {
		return "Member [no=" + no + ", memberid=" + memberid + ", password=" + password + ", name=" + name
				+ ", regDate=" + regDate + ", isshow=" + isshow + "]";
	}

}

