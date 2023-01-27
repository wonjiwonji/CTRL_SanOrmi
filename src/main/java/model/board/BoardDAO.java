package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;
import model.member.MemberVO;

// 시퀀스 넘버 넘겨주세요!

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;

	// INSERT_BOARD; 게시글 등록 쿼리문
	final String INSERT_BOARD = "INSERT INTO BOARD (B_TITLE, B_CONTENT, B_ID, B_DATE) VALUES(?,?,?, NOW())";
	// UPDATE_BOARD; 게시글 수정 쿼리문
	final String UPDATE_BOARD = "UPDATE BOARD SET B_TITLE=?,B_CONTENT=? WHERE B_NUM=?";
	
	// UPDATE_BOARD_CNT; 게시글 조회수+ 쿼리문
	final String UPDATE_BOARD_CNT = "UPDATE BOARD SET B_CNT = B_CNT+1 WHERE B_NUM=?";
	// DELETE_BOARD; 게시글 삭제 쿼리문
	final String DELETE_BOARD = "DELETE FROM BOARD WHERE B_NUM=?";
	// SELECTONE_BOARD; 게시글 상세보기 쿼리문 (게시글 하나 클릭 시 나오는 정보)
	final String SELECTONE_BOARD = "SELECT B_ID, B.B_TITLE, B.B_CONTENT, B.B_DATE,\r\n "
			+ "COUNT(BC.BC_NUM) AS C_CNT, B.B_CNT\r\n"
			+ "FROM BOARD B LEFT JOIN BCOMMENT BC ON B.B_NUM = BC.B_NUM WHERE B.B_NUM =? GROUP BY BC_NUM;";
	// SELECTALL_BOARD; 게시글 전체보기 쿼리문 ( 게시글 목록 시 나오는 정보 )
	final String SELECTALL_BOARD = "SELECT B_NUM, B_TITLE, B_CNT, B_ID, B_CONTENT, C_CNT FROM BOARD ORDER BY B_NUM DESC";
	// SELECTALL_MY_PAGE; 내가 쓴 글 전체보기 쿼리문 
	final String SELECTALL_MY_PAGE = "SELECT B_NUM, B_TITLE, B_CNT FROM BOARD WHERE B_ID=? ORDER BY B_NUM DESC";
	
	// INSERT_BCOMMENT; 댓글 등록 쿼리문
	final String INSERT_BCOMMENT = "INSERT INTO BCOMMENT (BC_ID, B_NUM,BC_CONTENT,BC_GROUP, BC_DATE) VALUES\r\n"
			+ "(?,?,?,(SELECT COALESCE(MAX(BC_GROUP),0)+1 FROM BCOMMENT AS BC_GROUP),NOW())";
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
	final String SELECTALL_BCOMMENT = "SELECT BC_CONTENT, BC_GROUP, BC_DATE, BC_ID FROM BCOMMENT WHERE B_NUM=? AND BC_SQE=0";
	// SELECTALL_BCCOMMENT; 대댓글 전체보기 쿼리문 ( 게시글에 대한 대댓글 전체 보기 )
	final String SELECTALL_BCCOMMENT = "SELECT BC_CONTENT,BC_GROUP, BC_DATE,BC_SQE,BC_ID FROM BCOMMENT WHERE B_NUM=? AND BC_SQE>0 AND BC_GROUP=?";
	// SELECTONE ; 댓글 삭제를 위해 BC_NUM을 이용하여 BC_NUM, BC_GROUP, BC_SQE를 조회
	final String SELECTONE = "SELECT BC_NUM, BC_GROUP, BC_SQE FROM BCOMMENT WHERE BC_NUM = ?";

	// SELECTALL_TOP5; 커뮤니티 TOP5
	final String SELECTALL_TOP5 = "SELECT B_NUM, B_TITLE, B_DATE, B_CNT FROM BOARD ORDER BY B_CNT DESC LIMIT 0,5";

	
	// insertBoard; 게시글 등록 메서드
	public boolean insertBoard(BoardVO bvo) { // bvo; bTitle, bContent, bId 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(INSERT_BOARD); // INSERT_BOARD; 게시글 등록
			// 인자로 받은 bvo에서 필요한 정보 추출
			pstmt.setString(1, bvo.getbContent()); // pstmt에 bContent 저장
			pstmt.setString(2, bvo.getbTitle()); // pstmt에 bTitle 저장
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
	
	
	// updatebCnt ; 게시글 조회수 변경
	public boolean updatebCnt(BoardVO bvo) { // bvo ; bNum 필요
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(UPDATE_BOARD_CNT); // UPDATE_BOARD_CNT ; 게시글 조회수 변경
			pstmt.setInt(1, bvo.getbNum()); // 게시글 번호
			pstmt.executeUpdate(); // 실행
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return true;
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
			ResultSet rs = pstmt.executeQuery(); // 실행결과 rs에 저장
			if (rs.next()) { // 저장할 값이 있다면
				BoardSet bs = new BoardSet(); // BoardSet 객체 bs 생성
				BoardVO board = new BoardVO(); // BoardVO 객체 board 생성

				board.setbNum(bvo.getbNum()); // 게시글 번호 저장

				board.setbId(rs.getString("B_ID")); // 게시글 작성자 저장
				board.setbTitle(rs.getString("B_TITLE")); // 게시글 제목 저장
				board.setbContent(rs.getString("B_CONTENT")); // 게시글 내용 저장
				board.setbDate(rs.getDate("B_DATE")); // 게시글 작성일 저장
				// B_DATE
				board.setbCnt(rs.getInt("B_CNT")); // 게시글 조회수 저장
				board.setcCnt(rs.getInt("C_CNT")); // 댓글 수 저장
				bs.setBoard(board); // setBoard 해줌

				pstmt = conn.prepareStatement(SELECTALL_BCOMMENT); // SELECTALL_BCOMMENT; 댓글 전체 보기
				pstmt.setInt(1, bvo.getbNum()); // pstmt에 bNum 저장
				ResultSet rs2 = pstmt.executeQuery(); // 실행결과 rs2에 저장

				ArrayList<BCommentVO> bcList = new ArrayList<BCommentVO>(); // <BCommentVO> 타입의 ArrayList bcList 생성
				while (rs2.next()) { // 저장할 정보가 남아있는 동안
					BCommentVO bcomment = new BCommentVO(); // BCommentVO 객체 bcomment 생성

					bcomment.setBcContent(rs2.getString("BC_CONTENT")); // 댓글 내용 저장
					bcomment.setBcID(rs2.getString("BC_ID")); // 댓글 작성자 저장
					bcomment.setBcGroup(bvo.getBcvo().getBcGroup()); // 댓글 그룹 저장
					bcomment.setbNum(bvo.getbNum()); // 게시글 번호 저장
					bcomment.setBcDate(rs2.getDate("BC_DATE")); // 댓글 작성일 저장
					bcList.add(bcomment); // 위에서 저장한 값들을 배열리스트 bcList에 add해줌

					pstmt = conn.prepareStatement(SELECTALL_BCCOMMENT); // SELECTALL_BCCOMMENT; 대댓글 전체보기
					pstmt.setInt(1, bvo.getbNum()); // pstmt에 bNum 저장
					pstmt.setInt(2, bvo.getBcvo().getBcGroup()); // pstmt에 bcGroup저장
					ResultSet rs3 = pstmt.executeQuery(); // 실행결과 rs3에 저장

					ArrayList<BCCommentVO> bccList = new ArrayList<BCCommentVO>(); // <BCCommentVO> 타입의 ArrayList
																					// bccList 생성
					while (rs3.next()) { // 저장할 정보가 남아 있는 동안
						BCCommentVO bccomment = new BCCommentVO(); // BCCommentVO 객체 bccomment생성

						bccomment.setBccContent(rs3.getString("BC_CONTENT")); // 대댓글 내용 저장
						bccomment.setBccID(rs3.getString("BC_ID")); // 대댓글 작성자 저장
						bccomment.setbNum(bvo.getbNum()); // 게시글 번호 저장
						bccomment.setBccSqe(rs3.getInt("BC_SQE")); // 대댓글 시퀀스 저장
						bccomment.setBccGroup(bvo.getBcvo().getBcGroup()); // 대댓글 그룹 저장
						bccomment.setBccDate(rs3.getDate("BC_DATE")); // 대댓글 작성일 저장
						bccList.add(bccomment); // 위에서 저장한 값들을 배열리스트 bccList에 add해줌
					}
					bcomment.setBccList(bccList); // setBccList해줌
				}
				bs.setBcList(bcList); // setBcList 해줌

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
			ResultSet rs = pstmt.executeQuery(); // 실행결과 rs에 저장
			while (rs.next()) { // 저장할 정보가 남아있는 동안
				BoardVO board = new BoardVO(); // 새로운 BoardVO 객체 board 생성
				board.setbNum(rs.getInt("B_NUM")); // 게시글 번호 저장 
				board.setbTitle(rs.getString("B_TITLE")); // 게시글 제목 저장
				board.setbId(rs.getString("B_ID")); // 작성자 저장
				board.setbCnt(rs.getInt("B_CNT")); // 게시글 조회수 저장
				board.setcCnt(rs.getInt("C_CNT")); // 댓글 수 저장
				bList.add(board); // ArrayList bList에 board 값들 add해줌

			}
		} catch (SQLException e) { // 위 try문 실행 중 에러(SQL) 발생 시
			e.printStackTrace(); // 무슨 에러인지 출력
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return bList; // ArrayList bList 반환
	}

	
	// selectAll_MY_PAGE ; 내가 쓴 글 전체 보기 메서드
	public ArrayList<BoardVO> selectAllMyPage(BoardVO bvo) { // 내가 쓴 글 전체보기 메서드

		ArrayList<BoardVO> bList = new ArrayList<>(); // <BoardVO> 타입의 ArrayList bList 생성
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(SELECTALL_MY_PAGE); // SELECTALL_BOARD; 게시글 전체 보기
			pstmt.setString(1, bvo.getbId()); // 작성자
			ResultSet rs = pstmt.executeQuery(); // 실행결과 rs에 저장
			while (rs.next()) { // 저장할 정보가 남아있는 동안
				BoardVO board = new BoardVO(); // 새로운 BoardVO 객체 board 생성
				board.setbNum(rs.getInt("B_NUM")); // 게시글 번호 저장 
				board.setbTitle(rs.getString("B_TITLE")); // 게시글 제목 저장
				board.setbCnt(rs.getInt("B_CNT")); // 게시글 조회수 저장
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
	
	
	public ArrayList<BoardVO> selectAllTop5() {
		ArrayList<BoardVO> datas = new ArrayList<BoardVO>(); // 실행 결과 담을 BoardVO 타입 배열리스트 datas 생성
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		try {
			pstmt = conn.prepareStatement(SELECTALL_TOP5); // SELECTALL_TOP5 ; 커뮤니티 TOP5 보기
			ResultSet rs = pstmt.executeQuery(); // 실행 결과 rs에 저장
			while (rs.next()) { // 저장할 정보가 남아 있는 동안
				BoardVO data = new BoardVO(); // 새로운 BoardVo 객체 data 생성해서 다음 값 저장
				data.setbNum(rs.getInt("B_NUM")); // 게시글 번호
				data.setbTitle(rs.getString("B_TITLE")); // 게시글 제목
				data.setbDate(rs.getDate("B_Date")); // 게시글 작성일
				data.setbCnt(rs.getInt("B_CNT")); // 게시글 조회수
				
				datas.add(data); // datas배열리스트에 data객체 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBC 연결 해제
		return datas;

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