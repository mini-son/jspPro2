<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title></title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 <script>
 $(function(){

 });
 </script>
</head>
<body>
 <h2>imsi.jsp</h2>
 <%--  ChangePwdHandler.java컨롤러를 거쳐오게되면 아래와 같이 Model을 받는다
		request.setAttribute("rowCnt", rowCnt);
 	   //1이면 수정성공, 0이면 수정실패	
  --%>
  rowCnt:${rowCnt}<br/>
  *수정<span id="result"></span>

  <c:if test="${rowCnt eq 1}">
   <script>
   	  alert("success!!!");
	  document.getElementById("result").innerHTML="<strong>성공!</strong>";
   </script>
  
  </c:if>
  <c:if test="${rowCnt ne 1}">
  <script>
  	  alert("PLZ Retry.");
	  document.getElementById("result").innerHTML="<strong>실패. PLZ Retry.</strong>";
   </script>
  </c:if>
  
  
  
</body>
</html>











