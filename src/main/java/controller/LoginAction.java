package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/main.do");
		forward.setRedirect(false);

		MemberDAO mdao=new MemberDAO();
		MemberVO mvo=new MemberVO();
		mvo.setId(request.getParameter("id"));
		mvo.setPw(request.getParameter("pw"));
		MemberVO member=mdao.selectOne(mvo);
		
		request.getSession().setAttribute("memberId", member.getId());
		request.getSession().setAttribute("memberName", member.getName());
		
		return forward;
	}

}