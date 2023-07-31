package board.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleNotFoundException;
import board.model.Board;
import board.service.BoardService;
import comment.model.Comment;
import comment.service.CommentService;
import mvc.command.CommandHandler;

//상세보기 요청 컨트롤러이다
//요청주소  http://localhost/board/read.do?no=1
public class ReadBoardController implements CommandHandler {

	private BoardService boardService = new BoardService();
	private CommentService commentService = new CommentService();

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
			
			//상세조회할 글 번호에 등록된 댓글목록조회
			//파라미터 no - 원글번호
			//리턴     List<Comment> - 특정원글에 작성된 댓글정보(댓글번호,원글번호,댓글작성자,댓글내용,댓글등록일)
			List<Comment> comments = commentService.getCommentList(no);
			
			//3.Model
			request.setAttribute("board", board);	   //(원글)상세정보	
			request.setAttribute("comments",comments); //댓글목록
			
			//4.View
			return request.getContextPath()+"/view/board/readBoard.jsp";
		}catch(ArticleNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);//404
			return null;
		}
		
	}

}
