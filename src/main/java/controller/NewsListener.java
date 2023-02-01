package controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.common.Crawling;
import model.news.NewsDAO;

@WebListener
public class NewsListener implements ServletContextListener {

    public NewsListener() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce) {
		NewsDAO ndao = new NewsDAO();
		Crawling cl = new Crawling();
		
		if (ndao.selectAllNews(null).size() < 4) {
			cl.sample();
		}

    }
}