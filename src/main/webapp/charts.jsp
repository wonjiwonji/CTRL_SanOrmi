<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 자바스크립트 차단된 콘텐츠 자동 허용 실시 -->
<!-- saved from url=(0013)about:internet -->

<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>통계페이지</title>

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="./images/favicon.ico" />

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" />

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet" />

<!-- 내부 CSS 스타일 지정 -->
<style>

/* html, body 영역 스타일 지정 */
html, body {
	width: 100%;
	height: 100%;
	margin: 0;
	padding: 0;
	border: none;
	overflow: auto;
}

/* [body 스크롤바 메인 스타일 지정] */
body::-webkit-scrollbar {
	width: 10px;
	background-color: #c1c1c1;
}
/* [body 스크롤바 thumb 스타일 지정] */
body::-webkit-scrollbar-thumb {
	background-color: #444444;
}

/* [캔버스 부모 div 레이아웃] */
#canvas_container {
	width: 70%;
	height: 50%;
	margin: 0 auto;
	padding: 0;
	border: 1px solid #000000;
	border-radius: 20px;
	background-color: #ffffff;
	position: relative;
	top: 5%;
	left: 0;
	display: block;
}
</style>


<!-- Jquery CDN 로드 : 항상 최신 버전 사용 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>



<!-- chart js 라이브러리 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.0/chart.min.js"></script>



