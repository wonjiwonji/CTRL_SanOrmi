package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QNADAO;
import model.qna.QNAVO;

public class InsertQNAAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("freeBoards.jsp");
		forward.setRedirect(true);

		QNAVO qvo=new QNAVO();
		QNADAO qdao=new QNADAO();
		qvo.setqTitle(request.getParameter("qTitle"));
		qvo.setqContent(request.getParameter("qContent"));
		qvo.setqId(request.getParameter("qId"));

		qdao.insertQNA(qvo);
		return forward;
	}

}
