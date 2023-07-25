<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,jdbc.connection.ConnectionProvider" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h2>dbConnection테스용 페이지</h2>
 <%
 try{ 	
	 	Connection conn = ConnectionProvider.getConnection();
		out.println("커넥션 연결 성공!!"); //브라우저출력
	 }catch(SQLException e){
		 out.println("커넥션 연결 실패:"+e.getMessage()); //브라우저출력
		 e.printStackTrace(); // 실행순서,에러발생위치,원인등 콘솔출력
		 application.log("커넥션 연결 실패:",e);//jsp의 내장객체application의 log()이용 콘솔출력
	 }
 %>
 
</body>
</html>