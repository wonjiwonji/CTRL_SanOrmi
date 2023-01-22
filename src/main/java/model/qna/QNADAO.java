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
	// INSERT_QNA; QNA 게시글 등록 쿼리문
	final String INSERT_QNA = "INSERT INTO QNA (Q_TITLE, Q_CONTENT, Q_ID, Q_DATE) VALUES(?,?,?, NOW())";
	// UPDATE_QNA; QNA 게시글 수정 쿼리문
	final String UPDATE_QNA = "UPDATE QNA SET Q_TITLE=?,Q_CONTENT=? WHERE Q_NUM=?";
	// DELETE_QNA; QNA 게시글 삭제 쿼리문
	final String DELETE_QNA = "DELETE FROM QNA WHERE Q_NUM=?";
	// SELECTONE_QNA;  QNA 게시글 상세보기 쿼리문 (게시글 하나 클릭 시 나오는 정보)
	final String SELECTONE_QNA = "SELECT RPAD(SUBSTR(Q.Q_ID, 1, 3), LENGTH(Q.Q_ID), '*') AS Q_ID, Q.Q_TITLE, Q.Q_CONTENT, Q.Q_DATE, Q.Q_CNT, COUNT(QC.QC_NUM) AS C_CNT FROM QNA Q, QCOMMENT QC WHERE Q.Q_NUM=? AND Q.Q_NUM = QC.Q_NUM";
	// SELECTALL_QNA; QNA 게시글 전체보기 쿼리문 ( 게시글 목록 시 나오는 정보 )
	final String SELECTALL_QNA = "SELECT Q_NUM, Q_TITLE, Q_CNT, RPAD(SUBSTR(Q_ID, 1, 3), LENGTH(Q_ID), '*') AS Q_ID, Q_CONTENT, C_CNT FROM QNA ORDER BY Q_NUM DESC";
	// INSERT_QCOMMENT; 댓글 등록 쿼리문
	final String INSERT_QCOMMENT = "INSERT INTO QCOMMENT (QC_ID, Q_NUM,QC_CONTENT,QC_GROUP, QC_DATE) VALUES(?,?,?,(SELECT COALESCE(MAX(QC_GROUP),0)+1 FROM QCOMMENT AS QC_GROUP),NOW())";
	// INSERT_QCCOMMENT; 대댓글 등록 쿼리문
	final String INSERT_QCCOMMENT = "INSERT INTO QCOMMENT (QC_ID, Q_NUM,QC_CONTENT,QC_GROUP,QC_SQE, QC_DATE)\r\n"
			+ "VALUES(?,?,?,?, (SELECT COALESCE(MAX(QCC.QC_SQE),0)+1\r\n"
			+ "FROM QCOMMENT QCC GROUP BY QC_GROUP HAVING QC_GROUP = ?) , NOW() );";

	 // DELETE_QCOMMENT; 댓글 삭제 쿼리문 ( 대댓글 까지 같이 삭제 )
	final String DELETE_QCOMMENT = "DELETE FROM QCOMMENT WHERE QC_GROUP = ?";
	// DELETE_QCCOMMENT; 대댓글 삭제 쿼리문 ( 대댓글만 삭제 )
	final String DELETE_QCCOMMENT = "DELETE FROM QCOMMENT WHERE QC_NUM=?";
	// SELECTALL_QCOMMENT; 댓글 전체보기 쿼리문 ( 게시글에 대한 댓글 전체 보기 )
	final String SELECTALL_QCOMMENT = "SELECT QC_CONTENT, QC_GROUP, QC_DATE, RPAD(SUBSTR(QC_ID, 1, 3), LENGTH(QC_ID), '*') AS QC_ID FROM QCOMMENT WHERE Q_NUM=? AND QC_SQE=0";
	 // SELECTALL_QCCOMMENT; 대댓글 전체보기 쿼리문 ( 게시글에 대한 대댓글 전체 보기 )
	final String SELECTALL_QCCOMMENT = "SELECT QC_CONTENT,QC_GROUP, QC_DATE,QC_SQE, RPAD(SUBSTR(QC_ID, 1, 3), LENGTH(QC_ID), '*') AS QC_ID FROM QCOMMENT WHERE Q_NUM=? AND QC_SQE>0 AND QC_GROUP=?";

	// insertQNA; QNA 게시글 등록 메서드
	public boolean insertQNA(QNAVO qvo) { // qvo; qTitle, qContent, qId 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(INSERT_QNA); // INSERT_QNA; QNA 게시글 등록
			// 인자로 받은 qvo에서 필요한 정보 추출
			pstmt.setString(1, qvo.getqTitle()); // pstmt에 qTitle 저장
			pstmt.setString(2, qvo.getqContent()); // pstmt에 qContent 저장
			pstmt.setString(3, qvo.getqId()); // pstmt에 qId 저장
			int res = pstmt.executeUpdate(); // pstmt실행 결과 res에 저장
			if (res <= 0) { // res가 0보다 같거나 작다면 // 즉, pstmt 실행시키는 것을 실패했다면
				return false; // false 반환
			}
		} catch (SQLException e) { // 위 try문 실행 중 에러(SQL) 발생 시
			e.printStackTrace(); // 무슨 에러인지 출력
			return false; // false 반환
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return true; // true 반환
	}
	
	// insertQComment; QNA 댓글 등록 메서드
	public boolean insertQComment(QCommentVO qcvo) { // qcvo; qcContent, qNum, qcId 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결

		try {
			pstmt = conn.prepareStatement(INSERT_QCOMMENT); // INSERT_QCOMMENT; 댓글 등록
			pstmt.setString(1, qcvo.getQcContent()); // pstmt에 qcContent 저장
			pstmt.setInt(2, qcvo.getqNum()); // pstmt에 qNum 저장
			pstmt.setString(3, qcvo.getQcID()); // pstmt에 qcId 저장

			int res = pstmt.executeUpdate(); // pstmt실행 결과 res에 저장
			if (res <= 0) { // res가 0보다 같거나 작다면 // 즉, pstmt 실행시키는 것을 실패했다면
				return false; // false 반환
			}
		} catch (SQLException e) { // 위 try문 실행 중 에러(SQL) 발생 시
			e.printStackTrace(); // 무슨 에러인지 출력
			return false; // false 반환
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return true; // true 반환
	}

	// insertQCComment; 대댓글 등록
	public boolean insertQCComment(QCCommentVO qccvo) {	// qccvo; qccId, qNum, qccContent, qccGroup 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결

		try {

			pstmt = conn.prepareStatement(INSERT_QCCOMMENT); // INSERT_QCCOMMENT; 대댓글 등록

			pstmt.setString(1, qccvo.getQccID()); // pstmt에 qccId 저장
			pstmt.setInt(2, qccvo.getqNum()); // pstmt에 qNum 저장
			pstmt.setString(3, qccvo.getQccContent()); // pstmt에 qccContent
			pstmt.setInt(4, qccvo.getQccGroup()); // pstmt에 qccGroup 저장

			int res = pstmt.executeUpdate();  // pstmt실행 결과 res에 저장
			if (res <= 0) {  // res가 0보다 같거나 작다면 // 즉, pstmt 실행시키는 것을 실패했다면
				return false;// false 반환

			}
		} catch (SQLException e) { // 위 try문 실행 중 에러(SQL) 발생 시
			e.printStackTrace(); // 무슨 에러인지 출력
			return false; // false 반환
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return true;  // true 반환

	}
	
	   // updateQNA; 게시글 수정
	public boolean updateQNA(QNAVO qvo) { // qvo; qTitle, qContent, qNum 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(UPDATE_QNA); // UPDATE_QNA; 게시글 수정
			pstmt.setString(1, qvo.getqTitle()); 	// pstmt에 qTitle 저장
			pstmt.setString(2, qvo.getqContent()); // pstmt에 qContent 저장
			pstmt.setInt(3, qvo.getqNum()); // pstmt에 qNum 저장
			int res = pstmt.executeUpdate(); // pstmt 실행 결과 res에 저장
			if (res <= 0) { // res가 0보다 같거나 작다면 // 즉, pstmt 실행시키는 것을 실패했다면
				return false; // false 반환
			}
		} catch (SQLException e) { // 위 try문 실행 중 에러(SQL) 발생 시
			e.printStackTrace(); // 무슨 에러인지 출력
			return false; // false 반환
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return true; // true 반환
	}

	// deleteQNA; 게시글 삭제
	public boolean deleteQNA(QNAVO qvo) { // qvo; qNum 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(DELETE_QNA); // DELETE_QNA; 게시글 삭제
			pstmt.setInt(1, qvo.getqNum()); // pstmt에 qNum 저장
			int res = pstmt.executeUpdate(); // pstmt 실행 결과 res에 저장 
			if (res <= 0) { // res가 0보다 같거나 작다면 // 즉, pstmt 실행시키는 것을 실패했다면
				return false; // false 반환
			}
		} catch (SQLException e) { // 위 try문 실행 중 에러(SQL) 발생 시
			e.printStackTrace(); // 무슨 에러인지 출력
			return false; // false 반환
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return true; // true 반환
	}

	// selectOneQNA; 게시글 상세보기
	public ArrayList<QNASet> selectOneQNA(QNAVO qvo) { // qvo; qNum 필요
		ArrayList<QNASet> datas = new ArrayList<QNASet>(); // <QNASet>타입의 ArrayList datas 생성 

		conn = JDBCUtil.connect(); // JDBCUtil 연결

		try {
			pstmt = conn.prepareStatement(SELECTONE_QNA); // SELECTONE_QNA; 게시글 상세보기
			pstmt.setInt(1, qvo.getqNum()); // pstmt에 qNum 저장
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				QNASet qs = new QNASet(); // QNASet 객체 qs 생성
				QNAVO qna = new QNAVO(); // QNAVO 객체 qna 생성

				qna.setqNum(qvo.getqNum());

				qna.setqId(rs.getString("Q_ID"));
				qna.setqTitle(rs.getString("Q_TITLE"));
				qna.setqContent(rs.getString("Q_CONTENT"));
				qna.setqDate(rs.getDate("Q_DATE"));
				// B_DATE
				qna.setqCnt(rs.getInt("Q_CNT"));
				qna.setcCnt(rs.getInt("C_CNT"));
				qs.setQna(qna);

				pstmt = conn.prepareStatement(SELECTALL_QCOMMENT); // SELECTALL_QCOMMENT; 댓글 전체 보기
				pstmt.setInt(1, qvo.getqNum()); // pstmt에 qNum 저장
				ResultSet rs2 = pstmt.executeQuery();

				ArrayList<QCommentVO> qcList = new ArrayList<QCommentVO>(); // <QCommentVO> 타입의 ArrayList  qcList 생성 
				while (rs2.next()) {
					QCommentVO qcomment = new QCommentVO();// QCommentVO 객체 qcomment 생성

					qcomment.setQcContent(rs2.getString("QC_CONTENT"));
					qcomment.setQcID(rs2.getString("QC_ID"));
					qcomment.setQcGroup(qvo.getQcvo().getQcGroup());
					qcomment.setqNum(qvo.getqNum());
					qcomment.setQcDate(rs2.getDate("QC_DATE"));
					qcList.add(qcomment);

					pstmt = conn.prepareStatement(SELECTALL_QCCOMMENT); // SELECTALL_QCCOMMENT; 대댓글 전체보기
					pstmt.setInt(1, qvo.getqNum()); // pstmt에 qNum 저장
					pstmt.setInt(2, qvo.getQcvo().getQcGroup()); // pstmt에 qcGroup저장
					ResultSet rs3 = pstmt.executeQuery();

					ArrayList<QCCommentVO> qccList = new ArrayList<QCCommentVO>(); // <QCCommentVO> 타입의 ArrayList  qccList 생성
					while (rs3.next()) {
						QCCommentVO qccomment = new QCCommentVO(); // QCCommentVO 객체 qccomment생성 

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

				datas.add(qs); // ArrayList datas에 qs 값들 add해줌
			}

		} catch (Exception e) { // 위 try문 실행 중 에러(SQL) 발생 시
			e.printStackTrace(); // 무슨 에러인지 출력
		}

		return datas; // ArrayList datas 반환

	}

	public ArrayList<QNAVO> selectAllQNA() {	

		ArrayList<QNAVO> qList = new ArrayList<>(); // <QNAVO> 타입의 ArrayList qList 생성
		conn = JDBCUtil.connect();// JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(SELECTALL_QNA);// SELECTALL_QNA; 게시글 전체 보기 
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				QNAVO qna = new QNAVO();
				qna.setqNum(rs.getInt("Q_NUM"));
				qna.setqTitle(rs.getString("Q_TITLE"));
				qna.setqId(rs.getString("Q_ID"));
				qna.setqCnt(rs.getInt("Q_CNT"));
				qna.setcCnt(rs.getInt("C_CNT"));
				qList.add(qna); // ArrayList qList에 qna 값들 add해줌

			}
		} catch (SQLException e) { // 위 try문 실행 중 에러(SQL) 발생 시
			e.printStackTrace(); // 무슨 에러인지 출력
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return qList; // ArrayList qList 반환
	}

	public boolean deleteQComment(QCommentVO qcvo) { // qcvo; qcNum, qcGroup 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			if (qcvo.getQccvo().getQccSqe() >0) {// qccvo에 있는 qccSqe가 0보다 크다면 
				pstmt = conn.prepareStatement(DELETE_QCCOMMENT); // DELETE_QCCOMMENT; 대댓글 삭제
				pstmt.setInt(1, qcvo.getQcNum());// pstmt에 qcNum 저장
			} else { // qccSqe가 0보다 크지 않다면
				pstmt = conn.prepareStatement(DELETE_QCOMMENT);// DELETE_QCOMMENT; 댓글 삭제 
				pstmt.setInt(1, qcvo.getQcGroup());// pstmt에 qcGroup 저장
			}
			int res = pstmt.executeUpdate(); // pstmt실행 결과 res에 저장
			if (res <= 0) { // res가 0보다 같거나 작다면 // 즉, pstmt 실행시키는 것을 실패했다면
				return false; // false 반환
			}
		} catch (SQLException e) { // 위 try문 실행 중 에러(SQL) 발생 시
			e.printStackTrace(); // 무슨 에러인지 출력
			return false; // false 반환
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return true; // true 반환
	}

	

}