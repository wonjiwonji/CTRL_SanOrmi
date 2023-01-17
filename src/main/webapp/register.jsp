<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>회원 가입 페이지</title>

    <script type="text/javascript">
      //joinform_check 함수로 유효성 검사
      function joinform_check() {
        //console.log("log");
        //변수에 담아주기
        var uid = document.getElementById('uid');
        var pwd = document.getElementById('pwd');
        var repwd = document.getElementById('repwd');
        var fname = document.getElementById('fname');
        var lname = document.getElementById('lname');
        var email_id = document.getElementById('email_id');
        /* var agree = document.getElementById("agree"); */

        if (uid.value == '') {
          //해당 입력값이 없을 경우 같은말: if(!uid.value)
          alert('아이디를 입력하세요.');
          uid.focus(); //focus(): 커서가 깜빡이는 현상, blur(): 커서가 사라지는 현상
          return false; //return: 반환하다 return false:  아무것도 반환하지 말아라 아래 코드부터 아무것도 진행하지 말것
        }
        var idCheck = /^(?=.*[a-zA-Z])[-a-zA-Z0-9_.]{2,10}$/;

        if (!idCheck.test(uid.value)) {
          alert(
            '아이디는 2-10자의 영문과 숫자와 일부 특수문자(._-)만 사용해야 합니다.',
          );
          uid.focus();
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
          alert('이메일 주소를 입력하세요.');
          email_id.focus();
          return false;
        }

        /* if (!agree.checked) { //체크박스 미체크시
         alert("약관 동의를 체크하세요.");
         agree.focus();
         return false;
      } */

        //입력 값 전송
        document.join_form.submit(); //유효성 검사의 포인트
      }

      //아이디 중복체크 팝업창(현재 공백문서)
      function id_check() {
        //window.open("팝업될 문서 경로", "팝업될 문서 이름", "옵션");
        window.open('', '', 'width=600, height=200, left=200, top=100');
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
    <link
      href="vendor/fontawesome-free/css/all.min.css"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
      rel="stylesheet"
    />
    <!-- Custom styles for this page -->
    <link
      href="vendor/datatables/dataTables.bootstrap4.min.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="css/board.css" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />

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
    <header id="fh5co-header-section" class="sticky-banner">
      <div class="container">
        <div class="nav-header">
          <a class="js-fh5co-nav-toggle fh5co-nav-toggle dark"><i></i></a>
          <a id="fh5co-logo" class="cover-color" href="main.html">
            <img class="mg-logo" src="./images/logo1.png" alt="ren" /> 산오르미
          </a>
          <!-- START #fh5co-menu-wrap -->
          <nav id="fh5co-menu-wrap" role="navigation">
            <ul class="sf-menu" id="fh5co-primary-menu">
              <li><a href="adminPage.html">관리자 페이지</a></li>
              <li><a href="contact.html">명산 소개</a></li>
              <li><a href="QnA.html">자주 묻는 질문</a></li>
              <li><a href="tablespage.html">커뮤니티</a></li>
              <li>
                <a class="fh5co-sub-ddown sf-with-ul">로그인</a>
                <ul class="fh5co-sub-menu">
                  <li><a href="login.html">로그인</a></li>
                  <li><a href="agree.html">회원가입</a></li>
                </ul>
              </li>
              <li>
                <a class="fh5co-sub-ddown sf-with-ul">마이페이지</a>
                <ul class="fh5co-sub-menu">
                  <li><a href="mypage.html">작성글 확인</a></li>
                  <li><a href="update.html">회원정보 수정</a></li>
                </ul>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </header>

    <div class="container">
      <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
          <!-- Nested Row within Card Body -->

          <form class="user" name="join_form" action="login.html" method="post">
            <div class="row">
              <!-- <div class="col-lg-5 d-none d-lg-block bg-register-image"></div> -->
              <div class="col-lg-5 d-none d-lg-block">
                <img
                  src="./images/m2.jpg"
                  style="height: 100%; width: 420px; display: flex"
                  alt="학그림"
                />
              </div>
              <div class="col-lg-7">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">회원 가입</h1>
                  </div>
                  <!-- <form class="user"> -->
                  <div class="form-group row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                      <input
                        type="text"
                        class="form-control form-control-user"
                        placeholder="아이디"
                        name="udi"
                        id="uid"
                      />
                    </div>
                    <div class="col-sm-6">
                      <button
                        type="button"
                        class="jw jw-btn-success btn-user btn-block"
                        onclick="location.href='error404.html'"
                      >
                        <!-- onclick="id_check();" 중복확인 아직 구현하지 않아 404페이지로 돌림 -->
                        중복 확인
                      </button>
                    </div>
                  </div>
                  <div class="form-group row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                      <input
                        type="password"
                        class="form-control form-control-user"
                        name="pwd"
                        id="pwd"
                        placeholder="비밀번호"
                      />
                      <!-- <input type="password" class="form-control form-control-user"
                                 id="exampleInputPassword" placeholder="Password" required /> -->
                    </div>
                    <div class="col-sm-6">
                      <input
                        type="password"
                        class="form-control form-control-user"
                        name="repwd"
                        id="repwd"
                        placeholder="비밀번호 재확인"
                      />
                      <!-- <input type="password"
                                 id="exampleRepeatPassword" placeholder="Repeat Password"
                                 required /> -->
                    </div>
                  </div>
                  <div class="form-group row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                      <input
                        type="text"
                        name="fname"
                        id="fname"
                        class="form-control form-control-user"
                        placeholder="성"
                      />
                      <!-- <input type="text" 
                                 id="exampleFirstName" placeholder="FirstName" required /> -->
                    </div>
                    <div class="col-sm-6">
                      <input
                        type="text"
                        name="lname"
                        id="lname"
                        class="form-control form-control-user"
                        placeholder="이름"
                      />
                      <!-- <input type="text" class="form-control form-control-user"
                                 id="exampleLastName" placeholder="LastName" required /> -->
                    </div>
                  </div>
                  <!-- </form> -->
                  <!-- 여기임! 뭐냐면,,,,,,,,,, 주소! -->

                  <!-- <form class="user"> -->
                  <div class="form-group row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                      <input
                        type="text"
                        class="form-control form-control-user"
                        id="sample2_postcode"
                        name="zipNo"
                        placeholder="우편번호"
                      />
                    </div>
                    <div class="col-sm-6">
                      <!-- onclick="search_address();" 우편번호 찾기 시 이동할 페이지, 현재 api 구현안돼 404로 돌림 -->
                      <button
                        type="button"
                        onclick="openAddressAPI()"
                        class="jw jw-btn-success btn-user btn-block"
                        value="우편번호 찾기"
                      >
                        우편번호 찾기
                      </button>

                      <!-- 	<input type="button" onclick="sample2_execDaumPostcode()"
											value="우편번호 찾기"><br> -->

                      <!-- <input type="submit"
                                 class="jw jw-btn-success btn-user btn-block" value="우편번호 찾기" /> -->
                    </div>
                  </div>
                  <!-- </form> -->
                  <!-- <form class="user" method="post"> -->
                  <div class="form-group">
                    <input
                      type="text"
                      id="sample2_address"
                      class="form-control form-control-user"
                      placeholder="기본 주소"
                    />
                    <!-- <input type="text" name="postal_code" id="postal_code"
                         class="form-control form-control-user" placeholder="주소"> -->
                    <!-- <input type="text" class="form-control form-control-user"
                              id="exampleInputAdress" placeholder="주소" required /> -->
                  </div>
                  <div class="form-group">
                    <input
                      type="text"
                      id="sample2_detailAddress"
                      class="form-control form-control-user"
                      placeholder="상세 주소"
                    />

                    <!-- <input type="text" class="form-control form-control-user"
                              id="exampleInputDAdress" placeholder="상세주소" required /> -->
                  </div>

                  <!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
                  <div
                    id="layer"
                    style="
                      display: none;
                      position: fixed;
                      overflow: hidden;
                      z-index: 1;
                      -webkit-overflow-scrolling: touch;
                    "
                  >
                    <img
                      src="//t1.daumcdn.net/postcode/resource/images/close.png"
                      id="btnCloseLayer"
                      style="
                        cursor: pointer;
                        position: absolute;
                        right: -3px;
                        top: -3px;
                        z-index: 1;
                      "
                      onclick="closeDaumPostcode()"
                      alt="닫기 버튼"
                    />
                  </div>

                  <!-- 주소 끝 -->

                  <div class="form-group row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                      <input
                        type="text"
                        name="email_id"
                        id="email_id"
                        class="form-control form-control-user"
                        placeholder="이메일 앞자리"
                      />
                    </div>
                    <div class="col-sm-6">
                      <input
                        type="text"
                        name="email_add"
                        id="email_add"
                        class="form-control form-control-user"
                        placeholder="@ 이메일 뒷자리"
                      />
                    </div>
                  </div>
                  <div class="form-group">
                    <select
                      name="email_sel"
                      id="email_sel"
                      onchange="change_email();"
                      class="jw jw-btn-success btn-user btn-block"
                    >
                      <!-- <button type="button"
                                 class="jw jw-btn-success btn-user btn-block"
                                 onclick="id_check();">중복 확인</button> -->

                      <option value="">직접입력</option>
                      <option value="naver.com">네이버</option>
                      <option value="gmail.com">지메일</option>
                      <option value="nate.com">네이트</option>
                    </select>
                    <br />
                  </div>
                  <div class="join_btn">
                    <button
                      type="button"
                      onclick="joinform_check();"
                      class="btn btn-success btn-user btn-block"
                    >
                      회원 가입
                    </button>
                  </div>
                  <hr />
                  <a
                    href="error404.html"
                    class="btn btn-google btn-user btn-block"
                  >
                    <i class="fab fa-google fa-fw"></i> 구글로 회원가입
                  </a>
                  <a
                    href="error404.html"
                    class="btn btn-facebook btn-user btn-block"
                  >
                    <i class="fab fa-facebook-f fa-fw"></i> 카카오로 회원가입
                  </a>
                  <!-- </form> -->
                  <hr />
                  <!-- <div class="text-center">
                           <a class="small" href="forgot-password.html">비밀번호 찾기</a>
                        </div> -->
                  <div class="text-center">
                    <a class="small" href="login.html"
                      >이미 회원이시가요? 로그인으로 이동!</a
                    >
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

    <!-- Footer -->
    <footer>
      <div id="footer">
        <div class="container">
          <div class="row">
            <div class="col-md-6 col-md-offset-3 text-center">
              <p class="fh5co-social-icons">
                <span><i class="icon-twitter2"></i></span>
                <span><i class="icon-facebook2"></i></span>
                <span><i class="icon-instagram"></i></span>
                <span><i class="icon-dribbble2"></i></span>
                <span><i class="icon-youtube"></i></span>
              </p>
              <p>
                Control Co., Ltd. &copy; Website 2023
                <i class="icon-heart3"></i>
              </p>
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!-- End of Footer -->

    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

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