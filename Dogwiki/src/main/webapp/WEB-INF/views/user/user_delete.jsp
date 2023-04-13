<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<title>user delete</title>
		<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
	</head>
		<body>
			<section>
			<div align="center">
				<form action = "/user_delete" method = "post">
					<h3>회원 탈퇴</h3>
					
					<div class="int-area">
						<input type="password" id="pw" name="pw" autocomplete="off" required>
						<label for="pw">현재 비밀번호</label>
					</div>
					
					<div class="btn-area">
						<input type = "submit" value="확인" style="border-radius: 13px; background-color: #FEEBB6; margin-right: 20px;">
						<input type = "button" value="취소" onclick="history.go(-1)" style="border-radius: 13px;">
					</div>

				</form>
			</div>
		</section>
	<script src = "http://code.jquery.com/jquery-3.4.0.min.js"></script>
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
