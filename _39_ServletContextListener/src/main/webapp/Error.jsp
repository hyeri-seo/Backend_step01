<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템 오류!</title>
</head>
<body>
	<p>
		요청을 처리하는 중에 문제가 발생하였습니다. <br>
		잠시 후에 다시 요청 부탁드립니다.<br>
		계속 문제가 생긴다면 시스템 운영팀(02-1111-2222)에 연락하시기 바랍니다.
	</p>
	<%
		Exception error = (Exception)request.getAttribute("error");
		String err = error.getMessage();
		StackTraceElement[] trace = error.getStackTrace();
		//int traceLen = trace.length;
		
		// 브라우저에 출력되지 않도록
		// 대신 서블릿에 e.printStackTrace()를 호출하였음
		int traceLen = 0;				
	%>
	
	<p>Error: <%=err %></p>
	
	<%
		for(int i=0;i<traceLen;i++){
			String stack = trace[i].toString();
	%>		
		<p><%=stack %></p>
	<%		
		}
	%>
	

</body>
</html>