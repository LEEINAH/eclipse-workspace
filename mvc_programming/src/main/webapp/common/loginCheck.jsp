<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 세션 정보를 꺼내서 담겨있지 않으면 로그인 화면으로 넘긴다
if (session.getAttribute("midx") == null) {
	out.println("<script>alert('로그인을 해주세요.');location.href='"+request.getContextPath()+"/member/memberLogin.aws'</script>");
}
%>