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
		jQuery("#delete").click(function(){
			fn_userDel();
		});
	});
	
	function allChk(obj){
		var chkObj = document.getElementsByName("rowCheck");
		var rowCnt = chkObj.length - 1;
		var check = obj.checked;
		if(check){
			for(var i=0; i<=rowCnt; i++){
				if(chkObj[i].type == "checkbox")
					chkObj[i].checked = true;
			}
		}else{
			for(var i=0; i<=rowCnt; i++){
				if(chkObj[i].type == "checkbox")
					chkObj[i].checked = false;
			}
		}
	}
	
	function fn_userDel(){
		var userid = "";
		var memberChk = document.getElementsByName("rowCheck");
		var chked = false;
		var indexid = false;
		for(var i=0; i<memberChk.length; i++){
			if(memberChk[i].checked){
				if(indexid){
					userid = userid + '-';
				}
				userid = userid + memberChk[i].value;
				indexid = true;
			}
		}
		if(!indexid){
			alert("삭제할 사용자를 체크해 주세요.");
			return;
		}
		document.userForm.delIdx.value=userid;
		
		var agree = confirm("삭제 하시겠습니까?");
		if(agree){
			document.userForm.action = "/deleteInfo";
			document.userForm.submit();
		}
	}
</script>
</head>
<body>
<c:set var="name" value="${id}" />
<c:set var="list" value="${list}" />
<div align="center">${name}님 어서 오세요</div><div align="right"><a href="/logout">로그아웃</a></div>
<form name="userForm" align="center">
<div align="right"><input type="text" size="40%"/><input type="button" id="search" value="검색" /></div>
<div><input type="button" value="글쓰기" onclick="location.href='writeDetail?user=${name}&mode=N'"/><input type="button" id="delete" value="글삭제"/></div>
<table align="center">
	<tr>
		<td><input type="checkbox" id="allCheck" onclick="allChk(this);" /></td>
		<td>순번</td>
		<td>제목</td>
	</tr>
	<input type="hidden" name="id" value="${name}" />
	<input type="hidden" name="delIdx" />
	<c:if test="${not empty list}">
	<c:forEach var="list" items="${list}">
		<c:set var="idx" value="${list.idx}" />
		<tr>
			<td><input type="checkbox" name="rowCheck" value="${idx}" /></td>
			<td>${idx}</td>
			<td><a href="/writeDetail?user=${name}&idx=${idx}&mode=M">${list.subject}</a></td>
		</tr>
	</c:forEach>	
	</c:if>
</table>
</form>
</body>
</html>