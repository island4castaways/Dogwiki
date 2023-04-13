<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<section>
	<div align="center">
		<form>
			<h2>게시판 글내용 보기</h2>
			<table border="1" style="width: 500px;" class="board-wrap">
				<tr>
					<td width="20%">분류</td>
					<td width="30%">${boardContent.category == 2 ? '사료간식 추천':'아이가 아파요'}</td>
					<td width="20%">조회수</td>
					<td width="30%">${boardContent.hit}</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${boardContent.user.userid}</td>
					<td>작성일</td>
					<td>${boardContent.regdate}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3">${boardContent.title}</td>
				</tr>
				<tr>
					<td>글내용</td>
					<td colspan="3" height="100%"><pre>${boardContent.content}</pre></td>
		
				</tr>
			</table>
							
					<!-- 글 등록 메뉴 -->				
					<div class="content-btn">
						<input type="button" value="목록" onclick="location.href='board_list?category=${param.category}&search=${param.search}&page=${page}'">
						<c:if test="${boardContent.user.userid.equals(sessionScope.userid)}">
							<input type="button" value="수정" onclick="location.href='board_modify?category=${param.category}&search=${param.search}&num=${boardContent.num}&page=${page}'">
						</c:if>
					</div>		
		</form>
	</div>
	</section>
	

	<div class="comments">
		<table class="content-wrap">
			<tr>
				<td>작성자</td>
				<td >댓글 내용</td>
				<td >작성일</td>
			
				
			</tr>
			<c:forEach var="cmt" items="${cmtList}">
				<tr>
					<c:choose>
						<c:when test="${cmt.user.userid.equals(sessionScope.userid)}">
							<td>${cmt.user.userid}</td>
							<td>
								<form method="post" action="board_comment" class="content">
									<input type="hidden" name="category" value="${param.category}">
									<input type="hidden" name="search" value="${param.search}">
									<input type="hidden" name="page" value="${page}">
									<input type="hidden" name="board_basic" value="${boardContent.num}">
									<input type="hidden" name="cmtNum" value="${cmt.cmtNum}">
									<input type="hidden" name="user" value="${cmt.user.userid}">
									<div class="input-container">
										<input type="text" name="cmtContent" value="${cmt.cmtContent}">
									<div class="write-brn">	
										<input type="submit" name="modify_comment" value="수정">
									</div>
									</div>
								</form>
							</td>
							<td>${cmt.cmtDate}</td>
							<td>
								<form name="deleteForm" method="post" action="board_comment_delete">
									<input type="hidden" name="category" value="${param.category}">
									<input type="hidden" name="search" value="${param.search}">
									<input type="hidden" name="page" value="${page}">
									<input type="hidden" name="board_num" value="${boardContent.num}">
									<input type="hidden" name="cmtNum" value="${cmt.cmtNum}">
									<div class="write-brn">	
										<input type="submit" name="delete_comment" value="삭제" onclick="check()">
									</div>
								</form>
							</td>
						</c:when>
						<c:otherwise>
							<td>${cmt.user.userid}</td>
							<td>${cmt.cmtContent}</td>
							<td>${cmt.cmtDate}</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="training">
		<form method="post" action="board_comment">
			<input type="text" name="category" value="${param.category}" hidden>
			<input type="text" name="search" value="${param.search}" hidden>
			<input type="text" name="page" value="${page}" hidden>
			<input type="text" name="board_basic" value="${boardContent.num}" hidden>
			<input type="text" name="user" value="${sessionScope.userid}" hidden>
			
			<div class="input-container">
				<input type="text" name="cmtContent" placeholder="댓글 작성">
			<div class="write-brn" style="margin-left: 10px;">	
			<input type="submit" value="완료">
			</div>
			</div>
		</form>
	</div>
</section>

<script>
	function check() {
		if(confirm("댓글을 삭제하시겠습니까?")) {
			document.deleteForm.submit();
		}
	}
</script>