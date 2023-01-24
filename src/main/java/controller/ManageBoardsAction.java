package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.report.ReportDAO;
import model.report.ReportVO;

public class ManageBoardsAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/manageBoards.jsp");
		forward.setRedirect(false);
		
		ArrayList<ReportVO> rList = new ArrayList<ReportVO>();
		ReportDAO rdao = new ReportDAO();
		
		rList=rdao.selectAll();

		request.getSession().setAttribute("rList", rList);
		
		return forward;
	}

}
