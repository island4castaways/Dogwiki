<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<table class="table table-boardered">
	<!-- 게시글 -->
	<thead>
		<tr>
			<th colspan="2"><a href="/pic_board/pic_list">more</a></th>
		</tr>
		<tr>
			<th>사진</th>
			<th>좋아요</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="bo" items="${picEntity}">
			<tr>
				<td><a
					href="/pic_board/pic_content?category=1&search=${search}&num=${bo.picnum}&page=${pageNumber}">
						<img src="/files/${bo.filename}"
						style="width: 300px; height: auto;">
				</a></td>
				<td>${bo.joa}</td>
			</tr>
		</c:forEach>
	</tbody>
	<tbody>
	</tbody>
</table>

<table class="table table-boardered">
	<!-- 게시글 -->
	<thead>
		<tr>
			<th colspan="2"><a href="/board/board_list?category=2">more</a></th>
		</tr>
		<tr>
			<th>작성자</th>
			<th>제목</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="bo" items="${board2Entity}">
			<tr>
				<td>${bo.user.userid}</td>
				<td><a
					href="/board/board_content?category=2&search=${search}&num=${bo.num}&page=${pageNumber}">${bo.title}</a>
				</td>
				<td>${bo.regdate}</td>
				<td>${bo.hit}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<table class="table table-boardered">
	<!-- 게시글 -->
	<thead>
		<tr>
			<th colspan="2"><a href="/board/board_list?category=3">more</a></th>
		</tr>
		<tr>
			<th>작성자</th>
			<th>제목</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="bo" items="${board3Entity}">
			<tr>
				<td>${bo.user.userid}</td>
				<td><a
					href="/board/board_content?category=3&search=${search}&num=${bo.num}&page=${pageNumber}">${bo.title}</a>
				</td>
				<td>${bo.regdate}</td>
				<td>${bo.hit}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<table>
	<tr>
		<th><a href="/training">more</a></th>
	</tr>
	<tr>
		<td>최신 훈련 영상</td>
	</tr>
	<tr>
		<td><iframe src="${trainingEntity.trUrl}"></iframe></td>
	</tr>

</table>




