package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;

// 시퀀스 넘버 넘겨주세요!

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;

	// INSERT_BOARD; 게시글 등록 쿼리문
	final String INSERT_BOARD = "INSERT INTO BOARD (B_TITLE, B_CONTENT, B_ID, B_DATE) VALUES(?,?,?, NOW())";
	// UPDATE_BOARD; 게시글 수정 쿼리문
	final String UPDATE_BOARD = "UPDATE BOARD SET B_TITLE=?,B_CONTENT=? WHERE B_NUM=?";
	// DELETE_BOARD; 게시글 삭제 쿼리문
	final String DELETE_BOARD = "DELETE FROM BOARD WHERE B_NUM=?";
	// SELECTONE_BOARD; 게시글 상세보기 쿼리문 (게시글 하나 클릭 시 나오는 정보)
	final String SELECTONE_BOARD = "SELECT RPAD(SUBSTR(B.B_ID, 1, 3), LENGTH(B.B_ID), '*') AS B_ID, B.B_TITLE, B.B_CONTENT, B.B_DATE, B.B_CNT, COUNT(BC.BC_NUM) AS C_CNT FROM BOARD B, BCOMMENT BC WHERE B.B_NUM=? AND B.B_NUM = BC.B_NUM";
	// SELECTALL_BOARD; 게시글 전체보기 쿼리문 ( 게시글 목록 시 나오는 정보 )
	final String SELECTALL_BOARD = "SELECT B_NUM, B_TITLE, B_CNT, RPAD(SUBSTR(B_ID, 1, 3), LENGTH(B_ID), '*') AS B_ID, B_CONTENT, C_CNT FROM BOARD ORDER BY B_NUM DESC";
	// INSERT_BCOMMENT; 댓글 등록 쿼리문
	final String INSERT_BCOMMENT = "INSERT INTO BCOMMENT (BC_ID, B_NUM,BC_CONTENT,BC_GROUP, BC_DATE) VALUES(?,?,?,(SELECT COALESCE(MAX(BC_GROUP),0)+1 FROM BCOMMENT AS BC_GROUP),NOW())";
	// INSERT_BCCOMMENT; 대댓글 등록 쿼리문
	final String INSERT_BCCOMMENT = "INSERT INTO BCOMMENT (BC_ID, B_NUM,BC_CONTENT,BC_GROUP,BC_SQE, BC_DATE)\r\n"
			+ "VALUES(?,?,?,?,\r\n"
			+ "(SELECT COALESCE(MAX(BCC.BC_SQE),0)+1 FROM BCOMMENT BCC GROUP BY BC_GROUP HAVING BC_GROUP = ?),\r\n"
			+ "NOW() );";
	// DELETE_BCOMMENT; 댓글 삭제 쿼리문 ( 대댓글 까지 같이 삭제 )
	final String DELETE_BCOMMENT = "DELETE FROM BCOMMENT WHERE BC_GROUP = ?";
	// DELETE_BCCOMMENT; 대댓글 삭제 쿼리문 ( 대댓글만 삭제 )
	final String DELETE_BCCOMMENT = "DELETE FROM BCOMMENT WHERE BC_NUM=?";
	// SELECTALL_BCOMMENT; 댓글 전체보기 쿼리문 ( 게시글에 대한 댓글 전체 보기 )
	final String SELECTALL_BCOMMENT = "SELECT BC_CONTENT, BC_GROUP, BC_DATE, RPAD(SUBSTR(BC_ID, 1, 3), LENGTH(BC_ID), '*') AS BC_ID FROM BCOMMENT WHERE B_NUM=? AND BC_SQE=0";
	// SELECTALL_BCCOMMENT; 대댓글 전체보기 쿼리문 ( 게시글에 대한 대댓글 전체 보기 )
	final String SELECTALL_BCCOMMENT = "SELECT BC_CONTENT,BC_GROUP, BC_DATE,BC_SQE, RPAD(SUBSTR(BC_ID, 1, 3), LENGTH(BC_ID), '*') AS BC_ID FROM BCOMMENT WHERE B_NUM=? AND BC_SQE>0 AND BC_GROUP=?";
	// SELECTONE ; 댓글 삭제를 위해 BC_NUM을 이용하여 BC_NUM, BC_GROUP, BC_SQE를 조회
	final String SELECTONE = "SELECT BC_NUM, BC_GROUP, BC_SQE FROM BCOMMENT WHERE BC_NUM = ?";

	// insertBoard; 게시글 등록 메서드
	public boolean insertBoard(BoardVO bvo) { // bvo; bTitle, bContent, bId 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(INSERT_BOARD); // INSERT_BOARD; 게시글 등록
			// 인자로 받은 bvo에서 필요한 정보 추출
			pstmt.setString(1, bvo.getbTitle()); // pstmt에 bTitle 저장
			pstmt.setString(2, bvo.getbContent()); // pstmt에 bContent 저장
			pstmt.setString(3, bvo.getbId()); // pstmt에 bId 저장
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

	// insertBComment; 댓글 등록 메서드
	public boolean insertBComment(BCommentVO bcvo) { // bcvo; bcContent, bNum, bcId 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결

		try {
			pstmt = conn.prepareStatement(INSERT_BCOMMENT); // INSERT_BCOMMENT; 댓글 등록
			// 인자로 받은 bcvo에서 필요한 정보 추출
			pstmt.setString(1, bcvo.getBcContent()); // pstmt에 bcContent 저장
			pstmt.setInt(2, bcvo.getbNum()); // pstmt에 bNum 저장
			pstmt.setString(3, bcvo.getBcID()); // pstmt에 bcId 저장

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

	// insertBCComment; 대댓글 등록
	public boolean insertBCComment(BCCommentVO bccvo) { // bccvo; bccId, bNum, bccContent, bccGroup 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결

		try {
			pstmt = conn.prepareStatement(INSERT_BCCOMMENT); // INSERT_BCCOMMENT; 대댓글 등록

			pstmt.setString(1, bccvo.getBccID()); // pstmt에 bccId 저장
			pstmt.setInt(2, bccvo.getbNum()); // pstmt에 bNum 저장
			pstmt.setString(3, bccvo.getBccContent()); // pstmt에 bccContent 저장
			pstmt.setInt(4, bccvo.getBccGroup()); // pstmt에 bccGroup 저장
			pstmt.setInt(5, bccvo.getBccGroup());
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

	// updateBoard; 게시글 수정
	public boolean updateBoard(BoardVO bvo) { // bvo; bTitle, bContent, bNum 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(UPDATE_BOARD); // UPDATE_BOARD; 게시글 수정
			pstmt.setString(1, bvo.getbTitle()); // pstmt에 bTitle 저장
			pstmt.setString(2, bvo.getbContent()); // pstmt에 bContent 저장
			pstmt.setInt(3, bvo.getbNum()); // pstmt에 bNum 저장
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

	// deleteBoard; 게시글 삭제
	public boolean deleteBoard(BoardVO bvo) { // bvo; bNum 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(DELETE_BOARD); // DELETE_BOARD; 게시글 삭제
			pstmt.setInt(1, bvo.getbNum()); // pstmt에 bNum 저장
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

	// selectOneBoard; 게시글 상세보기
	public ArrayList<BoardSet> selectOneBoard(BoardVO bvo) { // bvo; bNum 필요
		ArrayList<BoardSet> datas = new ArrayList<BoardSet>(); // <BoardSet>타입의 ArrayList datas 생성

		conn = JDBCUtil.connect(); // JDBCUtil 연결

		try {
			pstmt = conn.prepareStatement(SELECTONE_BOARD); // SELECTONE_BOARD; 게시글 상세보기
			pstmt.setInt(1, bvo.getbNum()); // pstmt에 bNum 저장
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				BoardSet bs = new BoardSet(); // BoardSet 객체 bs 생성
				BoardVO board = new BoardVO(); // BoardVO 객체 board 생성

				board.setbNum(bvo.getbNum());

				board.setbId(rs.getString("B_ID"));
				board.setbTitle(rs.getString("B_TITLE"));
				board.setbContent(rs.getString("B_CONTENT"));
				board.setbDate(rs.getDate("B_DATE"));
				// B_DATE
				board.setbCnt(rs.getInt("B_CNT"));
				board.setcCnt(rs.getInt("C_CNT"));
				bs.setBoard(board);

				pstmt = conn.prepareStatement(SELECTALL_BCOMMENT); // SELECTALL_BCOMMENT; 댓글 전체 보기
				pstmt.setInt(1, bvo.getbNum()); // pstmt에 bNum 저장
				ResultSet rs2 = pstmt.executeQuery();

				ArrayList<BCommentVO> bcList = new ArrayList<BCommentVO>(); // <BCommentVO> 타입의 ArrayList bcList 생성
				while (rs2.next()) {
					BCommentVO bcomment = new BCommentVO(); // BCommentVO 객체 bcomment 생성

					bcomment.setBcContent(rs2.getString("BC_CONTENT"));
					bcomment.setBcID(rs2.getString("BC_ID"));
					bcomment.setBcGroup(bvo.getBcvo().getBcGroup());
					bcomment.setbNum(bvo.getbNum());
					bcomment.setBcDate(rs2.getDate("BC_DATE"));
					bcList.add(bcomment);

					pstmt = conn.prepareStatement(SELECTALL_BCCOMMENT); // SELECTALL_BCCOMMENT; 대댓글 전체보기
					pstmt.setInt(1, bvo.getbNum()); // pstmt에 bNum 저장
					pstmt.setInt(2, bvo.getBcvo().getBcGroup()); // pstmt에 bcGroup저장
					ResultSet rs3 = pstmt.executeQuery();

					ArrayList<BCCommentVO> bccList = new ArrayList<BCCommentVO>(); // <BCCommentVO> 타입의 ArrayList
																					// bccList 생성
					while (rs3.next()) {
						BCCommentVO bccomment = new BCCommentVO(); // BCCommentVO 객체 bccomment생성

						bccomment.setBccContent(rs3.getString("BC_CONTENT"));
						bccomment.setBccID(rs3.getString("BC_ID"));
						bccomment.setbNum(bvo.getbNum());
						bccomment.setBccSqe(rs3.getInt("BC_SQE"));
						bccomment.setBccGroup(bvo.getBcvo().getBcGroup());
						bccomment.setBccDate(rs3.getDate("BC_DATE"));
						bccList.add(bccomment);
					}
					bcomment.setBccList(bccList);
				}
				bs.setBcList(bcList);

				datas.add(bs); // ArrayList datas에 bs 값들 add해줌
			}

		} catch (Exception e) { // 위 try문 실행 중 에러(SQL) 발생 시
			e.printStackTrace(); // 무슨 에러인지 출력
		}

		return datas; // ArrayList datas 반환

	}

	// selectAllBoard ; 게시글 전체 보기 메서드
	public ArrayList<BoardVO> selectAllBoard() { // 게시글 전체보기 메서드

		ArrayList<BoardVO> bList = new ArrayList<>(); // <BoardVO> 타입의 ArrayList bList 생성
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(SELECTALL_BOARD); // SELECTALL_BOARD; 게시글 전체 보기
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setbNum(rs.getInt("B_NUM"));
				board.setbTitle(rs.getString("B_TITLE"));
				board.setbId(rs.getString("B_ID"));
				board.setbCnt(rs.getInt("B_CNT"));
				board.setcCnt(rs.getInt("C_CNT"));
				bList.add(board); // ArrayList bList에 board 값들 add해줌

			}
		} catch (SQLException e) { // 위 try문 실행 중 에러(SQL) 발생 시
			e.printStackTrace(); // 무슨 에러인지 출력
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return bList; // ArrayList bList 반환
	}

	// selectOne ; 댓글 삭제를 위해 BC_NUM을 이용하여 BC_NUM, BC_GROUP, BC_SQE를 조회
	public BCommentVO selectOne(BCommentVO bcvo) { // bcvo ; bcNum 필요
		BCommentVO data = null;
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(SELECTONE); // SELECTONE ; 댓글 삭제를 위해 BC_NUM을 이용하여 BC_NUM, BC_GROUP, BC_SQE를 조회
			pstmt.setInt(1, bcvo.getBcNum()); // 댓글 번호
			ResultSet rs = pstmt.executeQuery(); // pstmt 실행, 실행한 결과 rs에 저장
			if (rs.next()) { // rs에 정보가 있는 동안
				data = new BCommentVO(); // BCommentVO 새로운 객체 data 생성
				data.setBcNum(rs.getInt("BC_NUM")); // 댓글 번호
				data.setBcGroup(rs.getInt("BC_GROUP")); // 댓글 그룹
				data.setBcSQE(rs.getInt("BC_SQE")); // 댓글 시퀀스
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return data; // data 리턴
	}

	public boolean deleteBComment(BCommentVO bcvo) { // bcvo; selectOne을 실행한 return 값 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			if (bcvo.getBccvo().getBccSqe() > 0) { // bccvo에 있는 bccSqe가 0보다 크다면
				pstmt = conn.prepareStatement(DELETE_BCCOMMENT); // DELETE_BCCOMMENT; 대댓글 삭제
				pstmt.setInt(1, bcvo.getBcNum()); // pstmt에 bcNum 저장
			} else { // bccSqe가 0보다 크지 않다면
				pstmt = conn.prepareStatement(DELETE_BCOMMENT); // DELETE_BCOMMENT; 댓글 삭제
				pstmt.setInt(1, bcvo.getBcGroup()); // pstmt에 bcGroup 저장
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

//	public static void main(String[] args) {
//
////		BoardDAO bdao = new BoardDAO();
////		BCommentVO bcvo = new BCommentVO();
////		bcvo.setBcContent("댓글2내용");
////		bcvo.setbNum(2);
////		bcvo.setBcID("jiwon");
////		bdao.insertBComment(bcvo);
//		
//		
////		BoardDAO bdao = new BoardDAO();
////		BCCommentVO bccvo = new BCCommentVO();
////		bccvo.setBccID("jiwon");
////		bccvo.setbNum(2);
////		bccvo.setBccContent("대댓글 내용3-3");
////		bccvo.setBccGroup(3);
////		bdao.insertBCComment(bccvo);
//		
////		BoardDAO bdao = new BoardDAO();
////		BCommentVO bcvo = new BCommentVO();
////		bcvo.setBcNum(13);
////		bcvo.setBcGroup(3);
////		System.out.println(bcvo);
////		bdao.deleteBComment(bcvo);
//		
////		BoardDAO bdao = new BoardDAO();
////		BCommentVO bcvo = new BCommentVO();
////		bcvo.setBcNum(8);
////		System.out.println(bcvo);
////		bdao.deleteBComment(bdao.selectOne(bcvo));
//
//	}
}