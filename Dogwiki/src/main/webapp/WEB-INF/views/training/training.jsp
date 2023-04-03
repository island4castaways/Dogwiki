<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
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
						<li><a href="/training?st=강형욱">강형욱 훈련사</a></li>
					</td>
					<td>&nbsp;</td>
					<td>
						<li><a href="/training?st=설채현">설채현 수의사</a></li>
					</td>
				</ul>
			</tr>
		</table>
		
		<c:if test="${msg != null}">
			<p>${msg}</p>
		</c:if>
		
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
				<c:forEach var="en" items="${list}">
					<tr>
						<td>${en.trProf}</td>
						<td><a href="/training/content?num=${en.trId}&pn=${page.pageNum}">${en.trTitle}</a></td>
						<td>${en.trDate}</td>
						<td>${en.trHit}</td>
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
		
		<div align="center">
			<ul>
				<c:if test="${page.hasPrev}">
					<li>
						<a href="/training?pn=${page.startNum - 1}">이전</a>
					</li>
				</c:if>
				
				<c:forEach var="num" begin="${page.startNum}" 
					end="${page.endNum}" step="1">
					<li>
						<a href="/training?pn=${num - 1}">${num}</a>
					</li>				
				</c:forEach>
				
				<c:if test="${page.hasNext}">
					<li>
						<a href="/training?pn=${page.endNum + 1}">다음</a>
					</li>
				</c:if>
			</ul>
		</div>
		
	</body>
</html>