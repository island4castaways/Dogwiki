<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resources/css/write.css">

<section style="margin-top: 150px;">
    <h1 class="con" style="margin-bottom: 20px; text-align: center;">게시판 글내용 보기</h1>

<section class="article-detail table-common con row" style="text-align:center;">
    <!-- 글 등록 메뉴 -->	
        <table class="cell" border="1">
            <colgroup>
                <col width="100px">
            </colgroup>
            
            <tbody>
                <tr class="article-title">
  <th style="font-size: 17px; width: 100px;">작성자</th>
  <td>${picEntity.user.userid}</td>
  <th style="width: 100px; font-size: 17px;">제목</th>
  <td colspan="3">${picEntity.title}</td>            	
  <th style="width: 100px; font-size: 17px; text-align: center;">분류</th>       
  <td width="23%">내새끼 짱</td>
</tr>
<tr class="article-info">
  <th style="font-size: 17px; width: 150px;">작성일</th>
  <td style="font-size: 17px;">${picEntity.pic_regdate}</td>
  <th style="font-size: 17px; width: 150px;">조회수</th>
  <td style="font-size: 17px;">${picEntity.hit}</td>    
  
  <tr class="article-body">
  <th>사진</th>
  <td colspan="8" style="text-align: center;">
    <img src="/files/${picEntity.filename}" style="width: 80%; height: auto;">
  </td>
</tr>  
     
</tr>
		</tbody>
		
		
        </table>

</section>

		<section class="pic_delete" style="margin-top: 5px;">
		<form name="heartForm" method="post" action="pic_heart" class="content-btn">
			<input type="hidden" name="category" value="${param.category}">
			<input type="hidden" name="search" value="${param.search}">
			<input type="hidden" name="page" value="${page}">
			<input type="hidden" name="picnum" value="${picEntity.picnum}">						
				<input class="${heartExists ? 'heartOn' : 'heartOff'}" 
       type="button" name="heart" 
       value="좋아요&nbsp;&#9829;&nbsp;+${picEntity.heart}" 
       onclick="heartCheck()"
       style="background-color: ${heartExists ? '#f39aa0' : 'rightgrey' };"
>
			<input type="button" value="목록" onclick="location.href='pic_list?category=${param.category}&search=${param.search}&page=${page}'">
		</form>
				
		<form name="deleteForm" action="pic_delete" method="get" class="content-btn" style="margin-left: 10px;">
			<input type="hidden" name="picnum" value="${picEntity.picnum}">
			<c:if test="${picEntity.user.userid.equals(sessionScope.userid)}">
				<input type ="submit" value="삭제" onclick="deleteCheck()">
			</c:if>
		</form>
		</section>

<script>
	function heartCheck() {
		if(${sessionScope.userid != null}) {
			document.heartForm.submit();
		} else {
			if(confirm("좋아요를 누르시려면 로그인이 필요합니다. 로그인 하시겠습니까?")) {
				location.href="/login";
			}
		}
	}
	
	function deleteCheck() {
		if(confirm("게시글을 삭제하시겠습니까?")) {
			document.deleteForm.submit();
		}
	}
</script>
</section>
<%@ include file="/resources/include/footer.jsp"%>