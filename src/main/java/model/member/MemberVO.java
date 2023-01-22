package model.member;

import java.util.Date;

public class MemberVO {

	private String id; // 아이디 (PK)
	private String mPw; // 비밀번호
	private String mEmail; // 이메일
	private String mAddress; // 주소
	private String mName; // 이름
	private Date mDate; // 가입 일자
	private int mBanCnt; // 벤 당한 횟수
	private int mBoardCnt; // 게시글 작성 횟수 (잔여 게시글 기준)

	// getter, setter
	public int getmBanCnt() {
		return mBanCnt;
	}

	public void setmBanCnt(int mBanCnt) {
		this.mBanCnt = mBanCnt;
	}

	public int getmBoardCnt() {
		return mBoardCnt;
	}

	public void setmBoardCnt(int mBoardCnt) {
		this.mBoardCnt = mBoardCnt;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
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

	// toString
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", mPw=" + mPw + ", mEmail=" + mEmail + ", mAddress=" + mAddress + ", mName="
				+ mName + ", mDate=" + mDate + ", mBanCnt=" + mBanCnt + ", mBoardCnt=" + mBoardCnt + "]";
	}

}
