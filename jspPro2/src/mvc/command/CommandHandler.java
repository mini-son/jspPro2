package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//p531
//인터페이스란? - 상수,추상메서드
//요청처리를 위한 담당컨트롤러의 요청메서드의 이름을 동일하게 하는 용도의 인터페이스
//모든 핸들러(담당컨트롤러)는 반드시 interface CommandHandler를 구현해야 한다
public interface CommandHandler {
	
	//담당컨트롤러의 요청메서드
	//리턴유형 : String client에게 보여주는 jsp문서의 경로와 파일명
	public String process(HttpServletRequest  request,
			              HttpServletResponse response) throws Exception;
}





