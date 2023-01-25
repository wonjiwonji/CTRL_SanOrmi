package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardVO;
import model.member.MemberDAO;
import model.member.MemberVO;

public class InsertBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("freeBoards.do");
		forward.setRedirect(true);

		BoardVO bvo=new BoardVO();
		BoardDAO bdao=new BoardDAO();
		bvo.setbTitle(request.getParameter("bTitle"));
		bvo.setbContent(request.getParameter("bContent"));
		bvo.setbId(request.getParameter("bId"));
		
		MemberVO mvo=new MemberVO();
		MemberDAO mdao=new MemberDAO();
		mvo.setId(request.getParameter("id"));
		
		bdao.insertBoard(bvo);
		mdao.updateMBoardCnt(mvo);
		return forward;
	}

}
