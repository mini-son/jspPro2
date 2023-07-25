package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.Member;

//p596
//컨트롤러<->Service<->DAO<->DB
//Service의 역할 : 여기에서는 transaction관리, 컨트롤러의 역할 일부
public class JoinService {
	//필드-p596 14라인
	private MemberDAO  memberDAO = new MemberDAO();
	//생성자
	
	//회원가입처리 - p596 16라인
	//파라미터 JoinRequest joinReq: user가 입력한 정보(id,이름,비번,비번확인용)
	public void join(JoinRequest joinReq) {
		System.out.println("JoinService의 join()진입");
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//autoCommit하지마 설정 
			conn.setAutoCommit(false); //p596 20라인
			
			//id중복검사-p596 22라인
			/*파라미터 conn - 커넥션
			 * joinReq.getId()-user가 입력한 id
			 *리턴     Member -user가 입력한 id에 해당하는 회원정보(회원번호,id,비번,이름,가입일)*/
			Member member = memberDAO.selectById(conn, joinReq.getId()); //id로 회원정보가져오기-id중복체크, 회원상세정보가져오기, 비번찾기 등등
			if(member!=null) { //회원정보가 존재하면 => 조회한 id를 이미 사용하는 회원이 있다는 뜻!
				JdbcUtil.rollback(conn);
				throw new DuplicationException(); //ID중복예외로 생성. p596 25라인
			}
						
			//회원정보등록-p596 28라인
			/*파라미터 conn - 커넥션
			 *         JoinRequest joinReq: user가 입력한 정보(id,비번,이름), 가입일에 해당하는 Date객체*/
			Member member1 = new Member(0, joinReq.getId(), joinReq.getPassword(), joinReq.getName(),new Date());
			memberDAO.insert(conn, member1);
						
			conn.commit(); //p596 35라인
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);//rollback  //p597 37라인
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn); //p597 40라인
		}
	}
	
	
	//myWay-회원가입처리
	//컨트롤러부터 전달받은 회원정보를 (전달하여)
	//DAO의 회원가입처리메서드를 호출
	/*매개변수 String memberid,String password,String name =>회원정보
	 *리턴유형 int : insert가 성공되면 1, 실패되면 0
	 * */
	public int  joinMyWay( String memberid,String password,String name) {
		System.out.println("JoinService의 joinMyWay()진입");
		
		Connection conn = null;
		int rowCnt = 0;
		try {
			conn = ConnectionProvider.getConnection();
			
			//autoCommit하지마 설정
			conn.setAutoCommit(false);
			
			//Member객체생성
			Member member = new Member(0, memberid, password, name, null);
			
			rowCnt = memberDAO.insertMyWay(conn,member);
			//insert가 성공되면 1를, 실패되면 0
			System.out.println("Service.joinMyWay()의 rowCnt="+rowCnt);
			
			//commit
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);//rollback
		}
		
		return rowCnt;
	}

	
	
	
	/*
	 * public int joinMyWay( String memberid,String password,String name) {
	 * System.out.println("JoinService의 joinMyWay()진입");
	 * 
	 * MemberDAO memberDAO = new MemberDAO(); Connection conn = null; int rowCnt =
	 * 0; try { conn = ConnectionProvider.getConnection();
	 * 
	 * //autoCommit하지마 설정 conn.setAutoCommit(false);
	 * 
	 * rowCnt = memberDAO.insertMyWay(conn,memberid,password,name); //insert가 성공되면
	 * 1를, 실패되면 0 System.out.println("Service.joinMyWay()의 rowCnt="+rowCnt);
	 * 
	 * //commit conn.commit(); } catch (SQLException e) { e.printStackTrace();
	 * JdbcUtil.rollback(conn);//rollback }
	 * 
	 * return rowCnt; }
	 */
	
}











