package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardSet;
import model.board.BoardVO;

public class CommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/comment.jsp");
		forward.setRedirect(false);
		
		BoardDAO bdao=new BoardDAO();
		BoardVO bvo=new BoardVO();
		ArrayList<BoardSet> bdatas = new ArrayList<BoardSet>();
		
		bvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
		bdatas=bdao.selectOneBoard(bvo);
		
		request.getSession().setAttribute("bdatas", bdatas);

		return forward;
	}

}
