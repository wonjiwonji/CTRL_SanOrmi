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
		forward.setPath("view.do");
		forward.setRedirect(true);

		BCommentVO bcvo=new BCommentVO();
		BoardDAO bdao=new BoardDAO();
		bcvo.setBcContent(request.getParameter("bcContent"));
		bcvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
		bcvo.setBcID(request.getParameter("bcID"));

		bdao.insertBComment(bcvo);
		return forward;
	}

}
