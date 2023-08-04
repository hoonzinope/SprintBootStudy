<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>music dashBoard</title>
  <link
        rel="stylesheet"
        href="./resources/css/details/post.css"
        crossorigin="anonymous"
        referrerpolicy="no-referrer"
    />

    <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
    <!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>
</head>
<body>
  <div class="wrap">
    <div class="goingHome"><a href = "/musicBoard">home</a></div>
    <div class="search">
       <select class="selectTerm">
         <option value="artist">artist</option>
         <option value="album">album</option>
         <option value="track">track</option>
       </select>
       <input type="text" class="searchTerm" placeholder="What music are you looking for?">
       <button type="submit" class="searchButton">
        <svg class="svg-inline--fa fa-search fa-w-16" aria-hidden="true" data-prefix="fas" data-icon="search" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
          <path fill="currentColor" d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5 7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7 0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128 128 0 70.7-57.2 128-128 128z"></path>
          </svg>
      </button>
    </div>
 </div>
 <div class="content">
  <section class="post">
    <div id="name"></div>
    <div id="img"></div>
    <div id="type"></div>
  </section>
 </div>
<a href="#" class="btn_gotop">
  <span class="glyphicon glyphicon-chevron-up">
  </span>
</a>
</body>
<script>
  let resultInfo = ${resultInfo};
  console.log(resultInfo);
</script>
<script type="text/javascript" src="./resources/js/details/post.js"></script>
</html>