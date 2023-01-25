package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class JoinKakaoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("main.do");
		forward.setRedirect(true);

		MemberDAO mdao=new MemberDAO();
		MemberVO mvo=new MemberVO();

		mvo.setId(request.getParameter("account_email"));
		mvo.setmName(request.getParameter("profile_nickname"));
		mvo.setmEmail(request.getParameter("account_email"));

		if(mdao.selectOneMember(mvo)==null) {
			mdao.insertKakaoMember(mvo);
			mdao.loginMember(mvo);
			request.getSession().setAttribute("id", mvo.getId());
			System.out.println(mvo);

		} else {
			mdao.loginMember(mvo);
			request.getSession().setAttribute("id", mvo.getId());
			System.out.println(mvo);
		}
		


		return forward;
	}

}
