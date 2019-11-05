<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 페이지</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	jQuery(document).ready(function(){		
		jQuery("#register").click(function(){
			jQuery('form').attr({action:'/registerInfo', method:'post'}).submit();
		});
		
		jQuery("#modify").click(function(){
			jQuery('form').attr({action:'/modifyInfo', method:'post'}).submit();
		});
	});
</script>
</head>
<body>
<c:set var="detail" value="${writeInfo}" />
<c:set var="name" value="${user}" />
<c:set var="mode" value="${mode}" />
<c:choose>
<c:when test="${mode eq 'M'}">
<form id="regInfo" align="center">
<table align="center">
	<input type="hidden" value="${detail.idx}" name="idx" />
	<tr>
		<td>제목</td>
		<td><input type="text" value="${detail.subject}" name="subject" /></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><input type="text" value="${detail.writer}" name="writer" readonly /></td>
	</tr>
	<tr>
		<td>등록시간</td>
		<td><input type="text" value="${detail.reg_date}" name="reg_date" readonly /></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea name="content">${detail.content}</textarea></td>
	</tr>		
</table>
<div><input type="button" value="수정" id="modify" /><input type="button" value="목록" onclick="location.href='list?id=${name}'" /></div>
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
		<td><input type="text" value="${name}" name="writer" readonly /></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea name="content"></textarea></td>
	</tr>		
</table>
<div><input type="button" value="등록" id="register" /><input type="button" value="목록" onclick="location.href='list?id=${name}'"/></div>
</form>
</c:otherwise>
</c:choose>
</body>
</html>