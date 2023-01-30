package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QNADAO;
import model.qna.QNASet;
import model.qna.QNAVO;

public class QCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/qComment.jsp");
		forward.setRedirect(false);
		
		QNADAO qdao=new QNADAO();
		QNAVO qvo=new QNAVO();
		ArrayList<QNASet> selectQs = new ArrayList<QNASet>();
		
		qvo.setqNum(Integer.parseInt(request.getParameter("qNum")));
		selectQs=qdao.selectOneQNA(qvo);
		
		request.getSession().setAttribute("selectQs", selectQs);

		return forward;
	}

}
