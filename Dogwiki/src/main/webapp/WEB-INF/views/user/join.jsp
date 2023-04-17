<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<title>Join</title>
		<link rel="stylesheet" type="text/css" href="/resources/css/main.css">

	</head>
	<body>
	
		<section class="input-form">				
				<form name="regForm" id="Form" action="/join" method="post">
					<h2>회원가입 페이지</h2>
					
					<div class="int-area">
						<input type="text" id="userid" name="userid" autocomplete="off" required>
						<label for="userid" >아이디</label>
					</div>
					
					<div class="int-area">
						<input type="password" id="pw" name="pw" autocomplete="off" required >
						<label for="pw">비밀번호</label>
					</div>
					
					<div class="int-area">
						<input type="password" id="pw_check" name="pw_check" autocomplete="off" required>
						<label for="pw_check">비밀번호 확인</label>
					</div>
					
					<div class="int-area">
						<input type="text" id="name" name="username" autocomplete="off" required >
						<label for="name">이름</label>
					</div>
					
					<div class="int-area">
						<input type="text"  id="phone" name="phone" autocomplete="off" required> 
						<label for="phone">연락처</label>
					</div>
					
					<div class="int-area">
						<input type="email" id="email" name="email" autocomplete="off" required >
						<label for="email">이메일</label>
					</div>
					
				<div class="btn-area">
					<input type="button" value="회원가입" onclick="check()"  style="border-radius: 13px;margin-right: 20px;">
					<input type="button" value="로그인" onclick="location.href='/login'" style="border-radius: 13px">
				</div>				
			</form>


		</section>
		
		<script>
		
		function check() {
			//form은 document.태그이름.태그이름으로 하위태그에 접근이 가능함...(dom접근)
			//console.log(document.regForm.id.value); 공백이 출력됨...
			if(document.regForm.userid.value.includes(" ")){
	               alert("아이디에 공백은 넣을 수 없습니다.")
	               return;
	        }else if(document.regForm.userid.value == '') {
	               alert("아이디는 필수 사항입니다.")
	               return;
	        }else if((document.regForm.userid.value.length < 4) || (document.regForm.userid.value.length > 8)){
				alert("아이디는 4글자 이상 8글자 이하로 입력하세요")
				return;
			}else if(document.regForm.pw.value == '') {
				alert("비밀번호는 필수 사항입니다.")
				return;
			}else if(document.regForm.pw.value.includes(" ")){
	               alert("비밀번호에 공백은 넣을 수 없습니다.")
	               return;
			}else if(document.regForm.pw.value != document.regForm.pw_check.value){
				alert("비밀번호 확인란을 확인해주세요")
				return;
			}else if(document.regForm.username.value == ''){
				alert("이름은 필수 사항입니다.");
				return;
			}else if(confirm("회원 가입을 하시겠습니까?")){
				document.regForm.submit(); //자바스크립트의 submit()는 form태그 submit기능
			}
		}
	</script>

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
