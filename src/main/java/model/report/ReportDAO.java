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

	final String INSERT_REPORT = "INSERT INTO REPORT (B_NUM, R_ID, R_TARGETID) VALUES(?, ?, ?)";
	final String DELETE_REPORT = "delete from report where R_NUM =?";
	final String SELECTONE_REPORT = "SELECT R.R_NUM, R.R_TARGETID , R.R_ID , B.B_TITLE , B.B_CONTENT FROM REPORT R, BOARD B WHERE R.B_NUM = B.B_NUM AND R.R_ID = ?";
	final String SELECTALL_REPORT = "SELECT R.R_NUM, B.B_TITLE, R.R_ID, R.R_TARGETID, B.B_DATE FROM REPORT R, BOARD B WHERE R.B_NUM = B.B_NUM";

	public boolean insert(ReportVO rvo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT_REPORT);
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
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(DELETE_REPORT);
			pstmt.setInt(1, rvo.getrNum());
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
         pstmt.setString(1, rvo.getrId());
         ResultSet rs=pstmt.executeQuery();
         if(rs.next()) {
            data=new ReportVO();
            data.setrNum(rs.getInt("R.R_NUM"));
            data.setrTargetId(rs.getString("R.R_TARGITID"));
            data.setrId(rs.getString("R.R_ID"));
            data.setrTitle(rs.getString("B.B_TITLE"));
            data.setrTitle(rs.getString("B.B_CONTENT"));
            
            pstmt.executeUpdate();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      JDBCUtil.disconnect(conn, pstmt);
      return data;
   }

	public ArrayList<ReportVO> selectAll(ReportVO rvo) {
		ArrayList<ReportVO> datas = new ArrayList<ReportVO>();
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SELECTALL_REPORT);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportVO data = new ReportVO();
	            data.setrNum(rs.getInt("R.R_NUM"));
	            data.setrTitle(rs.getString("B.B_TITLE"));
	            data.setrId(rs.getString("R.R_ID"));
	            data.setrTargetId(rs.getString("R.R_TARGITID"));
	            data.setrTitle(rs.getString("B.B_DATE"));
	            
	            pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return datas;
	}
}