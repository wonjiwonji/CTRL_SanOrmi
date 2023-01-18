package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardVO;

public class InsertBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/main.do");
		forward.setRedirect(false);

		BoardVO bvo=new BoardVO();
		BoardDAO bdao=new BoardDAO();

		bdao.insertBoard(bvo);
		return forward;
	}

}
