package model.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;


// 시퀀스 넘버 넘겨주세요!

public class QNADAO {
   Connection conn;
   PreparedStatement pstmt;

   final String INSERT_QNA = "INSERT INTO QNA (Q_TITLE, Q_CONTENT, Q_ID, Q_DATE) VALUES(?,?,?, NOW())";
   final String UPDATE_QNA = "UPDATE QNA SET Q_TITLE=?,Q_CONTENT=? WHERE Q_NUM=?";
   final String DELETE_QNA = "DELETE FROM QNA WHERE Q_NUM=?";
   final String SELECTONE_QNA = "SELECT RPAD(SUBSTR(Q.Q_ID, 1, 3), LENGTH(Q.Q_ID), '*') AS Q_ID, Q.Q_TITLE, Q.Q_CONTENT, Q.Q_DATE, Q.Q_CNT, COUNT(QC.QC_NUM) AS C_CNT FROM QNA Q, QCOMMENT QC WHERE Q.Q_NUM=? AND Q.Q_NUM = QC.Q_NUM";
   final String SELECTALL_QNA = "SELECT Q_NUM, Q_TITLE, Q_CNT, RPAD(SUBSTR(Q_ID, 1, 3), LENGTH(Q_ID), '*') AS Q_ID, Q_CONTENT, C_CNT FROM QNA ORDER BY Q_NUM DESC";

   final String INSERT_QCOMMENT = "INSERT INTO QCOMMENT (QC_ID, Q_NUM,QC_CONTENT,QC_GROUP, QC_DATE) VALUES(?,?,?,(SELECT COALESCE(MAX(QC_GROUP),0)+1 FROM QCOMMENT AS QC_GROUP),NOW())";
   final String INSERT_QCCOMMENT = "INSERT INTO QCOMMENT (QC_ID, Q_NUM,QC_CONTENT,QC_GROUP,QC_SQE, QC_DATE)\r\n"
         + "VALUES(?,?,?,?, (SELECT COALESCE(MAX(QCC.QC_SQE),0)+1\r\n"
         + "FROM QCOMMENT QCC GROUP BY QC_GROUP HAVING QC_GROUP = ?) , NOW() );";

   final String DELETE_QCOMMENT = "DELETE FROM QCOMMENT WHERE QC_GROUP = ?";
   final String DELETE_QCCOMMENT = "DELETE FROM QCOMMENT WHERE QC_NUM=?";
   final String SELECTALL_QCOMMENT = "SELECT QC_CONTENT, QC_GROUP, QC_DATE, RPAD(SUBSTR(QC_ID, 1, 3), LENGTH(QC_ID), '*') AS QC_ID FROM QCOMMENT WHERE Q_NUM=? AND QC_SQE=0";
   final String SELECTALL_QCCOMMENT = "SELECT QC_CONTENT,QC_GROUP, QC_DATE,QC_SQE, RPAD(SUBSTR(QC_ID, 1, 3), LENGTH(QC_ID), '*') AS QC_ID FROM QCOMMENT WHERE Q_NUM=? AND QC_SQE>0 AND QC_GROUP=?";

