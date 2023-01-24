package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.report.ReportDAO;
import model.report.ReportVO;

public class ManageBoardsDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/manageBoardsDetail.jsp");
		forward.setRedirect(false);
		
		ReportDAO rdao = new ReportDAO();
		ReportVO rvo = new ReportVO();
		ReportVO srvo = new ReportVO();
		
		rvo.setrNum(Integer.parseInt(request.getParameter("rNum")));
		// rNum인지 rId인지 확인 필요
		
		srvo=rdao.selectOne(rvo);

		request.getSession().setAttribute("srvo", srvo);

		return forward;
	}

}
