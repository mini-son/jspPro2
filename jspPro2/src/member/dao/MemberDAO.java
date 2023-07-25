package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import member.model.Member;

//p592
//DAO - DB의 member(회원) 테이블과 연동하여 쿼리작업을 하는 클래스이다
//컨트롤러<->Service<->DAO->DB

//1.드라이버로딩->2.커넥션얻기->3.객체준비->4.쿼리실행->5.자원반환
//1.드라이버로딩은 환경설정단계에서 준비
//2.커넥션얻기는 Service로부터 전달
//5.자원반환 -(여기에서는 대부분) connection은 Service에서, 그외 rs,stmt는 DAO에서..
public class MemberDAO {
	//필드
	//생성자

	//p592 15라인
	//id로 회원정보가져오기-id중복체크, 회원상세정보가져오기, 비번찾기 등등
	//파라미터  String id -user가 입력한 id
	//리턴      Member - user가 입력한 id에 해당하는 회원정보(회원번호,id,비번,이름,가입일)
	public Member selectById(Connection conn, String id) throws SQLException {
		System.out.println("MemberDAO-selectById()진입");
		
		//3.객체준비
		String sql = "select no,memberid,password,name,regdate " + 
					 "from member " + 
					 "where memberid=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Member member = null; //회원정보를 저장하기위한 변수 선언 및 초기화
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,id);
			rs = stmt.executeQuery();  //4.쿼리실행
			
			//p592 24라인
			if(rs.next()) { //조회한 id와 일치하는 회원이 존재한다면
				/*데이터타입 변수명 = rs.get데이터타입("컬럼명");
				  데이터타입 변수명 = rs.get데이터타입(int 컬럼순서); */
				 int no = rs.getInt("no");
				 String memberid = rs.getString("memberid");
				 String password = rs.getString("password");
				 String name = rs.getString("name");
				 
				 //member테이블의 regdate컬럼의 값을 Timestamp타입으로 저장한 뒤
				 //Date타입으로 형변환
				 Timestamp ts = rs.getTimestamp("regdate");
				 Date d = toDate(ts);
				
				 member = new Member(no, memberid, password, name, d);
			}
			return member;
		} finally { //5.자원반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	//Timestamp타입을 Date타입으로 형변환-p593 38라인
	private Date toDate(Timestamp date) {
		// (조건)?조건참일경우:거짓일경우;
		return  (date==null)?null:new Date(date.getTime());
	}
	
	
	
	
	//myWay-회원가입처리
	//Service부터 전달받은 회원정보를 회원테이블에 입력
	public int  insertMyWay(Connection conn, Member member) throws SQLException {
		System.out.println("MemberDAO-insertMyWay()진입");
		
		//3.객체준비
		String sql = "insert into member(memberid,password,name,regdate) " + 
			 "values(?,?,?,now())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		//4.쿼리실행
		//stmt.set데이터타입(?순서,값);
		stmt.setString(1,member.getMemberid());  //memberid
		stmt.setString(2,member.getPassword()); //password
		stmt.setString(3,member.getName()); //name 
		int rowCnt = stmt.executeUpdate();
		//insert쿼리가 실행된 행수를 리턴받는다
		//여기에서는 insert가 성공되면 1를, 실패되면 0을 리턴받는다.
		System.out.println("DAO.insertMyWay()의 rowCnt="+rowCnt);
		
		//5.자원반환->여기에서는 생략하지만 반드시 해야한다!
		
		return rowCnt;		
	}

	//회원정보등록-p593 42라인
	/*파라미터 conn - 커넥션
	 *         Member mem: user가 입력한 정보(id,비번,이름),가입일에 해당하는 Date객체*/
	public void insert(Connection conn, Member mem) throws SQLException {
		System.out.println("MemberDAO-insert()진입");
		
		//3.객체준비
		String sql = "insert into member(memberid,password,name,regdate) " + 
					 "values(?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		//4.쿼리실행
		//stmt.set데이터타입(?순서,값);
		stmt.setString(1,mem.getMemberid());
		stmt.setString(2,mem.getPassword());
		stmt.setString(3,mem.getName());
		stmt.setTimestamp( 4, new Timestamp(mem.getRegDate().getTime()));
		int rowCnt = stmt.executeUpdate();
		System.out.println("MemberDAO-insert()실행결과="+rowCnt);//콘솔 확인용
		//1이면 입력성공, 0이면 입력실패
	}

	//비번변경
	//파라미터 Member member-변경하고자 하는 회원id 및 변경내용
	public int update(Connection conn, Member member) throws SQLException {
		//3.객체준비
		String sql = "update member " + 
					 "set    name=?, password=? " + 
				     "where  memberid=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		//4.쿼리실행
		//stmt.set데이터타입(?순서,값);
		stmt.setString(1,member.getName());
		stmt.setString(2,member.getPassword());
		stmt.setString(3,member.getMemberid());
		int rowCnt = stmt.executeUpdate();
		System.out.println("MemberDAO-update()실행결과="+rowCnt);//콘솔 확인용
		//1이면 수정성공, 0이면 수정실패
		return rowCnt;
	}

}












