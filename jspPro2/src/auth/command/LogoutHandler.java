package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

//p611
//요청주소  http://localhost/logout.do
//로그아웃요청처리 클래스이다 - 세션종료(정보삭제)
public class LogoutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession  session = request.getSession();
		if(session!=null) {
			session.invalidate(); //p611 16라인
		}
		//위의 2줄을 아래와 같이 줄일 수 있다.
		//request.getSession().invalidate();
		
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		
		return null;
	}

}










