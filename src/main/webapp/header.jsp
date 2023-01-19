<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<% request.setCharacterEncoding("UTF-8"); %>    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>NavBar</title>
    <link rel="shortcut icon" href="./images/favicon.ico" />

    <!-- link start -->
    <!-- Custom fonts for this template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />

    <!-- Custom styles for this template -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet" />

    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/board.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="favicon.ico" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700,300" rel="stylesheet" type="text/css" />

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
    <script src="js/modernizr-2.6.2.min.js"></script>
</head>
<body>
    <header id="fh5co-header-section" class="sticky-banner">
      <div class="container">
          <!-- START #fh5co-menu-wrap -->
        <div class="nav-header"> <a class="js-fh5co-nav-toggle fh5co-nav-toggle dark"><i></i></a> <a id="fh5co-logo" class="cover-color" href="main.do"> <img class="mg-logo" src="./images/logo1.png" alt="ren" /> 산오르미 </a> 
          <!-- START #fh5co-menu-wrap -->
          <nav id="fh5co-menu-wrap" role="navigation">
            <ul class="sf-menu" id="fh5co-primary-menu">
              <li><a href="adminPage.do">관리자 페이지</a></li>
              <li><a href="myeongsan.do">명산 소개</a></li>
              <li><a href="qna.do">자주 묻는 질문</a></li>
              <li><a href="freeBoards.do">커뮤니티</a></li>
              <li> <a class="fh5co-sub-ddown sf-with-ul">로그인</a>
                <ul class="fh5co-sub-menu">
                  <li><a href="loginPage.do">로그인</a></li>
                  <li><a href="agree.do">회원가입</a></li>
                </ul>
              </li>
              <li> <a class="fh5co-sub-ddown sf-with-ul">마이페이지</a>
                <ul class="fh5co-sub-menu">
                  <li><a href="myBoard.do">작성글 확인</a></li>
                  <li><a href="updateInfo.do">회원정보 수정</a></li>
                </ul>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </header>
</body>
</html>