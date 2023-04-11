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
			<form action="/user_delete" method="post">
				<h3>현재 비밀번호를 입력하세요</h3>
				<hr>
				<input type="password" name="pw"> <input type="submit"
					value="확인">
			</form>
		</div>
	</section>
</body>
<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script>
<script>
		$(document).ready(function() {
			var msg = '${msg}';
			if(msg != ''){
				alert(msg);
			}
		})
		</script>
</html>