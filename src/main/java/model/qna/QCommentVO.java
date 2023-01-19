package model.qna;

import java.util.ArrayList;
import java.util.Date;

public class QCommentVO {
	private QCCommentVO qccvo;
	private int qcNum;
	private String qcID;
	private String qcContent;
	private int qNum;
	private int qcGroup;
	private int qcCnt;
	private Date qcDate;
	private ArrayList <QCCommentVO> qccList;

	
	
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
	public int getQcCnt() {
		return qcCnt;
	}
	public void setQcCnt(int qcCnt) {
		this.qcCnt = qcCnt;
	}
	public Date getQcDate() {
		return qcDate;
	}
	public void setQcDate(Date qcDate) {
		this.qcDate = qcDate;
	}
	@Override
	public String toString() {
		return "QCommentVO [qcNum=" + qcNum + ", qcID=" + qcID + ", qcContent=" + qcContent + ", qNum=" + qNum
				+ ", qcGroup=" + qcGroup + ", qcCnt=" + qcCnt + ", qcDate=" + qcDate + ", qccList=" + qccList + "]";
	}

	
	
}
