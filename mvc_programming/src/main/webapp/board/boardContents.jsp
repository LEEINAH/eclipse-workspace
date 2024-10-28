<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mvc.vo.BoardVo" %>   

<%
BoardVo bv = (BoardVo)request.getAttribute("bv"); // Object타입이기 때문에 강제 형변환을 해서 BoardVo타입으로 만들어준다

String memberName = "";
if (session.getAttribute("memberName") != null) {
	memberName = (String)session.getAttribute("memberName");
}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용</title>
<link href="../css/style2.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-latest.min.js"></script> 
<!-- jquery CDN 주소 -->
<script> 

$(document).ready(function() {
	// alert("dddd");
	$.boardCommentList();
	
	$("#btn").click(function() {
		// alert("추천 버튼 클릭");
		
		$.ajax({
			type : "get", // 전송 방식
			url : "<%=request.getContextPath()%>/board/boardRecom.aws?bidx=<%=bv.getBidx()%>",
			// 가상 경로
			dataType : "json", // json 타입은 문서에서 {"키 값" : "value 값", "키 값 2" : "value 값 2"}
			success : function	(result) { // 결과가 넘어와서 성공했을 때 받는 영역
				alert("전송 성공 테스트");	
			
				var str ="추천("+result.recom+")";
			
				$("#btn").val(str);
			},
			error : function () { // 결과가 넘어와서 실패했을 때 받는 영역
				alert("전송 실패");
			}
		});
	});
	
	$("#cmtBtn").click(function() {
		
		let loginCheck = "<%=session.getAttribute("midx") %>";
		
		if (loginCheck == "" || loginCheck == "null" || loginCheck == null) {
			alert("로그인을 해주세요.");
			return;
		}
		
		// 유효성 검사
		let cwriter = $("#cwriter").val();
		let ccontents = $("#ccontents").val();
		
		if (cwriter == "") {
			alert("작성자를 입력해주세요.");
			$("#cwriter").focus();
			return;
		} else if (ccontents == "") {
			alert("내용을 입력해주세요.");
			$("#ccontents").focus();
			return;
		}
		
		$.ajax({
			type : "post", // 전송 방식
			url : "<%=request.getContextPath()%>/comment/commentWriteAction.aws",
			data : {"cwriter" : cwriter, 
					"ccontents" : ccontents, 
					"bidx" : "<%=bv.getBidx()%>", 
					"midx" : "<%=session.getAttribute("midx")%>"},
			// 가상 경로
			dataType : "json", // json 타입은 문서에서 {"키 값" : "value 값", "키 값 2" : "value 값 2"}
			success : function	(result) { // 결과가 넘어와서 성공했을 때 받는 영역
				alert("전송 성공 테스트");	
			
				var str ="("+result.value+")";
				alert(str);
			},
			error : function () { // 결과가 넘어와서 실패했을 때 받는 영역
				alert("전송 실패");
			}
		});
	});	
});

//jquery로 만드는 함수
$.boardCommentList = function () { 
	alert("dddd");
	$.ajax({
		type : "get", // 전송 방식
		url : "<%=request.getContextPath()%>/comment/commentList.aws?bidx=<%=bv.getBidx()%>",
		// 가상 경로
		dataType : "json", // json 타입은 문서에서 {"키 값" : "value 값", "키 값 2" : "value 값 2"}
		success : function	(result) { // 결과가 넘어와서 성공했을 때 받는 영역
			alert("전송 성공 테스트");
		},
		error : function () { // 결과가 넘어와서 실패했을 때 받는 영역
			alert("전송 실패");
		}
	});
	
}

</script>
</head>
<body>
<header>
	<h2 class="mainTitle">글내용</h2>
</header>

<article class="detailContents">
	<h2 class="contentTitle"><%=bv.getSubject() %> (조회수:<%=bv.getViewcnt() %>)</h2>
	<input type="button" id="btn" value="추천(<%=bv.getRecom()%>)">
	<p class="write"><%=bv.getWriter() %> (<%=bv.getWriteday() %>)</p>
	<hr>
	
	<div class="content">
		<%=bv.getContents() %>
	</div>
	<% if (bv.getFilename() == null || bv.getFilename().equals("") ) {}else{ %>
	<img src="<%=request.getContextPath()%>/image/<%=bv.getFilename()%>">
	<% } %>
	
	<p>
	<a href="<%=request.getContextPath()%>/board/boardDownload.aws?filename=<%=bv.getFilename()%>" class="fileDown">
	첨부파일 다운로드</a></p>
</article>
	
<div class="btnBox">
	<a class="btn aBtn" href="<%=request.getContextPath() %>/board/boardModify.aws?bidx=<%=bv.getBidx() %>">수정</a>
	<a class="btn aBtn" href="<%=request.getContextPath() %>/board/boardDelete.aws?bidx=<%=bv.getBidx() %>">삭제</a>
	<a class="btn aBtn" href="<%=request.getContextPath() %>/board/boardReply.aws?bidx=<%=bv.getBidx() %>">답변</a>
	<a class="btn aBtn" href="<%=request.getContextPath() %>/board/boardList.aws">목록</a>
</div>

<article class="commentContents">
	<form name="frm">
		<p class="commentWriter" style="width:100px;">
		<input type="text" id="cwriter" name="cwriter" value="<%=memberName%>" readonly="readonly" style="width:100px;border:0px; text-align:center;">
		</p>	
		<input type="text" id="ccontents"  name="ccontents">
		<button type="button" id="cmtBtn" class="replyBtn">댓글쓰기</button>
	</form>	
	<table class="replyTable">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>내용</th>
			<th>날짜</th>
			<th>DEL</th>
		</tr>
		<tr>
			<td>1</td>
			<td>홍길동</td>
			<td class="content">댓글입니다</td>
			<td>2024-10-18</td>
			<td>sss</td>
		</tr>
	</table>
</article>

</body>
</html>