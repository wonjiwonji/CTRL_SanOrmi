package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class CommunityKingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("charts.jsp");
		forward.setRedirect(false);
		
		MemberDAO mdao=new MemberDAO();
		ArrayList<MemberVO> kinglist=new ArrayList<MemberVO>();
		
		kinglist=mdao.selectAllCommunityKing();

		request.getSession().setAttribute("kinglist", kinglist);
		
		return forward;
	}

}
