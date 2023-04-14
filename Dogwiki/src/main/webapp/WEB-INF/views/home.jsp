<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/resources/include/header.jsp"%>
<link rel="stylesheet" type="text/css" href="/resources/css/home.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />


    <!-- 게시글 -->
    
    <div class="home home-top">
    <h3>조회수 TOP3 미모</h3>
        <c:forEach var="bo" items="${picEntity}">
                <div class="content-img">
                    <h4>${bo.title}</h4>
                    <a href="/pic_board/pic_content?category=1&search=${search}&num=${bo.picnum}&page=${pageNumber}">
                        <img src="/files/${bo.filename}" style="width:auto; height: 150px; ">
                        <p><i class="fa-sharp fa-solid fa-heart"></i> &nbsp; ${bo.joa}</p>
                    </a>
                </div>
        </c:forEach>
        <div style="text-align: right">
            <a href="/pic_board/pic_list">more &nbsp;<i class="fa-solid fa-arrow-right"></i></a>
        </div>
    </div>

	
	<table class="home home-middle">
		<!-- 게시글 -->
		<h3>게시판 조회수 TOP 5</h3>
		<thead>
			<tr>
				<th><a href="/board/board_list?category=2">more &nbsp;<i class="fa-solid fa-arrow-right"></i></a></th>
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


<table class="home home-bottom">
	<!-- 게시글 -->
	<thead>
		<tr>
			<th><a href="/board/board_list?category=3">more &nbsp;<i class="fa-solid fa-arrow-right"></i></a></th>
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

<table class="home home-avi">
	<tr>
		<th><a href="/training">
</a></th>
	</tr>
	<tr>
		<h3>최신 훈련 영상</h3>
	</tr>
	<tr>
		<td><iframe src="${trainingEntity.trUrl}" width="800" height="450" ></iframe></td>
	</tr>

</table>
<%@ include file="/resources/include/footer.jsp"%>



