package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		
		String pw=request.getParameter("mPw");
		System.out.println(pw);
		
		if(pw.equals("NAVER")) {
			forward.setPath("snsLogout.jsp");
			forward.setRedirect(false);
		}
		else if(pw.equals("KAKAO")) {
			forward.setPath("snsLogout.jsp");
			forward.setRedirect(false);
		}
		else {
			forward.setPath("main.do");
			forward.setRedirect(true);
			request.getSession().invalidate();
		}
		
		return forward;
	}

}
