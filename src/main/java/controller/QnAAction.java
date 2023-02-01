package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QNADAO;
import model.qna.QNAVO;

public class QnAAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/QnA.jsp");
		forward.setRedirect(false);
		
		ArrayList<QNAVO> qList = new ArrayList<>();
		QNADAO qdao=new QNADAO();

		qList=qdao.selectAllQNA(null);
		
		request.getSession().setAttribute("qList", qList);
		
		return forward;
	}

}
