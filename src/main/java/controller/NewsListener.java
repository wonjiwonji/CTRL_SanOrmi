package controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.common.Crawling;
import model.news.NewsDAO;

/**
 * Application Lifecycle Listener implementation class NewsListener2
 *
 */
@WebListener
public class NewsListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public NewsListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
		System.out.println("  로그 : 서블릿 컨테이너(톰캣) 시작을 감지(모니터링)하여 동작되는 리스너 메소드");
//		ServletContext sc = sce.getServletContext();
		
		NewsDAO ndao = new NewsDAO();
		Crawling cl = new Crawling();
		
		System.out.println(ndao.selectAllNews().size());
		if (ndao.selectAllNews().size() < 4) {
			cl.sample();
		}
//
//		sc.setAttribute("cl", cl.sample());
//		System.out.println(sc);
	}
}