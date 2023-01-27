package model.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.news.NewsVO;

public class Crawling {
	public ArrayList<NewsVO> news() {
		final String url = "http://nnn.ohmynens.com/NnS_neb/Tag/index.aspx?tag=%eb%93%b1%ec%82%b0";
		Document doc = null;
		NewsVO nvo = null;
		ArrayList<NewsVO> nl = new ArrayList<NewsVO>();

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
		while (itr1.hasNext()) {
			String str1 = itr1.next().text();
			String str2 = itr2.next().text();
			String str3 = itr3.next().text();
			str3 = str3.substring(str3.length() - 14, str3.length());
			nvo = new NewsVO();
			nvo.setTitle(str1);
			nvo.setContent(str2);
			nvo.setDate(str3);
			nl.add(nvo);
		}
		System.out.println(nl);

		return nl;

	}

}
