<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
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

 <%-- ListBoardController로 부터  
 	 request.setAttribute("nowPage", pageNo); //현재페이지
 	 //request.setAttribute("listBoard", listBoard); //board목록
     request.setAttribute("boardPage", boardPage);	//board목록,총페이지수 등 
 
     BoardPage boardPage에는  
	      총 게시글수포함(getTotal()호출)
		  board목록포함(getContent()호출) 
		  int  totalPages;	//총페이지수   
		  int  startPage;	//시작페이지  
		  innt  endPage;	//끝페이지--%> 
 boardPage : ${boardPage}
 <!-- 내용 -->	
 <div class="container"><!-- page title -->
	<h2  class="mt-4 mb-3 text-center">listBoard</h2>
 	
 	<!-- Board List table -->
 	<div>총 게시글수 : ${boardPage.total}건 / 현재페이지 : ${nowPage}</div>
 	<table class="table table-hover">
 	 <thead  class="thead-light">
 	  <tr>
 	  	<th scope="col">번호</th>
 	  	<th scope="col">제목</th>
 	  	<th scope="col">작성자</th>
 	  	<th scope="col">작성일</th>
 	  </tr>
 	 </thead>
 	 <tbody> <%-- 총게시글(수가 0이면)없으면  true리턴, 그렇지 않으면 false --%>
 	  <c:if test="${boardPage.hasNoBoards()}"> 
 	  <tr>
 	  	<td colspan="4">게시글이 존재하지 않습니다. 첫번째 작성자가 되세요~</td>
 	  </tr>
 	  </c:if>
 	 
 	  <%-- <c:forEach>반복문이용 1페이지당 출력할 게시글수 만큼 반복 출력 시작 
 	  <c:forEach var="변수명"  items="배열명 또는 컬렉션" >--%>
 	  <c:forEach var="board"  items="${boardPage.content}" >
 	  <tr>
 	  	<td>${board.number}</td>
 	  	<%-- read.do?no=상세하게보고싶은글번호&pageNo=현재페이지"  --%>
 	  	<td><a href="read.do?no=${board.number}&pageNo=${nowPage}">${board.title}</a></td>
 	  	<td>${board.writerId}</td>
 	  	<td>${board.writeDate}</td>
 	  </tr>
 	 </c:forEach> <%--내용출력 끝 --%>
 	 </tbody>
 	</table>
 	<div>
 	  <a href="<%=request.getContextPath()%>/board/write.do" class="btn btn-outline-dark btn-sm">글 쓰기</a>
 	</div>
 	<br/>
 	
 	<%--paganation 출력부분 --------------------------------------------   --%>
 	<!-- nav요소를 이용하여 paganation을 감싼다 -->
 	<nav aria-label="Page navigation"> <!--  justify-content-center클래스는 가운데정렬  -->
	 <ul class="pagination justify-content-center" style="margin:0 0">
	  <%--<c:if>이용하여 노출여부가 달라진다  --%>
 	  <c:if test="${boardPage.startPage>5}">
	   <li class="page-item"><a class="page-link" href="list.do?pageNo=${boardPage.startPage-5}">&lt;&lt;prev</a></li>
	  </c:if> 
	  
	  <%--p653 43라인 <c:forEach></c:forEach>반복문이용 --%>
	  <c:forEach var="pNo"  begin="${boardPage.startPage}" 
						    end="${boardPage.endPage}"  step="1">
	   <li class="page-item"><a class="page-link" href="list.do?pageNo=${pNo}">${pNo}</a></li>
	  </c:forEach>
	   
	  <%--<c:if>이용하여 노출여부가 달라진다 --%>
	  <c:if test="${boardPage.endPage< boardPage.totalPages}"> 
	   <li class="page-item"><a class="page-link" href="list.do?pageNo=${boardPage.startPage+5}">next&gt;&gt;</a></li>
	  </c:if> 
	 </ul> 	
    </nav>
 </div>	
 
<%@ include file="../bootstrap4js.jsp" %> 
</body>
</html>





























