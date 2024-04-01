package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/auth/login")
public class LogInServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/auth/LogInForm.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
		
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			/*
		      ServletContext sc = this.getServletContext();
		      conn = (Connection) sc.getAttribute("conn"); 

		      MemberDao memberDao = new MemberDao();
		      memberDao.setConnection(conn);
		      */
		      
		      Member member = memberDao.exist(
		    		  req.getParameter("email"), 
		    		  req.getParameter("password"));
		      if (member != null) {
		        HttpSession session = req.getSession();
		        session.setAttribute("member", member);
		        resp.sendRedirect("../member/list");

		      } else {
		        RequestDispatcher rd = req.getRequestDispatcher(
		            "/auth/LogInFail.jsp");
		        rd.forward(req, resp);
		      }			
			
		}catch(Exception e) {
			
		}
		/*
		finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
		}
		*/
	}
}















