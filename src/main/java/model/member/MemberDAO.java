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
   final String SELECTALL_MEMBER = "SELECT ID M_NAME M_EMAIL M_ADDRESS M_REGDATE M_BANCNT FROM MEMBER";

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
   public boolean updateMember(MemberVO mvo) { // mvo ; mPw, mName, mEmail, mAddress, Id 필요
      conn = JDBCUtil.connect(); // JDBCUtil 연결
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
      JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
      return true;
   }

   // updateMBoardCnt ; 회원이 작성한 게시글 갯수 변경
   public boolean updateMBoardCnt(MemberVO mvo) { // mvo ; Id 필요
      conn = JDBCUtil.connect(); // JDBCUtil 연결
      try {
         pstmt = conn.prepareStatement(UPDATE_MEMBER_BOARDCNT); // UPDATE_MEMBER_BOARD ; 회원이 작성한 게시글 갯수 변경
         pstmt.setString(2, mvo.getId()); // Id
         pstmt.executeUpdate(); // 실행
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
      return true;
   }

   // updateBanCnt ; 회원 별 Ban 당한 횟수 변경
   public boolean updateBanCnt(MemberVO mvo) { // mvo ; Id 필요
      conn = JDBCUtil.connect();
      try {
         pstmt = conn.prepareStatement(UPDATE_MEMBER_BANCNT); // updateBanCnt ; 회원 별 Ban 당한 횟수 변경
         pstmt.setString(2, mvo.getId()); // Id
         pstmt.executeUpdate(); // 실행
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
      return true;
   }

   // deleteMember ; 회원 삭제
   public boolean deleteMember(MemberVO mvo) { // mvo ; Id 필요
      conn = JDBCUtil.connect(); // JDBCUtil 연결
      try {
         pstmt = conn.prepareStatement(DELETE_MEMBER); // DELETE_MEMBER ; 회원 삭제
         pstmt.setString(1, mvo.getId()); // Id
         pstmt.executeUpdate(); // 실행
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
      return true;
   }

   // 회원 상세 정보 보기
   public MemberVO selectOneMember(MemberVO mvo) { // mvo ; Id 필요
      MemberVO data = null;
      conn = JDBCUtil.connect(); // JDBCUtil 연결
      try {
         pstmt = conn.prepareStatement(SELECTONE_MEMBER); // pstmt에 SELECTONE_MEMBER ; 회원 상세 보기 담아 실행할 준비
         pstmt.setString(1, mvo.getId()); // pstmt에 mvo에서 받아온 Id 값 세팅
         ResultSet rs = pstmt.executeQuery(); // pstmt 실행, 실행한 결과 rs에 저장
         if (rs.next()) { // rs에 정보가 있는 동안
            data = new MemberVO(); // MemberVO 새로운 객체 data 생성
            data.setId(rs.getString("ID")); // data에 Id 저장
            data.setmName(rs.getString("M_NAME")); // 이름
            data.setmEmail(rs.getString("M_EMAIL")); // 이메일
            data.setmAddress(rs.getString("M_ADDRESS")); // 주소
            data.setmDate(rs.getDate("M_REGDATE")); // 가입 일자
            data.setmBanCnt(rs.getInt("M_BANCNT"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
      return data; // data 리턴
   }

   // loginMember ; 로그인
   public MemberVO loginMember(MemberVO mvo) { // mvo ; Id, Pw 필요
      MemberVO data = null;
      conn = JDBCUtil.connect(); // JDBCUtil 연결
      try {
         pstmt = conn.prepareStatement(LOGIN_MEMBER); // LOGIN_MEMBER ; 로그인
         pstmt.setString(1, mvo.getId()); // Id
         pstmt.setString(2, mvo.getmPw()); // Pw

         ResultSet rs = pstmt.executeQuery(); // 실행결과 rs에 저장
         if (rs.next()) { // 저장할 정보가 남아 있는 동안
            data = new MemberVO(); // 새로운 MemberVO 객체 data 생성해서 다음 결과 셋
            data.setId(rs.getString("ID")); // Id
            data.setmPw(rs.getString("M_PW")); // Pw
            data.setmName(rs.getString("M_NAME")); // 이름
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
      return data;
   }

   // selectAllMember ; 전체 회원 보기
   public ArrayList<MemberVO> selectAllMember() { // Input 필요 없음
      ArrayList<MemberVO> datas = new ArrayList<MemberVO>(); // 실행 결과 담을 MemberVO 타입 배열리스트 datas 생성
      conn = JDBCUtil.connect(); // JDBCUtil 연결
      try {
         pstmt = conn.prepareStatement(SELECTALL_MEMBER); // SELECTALL_MEMBER ; 전체 회원 보기
         ResultSet rs = pstmt.executeQuery(); // 실행 결과 rs에 저장
         while (rs.next()) { // 저장할 정보가 남아 있는 동안
            MemberVO data = new MemberVO(); // 새로운 MemberVo 객체 data 생성해서 다음 값 저장
            data.setId(rs.getString("ID")); // 아이디
            data.setmEmail(rs.getString("M_EMAIL")); // 이메일
            data.setmDate(rs.getDate("M_REGDATE")); // 가입일자
            data.setmName(rs.getString("M_NAME")); // 이름
            data.setmBanCnt(rs.getInt("M_BANCNT")); // 벤 카운트
            datas.add(data); // datas배열리스트에 data객체 추가
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      JDBCUtil.disconnect(conn, pstmt); // JDBC 연결 해제
      return datas;
   }

   /*
   // 테스트용
   public static void main(String[] args) { 
      MemberDAO mdao = new MemberDAO();
      MemberVO mvo = new MemberVO();
      mvo.setId("hwan");
      mvo.setmPw("1234");

      System.out.println(mdao.loginMember(mvo));

   }
   */

}