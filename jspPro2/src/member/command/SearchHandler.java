package member.command;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Member;
import member.service.MemberService;
import mvc.command.CommandHandler;

//요청주소  http://localhost/member/search.do?searchMember=홍
//회원검색 요청 담당 컨트롤러이다

public class SearchHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/ajaxex/ex.jsp";
	private MemberService memberService = new MemberService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기<input type="search" name="searchMember" id="searchMmeber"/>
		String sName = request.getParameter("searchMember");
		
		//2.비즈니스로직<->Service<->DAO<->DB
		//변수 String sName : user가 검색시 사용한 회원명
		//리턴 List<Member>: 회원들정보(no,memberid,name,regdate,password,isshow)	
		List<Member> memberList = memberService.searchMember(sName);
		
		//3.Model & 4.view
		//request.setAttribute("memberList", memberList);
		//return request.getContextPath()+FORM_VIEW;
		
		PrintWriter writer = response.getWriter();
		return null;
	}

}
