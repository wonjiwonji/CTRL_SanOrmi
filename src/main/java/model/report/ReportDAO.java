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

	// C
	// INSERT_REPORT ; 신고 보드에 추가
	final String INSERT_REPORT = "INSERT INTO REPORT (B_NUM, R_ID, R_TARGETID) VALUES(?, ?, ?)";

	// D
	// DELETE_REPORT ; 신고 게시보드 게시글 삭제
	final String DELETE_REPORT = "delete from report where R_NUM =?";

	// R
	// SELECTONE_REPORT; 신고글 상세보기
	final String SELECTONE_REPORT = "SELECT R.R_NUM, R.R_TARGETID , R.R_ID , B.B_TITLE , B.B_CONTENT FROM REPORT R, BOARD B \r\n"
			+ "WHERE R.B_NUM = B.B_NUM AND R.R_ID = ?";
	// SELECTALL_REPORT; 신고글 전체보기
	final String SELECTALL_REPORT = "SELECT R.R_NUM, B.B_TITLE, R.R_ID, R.R_TARGETID, B.B_DATE FROM REPORT R, BOARD B WHERE R.B_NUM = B.B_NUM";
	// SELECT_REPORT_CHECK; 신고 중복 검사
	final String SELECT_REPORT_CHECK = "SELECT * FROM report WHERE R_ID =? AND B_NUM =?";

	
	// C
	// insert ; 신고 정보 추가
	public boolean insert(ReportVO rvo) { // rvo ; 게시글 번호, 신고자 아이디, 작성자 아이디 필요

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(INSERT_REPORT); // INSERT_REPORT ; 신고 정보 추가

			pstmt.setInt(1, rvo.getbNum()); // 게시글 번호
			pstmt.setString(2, rvo.getrId()); // 신고자 아이디
			pstmt.setString(3, rvo.getrTargetId()); // 작성자 아이디

			pstmt.executeUpdate(); // 실행

		} catch (SQLException e) { // 예외처리
			e.printStackTrace();
			return false; // 실패 시 false 반환
		}
		JDBCUtil.disconnect(conn, pstmt); // DB 연결 해제
		return true; // 성공 시 true 반환
	}

	// D
	// delete ; 신고 기록 삭제
	public boolean delete(ReportVO rvo) { // rvo ; 신고 번호 필요

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(DELETE_REPORT); // DELETE_REPORT 신고 기록 삭제

			pstmt.setInt(1, rvo.getrNum()); // 신고 번호

			pstmt.executeUpdate(); // 실행

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}

	// R
	// selectOne ; 신고 기록 상세 보기
	public ReportVO selectOne(ReportVO rvo) { // rvo ; 신고 번호 필요

		ReportVO data = null; // 신고 기록 담을 ReportVO 객체 data 생성

		conn = JDBCUtil.connect(); // JDBC 연결

		try {
			pstmt = conn.prepareStatement(SELECTONE_REPORT); // SELECTONE_REPORT ; 신고 기록 상세 보기

			pstmt.setString(1, rvo.getrId()); // 신고 번호

			ResultSet rs = pstmt.executeQuery(); // pstmt 실행 결과 rs에 저장

			if (rs.next()) { // 기록이 있을 때 까지
				data = new ReportVO(); // ReportVO 타입 data 생성

				data.setrNum(rs.getInt("R.R_NUM")); // 신고 번호
				data.setrTargetId(rs.getString("R.R_TARGETID")); // 작성자 아이디
				data.setrId(rs.getString("R.R_ID")); // 신고자 아이디
				data.setrTitle(rs.getString("B.B_TITLE")); // 게시글 제목
				data.setrTitle(rs.getString("B.B_CONTENT")); // 게시글 내용
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBC 연결 해제
		return data;
	}

	// selectAll ; 신고 전체 목록 보기
	public ArrayList<ReportVO> selectAll(ReportVO rvo) {

		ArrayList<ReportVO> datas = new ArrayList<ReportVO>(); // ReportVO 타입의 배열리스트 datas 생성

		conn = JDBCUtil.connect(); // JDBC 연결

		try {
			pstmt = conn.prepareStatement(SELECTALL_REPORT); // SELECTALL_REPORT ; 신고 기록 전체 보기

			ResultSet rs = pstmt.executeQuery(); // pstmt 실행 결과 rs에 저장

			while (rs.next()) { // 읽을 값이 남아있는 동안
				ReportVO data = new ReportVO(); // ReportVO 객체 data 생성

				data.setrNum(rs.getInt("R.R_NUM")); // 신고 번호
				data.setrTitle(rs.getString("B.B_TITLE")); // 게시글 제목
				data.setrId(rs.getString("R.R_ID")); // 신고자 아이디
				data.setrTargetId(rs.getString("R.R_TARGETID")); // 작성자 아이디
				data.setrTitle(rs.getString("B.B_DATE")); // 작성 일자
				
				datas.add(data);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return datas;
	}

	// selectReportCheck; 신고 중복 검사
	public boolean selectReportCheck(ReportVO rvo) { // rvo; rId, bNum 필요

		conn = JDBCUtil.connect(); // JDBC 연결

		try {
			pstmt = conn.prepareStatement(SELECT_REPORT_CHECK); // SELECT_REPORT_CHECK ; 신고 중복 검사

			pstmt.setString(1, rvo.getrId()); // 신고자 아이디
			pstmt.setInt(2, rvo.getbNum()); // 게시글 번호

			ResultSet rs = pstmt.executeQuery(); // pstmt 실행 결과 rs에 저장
			if (rs.next()) { // 검색된 값이 있으면
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBC 연결 해제
		return true;
	}

}