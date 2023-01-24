package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class AdminPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/adminPage.jsp");
		forward.setRedirect(false);
		
		MemberDAO mdao=new MemberDAO();
		ArrayList<MemberVO> kinglist=new ArrayList<MemberVO>();
		
		kinglist=mdao.selectAllCommunityKing();

		request.getSession().setAttribute("kinglist", kinglist);
		
		return forward;
	}

}
