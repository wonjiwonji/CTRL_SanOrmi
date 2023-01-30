package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BCommentVO;
import model.board.BoardDAO;
import model.board.BoardVO;

public class DeleteBCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/bView.do");
		forward.setRedirect(false);

		BCommentVO bcvo=new BCommentVO();
		BoardDAO bdao=new BoardDAO();
		BoardVO bvo = new BoardVO();
		
		bvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
		
		bcvo.setBcNum(Integer.parseInt(request.getParameter("bcNum")));
		
		BCommentVO dbcvo=bdao.selectOne(bcvo);
		
		bdao.deleteBComment(dbcvo);
		return forward;
	}

}
