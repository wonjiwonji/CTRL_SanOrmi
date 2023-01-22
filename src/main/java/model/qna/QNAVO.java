package model.qna;

import java.util.Date;

public class QNAVO {
	private QCommentVO qcvo;
	private int qNum;
	private String qId;
	private String qTitle;
	private String qContent;
	private Date qDate;
	private int qCnt;
	private int cCnt;
	
	public QCommentVO getQcvo() {
		return qcvo;
	}
	public void setQcvo(QCommentVO qcvo) {
		this.qcvo = qcvo;
	}
	public int getqNum() {
		return qNum;
	}
	public void setqNum(int qNum) {
		this.qNum = qNum;
	}
	public String getqId() {
		return qId;
	}
	public void setqId(String qId) {
		this.qId = qId;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public Date getqDate() {
		return qDate;
	}
	public void setqDate(Date qDate) {
		this.qDate = qDate;
	}
	public int getqCnt() {
		return qCnt;
	}
	public void setqCnt(int qCnt) {
		this.qCnt = qCnt;
	}
	public int getcCnt() {
		return cCnt;
	}
	public void setcCnt(int cCnt) {
		this.cCnt = cCnt;
	}
	@Override
	public String toString() {
		return "QNAVO [qcvo=" + qcvo + ", qNum=" + qNum + ", qId=" + qId + ", qTitle=" + qTitle + ", qContent="
				+ qContent + ", qDate=" + qDate + ", qCnt=" + qCnt + ", cCnt=" + cCnt + "]";
	}
	
}
	
	
	
