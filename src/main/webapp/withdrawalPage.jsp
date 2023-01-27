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
<title>회원 정보 수정</title>

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="./images/favicon.ico" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
<meta name="keywords"
	content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
<meta name="author" content="FREEHTML5.CO" />
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />
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

<!-- CS Select -->
<link rel="stylesheet" href="css/cs-select.css" />
<link rel="stylesheet" href="css/cs-skin-border.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/update.css" />

<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
      <script src="js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div id="fh5co-wrapper">
		<div id="fh5co-page">

			<%-- 헤더임 --%>
			<jsp:include page="header.jsp" />
			<%-- 헤더라고 --%>

			<div id="main">
				<div id="fh5co-contact" class="fh5co-section-gray">
					<div class="container">
						<section class="join_section pdt60">
							<h2 class="join_title">회원 탈퇴</h2>
							<p class="join_desc mt">Withdrawal</p>
							<div class="center400 pdb60">
								<form name="join_form" id="fregister" method="post"
									autocomplete="off" action="updateMember.do">
									<!-- <form name="fregister" id="fregister" method="post"
                           autocomplete="off"> -->
									<div class="Idbox">
										<label><span> </span>아이디</label>
										<div class="box">
											<input type="text" name="id" value="${sessionScope.id }"
												id="reg_mb_nick" class="nickname" required maxlength="20"
												placeholder="ID (변경 불가)" oninput="init_chk_nick();"
												autocomplete="off" readonly />
										</div>
									</div>

									<div class="btnbox">
										<button type="button" onclick="removeCheck();"
											class="join_btn sub" id="btn_regi_submit">회원탈퇴</button>

										<script>
											function removeCheck() {
												if (confirm("정말 탈퇴하시겠습니까?") == true) { //확인
													location.href = "deleteAccount.do?id=${sessionScope.id}";
												} else { //취소
													return false;
												}
											}
										</script>

									</div>
								</form>
							</div>
						</section>
					</div>
				</div>
			</div>
			<!-- div id="main" 끝 -->

			<%-- 푸터임 --%>
			<jsp:include page="footer.jsp" />
			<%-- 푸터라고 --%>
		</div>
		<!-- END fh5co-page -->
	</div>
	<!-- END fh5co-wrapper -->


	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
</body>