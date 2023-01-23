package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BCommentVO;
import model.board.BoardDAO;

public class DeleteBCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("view.do");
		forward.setRedirect(true);

		BCommentVO bcvo=new BCommentVO();
		BoardDAO bdao=new BoardDAO();
		
		bcvo.setBcNum(Integer.parseInt(request.getParameter("bcNum")));
		bcvo.setBcGroup(Integer.parseInt(request.getParameter("bcGroup")));

		bdao.deleteBComment(bcvo);
		return forward;
	}

}
