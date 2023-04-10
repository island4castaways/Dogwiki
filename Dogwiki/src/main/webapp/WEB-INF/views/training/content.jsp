<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Training content</title>
	</head>
	<body>
		<section>
			<div align="center">
				<h2>훈련 영상 보기</h2>
				<hr>
				<table border="1" width="500">
					<tr>
						<td width="20%">전문가</td>
						<td width="40%">${en.trProf}</td>
						<td width="20%">업로드일</td>
						<td width="20%">${en.trDate}</td>
					</tr>
					<tr>
						<td width="20%">제목</td>
						<td width="40%">${en.trTitle}</td>
						<td width="20%">조회수</td>
						<td width="20%">${en.trHit}</td>
					</tr>
					<tr>
						<td colspan="4">
							<iframe src="${en.trUrl}"></iframe>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<input type="button" value="목록"
								onclick="location.href='/training?pn=${param.pn}&st=${param.st}&search=${param.search}'">
						</td>
					</tr>
				</table>
			</div>
		</section>
	</body>
</html>