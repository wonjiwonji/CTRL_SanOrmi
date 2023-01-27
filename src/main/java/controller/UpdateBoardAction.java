package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardVO;
import model.member.MemberDAO;
import model.member.MemberVO;

public class UpdateBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("bView.do");
		forward.setRedirect(false);

		BoardVO bvo=new BoardVO();
		BoardDAO bdao=new BoardDAO();
		bvo.setbTitle(request.getParameter("bTitle"));
		bvo.setbContent(request.getParameter("bContent"));
		bvo.setbNum(Integer.parseInt(request.getParameter("bNum")));

		bdao.updateBoard(bvo);
		
		System.out.println(bvo);
		return forward;
	}

}