<!-- 내부 JS 지정 -->
<script>

    	/*
    	[JS 요약 설명]
    	1. window.onload : 브라우저 로드 완료 상태를 나타냅니다 
    	2. window.getComputedStyle : 특정 객체 스타일 속성 값을 확인합니다
    	3. chart js : <canvas> 사용해 차트를 렌더링 하며 단일 노드와 함께 페이지에 포함된 스크립트만 있으면 됩니다
    	4. myChart.destroy() : 생성된 차트를 삭제합니다
    	5. chart js 공식 사이트 : https://www.chartjs.org/docs/latest/
    	6. chart js cdn 참고 사이트 : https://cdnjs.com/libraries/Chart.js
    	*/

   	
    	
    	/* [html 최초 로드 및 이벤트 상시 대기 실시] */    	
    	window.onload = function() {
    		console.log("");
    		console.log("[window onload] : [start]");
    		console.log("");

    		/* [캔버스 크기 지정 함수 호출] */    		    		
    		canvasSizeCheck(); 
            

    		/* [bar 세로 막대 : 그리기 실시] */    		    		
    		drawBarHeight();                		    	
    	};


    	/* [html 화면 사이즈 변경 이벤트 감지] */
    	window.onresize = function() {
    		console.log("");
    		console.log("[window onresize] : [start]");
    		console.log(""); 

    		/* [캔버스 크기 지정 함수 호출] */    		    		
    		canvasSizeCheck(); 
            

    		/* [bar 세로 막대 : 그리기 실시] */    		    		
    		drawBarHeight();             
    	};


    	/* [차트 그리기에 사용할 전역 변수 지정 실시] */
    	var canvas;
    	var ctx;
    	var height = "";
    	var width = "";
    	var myChart;


    	/* [캔버스 크기 지정 실시] */
    	function canvasSizeCheck(){
    		console.log("");
    		console.log("[canvasSizeCheck] : [start]");
    		console.log("");

    		// [캔버스를 포함하고 있는 상위 부모 컨테이너 id 지정 >> 부모 컨테이너 크기에 맞춰서 캔버스 리사이즈]
    		var tagId = document.getElementById("canvas_container");

    		height = window.getComputedStyle(tagId).height;
    		width = window.getComputedStyle(tagId).width;
    		console.log("");
    		console.log("[canvasSizeCheck] : [height] : " + height);
    		console.log("[canvasSizeCheck] : [width] : " + width);
    		console.log("");

    		// [화면 사이즈가 변경된 경우 기존 차트 삭제 실시]
    		if(ctx != null){ 
    			canvas.getContext("2d").clearRect(0, 0, canvas.width, canvas.height);
    			ctx.beginPath();
    			myChart.destroy();
    		}
    	};

    	/* [bar 세로 막대 : 그리기 함수] */
    	function drawBarHeight(){
    		console.log("");
    		console.log("[drawBarHeight] : [start]");
    		console.log("");

    		// [body 에 선언된 캔버스 id 지정 실시]
    		canvas = document.getElementById('myBarHeightChart');
    		ctx = canvas.getContext('2d');

    		// [캔버스 크기를 부모 컨테이너 크기에 맞춥니다 : 부모 크기 % 지정 (반응형)]
    		canvas.height = height;
    		canvas.width = width;

    		// [차트 그리기 실시]
    		myChart = new Chart(ctx, {
    			type: 'bar', // [차트 타입 지정]
    			data: {
    				labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple'], // [데이터 라벨 (제목)]
    				datasets: [{
    					label: '# of Votes', // [데이터 시트 제목]
    					data: [26, 19, 23, 5, 2], // [데이터 : Red ~ purple]
    					backgroundColor: [ // [막대 배경 색상 : Red ~ purple ]
    						'rgba(255, 99, 132, 0.4)',
    						'rgba(54, 162, 235, 0.4)',
    						'rgba(255, 206, 86, 0.4)',
    						'rgba(75, 192, 192, 0.4)',
    						'rgba(153, 102, 255, 0.4)',
    					],
    					borderColor: [ // [막대 테두리 색상 : Red ~ purple ]
    						'rgba(255, 99, 132, 1)',
    						'rgba(54, 162, 235, 1)',
    						'rgba(255, 206, 86, 1)',
    						'rgba(75, 192, 192, 1)',
    						'rgba(153, 102, 255, 1)',
    					],
    					borderWidth: 1 // [막대 테두리 굵기 설정]
    				}]
    			},
    			options: {
    				plugins: {
    					title: { // [차트 타이틀 지정 실시]
    						display: true,
    						text: 'Twok Title',
    						color: '#911', // [타이틀 폰트 색상]
    						font: { // [타이틀 폰트 스타일 변경]
    							family: 'Comic Sans MS',
    							size: 20,
    							weight: 'bold',
    							lineHeight: 1.2,    							
    						},
    						padding: {top: 20, left: 0, right: 0, bottom: 0}    						
    					}
    				},
    				scales: {
    					y: { // [y 축 관련 설정] 
    						min: 0, // [y 축 데이터 설정 0 ~ 30 까지 제한]
    						max: 30,
    						grid: { // [y 축 데이터 시트 배경 선색 표시]
    							drawBorder: false,
    							color: function(context) {
    								if (context.tick.value >= 20) {
    									return 'rgba(0, 0, 255, 0.2)'; // 파랑
    								}
    								else if (context.tick.value < 20 && context.tick.value >= 10) {
    									return 'rgba(255, 0, 0, 0.2)'; // 빨강
    								}
    								else {
    									return 'rgba(0, 0, 0, 0.2)'; // 검정색
    								}    								
    							}
    						},
    						ticks: {
    							color: '#911', // [y 축 폰트 색상 설정]
    							font: { // [y축 폰트 스타일 변경]
    								family: 'Comic Sans MS',
    								size: 15,
    								weight: 'bold',
    								lineHeight: 1.2,   
    							} 
    						}
    					},
    					x: { // [x 축 관련 설정] 
    						ticks: {
    							color: '#000000', // [x 축 폰트 색상 설정]
    							font: { // [x축 폰트 스타일 변경]
    								family: 'Comic Sans MS',
    								size: 20,
    								weight: 'bold',
    								lineHeight: 1.2,   
    							} 
    						}
    					}
    				}    				
    			}
    		});
    	};
    	
    </script>



