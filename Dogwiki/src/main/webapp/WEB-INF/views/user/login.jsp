<%@ include file="/resources/include/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<section style="margin-top: 150px;">
<title>Login</title>

<section class="input-form">
	<h2>로그인 페이지</h2>
	<form action="/login" method="post" class="login">

		<div class="int-area">
			<input type="text" id="userid" name="userid" autocomplete="off" required>
			<label for="userid">아이디</label>
		</div>

		<div class="int-area">
			<input type="password" id="pw" name="pw" autocomplete="off" required>
			<label for="pw">비밀번호</label>
		</div>

		<div class="btn-area" style=" margin-bottom:25px;">
			<input type="submit" value="로그인"
				style="border-radius: 13px; margin-right: 20px;">

			<input type="button" value="회원가입" onclick="location.href='join'" style="border-radius: 13px;">
		</div>
	</form>
</section>

<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script>
			$(document).ready(function(){
				var msg = '${msg}';
				if(msg != '') {
					alert(msg);
				}
			})
		</script>
</section>
<%@ include file="/resources/include/footer.jsp"%>