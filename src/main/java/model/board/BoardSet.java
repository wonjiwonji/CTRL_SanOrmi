package model.board;

import java.util.ArrayList;

public class BoardSet {

	private BoardVO board; // BoardVO
	private ArrayList<BCommentVO> bcList; // ArrayList<BCommentVO>

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
		return "BoardSet [board=" + board + ", bcList=" + bcList + "]";
	}

}