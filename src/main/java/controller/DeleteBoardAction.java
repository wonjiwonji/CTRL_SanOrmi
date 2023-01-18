package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardVO;

public class DeleteBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/tables.jsp");
		forward.setRedirect(false);

		BoardVO bvo=new BoardVO();
		BoardDAO bdao=new BoardDAO();
		bvo.setbNum(Integer.parseInt(request.getParameter("bNum")));

		bdao.deleteBoard(bvo);
		return forward;
	}

}
