<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
	<c:when test="${sessionScope.id == 'admin'}">
		<li><a href="adminPage.do">관리자 페이지</a></li>
		<li><a href="logout.do?mPw=${sessionScope.mPw}">로그아웃</a></li>
	</c:when>
	<c:when test="${sessionScope.id != null}">
		<li><a class="fh5co-sub-ddown sf-with-ul">회원정보</a>
			<ul class="fh5co-sub-menu">
				<li><a href="myBoard.do?bId=${sessionScope.id}">작성글 확인</a></li>
				<li><a href="withdrawal.do">회원탈퇴</a></li>
				<c:if test="${!(fn:contains(sessionScope.id, '@'))}">
				<li><a href="updateInfo.do?id=${sessionScope.id}">정보 수정</a></li>
				</c:if>
			</ul></li>
		<li><a href="logout.do?mPw=${sessionScope.mPw}">로그아웃</a></li>
	</c:when>
	<c:otherwise>
		<li><a class="fh5co-sub-ddown sf-with-ul">로그인/회원가입</a>
			<ul class="fh5co-sub-menu">
				<li><a href="loginPage.do">로그인</a></li>
				<li><a href="agree.do">회원가입</a></li>
			</ul></li>
	</c:otherwise>
	
</c:choose>