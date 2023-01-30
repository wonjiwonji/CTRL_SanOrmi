package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteBoardQnAAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		
		if(request.getParameter("id")=="") {
			forward.setPath("/login.jsp");
			forward.setRedirect(false);

			return forward;
		}
		
		forward.setPath("/writeBoardQnA.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
