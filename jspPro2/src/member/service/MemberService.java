package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.Member;

//컨트롤러-<>Service<->DAO<->DB
public class MemberService {

	private MemberDAO memberDAO = new MemberDAO(); 
	
	//회원검색-ajax
	//변수 String sName : user가 검색시 사용한 회원명
	//리턴 List<Member>: 회원들정보(no,memberid,name,regdate,password,isshow)
	public List<Member> searchMember(String sName){
		Connection conn = null;
		List<Member> memberList = null;
		try {
			conn = ConnectionProvider.getConnection();

			//변수 String sName : user가 검색시 사용한 회원명
			//리턴 List<Member>: 회원들정보(no,memberid,name,regdate,password,isshow)	
			memberList = memberDAO.searchMember(conn, sName);
	
			return memberList;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.close(conn);
		}
		return memberList;
		
	}
}
