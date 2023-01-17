package model.qna;

import java.util.Date;

public class QNAVO {
	private int qNum;
	private String qId;
	private String qTitle;
	private String qContent;
	private int qCnt;
	
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
	public int getqCnt() {
		return qCnt;
	}
	public void setqCnt(int qCnt) {
		this.qCnt = qCnt;
	}
	
	@Override
	public String toString() {
		return "QNAVO [qNum=" + qNum + ", qId=" + qId + ", qTitle=" + qTitle + ", qContent=" + qContent + ", qCnt="
				+ qCnt + "]";
	}

}
