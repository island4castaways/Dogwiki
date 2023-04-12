<%@ include file="/resources/include/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<title>password change</title>
		<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
	</head>
	<body>
		<section class="input-form">
			<div align="center">
				<h3>비밀번호 변경</h3>
				<form action = "/user_change_pw" method="post">
				
				<div class="int-area">
					<input type = "password" id="pw" name="oldpw">
					<label for="pw">현재 비밀번호</label>
				</div>
				
				<div class="int-area">
					<input type = "password" id="newpw" name = "newpw">
					<label for="newpw">새로운 비밀번호</label>
				</div>	
				
				<div class="btn-area">
					<input type = "submit" value="확인"  style="border-radius: 13px; margin-right: 20px;">
					</div>

				<div class="btn-area">
					<input type = "button" value="취소" onclick="history.go(-1)" style="border-radius: 13px;">
				</div>
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