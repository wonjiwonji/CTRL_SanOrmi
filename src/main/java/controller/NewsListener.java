package controller;

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
		ServletContext sc = sce.getServletContext();
		Crawling news = new Crawling();
		sc.setAttribute("news", news.news());
	}
}
