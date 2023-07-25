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
	<h2  class="mt-5 mb-4 text-center">template_bt4_main.jsp</h2>
	내용블러블러~~
	<!-- button -->
	<div class="d-flex justify-content-end">
     <a href="" class="btn btn-outline-dark btn-sm">a요소아웃라인버튼</a>
	 <button type="submit"  class="btn btn-primary">submit용버튼</button>
	 <button type="button"  class="btn">버튼</button>
	 <button type="reset"   class="btn btn-secondary">reset버튼</button>
	</div>
 </div>

<%@ include file="bootstrap4js.jsp" %> 	

</body>
</html>













