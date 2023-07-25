<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비번변경폼</title>
</head>
<body>
 <h2>changePwdForm.jsp</h2>
 <pre>	 
     jsp만의 주소는 http://localhost/view/changePwdForm.jsp이지만
     http://localhost/changePwd.do요청에 의해서 강제로 보여지게 되는 view이다
 </pre>    
 <div class="container">
  <h2>비밀번호변경</h2>
  <form id="changePwdForm" action="changePwd.do" method="post"> 
 	<div>
	 	<label for="curPwd">현재 비밀번호</label>
	 	<input type="text" name="curPwd" id="curPwd" class="" />
	 	<c:if test="${errors.curPwd}">현재 비밀번호를 입력하세요</c:if>
	 	<c:if test="${errors.badCurPwd}">현재 비밀번호와 일치하지 않습니다</c:if>
 	</div>
 	<div>
	 	<label for="newPwd">새 비밀번호</label>
	 	<input type="password" name="newPwd" id="newPwd" class="" />
	 	<c:if test="${errors.newPwd}">새 비밀번호를 입력하세요</c:if>
 	</div>
 	<div>
	 	<input  type="submit" value="비번변경" class=""/>
	 	<input  type="reset"  value="취소" class=""/>
	</div> 	
  </form>		
 </div>
</body>
</html>





