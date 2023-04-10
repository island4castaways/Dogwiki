<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section>
   <div align="center">
      <form name = "regform" action="board_write" method="post">
         <h2>게시판 글작성 페이지</h2>
         <hr>
         <table border="1" width="500">
            <tr>
               <td>작성자</td>
               <!--<td><input type = "text" name ="writer_id" size="10" value="${sessionScope.user_name}"
               ${sessionScope.user_name != null ? "readonly" : "" }></td>-->
               <td><input type = "text" name ="writer_id"></td>
            </tr>
            <tr>
               <td>글제목</td>
               <td><input type = "text" name ="title"></td>
            </tr>
            <tr>
               <td>분류</td>
               <td><select name="category">
					<option value=1 >우리 아이가 제일 멋져</option>
					<option value=2 >사료&간식 추천</option>
					<option value=3 >아이가 아파요</option>
				</select>
				</td>
            </tr>
            <tr>
               <td>글내용</td>
               <td><textarea rows="10" style = "width:100%;" name ="content"></textarea></td>
            </tr>
        
            <tr>
               <!-- 글 등록 메뉴 -->
               <td colspan="2" align="center">
               	  <input type = "hidden" name ="category" value="${param.category}">
                  <input type ="button" value="등록" onclick="registCheck()">
                  <input type ="button" value="목록" onclick="location.href='board_list?category=${param.category }&search=${param.search}&page=${param.page}'">
               </td>
            </tr>
         </table>
      </form>
   </div>
</section>
<!-- registCheck()구현: 작성자, 글제목에 공백을 확인하고, 공백이 아니라면 submit() 처리 -->
<script type = "text/javascript">
   function registCheck(){
      if(document.regform.writer_id.value == ""){
         alert("작성자를 입력하세요.");
         return;
      }else if(document.regform.title.value == ""){
         alert("제목을 입력하세요.");
         return;
      }else if(confirm("게시글을 등록하겠습니까?")){
         document.regform.submit();
      }
   }
</script>