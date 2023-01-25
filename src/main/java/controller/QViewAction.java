package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardSet;
import model.qna.QNADAO;
import model.qna.QNASet;
import model.qna.QNAVO;

public class QViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/qView.jsp");
		forward.setRedirect(false);
		
		QNADAO qdao = new QNADAO();
		QNAVO qvo = new QNAVO();
		ArrayList<QNASet> qbvo = new ArrayList<QNASet>();
		
		qvo.setqNum(Integer.parseInt(request.getParameter("qNum")));
		
		System.out.println("로그1: "+qvo);
		
		qbvo=qdao.selectOneQNA(qvo);
		qdao.updatebCnt(qvo);
		
		System.out.println(qbvo);
		
		request.getSession().setAttribute("qbvo", qbvo);
		
		return forward;
	}

}
