<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
	<!-- 
		데이터 공유 저장소
		ServletContext application : App동작시 생성 ~ App종료시 소멸, 전역공간, 모든 곳에서 접근 가능
		HttpSession session : 브라우저가 서버에 session이 형성되면 생성되는 공간, 타임아웃 소멸, 로그인 정보
		HttpServletRequest request : 브라우저에서 서버로 요청이 전달되었고, 서버가 응답하면 소멸
		PageContext pageContext : jsp페이지 내에서만
		
		EL은 객체를 찾을 때 다음 순서대로 찾는다.
		pageContext ->  request -> session -> application
		
		현재는 request에 member객체가 저장되어있으므로
		아래의 EL은 member를 바로 꺼내 사용한다.
		
		${member.no}에서 no를 '프로퍼티'라고 부른다.
		이것은 member.getNo() 함수를 호출하는데 get은 제거하고 N은 소문자로 바꾸는 규칙이다.
		결국 ${member.no}는 member.getNo()와 같다.
		
		만약 request공간과 session공간에 동일한 nameList객체가 존재할 때
		만약 session에 존재하는 nameList.getCount()를 꺼내고 싶을 때는?
		${nameList.count}  => request에 있는 것을 꺼낸다.
		${sessionScope.nameList.count} => session에 있는 것을 꺼낸다.
		
		${pageScope.변수이름}
		${requestScope.변수이름}
		${sessionScope.변수이름}
		${applicationScope.변수이름}
		
		* EL의 목적
		 - html코드와의 위화감을 줄이기 위해서
		 - Error를 줄이기 위해서(코딩 오타 방지)
	
	 -->
	<h1>회원정보</h1>
	<form action='update' method='post'>
		번호: <input type='text' name='no' value='${member.no }' readonly><br>
		이름: <input type='text' name='name' value='${member.name }'><br>
		이메일: <input type='text' name='email' value='${member.email }'><br>
		가입일: ${member.createdDate }><br>
		<input type='submit' value='저장'>
		<input type='button' value='삭제' 
			onclick='location.href="delete?no=${member.no}";'>
		<input type='button' value='취소' onclick='location.href="list"'>
	</form>
</body>
</html>




