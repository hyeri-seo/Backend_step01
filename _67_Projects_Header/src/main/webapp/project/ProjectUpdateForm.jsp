<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 상세 정보</title>
<style>
	ul{padding: 0;}
	li{list-style:none;}
	label{
		float:left;
		text-align:right;
		width:60px;
	}
</style>
</head>
<body>
	<jsp:include page="/Header.jsp"/>
	
	<h1>프로젝트 정보</h1>
	<form action="update.do" method="post">
		<ul>
			<li>
				<label for="no">번호</label>
				<input type="text" id="title" name="title" size="50" value="${project.title}">
			</li>
			<li>
				<label for="content">내용</label>
				<textarea name="content" id="content" cols="40" rows="5">${project.content}</textarea>
			</li>
			<li>
				<label for="sdate">시작일</label>
				<input type="text" id="sdate" name="startDate" placeholder="예)2024-04-05">
			</li>
			<li>
				<label for="edate">종료일</label>
				<input type="text" id="edate" name="endDate" placeholder="예)2024-07-30"
						value="${project.endDate}">
			</li>
			<li>
				<label for="state">상태</label>
				<select name="state" id="state">
					<option value="0" ${project.state == 0 ? "selected":""}>준비</option>
					<option value="1" ${project.state == 1 ? "selected":""}>진행</option>
					<option value="2" ${project.state == 2 ? "selected":""}>완료</option>
					<option value="3" ${project.state == 3 ? "selected":""}>취소</option>
				</select>
			</li>
			<li>
				<label for="tags">태그</label>
				<input type="text" id="tags" name="tags" placeholder="예)태그1 태그2 태그3" size="50"
						value="${project.tags}">
			</li>
		</ul>
		<input type="submit" value="저장">
		<input type="button" value="삭제" onclick="location.href='delete.do?no=${project.no}'">
		<input type="button" value="취소" onclick="location.href='list.do'">
	</form>
	
	<jsp:include page="/Tail.jsp"/>
</body>
</html>








