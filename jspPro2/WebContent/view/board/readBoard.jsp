<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u"   tagdir="/WEB-INF/tags"%>
<%-- <c:set var="변수명"  value="변수값" /> --%>
<c:set var="cPath"  value="<%=request.getContextPath()%>" />
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
  //<input type="file" name="imageFileName" id="imageFileName" onchange="readURL(this);"/>
  //file을 선택하면 호출되는 메서드
  //매개변수 obj에는 이벤트가 발생한 객체=>change이벤트가 발생한 input요소객체 저장된다
  function readURL(obj){
	  if (obj.files && obj.files[0]) { //파일이 있드면
	         var reader = new FileReader(); //FileReader()객체생성
	         reader.onload = function (e) {
	        	 //id가 preview인 요소의 src속성값을 설정 =>img src속성값을 파일명으로 적용
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(obj.files[0]);
	  } 
  }
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
	

 </div>
<%@ include file="../bootstrap4js.jsp" %> 
</body>
</html>
