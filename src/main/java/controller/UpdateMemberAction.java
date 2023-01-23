package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class UpdateMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("main.jsp");
		forward.setRedirect(true);
		
		MemberDAO mdao=new MemberDAO();
		MemberVO mvo=new MemberVO();
		mvo.setmPw(request.getParameter("mPw"));
		mvo.setmName(request.getParameter("mName"));
		mvo.setmEmail(request.getParameter("mEmail"));
		mvo.setmAddress(request.getParameter("mAddress"));
		mvo.setId(request.getParameter("id"));
		
		System.out.println(mvo);
		
		mdao.updateMember(mvo);
		
		request.getSession().setAttribute("id", mvo.getId());
		request.getSession().setAttribute("mName", mvo.getmName());
		
		return forward;
	}

}
