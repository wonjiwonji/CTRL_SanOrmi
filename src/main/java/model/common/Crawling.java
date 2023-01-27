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
		final String url = "http://www.ohmynews.com/NWS_Web/Tag/index.aspx?tag=%eb%93%b1%ec%82%b0";
		Document doc = null;
		NewsVO nvo = null;
		NewsDAO ndao = new NewsDAO();
		int cnt = 0;
//		ArrayList<NewsVO> nl = new ArrayList<NewsVO>();
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements eles1 = doc.select("li>div>div>dl>dt>a");
		Elements eles2 = doc.select("li>div>div>dl>dd>a");
		Elements eles3 = doc.select("li>div>div>p");
		Iterator<Element> itr1 = eles1.iterator();
		Iterator<Element> itr2 = eles2.iterator();
		Iterator<Element> itr3 = eles3.iterator();

		while (itr1.hasNext() && cnt < 4) {

			String str1 = itr1.next().text();
			String str2 = itr2.next().text();
			String str3 = itr3.next().text();
			str3 = str3.substring(str3.length() - 14, str3.length());
			System.out.println("  로그 str3 : "+str3);
			
			nvo = new NewsVO();
			nvo.setnTitle(str1);
			nvo.setnContent(str2);
			nvo.setnDate(str3);
			System.out.println("  로그 nvo : "+nvo);
			ndao.insertNews(nvo);
			cnt++;

		}
		System.out.println("  로그 : 크롤링 완료");
	}

	public static void main(String[] args) {
		Crawling cl = new Crawling();
		cl.sample();
		
	}

}
