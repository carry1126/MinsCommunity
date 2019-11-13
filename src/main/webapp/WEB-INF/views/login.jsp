<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	jQuery(document).ready(function($){
			console.log("jQuery 사용");
			window.alert("${msg}");
			jQuery("#login").click(function(){
					console.log("로그인 버튼 클릭");
					var id = jQuery("#id").val();
					var pw = jQuery("#pw").val();
					console.log("id : " + id, " pw : " + pw);
					
					jQuery('form').attr({action:'/loginValidate', method:'post'}).submit();
					
					
					//AJAX 처리
/* 					
					$.ajax({
						url : '/loginValidate',
						dataType : 'json',
						type : 'POST',
						data : {'id':id, 'pw':pw},
						success : function(data, status, xhr){
								console.log("로그인성공");
						}
						,
						error : function(jqXHR, textStatus, errorThrown){
								console.log(jqXHR.responseText);							
						}

	 				});
*/
					
			});
			
			jQuery("#membership").click(function(){
				window.location.href="/memebership";
			});
	});
</script>
</head>
<body>
<form>
	<table align="center">
		<tr>
			<td>ID</td>
			<td><input type="text" name="id" id="id"/></td>
		</tr>
		<tr>
			<td>PW</td>
			<td><input type="password" name="pw" id="pw"/></td>		
		</tr>
		<tr>
			<td><input type="button" value="로그인" id="login" /></td>
			<td><input type="button" value="회원가입" id="membership" /></td>
		</tr>
	</table>
</form>
</body>
</html>