package model.comment;

import java.util.Date;

public class CommentVO {
	private int cNum;
	private String cID;
	private String cContent;
	private int bNum;
	private int qNum;
	private int cGroup;
	private int sqeNo;
	private Date cDate;
	
	public Date getcDate() {
		return cDate;
	}
	public int getqNum() {
		return qNum;
	}
	public void setqNum(int qNum) {
		this.qNum = qNum;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	public int getcNum() {
		return cNum;
	}
	public void setcNum(int cNum) {
		this.cNum = cNum;
	}
	public String getcID() {
		return cID;
	}
	public void setcID(String cID) {
		this.cID = cID;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public int getcGroup() {
		return cGroup;
	}
	public void setcGroup(int cGroup) {
		this.cGroup = cGroup;
	}
	public int getSqeNo() {
		return sqeNo;
	}
	public void setSqeNo(int sqeNo) {
		this.sqeNo = sqeNo;
	}
	@Override
	public String toString() {
		return "CommentVO [cNum=" + cNum + ", cID=" + cID + ", cContent=" + cContent + ", bNum=" + bNum + ", qNum="
				+ qNum + ", cGroup=" + cGroup + ", sqeNo=" + sqeNo + ", cDate=" + cDate + "]";
	}


}
