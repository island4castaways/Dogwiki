
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section>
	<div align="center">
		<form name="regform" action="../pic_board/write" method="post" enctype="multipart/form-data">
			<h2>게시판 글작성 페이지</h2>
			<hr>
			<table border="1" width="500">
				<tr>
					<td>작성자</td>
					
					<td><input type="text" name="user" size="10" value="${sessionScope.userid}"
					${sessionScope.userid != null ? "readonly" : ""}></td>
					
				</tr>
				<tr>
					<td>글제목</td>
					<td>
						<input type="text" name="title">
					</td>
				</tr>
				<tr>
					<td>분류</td>
					<td>
					<select name="category" onchange="selectOnChange(value)">
							<option value=1 ${param.category == '1' ? 'selected="selected"' : ''}>우리 아이가 제일 멋져</option>
							<option value=2 ${param.category == '2' ? 'selected="selected"' : ''}>사료&간식 추천</option>
							<option value=3 ${param.category == '3' ? 'selected="selected"' : ''}>아이가 아파요</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>사진</td>
					<td>
						<input type="file" name="file""> 
					</td>
				</tr>
				<tr>
				<!-- 글 등록 메뉴 -->
					<td colspan="2" align="center">
						<input type="hidden" name="category" value="${param.category}">
						<input type="button" value="등록" onclick="registCheck()">
						<input type="button" value="목록" onclick="history.go(-1)">
					</td>
				</tr>
			</table>
		</form>
	</div>
</section>
<!-- registCheck()구현: 작성자, 글제목에 공백을 확인하고, 공백이 아니라면 submit() 처리 -->
<script type="text/javascript">
	function registCheck() {
		if(document.regform.user.value == "") {
			alert("작성자를 입력하세요.");
			return;
		} else if(document.regform.title.value == "") {
			alert("제목을 입력하세요.");
			return;
		} else if(confirm("게시글을 등록하겠습니까?")) {
			document.regform.submit();
		}
	}
</script>
</body>
</html>

<script type="text/javascript">
	function selectOnChange(value) {
		if(value == 2) {
			location.href="/board/board_write?category=2";
		} else if(value == 3) {
			location.href="/board/board_write?category=3"
		}
		var category = value;
	}
</script>