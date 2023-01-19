package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

// type: 'POST',
// url: 'check',
//  => check라는 URL을 향해 POST방식으로 요청
@WebServlet("/check")
public class CheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	// type: 'POST',
	// url: 'check',
	//  => check라는 URL을 향해 POST방식으로 요청
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mdao=new MemberDAO();
		MemberVO mvo=new MemberVO();
		mvo.setId(request.getParameter("id")); // data: {id:id},
		MemberVO member=mdao.selectOneMember(mvo);
		
		if(member==null) {
			// response.getWriter() == JSP 내장객체 out에 해당함
			response.getWriter().println("1"); // 중복아님. 가입가능
		}
		else {
			response.getWriter().println("-1"); // 중복임. 가입불가능
		}
	}

}
