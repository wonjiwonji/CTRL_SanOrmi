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

		mvo.setId(request.getParameter("email"));
		mvo.setmName(request.getParameter("name"));
		mvo.setmEmail(request.getParameter("email"));
		
		System.out.println(mvo);
		
		if(mdao.selectOneMember(mvo)==null) {
			mdao.insertNaverMember(mvo);
			mdao.loginMember(mvo);
			request.getSession().setAttribute("id", mvo.getId());
			request.getSession().setAttribute("mPw", mvo.getmPw());
			System.out.println(mvo);
		} else {
			mdao.loginMember(mvo);
			request.getSession().setAttribute("id", mvo.getId());
			request.getSession().setAttribute("mPw", mvo.getmPw());
			System.out.println(mvo);
		}

		return forward;
	}

}
