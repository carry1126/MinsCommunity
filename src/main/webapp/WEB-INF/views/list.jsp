<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 목록</title>
<script>

</script>
</head>
<body>
<c:set var="name" value="${id}" />
<c:set var="list" value="${list}" />
${name}님 어서 오세요
<form>
<div aligin="right"><input type="text" size="40%"/><input type="button" id="search" value="검색" /></div>
<div><input type="button" id="write" value="글쓰기"/><input type="button" id="delete" value="글삭제"></div>
<table>
	<tr>
		<td><input type="checkbox" /></td>
		<td>순번</td>
		<td>제목</td>
	</tr>
	<c:if test="${not empty list}">
	<c:forEach var="list" items="${list}">
		<tr>
			<td><input type="checkbox" /></td>
			<td>${list.IDX}</td>
			<td><a href="#">${list.SUBJECT}</a></td>
		</tr>
	</c:forEach>	
	</c:if>
</table>
</form>
</body>
</html>