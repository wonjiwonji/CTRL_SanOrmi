package model.board;

import java.util.Date;

public class BCCommentVO {
	private int bccNum;
	private String bccID;
	private String bccContent;
	private int bNum;
	private int bccGroup;
	private int bccSqe;
	private Date bccDate;

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

	public String getBccContent() {
		return bccContent;
	}

	public void setBccContent(String bccContent) {
		this.bccContent = bccContent;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
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
		return "BCCommentVO [bccNum=" + bccNum + ", bccID=" + bccID + ", bccContent=" + bccContent + ", bNum=" + bNum
				+ ", bccGroup=" + bccGroup + ", bccSqe=" + bccSqe + ", bccDate=" + bccDate + "]";
	}


}
