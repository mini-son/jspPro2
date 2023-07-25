<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">
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
      <%-- 비로그인시 보여지는 부분 시작 --%>
 	  <c:if test="${empty AUTH_USER}">
      <li class="nav-item">
		  <a class="nav-link" href="<%=request.getContextPath()%>/join.do">회원가입</a>
	  </li>	
 	  <li class="nav-item">
		  <a class="nav-link"  href="<%=request.getContextPath()%>/login.do">로그인</a>
	  </li>	  
 	  </c:if> <%-- 비로그인시 보여지는 부분 끝 --%>
      <%-- 로그인을 했을 경우 보여지는 부분 시작 --%>
      <li class="nav-item">
 		<c:if test="${!empty AUTH_USER}"> 
        <a class="nav-link" href="#">${AUTH_USER.name} 님</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">로그아웃</a>
      </li>  
      </c:if>
    </ul>
   </div> 
 </nav>
 
 
 
 
 
 
 
 
 
 
 
 
 