package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardVO;
import model.member.MemberDAO;
import model.member.MemberVO;
import model.report.ReportDAO;
import model.report.ReportVO;

public class AcceptReportAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("manageBoards.do");
		forward.setRedirect(true);
		
		MemberVO mvo=new MemberVO();
		MemberDAO mdao=new MemberDAO();
		BoardVO bvo=new BoardVO();
		BoardDAO bdao=new BoardDAO();
		ReportVO rvo=new ReportVO();
		ReportVO rrvo=new ReportVO();
		ReportDAO rdao=new ReportDAO();
		
		rvo.setrNum(Integer.parseInt(request.getParameter("rNum")));
		
		rrvo=rdao.selectOne(rvo);	// 신고번호의 해당 rvo 가져오기
		bvo.setbNum(rrvo.getbNum());	// 신고번호에 해당하는 rvo의 bvo 넘버 가져오기 
		
		mvo.setId(rrvo.getrTargetId());
		
		System.out.println("로그rvo : "+rvo);
		System.out.println("로그rrvo : "+rrvo);
		System.out.println("로그mvo : "+mvo);
		System.err.println("로그bvo : "+bvo);
		
		rdao.delete(rrvo);
		bdao.deleteBoard(bvo);
		mdao.updateBanCnt(mvo);
		return forward;
	}
}
