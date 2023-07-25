package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.command.JoinHandler;
import mvc.command.CommandHandler;
import mvc.command.NullHandler;


//p540
// /WEB-INF/commandHandlerURI.properties파일에 있는 정보롤 토대로
//  요청주소에 따른 담당Handler를 호출하고
//  특정 view페이지를   클라이언트에게 응답하는 클래스이다
public class ControllerUsingURI extends HttpServlet {
	//필드 - p540 22라인
    // <커맨드, 핸들러인스턴스> 매핑 정보 저장
    private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

    //메서드
	//init()는 서블릿을 처음 메모리에 올릴때 실행되어, 서블릿을 초기화하며 처음에 한번만 실행. 
    public void init() throws ServletException {
		// web.xml문서 설정부분에서   /WEB-INF/commandHandlerURI.properties를 가져와
		// String타입  configFile변수에 저장
        String configFile = getInitParameter("configFile");
        System.out.println("configFile="+configFile); //  /WEB-INF/commandHandlerURI.properties
        Properties prop = new Properties();//Properties객체
        System.out.println("prop="+prop);  //{}
        String configFilePath = getServletContext().getRealPath(configFile);
        System.out.println("configFilePath="+configFilePath);
        //configFilePath=C:\webStudy\jspWK\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\jspPro\WEB-INF\commandHandlerURI.properties
       
        //실행동작할 수 있는 파일로 만든다-p540  29라인
        try (FileReader fis = new FileReader(configFilePath)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new ServletException(e);
        }
        
        //메모리상의 Map에 저장된 key=value출력
        System.out.println("웹상의요청주소=담당컨트롤러명");
        Iterator keyIter = prop.keySet().iterator();
        while (keyIter.hasNext()) {
            String command = (String) keyIter.next();  //   /join.do
            String handlerClassName = prop.getProperty(command);//  member.command.JoinHandler
            try {
                Class<?> handlerClass = Class.forName(handlerClassName);
                CommandHandler handlerInstance = 
                        (CommandHandler) handlerClass.newInstance();
                commandHandlerMap.put(command, handlerInstance);
                
                System.out.println(command+"="+commandHandlerMap.get(command) );
            } catch (ClassNotFoundException | InstantiationException 
            		| IllegalAccessException e) {
                throw new ServletException(e);
            }
        }
        
    }

	//get방식요청시 호출.요청을 처리하고 응답을 생성
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("doGet()호출");//콘솔출력
    	process(request, response);
    }//doGet()

	//post방식요청시 호출.요청을 처리하고 응답을 생성
    protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("doPost()호출");
    	process(request, response);
    }

	//get방식요청,post방식요청시 호출-p541 60라인
    private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {       
		String command = request.getRequestURI();//   /경로
		//대상문자열.indexOf(찾는문자) 
		//=>대상문자열에서  찾는문자의 인덱스번호를 리턴(0부터시작)
		//=>대상문자열에서  찾는문자가 없으면 -1를 리턴 
		
		//대상문자열.substring(시작인덱스,끝인덱스-1)
		//시작인덱스가 0이면     대상문자열의 가장 첫 글자부터 추출
		//끝인덱스가   생략되면  대상문자열의 끝까지           추출
		//끝인덱스는   불포함되므로  실제적으로는 끝인덱스의 앞 글자까지 추출
		//예) substring(0,3)-> 대상문자열의 가장 첫 글자부터 끝인덱스의 앞 글자인 (0,2)로 추출된다
		
		//포트번호뒤에 /로 시작한다면 =>컨패로 시작한다면
		//request.getContextPath().length() : 컨패의 글자수
		//컨패를 server.xml에서 "/"로 지정하니
		//request.getContextPath().length()는 0로 리턴되었어요
		//결국엔   컨패뒤 부터  경로끝까지 추출해서  command변수에 저장
		if(command.indexOf(request.getContextPath())==0) {//p541 63라인
			command=command.substring(request.getContextPath().length());
		}
		
		
		//  /WEB-INF/commandHandlerURI.properties파일에 있는
		//요청주소에 따른 담당Handler를 가져오기
		//예) 요청주소=패키지명.담당Handler이름
		//예) /join.do=member.command.JoinHandler
		CommandHandler handler=commandHandlerMap.get(command); //p541 66라인
		if(handler==null) {
			handler = new NullHandler(); //담당Handler가 없으면 404응답으로 처리
		}
		
		//p541-70라인
		String viewPage = null;
		try { 
//viewPage에 담당handler의 리턴해주는 (client에게 보여주는)jsp문서의 경로와 파일명을 저장
			viewPage = handler.process(request, response);
		} catch (Throwable e) {//The Throwable class is the superclass of all errors and exceptions in the Java language
			e.printStackTrace();
			throw new ServletException();
		}//  /view/joinForm.jsp
		if( viewPage!=null ) {  //p540  76라인
			//RequestDispatcher의 forward()를 이용하여 view페이지로 강제이동
			//참고 <jsp:forward>액션태그(p177)
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
    }//process()끝
    
    
}








