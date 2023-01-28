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
</head>
<body>

	<c:choose>
	<c:when test="${(fn:contains(sessionScope.mPw, 'KAKAO'))}">
		<a href="javascript:kakaoLogout();"> <img src="./img/kakao_login_large_narrow5.png" style="width: 277.5px; height: 60px; margin: auto; margin-bottom: 5px; display: block;" alt="카카오 로고"/> </a> 
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
			<p>네이버</p>
			<!-- 	if(pw.includes('NAVER')){
		$(document).ready(function(){
			window.open= "https://nid.naver.com/nidlogin.logout";
			window.close;
			})
		alert('네이버 회원 탈퇴');
		location.href = "main.do";
		} -->
		</c:when>
		<c:otherwise>

		</c:otherwise>

	</c:choose>

</body>
</html>