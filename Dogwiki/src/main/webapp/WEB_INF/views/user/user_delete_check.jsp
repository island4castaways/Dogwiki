<%@ include file="/resource/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<title>user delete</title>
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
		<body>
			<section>
			<div align="center">
				<form action = "/user_change_pw" method = "post">
					<h3>비밀번호 변경 페이지</h3>
					
					<div class="int-area">
						<input type="password" name="oldpw" autocomplete="off" required>
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

<%@ include file="../include/footer.jsp"%>