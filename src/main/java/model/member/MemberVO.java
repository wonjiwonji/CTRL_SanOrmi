package model.member;

import java.util.Date;

public class MemberVO {
	
	private String id;
	private String mPw;
	private String mEmail;
	private String mAddress;
	private String mName;
	private Date mDate;
	private boolean mBan;
	
	
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public boolean ismBan() {
		return mBan;
	}
	public void setmBan(boolean mBan) {
		this.mBan = mBan;
	}
	public Date getmDate() {
		return mDate;
	}
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmAddress() {
		return mAddress;
	}
	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", mPw=" + mPw + ", mEmail=" + mEmail + ", mAddress=" + mAddress + ", mName="
				+ mName + ", mDate=" + mDate + ", mBan=" + mBan + "]";
	}
	
	

	
}
