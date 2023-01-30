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

		forward.setPath("/bView.do");
		forward.setRedirect(false);

		bcvo.setBcID(request.getParameter("bcID"));
		bcvo.setBcContent(request.getParameter("bcContent"));
		bcvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
		
		bdao.insertBComment(bcvo);
		return forward;

	}

}
