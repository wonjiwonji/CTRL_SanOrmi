package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;
import model.report.ReportDAO;
import model.report.ReportVO;

public class ReportAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();

		ReportVO rvo=new ReportVO();
		ReportDAO rdao=new ReportDAO();
		rvo.setrId(request.getParameter("rId"));
		rvo.setbNum(Integer.parseInt(request.getParameter("bNum")));
		rvo.setrTargetId(request.getParameter("rTargetId"));
		
		MemberVO mvo=new MemberVO();
		MemberDAO mdao=new MemberDAO();
		mvo.setId(request.getParameter("id"));
		
		if(rdao.selectReportCheck(rvo)) {
			forward.setPath("reportOK.jsp");
			forward.setRedirect(true);
			rdao.insert(rvo);
			mdao.updateBanCnt(mvo);
			return forward;
		}
		
		forward.setPath("reportNotOK.jsp");
		forward.setRedirect(true);
		return forward;
		
	}

}
