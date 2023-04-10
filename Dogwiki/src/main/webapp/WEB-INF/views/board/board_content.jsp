<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<div align="center">
		<form>
			<h2>게시판 글내용 보기</h2>
			<hr>
			<table border="1" width="500">
				<tr>
					<td width="20%">글번호</td>
					<td width="30%">${boardContent.num}</td>
					<td width="20%">조회수</td>
					<td width="30%">${boardContent.hit}</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${boardContent.regdate}</td>
					<td>작성자</td>
					<td>${boardContent.writer_id}</td>
				</tr>
				<tr>
					<td>분류</td>
					<td colspan="3">${boardContent.category}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3">${boardContent.title}</td>
				</tr>
				<tr>
					<td>글내용</td>
					<td colspan="3" height="100%"><pre>${boardContent.content}</pre></td>
				</tr>
				<tr>
					<!-- 글 등록 메뉴 -->
					<td colspan="2" align="center">
						<input type ="button" value="목록" onclick="location.href='board_list?category=${param.category}&search=${param.search}&page=${page}'">
						<input type ="button" value="수정" onclick="location.href='board_modify?category=${param.category}&search=${param.search}&num=${boardContent.num}&page=${page}'">
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div class="comments">
		<table>
			<tr>
				<td width="10%">작성자</td>
				<td width="80%">댓글 내용</td>
				<td width="10%">작성일</td>
			</tr>
			<c:forEach var="cmt" items="${cmtList}">
				<tr>
					<td>${cmt.com_writer_id}</td>
					<td>${cmt.com_content}</td>
					<td>${cmt.com_regdate}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="comment-txt">
		<form method="post" action="comment_write">
			   <input type="text" name="cmt_content" placeholder="댓글 작성">
			   <input type="submit" value="완료">
		</form>
	</div>
</section>