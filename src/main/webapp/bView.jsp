<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>게시글</title>

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="./images/favicon.ico" />

<!-- link start -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet" />

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="css/board.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="favicon.ico" />
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,700,300"
	rel="stylesheet" type="text/css" />

<script src="https://kit.fontawesome.com/51772bd9bd.js"
	crossorigin="anonymous"></script>
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
<link rel="stylesheet" href="css/sub_layout.css" />
<link rel="stylesheet" href="css/styleguide.css" />
<link rel="stylesheet" href="css/community.css" />
<link rel="stylesheet" href="css/join.css" />
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/like.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!--  모두의 낚시 -->

<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>

<%-- <%
   String loginUser = (String)session.getAttribute("id");

   pageContext.setAttribute("loginUser", loginUser);
%> --%>

<!--[if lt IE 9]>
      <script src="js/respond.min.js"></script>
    <![endif]-->
<!-- link end -->
</head>
<body>
	<div id="fh5co-wrapper">
		<div id="fh5co-page">

			<%-- 헤더임 --%>
			<jsp:include page="header.jsp" />
			<%-- 헤더라고 --%>
			<article class="view_wrap pd60">
				<div class="center clearfix">
					<div class="container mt-3">
						<hr>
						<div class="mb-3 mt-3 clearfix">
							<span class="float-start me-2">${bbvo[0].board.bNum }번 글</span> <span
								class="float-end ms-4">${bbvo[0].board.bDate }</span> <span
								class="float-end">${bCnt }</span>
						</div>

						<section>
							<c:forEach var="boardSet" items="${bbvo}">
								<c:set var="board" value="${boardSet.board}" />
								<div class="mb-3">
									<label for="name">작성자:</label>
									<div class="border bg-light rounded p-2">${board.bId }</div>
								</div>
								<div class="mb-3 mt-3">
									<label for="subject">제목:</label>
									<div class="border bg-light rounded p-2">${board.bTitle }</div>
								</div>
								<div class="mb-3 mt-3">
									<label for="content">내용:</label>
									<div class="border bg-light rounded p-2">${board.bContent }</div>
								</div>

								<c:if test="${bbvo[0].board.bId == sessionScope.id}">
									<!-- 작성자==로그인유저 -->
									<a class="btn btn-outline-dark"
										href="amendBoardFree.do?bNum=${board.bNum}">수정</a>
									<a class="btn btn-outline-dark"
										href="deleteBoard.do?bNum=${board.bNum}">삭제</a>
								</c:if>

								<c:if
									test="${bbvo[0].board.bId != sessionScope.id && sessionScope.id != null }">
									<!-- 작성자 != 로그인유저 && 로그인 유저 != null -->
									<a class="btn btn-outline-dark"
										href="report.do?rId=${sessionScope.id}&bNum=${board.bNum}&rTargetId=${board.bId}">신고</a>
								</c:if>

								<a class="btn btn-outline-dark" href="freeBoards.do">목록</a>
								<!-- 모든이에게 보이는 행동 -->
							</c:forEach>
						</section>
						<div class="container my-3 border rounded">
							<div class="mb-3 mt-3">
								<label>댓글: <span id="cmt_cnt">${bbvo[0].board.cCnt}</span>
									개
								</label>
								<form action="insertBComment.do" class="input-group my-2">
									<input type="hidden" class="form-control" id="input_cmt_num"
										name="bNum" value="${bbvo[0].board.bNum}"> <input
										type="hidden" class="form-control" id="input_writer"
										name="bcID" value="${sessionScope.id}"> <input
										type="text" class="form-control" id="input_comment"
										name="bcContent">
									<button type="submit" class="btn btn-outline-primary"
										id="btn_comment">작성</button>
								</form>
								<table class="table table-hover mt-3" id="cmt_table">
									<thead>
										<tr>
											<th style="width: 16.66%">작성자</th>
											<th>내용</th>
											<th style="width: 16.66%">작성일</th>
											<th style="width: 6.66%">댓글 달기</th>
											<th style="width: 6.66%">삭제</th>
										</tr>
									</thead>
									<tbody id="cmt_list">
										<c:forEach var="bcList" items="${bcList}">
											<tr>
												<td>${bcList.bcID}</td>
												<td>${bcList.bcContent}</td>
												<td>${bcList.bcDate}</td>
												<td>댓글 달기</td>
												<td>
												${sessionScope.id}
												${bcList.bcID}
												</td>
												<c:if test="${sessionScope.id} == ${bcList.bcID}">
													<td>삭제</td>
												</c:if>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- center -->

				<div class="report_popup">
					<div class="tit">
						<span class="line1"></span> <span class="line2"></span>
					</div>
					<div class="con">
						<div class="button_wrap tac">
							<!-- <button type="button" class="delete_btn">닫기</button> -->
						</div>
					</div>
				</div>
			</article>

			<jsp:include page="footer.jsp" />


		</div>
	</div>
</body>
</html>