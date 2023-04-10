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
				<h2>로그인 페이지</h2>
				<hr>
				<form action="/login" method="post">
					<input type="text" name="userid" placeholder="아이디"><br>
					<input type="password" name="pw" placeholder="비밀번호"><br>
					<br>
					<input type="submit" value="로그인" class="btn btn-default">
					<input type="button" value="회원가입" class="btn btn-primary" onclick="location.href='join'">				
				</form>	
			</div>
		</section>
		
		<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
		<script>
			$(document).ready(function(){
				var msg = '${msg}';
				if(msg != ''){
					alert(msg);
				}
			})
		</script>
	</body>
</html>