package model.board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	final String INSERT_BOARD="INSERT INTO BOARD (B_TITLE, B_CONTENT, B_ID, B_DATE) VALUES(?,?,?, NOW())";
	final String UPDATE_BOARD="UPDATE BOARD SET B_TITLE=?,B_CONTENT=? WHERE B_NUM=?";
	final String DELETE_BOARD="DELETE FROM BOARD WHERE B_NUM=?";
	final String SELECTONE_BOARD="SELECT RPAD(SUBSTR(B_ID, 1, 3), LENGTH(B_ID), '*') AS B_ID, B_TITLE, B_CONTENT, B_DATE, B_CNT, C_CNT FROM BOARD where b_num=? ORDER BY B_NUM DESC";
	final String SELECTALL_BOARD="SELECT B_NUM, RPAD(SUBSTR(B_ID, 1, 3), LENGTH(B_ID), '*') AS B_ID, B_TITLE, B_CONTENT, C_CNT FROM BOARD ORDER BY B_NUM DESC";
	final String INSERT_BCOMMENT="INSERT INTO BCOMMENT (BC_ID, B_NUM,BC_CONTENT,BC_GROUP,BC_SQE, BC_DATE) VALUES(?,?,?,?,?,NOW())";
	final String DELETE_BCOMMENT="DELETE FROM BCOMMENT WHERE BC_NUM=?";
	final String SELECTALL_BCOMMENT="SELECT * FROM COMMENT ORDER BY C_NUM DESC";

	public boolean insertBoard(BoardVO bvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(INSERT_BOARD);
			pstmt.setString(1, bvo.getbTitle());
			pstmt.setString(2, bvo.getbContent());
			pstmt.setString(3, bvo.getbId());
			int res=pstmt.executeUpdate();
			if(res<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}
	public boolean updateBoard(BoardVO bvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(UPDATE_BOARD);
			pstmt.setString(1, bvo.getbTitle());
			pstmt.setString(2, bvo.getbContent());
			pstmt.setInt(3, bvo.getbNum());
			int res=pstmt.executeUpdate();
			if(res<=0) {
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
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(DELETE_BOARD);
			pstmt.setInt(1, bvo.getbNum());
			int res=pstmt.executeUpdate();
			if(res<=0) {
				return false;
			}
			} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}
	public BoardVO selectOneBoard(BoardVO bvo) {
		BoardVO data=null;
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTONE_BOARD);
			pstmt.setInt(1, bvo.getbNum());
			ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					data=new BoardVO();
					data.setbId(rs.getString("B_ID"));
					data.setbTitle(rs.getString("B_TITLE"));
					data.setbContent(rs.getString("B_CONTENT"));
					data.setbDate(rs.getDate("B_DATE"));
					data.setbCnt(rs.getInt("B_CNT"));
					data.setcCnt(rs.getInt("C_CNT"));
					
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return data;
	}
	public ArrayList<BoardSet> selectAllBoard(BoardVO bvo) {
		ArrayList<BoardSet> datas=new ArrayList<BoardSet>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTALL_BOARD);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardSet bs = new BoardSet();
				BoardVO data=new BoardVO();
				ArrayList <BCommentVO> bcList = new ArrayList<BCommentVO>();
				data.setbNum(rs.getInt("B_NUM"));
				data.setbTitle(rs.getString("B_TITLE"));
				data.setbId(rs.getString("B_ID"));
				data.setbCnt(rs.getInt("B_CNT"));
				data.setcCnt(rs.getInt("C_CNT"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return datas;
	}
	public boolean insertBComment(BCommentVO bcvo) {
		conn=JDBCUtil.connect();
	
		try {
		if(bcvo.getBcGroup()>0) { // 대댓글이면
			
		}else {
			
		}
			pstmt=conn.prepareStatement(INSERT_BCOMMENT);
			pstmt.setString(1, bcvo.getBcContent());
			pstmt.setInt(2, bcvo.getbNum());
			pstmt.setString(3, bcvo.getBcID());
			pstmt.setInt(4, bcvo.getBcGroup());
			
			int res=pstmt.executeUpdate();	
			if(res<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}
	
	public boolean deleteBComment(BCommentVO bcvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(DELETE_BCOMMENT);
			pstmt.setInt(1, bcvo.getBcNum());
			int res=pstmt.executeUpdate();
			if(res<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}
	public BCommentVO selectOneBComment(BCommentVO bcvo) {
		BCommentVO data=null;
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTONE_BCOMMENT);
			pstmt.setInt(1, bcvo.getBcNum());
			ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					data=new BCommentVO();
					data.setBcNum(rs.getInt("BC_NUM"));
					data.setBcContent(rs.getString("BC_CONTENT"));
					data.setBcID(rs.getString("BC_ID"));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return data;
	}
	public ArrayList<BCommentVO> selectAllBComment(BCommentVO bcvo) {
		ArrayList<BCommentVO> datas=new ArrayList<BCommentVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTALL_BCOMMENT);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				BCommentVO data=new BCommentVO();
				data.setBcNum(rs.getInt("BC_NUM"));
				data.setBcContent(rs.getString("BC_CONTENT"));
				data.setBcID(rs.getString("BC_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return datas;
	}
}
	
	
}