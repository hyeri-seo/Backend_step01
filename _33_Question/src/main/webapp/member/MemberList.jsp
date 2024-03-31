<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="spms.vo.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
	<%
	// forward를 하면 아래 부분이 보여지지 않는다.
	//<jsp:forward page="/Forward.jsp" />
	 %>
	 
	<jsp:include page="/Header.jsp" />

	<h1>회원 목록</h1>
	<p><a href='add'>신규 회원</a></p>
	
	<!-- 되도록 jsp에서 java코드를 배제함으로써 이질감을 없애고, 일관성을 유지하게 하여
		협업작업에서 불필요한 불편함을 방지하기 위해서
	 -->
	<jsp:useBean id="members"
				scope="request"
				class="java.util.ArrayList"
				type="java.util.List<spms.vo.Member>" />
	<%
		//List<Member> members = (List<Member>)request.getAttribute("members");
		for(Member member : members){
	%>
			<%=member.getNo() %>,
			<a href='update?no=<%=member.getNo() %>'><%=member.getName() %></a>,
			<%=member.getEmail() %>,
			<%=member.getCreatedDate() %>
			<a href='delete?no=<%=member.getNo() %>'>[삭제]</a><br>
	<%
		}
	%>
	
	<jsp:include page="/Tail.jsp" />
</body>
</html>










