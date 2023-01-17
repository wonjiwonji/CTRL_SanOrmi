package model.comment;

import java.util.Date;

public class CommentVO {
	private int cNum;
	private String cID;
	private String cContent;
	private int bNum;
	private int qNum;
	private int cGroup;
	private int cSqe;
	private int cCnt;
	
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
	public int getqNum() {
		return qNum;
	}
	public void setqNum(int qNum) {
		this.qNum = qNum;
	}
	public int getcGroup() {
		return cGroup;
	}
	public void setcGroup(int cGroup) {
		this.cGroup = cGroup;
	}
	public int getcSqe() {
		return cSqe;
	}
	public void setcSqe(int cSqe) {
		this.cSqe = cSqe;
	}
	public int getcCnt() {
		return cCnt;
	}
	public void setcCnt(int cCnt) {
		this.cCnt = cCnt;
	}
	@Override
	public String toString() {
		return "CommentVO [cNum=" + cNum + ", cID=" + cID + ", cContent=" + cContent + ", bNum=" + bNum + ", qNum="
				+ qNum + ", cGroup=" + cGroup + ", cSqe=" + cSqe + ", cCnt=" + cCnt + "]";
	}
	
	


}
