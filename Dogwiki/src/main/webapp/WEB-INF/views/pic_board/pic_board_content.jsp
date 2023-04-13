<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<section>
	<div align="center">
		<h2>게시판 글내용 보기</h2>

		<table border="1" style="width: 500px;" class="pic-board-wrap">
			<tr>
				<td width="20%">분류</td>
				<td >내새끼 짱</td>
				<td style="width: 100px;">조회수</td>
				<td >${picEntity.hit}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${picEntity.user.userid}</td>
				<td style="width: 20px;">작성일</td>
				<td>${picEntity.pic_regdate}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3">${picEntity.title}</td>
			</tr>
			<tr>
				<td>사진</td>
				<td colspan="3" height="100%"><img src="/files/${picEntity.filename}" style="width:300px; height:auto;"></td>
			</tr>
		</table>
		<form name="heartForm" method="post" action="pic_heart" class="content-btn">
			<input type="hidden" name="category" value="${param.category}">
			<input type="hidden" name="search" value="${param.search}">
			<input type="hidden" name="page" value="${page}">
			<input type="hidden" name="picnum" value="${picEntity.picnum}">
			<input class="${heartExists ? 'heartOn' : 'heartOff'}" type="submit" name="heart" value="좋아요&nbsp;&#9829;&nbsp;+${picEntity.heart}">
			<input type="button" value="목록" onclick="location.href='pic_list?category=${param.category}&search=${param.search}&page=${page}'">
		</form>
		<form name="deleteForm" action="pic_delete" method="get">
			<input type="hidden" name="picnum" value="${picEntity.picnum}">
			<c:if test="${picEntity.user.userid.equals(sessionScope.userid)}">
				<input type ="submit" value="삭제" onclick="check()">
			</c:if>
		</form>
	</div>
</section>

<script>
	function check() {
		if(confirm("게시글을 삭제하시겠습니까?")) {
			document.deleteForm.submit();
		}
	}
</script>