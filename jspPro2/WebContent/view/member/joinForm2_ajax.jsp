<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="cPath"  value="<%=request.getContextPath()%>"/>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입폼</title>
<link rel="stylesheet" href="${cPath}/css/myCss.css">
<script src="${cPath}/js/jquery-3.7.0.min.js"></script>
<script>
$(document).ready(function(){
	//<input type="button" value="ID중복검사" id="idCheckBtn"/>
	//$(선택자).action();
	$("#idCheckBtn").click(function(){		
		//회원id는 필수입력
		let userInputId = $("#memberid").val();  //<input type="text" name="memberid" id="memberid" class="" />
		if(userInputId===""){ 
			alert("jq 회원id는 필수입력입니다.");
			$("#memberid").focus();
			return;
		}
		
	$.ajax({
	  type:"post", //요청타입."get" "post"
	  url:"${cPath}/member/idCheck.do",   //요청url.예)"/board/list.do"
	  data:{inputId:userInputId}, //서버로 보내는 data
	  dateType:"text",   //서버에서 응답받는 데이터type / 예)"json","xml","html","text"등
	  success: //정상 호출 함수
	    function(response){//response안에 응답내용
		  console.log(response);
		  if(response==="usable"){
		  	$("#result").text("사용 가능한  ID입니다.");
		  }else{
		  	$("#result").text("이미 사용중인 ID입니다.");
	  	  }
	    },
	  error://오류발생 호출 함수. 4xx 또는 5xx
		    // jqXHR: XMLHttpRequest 객체
		    // textStatus: 에러 상태를 설명하는 문자열
		    // errorThrown: 에러의 예외 객체 (예외가 발생하지 않으면 undefined)
	  	function(jqXHR, textStatus, errorThrown) {
	      console.log("error:",textStatus,errorThrown);  	
	  	  alert("오류발생시 호출되는 함수. 오 NO~!!");
	  	  
		},
	  complete: //완료후 호출 함수
		function(data,textStatus){
		  console.log(data,textStatus);
		  alert("완료후 호출되는 함수부분");
	    },
	  async:true //비동기여부.true(기본.비동기) 또는 false(동기)
	});//ajax()끝
		
  });//$("#idCheckBtn").click()끝

	
});//jQuery끝
</script>
<%--
<script>
$(document).ready(function(){
	
	//<form id="joinForm">이지만 아래와 같은 효과
	//<form id="joinForm" action="join.do" method="post" onsubmit="return validCK();">
	$("#joinForm").on("submit",function(){

		//회원id는 필수입력
		let memberidObj = $("#memberid");
		if(memberidObj.val()==""){
			alert("jq 회원id는 필수입력입니다.");
			memberidObj.focus();
			return false;
		}
		
		//비번<input type="password" name="password" id="password" class=""/>
 		let passwordObj = $("#password");
 		if(passwordObj.val()==""){
 			alert("jq 비번은 필수입력입니다.");
 			passwordObj.focus();
 			return false;
 		}
 		
 		
 		//회원명<input type="text" name="name" id="name" class=""/>
 		let nameObj = $("#name");
 		if(nameObj.val()==""){
 			alert("jq 이름은 필수입력입니다.");
 			nameObj.focus();
 			return false;
 		}
 		
 		
 		//가입일<input type="date" name="regDate" id="regDate" class=""/>
 		let regDateObj = $("#regDate");
 		if(regDateObj.val()==""){
 			alert("jq 날짜를 선택하세요");
 			regDateObj.focus();
 			return false;
 		}
		
 		
 		//성별 필수선택
 		//<input type="radio" name="gender" id="gender0" class="" value="male"/>남성
 		//$("input[name='gender']")  input요소중에 name속성의 값이 gender인 모든 요소들
 		/*$("input[name='gender']").is(":checked")는
 		input요소중에 name속성의 값이 gender인 모든 요소들중에 선택된 요소가 있으면 true리턴*/
 		let genderChecked=$("input[name='gender']").is(":checked");
 		console.log(genderChecked);
 		if(!genderChecked){
 			alert("jq 성별을 선택하세요");
 			return false;
 		}
 	
 		//체크박스 필수입력
 		//<input type="checkbox" name="genre" id="genre0" class="" value="action"/>액션
 		if( !$('input[type="checkbox"]:checked').length ){
 			alert("jq 장르를 1개 이상 선택하세요");
 			return false;
 		}
 	
 		
 		//<select name="loc" id="loc">
 		//<option value="seoul">서울</option>
 		if($("#loc").val()==""){
 			alert("jq 지역을 선택하세요");
 			return false;
 		}
 		
		//필수입력되었으면 submit진행
		//문법> $(선택자).attr("속성명", "속성값");
		//id가 "joinForm"인 폼요소의  
		//action속성값을 "join.do"설정 
		//method속성값을 "post"설정
 		$("#joinForm").attr("action", "join.do");
 		$("#joinForm").attr("method", "post");
		return true;
	});
	
	
});

