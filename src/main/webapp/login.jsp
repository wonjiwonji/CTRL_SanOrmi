<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>로그인 페이지</title>

<!-- 네이버 로그인 js -->
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="./images/favicon.ico" />

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" />
<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="css/board.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet" />

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
<link rel="stylesheet" href="css/reset.css" />
</head>

<body class="bg-gradient-success">

	<%-- 헤더임 --%>
	<jsp:include page="header.jsp" />
	<%-- 헤더라고 --%>

	<div class="container">
		<!-- Outer Row -->
		<div class="row justify-content-center">
			<div class="col-xl-10 col-lg-12 col-md-9">
				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<!-- <div class="col-lg-6 d-none d-lg-block bg-login-image"></div> -->
							<div class="col-lg-6 d-none d-lg-block">
								<img src="./images/m1.jpg"
									style="height: 462px; width: 450px; display: flex" alt="그" />
							</div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">로그인</h1>
									</div>

									<form action="login.do" method="post">
										<div class="form-group">
											<input type="text" class="form-control form-control-user"
												id="exampleInputID" name="id" placeholder="ID" required />
										</div>
										<div class="form-group">
											<input type="password" class="form-control form-control-user"
												id="exampleInputPassword" name="mPw" placeholder="Password"
												required />
										</div>
										<div class="form-group">
											<div class="custom-control custom-checkbox small">
												<input type="checkbox" class="custom-control-input"
													id="customCheck" />
												<!-- <label class="custom-control-label" for="customCheck">Remember Me</label> -->
											</div>
										</div>
										<input type="submit"
											class="btn btn-success btn-user btn-block" value="로그인" />
										<hr />

										<!-- 카카오 로그인 js 연결 및 버튼 생성 -->
										<a href="javascript:kakaoLogin();"> <img
											src="./img/kakao_login_medium_wide.png"
											style="width: 277.5px; height: 60px; margin: auto; margin-bottom: 5px; display: block;" />
										</a>

										<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
										<script>
								// SDK를 초기화 합니다. 사용할 앱 키(javascript) 입력합니다.
                    window.Kakao.init('78892b6e583dfe48cb2d2caf38da1114');
                 // SDK 초기화 여부를 판단합니다. true가 나온다면 정상 작동
                    console.log(Kakao.isInitialized());

                      // 카카오 로그인 함수 생성
                    function kakaoLogin() {
                      window.Kakao.Auth.login({
                        scope: 'profile_nickname, account_email', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
                        success: function (authObj) {
                          console.log(authObj); // 로그인 성공하면 받아오는 데이터
                          window.Kakao.API.request({
                            // 사용자 정보 가져오기
                      		url: '/v2/user/me',
                         	//url: '/v1/user/unlink',
                           // 연결 끊기(회원탈퇴)
                            success: (res) => {
                              const kakao_account = res.kakao_account;
                              console.log(kakao_account);
                            },
                          });
                          //window.location.href='main.do' //리다이렉트 주소
                        },
                        fail: function (error) {
                          console.log(error);	// 실패하면 콘솔에 error 메세지
                        },
                      });
                    }
                  	
                  //토큰 가져오기 (콘솔로 정보 보기)
                    function selectMyAccessTockenWithKakao() {
                    	
                     	var param = {"code" : code}
                    	
                    	 	$.ajax({
                    			url  : '/selectMyAccessTockenWithKakao',
                    	        type : 'get',
                    	        data : param,
                    	        dataType : "JSON", 
                    	        contentType: "application/x-www-form-urlencoded;charset=utf-8", 
                    	        success : function(data){
                    	            
                    	        	console.log(data);
                    	        },
                    	        error: function(xhr, type){ 
                    		        console.log(xhr); 
                    		        console.log(type); 
                    			}
                    	    })
                    	
                      }
                    
                  </script>

										<!-- <ul>
									<li>
										아래와같이 아이디를 꼭 써준다. <a id="naverIdLogin_loginButton"
										href="javascript:void(0)"> <span>네이버 로그인</span>
									</a>
									</li>
									<li onclick="naverLogout(); return false;"><a
										href="javascript:void(0)"> <span>네이버 로그아웃</span>
										
									</a></li>
								</ul> -->

										<!-- 네이버 로그아웃 -->
										<!-- http://nid.naver.com/nidlogin.logout  -->

										<!-- 네이버 로그인 버튼 노출 영역 -->
										<div id="naver_id_login"
											style="display: flex; justify-content: center;"></div>
										<!-- //네이버 로그인 버튼 노출 영역 -->
										<script type="text/javascript">
  									var naver_id_login = new naver_id_login("dClwkVzJ3MRBP_IElx5I", "http://localhost:8080/ctrl/main.jsp");
  									var state = naver_id_login.getUniqState();
  									naver_id_login.setButton("green", 3, 60);
  									naver_id_login.setDomain("http://localhost:8080");
  									naver_id_login.setState(state);
  									//naver_id_login.setPopup();
  									naver_id_login.init_naver_id_login();
  								</script>
									</form>
									<hr />
									<!-- <div class="text-center">
                                            <a class="small" href="forgot-password.html">비밀번호 찾기</a>
                                        </div> -->
									<div class="text-center">
										<a class="small" href="register.do">회원가입</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

	<%-- 푸터임 --%>
	<jsp:include page="footer.jsp" />
	<%-- 푸터라고 --%>

</body>

</html>
