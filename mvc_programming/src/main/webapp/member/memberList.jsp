<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="mvc.vo.*" %>
<%
// ArrayList 객체를 화면까지 가져왔다
ArrayList<MemberVo> alist = (ArrayList<MemberVo>)request.getAttribute("alist");

// System.out.println("첫 객체 아이디 출력 : " + alist.get(0).getMemberid());

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록 보기</title>
<style>
table {
	border:1px solid skyblue;
	border-collapse:collapse;
}
th, td {
	padding:10px;
}
th {
	text-align:center;
	width:100px;
	border:1px solid lightgray;
	border-top:1px solid skyblue;
}
td {
	width:100px;
	height:20px;
	text-align:right;
	border:1px dotted lightgray;
}
thead {
	background:skyblue;
	color:white;
}
tfoot {
	border:1px solid lightgray;
}
tbody tr:nth-child(even) {
	background:aliceblue;
}
tbody tr:hover {
	background:gray;
}
</style> 
</head>
<body>
<h3>회원 목록</h3>
<hr>
	<table>
		<thead>
			<tr>
				<th>회원 번호</th>
				<th>회원 아이디</th>
				<th>회원 이름</th>
				<th>성별</th>
				<th>가입일</th>
			</tr>
		</thead>
		<tbody>
		 <%--  <% for (int i = 0; i < alist.size(); i++) { %>
			<tr>
				<td style="border-left:1px solid lightgray"><%=alist.get(i).getMidx() %></td>
				<td><%=alist.get(i).getMemberid() %></td>
				<td><%=alist.get(i).getMembername() %></td>
				<td><%=alist.get(i).getMembergender() %></td>
				<td style="border-right:1px solid lightgray"><%=alist.get(i).getWriteday() %></td>
			</tr>
		  <%} %> --%>
		  <%
		  	int num = 0;
		  	for (MemberVo mv : alist) { %>
		  	<tr>
				<td style="border-left:1px solid lightgray"><%=mv.getMidx() %></td>
				<td><%=mv.getMemberid() %></td>
				<td><%=mv.getMembername() %></td>
				<td><%=mv.getMembergender() %></td>
				<td style="border-right:1px solid lightgray"><%=mv.getWriteday().substring(0, 10)%></td>
			</tr>
			<%
			  num = num + 1;
			  } %>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5"><%=num %></td>
			</tr>
		</tfoot>
	</table>
</body>
</html>