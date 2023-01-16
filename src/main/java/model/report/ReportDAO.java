package model.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;

public class ReportDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	final String INSERT_REPORT ="INSERT INTO REPORT (B_NUM, R_ID, R_TARGETID) VALUES(?, ?, ?)";
	final String DELETE_REPORT="delete from report where R_NUM =?";
	final String SELECTONE_REPORT="select * from board where exists(select b_num from report where board.b_num = report.b_num) and r_num = ?";
	final String SELECTALL_REPORT="select * from board where exists(select b_num from report where board.b_num = report.b_num)";

	public boolean insert(ReportVO rvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(INSERT_REPORT);
			pstmt.setInt(1, rvo.getbNum());
			pstmt.setString(2, rvo.getrId());
			pstmt.setString(3, rvo.getrTargetId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}
	
	public boolean delete(ReportVO rvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(DELETE_REPORT);
			pstmt.setInt(1, rvo.getbNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}
	public ReportVO selectOne(ReportVO rvo) {
		ReportVO data=null;
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTONE_REPORT);
			pstmt.setInt(1, rvo.getbNum());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				data=new ReportVO();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return data;
	}
	public ArrayList<ReportVO> selectAll(ReportVO rvo) {
		ArrayList<ReportVO> datas=new ArrayList<ReportVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTALL_REPORT);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				ReportVO data=new ReportVO();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return datas;
	}
}