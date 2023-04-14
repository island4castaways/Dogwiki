<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

		<section>
			<div align="center">
				<h2>훈련 영상 보기</h2>
				<hr>
				<table border="1" style="width: 900px;" class="board-wrap">
					<tr>
						<td width="40%">전문가</td>
						<td width="50%">${en.trProf}</td>
					</tr>
					<tr>
						<td width="20%">제목</td>
						<td width="80%">${en.trTitle}</td>
						
					</tr>
					
					</table>
					<table>
					<tr>
						<td colspan="1" align="center">
							<iframe src="${en.trUrl}"></iframe>
						</td>
					</tr>
					</table>
	
			<div class="content-btn">
				<input type="button" value="목록"
					onclick="location.href='/training?pn=${param.pn}&st=${param.st}&search=${param.search}'">
			</div>
		</div>
		</section>
	</body>
</html>