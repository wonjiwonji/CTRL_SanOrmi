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
	
	final String INSERT_BOARD="INSERT INTO BOARD (B_TITLE, B_CONTENT, B_ID, B_DATE) VALUES(?,?,?, NOW())";
	final String UPDATE_BOARD="UPDATE BOARD SET B_TITLE=?,B_CONTENT=? WHERE B_NUM=?";
	final String DELETE_BOARD="DELETE FROM BOARD WHERE B_NUM=?";
	final String SELECTONE_BOARD="SELECT RPAD(SUBSTR(B_ID, 1, 3), LENGTH(B_ID), '*') AS B_ID, B_TITLE, B_CONTENT, B_DATE, B_CNT, C_CNT FROM BOARD WHERE B_NUM=? ORDER BY B_NUM DESC";
	final String SELECTALL_BOARD="SELECT B_NUM, B_TITLE, B_CNT, RPAD(SUBSTR(B_ID, 1, 3), LENGTH(B_ID), '*') AS B_ID, B_CONTENT, C_CNT FROM BOARD ORDER BY B_NUM DESC";
	final String INSERT_BCOMMENT="INSERT INTO BCOMMENT (BC_ID, B_NUM,BC_CONTENT,BC_GROUP,BC_SQE, BC_DATE) VALUES(?,?,?,?,?,NOW())";
	final String DELETE_BCOMMENT="DELETE FROM BCOMMENT WHERE BC_NUM=?";
	final String SELECTALL_BCOMMENT="SELECT BC_CONTENT, BC_DATE, RPAD(SUBSTR(BC_ID, 1, 3), LENGTH(BC_ID), '*') AS BC_ID FROM BCOMMENT WHERE B_NUM=? AND BC_SQE=0";
	final String SELECTALL_BCCOMMENT="SELECT BC_CONTENT, BC_DATE, RPAD(SUBSTR(BC_ID, 1, 3), LENGTH(BC_ID), '*') AS BC_ID FROM BCOMMENT WHERE B_NUM=? AND BC_SQE>0 AND BC_GROUP=?";

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
	public ArrayList<BoardSet> selectOneBoard(BoardVO bvo) {
		BoardVO data=null;
		ArrayList<BoardSet> datas=new ArrayList<BoardSet>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTONE_BOARD);
//			pstmt.setInt(1, bvo.getBoard().getbNum());
			pstmt.setInt(1, bvo.getbNum());
			ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					BoardSet bs = new BoardSet();
					data=new BoardVO();
					ArrayList <BCommentVO> bcList = new ArrayList<BCommentVO>();
					ArrayList <BCCommentVO> bccList = new ArrayList<BCCommentVO>();
					data.setbId(rs.getString("B_ID"));
					data.setbTitle(rs.getString("B_TITLE"));
					data.setbContent(rs.getString("B_CONTENT"));
					data.setbDate(rs.getDate("B_DATE"));
					data.setbCnt(rs.getInt("B_CNT"));
					data.setcCnt(rs.getInt("C_CNT"));
					bs.setBoard(data);
					
					
					pstmt=conn.prepareStatement(SELECTALL_BCOMMENT);
//					pstmt.setInt(1, bvo.getBoard().getbNum());
					pstmt.setInt(1, bvo.getbNum());
					ResultSet rs2=pstmt.executeQuery();
					while(rs2.next()) {
						BCommentVO bcomment=new BCommentVO();
						bcomment.setBcContent(rs2.getString("BC_CONTENT"));
						bcomment.setBcDate(rs2.getDate("BC_DATE"));
						bcomment.setBcID(rs2.getString("BC_ID"));
						bcList.add(bcomment);
						
//						
//						pstmt=conn.prepareStatement(SELECTALL_BCCOMMENT);
//						pstmt.setInt(1, bvo.getBoard().getbNum());
//						pstmt.setInt(1, bcomment.getBcGroup());						
//						ResultSet rs3=pstmt.executeQuery();
//						while(rs3.next()) {
//						BCCommentVO bccomment=new BCCommentVO();
//						bccomment.setBccContent(rs3.getString("BC_CONTENT"));
//						bccomment.setBccDate(rs3.getDate("BC_DATE"));
//						bccomment.setBccID(rs3.getString("B_ID"));
//						bccList.add(bccomment);
//						}				
				}
					bs.setBcList(bcList);
					
					
					datas.add(bs);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return datas;
	}
//	public ArrayList<BoardVO> selectAllBoard(BoardVO bvo) {
//		conn=JDBCUtil.connect();
//		try {
//			pstmt=conn.prepareStatement(SELECTALL_BOARD);
//			ResultSet rs=pstmt.executeQuery();
//			while(rs.next()) {
//				BoardVO board=new BoardVO();
//				board.setbNum(rs.getInt("B_NUM"));
//				board.setbTitle(rs.getString("B_TITLE"));
//				board.setbId(rs.getString("B_ID"));
//				board.setbCnt(rs.getInt("B_CNT"));
//				board.setcCnt(rs.getInt("C_CNT"));
//				bs.setBoard(board);
//				
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		JDBCUtil.disconnect(conn, pstmt);
//		return board;
//	}
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
			pstmt=conn.prepareStatement(SELECTALL_BCOMMENT);
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
	public static void main(String[] args) {
		BoardDAO b = new BoardDAO();
		BoardVO a = new BoardVO();
		System.out.println("ss");
		a.setbNum(1);
		System.out.println("dd");
//		a.getBc().setBcGroup(1);
		
		System.out.println(b.selectOneBoard(a)); 
		
	}
}