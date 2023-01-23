package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class JoinNaverAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("main.jsp");
		forward.setRedirect(true);

		MemberDAO mdao=new MemberDAO();
		MemberVO mvo=new MemberVO();

		mvo.setId(request.getParameter("mEmail"));
		mvo.setmName(request.getParameter("mName"));
		mvo.setmEmail(request.getParameter("mEmail"));
		
		if(mdao.selectOneMember(mvo)==null) {
			mdao.insertNaverMember(mvo);
			mdao.loginMember(mvo);

		} else {
			mdao.loginMember(mvo);
		}

		System.out.println(mvo);

		return forward;
	}

}
