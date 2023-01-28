package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class UpdateMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("main.jsp");
		forward.setRedirect(true);
		
		MemberDAO mdao=new MemberDAO();
		MemberVO mvo=new MemberVO();
		MemberVO me=new MemberVO();
		
		mvo.setId(request.getParameter("id"));
		
		me=mdao.selectOneMember(mvo);
		
		String mEmail=request.getParameter("mEmail1")+"#"+request.getParameter("mEmail2");
		String mAddress=request.getParameter("mAddress1")
				+"#"+request.getParameter("mAddress2")
				+"#"+request.getParameter("mAddress3");
		
		mvo.setmPw(request.getParameter("mPw"));
		mvo.setmName(request.getParameter("mName"));
		mvo.setmEmail(request.getParameter(mEmail));
		mvo.setmAddress(request.getParameter(mAddress));
		
		if(mvo.getmAddress()==null) {
			mvo.setmAddress(me.getmAddress());
		}
		
		mdao.updateMember(mvo);
		
		return forward;
	}

}
