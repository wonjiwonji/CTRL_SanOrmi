<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>ctrl</h1>
	<form action="/dogs" id="instaForm">
		<input type="text" name="username" placeholder="username" /> <input
			type="text" name="comment" placeholder="comment" />
		<button>댓글 달기</button>
	</form>
	<h2>댓글:</h2>
	<ul id="comments">
	</ul>

	<script>
	
	const instaForm = document.querySelector("#instaForm");
	const commentsContainer = document.querySelector("#comments");

	// 댓글 작성
	instaForm.addEventListener("submit", function (e) {
	  e.preventDefault();
	  const usernameInput = instaForm.elements.username;
	  const commentInput = instaForm.elements.comment;
	  addComment(usernameInput.value, commentInput.value);
	  usernameInput.value = "";
	  commentInput.value = "";
	});

	const addComment = (username, comment) => {
	  const newComment = document.createElement("li");
	  const bTag = document.createElement("b");
	  bTag.append(username);
	  newComment.append(bTag);
	  newComment.append(comment);
	  commentsContainer.append(newComment);
	};

	// 댓글 삭제
	commentsContainer.addEventListener("click", (e) => {
	  e.target.nodeName === "LI" && e.target.remove();
	});

</script>





</body>
</html>