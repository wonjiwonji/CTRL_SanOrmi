package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

@WebServlet("/check")
public class CheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CheckController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mdao=new MemberDAO();
		MemberVO mvo=new MemberVO();
		mvo.setId(request.getParameter("id"));
		MemberVO member=mdao.selectOneMember(mvo);
		
		if(member==null) {
			response.getWriter().println("1");
		}
		else {
			response.getWriter().println("-1");
		}
	}

}
