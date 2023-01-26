package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("main.do");
		forward.setRedirect(false);
		
		MemberDAO mdao=new MemberDAO();
		MemberVO mvo=new MemberVO();
		mvo.setId(request.getParameter("id"));
		mvo.setmPw(request.getParameter("mPw"));
		
		System.out.println(mvo);
		
		MemberVO member=mdao.loginMember(mvo);
		
		request.getSession().setAttribute("id", member.getId());
		request.getSession().setAttribute("mPw", member.getmPw());
//		request.getSession().setAttribute("mName", member.getmName());
//		request.getSession().setAttribute("mAddress", member.getmAddress());
//		request.getSession().setAttribute("mEmail", member.getmEmail());
//		request.getSession().setAttribute("mBanCnt", member.getmBanCnt());
//		request.getSession().setAttribute("mBoardCnt", member.getmBoardCnt());
//		request.getSession().setAttribute("mDate", member.getmDate());
		
		return forward;
	}

}