</script> --%>
</head>
<body>
 <h2>joinForm2</h2>
 
 <form id="joinForm2" action="join.do" method="post"> 
 	<div>
	 	<label for="memberid">ID</label>
	 	<input type="text" name="memberid" id="memberid" class="" />
	 	<input type="button" value="ID중복검사" id="idCheckBtn"/>
	 	<span  id="result" class="error"></span>
 	</div>
 	<div>
	 	<label for="password">비밀번호</label>
	 	<input type="password" name="password" id="password" class="" />
	 	<!-- <span class="error">비밀번호를 입력하세요</span> -->
 	</div>
 	<div>
	 	<label for="confirmPassword">비밀번호확인</label>
	 	<input type="password" name="confirmPassword" id="confirmPassword" class=""/>
	 	<!-- <span class="error">확인용 비밀번호를 입력하세요</span> -->
 	</div>
 	
 	<select id="sido">
        <option value="">시도를 선택하세요</option>
    </select>
    <select id="sigungu">
        <option value="">시군구를 선택하세요</option>
    </select>
 	 	
 	<div>
	 	<input  type="submit" value="가입(java용)" class=""/>
	 	<input  type="submit" id="submitBtn1"  class="" value="가입(js용)" />
	 	<input  type="submit" id="submitBtn2"  class="" value="가입(jq용)" />
 	</div>
 </form>
 
 <script>
 	//유효성검사
 	function validCK(){
 		//window.document.폼객체.폼하위객체
 		//회원id<input type="text" name="memberid" id="memberid"  class=""/>
 		let memberidObj = document.getElementById("memberid");
 		if(memberidObj.value==""){
 			alert("회원id는 필수입력입니다.");
 			memberidObj.focus();
 			return false;
 		}
 		
 		
 		//비번<input type="password" name="password" id="password" class=""/>
 		let passwordObj = document.getElementById("password");
 		if(passwordObj.value==""){
 			alert("비번은 필수입력입니다.");
 			passwordObj.focus();
 			return false;
 		}
 		
 		
 		//회원명<input type="text" name="name" id="name" class=""/>
 		let nameObj = document.getElementById("name");
 		if(nameObj.value==""){
 			alert("이름은 필수입력입니다.");
 			nameObj.focus();
 			return false;
 		}
 		
 		
 		//가입일<input type="date" name="regDate" id="regDate" class=""/>
 		let regDateObj = document.getElementById("regDate");
 		if(regDateObj.value==""){
 			alert("날짜를 선택하세요");
 			regDateObj.focus();
 			return false;
 		}
 		
 		//js에서는  radio요소와 checkbox요소는 collection으로 취급->배열 
 		//선택되면(checked) true리턴, 선택된 상태가 아니면 false리턴
 		//document.getElementsByName("name속성값"): name속성값으로 요소들에 접근
 		//<input type="radio" name="gender" id="gender0" class="" value="male"/>남성
 		let genderColl=document.getElementsByName("gender");
 		//console.log(genderColl);
 		//alert(genderColl);  //object NodeList
 		let genderChecked=false;
 		for(var i=0;i<genderColl.length ;i++){ //radio의 개수만큼 반복
 			if( genderColl[i].checked ){  //라디오항목이 선택되면(checked) true리턴
 				genderChecked=true;
 				break;//반복문종료
 			}
 		}
 		if(!genderChecked){
 			alert("성별을 선택하세요");
 			return false;
 		}
 		
 		//장르 필수선택
 		let genreColl=document.getElementsByName("genre");
 		let genreChecked=false;
 		for(var i=0;i<genreColl.length ;i++){ //radio의 개수만큼 반복
 			if( genreColl[i].checked ){  //체크박스항목이 선택되면(checked) true리턴
 				genreChecked=true;
 				break;//반복문종료
 			}
 		}
 		if(!genreChecked){
 			alert("장르를 선택하세요");
 			return false;
 		}
 		
 		
 		//모든 체크박스를 선택해야만 하는 경우
 		//<input type="checkbox" name="genre" id="genre0" class="" value="action"/>액션
 /*		let genre0=document.getElementById("genre0");
 		let genre1=document.getElementById("genre1");
 		let genre2=document.getElementById("genre2");
 		let genre3=document.getElementById("genre3");
 		let genre4=document.getElementById("genre4");
 		if( !genre0.checked || !genre1.checked ||!genre2.checked || !genre3.checked|| !genre4.checked ){
 			alert("장르를 선택하세요");
 			return false;
 		}
 	*/	
 		
 		//<select name="loc" id="loc">
 		//<option value="seoul">서울</option>
 		let locObj = document.getElementById("loc");
 		if(locObj.value==""){
 			alert("지역을 선택하세요");
 			locObj.focus();
 			return false;
 		}
 		
 		
 		//필수입력되었으면 submit진행
 		return true;
 	}
 </script>
</body>
</html>

<%--
	$.ajax({
	  type:value, //요청타입."get" "post"
	  url:value,  //요청url.예)"/board/list.do"
	  data:{name:value}, //서버로 보내는 data
	  dateType:value, //서버에서 응답받는 데이터type / 예)"json","xml","html","text"등
	  success: //정상 호출 함수
	    function(response){//response안에 응답내용
		  
	    },
	  error://오류발생 호출 함수. 4xx 또는 5xx
		    // jqXHR: XMLHttpRequest 객체
		    // textStatus: 에러 상태를 설명하는 문자열
		    // errorThrown: 에러의 예외 객체 (예외가 발생하지 않으면 undefined)
	  	function(jqXHR, textStatus, errorThrown) {
		},
	  complete: //완료후 호출 함수
		function(data,textStatus){
	    },
	  async:value //비동기여부.true(기본.비동기) 또는 false(동기)
	});//ajax()끝

 --%>
