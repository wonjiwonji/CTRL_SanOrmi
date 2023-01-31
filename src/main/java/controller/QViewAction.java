package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QCCommentVO;
import model.qna.QCommentVO;
import model.qna.QNADAO;
import model.qna.QNASet;
import model.qna.QNAVO;

public class QViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/bView.jsp");
		forward.setRedirect(false);
		
		QNADAO qdao = new QNADAO();
		QNAVO qvo = new QNAVO();
		ArrayList<QNASet> qbvo = new ArrayList<QNASet>();
		ArrayList<QCommentVO> qcList = new ArrayList<QCommentVO>();
		ArrayList<QCCommentVO> qccListSample = new ArrayList<QCCommentVO>();
		ArrayList<ArrayList<QCCommentVO>> qccList = new ArrayList<ArrayList<QCCommentVO>>();
		
		qvo.setqNum(Integer.parseInt(request.getParameter("qNum")));
		
		qbvo=qdao.selectOneQNA(qvo);
		qdao.updatebCnt(qvo);
		
		request.getSession().setAttribute("qbvo", qbvo);
		
		if(!qbvo.isEmpty()) {
			qcList=qbvo.get(0).getQcList();
			request.getSession().setAttribute("qcList", qcList);
			
			if(!qcList.isEmpty()) {
				for(int i=0; i<qcList.size(); i++) {
					qccListSample=qcList.get(i).getQccList();
					qccList.add(qccListSample);
				}
				request.getSession().setAttribute("qccList", qccList);
			}
		}
		
		return forward;
	}

}
