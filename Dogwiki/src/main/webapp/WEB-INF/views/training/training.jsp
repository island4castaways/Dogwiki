<%@ include file="/resources/include/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<title>Join</title>

		<title>training info</title>
	<section style="margin-top: 150px;">
		<table class="table1">
    <h2>훈련 정보</h2>
    <tr>
        <td>
                <li>
                    <a href="/training" >전체 보기</a>
                </li>
        </td>

        <td >
                <li>
                    <a href="/training?st=강형욱">강형욱 훈련사</a>
                </li>
        </td>                
        <td >
                <li>
                    <a href="/training?st=설채현">설채현 수의사</a>
                </li>                   
        </td>                   
    </tr>
</table>
		
		<c:if test="${msg != null}">
			<p>${msg}</p>
		</c:if>
		
		<table class="training" >
			<thead>
				<tr>
					<th>전문가</th>
					<th align="left">훈련 내용</th>
					<th align="left">업로드일</th>
					<th>조회수</th>
				</tr>
			</thead>
			
			<tbody class="trainig">
				<c:forEach var="en" items="${list}">
					<tr>
						<td style="width: 90px;">${en.trProf}</td>
						<td style="width: 700px;">
							<a href="/training/content?num=${en.trId}
								&pn=${page.pageNum}&st=${st}&search=${search}">
								${en.trTitle}
							</a>
						</td>
						<td>${en.trDate}</td>
						<td style="width: 30px;" >${en.trHit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
			
			<tbody>
				<tr>
					<td colspan="5" >						
						<form action="/training" method="get" class="trining-form">
						<div class="input-wrapper">
							<div class="int-area">
								<input type="text" name="search" placeholder="제목 검색" >
							</div>
							<div class="int-area">
								<input type="hidden" name="st" value="${st}">
							</div>

							<div class="btn-training">
								<input type="submit" value="검색" style="border-radius: 13px;" >
							</div>
						</div>
					</form>
				</td>
				</tr>
			</tbody>
		
		<div align="center" class="paging">
			<ul class="paging-list">
				<c:if test="${page.hasPrev}">
					<li class="paging-item">
						<a href="/training?pn=${page.startPage - 1}
							&st=${st}&search=${search}" class="paging-link">
							이전
						</a>
					</li>
				</c:if>

				
				<c:forEach var="num" begin="${page.startPage}" 
					end="${page.endPage}" step="1">
					<li class="paging-item">
						<a href="/training?pn=${num - 1}&st=${st}&search=${search}">
							${num}
						</a>
					</li>				
				</c:forEach>
				
				<c:if test="${page.hasNext}">
					<li class="paging-item">
						<a href="/training?pn=${page.endPage + 1}
									&st=${st}&search=${search}" class="paging-link">
									다음
						</a>
					</li>
				</c:if>
			</ul>
		</div>
		
	</section>
</html>