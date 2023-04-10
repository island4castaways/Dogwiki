<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인페이지</title>
	</head>
	<body>
		<section>
			<div align="center">
				<h3>비밀번호 변경 페이지</h3>
				<hr>
				<form action = "/user_change_pw" method = "post">
					현재 비밀번호 : <input type = "password" name = "oldpw"><br>
					변경 비밀번호 : <input type = "password" name = "newpw"><br>
					<input type = "submit" value="확인">
					<input type = "button" value="취소" onclick="history.go(-1)"><br>
					<br>
				</form>
			</div>
		</section>
	</body>
	<script src = "http://code.jquery.com/jquery-3.4.0.min.js"></script>
	<script>
	$(document).ready(function(){
		var msg = '${msg}';
		if(msg != ''){
			alert(msg);
		}
	})
	</script>
</html>