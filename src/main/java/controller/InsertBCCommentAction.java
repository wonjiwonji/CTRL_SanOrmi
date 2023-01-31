package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BCCommentVO;
import model.board.BoardDAO;

public class InsertBCCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/bView.do");
		forward.setRedirect(false);

		BCCommentVO bccvo=new BCCommentVO();
		BoardDAO bdao=new BoardDAO();
		
		bccvo.setBccID(request.getParameter("bccID"));
		bccvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
		bccvo.setBccContent(request.getParameter("bccContent"));
		bccvo.setBccGroup(Integer.parseInt(request.getParameter("bccGroup")));

		bdao.insertBCComment(bccvo);
		return forward;
	}

}
