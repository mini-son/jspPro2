package board.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;


//요청주소   /board/download.do?boardno=글번호&imageFileName=이미지파일명
public class DownloadController implements CommandHandler {

	private static String imageRepository = "C:\\board\\image_repository"; //업로드된 이미지파일이 저장이 되는 실제위치

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//1.파라미터받기
		String boardno = request.getParameter("boardno");//글번호
		String imageFileName= request.getParameter("imageFileName");//이미지파일명
		
		//2.비즈니스로직  3.Model  
		// 이미지파일저장소의 (특정글번호)폴더에 저장되어 있는 File을 읽어들여
		// ->클라이언트에게 응답response(내보낸다)
		String path = imageRepository+"\\"+boardno+"\\"+imageFileName;
		File imageFile = new File(path);

		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment;fileName=" + imageFileName);
		
		FileInputStream in = new FileInputStream(imageFile);
		OutputStream out = response.getOutputStream();
		
		byte[] buffer = new byte[1024*8];
		while(true) {
			int cnt = in.read(buffer);
			if(cnt==-1) break;
			out.write(buffer,0,cnt);
		}
		
		in.close();
		out.close();
		
		return null;
	}

	
}

/*  response.setHeader("Cache-Control", "no-cache"); 
브라우저에는 캐시가 있는데, 
이것은 서버와의 불필요한 통신을 하지 않기 위해 마련된 공간.
최초 서버로부터 요청한 자원들(javascript, HTML, CSS, 이미지 등)을 내려받고 
같은 자원을 새로고침등을 통해서 다시 요청하는 경우 
브라우저는 실제로는 서버로 HTTP 요청을 하지 않고 
브라우저 자신의 캐시에 저장해 두었던 자원들을 사용하게 됨.

예를 들어 test.jsp 를 최초 요청한 경우 
서버로부터 응답된 자원들을 브라우저 캐시에 저장하고 
F5나 주소표시줄에 주소를 입력해 다시 test.jsp를 요청한 경우 
불필요하게 다시 HTTP 요청을 하는것이 아니라 
캐시에서 꺼내서 화면에 보여주는 것.
*/

/*response.setHeader("content-disposition", "attachment;fileName="+imageFileName);
 *일반적인 HTTP 응답에서 Content-Disposition 헤더는 
 *컨텐츠가 브라우저에 inline 되어야 하는 웹페이지 자체이거나 웹페이지의 일부인지, 
 *아니면 attachment로써 다운로드 되거나 로컬에 저장될 용도록 쓰이는 것인지를 알려주는 헤더
 *
 * fileName="+실제파일명
 */