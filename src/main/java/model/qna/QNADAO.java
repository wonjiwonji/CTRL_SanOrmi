package model.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.board.BoardVO;
import model.common.JDBCUtil;

// 시퀀스 넘버 넘겨주세요!

public class QNADAO {
	Connection conn;
	PreparedStatement pstmt;
	// INSERT_QNA; QNA 게시글 등록 쿼리문
	final String INSERT_QNA = "INSERT INTO QNA (Q_TITLE, Q_CONTENT, Q_ID, Q_DATE) VALUES(?,?,?, NOW())";
	// UPDATE_QNA; QNA 게시글 수정 쿼리문
	final String UPDATE_QNA = "UPDATE QNA SET Q_TITLE=?,Q_CONTENT=? WHERE Q_NUM=?";
	
	// UPDATE_QNA_CNT; 게시글 조회수+ 쿼리문
		final String UPDATE_QNA_CNT = "UPDATE QNA SET Q_CNT = Q_CNT+1 WHERE Q_NUM=?";
	// DELETE_QNA; QNA 게시글 삭제 쿼리문
	final String DELETE_QNA = "DELETE FROM QNA WHERE Q_NUM=?";
	// SELECTONE_QNA; QNA 게시글 상세보기 쿼리문 (게시글 하나 클릭 시 나오는 정보)
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
	// SELECTONE ; 댓글 삭제를 위해 QC_NUM을 이용하여 QC_NUM, QC_GROUP, QC_SQE를 조회
	final String SELECTONE = "SELECT QC_NUM, QC_GROUP, QC_SQE FROM QCOMMENT WHERE QC_NUM = ?";

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
	public boolean insertQCComment(QCCommentVO qccvo) { // qccvo; qccId, qNum, qccContent, qccGroup 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결

		try {

			pstmt = conn.prepareStatement(INSERT_QCCOMMENT); // INSERT_QCCOMMENT; 대댓글 등록

			pstmt.setString(1, qccvo.getQccID()); // pstmt에 qccId 저장
			pstmt.setInt(2, qccvo.getqNum()); // pstmt에 qNum 저장
			pstmt.setString(3, qccvo.getQccContent()); // pstmt에 qccContent
			pstmt.setInt(4, qccvo.getQccGroup()); // pstmt에 qccGroup 저장

			int res = pstmt.executeUpdate(); // pstmt실행 결과 res에 저장
			if (res <= 0) { // res가 0보다 같거나 작다면 // 즉, pstmt 실행시키는 것을 실패했다면
				return false;// false 반환

			}
		} catch (SQLException e) { // 위 try문 실행 중 에러(SQL) 발생 시
			e.printStackTrace(); // 무슨 에러인지 출력
			return false; // false 반환
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return true; // true 반환

	}

	// updateQNA; 게시글 수정
	public boolean updateQNA(QNAVO qvo) { // qvo; qTitle, qContent, qNum 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(UPDATE_QNA); // UPDATE_QNA; 게시글 수정
			pstmt.setString(1, qvo.getqTitle()); // pstmt에 qTitle 저장
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

	// updateqCnt ; 게시글 조회수 변경
	public boolean updatebCnt(QNAVO qvo) { // qvo ; qNum 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(UPDATE_QNA_CNT); // UPDATE_QNA_CNT ; 게시글 조회수 변경
			pstmt.setInt(1, qvo.getqNum()); // 게시글 번호
			pstmt.executeUpdate(); // 실행
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return true;
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
			ResultSet rs = pstmt.executeQuery(); // 실행결과 rs에 저장
			if (rs.next()) { // 저장할 값이 남아있다면
				QNASet qs = new QNASet(); // QNASet 객체 qs 생성
				QNAVO qna = new QNAVO(); // QNAVO 객체 qna 생성

				qna.setqNum(qvo.getqNum()); // 게시글 번호 저장

				qna.setqId(rs.getString("Q_ID")); // 게시글 작성자 저장
				qna.setqTitle(rs.getString("Q_TITLE")); // 게시글 제목 저장
				qna.setqContent(rs.getString("Q_CONTENT")); // 게시글 내용 저장
				qna.setqDate(rs.getDate("Q_DATE")); // 게시글 작성일 저장
				// B_DATE
				qna.setqCnt(rs.getInt("Q_CNT")); // 게시글 조회수 저장
				qna.setcCnt(rs.getInt("C_CNT")); // 댓글 수 저장
				qs.setQna(qna); // setQna 해줌

				pstmt = conn.prepareStatement(SELECTALL_QCOMMENT); // SELECTALL_QCOMMENT; 댓글 전체 보기
				pstmt.setInt(1, qvo.getqNum()); // pstmt에 qNum 저장
				ResultSet rs2 = pstmt.executeQuery(); // 실행결과 rs2에 저장

				ArrayList<QCommentVO> qcList = new ArrayList<QCommentVO>(); // <QCommentVO> 타입의 ArrayList qcList 생성
				while (rs2.next()) { // 저장할 정보가 남아있는 동안
					QCommentVO qcomment = new QCommentVO();// QCommentVO 객체 qcomment 생성

					qcomment.setQcContent(rs2.getString("QC_CONTENT")); // 댓글 내용 저장
					qcomment.setQcID(rs2.getString("QC_ID")); // 댓글 작성자 저장
					qcomment.setQcGroup(qvo.getQcvo().getQcGroup()); // 댓글 그룹 저장
					qcomment.setqNum(qvo.getqNum()); // 게시글 번호 저장
					qcomment.setQcDate(rs2.getDate("QC_DATE")); // 댓글 작성일 저장
					qcList.add(qcomment); // qc리스트에 위의 값들을 add해줌

					pstmt = conn.prepareStatement(SELECTALL_QCCOMMENT); // SELECTALL_QCCOMMENT; 대댓글 전체보기
					pstmt.setInt(1, qvo.getqNum()); // pstmt에 qNum 저장
					pstmt.setInt(2, qvo.getQcvo().getQcGroup()); // pstmt에 qcGroup저장
					ResultSet rs3 = pstmt.executeQuery(); // 실행결과 rs3에 저장

					ArrayList<QCCommentVO> qccList = new ArrayList<QCCommentVO>(); // <QCCommentVO> 타입의 ArrayList
																					// qccList 생성
					while (rs3.next()) {
						QCCommentVO qccomment = new QCCommentVO(); // QCCommentVO 객체 qccomment생성

						qccomment.setQccContent(rs3.getString("QC_CONTENT")); // 대댓글 내용 저장
						qccomment.setQccID(rs3.getString("QC_ID")); // 대댓글 작성자 저장
						qccomment.setqNum(qvo.getqNum()); // 게시글 번호 저장 
						qccomment.setQccSqe(rs3.getInt("QC_SQE")); // 대댓글 시퀀스 저장
						qccomment.setQccGroup(qvo.getQcvo().getQcGroup()); // 대댓글 그룹 저장
						qccomment.setQccDate(rs3.getDate("QC_DATE")); // 대댓글 작성일 저장
						qccList.add(qccomment); // qccList에 위의 값들을 add해줌
					}
					qcomment.setQccList(qccList); // setQccList해줌
				}
				qs.setQcList(qcList); // setQcList해줌

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
			ResultSet rs = pstmt.executeQuery(); // 실행결과 rs에 저장
			while (rs.next()) { // 저장할 정보가 있는 동안
				QNAVO qna = new QNAVO(); // QNAVO의 객체 qna 생성
				qna.setqNum(rs.getInt("Q_NUM")); // 게시글 번호 저장
				qna.setqTitle(rs.getString("Q_TITLE")); // 게시글 제목 저장
				qna.setqId(rs.getString("Q_ID")); // 게시글 작성자 저장
				qna.setqCnt(rs.getInt("Q_CNT")); // 게시글 조회수 저장
				qna.setcCnt(rs.getInt("C_CNT")); // 댓글수 저장
				qList.add(qna); // ArrayList qList에 qna 값들 add해줌

			}
		} catch (SQLException e) { // 위 try문 실행 중 에러(SQL) 발생 시
			e.printStackTrace(); // 무슨 에러인지 출력
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return qList; // ArrayList qList 반환
	}

	// selectOne ; 댓글 삭제를 위해 QC_NUM을 이용하여 QC_NUM, QC_GROUP, QC_SQE를 조회
	public QCommentVO selectOne(QCommentVO qcvo) { // qcvo ; qcNum 필요
		QCommentVO data = null;
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(SELECTONE); // SELECTONE ; 댓글 삭제를 위해 QC_NUM을 이용하여 QC_NUM, QC_GROUP, QC_SQE를 조회
			pstmt.setInt(1, qcvo.getQcNum()); // 댓글 번호
			ResultSet rs = pstmt.executeQuery(); // pstmt 실행, 실행한 결과 rs에 저장
			if (rs.next()) { // rs에 정보가 있는 동안
				data = new QCommentVO(); // QCommentVO 새로운 객체 data 생성
				data.setQcNum(rs.getInt("QC_NUM")); // 댓글 번호
				data.setQcGroup(rs.getInt("QC_GROUP")); // 댓글 그룹
				data.setQcSQE(rs.getInt("QC_SQE")); // 댓글 시퀀스
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return data; // data 리턴
	}

	public boolean deleteQComment(QCommentVO qcvo) { // qcvo; selectOne을 실행한 return 값 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			if (qcvo.getQccvo().getQccSqe() > 0) {// qccvo에 있는 qccSqe가 0보다 크다면
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