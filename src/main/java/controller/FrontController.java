package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		System.out.println("uri: "+uri);
		String cp=request.getContextPath();
		System.out.println("cp: "+cp);
		String command=uri.substring(cp.length());
		System.out.println("command: "+command);

		ActionForward forward=null;
		if(command.equals("/main.do")) {
			try {
				forward=new MainAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/login.do")) {

		}
		else if(command.equals("/logout.do")) {

		}
		
		if(forward==null) {
			forward=new ActionForward();
			forward.setPath("/error404.jsp");
			forward.setRedirect(false);
		}

		if(forward.isRedirect()) {
			System.out.println("redirect");
			response.sendRedirect(forward.getPath());
		}
		else {
			System.out.println("forward");
			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}

}