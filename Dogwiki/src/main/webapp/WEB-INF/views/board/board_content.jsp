<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resources/css/write.css">

    <h1 class="con" style="margin-bottom: 20px;">게시판 글내용 보기</h1>
    <section class="article-detail table-common con row" style="text-align:center;">
    <!-- 글 등록 메뉴 -->	
   
        
        <table class="cell" border="1">
            <colgroup>
                <col width="100px">
            </colgroup>
            
            <tbody>
                <tr class="article-title">
                <th style="font-size: 17px;">작성자</th>
               	<td>${boardContent.user.userid}</td>
                    <th style="width: 60px; font-size: 17px;">제목</th>
                    	<td colspan="3">${boardContent.title}</td>
                    	
                    <th style="width: 60px; font-size: 17px; text-align: center;">분류</th>       
					<td width="30%">${boardContent.category == 2 ? '사료간식 추천':'아이가 아파요'}</td>
                </tr>
                <tr class="article-info">
                    <th style="font-size: 17px;">작성일</th>
                    <td style="font-size: 17px; ">${boardContent.regdate}</td>
                    <th style="font-size: 17px ; width: 90px;">조회수</th>
                    <td style="font-size: 17px;">${boardContent.hit}</td>
                </tr>
                
                <tr class="article-body">
				<th>글내용</th>
				<td colspan="1"><pre style="font-size: 17px;">${boardContent.content}</pre></td>
			</tr>
            </tbody>
        </table>
   	
	</section>
	 <section style="margin-top: 10px;" >			
					<div class="content-btn">
						<input type="button" value="목록" onclick="location.href='board_list?category=${param.category}&search=${param.search}&page=${page}'">
						<c:if test="${boardContent.user.userid.equals(sessionScope.userid)}">
							<input type="button" value="수정" onclick="location.href='board_modify?category=${param.category}&search=${param.search}&num=${boardContent.num}&page=${page}'">
						</c:if>
					</div>		
	</section>
	


	<div class="con reply">	
		<table class="reply-list table-common">
			<tr>
				<td>작성자</td>
				<td >댓글 내용</td>
				<td >작성일</td>
							
			</tr>
			<c:forEach var="cmt" items="${cmtList}">
				<tr class="article-title">
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
	
	<div class="reply-list table-common">
		<form method="post" action="board_comment">
			<input type="text" name="category" value="${param.category}" hidden>
			<input type="text" name="search" value="${param.search}" hidden>
			<input type="text" name="page" value="${page}" hidden>
			<input type="text" name="board_basic" value="${boardContent.num}" hidden>
			<input type="text" name="user" value="${sessionScope.userid}" hidden>

		<div class="con reply">
			<h3>댓글 입력</h3>
			<section class="reply-form">						
					<input type="text" name="cmtContent" style="width: 200px;"> <input
						type="submit" value="작성">							
			</section>
			
		</div>
	</form>
	</div>

		

<script>
	function check() {
		if(confirm("댓글을 삭제하시겠습니까?")) {
			document.deleteForm.submit();
		}
	}
</script>
<%@ include file="/resources/include/footer.jsp"%>