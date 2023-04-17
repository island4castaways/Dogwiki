<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resources/css/write.css">

    <section class="article-detail table-common con row" style="text-align:center;">
			<h2 style="margin-bottom: 15px;">훈련 영상 보기</h2>
        <table class="cell" border="1">
            <colgroup>
                <col width="100px">
            </colgroup>
            
            <tbody>
                <tr class="article-title">
                  <th style="font-size: 17px;">훈련사</th>
						<td colspan="3">${en.trProf}</td>
					</tr>
					<tr>
						<th style="font-size: 17px;">제목</th>
						<td width="80%" style="font-size: 15px;">${en.trTitle}</td>
						
					</tr>
					
					</table>
	<table>
		<tr>
			<td colspan="1" align="center"><iframe src="${en.trUrl}"
					width="930" height="523.125"></iframe></td>
		</tr>
	</table>

	<div class="content-btn">
				<input type="button" value="목록"
					onclick="location.href='/training?pn=${param.pn}&st=${param.st}&search=${param.search}'">
			</div>
		</section>

	</body>
<%@ include file="/resources/include/footer.jsp"%>
</html>