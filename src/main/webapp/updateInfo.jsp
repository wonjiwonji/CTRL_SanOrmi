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
<script type="text/javascript">
      //joinform_check 함수로 유효성 검사
      function joinform_check() {
        console.log('log');
        //변수에 담아주기
        var pwd = document.getElementById('pwd');
        var repwd = document.getElementById('repwd');

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
        //입력 값 전송
        /* console.log('로그 : 회원 정보 변경 성공!'); */
        alert('회원 정보 변경 성공!');
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

      //우편번호 검색 팝업창(현재 공백문서)
      function search_address() {
        window.open('', 'b', 'width=600, height=300, left=200, top=100');
      }
    </script>

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
							<h2 class="join_title">회원 정보 수정</h2>
							<p class="join_desc mt">Edit Member Information</p>
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
									<div class="passwordbox">
										<label><span> </span>비밀번호</label>
										<div class="box box2">
											<input type="password" name="mPw" id="pwd"
												placeholder="비밀번호 입력" />
										</div>
										<div class="box box3" style="margin-top: 7px">
											<input type="password" id="repwd" placeholder="비밀번호 확인"
												maxlength="20" />
										</div>
									</div>
									<div class="emailbox">
										<label><span> </span>이름</label>
										<div class="box box1">
											<input type="text" name="mName1" id="fname"
												class="email_front" placeholder="성" readonly /> <em
												class="ctm">&nbsp;</em> <input type="text" name="mName2"
												id="lname" class="email_front" placeholder="이름" readonly />
										</div>
									</div>
									<div class="emailbox">
										<label><span> </span>이메일</label>
										<div class="box box1">
											<input type="text" name="mEmail1" id="postal_code"
												class="email_front" placeholder="이메일 앞자리" /> <em
												class="ctm">@</em> <select
												style="width: 150px; margin-right: auto" name="mEmail2"
												id="email_add">
												<option value="1">직접입력</option>
												<option value="gmail.com">gmail.com</option>
												<option value="naver.com" selected>naver.com</option>
											</select> <input type="text" name="mEmail2" id="str_email02"
												style="width: 400px" placeholder="이메일 뒷자리" />
										</div>
									</div>
									<div class="emailbox">
										<label><span> </span>주소 (선택 사항)</label>
										<div class="box box1">
											<input type="text" class="email_front" id="sample2_postcode"
												name="mAddress1" placeholder="우편번호" /> <em class="ctm">&nbsp;</em>
											<button type="button" onclick="openAddressAPI()"
												class="adress_btn" value="우편번호 찾기"
												style="background: #858796; color: #fff; height: 46px; padding: 0px 15px;">
												우편번호 찾기</button>
										</div>
									</div>
									<div class="adressbox">
										<div class="box box2">
											<input type="text" id="sample2_address" name="mAddress2"
												class="form-control form-control-user" placeholder="기본 주소" />
										</div>
										<div class="box box3" style="margin-top: 7px">
											<input type="text" id="sample2_detailAddress"
												name="mAddress3" class="form-control form-control-user"
												placeholder="상세 주소" />
										</div>
									</div>
									<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
									<div id="layer"
										style="display: none; position: fixed; overflow: hidden; z-index: 9999999; -webkit-overflow-scrolling: touch;">
										<img src="//t1.daumcdn.net/postcode/resource/images/close.png"
											id="btnCloseLayer"
											style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1;"
											onclick="closeDaumPostcode()" alt="닫기 버튼" />
									</div>

									<!-- 주소 끝 -->
									<!--
                <div class="receivebox">
                  <label><span> </span>메일수신</label>
                  <div>
                    <input type="checkbox" name="mb_mailling" id="mb_mailling1" value="1" />
                    <i></i>
                    <label for="mb_mailling1" class="txc1">수신동의</label>
                    <small class="ctm">※ 수신 설정시 다양한 등산 정보를 받아 보실 수 있습니다.</small> </div>
                </div>
                -->
									<div class="btnbox">
										<button type="button" onclick="joinform_check();"
											class="join_btn sub" id="btn_regi_submit">회원 정보 수정</button>
									</div>
								</form>
							</div>
						</section>
					</div>
				</div>
			</div>
			<!-- div id="main" 끝 -->

			<script type="text/javascript"
				src="http://code.jquery.com/jquery-latest.min.js"></script>
			<script type="text/javascript">
          //이메일 입력방식 선택
          $('#selectEmail').change(function () {
            $('#selectEmail option:selected').each(function () {
              if ($(this).val() == '1') {
                //직접입력일 경우
                $('#str_email02').val(''); //값 초기화
                $('#str_email02').attr('disabled', false); //활성화
              } else {
                //직접입력이 아닐경우
                $('#str_email02').val($(this).text()); //선택값 입력
                $('#str_email02').attr('disabled', true); //비활성화
              }
            });
          });
        </script>

			<%-- 푸터임 --%>
			<jsp:include page="footer.jsp" />
			<%-- 푸터라고 --%>
		</div>
		<!-- END fh5co-page -->
	</div>
	<!-- END fh5co-wrapper -->

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

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
</body>
</html>