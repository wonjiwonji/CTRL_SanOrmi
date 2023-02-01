package model.board;

import java.util.ArrayList;
import java.util.Date;

public class BCommentVO {

	private int bcNum; // 댓글 번호
	private String bcID; // 댓글 작성자
	private int bNum; // 게시글 번호
	private String bcContent; // 댓글 내용
	private int bcGroup; // 댓글 그룹
	private int bcSQE; // 댓글 시퀀스 (0)
	private Date bcDate; // 댓글 작성일
	private BCCommentVO bccvo; // BCCommentVO
	private ArrayList<BCCommentVO> bccList; // ArrayList<BCCommentVO>

	public BCommentVO() {
		this.bccvo = new BCCommentVO();
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

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public String getBcContent() {
		return bcContent;
	}

	public void setBcContent(String bcContent) {
		this.bcContent = bcContent;
	}

	public int getBcGroup() {
		return bcGroup;
	}

	public void setBcGroup(int bcGroup) {
		this.bcGroup = bcGroup;
	}

	public int getBcSQE() {
		return bcSQE;
	}

	public void setBcSQE(int bcSQE) {
		this.bcSQE = bcSQE;
	}

	public Date getBcDate() {
		return bcDate;
	}

	public void setBcDate(Date bcDate) {
		this.bcDate = bcDate;
	}

	public BCCommentVO getBccvo() {
		return bccvo;
	}

	public void setBccvo(BCCommentVO bccvo) {
		this.bccvo = bccvo;
	}

	public ArrayList<BCCommentVO> getBccList() {
		return bccList;
	}

	public void setBccList(ArrayList<BCCommentVO> bccList) {
		this.bccList = bccList;
	}

	@Override
	public String toString() {
		return "BCommentVO [bcNum=" + bcNum + ", bcID=" + bcID + ", bNum=" + bNum + ", bcContent=" + bcContent
				+ ", bcGroup=" + bcGroup + ", bcSQE=" + bcSQE + ", bcDate=" + bcDate + ", bccvo=" + bccvo + ", bccList="
				+ bccList + "]";
	}

}