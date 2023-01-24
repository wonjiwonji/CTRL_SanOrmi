package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		System.out.println("uri: "+uri);
		String cp=request.getContextPath();
		System.out.println("cp: "+cp);
		String command=uri.substring(cp.length());
		System.out.println("command: "+command);

		ActionForward forward=null;
		if(command.equals("/main.do")) {
			try {
				forward=new MainAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/login.do")) {
			try {
				forward=new LoginAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/logout.do")) {
			try {
				forward=new LogoutAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/join.do")) {
			try {
				forward=new JoinAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/joinKakao.do")) {
			try {
				forward=new JoinKakaoAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/joinNaver.do")) {
			try {
				forward=new JoinNaverAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/updateMember.do")) {
			try {
				forward=new DeleteBoardAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/deleteMember.do")) {
			try {
				forward=new DeleteMemberAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/insertBoard.do")) {
			try {
				forward=new InsertBoardAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/updateBoard.do")) {
			try {
				forward=new UpdateBoardAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/deleteBoard.do")) {
			try {
				forward=new DeleteBoardAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/insertBComment.do")) {
			try {
				forward=new InsertBCommentAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/insertBCComment.do")) {
			try {
				forward=new InsertBCCommentAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/deleteBComment.do")) {
			try {
				forward=new DeleteBCommentAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/insertQNA.do")) {
			try {
				forward=new InsertQNAAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/updateQNA.do")) {
			try {
				forward=new UpdateQNAAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/deleteQNA.do")) {
			try {
				forward=new DeleteQNAAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/insertQComment.do")) {
			try {
				forward=new InsertQCommentAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/insertQCComment.do")) {
			try {
				forward=new InsertQCCommentAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/deleteQComment.do")) {
			try {
				forward=new DeleteQCommentAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/communityKing.do")) {
			try {
				forward=new CommunityKingAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/report.do")) {
			try {
				forward=new ReportAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/acceptReport.do")) {
			try {
				forward=new AcceptReportAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/cancleReport.do")) {
			try {
				forward=new CancleReportAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		else if(command.equals("/adminPage.do")) {
			try {
				forward=new AdminPageAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/agree.do")) {
			try {
				forward=new AgreeAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/charts.do")) {
			try {
				forward=new ChartsAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/comment.do")) {
			try {
				forward=new CommentAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/forgot.do")) {
			try {
				forward=new ForgotAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/freeBoards.do")) {
			try {
				forward=new FreeBoardsAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/loginPage.do")) {
			try {
				forward=new LoginPageAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/manageBoards.do")) {
			try {
				forward=new ManageBoardsAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/manageBoardsDetail.do")) {
			try {
				forward=new ManageBoardsDetailAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/manageMem.do")) {
			try {
				forward=new ManageMemAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/manageMemDetail.do")) {
			try {
				forward=new ManageMemDetailAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/myBoard.do")) {
			try {
				forward=new MyBoardAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/myeongsan.do")) {
			try {
				forward=new MyeongsanAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/qna.do")) {
			try {
				forward=new QnAAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/register.do")) {
			try {
				forward=new RegisterAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/updateInfo.do")) {
			try {
				forward=new UpdateInfoAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/view.do")) {
			try {
				forward=new ViewAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/writeBoardFree.do")) {
			try {
				forward=new WriteBoardFreeAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/writeBoardQnA.do")) {
			try {
				forward=new WriteBoardQnAAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward==null) {
			forward=new ActionForward();
			forward.setPath("/error404.jsp");
			forward.setRedirect(false);
		}

		if(forward.isRedirect()) {
			System.out.println("redirect");
			response.sendRedirect(forward.getPath());
		}
		else {
			System.out.println("forward");
			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}

}