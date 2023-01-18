package model.board;

import java.util.ArrayList;
import java.util.Date;

public class BCommentVO {
	private int bcNum;
	private String bcID;
	private String bcContent;
	private int bNum;
	private int bcGroup;
	private int bcCnt;
	private Date bcDate;
	private ArrayList <BCCommentVO> bccList;

	public ArrayList<BCCommentVO> getBccList() {
		return bccList;
	}

	public void setBccList(ArrayList<BCCommentVO> bccList) {
		this.bccList = bccList;
	}

	public int getBcNum() {
		return bcNum;
	}

	public void setBcNum(int bcNum) {
		this.bcNum = bcNum;
	}

	public String getBcID() {
		return bcID;
	}

	public void setBcID(String bcID) {
		this.bcID = bcID;
	}

	public String getBcContent() {
		return bcContent;
	}

	public void setBcContent(String bcContent) {
		this.bcContent = bcContent;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public int getBcGroup() {
		return bcGroup;
	}

	public void setBcGroup(int bcGroup) {
		this.bcGroup = bcGroup;
	}

	public int getBcCnt() {
		return bcCnt;
	}

	public void setBcCnt(int bcCnt) {
		this.bcCnt = bcCnt;
	}

	public Date getBcDate() {
		return bcDate;
	}

	public void setBcDate(Date bcDate) {
		this.bcDate = bcDate;
	}

	@Override
	public String toString() {
		return "BCommentVO [bcNum=" + bcNum + ", bcID=" + bcID + ", bcContent=" + bcContent + ", bNum=" + bNum
				+ ", bcGroup=" + bcGroup + ", bcCnt=" + bcCnt + ", bcDate=" + bcDate + ", bccList=" + bccList + "]";
	}


}
