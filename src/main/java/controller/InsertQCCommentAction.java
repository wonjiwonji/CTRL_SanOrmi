package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QCCommentVO;
import model.qna.QNADAO;

public class InsertQCCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/qView.do");
		forward.setRedirect(false);
		
		if(request.getParameter("qccID")=="") {
			forward.setPath("/loginNo.jsp");
			forward.setRedirect(false);

			return forward;
		}

		QCCommentVO qccvo=new QCCommentVO();
		QNADAO qdao=new QNADAO();
		
		qccvo.setQccID(request.getParameter("qccID"));
		qccvo.setqNum(Integer.parseInt(request.getParameter("qNum")));
		qccvo.setQccContent("ㄴ[답글]  " + request.getParameter("qccContent"));
		qccvo.setQccGroup(Integer.parseInt(request.getParameter("qccGroup")));

		qdao.insertQCComment(qccvo);
		return forward;
	}

}
