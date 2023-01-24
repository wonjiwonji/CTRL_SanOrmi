package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardVO;

public class FreeBoardsAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/freeBoards.jsp");
		forward.setRedirect(false);
		
		ArrayList<BoardVO> bList = new ArrayList<>();
		BoardDAO bdao=new BoardDAO();

		bList=bdao.selectAllBoard();
		
		request.getSession().setAttribute("bList", bList);

		return forward;
	}

}
