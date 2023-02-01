package model.news;

public class NewsVO {
	private int nSqe; // 시퀀스
	private String nTitle; // 제목
	private String nContent; // 내용
	private String nDate; // 작성일

	public int getnSqe() {
		return nSqe;
	}

	public void setnSqe(int nSqe) {
		this.nSqe = nSqe;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public String getnDate() {
		return nDate;
	}

	public void setnDate(String nDate) {
		this.nDate = nDate;
	}

	@Override
	public String toString() {
		return "NewsVO [nSqe=" + nSqe + ", nTitle=" + nTitle + ", nContent=" + nContent + ", nDate=" + nDate + "]";
	}

}
