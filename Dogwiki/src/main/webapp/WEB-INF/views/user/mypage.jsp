<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="/resources/css/main.css">		
		<!-- Slick.js 스타일시트 파일 추가 -->
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css"/>

</head>
	<body>	
	<section class="mypage-form">

		  <div class="image-slider">
   
    <div>
      <img src="../img/img1.jpg" width="400" height="450">
    </div>
  </div>

		<div style="height:500px; margin-left: 40px;" align="center" >
		<h2>MyPage</h2>
        <h3>${sessionScope.userid }(${sessionScope.username })님의 회원정보를 관리합니다.</h3>
        
        		<div class="btn-my">			
					<input type="button" value="비밀번호 변경" onclick="location.href='user_change_pw'" style="border-radius: 13px;" >
					</div>
					
       		 	<div class="btn-my">			
					<input type="button" value="회원정보 변경" onclick="location.href='user_update' " style="border-radius: 13px;">
					</div>
    
        		<div class="btn-my">			
					<input type="button" value="회원탈퇴" onclick="location.href='user_delete' " style="border-radius: 13px;">
					</div>
					
        		<div class="btn-my">			
					<input type="submit" value="로그아웃"  onclick="location.href='user_logout' " style="border-radius: 13px;">
					</div>
      
        		<div class="btn-my">			
					<input type="button" value="내가 쓴 글"  onclick="location.href='board/board_list' " style="border-radius: 13px;">
				</div>       

    </div>

	</section>
	
	</body>
	<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
	<!-- Slick.js 라이브러리 추가 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>

	<!-- 슬라이더 옵션 설정 및 초기화 -->
		<script>
		$(document).ready(function(){
			var msg = '${msg}';
			if(msg != ''){
				alert(msg);
			}
		})
	      	
		</script>
</html>