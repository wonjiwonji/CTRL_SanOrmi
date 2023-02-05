package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QCommentVO;
import model.qna.QNADAO;
import model.qna.QNAVO;

public class InsertQCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/qView.do");
		forward.setRedirect(false);

		if(request.getParameter("qcID")=="") {
			forward.setPath("/loginNo.jsp");
			forward.setRedirect(false);

			return forward;
		}
		
		QCommentVO qcvo=new QCommentVO();
		QNAVO qvo=new QNAVO();
		QNADAO qdao=new QNADAO();

		qcvo.setQcID(request.getParameter("qcID"));
		qcvo.setQcContent(request.getParameter("qcContent"));
		qcvo.setqNum(Integer.parseInt(request.getParameter("qNum")));
		
		qvo.setqNum(Integer.parseInt(request.getParameter("qNum")));
		
		qdao.insertQComment(qcvo);
		return forward;
	}
}
