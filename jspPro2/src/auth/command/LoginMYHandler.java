package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;


//로그인폼화면을 보여주는 담담컨트롤러
//요청주소  http://localhost/loginMY.do
//          http://localhost/view/loginFormMY.jsp
public class LoginMYHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		//2.비즈니스로직수행<->Service<->DAO<->DB
		//3.Model
		//4.View
		return request.getContextPath()+"/view/loginFormMY.jsp";
	}

}
