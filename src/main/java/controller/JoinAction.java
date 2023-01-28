package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("registerOK.jsp");
		forward.setRedirect(true);
		
		MemberDAO mdao=new MemberDAO();
		MemberVO mvo=new MemberVO();
		
		String mEmail=request.getParameter("mEmail1")+"#"+request.getParameter("mEmail2");
		String mAddress=request.getParameter("mAddress1")
				+"#"+request.getParameter("mAddress2")
				+"#"+request.getParameter("mAddress3");
		
		mvo.setId(request.getParameter("id"));
		mvo.setmPw(request.getParameter("mPw"));
		mvo.setmName("mName");
		mvo.setmAddress(mAddress);
		mvo.setmEmail(mEmail);
		
		mdao.insertMember(mvo);
		
		return forward;
	}

}
