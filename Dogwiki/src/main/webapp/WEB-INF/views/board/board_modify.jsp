<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section>
   <div align="center">
      <form name = "regform" action="board_update?pageNum=${param.pageNum }" method="post">
         <h2>게시판 글작성 페이지</h2>
         <hr>
         <table border="1" width="500">
            <tr>
               <td>글번호</td>
               <td><input type = "text" name ="num" value="${boardContent.num}" readonly></td>
            </tr>
            <tr>
               <td>작성자</td>
               <td><input type = "text" name ="writer" value="${boardContent.writer_id}" readonly></td>
            </tr>
            <tr>
               <td>제목</td>
               <td><input type = "text" name ="title" value="${boardContent.title}" style="width:100%;"></td>
            </tr>
            <tr>
               <td>글내용</td>
               <td align="center"><textarea rows="10" style = "width:100%;" name ="content">${boardContent.content }</textarea></td>
            </tr>
            <tr>
               <!-- 글 등록 메뉴 -->
               <td colspan="2" align="center">
                  <input type ="button" value="수정" onclick="modifyCheck()">
                  <input type ="button" value="목록" onclick="location.href='board_list?page=${page}'">
                  <input type ="button" value="삭제하기" onclick="location.href='board_delete?num=${vo.num}&pageNum=${param.pageNum}'">
               </td>
            </tr>
         </table>
      </form>
   </div>
</section>
<!-- registCheck()구현: 작성자, 글제목에 공백을 확인하고, 공백이 아니라면 submit() 처리 -->
<script type = "text/javascript">
   function modifyCheck(){
      if(document.regform.title.value == ""){
         alert("제목을 입력하세요.");
         return;
      }else if(confirm("게시글을 수정하겠습니까?")){
         document.regform.submit();
      }
   }
</script>
