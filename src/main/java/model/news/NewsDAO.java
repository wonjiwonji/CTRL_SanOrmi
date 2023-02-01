package model.news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;

public class NewsDAO {

	Connection conn;
	PreparedStatement pstmt;

	// INSERT_NEWS ; 크롤링한 뉴스 데이터 삽입
	final String INSERT_NEWS = "INSERT INTO CRAWLING (C_TITLE, C_CONTENT, C_DATE) VALUES (?, ?, ?)";
	// SELECTALL_NEWS ; 크롤링한 뉴스 데이터 전체 출력
	final String SELECTALL_NEWS = "SELECT C_TITLE, C_CONTENT, C_DATE FROM CRAWLING";

	
	// insertNews ; 크롤링한 뉴스 데이터 삽입
	public boolean insertNews(NewsVO nvo) { // nvo ; nTitle, nContent, nDate 필요
		
		conn = JDBCUtil.connect(); // JDBCUtil 연결

		try {
			pstmt = conn.prepareStatement(INSERT_NEWS); // INSERT_NEWS; 크롤링한 뉴스 데이터 삽입
			pstmt.setString(1, nvo.getnTitle()); // 제목
			pstmt.setString(2, nvo.getnContent()); // 내용
			pstmt.setString(3, nvo.getnDate()); // 작성일자
			
			int res = pstmt.executeUpdate(); // pstmt에 저장된 쿼리문 실행
			if (res <= 0) { // 실패
				return false;
			}
			
		} catch (SQLException e) { // 예외처리
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt); // JDBCUtil 연결 해제
		return true;
	}
	
	// selectAllNews ; 크롤링한 뉴스 데이터 전체 출력
	public ArrayList<NewsVO> selectAllNews(NewsVO nvo) {
		
		ArrayList<NewsVO> nl = new ArrayList<>(); // NewsVO 타입 ArrayList nl 생성
		
		conn = JDBCUtil.connect(); // JDBCUtil 연결
		
		try {
			pstmt = conn.prepareStatement(SELECTALL_NEWS); // SELECTALL_NEWS ; 크롤링한 뉴스 데이터 전체 출력
			ResultSet rs = pstmt.executeQuery(); // pstmt 실행 결과 ResultSet rs에 저장
			
			while (rs.next()) { // 읽어들일 값이 남아있으면
				NewsVO news = new NewsVO(); // NewsVO 객체 생성
				
				news.setnTitle(rs.getString("C_TITLE")); // 제목
				news.setnContent(rs.getString("C_CONTENT")); // 내용
				news.setnDate(rs.getString("C_DATE")); // 작성일 저장
				
				nl.add(news); // 배열리스트에 NewsVO 객체 추가
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return nl;
	}

}
