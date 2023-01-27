package model.news;

public class NewsVO {
	private String nTitle;
	private String nContent;
	private String nDate;

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
		return "NewsVO [nTitle=" + nTitle + ", nContent=" + nContent + ", nDate=" + nDate + "]";
	}

}
