<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>training info</title>
	</head>
	<body>
		<h2>훈련 정보</h2>
		<hr>
		<table class="table">
			<tr>
				<ul>
					<td>
						<li><a href="#">강형욱 훈련사</a></li>
					</td>
					<td>&nbsp;</td>
					<td>
						<li><a href="#">설채현 수의사</a></li>
					</td>
				</ul>
			</tr>
		</table>
		
		<table class="table">
			<thead>
				<tr>
					<th>전문가</th>
					<th>훈련 내용</th>
					<th>업로드일</th>
					<th>조회수</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="entity" items="${ list }">
					<tr>
						<td>${ entity.prof }</td>
						<td><a href="#">${ entity.tr_title }</a></td>
						<td>${ entity.upd_date }</td>
						<td>${ entity.tr_hit }</td>
					</tr>
				</c:forEach>
			</tbody>
			
			<tbody>
				<tr>
					<td colspan="5" align="right">
						<form action="search" class="form">
							<div>
								<input type="text" name="search" 
								placeholder="제목 검색" class="form">
								<input type="submit" value="검색" class="button">
							</div>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>