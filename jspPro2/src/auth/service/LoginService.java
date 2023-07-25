package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.Member;

//p605
//LoginHandler<->Service<->DAO<->DB 
public class LoginService {
	//필드
	private MemberDAO memberDAO = new MemberDAO();
	
	//생성자 LoginService()
	
	//로그인처리
	//파라미터 String id, String password-유저가 입력한 id,비번
	//리턴     User - 로그인에 성공한 회원정보(회원id,회원명)
	public User  login(String id, String password) {
	
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			//p605 16라인
			//파라미터  String id-유저가 입력한 id
			//리턴      Member   -유저가 입력한 id에 해당하는 회원정보(회원번호,id,비번,이름,가입일)
			Member member = memberDAO.selectById(conn, id);
			if(member==null) {
				throw new LoginFailException();
			}
			
			//p606 20라인- 일치하면 true리턴, 불일치시 false리턴
			if( !member.matchPassword(password) ) {
				throw new LoginFailException();
			}
			return new User( member.getMemberid(), member.getName() );
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	
	}
	
}
















