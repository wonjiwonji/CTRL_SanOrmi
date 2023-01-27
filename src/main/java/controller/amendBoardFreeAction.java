package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardSet;
import model.board.BoardVO;

public class amendBoardFreeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/amendBoardFree.jsp");
		forward.setRedirect(false);
		
		BoardDAO bdao = new BoardDAO();
		BoardVO bvo = new BoardVO();
		BoardVO vbvo = new BoardVO();
		ArrayList<BoardSet> bbvo = new ArrayList<BoardSet>();
		
		bvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
		
		bbvo=bdao.selectOneBoard(bvo);
		
		vbvo=bbvo.get(0).getBoard();
		request.getSession().setAttribute("vbvo", vbvo);

		return forward;
	}

}
