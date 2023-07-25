package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

//모든 컨트롤러는 반드시 CommandHandler 인터페이스를 구현하기로 약속하였다

//회원가입폼 화면 보여주는 요청에 대한 담당컨트롤러이다 
//요청주소  http://localhost/joinForm.do
//          http://localhost/view/member/joinForm.jsp
public class JoinFormHandler implements CommandHandler{
	          
	//요청메서드
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		//2.비즈니스로직수행-Service->DAO->DB
		//3.Model
		//4.View
		return request.getContextPath()+"/view/member/joinForm.jsp";
	}
}







