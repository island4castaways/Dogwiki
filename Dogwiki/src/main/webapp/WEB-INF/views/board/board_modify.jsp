<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resources/css/write.css">


<h1 class="con" style="margin-bottom: 20px; margin-top: 100px;">게시판 글내용 보기</h1>
        <form name = "regform" action="board_update" method="post">
    <section class="article-detail table-common con row" style="text-align:center;">
    <!-- 글 등록 메뉴 -->	
   
        <table class="cell" border="1">
            <colgroup>
                <col width="100px">
            </colgroup>
            
            <tbody>
                <tr class="article-title">
                <th style="font-size: 17px;">작성자</th>
               	<td>
                <div class="int-area">
               	<input type="text" name="user" value="${boardContent.user.userid}" readonly>   </div></td>
                 
                    <th style="width: 60px; font-size: 17px;">제목</th>
                    	<td colspan="3">
                    	<div class="int-area">
                    		<input type="text" name="title" value="${boardContent.title}" style="width:100%;"></div></td>
                    	
                    <th style="width: 90px; font-size: 17px; text-align: center;">글번호</th>       
					<td width="30%">
					
					<div class="int-area">
					<input type="text" name="num" value="${boardContent.num}" readonly>
					</div></td>
                </tr>
                <tr class="article-info">
                    <th style="font-size: 17px;">작성일</th>
                    <td style="font-size: 17px; ">${boardContent.regdate}</td>
                    <th style="font-size: 17px ; width: 90px;">조회수</th>
                    <td style="font-size: 17px;">${boardContent.hit}</td>
                </tr>
                
                
                <tr class="article-body">
				<th>글내용</th>
				<td colspan="7"><textarea rows="25" style="width:100%;" name="content">${boardContent.content}</textarea></td>
			</tr>
            </tbody>            
        </table>
        </section>
         <section >
         <div class="content-btn" style="margin-top: 15px;">
               	  <input type="hidden" name="category" value="${param.category}">
               	  
                  <input type="button" value="수정" onclick="modifyCheck()">
                  <input type="button" value="목록" onclick="location.href='board_list?category=${param.category}&search=${param.search}&page=${param.page}'">
                  <input type="button" value="삭제하기" onclick="location.href='board_delete?num=${boardContent.num}&page=${param.page}&category=${param.category}'">
               
               </div>
              
  	
	</section>
        </form>
        

<!-- registCheck()구현: 작성자, 글제목에 공백을 확인하고, 공백이 아니라면 submit() 처리 -->
<script type = "text/javascript">
   function modifyCheck() {
      if(document.regform.title.value == "") {
         alert("제목을 입력하세요.");
         return;
      } else if(confirm("게시글을 수정하겠습니까?")) {
         document.regform.submit();
      }
   }
</script>
<%@ include file="/resources/include/footer.jsp"%>
