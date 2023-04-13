<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
<div class = "container"></div>
	<h3>Dogwiki 게시판</h3>
		<label for="category">분류</label>
		<select name="category" onchange="selectOnChange(value)">
			<option value=1 ${category == '1' ? 'selected="selected"' : ''}>내새끼 짱</option>
			<option value=2 ${category == '2' ? 'selected="selected"' : ''}>사료간식 추천</option>
			<option value=3 ${category == '3' ? 'selected="selected"' : ''}>아이가 아파요</option>
		</select>

	<table class ="table table-boardered">
		<!-- 게시글 -->
		<thead>
			<tr>
				<th>작성자</th>
				<th>제목</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bo" items="${page}">
				<tr>
					<td>${bo.user.userid}</td>
					<td>
						<a href="board_content?category=${category}&search=${search}&num=${bo.num}&page=${pageNumber}">${bo.title}</a>
					</td>
					<td>${bo.regdate}</td>
					<td>${bo.hit}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tbody>
			<!-- 작성글 검색 및 글작성하기 메뉴 -->
			<tr>
				<td colspan="5" align="right">
					<form method="get" action="board_list" class="form-inline">
						<div class="form-group">
							<c:if test="${empty category}">
								<input type="hidden" name="category" value=2>
							</c:if>
							<input type="hidden" name="category" value="${category}">
							<input type="text" name="search" placeholder="제목검색" value="${filter}" class="form-control">
							
							<div class="btn-area">
								<input type="submit" value="검색" style="border-radius: 13px">
							</div>
							
							<div class="btn-area">
								<input type="button" value="글 작성" style="border-radius: 13px" onclick="location.href='board_write?category=${category}'">
							</div>
						</div>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	
	<!-- 페이지 작업하는 공간 -->
	<div align="center">
		<ul class="pageination">
			<c:if test="${!boardList.isFirst() && pageNumber > 9}">
				<li><a href="board_list?category=${category}&search=${search}&page=${startPage-2}">이전</a></li>
			</c:if>
			<c:forEach var="page" begin="${startPage}" end="${endPage}">
    			<li class="${pageNumber-1 == page ? 'active' : ''}">
       				<a href="board_list?category=${category}&search=${search}&page=${page-1}">${page}</a>
       			</li>
			</c:forEach>
			<c:if test="${!boardList.isLast()&&endPage<totalPage}">
				<li>
					<a href="board_list?category=${category}&search=${search}&page=${endPage}">다음</a>
				</li>
			</c:if>
		</ul>
	</div>

<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script>

<script>
	$(document).ready(function() {
		var msg = '${msg}';
		if(msg != ''){
			alert(msg);
		}
	})
</script>

<script type = "text/javascript">
	function selectOnChange(value) {
		if(value == 1) {
			location.href="../pic_board/pic_list?category=1";
		} else if(value == 2) {
			location.href="board_list?category=2";
		} else {
			location.href="board_list?category=3";
		}
	}
</script>