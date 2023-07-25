package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleNotFoundException;
import article.service.OurArticleData;
import article.service.ReadArticleService;
import board.model.Board;
import board.service.BoardService;
import mvc.command.CommandHandler;

//상세보기 요청 컨트롤러이다
//요청주소  http://localhost/board/read.do?no=1
public class ReadBoardController implements CommandHandler {

	private BoardService boardService = new BoardService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		String strNo = request.getParameter("no"); //상세하게 보고 싶은 글번호
		int no = Integer.parseInt(strNo); //String을 int로 변환
		System.out.println("ReadBoardController- process() no="+no);
		
		//2.비즈니스로직<->Service<->DAO<->DB
		try {
			//파라미터 int no : 상세조회할 글 번호
			//리턴 Board board : 글번호,작성자id,제목,작성일,내용,이미지파일명
			Board board = boardService.getDetail(no);  
			
			//3.Model
			request.setAttribute("board", board);
			
			//4.View
			return request.getContextPath()+"/view/board/readBoard.jsp";
		}catch(ArticleNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);//404
			return null;
		}
		
	}

}
