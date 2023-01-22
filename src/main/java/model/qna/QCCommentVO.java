package model.qna;

import java.util.Date;

public class QCCommentVO {

	private int qccNum;	// 대댓글 번호
	private String qccID;	// 대댓글 작성자
	private String qccContent; // 대댓글 내용
	private int qNum; // qna 게시글 번호
	private int qccGroup; // 대댓글 그룹
	private int qccSqe;	// 대댓글 시퀀스
	private Date qccDate; // 대댓글 작성일
	
	public int getQccSqe() { 
		return qccSqe;
	}
	public void setQccSqe(int qccSqe) {
		this.qccSqe = qccSqe;
	}
	public int getQccNum() {
		return qccNum;
	}
	public void setQccNum(int qccNum) {
		this.qccNum = qccNum;
	}
	public String getQccID() {
		return qccID;
	}
	public void setQccID(String qccID) {
		this.qccID = qccID;
	}
	public String getQccContent() {
		return qccContent;
	}
	public void setQccContent(String qccContent) {
		this.qccContent = qccContent;
	}
	public int getqNum() {
		return qNum;
	}
	public void setqNum(int qNum) {
		this.qNum = qNum;
	}
	public int getQccGroup() {
		return qccGroup;
	}
	public void setQccGroup(int qccGroup) {
		this.qccGroup = qccGroup;
	}
	public Date getQccDate() {
		return qccDate;
	}
	public void setQccDate(Date qccDate) {
		this.qccDate = qccDate;
	}
	@Override
	public String toString() {
		return "QCCommentVO [qccSqe=" + qccSqe + ", qccNum=" + qccNum + ", qccID=" + qccID + ", qccContent="
				+ qccContent + ", qNum=" + qNum + ", qccGroup=" + qccGroup + ", qccDate=" + qccDate + "]";
	}
	
	
}
