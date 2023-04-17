<%@ include file="/resources/include/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<section style="margin-top: 150px;">

	<div align="center">
			<!-- 구분 값을 사용하겠다 , name ="regForm" -->
			<form name="regForm" action="user_update" method="post">
				<h2>Update</h2>
				
				<div class="int-area">
					<input type="text" name="userid" size="15" value="${entity.userid}" readonly style="margin-bottom: 2px">
					<label for="id"></label>
				</div>
				
				<div class="int-area">
					<input type="text" name="username" value="${entity.username}"size="15" style="margin-bottom: 2px" autocomplete="off" required>
					<label for="name">이름</label>
				</div>
					
				<div class="int-area">
					<input type="text" name="phone" value="${entity.phone}" size="15" style="margin-bottom: 2px" autocomplete="off" required>
					<label for="phone">연락처</label>					
				</div>
					
				<div class="int-area">
					<input type="email" name="email" value="${entity.email}"size="15" style="margin-bottom: 2px" autocomplete="off" required>
					<label for="email">이메일</label>
				</div>
				
				<div class="btn-area">			
					<input type="submit" value="정보수정" style="border-radius: 13px; margin-right: 10px;"  onclick="check()" > 
						
					<input type="button" value="마이페이지" style="border-radius: 13px;"onclick="location.href='mypage'">
				</div>
			</form>
	</div>

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
</section>
<%@ include file="/resources/include/footer.jsp"%>