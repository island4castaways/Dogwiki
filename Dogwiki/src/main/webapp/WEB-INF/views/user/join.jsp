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
				<form name="regForm" id="Form" action="/join" method="post">
					<h2>회원가입 페이지</h2>
					<table>
						<tr>
							<td>아이디</td>
							<td>
								<input type="text" name="userid" placeholder="4글자 이상 8글자 이하">
							</td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td>
								<input type="password" name="pw">
							</td>
						</tr>
						<tr>
							<td>비밀번호 확인</td>
							<td>
								<input type="password" name="pw_check">
							</td>
						</tr>
						<tr>
							<td>name</td>
							<td>
								<input type="text" name="username">
							</td>
						</tr>
						<tr>
							<td>연락처:</td>
							<td>
								<input type="text" name="phone">
							</td>
						</tr>
						<tr>
							<td>이메일</td>
							<td>
								<input type="email" name="email">
							</td>
						</tr>
					</table>
					<br>
					<input type="button" value="회원가입" class="btn btn-primary" onclick="check()">
					<input type="button" value="로그인" class="btn btn-info" onclick="location.href='/login'">
				</form>
				<br>
			</div>
		</section>
	
		<script>
			function check() {
				//form은 document.태그이름.태그이름으로 하위태그에 접근이 가능함...(dom접근)
				//console.log(document.regForm.id.value); 공백이 출력됨...
				if(document.regForm.userid.value.includes(" ")) {
					alert("아이디에 공백은 넣을 수 없습니다.")
					return;
				} else if(document.regForm.userid.value == '') {
					alert("아이디는 필수 사항입니다.")
					return;
				} else if(document.regForm.userid.value.length < 4 
						|| document.regForm.userid.value.length > 8) {
					alert("아이디는 4글자 이상 8글자 이하로 입력하세요")
					return;
				} else if(document.regForm.pw.value == '') {
					alert("비밀번호는 필수 사항입니다.")
					return;
				} else if(document.regForm.pw.value.includes(" ")) {
					alert("비밀번호에 공백은 넣을 수 없습니다.")
					return;
				} else if(document.regForm.pw.value != document.regForm.pw_check.value) {
					alert("비밀번호 확인란을 확인해주세요")
					return;
				} else if(document.regForm.username.value == '') {
					alert("이름은 필수 사항입니다.");
					return;
				} else if(confirm("회원 가입을 하시겠습니까?")) {
					document.regForm.submit(); //자바스크립트의 submit()는 form태그 submit기능
				}
			}
		</script>
		
		<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
		<script>
			$(document).ready(function() {
				var msg = '${msg}';
				if(msg != '') {
					alert(msg);
				}
			})
		</script>
	</body>
</html>