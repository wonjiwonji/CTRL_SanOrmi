package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QNADAO;
import model.qna.QNAVO;

public class UpdateQNAAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("qView.do");
		forward.setRedirect(false);

		QNAVO qvo=new QNAVO();
		QNADAO qdao=new QNADAO();
		qvo.setqTitle(request.getParameter("qTitle"));
		qvo.setqContent(request.getParameter("qContent"));
		qvo.setqNum(Integer.parseInt(request.getParameter("qNum")));

		qdao.updateQNA(qvo);
		return forward;
	}

}
