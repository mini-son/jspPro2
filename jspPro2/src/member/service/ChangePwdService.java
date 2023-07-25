package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.LoginFailException;
import auth.service.User;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.Member;

//p621
public class ChangePwdService {
	//필드
	private MemberDAO memberDAO = new MemberDAO();
	
	//생성자
	
	//비번변경처리
	/*파라미터  String userId-암호를 변경하고자 하는 회원id <-세션
	 			String curPwd-암호를 변경하고자 하는 회원의 현재 비번
	 			String newPwd-암호를 변경하고자 하는 회원의 새   비번*/
	public int changePwd(String userId, String curPwd, String newPwd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//p621 21라인
			//파라미터  String userId-암호를 변경하고자 하는 회원id
			//리턴      Member       -유저가 입력한 id에 해당하는 회원정보(회원번호,id,비번,이름,가입일)
			Member member = memberDAO.selectById(conn, userId);
			if(member==null) {
				throw new MemberNotFoundException();
			}
			
			//p621 25라인- 일치하면 true리턴, 불일치시 false리턴
			//현재 암호가 일치하지 않는 경우
			if( !member.matchPassword(curPwd) ) {
				throw new InvalidPwdException();
			}
			
			//p621 28라인
			//파라미터 String newPwd-암호를 변경하고자 하는 회원의 새 비번
			member.changePassword(newPwd);
			System.out.println("중간점검 ChangePwdService의 member="+member);
			
			//파라미터 Member member-변경하고자 하는 회원id 및 변경내용
			int rowCnt = memberDAO.update(conn,member);
			System.out.println("ChangePwdService-비번변경 실행결과="+rowCnt);//콘솔 확인용

			//1이면 수정성공, 0이면 수정실패
			
			conn.commit();

			return rowCnt;
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
}






