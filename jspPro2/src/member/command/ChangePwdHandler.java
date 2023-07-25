package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.ChangePwdService;
import member.service.InvalidPwdException;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;


//622
//비번변경 요청을 담당하는 컨트롤러 클래스이다
//요청주소 http://localhost/changePwd.do
public class ChangePwdHandler implements CommandHandler {

	//필드
	private static final String FORM_VIEW ="/view/changePwdForm.jsp";
	private ChangePwdService changePwdService = new ChangePwdService();

	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ChangePwdHandler의 process()진입");
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
			return processSubmit(request,response); //p622 25라인
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	}//process

	//비번변경폼을 보여주기-p623 32라인
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return request.getContextPath()+FORM_VIEW;
	}
	
	//비번변경처리-p623 37라인
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		String curPwd = request.getParameter("curPwd"); //기존 비번
		String newPwd = request.getParameter("newPwd"); //새   비번
		User user = (User)request.getSession().getAttribute("AUTH_USER");
		
		//에러정보가 담기는 Map- p623 39라인
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors",errors); //Model

		//필수입력(유효성)검사- p623 47라인
		//에러가 있다면
		//Map참조변수 errors에  문자열타입으로 key로, True를 값으로 저장 
		if(curPwd==null || curPwd.isEmpty()) {
			errors.put( "curPwd", Boolean.TRUE );
		}
		if(newPwd==null || newPwd.isEmpty()) {
			errors.put( "newPwd", Boolean.TRUE );
		}
		
		//p623 53라인
		if(!errors.isEmpty()) { //에러가 있으면  비번변경폼페이지로 이동
			return request.getContextPath()+FORM_VIEW;
		}
		
		//p623 57라인
		try {
			//2.비즈니스로직<->Service<->DAO<->DB
			//Member클래스의 password필드의 값을 새 비번으로 변경
			/*파라미터  String userId-암호를 변경하고자 하는 회원id <- from  세션
			 			String curPwd-암호를 변경하고자 하는 회원의 현재 비번 <-from 파라미터
			 			String newPwd-암호를 변경하고자 하는 회원의 새   비번 <-from 파라미터 */
			int rowCnt = changePwdService.changePwd(user.getId(),curPwd,newPwd);
			System.out.println("ChangePwdHandler-비번변경 실행결과="+rowCnt);//콘솔 확인용
			//1이면 수정성공, 0이면 수정실패
			
			//3.Model
			request.setAttribute("rowCnt", rowCnt);
			
			//4.View- 성공시:imsi.jsp이동 
			return request.getContextPath()+"/view/imsi.jsp";		
		}catch(InvalidPwdException e) {//현재 암호가 일치하지 않는 경우
			errors.put( "badCurPwd", Boolean.TRUE );
			return request.getContextPath()+FORM_VIEW;
		}catch(MemberNotFoundException e) {//암호를 변경할 회원 데이터가 존재하지 않는 경우
			response.sendError(HttpServletResponse.SC_BAD_REQUEST); //400
			return null;
		}
		
	}
	
	
}








