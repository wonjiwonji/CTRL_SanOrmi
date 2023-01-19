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

<title>회원 가입 페이지</title>

<!-- 중복 확인 -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function check(){
		console.log('로그 1 : check()라는 JS 함수가 연결되었음');
		var id=$('#id').val(); // $('id 속성이 id인 요소')의 값을 불러올래!
		$.ajax({
			type: 'POST',
			url: 'check',
			data: {id:id},
			success: function(result){
				console.log('로그 2 : 응답받은 데이터( == response.getWriter() == out ) 출력');
				console.log(result);
				if(result==1){
					$('#checkmsg').html('사용가능'); // $('id 속성이 checkmsg인 요소')에 텍스트 추가
					$('#button_joinus').removeAttr("disabled");
				}	
				else{
					$('#checkmsg').html('사용불가능');
					$('#button_joinus').setAttribute("disabled");
				}
			}
		})
	}
</script>
<!-- 중복 확인 끝 -->
<!-- 네이버 로그인 API -->
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<script type="text/javascript">
      //joinform_check 함수로 유효성 검사
      function joinform_check() {
        //console.log("log");
        //변수에 담아주기
        var id = document.getElementById('id');
        var pwd = document.getElementById('pwd');
        var repwd = document.getElementById('repwd');
        var fname = document.getElementById('fname');
        var lname = document.getElementById('lname');
        var email_id = document.getElementById('email_id');
        /* var agree = document.getElementById("agree"); */

        if (id.value == '') {
          //해당 입력값이 없을 경우 같은말: if(!id.value)
          alert('아이디를 입력하세요.');
          id.focus(); //focus(): 커서가 깜빡이는 현상, blur(): 커서가 사라지는 현상
          return false; //return: 반환하다 return false:  아무것도 반환하지 말아라 아래 코드부터 아무것도 진행하지 말것
        }
        var idCheck = /^(?=.*[a-zA-Z])[-a-zA-Z0-9_.]{2,10}$/;

        if (!idCheck.test(id.value)) {
          alert(
            '아이디는 2-10자의 영문과 숫자와 일부 특수문자(._-)만 사용해야 합니다.',
          );
          id.focus();
          return false;
        }
        if (pwd.value == '') {
          alert('비밀번호를 입력하세요.');
          pwd.focus();
          return false;
        }
        //비밀번호 영문자+숫자+특수조합(8~25자리 입력) 정규식
        var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;

        if (!pwdCheck.test(pwd.value)) {
          alert(
            '비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.',
          );
          pwd.focus();
          return false;
        }
        if (repwd.value !== pwd.value) {
          alert('비밀번호가 일치하지 않습니다..');
          repwd.focus();
          return false;
        }
        if (lname.value == '') {
          alert('이름을 입력하세요.');
          lname.focus();
          return false;
        }
        var reg = /^[0-9]+/g; //숫자만 입력하는 정규식

        if (email_id.value == '') {
          alert('이메일 앞자리를 입력하세요.');
          email_id.focus();
          return false;
        }
        
        if (email_add.value == '') {
            alert('이메일 뒷자리를 입력하세요.');
            email_add.focus();
            return false;
          }
        <!-- 로직추가해야함....
        if (checkmsg.value == '') {
        	alert('아이디 중복확인을 해주세요.');
            id.focus();
            return false;
        } 
        if (checkmsg.value == '사용불가능') {
            alert('사용가능한 아이디를 입력해주세요.');
            id.focus();
            return false;
          }
        -->
        if (checkmsg.value == '') {
        	
        }

        //입력 값 전송
        document.join_form.submit(); //유효성 검사의 포인트
      }

      //이메일 옵션 선택후 주소 자동 완성
      function change_email() {
        var email_add = document.getElementById('email_add');
        var email_sel = document.getElementById('email_sel');

        //지금 골라진 옵션의 순서와 값 구하기
        var idx = email_sel.options.selectedIndex;
        var val = email_sel.options[idx].value;

        email_add.value = val;
      }
    </script>

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
	<jsp:include page="header.jsp"/>
	<%-- 헤더라고 --%>

	<div class="container">
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
<% //TODO %>
				<form class="user" name="join_form" action="loginPage.do"
					method="post">
					<div class="row">
						<!-- <div class="col-lg-5 d-none d-lg-block bg-register-image"></div> -->
						<div class="col-lg-5 d-none d-lg-block">
							<img src="./images/m2.jpg"
								style="height: 100%; width: 420px; display: flex" alt="학그림" />
						</div>
						<div class="col-lg-7">
							<div class="p-5">
								<div class="text-center">
									<h1 class="h4 text-gray-900 mb-4">회원 가입</h1>
								</div>
								<!-- <form class="user"> -->
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="text" class="form-control form-control-user"
											placeholder="아이디" name="id" id="id" />
									</div>
									<div class="col-sm-6">
										<button type="button"
											class="jw jw-btn-success btn-user btn-block"
											onclick="check()">
											<!-- onclick="id_check();" 중복확인 아직 구현하지 않아 404페이지로 돌림 -->
											중복 확인
										</button>
									</div>
									<div><p id="checkmsg"></p></div>
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="password" class="form-control form-control-user"
											name="pwd" id="pwd" placeholder="비밀번호" />
										<!-- <input type="password" class="form-control form-control-user"
                                 id="exampleInputPassword" placeholder="Password" required /> -->
									</div>
									<div class="col-sm-6">
										<input type="password" class="form-control form-control-user"
											name="repwd" id="repwd" placeholder="비밀번호 재확인" />
										<!-- <input type="password"
                                 id="exampleRepeatPassword" placeholder="Repeat Password"
                                 required /> -->
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="text" name="fname" id="fname"
											class="form-control form-control-user" placeholder="성" />
										<!-- <input type="text" 
                                 id="exampleFirstName" placeholder="FirstName" required /> -->
									</div>
									<div class="col-sm-6">
										<input type="text" name="lname" id="lname"
											class="form-control form-control-user" placeholder="이름" />
										<!-- <input type="text" class="form-control form-control-user"
                                 id="exampleLastName" placeholder="LastName" required /> -->
									</div>
								</div>
								<!-- </form> -->
								<!-- 여기임! 뭐냐면,,,,,,,,,, 주소! -->

								<!-- <form class="user"> -->
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="text" class="form-control form-control-user"
											id="sample2_postcode" name="zipNo" placeholder="우편번호" />
									</div>
									<div class="col-sm-6">
										<!-- onclick="search_address();" 우편번호 찾기 시 이동할 페이지, 현재 api 구현안돼 404로 돌림 -->
										<button type="button" onclick="openAddressAPI()"
											class="jw jw-btn-success btn-user btn-block" value="우편번호 찾기">
											우편번호 찾기</button>

										<!-- 	<input type="button" onclick="sample2_execDaumPostcode()"
											value="우편번호 찾기"><br> -->

										<!-- <input type="submit"
                                 class="jw jw-btn-success btn-user btn-block" value="우편번호 찾기" /> -->
									</div>
								</div>
								<!-- </form> -->
								<!-- <form class="user" method="post"> -->
								<div class="form-group">
									<input type="text" id="sample2_address"
										class="form-control form-control-user" placeholder="기본 주소" />
									<!-- <input type="text" name="postal_code" id="postal_code"
                         class="form-control form-control-user" placeholder="주소"> -->
									<!-- <input type="text" class="form-control form-control-user"
                              id="exampleInputAdress" placeholder="주소" required /> -->
								</div>
								<div class="form-group">
									<input type="text" id="sample2_detailAddress"
										class="form-control form-control-user" placeholder="상세 주소" />

									<!-- <input type="text" class="form-control form-control-user"
                              id="exampleInputDAdress" placeholder="상세주소" required /> -->
								</div>

								<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
								<div id="layer"
									style="display: none; position: fixed; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
									<img src="//t1.daumcdn.net/postcode/resource/images/close.png"
										id="btnCloseLayer"
										style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1;"
										onclick="closeDaumPostcode()" alt="닫기 버튼" />
								</div>

								<!-- 주소 끝 -->

								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="text" name="email_id" id="email_id"
											class="form-control form-control-user" placeholder="이메일 앞자리" />
									</div>
									<div class="col-sm-6">
										<input type="text" name="email_add" id="email_add"
											class="form-control form-control-user"
											placeholder="@ 이메일 뒷자리" />
									</div>
								</div>
								<div class="form-group">
									<select name="email_sel" id="email_sel"
										onchange="change_email();"
										class="jw jw-btn-success btn-user btn-block">
										<!-- <button type="button"
                                 class="jw jw-btn-success btn-user btn-block"
                                 onclick="id_check();">중복 확인</button> -->

										<option value="">직접입력</option>
										<option value="naver.com">네이버</option>
										<option value="gmail.com">지메일</option>
										<option value="nate.com">네이트</option>
									</select> <br />
								</div>
								<div class="join_btn">
									<button type="button" id="button_joinus" onclick="joinform_check();"
										class="btn btn-success btn-user btn-block" disabled="">
										회원 가입</button>
								</div>
								<hr />
								<!--<a href="javascript:kakaoLogin();"> <img
									src="./img/kakao_login_medium_wide.png"
									style="width: 277.5px; height: 60px; margin: auto; margin-bottom: 5px; display: block;" />
								</a> -->
