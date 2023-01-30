package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BCommentVO;
import model.board.BoardDAO;

public class InsertBCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		
		BCommentVO bcvo=new BCommentVO();
		BoardDAO bdao=new BoardDAO();

		bcvo.setBcID(request.getParameter("bcID"));
		
		System.out.println(bcvo);
		
		if(bcvo.getBcID()!=null) {
			forward.setPath("bView.do");
			forward.setRedirect(true);
			
			bcvo.setBcContent(request.getParameter("bcContent"));
			bcvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
			
			bdao.insertBComment(bcvo);
			return forward;
		} else {
			forward.setPath("login.do");
			forward.setRedirect(true);
			return forward;
		}
		
	}

}
