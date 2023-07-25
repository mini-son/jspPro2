package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

//로그인폼화면 보여줘 요청을 담당하는 컨트롤러
//요청주소  http://localhost/loginFormMY.do
//          http://localhost/view/loginFormMY.jsp
public class LoginFormMYHandler implements  CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		//2.비즈니스로직
		//3.Model
		//4.View
		return request.getContextPath()+"/view/loginFormMY.jsp";
	}
	
}
