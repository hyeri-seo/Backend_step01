<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
	<h1>회원정보</h1>
	
	<form action='update' method='post'>
		번호: <input type='text' name='no' 
					value='req.getParameter("no")' readonly>
		이름: <input type='text' name='name'
					value='rs.getString("mname")'>
		이메일: <input type='text' name='email' value='rs.getString("email")'>
		가입일: rs.getDate("CRE_DATE")
		<input type='submit' value='저장'>
		<input type='button' value='삭제' 
				onclick='location.href=\"delete?no=req.getParameter("no")\";'>
		<input type='button' value='취소' onclick='location.href=\"list\"'>
	</form>
</body>
</html>