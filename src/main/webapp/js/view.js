$(function(){
	
	// 현재 글의 uid 값
	const uid = $("input[name='uid']").val().trim();
	
	// 글의 댓글들 불러오기
	loadComment(uid);
	
	// 댓글 작성 버튼 누르면 댓글 등록 하기.  
	// 1. 어느글에 대한 댓글인지? --> 위에 uid 변수에 담겨있다
	// 2. 어느 사용자가 작성한 댓글인지? --> logged_uid 값
	// 3. 댓글 내용은 무엇인지?  --> 아래 content
	$("#btn_comment").click(function(){
		// 입력한 값
		const content = $("#input_comment").val().trim();
		
		// 검증
		if(!content){
			alert("댓글 입력을 하세요");
			return;
		}
		
		// 전달할 parameter 준비
		const data = {
			"write_uid" : uid,
			"user_uid" : logged_uid,
			"content" : content
		}
		
		$.ajax({
			url : conPath + "/comment/write",
			type: "POST",
			data: data,
			cache: false,
			success: function(data, status){
				if(status == "success"){
					if(data.status !== "OK"){
						alert(data.status);
						return;
					}
					loadComment(uid);  // 댓글 목록 갱신
					$("#input_comment").val('');  // 기존 입력 지우기
				}
			}
		});
		
	});
	
});

// 글(write_uid) 의 댓글 목록 읽어오기
function loadComment(write_uid){
	
	$.ajax({
		url : conPath + "/comment/list?uid=" + write_uid,
		type : "GET",
		cache : false,
		success : function(data, status, xhr){
			if(status == "success"){
				//alert(xhr.responseText);  // 확인용
				
				// 서버측 처리 오류 발생시
				if(data.status !== "OK"){
					alert(data.status);
					return;
				}
				
				// 댓글 목록 만들기
				buildComment(data);
				// 댓글 목록을 불러오고 난 뒤에 삭제에 대한 이벤트 등록.
				addDelete();
			}
		}
	});
	
	
}//end loadComment()	


function buildComment(result){
	$("#cmt_cnt").text(result.count);  // 댓글 총 개수
	
	const out = [];
	
	result.data.forEach(comment => {
		let uid = comment.uid;
		let content = comment.content.trim();
		let regdate = comment.regdate;
		
		let user_uid = parseInt(comment.user.uid);
		let username = comment.user.username;
		let name = comment.user.name;
		
		
		// 삭제버튼 여부 : 작성자 본인의 댓글인 경우에만 보이기
		const delBtn = (logged_uid !== user_uid) ? '' : `
		      <i class="btn fa-solid fa-delete-left text-danger" data-bs-toggle="tooltip" 
		      	data-cmtdel-uid="${uid}" title="삭제"></i>
		`;
		
		
		const row = `
              <tr>
                <td><span><strong>${username}</strong><br><small class="text-secondary">(${name})</small></span></td>
                <td>
                    <span>${content}</span>${delBtn}
                </td>
                <td><span><small class="text-secondary">${regdate}</small></span></td>
              </tr>		
		`;
		
		out.push(row);	
	});
	
	
	$("#cmt_list").html(out.join("\n"));
} // end buildComment()


function addDelete(){
	
	// 현재 글의 uid
	const uid = $("input[name='uid']").val().trim();
	
	$("[data-cmtdel-uid]").click(function(){
		if(!confirm("댓글을 삭제하시겠습니까?")) return;
		
		// 삭제할 댓글의 uid
		const comment_uid = $(this).attr("data-cmtdel-uid");
		
		$.ajax({
			url : conPath + "/comment/delete",
			type : "POST",
			cache : false,
			data : {"uid": comment_uid},
			success : function(data, status){
				if(status == "success"){
					if(data.status !== "OK"){
						alert(data.status);
						return;
					}
					
					// 삭제후에도 다시 목록을 갱신해야 한다
					loadComment(uid);
				}
			}
		});
	});
	
	
} // end addDelete()