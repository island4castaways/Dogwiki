<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

	<div class = "container">
		<h3>Dogwiki 게시판</h3>
		
		<table class ="table table-boardered">
			<!-- 게시글 -->
			<thead>
				<tr>
					<th>글 번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var = "bo" items="${page }">
				<tr>
					<td>${bo.num }</td>
					<td>${bo.writer_id }</td>
					<td>
						<a href = "board_content?num=${bo.num }&page=${pageNumber}">${bo.title }</a>
					</td>
					<td>${bo.regdate }</td>
					<td>${bo.hit }</td>
				</tr>
				</c:forEach>
			</tbody>
			<tbody>
				<!-- 작성글 검색 및 글작성하기 메뉴 -->
				<tr>
					<td colspan="5" align="right">
					<form action = "" class="form-inline">
						<div class="form-group">
							<input type="text" name="search" placeholder="제목검색" class="form-control">
							<input type = "submit" value="검색" class="btn btn-default">
							<input type = "button" value="글 작성" class="btn btn-primary" onclick="location.href='board_write?page=${pageNumber}'">
						</div>
					</form>
					</td>
				</tr>
			</tbody>
		</table>
		
		<!-- 페이지 작업하는 공간 -->
		<div align="center">
		<ul class = pageination pagination -sm">
		<c:if test = "${!boardList.isFirst()&&pageNumber>9}">
		<li><a href="board_list?page=${startPage-2}">이전</a></li>
		</c:if>
		<c:forEach var="page" begin="${startPage}" end="${endPage}">
    	<li class="${pageNumber-1 == page ? 'active' : ''}">
        <a href="board_list?page=${page-1}">${page}</a></li>
		</c:forEach>
		<c:if test = "${!boardList.isLast()&&endPage<totalPage}">
		<li><a href="board_list?page=${endPage}">다음</a></li>
		</c:if>
		</ul>
		</div>

	<script src = "http://code.jquery.com/jquery-3.4.0.min.js"></script>
	<script>
	$(document).ready(function(){
		var msg = '${msg}';
		if(msg != ''){
			alert(msg);
		}
	})
	</script>
			

