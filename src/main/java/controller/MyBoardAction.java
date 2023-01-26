package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardVO;
import model.member.MemberDAO;
import model.member.MemberVO;

public class MyBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/myBoard.jsp");
		forward.setRedirect(false);

		BoardVO bvo=new BoardVO();
		BoardDAO bdao=new BoardDAO();
		ArrayList<BoardVO> myList = new ArrayList<>();
		
		bvo.setbId(request.getParameter("bId"));
		
		myList = bdao.selectAllMyPage(bvo);
		
		request.getSession().setAttribute("myList", myList);		
		
		return forward;
	}

}
