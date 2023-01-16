package model.comment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.board.BoardVO;
import model.common.JDBCUtil;

public class CommentDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	final String INSERT_COMMENT="INSERT INTO COMMENT (C_CONTENT,C_ID,C_GROUP,C_DATE) VALUES(?,?,?,?)";
	final String DELETE_COMMENT="DELETE FROM COMMENT WHERE C_NUM=?";
	final String SELECTONE_COMMENT="SELECT * FROM COMMENT WHERE C_NUM=?";
	final String SELECTALL_COMMENT="SELECT * FROM COMMENT ORDER BY C_NUM DESC";

	public boolean insert(CommentVO cvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(INSERT_COMMENT);
			pstmt.setString(1, cvo.getcContent());
			pstmt.setString(2, cvo.getcID());
			pstmt.setInt(3, cvo.getcGroup());
			pstmt.setDate(4, (Date) cvo.getcDate());
			
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
	
	public boolean delete(CommentVO cvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(DELETE_COMMENT);
			pstmt.setInt(1, cvo.getcNum());
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
	public CommentVO selectOne(CommentVO cvo) {
		CommentVO data=null;
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTONE_COMMENT);
			pstmt.setInt(1, cvo.getcNum());
			ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					data=new CommentVO();
					data.setcNum(rs.getInt("C_NUM"));
					data.setcContent(rs.getString("C_CONTENT"));
					data.setcID(rs.getString("C_ID"));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return data;
	}
	public ArrayList<CommentVO> selectAll(CommentVO cvo) {
		ArrayList<CommentVO> datas=new ArrayList<CommentVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTALL_COMMENT);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				CommentVO data=new CommentVO();
				data.setcNum(rs.getInt("C_NUM"));
				data.setcContent(rs.getString("C_CONTENT"));
				data.setcID(rs.getString("C_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return datas;
	}
}