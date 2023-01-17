package model.qna;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.qna.QNAVO;
import model.common.JDBCUtil;

public class QNADAO {
	Connection conn;
	PreparedStatement pstmt;
	
	final String INSERT_QNA="INSERT INTO QNA (Q_TITLE,Q_CONTENT,Q_ID,Q_DATE) VALUES(?,?,?,NOW())";
	final String UPDATE_QNA="UPDATE QNA SET Q_TITLE=?,Q_CONTENT=? WHERE Q_NUM=?";
	final String DELETE_QNA="DELETE FROM QNA WHERE Q_NUM=?";
	final String SELECTONE_QNA="SELECT * FROM QNA WHERE Q_NUM=?";
	final String SELECTALL_QNA="SELECT * FROM QNA ORDER BY Q_NUM DESC";

	public boolean insert(QNAVO qvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(INSERT_QNA);
			pstmt.setString(1, qvo.getqTitle());
			pstmt.setString(2, qvo.getqContent());
			pstmt.setString(3, qvo.getqId());

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
	public boolean update(QNAVO qvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(UPDATE_QNA);
			pstmt.setString(1, qvo.getqTitle());
			pstmt.setString(2, qvo.getqContent());
			pstmt.setInt(3, qvo.getqNum());
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
	public boolean delete(QNAVO qvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(DELETE_QNA);
			pstmt.setInt(1, qvo.getqNum());
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
	public QNAVO selectOne(QNAVO qvo) {
		QNAVO data=null;
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTONE_QNA);
			pstmt.setInt(1, qvo.getqNum());
			ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					data=new QNAVO();
					data.setqNum(rs.getInt("Q_NUM"));
					data.setqTitle(rs.getString("Q_TITLE"));
					data.setqContent(rs.getString("Q_CONTENT"));
					data.setqId(rs.getString("Q_ID"));
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return data;
	}
	public ArrayList<QNAVO> selectAll(QNAVO qvo) {
		ArrayList<QNAVO> datas=new ArrayList<QNAVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTALL_QNA);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				QNAVO data=new QNAVO();
				data.setqNum(rs.getInt("Q_NUM"));
				data.setqTitle(rs.getString("Q_TITLE"));
				data.setqContent(rs.getString("Q_CONTENT"));
				data.setqId(rs.getString("Q_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return datas;
	}
}