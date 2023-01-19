<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
<c:when test="${membermName != null}">
			<li> <a class="fh5co-sub-ddown sf-with-ul">회원정보</a>
		<ul class="fh5co-sub-menu">
			<li><a href="logout.do">로그아웃</a></li>
			<li><a href="myBoard.do">마이페이지</a></li>
		</ul>
	</li>
</c:when>
<c:otherwise>
	<!--<li><a href="logout.do" class="sf-menu" id="fh5co-primary-menu">로그아웃</a></li>-->
		<li> <a class="fh5co-sub-ddown sf-with-ul">로그인</a>
		<ul class="fh5co-sub-menu">
			<li><a href="loginPage.do">로그인</a></li>
			<li><a href="agree.do">회원가입</a></li>
		</ul>
	</li>
</c:otherwise>
</c:choose>
