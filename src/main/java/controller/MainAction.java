package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardVO;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/main.jsp");
		forward.setRedirect(false);
		
		ArrayList<BoardVO> best5 = new ArrayList<BoardVO>();
		BoardDAO bdao=new BoardDAO();
		
		best5=bdao.selectAllTop5();

		request.getSession().setAttribute("best5", best5);
		
		
		return forward;
	}
}
