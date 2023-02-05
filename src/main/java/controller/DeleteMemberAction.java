package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class DeleteMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("main.jsp");
		forward.setRedirect(false);
		
		MemberDAO mdao=new MemberDAO();
		MemberVO mvo=new MemberVO();
		mvo.setId(request.getParameter("id"));
		
		mdao.deleteMember(mvo);
		
		request.getSession().invalidate();
		
		return forward;
	}
}
