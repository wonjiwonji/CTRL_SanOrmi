package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;

public class MemberDAO {
   Connection conn;
   PreparedStatement pstmt;
   
   final String INSERT_MEMBER="INSERT INTO MEMBER (ID,M_PW,M_NAME,M_ADDRESS,M_EMAIL,M_REGDATE) VALUES(?,?,?,?,?,NOW())";
   final String UPDATE_MEMBER="UPDATE MEMBER SET M_PW=? M_NAME=? M_EMAIL=? M_ADDRESS=? WHERE ID=?";
   final String UPDATE_MEMBER_BANCNT = "UPDATE MEMBER SET M_BANCNT = ? WHERE ID=?";
   final String UPDATE_MEMBER_BOARDCNT = "UPDATE MEMBER SET M_BOARDCNT = ? WHERE ID=?";   
   final String DELETE_MEMBER="DELETE FROM MEMBER WHERE ID=? ";
   final String SELECTONE_MEMBER="SELECT * FROM MEMBER WHERE ID=?";
   final String LOGIN_MEMBER="SELECT * FROM MEMBER WHERE ID=? AND PW=?";
   final String SELECTALL_MEMBER="SELECT ID M_NAME M_EMAIL M_ADDRESS M_REGDATE M_BCNT FROM MEMBER";

   public boolean insertMember(MemberVO mvo) {
      conn=JDBCUtil.connect();
      try {
         pstmt=conn.prepareStatement(INSERT_MEMBER);
         pstmt.setString(1, mvo.getId());
         pstmt.setString(2, mvo.getmPw());
         pstmt.setString(3, mvo.getmName());
         if(mvo.getmAddress()==null) {
                  pstmt.setString(4, "NULL");
               }else {
                  pstmt.setString(4,mvo.getmAddress());
                  
               }
         pstmt.setString(5, mvo.getmEmail());
         
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
   public boolean updateMember(MemberVO mvo) {
      conn=JDBCUtil.connect();
      try {
         pstmt=conn.prepareStatement(UPDATE_MEMBER);
         pstmt.setString(1, mvo.getmPw());
         pstmt.setString(2, mvo.getmName());
         pstmt.setString(3,mvo.getmEmail());
         pstmt.setString(4,mvo.getmAddress());
         pstmt.setString(5,mvo.getId());
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      JDBCUtil.disconnect(conn, pstmt);
      return true;
   }
   
   public boolean updateMBoardCnt(MemberVO mvo) {
      conn=JDBCUtil.connect();
      try {
         pstmt=conn.prepareStatement(UPDATE_MEMBER_BOARDCNT);
         pstmt.setString(2,mvo.getId());
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      JDBCUtil.disconnect(conn, pstmt);
      return true;
   }
   
   public boolean updateBanCnt(MemberVO mvo) {
      conn=JDBCUtil.connect();
      try {
         pstmt=conn.prepareStatement(UPDATE_MEMBER_BANCNT);
         pstmt.setString(2,mvo.getId());
         
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      JDBCUtil.disconnect(conn, pstmt);
      return true;
   }

   
   public boolean deleteMember(MemberVO mvo) {
      conn=JDBCUtil.connect();
      try {
         pstmt=conn.prepareStatement(DELETE_MEMBER);
         pstmt.setString(1, mvo.getId());
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      JDBCUtil.disconnect(conn, pstmt);
      return true;
   }
   public MemberVO selectOneMember(MemberVO mvo) {
      MemberVO data=null;
      conn=JDBCUtil.connect();
      try {
         pstmt=conn.prepareStatement(SELECTONE_MEMBER);
         pstmt.setString(1, mvo.getId());
         ResultSet rs=pstmt.executeQuery();
         if(rs.next()) {
            data=new MemberVO();
            data.setId(rs.getString("ID"));
            data.setmName(rs.getString("M_NAME"));
            data.setmEmail(rs.getString("M_EMAIL"));
            data.setmAddress(rs.getString("M_ADDRESS"));
            data.setmDate(rs.getDate("M_REGDATE"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      JDBCUtil.disconnect(conn, pstmt);
      return data;
   }
   
   public MemberVO loginMember(MemberVO mvo) {
	      MemberVO data=null;
	      conn=JDBCUtil.connect();
	      try {
	            pstmt=conn.prepareStatement(LOGIN_MEMBER);
	            pstmt.setString(1, mvo.getId());
	            pstmt.setString(2, mvo.getmPw());
	      
	         ResultSet rs=pstmt.executeQuery();
	         if(rs.next()) {
	            data=new MemberVO();
	            data.setId(rs.getString("ID"));
	            data.setmPw(rs.getString("M_PW"));
	            data.setmName(rs.getString("M_NAME"));
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      JDBCUtil.disconnect(conn, pstmt);
	      return data;
	   }
   
   
   public ArrayList<MemberVO> selectAllMember(MemberVO mvo) {
      ArrayList<MemberVO> datas=new ArrayList<MemberVO>();
      conn=JDBCUtil.connect();
      try {
         pstmt=conn.prepareStatement(SELECTALL_MEMBER);
         ResultSet rs=pstmt.executeQuery();
         while(rs.next()) {
            MemberVO data=new MemberVO();
            data.setId(rs.getString("ID"));
            data.setmEmail(rs.getString("M_EMAIL"));
            data.setmDate(rs.getDate("M_REGDATE"));
            data.setmName(rs.getString("M_NAME"));
            datas.add(data);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      JDBCUtil.disconnect(conn, pstmt);
      return datas;
   }
}