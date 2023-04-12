<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section>
	<div align="center">
		<h2>게시판 글내용 보기</h2>
		<hr>
		<table border="1" width="500">
			<tr>
				<td width="20%">글번호</td>
				<td width="30%">${picEntity.picnum}</td>
				<td width="20%">조회수</td>
				<td width="30%">${picEntity.hit}</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${picEntity.pic_regdate}</td>
				<td>작성자</td>
				<td>${picEntity.user.userid}</td>
			</tr>
			<tr>
				<td>분류</td>
				<td colspan="3">${picEntity.category}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3">${picEntity.title}</td>
			</tr>
			<tr>
				<td>사진</td>
				<td colspan="3" height="100%"><img src="/files/${picEntity.filename}" style="width:300px;height:auto;"></td>
			</tr>
			<tr>
				<td width="20%">좋아요</td>
				<td width="80%">
					<form name="joaForm" method="post" action="pic_joa">
						<input type="hidden" name="category" value="${param.category}">
						<input type="hidden" name="search" value="${param.search}">
						<input type="hidden" name="page" value="${page}">
						<input type="hidden" name="picnum" value="${picEntity.picnum}">
						<input type="submit" name="joa" value="&#9829;&nbsp;+">
						${picEntity.joa}
					</form>
				</td>
			</tr>
			<tr>
				<!-- 글 등록 메뉴 -->
				<td colspan="2" align="center">
					<input type="button" value="목록" onclick="location.href='pic_list?category=${param.category}&search=${param.search}&page=${page}'">
					<form name="deleteForm" action="pic_delete" method="get">
						<input type="hidden" name="picnum" value="${picEntity.picnum}">
						<input type="submit" value="삭제" onclick="check()">
					</form>
				</td>
			</tr>
		</table>
	</div>
</section>

<script>
	function check() {
		if(confirm("게시글을 삭제하시겠습니까?")) {
			document.deleteForm.submit();
		}
	}
</script>