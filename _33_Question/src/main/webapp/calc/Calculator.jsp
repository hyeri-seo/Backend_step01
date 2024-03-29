
<!-- page 지시자
 원래 jsp를 다른 언어도 지원하려고 했으나, 워낙 기술의 빠른 발달로 다른 Web방식도 나오니까
 그냥 jsp는 java만 지원하게 되었다.
 
 아래는 jsp가 java로 변환된다는 약속이다.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Model One 방식
	Java코드(Controller, Dao) + html 코드가 함께 있는 코딩 방식
	
	-> 나중에 java코드의 Controller는 Servlet으로, DB/Query는 java클래스로 만들고
	   jsp는 주로 View역할(html)만 담당한다.
	   
	지금은 jsp를 설명하기 위해서 java코드를 넣었다.
	 - 1) jsp도 서블릿이기 때문에(즉, java) java코드가 동작된다.
	 - 2) html과 java는 서로 다른 영역의 언어이다.
	      그런데 우리는 html에 DB로부터 가져온 변수값등을 html에 넣어서 전송해야 할 필요가 있다.
	      그런데 그냥 java코드를 html에 바로 넣는 것은 불가능하다.
	      그래서 jsp는 java의 변수를 html에 삽입하는 방법을 제공한다.
	      나중에는 주로 EL과 jstl을 사용해서 처리한다.
	      EL과 jstl은 표현식이기 때문에 간결하게 표현되어진다.
	      
	      스프링 부트는 jsp도 사용가능하지만 타임리프를 사용한다.
 -->

<%

//[스크립트릿]
// 자바코드를 넣을 수 있다.
 
// _jspService(HttpServletRequest request, HttpServletResponse response) 함수안에 포함된다.


String v1 = "";
String v2 = "";
String result = "";
String[] selected = {"", "", "", ""};

// request는 jsp가 java로 변환되어서 _jspService() 영역으로 들어가면
// 브라우저의 요청이 담긴 객체이름이다.
// request같은 것을 '내장 객체'라고 부른다.
if(request.getParameter("v1") != null){
	v1 = request.getParameter("v1");
	v2 = request.getParameter("v2");
	String op = request.getParameter("op");
	
	result = calculate(Double.parseDouble(v1), Double.parseDouble(v2), op);
	
	if("+".equals(op))
		selected[0] = "selected";
	else if("-".equals(op))
		selected[1] = "selected";
	else if("*".equals(op))
		selected[2] = "selected";
	else if("/".equals(op))
		selected[3] = "selected";	
}
%>    


<!-- 템플릿 데이터
	브라우저에 표현되어지는 html코드
	
	표현식
java의 변수를 html에 삽입하기 위해서 필요
 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계산기</title>
</head>
<body>
	<h2>JSP 계산기</h2>
	<form action="Calculator.jsp" method="get">
		<input type="text" name="v1" size="4" value="<%=v1 %>">
		<select name="op">
			<option value="+" <%=selected[0] %>>+</option>
			<option value="-" <%=selected[1] %>>-</option>
			<option value="*" <%=selected[2] %>>*</option>
			<option value="/" <%=selected[3] %>>/</option>
		</select>
		<input type="text" name="v2" size="4" value="<%=v2 %>">
		<input type="submit" value="=">
		<input type="text" size="8" value="<%=result %>"><br>
	</form>
</body>
</html>

<%!

/*
 jsp 선언문
 메서드나 필드 선언할 때 이렇게 하면
 Calculator_jsp 클래스의 멤버가 된다.
*/
private String calculate(double a, double b, String op){
	double result = 0;
	
	if("+".equals(op)) result = a + b;
	else if("-".equals(op)) result = a - b;
	else if("*".equals(op)) result = a * b;
	else if("/".equals(op)) result = a / b;
	
	return Double.toString(result);	
}
%>




