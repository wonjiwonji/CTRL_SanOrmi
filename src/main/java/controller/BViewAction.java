package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardSet;
import model.board.BoardVO;

public class BViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/bView.jsp");
		forward.setRedirect(false);
		
		BoardDAO bdao = new BoardDAO();
		BoardVO bvo = new BoardVO();
		ArrayList<BoardSet> bbvo = new ArrayList<BoardSet>();
		
		bvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
		
		bbvo=bdao.selectOneBoard(bvo);
		bdao.updatebCnt(bvo);
		
		request.getSession().setAttribute("bbvo", bbvo);
		
		System.out.println(bbvo);
		
		return forward;
	}

}
