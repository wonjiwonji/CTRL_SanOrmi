package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardSet;
import model.board.BoardVO;

public class BCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/bComment.jsp");
		forward.setRedirect(false);
		
		BoardDAO bdao=new BoardDAO();
		BoardVO bvo=new BoardVO();
		ArrayList<BoardSet> selectBs = new ArrayList<BoardSet>();
		
		bvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
		selectBs=bdao.selectOneBoard(bvo);
		
		request.getSession().setAttribute("selectBs", selectBs);

		return forward;
	}

}
