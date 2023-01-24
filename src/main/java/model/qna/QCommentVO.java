package model.qna;

import java.util.ArrayList;
import java.util.Date;

import model.board.BCCommentVO;

public class QCommentVO {
	private QCCommentVO qccvo; // QCCommentVO qccvo
	private int qcNum; // 댓글 번호
	private String qcID; // 댓글 작성자
	private String qcContent; // 댓글 내용
	private int qNum; // qna 게시글 번호
	private int qcGroup; // 댓글 그룹
	private Date qcDate; // 댓글 작성일
	private int qcSQE; // 댓글 시퀀스 (0)
	private ArrayList<QCCommentVO> qccList; // ArrayList <QCCommentVO> qccList

	public QCommentVO() {
		this.qccvo = new QCCommentVO();
	}

	public QCCommentVO getQccvo() {
		return qccvo;
	}

	public void setQccvo(QCCommentVO qccvo) {
		this.qccvo = qccvo;
	}

	public ArrayList<QCCommentVO> getQccList() {
		return qccList;
	}

	public void setQccList(ArrayList<QCCommentVO> qccList) {
		this.qccList = qccList;
	}

	public int getQcNum() {
		return qcNum;
	}

	public void setQcNum(int qcNum) {
		this.qcNum = qcNum;
	}

	public String getQcID() {
		return qcID;
	}

	public void setQcID(String qcID) {
		this.qcID = qcID;
	}

	public String getQcContent() {
		return qcContent;
	}

	public void setQcContent(String qcContent) {
		this.qcContent = qcContent;
	}

	public int getqNum() {
		return qNum;
	}

	public void setqNum(int qNum) {
		this.qNum = qNum;
	}

	public int getQcGroup() {
		return qcGroup;
	}

	public void setQcGroup(int qcGroup) {
		this.qcGroup = qcGroup;
	}

	public Date getQcDate() {
		return qcDate;
	}

	public void setQcDate(Date qcDate) {
		this.qcDate = qcDate;
	}

	public int getQcSQE() {
		return qcSQE;
	}

	public void setQcSQE(int qcSQE) {
		this.qcSQE = qcSQE;
	}

	@Override
	public String toString() {
		return "QCommentVO [qccvo=" + qccvo + ", qcNum=" + qcNum + ", qcID=" + qcID + ", qcContent=" + qcContent
				+ ", qNum=" + qNum + ", qcGroup=" + qcGroup + ", qcDate=" + qcDate + ", qcSQE=" + qcSQE + "]";
	}

}
