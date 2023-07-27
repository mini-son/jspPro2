package member.command;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import member.model.Member;
import member.service.MemberService;
import mvc.command.CommandHandler;

//요청주소  http://localhost/member/search.do
//회원검색 요청 담당 컨트롤러이다

public class SearchHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/ajaxex/ex.jsp";
	private MemberService memberService = new MemberService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SearchHandler의 process()진입");
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {//요청방식이 get방식이면  FORM_VIEW 보여주기
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//요청방식이 post방식이면 회원가입처리
			return processSubmit(request,response); 
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	}//process


	//페이지 보여주기
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return request.getContextPath()+FORM_VIEW;
	}
	
	//요청처리
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		//1.파라미터받기<input type="search" name="searchMember" id="searchMmeber"/>
		String sName = request.getParameter("searchMember");
		System.out.println("sName="+sName);
		//2.비즈니스로직<->Service<->DAO<->DB
		//변수 String sName : user가 검색시 사용한 회원명
		//리턴 List<Member>: 회원들정보(no,memberid,name,regdate,password,isshow)	
		List<Member> memberList = memberService.searchMember(sName);
		System.out.println("meberList="+memberList.size()); //검색결과로 받은 회원수
		
		//3.Model & 4.view
		//request.setAttribute("memberList", memberList);
		//return request.getContextPath()+FORM_VIEW;

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		JSONObject totalObj= new JSONObject();
		JSONArray memberArr = new JSONArray(); //배열준비
		
		for( int i=0; i<memberList.size(); i++ ) {
			JSONObject memberObj= new JSONObject();
			memberObj.put("no",memberList.get(i).getNo());
			memberObj.put("memberid",memberList.get(i).getMemberid());
			memberObj.put("name",memberList.get(i).getName());
			memberObj.put("regdate",memberList.get(i).getRegDate().toString());
			memberObj.put("password",memberList.get(i).getPassword());
			memberObj.put("isshow",memberList.get(i).getIsshow());
			memberArr.add(memberObj); //1명의 회원정보를 배열에 추가
		}
		
		totalObj.put("members",memberArr);
		
		//view에 응답할 때에는 JSONObject객체를 문자열로 변환시켜서 넘긴다
		String jsonStr = totalObj.toJSONString();
		System.out.println("jsonStr ="+jsonStr); //콘솔출력.확인용
		out.print(jsonStr); //client로 보내기
		return null;
	

	}
	



}
