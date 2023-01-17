package model.board;

import java.util.Date;

public class BoardVO {
	private int bNum;
	private String bId;
	private String bTitle;
	private String bContent;
	private String bReplyCnt;
	private Date bDate;
	private int bCnt;
	
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
	public String getbReplyCnt() {
		return bReplyCnt;
	}
	public void setbReplyCnt(String bReplyCnt) {
		this.bReplyCnt = bReplyCnt;
	}
	@Override
	public String toString() {
		return "BoardVO [bNum=" + bNum + ", bId=" + bId + ", bTitle=" + bTitle + ", bContent=" + bContent
				+ ", bReplyCnt=" + bReplyCnt + ", bDate=" + bDate + ", bCnt=" + bCnt + "]";
	}

}
