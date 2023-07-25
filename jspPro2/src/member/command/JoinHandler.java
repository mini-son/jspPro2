package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicationException;
import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;

//P597 - 회원가입요청에 따라 호출되는 클래스이다
//요청url - http://localhost:포트번호/컨패/join.do
//요청url - http://localhost/join.do
//          http://localhost/view/member/joinForm.jsp
//요청url->ControllerUsingURI->담당핸들러호출
public class JoinHandler implements CommandHandler  {

	//필드
	//private static final String FORM_VIEW ="/view/member/joinForm.jsp";
	private static final String FORM_VIEW ="/view/member/joinForm2_ajax.jsp";
	
	//생성자
	
	//담당컨트롤러의 요청메서드
	//요청주소 : http://localhost/join.do
	//파라미터
	//리턴유형 : String client에게 보여주는 jsp문서의 경로와 파일명
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("JoinHandler의 process()진입");
		System.out.println("request.getMethod()="+request.getMethod());
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
			return processSubmit(request,response); //p598 24라인
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	
	}
	
	//회원가입처리 //p598 35라인
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		//p598 36라인입력
		JoinRequest joinReq= new JoinRequest();
		
		//1.파라미터받기- p598 37라인
		String id = request.getParameter("memberid"); //회원id
		String name = request.getParameter("name");   //이름
		String password = request.getParameter("password"); //비번
		String confirmPassword = request.getParameter("confirmPassword"); //비번확인용
		joinReq.setId(id);
		joinReq.setName(name);
		joinReq.setPassword(password);
		joinReq.setConfirmPassword(confirmPassword);
		System.out.println("JoinHandler의 joinReq참조변수="+joinReq.toString());//콘솔 확인용
		
		//에러정보가 담기는 Map- p598 42라인
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors",errors);
		
		//필수입력(유효성)검사- p598 45라인
		joinReq.validate(errors);
		
		//p598 47라인
		if(!errors.isEmpty()) { //에러가 있으면  회원가입페이지로 이동
			return request.getContextPath()+FORM_VIEW;
		}
		
		//2.비즈니스로직처리<->Service<->DAO<->DB
		//p598 52라인
		try {
			JoinService joinService= new JoinService();
			joinService.join(joinReq);
			
			//3.Model - 세션,request객체명.setAttribute("속성명",Object값);
			
			//4.View-p599 53,56라인
			return request.getContextPath()+"/view/member/joinSucess.jsp";			
		}catch(DuplicationException e) {
			errors.put("duplicatedId",Boolean.TRUE); //id중복예외
			return request.getContextPath()+FORM_VIEW;
		}
	}


	//회원가입폼을 보여주기-p598 31라인
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return request.getContextPath()+FORM_VIEW;
	}
	
	
	
}
