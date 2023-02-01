
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>게시글 작성</title>
<link rel="shortcut icon" href="./images/favicon.ico" />

<!-- link start -->
<!-- summernote s -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<!-- summernote e -->
<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet" />

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="css/board.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="favicon.ico" />
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,700,300"
	rel="stylesheet" type="text/css" />

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
<!--  모두의 낚시 -->

<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
      <script src="js/respond.min.js"></script>
    <![endif]-->
<!-- link end -->
</head>

<script>
  $(document).ready(function(){
	  $('#content').summernote();
  });
  </script>

<body>

	<%-- 헤더시작 --%>
	<jsp:include page="header.jsp" />
	<%-- 헤더끝 --%>

	<section class="write_content pd60">
		<div class="center">
			<h2>작성</h2>
			<hr />
			<form action="insertQNA.do" style="display: inline">
				<div>
					<label for="name">작성자:</label>
					<!-- <span class= "form-control" name="bId">${sessionScope.id }</span> -->
					<input type="text" value="${sessionScope.id }" class="form-control"
						id="name" name="qId" readonly required />
				</div>
				<div class="mb-3 mt-3">
					<label for="subject">제목:</label> <input type="text"
						class="form-control" id="subject" placeholder="제목을 입력하세요"
						name="qTitle" required />
				</div>
				<div class="mb-3 mt-3">
					<label for="content">내용:</label>
					<textarea class="form-control" rows="5" id="content"
						placeholder="내용을 입력하세요" name="qContent" required></textarea>
				</div>
				<button type="submit" class="btncolor">작성완료</button>
			</form>
			<button class="btncolor2" onclick="location.href='qna.do'">목록</button>
		</div>
	</section>

	<!-- center -->
</body>

<%-- 푸터시작 --%>
<jsp:include page="footer.jsp" />
<%-- 푸터끝 --%>

</html>