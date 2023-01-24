package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class ManageMemDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/manageMemDetail.jsp");
		forward.setRedirect(false);
		
		MemberDAO mdao = new MemberDAO();
		MemberVO mvo = new MemberVO();
		MemberVO smvo = new MemberVO();
		
		mvo.setId(request.getParameter("id"));
		
		smvo=mdao.selectOneMember(mvo);

		request.getSession().setAttribute("smvo", smvo);
		
		return forward;
	}

}
