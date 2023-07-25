<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MAIN</title>
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
<style>
  #i1 {font-weight:900;}
	
  .intro {
   color: white;
   background-color: green;
  }
  .pointer {cursor: pointer;}

</style>
<script>
$(document).ready(function(){
	//<button type="button" id="LogoutBtn">로그아웃</button>
	$("#LogoutBtn").on("click",function(){
		//js의 location객체의 href속성의 값을 가져오기=>window의 현재url을 리턴 
		//location.href; 
		
		//js의 location객체의 href속성의 값을 설정하기=>window의 현재url을 변경 
		//location.href="설정값";
		location.href="http://localhost/logout.do";
	});
	
	//<span id="spanLogout">(span요소)로그아웃</span>
	$("#spanLogout").on("click",function(){
		location.href="http://localhost/logout.do";
	}).on("mouseover mouseout", function(){
	    $(this).toggleClass("intro pointer");
	});
});
</script>
</head>
<body>

 <!-- Navigation -->
 <%@ include file="../view/navigation.jsp" %>
 
  <!-- 내용 -->	
 <div class="container">
  <!-- page title -->
  <h2  class="mt-4 mb-3 text-center">index.jsp(메인화면)</h2>    

  <pre>프로토콜://ip주소:포트번호/컨패/경로
     http://localhost/
     http://localhost/index.jsp
  </pre>	
 
  <a href="/article/list.do">article게시판보기</a><br/>
  <a href="/board/list.do">board게시판보기</a><br/>
 <%-- 비로그인시 보여지는 부분 시작 --%>
 <c:if test="${empty AUTH_USER}">
  <a href="join.do">회원가입</a>
  <a href="login.do">로그인</a>
  <br/><br/><br/>
 </c:if> 
 <%-- 비로그인시 보여지는 부분 끝 --%>
 
 <%-- LoginHandler.java를 거쳐오게되면 아래와 같이 Model넘어온다
    //User - 로그인에 성공한 회원정보(회원id,회원명)=>세션에 저장될 정보
	User user = loginService.login(memberid, password);
	session.setAttribute("AUTH_USER", user); 
  --%>
 <%-- (user)로그인을 했을 경우 보여지는 부분 시작 --%>
 <c:if test="${!empty AUTH_USER  and  AUTH_USER.id!='adminid'}"> 
    <span id="i1">${AUTH_USER.name} 님</span> 
 	<button type="button" id="LogoutBtn">(버튼)로그아웃</button>
 	<a href="logout.do">(a요소)로그아웃</a>
 	<span id="spanLogout">(span요소)로그아웃</span><br/>
 	<a href="changePwd.do">비번변경</a>
 	<a href="#">내 정보</a>
 	<a href="/article/write.do">글쓰기</a>
 	<br/><br/><br/>
</c:if>
<%-- 로그인을 했을 경우 보여지는 부분 끝 --%>

<%-- (관리자)로그인을 했을 경우 보여지는 부분 시작 --%>
 <c:if test="${!empty AUTH_USER  and  AUTH_USER.id=='adminid'}"> 
    <span id="i1">${AUTH_USER.name} 님</span> 
 	<button type="button" id="LogoutBtn">(버튼)로그아웃</button><br/>
 	<a href="changePwd.do">비번변경</a><br/>
 	<a href="#">공지사항관리</a><br/>
 	<a href="#">공지사항입력</a><br/>
 	<a href="#">회원관리</a><br/>
 	<br/><br/><br/>
</c:if>
<%-- 로그인을 했을 경우 보여지는 부분 끝 --%>


<%@ include file="../view/bootstrap4js.jsp" %> 
</body>
</html>








