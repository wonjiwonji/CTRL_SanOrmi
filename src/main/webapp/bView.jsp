<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시글</title>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="./images/favicon.ico" />

    <!-- link start -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Custom fonts for this template -->
    <link
      href="vendor/fontawesome-free/css/all.min.css"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
      rel="stylesheet"
    />

    <!-- Custom styles for this template -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet" />

    <!-- Custom styles for this page -->
    <link
      href="vendor/datatables/dataTables.bootstrap4.min.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="css/board.css" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="favicon.ico" />
    <link
      href="https://fonts.googleapis.com/css?family=Open+Sans:400,700,300"
      rel="stylesheet"
      type="text/css"
    />

    <!-- Animate.css -->
    <link rel="stylesheet" href="css/animate.css" />
    <!-- Icomoon Icon Fonts-->
    <link rel="stylesheet" href="css/icomoon.css" />
    <!-- Bootstrap  -->
    <link rel="stylesheet" href="css/bootstrap.css" />
    <!-- Superfish -->
    <link rel="stylesheet" href="css/superfish.css" />
    <!-- Magnific Popup -->
    <link rel="stylesheet" href="css/magnific-popup.css" />
    <!-- Date Picker -->
    <link rel="stylesheet" href="css/bootstrap-datepicker.min.css" />
    <!-- CS Select -->
    <link rel="stylesheet" href="css/cs-select.css" />
    <link rel="stylesheet" href="css/cs-skin-border.css" />
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="css/sub_layout.css" />
    <link rel="stylesheet" href="css/styleguide.css" />
    <link rel="stylesheet" href="css/community.css" />
    <link rel="stylesheet" href="css/join.css" />
    <link rel="stylesheet" href="css/reset.css" />
    <link rel="stylesheet" href="css/like.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!--  모두의 낚시 -->

    <!-- Modernizr JS -->
    <script src="js/modernizr-2.6.2.min.js"></script>
    <!-- FOR IE9 below -->
    <!--[if lt IE 9]>
      <script src="js/respond.min.js"></script>
    <![endif]-->
    <!-- link end -->
  </head>
  <body>
    <div id="fh5co-wrapper">
      <div id="fh5co-page">
      
	<%-- 헤더임 --%>
	<jsp:include page="header.jsp"/>
	<%-- 헤더라고 --%>
	
        <article class="view_wrap pd60">
          <div class="center clearfix">
    <div class="container mt-3">
        <h2>B_TLTLE</h2>
        <hr>
        <div class="mb-3 mt-3 clearfix">
            <span class="float-start me-2">${bNum }</span>
            <span class="float-end ms-4">${bDate }</span>
            <span class="float-end">${bCnt }</span>
        </div>

        <section>
        <c:forEach var="boardSet" items="${vbvo}">
        <c:set var="board" value="${boardSet.board}" />
            <div class="mb-3">
                <label for="name">작성자:</label>
                <div class="border bg-light rounded p-2" >${board.bId }</div>
            </div>    
            <div class="mb-3 mt-3">
                <label for="subject">제목:</label>
                <div class="border bg-light rounded p-2" >${board.bTitle }</div>
            </div>    
            <div class="mb-3 mt-3">
                <label for="content">내용:</label>
                <div class="border bg-light rounded p-2">${board.bContent }</div>
            </div>
            <a class="btn btn-outline-dark" href="#">수정</a>
            <a class="btn btn-outline-dark" href="#">목록</a>
            <button type="button" class="btn btn-outline-dark">삭제</button>
            <a class="btn btn-outline-dark" href="#">작성</a>
		</c:forEach>
        </section>
            <!-- 댓글시작 -->
            <jsp:include page="comment.jsp"/>
            <!-- 댓글끝 -->
    </div>
          </div>
          <!-- center -->

          <div class="report_popup">
            <div class="tit">
              <span class="line1"></span> <span class="line2"></span>
            </div>
            <div class="con">
              <div class="button_wrap tac">
               <!-- <button type="button" class="delete_btn">닫기</button> 하단 버튼자리 -->
              </div>
            </div>
          </div>
        </article>

	<%-- 푸터임 --%>
	<jsp:include page="footer.jsp"/>
	<%-- 푸터라고 --%>
	
      </div>
    </div>
  </body>
</html>
