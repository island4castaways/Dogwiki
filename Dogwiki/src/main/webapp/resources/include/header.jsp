<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="/resources/css/sub.css">
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
		<link rel="stylesheet" type="text/css" href="/resources/css/board.css">
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
	</head>
	<body>
	<div id="menu">
		<ul class="main1">
			<li style="border: none;"><a href=""> <img href="/home" src="/resources/img/logo.png" width="214.5px"></li>
			<li style="border: none;"><a href="/board/board_list">Board</a>
				<ul class="main2">
					<li><a href="/pic_board/pic_list">내 새끼 짱</a></li>
					<li><a href="/board/board_list?category=2">사료 간식 추천</a></li>
					<li><a href="/board/board_list?category=3">아이가 아파요</a></li>
				</ul></li>

			<li><a href="/training">Training</a>
			<c:choose>
				<c:when test="${sessionScope.userid == null}">
					<li><a href="/login">Login</a></li>
					<li><a href="/join">Join</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/user_logout">Logout</a></li>
					<li><a href="/mypage">My page</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</body>
