package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("loginOK.jsp");
		forward.setRedirect(false);

		MemberDAO mdao=new MemberDAO();
		MemberVO mvo=new MemberVO();
		mvo.setId(request.getParameter("id"));
		mvo.setmPw(request.getParameter("mPw"));

		MemberVO member=mdao.loginMember(mvo);

		if(member!=null) {
			request.getSession().setAttribute("id", member.getId());
			request.getSession().setAttribute("mPw", member.getmPw());
			System.out.println(member);
			return forward;
		} else {
			return forward;
		}

	}

}
