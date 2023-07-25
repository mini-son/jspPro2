<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 이 문서는  JoinProcHandler에 의해서 view로 지정된 페이지이다
	 아래와 같이 Model작업되었다
	 request.setAttribute("속성명", Object 값);
	 request.setAttribute("insertedRowCnt", rowCnt);
--%>	
  <h2>joinSucess.jsp</h2>

  *request이용 모델: 입력된 행수=>${insertedRowCnt}<br/>
  attr1:${attr1}<br/>
  attr2:${attr2}<br/>
  uname:${uname}<br/>  
  
  <c:if test="${insertedRowCnt>=1}">
		회원가입을 축하합니다!!!<br/>  
  </c:if>  
  <c:if test="${insertedRowCnt==0}">
	<a href='joinForm.do'>회원가입하러 GO</a><br/>
  </c:if>  
  
  <hr/>
  *session이용 모델<br/>
    s_attr1:${s_attr1}<br/>
    s_attr2:${s_attr2}<br/>
    s_uname:${s_uname}<br/>
  
</body>
</html>













