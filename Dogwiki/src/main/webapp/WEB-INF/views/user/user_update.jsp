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
			<!-- 구분 값을 사용하겠다 , name ="regForm" -->
			<form name="regForm" action="user_update" method="post">
				<h2>정보수정 페이지</h2>
				<table>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="userid" value="${entity.userid}" readonly></td>
					</tr>
					<tr>
						<td>name</td> 
						<td><input type="text" name="username" value="${entity.username}"></td>
					</tr>
					<tr>
						<td>연락처</td>
						<td><input type="text" name="phone" value="${entity.phone}"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="email" name="email" value="${entity.email}"></td>
					</tr>
					
				</table>
				<br> 
				<input type="submit" value="정보수정" class="btn btn-primary" onclick="check()"> 
				<input type="button" value="마이페이지" class="btn btn-info" onclick="location.href='mypage'">
			</form>
		</div>
	</section>
</body>
<script>
      function check() {
            //form은 document.태그이름.태그이름으로 하위태그에 접근이 가능함...(dom 접근) 
            //console.log(document.regForm.id.value);
         if(document.regForm.id.value == ''){
            alert("아이디는 필수 사항입니다.")
            return;
         }else if((document.regForm.id.value.length < 4) ||(document.regForm.id.value.length > 8)){
            alert("아이디는 4글자 이상 8글자 이하로 입력하세요");
            return;
         }else if(document.regForm.name.value == ''){
            alert("이름은 필수사항입니다.");
            return;
         }else if(confirm("정보를 수정 하시겠습니까? ")){
            document.regForm.submit();   //자바스크립트의 submit()는 form태그 submit기능
         }
      }
      
   </script>

</html>