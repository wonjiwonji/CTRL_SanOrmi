package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class UpdateInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/updateInfo.jsp");
		forward.setRedirect(false);

		MemberDAO mdao = new MemberDAO();
		MemberVO mvo = new MemberVO();
		MemberVO me = new MemberVO();
		
		mvo.setId(request.getParameter("id"));
		
		me=mdao.selectOneMember(mvo);

		request.getSession().setAttribute("me", me);
		
		return forward;
	}

}
