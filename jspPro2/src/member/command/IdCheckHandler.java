package member.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.JoinService;
import mvc.command.CommandHandler;

//요청주소  /member/idCheck.do
//ID중복검사요청 담당 컨트롤러이다

public class IdCheckHandler implements CommandHandler {

	JoinService joinService =  new JoinService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기  {inputId:유저가입력한id}, //서버로 보내는 data
		String id = request.getParameter("inputId"); //유저가입력한id
		System.out.println("IdCheckHandler의 process() id="+id);
		PrintWriter writer = response.getWriter();
		
		//2.비즈니스로직
		//파라미터  String id-유저가 입력한 id
		//리턴      int-이미 사용중인 id이면 1리턴, 사용가능한 id이면 0리턴,그외 -1
		int result = joinService.idCheck(id);
		if(result==0) { //사용가능한 id
			writer.print("usable");  //브라우저에게 문자열type으로 response한다 
		}else {  //여기에서는 간단하게 else로 처리
			writer.print("not_available");
		}
		/*리턴유형  문자열type으로 view에게 응답
	  	  사용 가능한  ID이면  "usable"*/
		
		//3.Model //4.View
		return null;
	}

}
