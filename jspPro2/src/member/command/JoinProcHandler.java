package member.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.JoinService;
import mvc.command.CommandHandler;

//P597 - 회원가입처리요청에 따라 호출되는 클래스이다
//유저가 회원가입페이지에서 입력(선택)한 data를 받아서 db에 입력하고자~~ 
//요청url - http://localhost:포트번호/컨패/join.do
//요청url - http://localhost/joinProc.do

//요청url->ControllerUsingURI->담당핸들러호출
public class JoinProcHandler implements CommandHandler  {

	//담당컨트롤러의 요청메서드
	//요청주소 : http://localhost/joinProc.do?파라미터명=값&파라미터명=값
	//파라미터
	//리턴유형 : String client에게 보여주는 jsp문서의 경로와 파일명
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("JoinProcHandler의 process()진입");
	
		//1.파라미터받기
		//유저가 회원가입페이지에서 입력(선택)한 data를 받기
		String memberid = request.getParameter("memberid");//회원id
		String password = request.getParameter("password");//비번
		String name     = request.getParameter("name");//이름
		String regDate  = request.getParameter("regDate");//가입일
		System.out.printf("%s  %s  %s  %s",memberid,password,name,regDate);//콘솔 확인용
		
		//2.비즈니스로직처리<->Service<->DAO->DB
		JoinService joinService = new JoinService();
		int rowCnt = joinService.joinMyWay(memberid, password, name);
		//insert가 성공되면 1를, 실패되면 0
		System.out.println("Handler에서 서비스로부터 리턴받은 rowCnt="+rowCnt);
		
		
		//3.Model -세션,request이용
		//request이용
		//request.setAttribute("속성명", Object 값);
		request.setAttribute("insertedRowCnt", rowCnt);
		request.setAttribute("attr1", 3.14);
		request.setAttribute("attr2", true);
		request.setAttribute("uname", "홍길동");
		
		//세션이용
		HttpSession session = request.getSession();
		
		//세션객체참조변수명.setAttribute("속성명", Object 값);
		session.setAttribute("s_attr1", 3.14);
		session.setAttribute("s_attr2", true);
		session.setAttribute("s_uname", "session홍");
		
		//4.View
		return request.getContextPath()+"/view/member/joinSucess.jsp";
	}
	
	
	
	
	
}

















