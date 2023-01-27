package model.news;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.common.Crawling;

public class NewsListener implements ServletContextListener {

	public NewsListener() {

	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("로그2 : 서블릿 컨테이너(톰캣) 시작을 감지(모니터링)하여 동작되는 리스너 메소드");
		ServletContext sc = sce.getServletContext();
		Crawling news = new Crawling();
		sc.setAttribute("news", news.news());
	}

}
