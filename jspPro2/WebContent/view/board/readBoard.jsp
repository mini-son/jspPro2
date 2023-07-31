<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u"   tagdir="/WEB-INF/tags"%>
<c:set var="cPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <title></title>
 <!-- Bootstrap 4 CSS -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
 <!-- Flatly 테마 CSS -->
 <!-- <link rel="stylesheet" href="https://bootswatch.com/4/flatly/bootstrap.min.css"> -->
 <!--<link rel="stylesheet" href="https://bootswatch.com/4/Cosmo/bootstrap.min.css"> --> 
 <link rel="stylesheet" href="https://bootswatch.com/4/cerulean/bootstrap.min.css">  
    
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 <script>
 $(function(){

 });
 </script>
</head>
<body>
 <!-- Navigation -->
 <%@ include file="../navigation.jsp" %> 			

<%--//Board board : 글번호,작성자id,제목,작성일,내용,이미지파일명
	Board board = boardService.getDetail(no);  
    request.setAttribute("board", board);  --%>
 board : ${board}
 <!-- 내용 -->	
 <div class="container">
<!-- page title -->
	<h2  class="mt-4 mb-3 text-center">상세보기(readBoard)+수정,삭제기능</h2>
	
	 ★업로드기능있는 form은 반드시 method="post" enctype="multipart/form-data"★
	<form id="boardForm" action="${cPath}/board/modify.do" method="post"   enctype="multipart/form-data" >
	 <input type="hidden" name="boardno"   id="boardno" value="${board.number}"/>
	 <input type="hidden" name="writerid"  id="writerid" value="${board.writerId}"/>
	<table class="table table-bordered mt-3">
	 <tbody>
	  <tr>
	   <th scope="row" style="width:15%;">글번호</th>
	   <td>${board.number}</td>
	  </tr>
	  <tr>
	   <th scope="row">작성자id</th>
	   <td>${board.writerId}</td>
	  </tr>
	  <tr>
	   <th scope="row"><label for="title">제목</label></th>
	   <td><input type="text" name="title" id="title" value="${board.title}" ></td>
	  </tr>
	  <tr>
	   <th scope="row">작성일</th>
	   <td><fmt:formatDate value="${board.writeDate}"   pattern="yyyy년 M월 d일" /></td>
	  </tr>
	  <tr>
	   <th scope="row"><label for="content">내용</label></th>
	   <td><textarea name="content" id="content">${board.content}</textarea></td>
	  </tr>
	  <%-- 첨부이미지파일이 존재하면 아래 tr이 보여진다 --%>
	  <c:if test="${not empty board.imageFileName && board.imageFileName!=null}">
	  <tr>
	   <th scope="row">이미지</th>
	   <td>
	   	<input type="hidden" name="originalFileName" id="originalFileName" value="${board.imageFileName}"/>
	   	<img src="${cPath}/board/download.do?boardno=${board.number}&imageFileName=${board.imageFileName}" 
	   			 id="preview" style="width:250px;"/>
	   	</td>
	  </tr>
	  <tr>
	   <th scope="row"><label for="imageFileName">수정용이미지</label></th>
	   <td><input type="file" name="imageFileName" id="imageFileName" onchange="readURL(this);"/></td>
	  </tr> 
	  </c:if>
	 </tbody>	
	</table>
	<!-- button --> 
	<!-- d-flex:한개의 row를 block레벨로 차지 
	   flex-start:왼쪽정렬(기본)/ flex-end:오른쪽정렬 / flex-center:가운데정렬
	   justify-content-end : 오른쪽정렬-->
 	<div class="d-flex justify-content-end">
	 <c:set var="pageNo" value="${empty param.pageNo?1:param.pageNo}" /> 
     <a href="${cPath}/board/list.do?pageNo=${pageNo}" class="btn btn-outline-dark">목록보기</a>
  
     <c:if test="${AUTH_USER.id==board.writerId}">
	  <input type="submit" value="게시글수정"/>
     </c:if>
     	
	 <c:if test="${AUTH_USER.id eq board.writerId}">
	   <a href="${cPath}/board/delete.do?no=${board.number}" class="btn btn-outline-dark">게시글삭제(del)</a>
	 </c:if>
	</div>
	</form>	
	
	<%-- 댓글목록 시작 --%>
    <h3 class="mt-4">댓글</h3>
      <table class="table mt-3" id="commentTable">
          <thead>
              <tr>
                  <th scope="col">댓글번호</th>
                  <th scope="col">작성자</th>
                  <th scope="col">댓글 내용</th>
                  <th scope="col">작성일</th>
                  <th scope="col">수정</th>
                  <th scope="col">삭제</th>
              </tr>
          </thead>
          <tbody>
              <c:choose>
                  <c:when test="${not empty comments}">
                      <c:forEach var="comment" items="${comments}">
                          <tr>
                              <td>${comment.commentno}</td>
                              <td>${comment.writerId}</td>
                              <td>${comment.content}</td>
                              <td><fmt:formatDate value="${comment.writedate}" pattern="yyyy-MM-dd" /></td>
                              <td>
                                  <c:if test="${comment.writerId == sessionScope.AUTH_USER.id}">
                                      <button type="button" class="btn btn-sm btn-primary" 
                                               data-toggle="modal" data-target="#editModal" 
                                               data-commentno="${comment.commentno}" 
                                               data-boardno="${comment.boardno}" 
                                               data-writerid="${comment.writerId}" 
                                               data-content="${comment.content}">수정</button>
                                  </c:if>
                              </td>
                              <td>
                                  <c:if test="${comment.writerId == sessionScope.AUTH_USER.id}">
                                      <button type="button" class="btn btn-sm btn-danger" id="commentDelBtn" onclick="deleteComment(${comment.commentno})">삭제</button>
                                  </c:if>
                              </td>
                          </tr>
                      </c:forEach>
                  </c:when>
                  <c:otherwise>
                      <tr>
                          <td colspan="6">등록된 댓글이 없습니다.</td>
                      </tr>
                  </c:otherwise>
              </c:choose>
          </tbody>
      </table><%-- 댓글목록 출력 끝 --%>

        <%-- 댓글 작성 폼 --%>
        <div class="mt-4">
            <h4>새로운 댓글 작성</h4>
            <form id="commentForm" action="${cPath}/comment/write.do" method="post">
                <input type="hidden" name="boardno" value="${board.number}">
                <div class="form-group">
                    <label for="writerId">작성자</label>
                    <input type="text" class="form-control" id="comment_writerId" name="comment_writerId" value="${sessionScope.AUTH_USER.id}" required="required">
                </div>
                <div class="form-group">
                    <label for="content">댓글 내용</label>
                    <textarea class="form-control" id="comment_content" name="comment_content" required="required"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">댓글 작성</button>
            </form>
        </div>
    </div>

    <!-- 수정 모달창 -->
    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">댓글 수정</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="editForm" method="post">
                        <input type="hidden" name="commentno" id="commentno" />
                        <input type="hidden" name="boardno"   id="boardno" />
                        <div class="form-group">
                            <label for="writer">작성자</label>
                            <input type="text" class="form-control" id="editWriterId" name="writerId" required>
                        </div>
                        <div class="form-group">
                            <label for="editContent">댓글 내용</label>
                            <textarea class="form-control" id="editContent" name="content" required></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                    <button type="button" class="btn btn-primary" onclick="updateComment()">수정 완료</button>
                </div>
            </div>
        </div>
    </div><!-- 수정 모달 끝 -->
	<script>
    // 수정 모달이 나타날 때 정보를 설정
    $("#editModal").on("show.bs.modal", function (event) {
        var button = $(event.relatedTarget); 
        var commentno = button.data('commentno'); // data-commentno속성의 값
        var boardno   = button.data('boardno'); 
        var writerid  = button.data('writerid'); 
        var content   = button.data('content'); 

        // Modal창 내용 출력
        var modal = $(this);
        modal.find('#commentno').val(commentno);
        modal.find('#boardno').val(boardno);
        modal.find('#editWriterId').val(writerid);
        modal.find('#editContent').val(content);
    });

    //모달창에서 수정완료 버튼 클릭시 호출
    function updateComment() {
        // 수정된 댓글 정보 가져오기
        var commentno = $('#commentno').val();
        var boardno = $('#boardno').val();
        var writerid = $('#editWriterId').val(); 
        var content = $('#editContent').val(); //수정된 댓글내용

        //수정처리
        $.ajax({
            type: 'POST',
            url: '${cPath}/comment/modify.do',
            data: { //서버로 전송되는 data=>서버입장에서는 파라미터안에 담겨진 data
            		commentno:commentno,
            		boardno:boardno,
                	writerid:writerid,
                	content:content
            },
            dataType: 'text',
            success: function(response) {
				if(response==="success"){  //수정성공
					location.reload();
				}else{ //수정실패시
					alert("댓글 수정이 실패되었습니다.");
				}
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // 오류 발생 시 처리
                console.log('Error:', textStatus, errorThrown);
                alert('댓글 수정에 오류가 발생했습니다.');
            }
        });
    }

    
    
</script>
	
<%@ include file="../bootstrap4js.jsp" %> 
</body>
</html>


            











