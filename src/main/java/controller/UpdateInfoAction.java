package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class UpdateInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setPath("/updateInfo.jsp");
		forward.setRedirect(false);

		MemberDAO mdao = new MemberDAO();
		MemberVO mvo = new MemberVO();
		MemberVO me = new MemberVO();
		
		mvo.setId(request.getParameter("id"));
		me=mdao.selectOneMember(mvo);
		
		String mEmail=me.getmEmail();
		String mAddress=me.getmAddress();
		
		String[] arrayE = mEmail.split("#");
		String mEmail1 = arrayE[0];
		String mEmail2 = arrayE[1];
		
		String[] arrayA = mAddress.split("#");
		String mAddress1 = arrayA[0];
		String mAddress2 = arrayA[1];
		String mAddress3 = arrayA[2];
		
		
		request.getSession().setAttribute("mEmail1", mEmail1);
		request.getSession().setAttribute("mEmail2", mEmail2);
		request.getSession().setAttribute("mAddress1", mAddress1);
		request.getSession().setAttribute("mAddress2", mAddress2);
		request.getSession().setAttribute("mAddress3", mAddress3);
		
		return forward;
	}

}
