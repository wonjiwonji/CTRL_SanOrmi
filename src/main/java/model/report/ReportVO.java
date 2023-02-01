package model.report;

public class ReportVO {

	private int rNum; // 신고 번호 (PK)
	private int bNum; // 게시글 번호
	private String rId; // 신고자 아이디
	private String rTargetId; // 작성자 아이디

	private String rTitle; // 게시글 제목 (DB에는 없음)
	private String rContent; // 게시글 내용 (DB에는 없음)

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public String getrTargetId() {
		return rTargetId;
	}

	public void setrTargetId(String rTargetId) {
		this.rTargetId = rTargetId;
	}

	public String getrTitle() {
		return rTitle;
	}

	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	@Override
	public String toString() {
		return "ReportVO [rNum=" + rNum + ", bNum=" + bNum + ", rId=" + rId + ", rTargetId=" + rTargetId + ", rTitle="
				+ rTitle + ", rContent=" + rContent + "]";
	}

}