package model.qna;

import java.util.Date;

public class QCommentVO {

	private int qcNum;
	private String qcID;
	private String qcContent;
	private int qNum;
	private int qcGroup;
	private int qcSqe;
	private int qcCnt;
	private Date qcDate;
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
	public int getQcSqe() {
		return qcSqe;
	}
	public void setQcSqe(int qcSqe) {
		this.qcSqe = qcSqe;
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
		return "QCommentDAO [qcNum=" + qcNum + ", qcID=" + qcID + ", qcContent=" + qcContent + ", qNum=" + qNum
				+ ", qcGroup=" + qcGroup + ", qcSqe=" + qcSqe + ", qcCnt=" + qcCnt + ", qcDate=" + qcDate + "]";
	}
	
	
}
