<%@ include file="/resources/include/header.jsp"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resources/css/write.css">


	<form name = "regform" action="board_write" method="post" style="margin-top: 150px;">
   	 <section class="article-detail table-common con row" style="text-align:center;">
    <h1 class="con" style="margin-bottom: 20px;">게시글 작성</h1>

        
        <table class="cell" border="1">
            <colgroup>
                <col width="100px">
            </colgroup>
            
            <tbody>
                <tr class="article-title">
                <th style="font-size: 18px;">작성자</th>
	                <td class="board_write2">
			                 <div class="int-area">
				                 <input type="text" name="user" size="10" value="${sessionScope.userid}"
								${sessionScope.userid != null ? "readonly" : "" }>
							</div>
						</td>
						
                    <th style="width: 90px; font-size: 18px;">글 제목</th>
                    <td>
	                    <div class="int-area">
	                    	<input type = "text" name ="title" >
	                    </div>
                    </td>
                    
                    <th style="width: 60px; font-size: 18px; text-align: center;">분류</th>
                    <td colspan="3">
                    				<select name="category" onchange="selectOnChange(value)">
							<option value=1 ${param.category == '1' ? 'selected="selected"' : ''}>우리 아이가 제일 멋져</option>
							<option value=2 ${param.category == '2' ? 'selected="selected"' : ''}>사료& 간식 추천</option>
							<option value=3 ${param.category == '3' ? 'selected="selected"' : ''}>아이가 아파요</option>
						</select>
                    </td>
                </tr>
                
                <tr class="article-body">
                    <td colspan="4"><textarea rows="30" style = "width:185.2%;" name ="content" placeholder="여기에 내용를 적어보세요."></textarea></td>
                </tr>
            </tbody>
        </table>
        	<!-- 글 등록 메뉴 -->				
						<input type = "hidden" name ="category" value="${param.category}">
					<div class="write-brn" >
						<input type ="button" value="등록" onclick="registCheck()">
					</div>
					<div class="write-brn" style="margin-bottom: 20px;">
						<input type ="button" value="목록" onclick="location.href='board_list?category=${param.category }&search=${param.search}&page=${param.page}'">
					</div>
    </section>
				</form>	
    
<!-- registCheck()구현: 작성자, 글제목에 공백을 확인하고, 공백이 아니라면 submit() 처리 -->
<script type="text/javascript">
	function registCheck() {
		if(document.regform.user.value == "") {
			alert("작성자를 입력하세요.");
			return;
		} else if(document.regform.title.value == "") {
			alert("제목을 입력하세요.");
			return;
		} else if(confirm("게시글을 등록하겠습니까?")) {
			document.regform.submit();
		}
	}
</script>

<script type="text/javascript">
	function selectOnChange(value) {
		if(value == 1) {
			location.href="../pic_board/write?category=1";
		} else if(value == 2) {
			location.href="board_write?category=2";
		} else {
			location.href="board_write?category=3";
		}
		var category = value;
	}
</script>

<%@ include file="/resources/include/footer.jsp"%>
