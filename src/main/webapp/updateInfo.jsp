<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
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
<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
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
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700,300" rel="stylesheet" type="text/css" />

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
	<jsp:include page="header.jsp"/>
	<%-- 헤더라고 --%>
	
    <div id="main">
      <div id="fh5co-contact" class="fh5co-section-gray">
        <div class="container">
          <section class="join_section pdt60">
            <h2 class="join_title">회원 정보 수정</h2>
            <p class="join_desc mt">Edit Member Information</p>
            <div class="center400 pdb60">
              <form name="join_form" id="fregister" method="post" autocomplete="off" action="update.html">
                <!-- <form name="fregister" id="fregister" method="post"
                           autocomplete="off"> -->
                <div class="Idbox">
                  <label><span> </span>아이디</label>
                  <div class="box">
                    <input type="text" name="id" value="" id="id" class="nickname" required maxlength="20" placeholder="ID (변경 불가)" oninput="init_chk_nick();" autocomplete="off" readonly />
                  </div>
                </div>
                <div class="passwordbox">
                  <label><span> </span>비밀번호</label>
                  <div class="box box2">
                    <input type="password" name="mpw" id="pwd" placeholder="비밀번호 입력" />
                  </div>
                  <div class="box box3" style="margin-top: 7px">
                    <input type="password" id="repwd" placeholder="비밀번호 확인" maxlength="20" />
                  </div>
                </div>
                <div class="emailbox">
                  <label><span> </span>이름</label>
                  <div class="box box1">
                    <input type="text" name="mName1" id="fname" class="email_front" placeholder="성" readonly />
                    <em class="ctm">&nbsp;</em>
                    <input type="text" name="mName2" id="lname" class="email_front" placeholder="이름" readonly />
                  </div>
                </div>
                <div class="emailbox">
                  <label><span> </span>이메일</label>
                  <div class="box box1">
                    <input type="text" name="mEmail1" id="email_id" class="email_front" placeholder="이메일 앞자리" />
                    <em class="ctm">@</em>
                    <select style="width: 150px; margin-right: auto" name="mEmail2" id="email_add">
                      <option value="1">직접입력</option>
                      <option value="gmail.com">gmail.com</option>
                      <option value="naver.com" selected>naver.com</option>
                    </select>
                    <input type="text" name="mEmail2" id="email_add" style="width: 400px" placeholder="이메일 뒷자리" />
                  </div>
                </div>
                <div class="emailbox">
                  <label><span> </span>주소 (선택 사항)</label>
                  <div class="box box1">
                    <input type="text" class="email_front" id="sample2_postcode" name="mAddress1" placeholder="우편번호" />
                    <em class="ctm">&nbsp;</em>
                    <button type="button" onclick="location.href='error404.html'" class="adress_btn" style="  background: #858796; color: #fff; height: 46px; padding: 0px 15px;"> 우편번호 찾기 </button>
                  </div>
                </div>
                <div class="adressbox">
                  <div class="box box2">
                    <input type="text" id="sample2_address" name="mAddress2" class="form-control form-control-user" placeholder="기본 주소" />
                  </div>
                  <div class="box box3" style="margin-top: 7px">
                    <input type="text" id="sample2_detailAddress" name="mAddress3" class="form-control form-control-user" placeholder="상세 주소" />
                  </div>
                </div>
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
                  <button type="button" onclick="joinform_check();" class="join_btn sub" id="btn_regi_submit"> 회원 정보 수정 </button>
                </div>
              </form>
            </div>
          </section>
        </div>
      </div>
    </div>
    <!-- div id="main" 끝 --> 
    
    <script
          type="text/javascript"
          src="http://code.jquery.com/jquery-latest.min.js"
        ></script> 
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
    
    <!-- Footer -->
    <footer>
      <div id="footer">
        <div class="container">
          <div class="row">
            <div class="col-md-6 col-md-offset-3 text-center">
              <p class="fh5co-social-icons"> <span><i class="icon-twitter2"></i></span> <span><i class="icon-facebook2"></i></span> <span><i class="icon-instagram"></i></span> <span><i class="icon-dribbble2"></i></span> <span><i class="icon-youtube"></i></span> </p>
              <p> Control Co., Ltd. &copy; Website 2023 <i class="icon-heart3"></i> </p>
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!-- End of Footer --> 
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
</html>