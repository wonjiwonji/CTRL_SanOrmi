package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.qna.QCCommentVO;
import model.qna.QCommentVO;
import model.qna.QNADAO;
import model.qna.QNAVO;

public class DeleteQCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/bView.do");
		forward.setRedirect(false);

		QCommentVO qcvo=new QCommentVO();
		QCCommentVO qccvo=new QCCommentVO();
		QNADAO qdao=new QNADAO();
		QNAVO qvo = new QNAVO();
		
		String qNumSample = request.getParameter("qNum");
		
			int bNum=Integer.parseInt(qNumSample);
			qvo.setqNum(bNum);
		
		if(request.getParameter("bccNum") == null) {
			qcvo.setQcNum(Integer.parseInt(request.getParameter("qcNum")));
			qcvo.setQcGroup(Integer.parseInt(request.getParameter("qcGroup")));
			qcvo.setqNum(bNum);
			qdao.deleteQComment(qcvo);
		} else {
			qccvo.setQccNum(Integer.parseInt(request.getParameter("qccNum")));
			qdao.deleteQCComment(qccvo);
		}
		
		return forward;
	}

}
