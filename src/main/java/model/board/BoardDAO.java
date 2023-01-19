package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;

	final String INSERT_BOARD = "INSERT INTO BOARD (B_TITLE, B_CONTENT, B_ID, B_DATE) VALUES(?,?,?, NOW())";
	final String UPDATE_BOARD = "UPDATE BOARD SET B_TITLE=?,B_CONTENT=? WHERE B_NUM=?";
	final String DELETE_BOARD = "DELETE FROM BOARD WHERE B_NUM=?";
	final String SELECTONE_BOARD = "SELECT RPAD(SUBSTR(B.B_ID, 1, 3), LENGTH(B.B_ID), '*') AS B_ID, B.B_TITLE, B.B_CONTENT, B.B_DATE, B.B_CNT, COUNT(BC.BC_NUM) AS C_CNT FROM BOARD B, BCOMMENT BC WHERE B.B_NUM=? AND B.B_NUM = BC.B_NUM";
	final String SELECTALL_BOARD = "SELECT B_NUM, B_TITLE, B_CNT, RPAD(SUBSTR(B_ID, 1, 3), LENGTH(B_ID), '*') AS B_ID, B_CONTENT, C_CNT FROM BOARD ORDER BY B_NUM DESC";

	final String INSERT_BCOMMENT = "INSERT INTO BCOMMENT (BC_ID, B_NUM,BC_CONTENT,BC_GROUP, BC_DATE) VALUES(?,?,?,(SELECT COALESCE(MAX(BC_GROUP),0)+1 FROM BCOMMENT AS BC_GROUP),NOW())";
	final String INSERT_BCCOMMENT = "INSERT INTO BCOMMENT (BC_ID, B_NUM,BC_CONTENT,BC_GROUP,BC_SQE, BC_DATE)\r\n"
			+ "VALUES(?,?,?,?, (SELECT COALESCE(MAX(BCC.BC_SQE),0)+1\r\n"
			+ "FROM BCOMMENT BCC GROUP BY BC_GROUP HAVING BC_GROUP = 0) , NOW() );";

	final String DELETE_BCOMMENT = "DELETE FROM BCOMMENT WHERE BC_NUM=?";
	final String SELECTALL_BCOMMENT = "SELECT BC_CONTENT, BC_GROUP, BC_DATE, RPAD(SUBSTR(BC_ID, 1, 3), LENGTH(BC_ID), '*') AS BC_ID FROM BCOMMENT WHERE B_NUM=? AND BC_SQE=0";
	final String SELECTALL_BCCOMMENT = "SELECT BC_CONTENT,BC_GROUP, BC_DATE,BC_SQE, RPAD(SUBSTR(BC_ID, 1, 3), LENGTH(BC_ID), '*') AS BC_ID FROM BCOMMENT WHERE B_NUM=? AND BC_SQE>0 AND BC_GROUP=?";

	public boolean insertBoard(BoardVO bvo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT_BOARD);
			pstmt.setString(1, bvo.getbTitle());
			pstmt.setString(2, bvo.getbContent());
			pstmt.setString(3, bvo.getbId());
			int res = pstmt.executeUpdate();
			if (res <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}

	public boolean insertBComment(BCommentVO bcvo) {
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(INSERT_BCOMMENT);
			pstmt.setString(1, bcvo.getBcContent());
			pstmt.setInt(2, bcvo.getbNum());
			pstmt.setString(3, bcvo.getBcID());

			int res = pstmt.executeUpdate();
			if (res <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}

	public boolean insertBCComment(BCCommentVO bccvo) {
		conn = JDBCUtil.connect();

		try {

			pstmt = conn.prepareStatement(INSERT_BCCOMMENT);

			pstmt.setString(1, bccvo.getBccID());
			pstmt.setInt(2, bccvo.getbNum());
			pstmt.setString(3, bccvo.getBccContent());
			pstmt.setInt(4, bccvo.getBccGroup());

			int res = pstmt.executeUpdate(); // 실행된 값을 int로 반환 row 로 반환
			if (res <= 0) { // 실행 안됨
				System.out.println("실패!");
				return false;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("입력실패!");
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		System.out.println("성공!");
		System.out.println(bccvo);
		return true;

	}

	public boolean updateBoard(BoardVO bvo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(UPDATE_BOARD);
			pstmt.setString(1, bvo.getbTitle());
			pstmt.setString(2, bvo.getbContent());
			pstmt.setInt(3, bvo.getbNum());
			int res = pstmt.executeUpdate();
			if (res <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}

	public boolean deleteBoard(BoardVO bvo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(DELETE_BOARD);
			pstmt.setInt(1, bvo.getbNum());
			int res = pstmt.executeUpdate();
			if (res <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}

	public ArrayList<BoardSet> select(BoardVO bvo) {
		ArrayList<BoardSet> datas = new ArrayList<BoardSet>();

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SELECTONE_BOARD);
			pstmt.setInt(1, bvo.getbNum());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				BoardSet bs = new BoardSet();
				BoardVO board = new BoardVO();

				board.setbNum(bvo.getbNum());

				board.setbId(rs.getString("B_ID"));
				board.setbTitle(rs.getString("B_TITLE"));
				board.setbContent(rs.getString("B_CONTENT"));
				board.setbDate(rs.getDate("B_DATE"));
				// B_DATE
				board.setbCnt(rs.getInt("B_CNT"));
				board.setcCnt(rs.getInt("C_CNT"));
				bs.setBoard(board);

				pstmt = conn.prepareStatement(SELECTALL_BCOMMENT);
				pstmt.setInt(1, bvo.getbNum());
				ResultSet rs2 = pstmt.executeQuery();

				ArrayList<BCommentVO> bcList = new ArrayList<BCommentVO>();
				while (rs2.next()) {
					BCommentVO bcomment = new BCommentVO();

					bcomment.setBcContent(rs2.getString("BC_CONTENT"));
					bcomment.setBcID(rs2.getString("BC_ID"));
					bcomment.setBcGroup(bvo.getBcvo().getBcGroup());
					bcomment.setbNum(bvo.getbNum());
					bcomment.setBcDate(rs2.getDate("BC_DATE"));
					bcList.add(bcomment);

					pstmt = conn.prepareStatement(SELECTALL_BCCOMMENT);
					pstmt.setInt(1, bvo.getbNum());
					pstmt.setInt(2, bvo.getBcvo().getBcGroup());
					ResultSet rs3 = pstmt.executeQuery();

					ArrayList<BCCommentVO> bccList = new ArrayList<BCCommentVO>();
					while (rs3.next()) {
						BCCommentVO bccomment = new BCCommentVO();

						bccomment.setBccContent(rs3.getString("BC_CONTENT"));
						bccomment.setBccID(rs3.getString("BC_ID"));
						bccomment.setbNum(bvo.getbNum());
						bccomment.setBccSqe(rs3.getInt("BC_SQE"));
						bccomment.setBccGroup(bvo.getBcvo().getBcGroup());
						bccomment.setBccDate(rs3.getDate("BC_DATE"));
						bccList.add(bccomment);
					}
					bcomment.setBccList(bccList);
				}
				bs.setBcList(bcList);

				datas.add(bs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return datas;

	}

	public ArrayList<BoardVO> selectAllBoard() {

		ArrayList<BoardVO> bList = new ArrayList<>();
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SELECTALL_BOARD);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setbNum(rs.getInt("B_NUM"));
				board.setbTitle(rs.getString("B_TITLE"));
				board.setbId(rs.getString("B_ID"));
				board.setbCnt(rs.getInt("B_CNT"));
				board.setcCnt(rs.getInt("C_CNT"));
				bList.add(board);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return bList;
	}

	public boolean deleteBComment(BCommentVO bcvo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(DELETE_BCOMMENT);
			pstmt.setInt(1, bcvo.getBcNum());
			int res = pstmt.executeUpdate();
			if (res <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}

}
