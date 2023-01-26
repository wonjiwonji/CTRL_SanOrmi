<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 확인</title>
</head>
<body>
	<script type="text/javascript">
	if(${sessionScope.id == null}){
		alert('로그인 실패. 아이디 또는 비밀번호가 틀렸습니다.');
		location.href = "login.jsp";
	}
	else{
		alert('로그인 성공!');
		location.href = "main.do";
	}
	</script>
</body>
</html>