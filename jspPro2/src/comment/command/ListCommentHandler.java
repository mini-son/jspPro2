package comment.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.Comment;
import comment.service.CommentService;
import mvc.command.CommandHandler;

//요청주소    http://localhost/comment/list.do
//댓글목록조회 담당 컨트롤러이다

public class ListCommentHandler implements CommandHandler {

	
	private CommentService commentService = new CommentService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		int no = Integer.parseInt(request.getParameter("no"));//원글번호
		
		//2.비즈니스로직<->Service<->DAO<->DB
		//파라미터 no - 원글번호
		//리턴     List<Comment> - 특정원글에 작성된 댓글정보(댓글번호,원글번호,댓글작성자,댓글내용,댓글등록일)
		List<Comment> comments = commentService.getCommentList(no);
		
		//3.Model
		request.setAttribute("comments",comments);
		
		//4.View
		return null;
	}

}