<!--
								<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
								<script>
                    window.Kakao.init('78892b6e583dfe48cb2d2caf38da1114');

                    function kakaoLogin() {
                      // 카카오 로그인 함수 생성
                      window.Kakao.Auth.login({
                        scope: 'profile_nickname, account_email', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
                        success: function (authObj) {
                          console.log(authObj); // 로그인 성공하면 받아오는 데이터
                          window.Kakao.API.request({
                            // 사용자 정보 가져오기
                      		url: '/v2/user/me',
                         	//url: '/v1/user/unlink',
                           // 연결 끊기
                            success: (res) => {
                              const kakao_account = res.kakao_account;
                              console.log(kakao_account);
                            },
                          });
                          //window.location.href='main.jsp' //리다이렉트 되는 코드
                          // 액션으로 변경해야할 수도 있음
                        },
                        fail: function (error) {
                          console.log(error);
                        },
                      });
                    }
                  </script>
                   -->
								
								<!-- 네이버 로그아웃 -->
								<!-- 로그인 다시 시도하고 싶을 때 밑의 주소 복붙해서 로그아웃하면 됨 -->
								<!-- http://nid.naver.com/nidlogin.logout -->
								
								<!-- 네이버 로그인 버튼 노출 영역 -->
								<!--<div id="naver_id_login" style="display: flex; justify-content: center;"></div> -->
								<!-- //네이버 로그인 버튼 노출 영역 -->
								<!-- 
								<script type="text/javascript">
  									var naver_id_login = new naver_id_login("dClwkVzJ3MRBP_IElx5I", "http://localhost:8080/ctrl/main.jsp");
  									var state = naver_id_login.getUniqState();
  									naver_id_login.setButton("green", 3, 60);
  									naver_id_login.setDomain("http://localhost:8080");
  									naver_id_login.setState(state);
  									/* naver_id_login.setPopup(); */
  									naver_id_login.init_naver_id_login();
  								</script>
  								-->
								<!-- </form> -->
								<hr />
								<!-- <div class="text-center">
                           <a class="small" href="forgot-password.html">비밀번호 찾기</a>
                        </div> -->
								<div class="text-center">
									<a class="small" href="loginPage.do">이미 회원이신가요? 로그인으로 이동!</a>
								</div>
							</div>
						</div>
					</div>
				</form>
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
	<jsp:include page="footer.jsp"/>
	<%-- 푸터라고 --%>

	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	<script>
      //카카오api

      // 우편번호 찾기 화면을 넣을 element
      var element_layer = document.getElementById('layer');

      function closeDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_layer.style.display = 'none';
      }

      function openAddressAPI() {
        new daum.Postcode({
          oncomplete: function (data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') {
              // 사용자가 도로명 주소를 선택했을 경우
              addr = data.roadAddress;
            } else {
              // 사용자가 지번 주소를 선택했을 경우(J)
              addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === 'R') {
              // 법정동명이 있을 경우 추가한다. (법정리는 제외)
              // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
              if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                extraAddr += data.bname;
              }
              // 건물명이 있고, 공동주택일 경우 추가한다.
              if (data.buildingName !== '' && data.apartment === 'Y') {
                extraAddr +=
                  extraAddr !== ''
                    ? ', ' + data.buildingName
                    : data.buildingName;
              }
              /*  // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample2_extraAddress").value = extraAddr; */
            } else {
              document.getElementById('sample2_extraAddress').value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample2_postcode').value = data.zonecode;
            document.getElementById('sample2_address').value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('sample2_detailAddress').focus();

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            element_layer.style.display = 'none';
          },
          width: '100%',
          height: '100%',
          maxSuggestItems: 5,
        }).embed(element_layer);

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        initLayerPosition();
      }

      // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
      // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
      // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
      function initLayerPosition() {
        var width = 500; //우편번호서비스가 들어갈 element의 width
        var height = 600; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 1; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left =
          ((window.innerWidth || document.documentElement.clientWidth) -
            width) /
            2 -
          borderWidth +
          'px';
        element_layer.style.top =
          ((window.innerHeight || document.documentElement.clientHeight) -
            height) /
            2 -
          borderWidth +
          'px';
      }
    </script>
</body>
</html>
