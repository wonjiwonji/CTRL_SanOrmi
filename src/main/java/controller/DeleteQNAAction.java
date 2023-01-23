package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QNADAO;
import model.qna.QNAVO;

public class DeleteQNAAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("freeBoards.jsp");
		forward.setRedirect(true);

		QNAVO qvo=new QNAVO();
		QNADAO qdao=new QNADAO();
		qvo.setqNum(Integer.parseInt(request.getParameter("qNum")));

		qdao.deleteQNA(qvo);
		return forward;
	}

}
