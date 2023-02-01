package model.qna;

import java.util.Date;

public class QNAVO {
	
	private int qNum; // 게시글 번호
	private String qId; // 게시글 작성자
	private String qTitle; // 게시글 제목
	private String qContent; // 게시글 내용
	private int qCnt; // 게시글 조회수
	private int cCnt; // 댓글 수
	private Date qDate; // 게시글 작성일
	private QCommentVO qcvo; // QCommentVO

	public QNAVO() {
		this.qcvo = new QCommentVO();
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

	public Date getqDate() {
		return qDate;
	}

	public void setqDate(Date qDate) {
		this.qDate = qDate;
	}

	public QCommentVO getQcvo() {
		return qcvo;
	}

	public void setQcvo(QCommentVO qcvo) {
		this.qcvo = qcvo;
	}

	@Override
	public String toString() {
		return "QNAVO [qNum=" + qNum + ", qId=" + qId + ", qTitle=" + qTitle + ", qContent=" + qContent + ", qCnt="
				+ qCnt + ", cCnt=" + cCnt + ", qDate=" + qDate + ", qcvo=" + qcvo + "]";
	}

}