package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardVO;
import model.report.ReportDAO;
import model.report.ReportVO;

public class CancleReportAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("manageBoards.jsp");
		forward.setRedirect(true);

		ReportVO rvo=new ReportVO();
		ReportDAO rdao=new ReportDAO();
		rvo.setrNum(Integer.parseInt(request.getParameter("rNum")));

		rdao.delete(rvo);
		return forward;
	}
}
