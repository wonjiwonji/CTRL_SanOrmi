package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BCCommentVO;
import model.board.BCommentVO;
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
		ArrayList<BCommentVO> bcList = new ArrayList<BCommentVO>();
		ArrayList<BCCommentVO> bccList = new ArrayList<BCCommentVO>();
		ArrayList<BoardSet> bbvo = new ArrayList<BoardSet>();
		
		bvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
		
		bbvo=bdao.selectOneBoard(bvo);
		bdao.updatebCnt(bvo);
		
		bcList=bbvo.get(0).getBcList();
		System.out.println(bcList);
		System.out.println(bbvo.get(0).getBcList().get(0).getBcGroup());
		bbvo.get(0).getBcList().get(0).getBcGroup();
		//bccList=bcList.get(0).getBccList();
		
		request.getSession().setAttribute("bbvo", bbvo);
		request.getSession().setAttribute("bcList", bcList);
		//request.getSession().setAttribute("bccList", bccList);
		
		return forward;
	}

}
