<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<section>
   <div align="center">
         <h2>게시판 글작성 페이지</h2>
         <hr>
      <form name = "regform" action="board_update" method="post">
         <table border="1" style="width: 500px;" class="board-wrap">
            <tr>
               <td>글번호</td>
               <td><input type="text" name="num" value="${boardContent.num}" readonly></td>
            </tr>
            <tr>
               <td>작성자</td>
               <td><input type="text" name="user" value="${boardContent.user.userid}" readonly></td>
            </tr>
            <tr>
               <td>제목</td>
               <td><input type="text" name="title" value="${boardContent.title}" style="width:100%;"></td>
            </tr>
            <tr>
               <td>글내용</td>
               <td align="center"><textarea rows="10" style="width:100%;" name="content">${boardContent.content}</textarea></td>
            </tr>
            <tr>
               <!-- 글 등록 메뉴 -->
               <td colspan="2" align="center">
               	  <input type="hidden" name="category" value="${param.category}">
               	  <div class="write-brn">
                  <input type="button" value="수정" onclick="modifyCheck()">
                  <input type="button" value="목록" onclick="location.href='board_list?category=${param.category}&search=${param.search}&page=${param.page}'">
                  <input type="button" value="삭제하기" onclick="location.href='board_delete?num=${boardContent.num}&page=${param.page}&category=${param.category}'">
               </div>
               </td>
            </tr>
         </table>
      </form>
   </div>
</section>

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
