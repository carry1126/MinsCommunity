<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록 페이지</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	jQuery(document).ready(function(){

	});
</script>
</head>
<body>
<c:set var="name" value="${id}" />
<c:set var="list" value="${list}" />
<div align="center">${name}님 어서 오세요</div><div align="right"><a href="/logout">로그아웃</a></div>
<form align="center">
<div align="right"><input type="text" size="40%"/><input type="button" id="search" value="검색" /></div>
<div><input type="button" value="글쓰기" onclick="location.href='writeDetail?user=${name}&mode=N'"/><input type="button" id="delete" value="글삭제"/></div>
<table align="center">
	<tr>
		<td><input type="checkbox" /></td>
		<td>순번</td>
		<td>제목</td>
	</tr>
	<c:if test="${not empty list}">
	<c:forEach var="list" items="${list}">
		<c:set var="idx" value="${list.idx}" />
		<tr>
			<td><input type="checkbox" /></td>
			<td>${idx}</td>
			<td><a href="/writeDetail?user=${name}&idx=${idx}&mode=M">${list.subject}</a></td>
		</tr>
	</c:forEach>	
	</c:if>
</table>
</form>
</body>
</html>