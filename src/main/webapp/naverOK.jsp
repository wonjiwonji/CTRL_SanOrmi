<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 네이버 로그인 js -->
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>


<!-- 네이버 로그인 API -->
<script type="text/javascript">
	var naver_id_login = new naver_id_login("dClwkVzJ3MRBP_IElx5I",
			"http://localhost:8088/ctrl/main.jsp");
	// 접근 토큰 값 출력
	/* alert(naver_id_login.oauthParams.access_token); */
	// 네이버 사용자 프로필 조회
	naver_id_login.get_naver_userprofile("naverSignInCallback()");
	// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	function naverSignInCallback() {
		const email = naver_id_login.getProfileData('email');
		const name = naver_id_login.getProfileData('name');

		location.href = "joinNaver.do?name=" + name + "&email=" + email; //리다이렉트 주소
	}
</script>

<script type="text/javascript">
	var clientID = 'dClwkVzJ3MRBP_IElx5I'; // 네아로 클라이언트 아이디
	var callbackURL = 'http://localhost:8088/ctrl/naverOK.jsp'; // 네아로에서 설정한 CallBack URL 
	var siteURL = 'http://localhost:8088'; // 네아로에서 설정한 사이트 URL 
	var resKey = {
		'id' : '동일인 식별 정보',
		'nickname' : '사용자 별명',
		'name' : '사용자 이름',
		'email' : '사용자 메일 주소',
		'gender' : '성별(F: 여성,M: 남성, U: 확인불가 )',
		'age' : '사용자 연령대',
		'birthday' : '사용자 생일(MM-DD 형식)',
		'profile_image' : '사용자 프로필 사진 URL',
		'birthyear' : '출생연도',
		'mobile' : '휴대전화번호',
	}

	var naver_id_login = new naver_id_login(clientID, callbackURL);

	try {
		$('.result', opener.document).append(
				'<tr><th>접근권한 인증코드(access_token)</th><td>'
						+ naver_id_login.oauthParams.access_token
						+ '</td></tr>');

		// 네이버 사용자 프로필 조회
		naver_id_login.get_naver_userprofile("naverSignInCallback()");

		// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	} catch (e) {
		alert('네이버 로그인 인증에 실패하였습니다.');
		window.self.close();
	}

	function naverSignInCallback() {
		$.each(resKey, function(key, des) {
			var value = naver_id_login.getProfileData(key);

			const email = naver_id_login.getProfileData('email');
			const name = naver_id_login.getProfileData('name');

			location.href = "joinNaver.do?name=" + name + "&email=" + email; //리다이렉트 주소
		});

		window.self.close();
	}
</script>