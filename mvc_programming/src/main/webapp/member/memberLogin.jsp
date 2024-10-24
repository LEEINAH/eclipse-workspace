<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> 로그인 페이지 </TITLE>

<style>
header {
	width: 100%;
	height: 10%;
	text-align:center;
	background-color: #1463C4;
	padding:0px;
	margin:0px;
}

nav {
	width: 40%; 
	height: 70%; 
	float: left;
	--background-color: blue;
}

section {
	width: 50%;
	height: 30%;
	float: left;
	--background: olivedrab;
}

aside {
	width: 37%;
	height: 70%;
	float: left;
	--background: orange;
}

footer {
	width: 100%;
	height: 15%;
	clear: both; 
	text-align:center;
	--background: plum; 
}

body {
 	background-color:#5D93D6;
 	padding:0px;
	margin:0px;
}

table {
	border:1px solid black;
	width:400px;

}

article {
	text-align:center;
}

th {
	border:1px solid black;
 	background-color:white;
 	text-align:center;
}

td {
	border:1px solid black;
 	background-color: #61BBD9;
}


</style>

<script>
// 아이디 비밀번호 유효성 검사
function check() {
	// 이름으로 객체 찾기
	let memberid = document.getElementsByName("memberid");
	let memberpwd = document.getElementsByName("memberpwd");
	// alert(memberid[0].value);
	// alert(memberpwd[0].value);
	
	if (memberid[0].value == "") {
		alert("아이디를 입력해주세요.");
		memberid[0].focus();
		return;
	} else if (memberpwd[0].value == "") {
		alert("비밀번호를 입력해주세요.");
		memberpwd[0].focus();
		return;
	}
	
	var fm = document.frm;
	fm.action = "<%=request.getContextPath()%>/member/memberLoginAction.aws"; // 가상 경로 지정 action은 처리하는 의미
	fm.method = "post";
	fm.submit();
	
	return;
}

</script>


</HEAD>

<BODY>
 <header>
 <div style="height:5px;">
 </div>
 <h1>로그인 페이지</h1> 
 </header>
 <nav></nav>
 <div style="height:200px;"></div>
 <section>
   <article>
   <form name="frm">
   
   <table border=1 style="length:300px;">
   <tr> 
   <th>아이디</th>
         <td>
            <input type="text" name="memberid" style="width:150px;" maxlength="30">
         </td>
   </tr>
   <tr> 
   <th>비밀번호</th>
         <td>
            <input type="password" name="memberpwd" style="width:150px;" maxlength="30">
         </td>
   </tr>
   
   <tr>
      <td colspan=2 style="text-align:center;">
         <input type="button" name="btn" value="로그인" onclick="check();">
      </td>
   </tr>

   <tr>
      <td>
         <input type="button" name="btn" value="아이디 찾기">
      </td>
      <td>
         <input type="button" name="btn" value="비밀번호 찾기">
      </td>

   </tr>


   </table>
	</form>      
	</article>
</section>
<aside>
</aside>
<footer>
	made by lia.
</footer>

 </BODY>
</HTML>
