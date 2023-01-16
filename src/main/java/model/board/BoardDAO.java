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
	
	final String INSERT_BOARD="INSERT INTO BOARD (B_TITLE,B_CONTENT,B_ID,B_DATE) VALUES(?,?,?,?)";
	final String UPDATE_BOARD="UPDATE BOARD SET B_TITLE=?,B_CONTENT=? WHERE B_NUM=?";
	final String DELETE_BOARD="DELETE FROM BOARD WHERE B_NUM=?";
	final String SELECTONE_BOARD="SELECT B_NUM, RPAD(SUBSTR(B_ID, 1, 3), LENGTH(B_ID), '*') AS B_ID, B_TITLE, B_CONTENT, B_DATE FROM BOARD where b_num=? ORDER BY B_NUM DESC";
	final String SELECTALL_BOARD="select b_num, RPAD(SUBSTR(b_id, 1, 3), LENGTH(b_id), '*') as b_id, b_title, b_content, b_date from board order by b_num desc";

	public boolean insert(BoardVO bvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(INSERT_BOARD);
			pstmt.setString(1, bvo.getbTitle());
			pstmt.setString(2, bvo.getbContent());
			pstmt.setString(3, bvo.getbId());
			pstmt.setDate(4, (Date) bvo.getbDate());
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
	public boolean update(BoardVO bvo) {
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
	public boolean delete(BoardVO bvo) {
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
	public BoardVO selectOne(BoardVO bvo) {
		BoardVO data=null;
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTONE_BOARD);
			pstmt.setInt(1, bvo.getbNum());
			ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					data=new BoardVO();
					data.setbNum(rs.getInt("B_NUM"));
					data.setbTitle(rs.getString("B_TITLE"));
					data.setbContent(rs.getString("B_CONTENT"));
					data.setbId(rs.getString("B_ID"));
					data.setbDate(rs.getDate("B_DATE"));
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return data;
	}
	public ArrayList<BoardVO> selectAll(BoardVO bvo) {
		ArrayList<BoardVO> datas=new ArrayList<BoardVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTALL_BOARD);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardVO data=new BoardVO();
				data.setbNum(rs.getInt("B_NUM"));
				data.setbTitle(rs.getString("B_TITLE"));
				data.setbContent(rs.getString("B_CONTENT"));
				data.setbId(rs.getString("B_ID"));
				data.setbDate(rs.getDate("B_DATE"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return datas;
	}
}