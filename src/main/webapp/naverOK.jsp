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
  var naver_id_login = new naver_id_login("dClwkVzJ3MRBP_IElx5I", "http://localhost:8088/ctrl/main.jsp");
  // 접근 토큰 값 출력
  /* alert(naver_id_login.oauthParams.access_token); */
  // 네이버 사용자 프로필 조회
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  function naverSignInCallback() {
    const email=naver_id_login.getProfileData('email');
    const name=naver_id_login.getProfileData('name');
    
    console.log(email);
    console.log(name);
    
    location.href="joinNaver.do?name=" + name + "&email=" + email; //리다이렉트 주소
  }
</script>