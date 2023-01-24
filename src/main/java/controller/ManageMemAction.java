package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class ManageMemAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/manageMem.jsp");
		forward.setRedirect(false);

		ArrayList<MemberVO> mList = new ArrayList<MemberVO>();
		MemberDAO mdao = new MemberDAO();
		
		mList=mdao.selectAllMember();

		request.getSession().setAttribute("mList", mList);
		
		
		return forward;
	}

}
