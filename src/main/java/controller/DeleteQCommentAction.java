package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QCommentVO;
import model.qna.QNADAO;

public class DeleteQCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("view.do");
		forward.setRedirect(true);

		QCommentVO qcvo=new QCommentVO();
		QNADAO qdao=new QNADAO();
		
		qcvo.setQcNum(Integer.parseInt(request.getParameter("qcNum")));
//		qcvo.setQcGroup(Integer.parseInt(request.getParameter("qcGroup")));

		QCommentVO dqcvo=qdao.selectOne(qcvo);
		qdao.deleteQComment(dqcvo);
		return forward;
	}

}