</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-success sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="adminPage.do">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">
					산오르미 <sup>admin</sup>
				</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link" href="main.do">
					<i class="fas fa-fw fa-tachometer-alt"></i> <span>산오르미 페이지</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider" />

			<!-- Heading -->
			<div class="sidebar-heading">Management</div>
			<div id="collapsePages" class="collapse"
				aria-labelledby="headingPages" data-parent="#accordionSidebar">
				<div class="bg-white py-2 collapse-inner rounded">
					<h6 class="collapse-header">Login Screens:</h6>
					<a class="collapse-item" href="login.do">Login</a> <a
						class="collapse-item" href="register.do">Register</a> <a
						class="collapse-item" href="forgot.do">Forgot Password</a>
					<div class="collapse-divider"></div>
					<h6 class="collapse-header">Other Pages:</h6>
					<a class="collapse-item" href="error404.do">404 Page</a> <a
						class="collapse-item" href="blank.do">Blank Page</a>
				</div>
			</div>

			<!-- Nav Item - Charts -->
			<li class="nav-item active"><a class="nav-link" href="charts.do">
					<i class="fas fa-fw fa-chart-area"></i> <span>통계</span>
			</a></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="manageBoards.do">
					<i class="fas fa-fw fa-table"></i> <span>게시글 관리</span>
			</a></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="manageMem.do">
					<i class="fas fa-fw fa-table"></i> <span>회원 관리</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block" />

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>
		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">
						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none"><a
							class="nav-link dropdown-toggle" href="#" id="searchDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
								aria-labelledby="searchDropdown">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input type="text"
											class="form-control bg-light border-0 small"
											placeholder="Search for..." aria-label="Search"
											aria-describedby="basic-addon2" />
										<div class="input-group-append">
											<button class="btn btn-success" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div></li>
						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">Admin</span>
								<img class="img-profile rounded-circle"
								src="img/undraw_profile.svg" alt="관리자 초상화" />
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a>
							</div></li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">통계</h1>
					<p class="mb-4">현재 커뮤니티 내 자주 검색된 컨텐츠와 키워드를 확인합니다.</p>

					<!-- Content Row -->
					<div class="row">
						<div class="col-xl-8 col-lg-7">
							<!-- Bar Chart -->
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-dark">커뮤니티왕</h6>
								</div>
								<div class="card-body">
									<div class="chart-bar">
										<!-- 레이아웃 : bar 세로 막대 -->
										<div id="canvas_container">
											<canvas id="myBarHeightChart"></canvas>
										</div>

									</div>
									<hr />
									커뮤니티에 작성한 글 개수
								</div>
							</div>
						</div>
						<!-- chart-bar는 js > demo에 있음 -->

						<div class="col-xl-8 col-lg-7">
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-dark">(주)Ctrl 주가 그래프
									</h6>
									<div class="dropdown no-arrow">
										<a class="dropdown-toggle" href="#" role="button"
											id="dropdownMenuLink" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"> <i
											class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
										</a>
										<div
											class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
											aria-labelledby="dropdownMenuLink">
											<div class="dropdown-header">Dropdown Header:</div>
											<a class="dropdown-item" href="#">Action</a> <a
												class="dropdown-item" href="#">Another action</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item" href="#">Something else here</a>
										</div>
									</div>
								</div>
								<!-- Card Body -->
								<div class="card-body">
									<div class="chart-area">
										<canvas id="myAreaChart"></canvas>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">로그아웃 하시겠습니까?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">현재 세션을 종료할 준비가 되었으면 아래에서 "로그아웃"을
					선택하십시오.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">취소</button>
					<a class="btn btn-primary" href="logout.do?mPw=${sessionScope.mPw}">로그아웃</a>
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

	<!-- Page level plugins -->
	<script src="vendor/chart.js/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<!--    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>
    <script src="js/demo/chart-bar-demo.js"></script> -->
</body>
</html>