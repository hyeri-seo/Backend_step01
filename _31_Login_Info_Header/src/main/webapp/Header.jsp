<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="spms.vo.Member"  %>

<%
	Member member = (Member)session.getAttribute("member");
%>

<div style="background-color:#00008b;color:white;height:20px;padding:5px;">
	SPMS(Simple Project Management System)
	<span style="float:right"><%=member.getName() %>
		<a style="color: white;" href="<%=request.getContextPath() %>/auth/logout">로그아웃</a>
	</span>
</div>