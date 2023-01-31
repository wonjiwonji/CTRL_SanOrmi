package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class ChartsAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/charts.jsp");
		forward.setRedirect(false);
		
		MemberDAO mdao=new MemberDAO();
		ArrayList<MemberVO> kinglist=new ArrayList<MemberVO>();
		
		String kingId1="";
		String kingId2="";
		String kingId3="";
		String kingId4="";
		String kingId5="";
		
		int kingBc1=0;
		int kingBc2=0;
		int kingBc3=0;
		int kingBc4=0;
		int kingBc5=0;
		
		kinglist=mdao.selectAllCommunityKing();
		
		if(kinglist.size()==5) {
		kingId1=kinglist.get(0).getId();
		kingId2=kinglist.get(1).getId();
		kingId3=kinglist.get(2).getId();
		kingId4=kinglist.get(3).getId();
		kingId5=kinglist.get(4).getId();
		
		kingBc1=kinglist.get(0).getmBoardCnt();
		kingBc2=kinglist.get(1).getmBoardCnt();
		kingBc3=kinglist.get(2).getmBoardCnt();
		kingBc4=kinglist.get(3).getmBoardCnt();
		kingBc5=kinglist.get(4).getmBoardCnt();
		
		request.getSession().setAttribute("kingId1", kingId1);
		request.getSession().setAttribute("kingId2", kingId2);
		request.getSession().setAttribute("kingId3", kingId3);
		request.getSession().setAttribute("kingId4", kingId4);
		request.getSession().setAttribute("kingId5", kingId5);
		
		request.getSession().setAttribute("kingBc1", kingBc1);
		request.getSession().setAttribute("kingBc2", kingBc2);
		request.getSession().setAttribute("kingBc3", kingBc3);
		request.getSession().setAttribute("kingBc4", kingBc4);
		request.getSession().setAttribute("kingBc5", kingBc5);
		}
		
		return forward;
	}

}
