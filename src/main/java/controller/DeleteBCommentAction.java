package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BCCommentVO;
import model.board.BCommentVO;
import model.board.BoardDAO;
import model.board.BoardVO;

public class DeleteBCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/bView.do");
		forward.setRedirect(false);

		BCommentVO bcvo=new BCommentVO();
		BCCommentVO bccvo=new BCCommentVO();
		BoardDAO bdao=new BoardDAO();
		BoardVO bvo = new BoardVO();
		
		String bNumSample = request.getParameter("bNum");
		
			int bNum=Integer.parseInt(bNumSample);
			bvo.setbNum(bNum);
		
		if(request.getParameter("bccNum") == null) {
			bcvo.setBcNum(Integer.parseInt(request.getParameter("bcNum")));
			bcvo.setBcGroup(Integer.parseInt(request.getParameter("bcGroup")));
			bcvo.setbNum(bNum);
			bdao.deleteBComment(bcvo);
		} else {
			bccvo.setBccNum(Integer.parseInt(request.getParameter("bccNum")));
			bdao.deleteBCComment(bccvo);
		}
		
		return forward;
	}

}
