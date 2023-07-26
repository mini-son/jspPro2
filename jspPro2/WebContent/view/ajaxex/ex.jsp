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
	 $("#searchMmeberForm").submit(function(){		
			//검색어는 필수입력
			let userInput = $("#searchMember").val();  //<input type="search" name="searchMember" id="searchMember"/>
			if(userInput===""){ 
				alert("검색어는 필수입력입니다.");
				$("#searchMember").focus();
				return false;
			}
			
		$.ajax({
		  type:"GET", //요청타입."get" "post"
		  url:"${cPath}/member/search.do",   //요청url.예)"/board/list.do"
		  data:{searchMember:userInput}, //서버로 보내는 data
		  //dateType:"text",   //서버에서 응답받는 데이터type / 예)"json","xml","html","text"등
		  success: //정상 호출 함수
		    function(response){//response안에 응답내용
			  console.log(response);
			  alert("아싸~~~");	
		    },
		  error://오류발생 호출 함수. 4xx 또는 5xx
			    // jqXHR: XMLHttpRequest 객체
			    // textStatus: 에러 상태를 설명하는 문자열
			    // errorThrown: 에러의 예외 객체 (예외가 발생하지 않으면 undefined)
		  	function(jqXHR, textStatus, errorThrown) {
		      console.log("error:",textStatus,errorThrown);  	
		  	  alert("오류발생시 호출되는 함수. 오 NO~!!"); 	  
			}
		 });//ajax()끝
			
	  });//$("#searchMember").click()끝
 });
 </script>
</head>
<body>
 <h2>ex.jsp</h2>
 <form id="searchMmeberForm" method="GET">
  <input type="search" name="searchMember" id="searchMember"/>
  <input type="submit" value="조회"/>
 </form>
 <hr/>
 ${memberList}
  
</body>
</html>