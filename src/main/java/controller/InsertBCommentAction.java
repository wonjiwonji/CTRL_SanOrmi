package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BCommentVO;
import model.board.BoardDAO;
import model.board.BoardVO;

public class InsertBCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/bView.do");
		forward.setRedirect(false);
		
		if(request.getParameter("bcID")=="") {
			forward.setPath("/loginNo.jsp");
			forward.setRedirect(false);

			return forward;
		}
		
		BCommentVO bcvo=new BCommentVO();
		BoardVO bvo=new BoardVO();
		BoardDAO bdao=new BoardDAO();

		bcvo.setBcID(request.getParameter("bcID"));
		bcvo.setBcContent(request.getParameter("bcContent"));
		bcvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
		
		bvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
		
		bdao.insertBComment(bcvo);
		return forward;

	}

}
