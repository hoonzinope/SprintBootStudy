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
    <h3>게시글</h3>
    <section>
        <dt>제목</dt>
          <dd>
              <div id="title">
              </div>
          </dd>
          <dt>내용</dt>
            <dd>
                <div id="content">
                </div>
            </dd>
        <dt>작성자</dt>
          <dd>
            <div id="user">
            </div>
          </dd>
        <dt>작성시간</dt>
          <dd>
            <div id="createAt">
            </div>
          </dd>
    </section>
    <section>
        <h6>댓글 등록</h6>

    </section>
    <section>
        <h6>댓글 목록</h6>
        
    </section>
  </body>
    <script type="text/javascript">
        var postDetail = ${postDetail};
    </script>
  <script
  type="text/javascript"
  src="${pageContext.request.contextPath}/resources/js/detail.js"></script>
</html>