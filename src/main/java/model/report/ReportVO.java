package model.report;

public class ReportVO {
	private int rNum;
	private int bNum;
	private String rId;
	private String rTargetId;
	private String rTitle;
	private String rContent;

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
		return "ReportVO [rNum=" + rNum + ", bNum=" + bNum + ", rId=" + rId + ", rTargetId=" + rTargetId + "]";
	}

}
