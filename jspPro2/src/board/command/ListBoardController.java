package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardPage;
import board.service.BoardService;
import mvc.command.CommandHandler;


public class ListBoardController implements CommandHandler {

	private String FORM_VIEW = "/view/board/listBoard.jsp";
	private BoardService boardService = new BoardService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ListBoardController-process()진입");
		//1.파라미터받기
		String strPageNo = request.getParameter("pageNo"); //user가 선택한 페이지번호
		int pageNo = 1; //user가 선택안했을 때 기본적으로 보여지는 페이지번호
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		//2.비즈니스로직<->Service<->DAO<->DB
		/*파라미터  int pageNo : 보고싶은 페이지(=>현재 페이지)*/
		//ArticlePage articlePage = boardService.getArticlePage(pageNo);//총 게시글수
		BoardPage boardPage = boardService.getBoardPage(pageNo);
		
		
		//3.Model
	    /*ArticlePage articlePage에는  
	      총 게시글수포함(getTotal()호출)
		  article목록포함(getContent()호출) 
		  int  totalPages;	//총페이지수   
		  int  startPage;	//시작페이지  
		  int  endPage;		//끝페이지*/
		request.setAttribute("boardPage", boardPage);	//board목록,총페이지수 등
		//request.setAttribute("listBoard", listBoard); //board목록
		request.setAttribute("nowPage", pageNo); //현재페이지
		
		//4.View
		return request.getContextPath()+FORM_VIEW;
	}

}

