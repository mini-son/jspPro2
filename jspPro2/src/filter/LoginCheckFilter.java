package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//로그인 여부 검사 기능-p612
// 비번찾기  등과 같이 로그인 여부를 검사할 때 필터링하는 역할 클래스
// 예) 요청주소 /checkPwd.do
public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//세션에 저장된 속성 유무를 검사
		//우리는 로그인에 성공한 회원정보(회원id,회원명)를 "AUTH_USER"이름으로 User클래스에 저장
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession();
		if( session==null ||  session.getAttribute("AUTH_USER")==null) {
			HttpServletResponse response = (HttpServletResponse)res;
			response.sendRedirect(request.getContextPath()+"/login.do"); 
		}else {
			chain.doFilter(req, res);
		}
	}

}

/*
//리턴     User - 로그인에 성공한 회원정보(회원id,회원명)=>세션에 저장될 정보
User user = loginService.login(memberid, password);
			
//3.Model- request또는session.setAttribute("속성명",Object 값);
HttpSession session = request.getSession();
session.setAttribute("AUTH_USER", user); //p607 55라인 */















