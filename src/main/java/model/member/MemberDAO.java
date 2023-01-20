package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.board.BoardDAO;
import model.board.BoardSet;
import model.board.BoardVO;
import model.common.JDBCUtil;

public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;

	// INSERT_MEMBER ; 회원 가입
	final String INSERT_MEMBER = "INSERT INTO MEMBER (ID,M_PW,M_NAME,M_ADDRESS,M_EMAIL,M_REGDATE) VALUES(?,?,?,?,?,NOW())";
	// INSERT_KAKAO_MEMBER ; 카카오 회원 가입
	final String INSERT_KAKAO_MEMBER = "INSERT INTO MEMBER (ID,M_PW,M_NAME,M_EMAIL,M_REGDATE) VALUES(?,'KAKAO',?,?,NOW())";
	// INSERT_NAVER_MEMBER ; 네이버 회원 가입
	final String INSERT_NAVER_MEMBER = "INSERT INTO MEMBER (ID,M_PW,M_NAME,M_EMAIL,M_REGDATE) VALUES(?,'NAVER',?,?,NOW())";
	// UPDATE_MEMBER ; 회원 정보 변경
	final String UPDATE_MEMBER = "UPDATE MEMBER SET M_PW=? M_NAME=? M_EMAIL=? M_ADDRESS=? WHERE ID=?";
	// UPDATE_MEMBER_BANCNT ; BAN당할 경우 횟수 ++
	final String UPDATE_MEMBER_BANCNT = "UPDATE MEMBER SET M_BANCNT = ? WHERE ID=?";
	// UPDATE_MEMBER_BOARDCNT ; 본인이 쓴 게시글 갯수 추가 -> 커뮤니티왕!!!
	final String UPDATE_MEMBER_BOARDCNT = "UPDATE MEMBER SET M_BOARDCNT = ? WHERE ID=?";
	// DELETE_MEMBER ; 회원 삭제
	final String DELETE_MEMBER = "DELETE FROM MEMBER WHERE ID=? ";
	// LOGIN_MEMBER ; 로그인
	final String LOGIN_MEMBER = "SELECT * FROM MEMBER WHERE ID=? AND M_PW=?";
	// SELECTONE_MEMBER ; 회원 찾기
	final String SELECTONE_MEMBER = "SELECT * FROM MEMBER WHERE ID=?";
	// SELECTALL_MEMBER ; 회원 출력 -> 관리자(회원관리) 페이지
	final String SELECTALL_MEMBER = "SELECT ID M_NAME M_EMAIL M_ADDRESS M_REGDATE M_BCNT FROM MEMBER";

	// insertMember ; 회원 가입
	public boolean insertMember(MemberVO mvo) { // mvo ; Id, mPw, mName, mAddress(선택), mEmail 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(INSERT_MEMBER); // INSERT_MEMBER ; 회원 가입
			// 인자로 받은 mvo에서 필요한 정보 추출
			pstmt.setString(1, mvo.getId()); // pstmt에 id 저장
			pstmt.setString(2, mvo.getmPw()); // pstmt에 mPw 저장
			pstmt.setString(3, mvo.getmName()); // pstmt에 mName 저장
			if (mvo.getmAddress() == null) { // 만약 받은 mvo에 저장된 mAddress정보가 없으면 (주소는 선택사항이기 때문)
				pstmt.setString(4, ""); // 빈 값을 저장
			} else { // 받은 mAddress 정보가 있다면
				pstmt.setString(4, mvo.getmAddress()); // pstmt에 mAddress 저장
			}
			pstmt.setString(5, mvo.getmEmail()); // pstmt에 mEmail 저장

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

	// insertMember ; 회원 가입
	public boolean insertKakaoMember(MemberVO mvo) { // mvo ; Id, mPw, mName, mEmail 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(INSERT_KAKAO_MEMBER); // INSERT_MEMBER ; 회원 가입
			// 인자로 받은 mvo에서 필요한 정보 추출
			pstmt.setString(1, mvo.getId()); // pstmt에 id 저장
			pstmt.setString(2, mvo.getmName()); // pstmt에 mName 저장
			pstmt.setString(3, mvo.getmEmail()); // pstmt에 mEmail 저장

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

	// insertMember ; 회원 가입
	public boolean insertNaverMember(MemberVO mvo) { // mvo ; Id, mPw, mName, mEmail 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(INSERT_NAVER_MEMBER); // INSERT_MEMBER ; 회원 가입
			// 인자로 받은 mvo에서 필요한 정보 추출
			pstmt.setString(1, mvo.getId()); // pstmt에 id 저장
			pstmt.setString(2, mvo.getmName()); // pstmt에 mName 저장
			pstmt.setString(3, mvo.getmEmail()); // pstmt에 mEmail 저장

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

	// updateMember ; 회원 정보 변경
	public boolean updateMember(MemberVO mvo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(UPDATE_MEMBER); // UPDATE_MEMBER ; 회원 정보 변경
			pstmt.setString(1, mvo.getmPw()); // 비밀번호
			pstmt.setString(2, mvo.getmName()); // 이름
			pstmt.setString(3, mvo.getmEmail()); // 이메일
			pstmt.setString(4, mvo.getmAddress()); // 주소 (API를 사용해 입력받은 값)
			pstmt.setString(5, mvo.getId()); // ID
			pstmt.executeUpdate(); // 실행
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}

	public boolean updateMBoardCnt(MemberVO mvo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(UPDATE_MEMBER_BOARDCNT);
			pstmt.setString(2, mvo.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}

	public boolean updateBanCnt(MemberVO mvo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(UPDATE_MEMBER_BANCNT);
			pstmt.setString(2, mvo.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}

	public boolean deleteMember(MemberVO mvo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(DELETE_MEMBER);
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
		MemberVO data = null;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SELECTONE_MEMBER);
			pstmt.setString(1, mvo.getId());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data = new MemberVO();
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
		MemberVO data = null;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(LOGIN_MEMBER);
			pstmt.setString(1, mvo.getId());
			pstmt.setString(2, mvo.getmPw());

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data = new MemberVO();
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
		ArrayList<MemberVO> datas = new ArrayList<MemberVO>();
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SELECTALL_MEMBER);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO data = new MemberVO();
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

	public static void main(String[] args) {
		MemberDAO mdao = new MemberDAO();
		MemberVO mvo = new MemberVO();
		mvo.setId("hwan");
		mvo.setmPw("1234");

		System.out.println(mdao.loginMember(mvo));

	}

}