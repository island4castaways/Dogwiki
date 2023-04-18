<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<section style="margin-top: 150px;">
	<h3 style="text-align: center;">Dogwiki 게시판</h3>
	<section>
	<section class="section1">
		
		<select name="category" onchange="selectOnChange(value)" style="height: 35px;">
			<option value=1 ${param.category == '1' ? 'selected="selected"' : ''}>내새끼 짱</option>
			<option value=2 ${param.category == '2' ? 'selected="selected"' : ''}>사료간식 추천</option>
			<option value=3 ${param.category == '3' ? 'selected="selected"' : ''}>아이가 아파요</option>
		</select>
	</section>

	<table class="table-boardered">
		<!-- 게시글 -->
		<thead>
			<tr>
				<th>작성자</th>
				<th>제목</th>
				<th>날짜</th>
				<th style="text-align: center;">좋아요</th>
				<th style="text-align: center;">조회수</th>
			</tr>
		</thead>
		<tbody class="table-boardered">
			<c:forEach var="bo" items="${list}">
			<tr>
				<td style="width: 90px;">${bo.user.username}</td>
				<td style="width: 380px;">
					<a href="pic_content?category=${category}&search=${search}&num=${bo.picnum}&page=${pageNumber}">${bo.title}</a>
				</td>
				<td style="width: 230px;">${bo.pic_regdate}</td>
				<td style="width: 60px; text-align: center;">${bo.heart}</td>
				<td style="width: 60px; text-align: center;">${bo.hit}</td>
			</tr>
			</c:forEach>
		</tbody>
		<tbody>
		
					<!-- 작성글 검색 및 글작성하기 메뉴 -->
			<tr>
				<td colspan="5" class="board_list">
					<form method="get" action="board_list" class="form-inline">
						<div class="form-group">
							<c:if test="${empty category}">
								<input type="hidden" name="category" value=2>
							</c:if>
							<div class="board-input-wrapper">
								<input type="hidden" name="category" value="${category}">
							<div class="board-int-area">
								<input type="text" name="search" value="${filter}">
							</div>
							
							<div class="write-brn">
								<input type="submit" value="검색" style="border-radius: 10px">
							</div>
							
							<div class="write-brn" style="margin-left: 10px;">
								<input type="button" value="글 작성" style="border-radius: 10px" onclick="location.href='write?category=${category}'">
							</div>
							</div>
						</div>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	
	<!-- 페이지 작업하는 공간 -->
	<div align="center" class="paging">
		<ul class="paging-list">
			<c:if test="${!boardList.isFirst() && pageNumber>9}">
				<li>
					<a href="pic_list?category=${category}&search=${search}&page=${startPage-2}">이전</a>
				</li>
			</c:if>
			<c:forEach var="page" begin="${startPage}" end="${endPage}">
    			<li class="${pageNumber == (page-1) ? 'active' : 'paging'}">
       				<a href="pic_list?category=${category}&search=${search}&page=${page-1}">${page}</a>
       			</li>
			</c:forEach>
			<c:if test="${!boardList.isLast() && endPage<totalPage}">
				<li>
					<a href="pic_list?category=${category}&search=${search}&page=${endPage}">다음</a>
				</li>
			</c:if>
		</ul>
	</div>
	</section>

<script src="http://code.jquery.com/jquery-3.4.0.min.js"></script>

<script>
	$(document).ready(function() {
		var msg = '${msg}';
		if(msg != '') {
			alert(msg);
		}
	})
</script>

<script type="text/javascript">
	function selectOnChange(value) {
		if(value == 1) {
			location.href="../pic_board/pic_list?category=1";
		} else if(value == 2) {
			location.href="../board/board_list?category=2";
		} else {
			location.href="../board/board_list?category=3";
		}
	}
</script>
</section>
<%@ include file="/resources/include/footer.jsp"%>