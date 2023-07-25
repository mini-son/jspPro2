package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.service.User;
import mvc.command.CommandHandler;

//p606
//요청주소 http://localhost/login.do이면   호출되는  핸들러(담당컨트롤러)이다. 
//모든 핸들러(담당컨트롤러)는 반드시 interface CommandHandler를 구현해야 한다

public class LoginHandler implements CommandHandler{

	//필드
	private static final String FORM_VIEW ="/view/loginForm.jsp";
	private LoginService loginService = new LoginService();
	
	//담당컨트롤러의 요청메서드
	//리턴유형 String:  client에게 보여주는 jsp문서의 경로와 파일명
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LoginHandler의 process()진입");
	
		if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
			return processSubmit(request,response); //p607 25라인
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	}//process


	//로그인폼을 보여주기-p607 32라인
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return request.getContextPath()+FORM_VIEW;
	}
	
	//로그인처리-p607 36라인
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		String memberid = request.getParameter("memberid");//user가 입력한 id
		String password = trim(request.getParameter("password"));//user가 입력한 비번
		
		//에러정보가 담기는 Map- p607 41라인
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors",errors);

		//필수입력(유효성)검사- p607 44라인
		//에러가 있다면
		//Map참조변수 errors에  문자열타입으로 key로, True를 값으로 저장 
		if(memberid==null || memberid.isEmpty()) {
			errors.put( "id", Boolean.TRUE );
		}
		if(password==null || password.isEmpty()) {
			errors.put( "password", Boolean.TRUE );
		}
		
		//p607 49라인
		if(!errors.isEmpty()) { //에러가 있으면  로그인폼페이지로 이동
			return request.getContextPath()+FORM_VIEW;
		}
		
		//p607 53라인
		try {
			//2.비즈니스로직<->Service<->DAO<->DB 
			//파라미터 String memberid, String password-유저가 입력한 id,비번
			//리턴     User - 로그인에 성공한 회원정보(회원id,회원명)=>세션에 저장될 정보
			User user = loginService.login(memberid, password);
						
			//3.Model- request또는session.setAttribute("속성명",Object 값);
			HttpSession session = request.getSession();
			session.setAttribute("AUTH_USER", user); //p607 55라인
			
			//4.View - 성공시:index.jsp이동 
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return null;
		}catch(LoginFailException e) { //실패시:loginForm.jsp
			errors.put( "idOrPwNotMatch", Boolean.TRUE );
			return request.getContextPath()+FORM_VIEW;
		}
		
	}
	
	//좌우의 공백제거-p607 64라인
	private String trim(String str) {
		return  str==null?null:str.trim();
	}
	
	
}//class









