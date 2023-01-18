package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;

public class BCommentDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	final String INSERT_BCOMMENT="INSERT INTO BCOMMENT (BC_ID, B_NUM,BC_CONTENT,BC_GROUP,BC_SQE, BC_DATE) VALUES(?,?,?,?,?,NOW())";
	final String DELETE_BCOMMENT="DELETE FROM BCOMMENT WHERE BC_NUM=?";
	final String SELECTONE_BCOMMENT="SELECT * FROM COMMENT WHERE C_NUM=?";
	final String SELECTALL_BCOMMENT="SELECT * FROM COMMENT ORDER BY C_NUM DESC";

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