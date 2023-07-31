package comment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.Comment;
import comment.service.CommentService;
import mvc.command.CommandHandler;

//요청주소 http://localhost/comment/modify.do
//댓글 수정처리 담당 컨트롤러
/*data:{ //ajax방식으로  서버로 전송되는 data=>서버입장에서는 파라미터안에 담겨진 data
	commentno:commentno,
	boardno:boardno,
	writerid:writerid,
	content:content  }*/
public class ModifyCommentHandler implements CommandHandler {

	private CommentService commentService = new CommentService();
	@Override
	public String process(  HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기
		int commentno = Integer.parseInt(request.getParameter("commentno"));//댓글번호
		int boardno   = Integer.parseInt(request.getParameter("boardno"));
		String writerid= request.getParameter("writerid"); //댓글작성자id
		String content = request.getParameter("content"); //수정하려는 댓글 새내용
		
		//2.비즈니스로직<->Serivce<->DAO<->DB
		Comment comment  = new Comment(commentno,boardno,writerid,content,null);
		//파라미터 Comment : 댓글번호,원글번호,댓글작성자id,댓글 새내용
		//리턴 int : 수정성공시 1반환, 수정실패시 0반환
		int rowCnt =commentService.modifyComment(comment);
		
		String modifyResult = "";
		if(rowCnt==1) modifyResult = "success";  
		if(rowCnt==0) modifyResult = "fail";  
		
		//3.Model &4.View
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(modifyResult);
		return null;
	}

}
