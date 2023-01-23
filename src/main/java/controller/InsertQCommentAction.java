package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QCommentVO;
import model.qna.QNADAO;

public class InsertQCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("view.do");
		forward.setRedirect(true);

		QCommentVO qcvo=new QCommentVO();
		QNADAO qdao=new QNADAO();
		qcvo.setQcContent(request.getParameter("qcContent"));
		qcvo.setqNum(Integer.parseInt(request.getParameter("qNum")));
		qcvo.setQcID(request.getParameter("qcID"));

		qdao.insertQComment(qcvo);
		return forward;
	}

}
