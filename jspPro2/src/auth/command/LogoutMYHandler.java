package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

//로그아웃처리 요청시 호출되는 담당컨트롤러
//요청주소   http://logoutMY.do
/*성공했을 때 view=>index.jsp
					 000님/로그아웃/내정보 
  실패했을 때 view=>로그인폼JSP
 */
public class LogoutMYHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		//2.비즈니스로직수행<->Service<->DAO<->DB
		//3.Model
		//4.View -성공했을 때 view=>index.jsp
		//return request.getContextPath()+"/index.jsp";
		
		//4.View -실패했을 때 view=>로그인폼JSP
		//return request.getContextPath()+"/view/loginFormMY.jsp";
		response.sendRedirect(request.getContextPath()+"/view/loginFormMY.jsp");
		return null;
	}

}





