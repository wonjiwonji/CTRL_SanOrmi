package model.board;

import java.util.Date;

public class BoardVO {
	private BCommentVO bcvo; // BCommentVO 
	private int bNum; // 게시글 번호
	private String bId; // 게시글 작성자
	private String bTitle; // 게시글 제목
	private String bContent; // 게시글 내용
	private Date bDate; // 게시글 작성일
	private int bCnt; // 게시글 조회수
	private int cCnt; // 댓글 수 

	
	public BoardVO() {
		this.bcvo = new BCommentVO();
	}

	public BCommentVO getBcvo() {
		return bcvo;
	}

	public void setBcvo(BCommentVO bcvo) {
		this.bcvo = bcvo;
	}

	public int getcCnt() {
		return cCnt;
	}

	public void setcCnt(int cCnt) {
		this.cCnt = cCnt;
	}

	public int getbCnt() {
		return bCnt;
	}

	public void setbCnt(int bCnt) {
		this.bCnt = bCnt;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	@Override
	public String toString() {
		return "BoardVO [bcvo=" + bcvo + ", bNum=" + bNum + ", bId=" + bId + ", bTitle=" + bTitle + ", bContent="
				+ bContent + ", bDate=" + bDate + ", bCnt=" + bCnt + ", cCnt=" + cCnt + "]";
	}




}