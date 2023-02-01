<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
<link rel="icon" href="images/favicon.ico" />
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
							<h2 class="join_title"
								style="font-size: 27px; font-weight: 700; margin-bottom: 10px; text-align: center;">로그아웃</h2>
							<p class="join_desc mt"
								style="font-size: 12px; font-weight: 700; margin-bottom: 20px; text-align: center;">Logout</p>

							<c:choose>
								<c:when test="${(fn:contains(sessionScope.mPw, 'KAKAO'))}">
									<button onclick="kakaoLogout(); action(); return false;"
										class="kakao_logout"
										style="width: 277.5px; height: 60px; margin: auto; margin-bottom: 5px; display: block; background-color: #FEE500; text-align: center; font-size: large; font-weight: bold;">
										<a> <img src="./img/kakao.png" /></a> 카카오 로그아웃
									</button>
									<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
									<script>
				// SDK를 초기화 합니다. 사용할 앱 키(javascript) 입력합니다.
					window.Kakao.init('78892b6e583dfe48cb2d2caf38da1114');
                 // SDK 초기화 여부를 판단합니다. true가 나온다면 정상 작동
                    console.log(Kakao.isInitialized());

                      // 카카오 로그인 함수 생성
                    function kakaoLogout() {
                     	Kakao.Auth.login({
                        success: function (authObj) {
                          Kakao.API.request({
                            // 사용자 정보 가져오기
                         	url: '/v1/user/logout',
                           // 연결 끊기(회원탈퇴)
                            success: (response) => {
                          	location.href="logoutSNS.do"; //리다이렉트 주소
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
												<label
													style="display: block; font-size: 14px; font-weight: 700; margin-bottom: 10px;">
												<span>아이디</span></label>
												<div class="box">
													<input type="text" name="id" value="${sessionScope.id }"
														id="reg_mb_nick" class="nickname"
														style="margin-bottom:20px; display: block; width: 100%; height: 50px; box-sizing: border-box; padding: 0 15px; border: 1px solid #dadde0; border-radius: 4px; box-shadow: 2px 2px 5px rgb(0 0 0/ 10%); font-size: 14px; font-weight: 500; transition: border-color 0.3s;"
														required maxlength="20" placeholder="ID (변경 불가)"
														oninput="init_chk_nick();" autocomplete="off" readonly />
												</div>
											</div>
										</form>

										<div class="btnbox">
											<button type="button" onclick="check(); return false;"
												style="width: 277.5px; height: 60px; margin: auto; margin-bottom: 5px; display: block; background-color: #03C75A; text-align: center; font-size: large; font-weight: bold; color: white;">
											<a> <img src="./img/btnG_아이콘사각.png" style="width: 70px; height: 60px;"/></a> 네이버 로그아웃
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
	if (confirm("로그아웃 하시겠습니까?") == true) { //확인
		naverLogout();
	} else { //취소
		return false;
	}
}
function action() {
	location.href="logoutSNS.do"; //리다이렉트 주소
}

</script>
								</c:when>

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

</body>
</html>