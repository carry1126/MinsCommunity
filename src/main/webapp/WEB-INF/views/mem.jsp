<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<form name="form1">
	<table align="center">
		<tr>
			<td>ID</td>
			<td><input type="text" id="memid" name="memid" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" id="mempw" name="mempw"	/></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input type="text" id="mempwComp" name="mempwComp"	/></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" id="memmail" name="memmail" /></td>
		</tr>
		<tr>
			<td><button type="button" onclick="memReg()" >회원가입</button></td>
		</tr>
	</table>
</form>

<script type="text/javascript">
	function memReg(){
		//debugger;
		var memid = document.getElementById("memid").value;
		var mempw = document.getElementById("mempw").value;
		var mempwComp= document.getElementById("mempwComp").value;
		var memmail = document.getElementById("memmail").value;
		var idRegExp = /^[a-z0-9]{4,20}$/;
		var pwRegExp = /^[a-zA-Z0-9!@#$]{4,12}$/;
		var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
		
		//validationCheck
		//ID 체크
		if(memid == ""){
			alert("ID를 입력하세요.");
			document.getElementById("memid").focus();
			return false;
		}else if(idRegExp.test(memid) == false){
			alert("아이디는 영문 소문자와 숫자 4~12자리로 입력해야합니다.");
			document.getElementById("memid").value="";
			document.getElementById("memid").focus();
			return false;
		}

		//비밀번호 체크
		if(mempw == ""){
			alert("비밀번호를 입력하세요.");
			document.getElementById("mempw").focus();
			return false;
		}else if(pwRegExp.test(mempw) == false){
			alert("비밀번호는 영문대소문자와 숫자 특수문자(!@#$) 4~12자리로 입력해야합니다.");
			document.getElementById("mempw").value="";
			document.getElementById("mempw").focus();
			return false;			
		}
		
		if(mempwComp == ""){
			alert("비밀번호 확인을 입력하세요.");
			document.getElementById("mempwComp").focus();
			return false;
		}else if(pwRegExp.test(mempwComp) == false){
			alert("비밀번호는 영문대소문자와 숫자 특수문자(!@#$) 4~12자리로 입력해야합니다.");
			document.getElementById("mempwComp").value="";
			document.getElementById("mempwComp").focus();
			return false;			
		}else if(mempw != mempwComp){
			alert("비밀번호가 맞지 않습니다.");
			document.getElementById("mempw").value = "";
			document.getElementById("mempwComp").value = "";
			document.getElementById("mempw").focus();
			return false;
		}

		//이메일 체크
		if(memmail == ""){
			alert("이메일을 입력하세요.");
			document.getElementById("memmail").focus();
			return false;
		}else if(emailRegExp.test(memmail) == false){
			alert("이메일 형식이 올바르지 않습니다.!");
			document.getElementById("memmail").value="";
			document.getElementById("memmail").focus();
			return false;			
		}
		return true;
	}

</script>
</body>
</html>