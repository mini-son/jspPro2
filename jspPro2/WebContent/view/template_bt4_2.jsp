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
  <link rel="stylesheet" href="https://bootswatch.com/4/Spacelab/bootstrap.min.css">  
 
  <!-- Custom CSS for fixed navbar -->
   <style>
     .fixed-top {
         z-index: 3;
     }

     body {
         padding-top: 60px;
     }
   </style>
 
</head>
<body>
 <!-- Navigation Bar 
class="navbar": Bootstrap에서 제공하는 네비게이션 바의 기본 스타일을 적용.
navbar-expand-lg: 이 클래스를 통해 반응형으로 동작하도록 설정합니다. 
		 뷰포트의 너비가 lg(1200px) 이상이면, 네비게이션 링크들이 수평으로 표시, 그렇지 않으면 해밍버거 메뉴로 축소.
navbar-dark: 텍스트와 상호작용이 있는 컴포넌트의 색상을 어둡게.
bg-dark: 네비게이션 바 배경색을 어둡게.
fixed-top: 화면 상단에 네비게이션 바를 고정-->
<!-- Navigation Bar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">로그인</a>
        
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
                aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <!-- Navbar Links -->
        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#">회원가입</a></li>
                <li class="nav-item"><a class="nav-link" href="#">게시판</a></li>
                <li class="nav-item"><a class="nav-link" href="#">About Us</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Contact Us</a></li>
            </ul>
        </div>
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXakfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz4fnFO9gybBvRLFyyN+kW/BQro3T8j6XI4lK7T7rM46_tC6Y1Bf/Dbdbp"
        crossorigin="anonymous"></script>
<script="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP8zjCGMXP5R6nX6KZQJcdTd/ftMf6nH16Pz9JvqBabTTLNZQbVfaGnt"
        crossorigin="anonymous"></script>        	
</body>
</html>













