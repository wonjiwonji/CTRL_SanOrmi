package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteBoardFreeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		
		if(request.getParameter("id")=="") {
			forward.setPath("/loginNo.jsp");
			forward.setRedirect(false);

			return forward;
		}
		
		forward.setPath("/writeBoardFree.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
