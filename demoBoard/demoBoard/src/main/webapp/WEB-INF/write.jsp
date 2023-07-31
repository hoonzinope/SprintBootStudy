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
    <h3>게시판 글쓰기</h3>
    <section>
        <dt>제목</dt>
          <dd>
              <input
                  type="text"
                  id="title"
                  placeholder="제목 필수"
              />
          </dd>
          <dt>내용</dt>
            <dd>
                <textarea
                    id="content"
                    cols="50" rows="10"
                    placeholder="내용"
                ></textarea>
            </dd>
        <dt>작성자</dt>
          <dd>
              <input
                  type="text"
                  id="user"
                  placeholder="작성자명"
              />
          </dd>
          <dd>
            <input type="button" id="submit" value="등록"/>
          </dd>
    </section>
  </body>
  <script
  type="text/javascript" 
  src="${pageContext.request.contextPath}/resources/js/write.js"></script>
</html>