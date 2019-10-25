<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 페이지</title>
</head>
<body>
<c:set var="detail" value="${writeInfo}" />
<c:set var="mode" value="${detail.mode}" />
<c:choose>
<c:when test="${mode eq 'M'}">
<form align="center">
<table align="center">
	<input type="hidden" value="${detail.IDX}" name="idx" />
	<tr>
		<td>제목</td>
		<td><input type="text" value="${detail.SUBJECT}" name="subject" /></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><input type="text" value="${detail.WRITER}" name="writer" /></td>
	</tr>
	<tr>
		<td>등록시간</td>
		<td><input type="text" value="${detail.REG_DATE}" name="reg_date" /></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><input type="text" value="${detail.CONTENT}" name="content" /></td>
	</tr>		
</table>
<div><input type="button" value="수정"/><input type="button" value="목록"/></div>
</form>
</c:when>
<c:otherwise>
<form align="center">
<table align="center">
	<tr>
		<td>제목</td>
		<td><input type="text" name="subject" /></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><input type="text" name="writer" /></td>
	</tr>
	<tr>
		<td>등록시간</td>
		<td><input type="text" name="reg_date" /></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><input type="text" name="content" /></td>
	</tr>		
</table>
<div><input type="button" value="등록"/><input type="button" value="목록"/></div>
</form>
</c:otherwise>
</c:choose>
</body>
</html>