<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<section>
			<div align="center">
				<h2>MyPage</h2>
				<hr>
				${sessionScope.userid}(${sessionScope.username})님의 회원정보를 관리합니다.
				<hr>
				<input type="button" value="비밀번호 변경" class="btn btn-default" onclick="location.href='user_change_pw'">
				<input type="button" value="회원정보 변경" class="btn btn-primary" onclick="location.href='user_update'">
				<input type="button" value="회원탈퇴" class="btn btn-info" onclick="location.href='user_delete'">
				<input type="submit" value="로그아웃" class="btn btn-info" 	onclick="location.href='user_logout'">
			</div>
		</section>
	</body>
	<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
	<script>
		$(document).ready(function() {
			var msg = '${msg}';
			if(msg != '') {
				alert(msg);
			}
		})
	</script>
</html>