   public boolean insertQNA(QNAVO qvo) {
      conn = JDBCUtil.connect();
      try {
         pstmt = conn.prepareStatement(INSERT_QNA);
         pstmt.setString(1, qvo.getqTitle());
         pstmt.setString(2, qvo.getqContent());
         pstmt.setString(3, qvo.getqId());
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

   public boolean insertQComment(QCommentVO qcvo) {
      conn = JDBCUtil.connect();

      try {
         pstmt = conn.prepareStatement(INSERT_QCOMMENT);
         pstmt.setString(1, qcvo.getQcContent());
         pstmt.setInt(2, qcvo.getqNum());
         pstmt.setString(3, qcvo.getQcID());

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

   public boolean insertQCComment(QCCommentVO qccvo) {
      conn = JDBCUtil.connect();

      try {

         pstmt = conn.prepareStatement(INSERT_QCCOMMENT);

         pstmt.setString(1, qccvo.getQccID());
         pstmt.setInt(2, qccvo.getqNum());
         pstmt.setString(3, qccvo.getQccContent());
         pstmt.setInt(4, qccvo.getQccGroup());

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
      System.out.println(qccvo);
      return true;

   }

   public boolean updateQNA(QNAVO qvo) {
      conn = JDBCUtil.connect();
      try {
         pstmt = conn.prepareStatement(UPDATE_QNA);
         pstmt.setString(1, qvo.getqTitle());
         pstmt.setString(2, qvo.getqContent());
         pstmt.setInt(3, qvo.getqNum());
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

   public boolean deleteQNA(QNAVO qvo) {
      conn = JDBCUtil.connect();
      try {
         pstmt = conn.prepareStatement(DELETE_QNA);
         pstmt.setInt(1, qvo.getqNum());
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

   public ArrayList<QNASet> select(QNAVO qvo) {
      ArrayList<QNASet> datas = new ArrayList<QNASet>();

      conn = JDBCUtil.connect();

      try {
         pstmt = conn.prepareStatement(SELECTONE_QNA);
         pstmt.setInt(1, qvo.getqNum());
         ResultSet rs = pstmt.executeQuery();
         if (rs.next()) {
            QNASet qs = new QNASet();
            QNAVO qna = new QNAVO();

            qna.setqNum(qvo.getqNum());

            qna.setqId(rs.getString("Q_ID"));
            qna.setqTitle(rs.getString("Q_TITLE"));
            qna.setqContent(rs.getString("Q_CONTENT"));
            qna.setqDate(rs.getDate("Q_DATE"));
            // B_DATE
            qna.setqCnt(rs.getInt("Q_CNT"));
            qna.setcCnt(rs.getInt("C_CNT"));
            qs.setQna(qna);

            pstmt = conn.prepareStatement(SELECTALL_QCOMMENT);
            pstmt.setInt(1, qvo.getqNum());
            ResultSet rs2 = pstmt.executeQuery();

            ArrayList<QCommentVO> qcList = new ArrayList<QCommentVO>();
            while (rs2.next()) {
               QCommentVO qcomment = new QCommentVO();

               qcomment.setQcContent(rs2.getString("QC_CONTENT"));
               qcomment.setQcID(rs2.getString("QC_ID"));
               qcomment.setQcGroup(qvo.getQcvo().getQcGroup());
               qcomment.setqNum(qvo.getqNum());
               qcomment.setQcDate(rs2.getDate("QC_DATE"));
               qcList.add(qcomment);

               pstmt = conn.prepareStatement(SELECTALL_QCCOMMENT);
               pstmt.setInt(1, qvo.getqNum());
               pstmt.setInt(2, qvo.getQcvo().getQcGroup());
               ResultSet rs3 = pstmt.executeQuery();

               ArrayList<QCCommentVO> qccList = new ArrayList<QCCommentVO>();
               while (rs3.next()) {
                  QCCommentVO qccomment = new QCCommentVO();

                  qccomment.setQccContent(rs3.getString("QC_CONTENT"));
                  qccomment.setQccID(rs3.getString("QC_ID"));
                  qccomment.setqNum(qvo.getqNum());
                  qccomment.setQccSqe(rs3.getInt("QC_SQE"));
                  qccomment.setQccGroup(qvo.getQcvo().getQcGroup());
                  qccomment.setQccDate(rs3.getDate("QC_DATE"));
                  qccList.add(qccomment);
               }
               qcomment.setQccList(qccList);
            }
            qs.setQcList(qcList);

            datas.add(qs);
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      return datas;

   }

   public ArrayList<QNAVO> selectAllQNA() {

      ArrayList<QNAVO> qList = new ArrayList<>();
      conn = JDBCUtil.connect();
      try {
         pstmt = conn.prepareStatement(SELECTALL_QNA);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            QNAVO qna = new QNAVO();
            qna.setqNum(rs.getInt("Q_NUM"));
            qna.setqTitle(rs.getString("Q_TITLE"));
            qna.setqId(rs.getString("Q_ID"));
            qna.setqCnt(rs.getInt("Q_CNT"));
            qna.setcCnt(rs.getInt("C_CNT"));
            qList.add(qna);

         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      JDBCUtil.disconnect(conn, pstmt);
      return qList;
   }

   public boolean deleteQComment(QCommentVO qcvo) {
      conn = JDBCUtil.connect();
      try {
         System.out.println(qcvo.getQccvo().getQccSqe());
         if (qcvo.getQccvo().getQccSqe() >0) { // 대댓글 삭제
        	 System.out.println("탐");
        	 pstmt = conn.prepareStatement(DELETE_QCCOMMENT);
        	 pstmt.setInt(1, qcvo.getQcNum());
         } else {
        	 System.out.println("else");
        	 pstmt = conn.prepareStatement(DELETE_QCOMMENT);
        	 pstmt.setInt(1, qcvo.getQcGroup());
         }
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
   
   public static void main(String[] args) {
      QNADAO qdao = new QNADAO();
      QCommentVO qcvo = new QCommentVO();
      qcvo.getQccvo().setQccSqe(0);
//      bcvo.setBcNum(27);
      qcvo.setQcGroup(1);
      
      System.out.println(qdao.deleteQComment(qcvo));
   }

}