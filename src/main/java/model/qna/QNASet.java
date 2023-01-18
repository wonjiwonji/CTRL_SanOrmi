package model.qna;

import java.util.ArrayList;


public class QNASet {

	private QNAVO qna;
	private ArrayList<QCommentVO> qcList;
	public QNAVO getQna() {
		return qna;
	}
	public void setQna(QNAVO qna) {
		this.qna = qna;
	}
	public ArrayList<QCommentVO> getQcList() {
		return qcList;
	}
	public void setQcList(ArrayList<QCommentVO> qcList) {
		this.qcList = qcList;
	}
	
	@Override
	public String toString() {
		return "QNASet [qna=" + qna + ", qcList=" + qcList + "]";
	}
	
	
}
