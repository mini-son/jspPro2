<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <meta charset="UTF-8">
 <title>로그인폼</title>
 <link rel="stylesheet" href="<%=request.getContextPath() %>/css/myCss.css">
 <!-- Bootstrap 4 CSS -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 <script>
 $(function(){

 });
 </script>
</head>
<body>
 <!-- Navigation -->
 <%@ include file="navigation.jsp" %>
 
  <!-- 내용 -->	
 <div class="container">
  <!-- page title -->
  <h2  class="mt-4 mb-3 text-center">Login(loginForm.jsp)</h2>    
  <form id="loginForm" action="login.do" method="post"> 
 	<div>
	 	<label for="memberid">ID</label>
	 	<!-- 교재에서는 <input type="text" name="id"/>  -->
	 	<input type="text" name="memberid" id="memberid" class="" />
	 	<c:if test="${errors.id}" ><span class="error">ID를 입력하세요</span></c:if>
 	</div>
 	<div>
	 	<label for="password">비밀번호</label>
	 	<input type="password" name="password" id="password" class="" /><br/>
	 	<c:if test="${errors.password}" ><span class="error">비밀번호를 입력하세요</span></c:if>
	 	<c:if test="${errors.idOrPwNotMatch}" ><span class="error">아이디 혹은 비밀번호가 일치하지 않습니다.</span></c:if>
 	</div>
 	<div>
	 	<input  type="submit" value="로그인(java용)" class=""/>
	 	<input  type="reset"  value="취소" class=""/>
	</div> 	
  </form>		
 </div>
 
 <%@ include file="bootstrap4js.jsp" %> 
</body>
</html>





