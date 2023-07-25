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
 <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="#">
    <img src="../image/food2.jpg" alt="Logo" style="width:40px;">
  </a>

   <!-- Toggler/collapsibe Button   data-target="#Navbarlinks의 id값"   -->
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>

  <!-- Navbar links -->
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <!-- Links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="#">공지사항</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">QnA</a>
      </li>
      <!-- Dropdown -->
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
         회원관리
        </a>
        <div class="dropdown-menu">
          <a class="dropdown-item" href="#">회원목록조회</a>
          <a class="dropdown-item" href="#">VIP회원목록</a>
          <a class="dropdown-item" href="#">잠자는회원목록</a>
        </div>
      </li>
    </ul>
   </div> 
 </nav>

 <!-- 내용 -->
 <div class="container">
    <!-- page title -->
	<h2  class="mt-5 mb-4 text-center">페이지title</h2>
	내용블러블러~~
	<!-- button -->
	<div class="d-flex justify-content-end">
     <a href="" class="btn btn-outline-dark btn-sm">a요소아웃라인버튼</a>
	 <button type="submit"  class="btn btn-primary">submit용버튼</button>
	 <button type="button"  class="btn">버튼</button>
	 <button type="reset"   class="btn btn-secondary">reset버튼</button>
	</div>
 </div>
 	
 	
<!-- Bootstrap 4 JS -->
 <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>













