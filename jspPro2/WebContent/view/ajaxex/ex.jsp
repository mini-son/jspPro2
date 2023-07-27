<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title></title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 <script>
 	
 $(function(){
	 
	 //조회버튼 클릭시
	 $("#searchMemberBtn").on("click",function(){
		//검색어는 필수입력
		let userInput = document.getElementById("searchMember").value;  //<input type="search" name="searchMember" id="searchMember"/>
		if(userInput===""){ 
			alert("검색어는 필수입력입니다.");
			//$("#searchMember").focus();
			document.getElementById("searchMember").focus();
			return;
		}
			
		 $.ajax({
			  type:"post", //요청타입."get" "post"
			  url:"${cPath}/member/search.do",   //요청url.예)"/board/list.do"
			  data:{searchMember:userInput}, //서버로 보내는 data
			  //dateType:"text",   //서버에서 응답받는 데이터type / 예)"json","xml","html","text"등
			  success: //정상 호출 함수
			    function(response){
				  //response안에 응답내용이 문자열로 변환된 (JSONObject객체를) 받는다 
				  alert("아싸~~~");
				  console.log(response); 
				  //서버에서 응답받은 json을 파싱한다ㅡ
				 const jsonObj = JSON.parse(response);
				 console.log(jsonObj);
				 
				 var tableBody = $("#memberTable tbody");
				 tableBody.empty();
				 
				 if(jsonObj.members!=null && jsonObj.members.length>0){			 
					 for( let i in  jsonObj.members ){
						 var row = "<tr>";
						 row+="<td>"+jsonObj.members[i].no+"</td>";
						 row+="<td>"+jsonObj.members[i].memberid+"</td>";
						 row+="<td>"+jsonObj.members[i].name+"</td>";
						 row+="<td>"+jsonObj.members[i].regdate+"</td>";
						 row+="<td>"+jsonObj.members[i].password+"</td>";
						 row+="<td>"+jsonObj.members[i].isshow+"</td>";
						 row+="<td><a href='${cPath}/member/delete.do?no="+jsonObj.members[i].no+"'>삭제</a></td>";
						 row+="</tr>";
						 tableBody.append(row);
					 }
				 }else{
					 var row = "<tr><td colspan='7'>회원이 존재하지 않습니다</td></tr>";
					 tableBody.append(row);
					 $("#searchMember").val("");//검색어입력부분 초기화
				 }
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
	  });//$("#searchMember").click()끝
 });
 </script>
</head>
<body>
 <h2>회원정보(ex.jsp)</h2>
  <label for="searchMember">*회원검색</label>
  <input type="text" name="searchMember" id="searchMember"/>
  <input type="button" value="조회" id="searchMemberBtn"/>
 <hr/>
 
<div id="result">
  <table id="memberTable" border="1">
    <thead>   
      <tr>
        <th>번호</th>
        <th>아이디</th>
        <th>이름</th>
        <th>가입일</th>
        <th>비번</th>
        <th>isshow</th>
        <th>삭제</th>
      </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
</div>  
</body>
</html>
