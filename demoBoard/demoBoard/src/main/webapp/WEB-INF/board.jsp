<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>게시판</title>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
  </head>
  <body>
    <ul>
        <li><a href="/postBoard">글목록</a></li>
        <li><a href="writePost">글작성</a></li>
    </ul>
    <h3>게시판 글목록</h3>
    <table id="post_table">
        <thead>
            <tr>
                <th>제목</th>
                <th>작성자</th>
                <th>작성시간</th>
            <tr>
        </thead>
        <tbody>
            <tr>
            <tr>
        </tbody>
    </table>
  </body>
  <script type="text/javascript">
    var posts = ${posts};
  </script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board.js"></script>
</html>