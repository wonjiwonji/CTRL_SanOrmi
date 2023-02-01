package model.board;

import java.util.Date;

public class BCCommentVO {
	
	private int bccNum; // 대댓글 번호
	private String bccID; // 대댓글 작성자
	private int bNum; // 게시글 번호
	private String bccContent; // 대댓글 내용
	private int bccGroup; // 대댓글 그룹
	private int bccSqe; // 대댓글 시퀀스
	private Date bccDate; // 대댓글 작성일

	public int getBccNum() {
		return bccNum;
	}

	public void setBccNum(int bccNum) {
		this.bccNum = bccNum;
	}

	public String getBccID() {
		return bccID;
	}

	public void setBccID(String bccID) {
		this.bccID = bccID;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public String getBccContent() {
		return bccContent;
	}

	public void setBccContent(String bccContent) {
		this.bccContent = bccContent;
	}

	public int getBccGroup() {
		return bccGroup;
	}

	public void setBccGroup(int bccGroup) {
		this.bccGroup = bccGroup;
	}

	public int getBccSqe() {
		return bccSqe;
	}

	public void setBccSqe(int bccSqe) {
		this.bccSqe = bccSqe;
	}

	public Date getBccDate() {
		return bccDate;
	}

	public void setBccDate(Date bccDate) {
		this.bccDate = bccDate;
	}

	@Override
	public String toString() {
		return "BCCommentVO [bccNum=" + bccNum + ", bccID=" + bccID + ", bNum=" + bNum + ", bccContent=" + bccContent
				+ ", bccGroup=" + bccGroup + ", bccSqe=" + bccSqe + ", bccDate=" + bccDate + "]";
	}

}