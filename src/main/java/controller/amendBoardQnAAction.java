package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QNADAO;
import model.qna.QNASet;
import model.qna.QNAVO;

public class amendBoardQnAAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/amendBoardQnA.jsp");
		forward.setRedirect(false);
		
		QNADAO qdao = new QNADAO();
		QNAVO qvo = new QNAVO();
		QNAVO vqvo = new QNAVO();
		ArrayList<QNASet> qbvo = new ArrayList<QNASet>();
		
		qvo.setqNum(Integer.parseInt(request.getParameter("qNum")));
		
		qbvo=qdao.selectOneQNA(qvo);
		
		vqvo=qbvo.get(0).getQna();
		request.getSession().setAttribute("vqvo", vqvo);

		return forward;
	}

}
