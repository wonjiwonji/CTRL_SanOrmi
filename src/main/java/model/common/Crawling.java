package model.common;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.news.NewsDAO;
import model.news.NewsVO;

public class Crawling {

	public void sample() {
		System.out.println("  로그 : Crawling.sample() 시작");
		
		// 크롤링 할 url
		final String url = "http://www.ohmynews.com/NWS_Web/Tag/index.aspx?tag=%eb%93%b1%ec%82%b0";
		Document doc = null; // url로 읽은 값 저장할 곳
		NewsVO nvo = null; // NewsVO 객체 (제목, 내용, 일자 저장 예정)
		NewsDAO ndao = new NewsDAO(); // NewsDAO (DB에 insert하기 위함)
		int cnt = 0; // 크롤링 4회까지만 진행 (크롤링 횟수 카운팅)

		try {
			doc = Jsoup.connect(url).get(); // Jsoup을 이용하여 url 연결
		} catch (IOException e) { // 예외처리
			e.printStackTrace();
		}

		// doc에서 다음 구조를 가진 것을 찾아 Elements 객체에 저장
		Elements eles1 = doc.select("li>div>div>dl>dt>a"); // 뉴스 제목
		Elements eles2 = doc.select("li>div>div>dl>dd>a"); // 뉴스 내용
		Elements eles3 = doc.select("li>div>div>p"); // 뉴스 날짜
		
		// 각 요소를 이터레이터에 저장
		Iterator<Element> itr1 = eles1.iterator(); // 뉴스 제목
		Iterator<Element> itr2 = eles2.iterator(); // 뉴스 내용
		Iterator<Element> itr3 = eles3.iterator(); // 뉴스 날짜

		// 읽어들일 값이 있고, 크롤링 한 갯수가 4개 미만이면
		while (itr1.hasNext() && cnt < 4) { // 크롤링
			String str1 = itr1.next().text(); // 뉴스 제목
			String str2 = itr2.next().text(); // 뉴스 내용
			String str3 = itr3.next().text(); // 뉴스 날짜
			str3 = str3.substring(str3.length() - 14, str3.length());
			// 날짜 부분만 자르기! (뒤에서부터 14자)
			
			nvo = new NewsVO(); // 뉴스 VO 객체 생성
			nvo.setnTitle(str1); // 제목 저장
			nvo.setnContent(str2); // 내용 저장
			nvo.setnDate(str3); // 날짜만 자른 값 저장
			ndao.insertNews(nvo);// insertNews 실행 -> DB에 저장
			cnt++; // 크롤링 횟수 ++
		}
		System.out.println("  로그 : 크롤링 완료");
	}


}
