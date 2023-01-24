package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.report.ReportDAO;
import model.report.ReportVO;

public class ReportAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("reportOK.jsp");
		forward.setRedirect(true);

		ReportVO rvo=new ReportVO();
		ReportDAO rdao=new ReportDAO();
		rvo.setrId(request.getParameter("rId"));
		rvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
		rvo.setrTargetId(request.getParameter("rTargetId"));

		rdao.insert(rvo);
		return forward;
	}

}
