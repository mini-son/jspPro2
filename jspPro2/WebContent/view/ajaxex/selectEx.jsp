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
 
 	$.ajax({
	  type:"get", //요청타입."get" "post"
	  url:"${cPath}/sidoSelect.do",   //요청url.예)"/board/list.do"
	  dateType:"json",   //서버에서 응답받는 데이터type / 예)"json","xml","html","text"등
	  success: //정상 호출 함수
	    function(response){
		  //response안에 응답내용이 문자열로 변환된 (JSONObject객체를) 받는다 
		 console.log(response); 
		 
		 var sidoSelect = $("#sido");//<select name="sido" id="sido">
		 sidoSelect.empty();
		 sidoSelect.append('<option value="">선택하세요</option>');
		 		 
		 for( const sido in  response ){
			 console.log(sido)
			 sidoSelect.append('<option value="'+sido+'">'+sido+'</option>');
		 } 
		 
		 //두 번째 select문에서 선택시 호출 -> 해당 시군구를 가져와 2번째 select에 출력
		 sidoSelect.change(function(){
			 const sigunguArr = response[$(this).val()] || [];//서울시 경기도 ""
			 console.log(sigunguArr);
			 var sigunguSelect = $("#sigungu");//<select name="sigungu" id="sigungu">
			 sigunguSelect.empty();
			 sigunguSelect.append('<option value="">선택하세요</option>');
			 
			 for( const i in sigunguArr ){
				 console.log(i)
				 sigunguSelect.append('<option value="'+sigunguArr[i]+'">'+sigunguArr[i]+'</option>');
			 }
		 });//체인지이벤트끝
		
	    },
	  error://오류발생 호출 함수. 4xx 또는 5xx
		    // jqXHR: XMLHttpRequest 객체
		    // textStatus: 에러 상태를 설명하는 문자열
		    // errorThrown: 에러의 예외 객체 (예외가 발생하지 않으면 undefined)
	  	function(jqXHR, textStatus, errorThrown) {
	      console.log("error:",textStatus,errorThrown);  	
	  	  alert("no~"+textStatus); 	  
		}
	 });//ajax()끝
 	

 });//jQuery끝
 </script>
</head>
<body>
 <h2>동적 select 구현</h2>

 <form id="frm">
  <label for="sido">시/도</label>
  <select name="sido" id="sido">
  	<option value="">선택하세요</option>
  </select>
  <label for="sigungu">시/군/구</label>
  <select name="sigungu" id="sigungu">
  	<option value="">선택하세요</option>
  </select>
 </form> 

</body>
</html>
