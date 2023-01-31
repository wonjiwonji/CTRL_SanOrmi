<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>회원 탈퇴</title>
<link rel="icon" href="images/favicon.ico" />

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

							<c:choose>
								<c:when test="${(fn:contains(sessionScope.mPw, 'KAKAO'))}">
									<button onclick="kakaoLogout(); action(); return false;"
										class="kakao_logout"
										style="width: 277.5px; height: 60px; margin: auto; margin-bottom: 5px; display: block; background-color: #FEE500; text-align: center; font-size: large; font-weight: bold;">
										<a> <img src="./img/kakao.png" /></a> 카카오 회원탈퇴
									</button>
									<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
									<script>
				// SDK를 초기화 합니다. 사용할 앱 키(javascript) 입력합니다.
					window.Kakao.init('78892b6e583dfe48cb2d2caf38da1114');
                 // SDK 초기화 여부를 판단합니다. true가 나온다면 정상 작동
                    console.log(Kakao.isInitialized());

                      // 카카오 로그인 함수 생성
                    function kakaoDelete() {
                     	Kakao.Auth.login({
                        success: function (authObj) {
                          Kakao.API.request({
                            // 사용자 정보 가져오기
                         	url: '/v1/user/unlink',
                           // 연결 끊기(회원탈퇴)
                            success: (response) => {
                              
                          	location.href="deleteAccount.do"; //리다이렉트 주소
                            },
                          });
                        },
                        fail: function (error) {
                          console.log(error);	// 실패하면 콘솔에 error 메세지
                        },
                      });
                    }
                    </script>


								</c:when>


								<c:when test="${(fn:contains(sessionScope.mPw, 'NAVER'))}">

									<div class="center400 pdb60">
										<form name="join_form" id="fregister" method="post"
											autocomplete="off">
											<div class="Idbox">
												<label><span> </span>아이디</label>
												<div class="box">
													<input type="text" name="id" value="${sessionScope.id }"
														id="reg_mb_nick" class="nickname" required maxlength="20"
														placeholder="ID (변경 불가)" oninput="init_chk_nick();"
														autocomplete="off" readonly />
												</div>
											</div>
										</form>

										<div class="btnbox">
											<button type="button" onclick="check(); return false;"
												style="width: 277.5px; height: 60px; margin: auto; margin-bottom: 5px; display: block; background-color: #03C75A; text-align: center; font-size: large; font-weight: bold; color: white;">
											<a> <img src="./img/btnG_아이콘사각.png" style="width: 70px; height: 60px;"/></a> 네이버 회원탈퇴
												</button>

										</div>
									</div>

									<script
										src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js"
										charset="utf-8"></script>

									<script>

var logoutPopUp;
function openPopUp() {
    logoutPopUp= window.open("https://nid.naver.com/nidlogin.logout", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1,height=1");
}
function closePopUp(){
    logoutPopUp.close();
}

function naverLogout() {
	openPopUp();
	setTimeout(function() {
		closePopUp();
		}, 100);
	
	action();
	
}

function check() {
	if (confirm("정말 탈퇴하시겠습니까?") == true) { //확인
		naverLogout();
	} else { //취소
		return false;
	}
}

function action() {
	location.href = "deleteAccount.do?id=${sessionScope.id}"; //리다이렉트 주소
}

</script>

								</c:when>
								<c:otherwise>
									<div class="center400 pdb60">
										<form name="join_form" id="fregister" method="post"
											autocomplete="off" action="deleteAccount.do">
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
								</c:otherwise>

							</c:choose>

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