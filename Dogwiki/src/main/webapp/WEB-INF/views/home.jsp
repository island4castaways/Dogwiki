<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/resources/include/header.jsp"%>
<link rel="stylesheet" type="text/css" href="/resources/css/home.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />


    <!-- 게시글 -->

    <div class="home home-top" >
    <h2>조회수 TOP3 미모</h2>
        <c:forEach var="bo" items="${picEntity}">
                <div class="content-img">
                    <h3>${bo.title}</h3>
                    <div class="img">
                       <a href="/pic_board/pic_content?category=1&search=${search}&num=${bo.picnum}&page=${pageNumber}">
                           <img src="/files/${bo.filename}" style="width:auto; height: 150px; ">
                           <p><i class="fa-sharp fa-solid fa-heart" style="color: red;"></i> &nbsp; ${bo.heart}</p>
                       </a>
                    </div>
                </div>
        </c:forEach>
        <div style="text-align: right">
            <a href="/pic_board/pic_list">view more &nbsp;<i class="fa-solid fa-arrow-right"></i></a>
        </div>
    </div>


   <div class="home home-middle">
      <table>
         <!-- 게시글 -->
         <h2>사료간식 추천 TOP 5</h2>
         <thead>
            <tr>
               <th>작성자</th>
               <th>제목</th>
               <th>날짜</th>
               <th>조회수</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach var="bo" items="${board2Entity}">
               <tr>
                  <td>${bo.user.userid}</td>
                  <td ><a
                     href="/board/board_content?category=2&search=${search}&num=${bo.num}&page=${pageNumber}">${bo.title}</a>
                  </td>
                  <td>${bo.regdate}</td>
                  <td>${bo.hit}</td>
               </tr>
            </c:forEach>
         </tbody>
      </table>
   </div>


<div  class="home home-bottom">
   <table>
      <!-- 게시글 -->
      <h2>아이가 아파요 TOP 5</h2>
      <thead>
         <tr>
            <th style="margin: 20px;">작성자</th>
            <th style="margin: 60px;">제목</th>
            <th style="margin: 20px;">날짜</th>
            <th style="margin: 20px;">조회수</th>
         </tr>
      </thead>
      <tbody>
         <c:forEach var="bo" items="${board3Entity}">
            <tr>
               <td>${bo.user.userid}</td>
               <td><a
                  href="/board/board_content?category=3&search=${search}&num=${bo.num}&page=${pageNumber}">${bo.title}</a>
               </td>
               <td>${bo.regdate}</td>
               <td>${bo.hit}</td>
            </tr>
         </c:forEach>
      </tbody>
   </table>
</div>

   <div  class="home home-avi">
            <h2>최신 훈련 영상</h2>
         <div class="av">
            <iframe src="${trainingEntity.trUrl}" width="800" height="450" ></iframe>
         </div>
   </div>

<%@ include file="/resources/include/footer.jsp"%>


