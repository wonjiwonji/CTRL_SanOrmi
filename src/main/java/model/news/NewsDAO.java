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

//	final String SELECTONE_NEWS = "SELECT C_TITLE, C_CONTENT, C_DATE FROM CRAWLING WHERE C_SQE = ?";

	// insertNews ; 크롤링한 뉴스 데이터 삽입
	public boolean insertNews(NewsVO nvo) { // nvo ; nTitle, nContent, nDate 필요
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(INSERT_NEWS);
			pstmt.setString(1, nvo.getnTitle());
			pstmt.setString(2, nvo.getnContent());
			pstmt.setString(3, nvo.getnDate());
			
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
	
	// selectAllNews ; 크롤링한 뉴스 데이터 전체 출력
	public ArrayList<NewsVO> selectAllNews() {
		ArrayList<NewsVO> nl = new ArrayList<>();
		conn = JDBCUtil.connect();
		

		try {
			pstmt = conn.prepareStatement(SELECTALL_NEWS); // SELECTALL_NEWS ; 크롤링한 뉴스 데이터 전체 출력
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				NewsVO nvo = new NewsVO();
				nvo.setnTitle(rs.getString("C_TITLE"));
				nvo.setnContent(rs.getString("C_CONTENT"));
				nvo.setnDate(rs.getString("C_DATE"));
				nl.add(nvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return nl;
	}

}
