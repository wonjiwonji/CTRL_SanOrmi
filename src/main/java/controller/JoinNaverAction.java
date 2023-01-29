package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class JoinNaverAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("main.do");
		forward.setRedirect(true);

		MemberDAO mdao=new MemberDAO();
		MemberVO mvo=new MemberVO();

		mvo.setId("(N)"+request.getParameter("email"));
		mvo.setmName(request.getParameter("name"));
		mvo.setmEmail(request.getParameter("email"));
		mvo.setmPw("NAVER");
		
		if(mdao.selectOneMember(mvo)==null) {
			mdao.insertNaverMember(mvo);
			mdao.loginMember(mvo);
			request.getSession().setAttribute("id", mvo.getId());
			request.getSession().setAttribute("mPw", mvo.getmPw());
		} else {
			mdao.loginMember(mvo);
			request.getSession().setAttribute("id", mvo.getId());
			request.getSession().setAttribute("mPw", mvo.getmPw());
		}

		return forward;
	}

}
