package model.board;

import java.util.ArrayList;

public class BoardSet {
	private BCommentVO bc;
	public BCommentVO getBc() {
		return bc;
	}

	public void setBc(BCommentVO bc) {
		this.bc = bc;
	}

	private BoardVO board;
	private ArrayList<BCommentVO> bcList;

	public BoardVO getBoard() {
		return board;
	}

	public void setBoard(BoardVO board) {
		this.board = board;
	}

	public ArrayList<BCommentVO> getBcList() {
		return bcList;
	}

	public void setBcList(ArrayList<BCommentVO> bcList) {
		this.bcList = bcList;
	}

	@Override
	public String toString() {
		return "BoardSet [bc=" + bc + ", board=" + board + ", bcList=" + bcList + "]";
	}


}
