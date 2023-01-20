package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardVO;

public class InsertBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("writeBoardFree.jsp");
		forward.setRedirect(true);

		BoardVO bvo=new BoardVO();
		BoardDAO bdao=new BoardDAO();
		bvo.setbTitle(request.getParameter("bTitle"));
		bvo.setbContent(request.getParameter("bContent"));
		bvo.setbId(request.getParameter("bId"));

		bdao.insertBoard(bvo);
		return forward;
	}

}
