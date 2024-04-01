package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/add")
@SuppressWarnings("serial")
public class MemberAddServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberAddServlet::doGet() 호출");
		
		RequestDispatcher rd = req.getRequestDispatcher(
				"/member/MemberForm.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberAddServlet::doPost() 호출");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"),	// JDBC url
					sc.getInitParameter("username"),								// id
					sc.getInitParameter("password"));
//			stmt = conn.prepareStatement(
//					"INSERT INTO members(email,pwd,mname,cre_date,mod_date)" + 
//					" VALUES(?,?,?,NOW(),NOW())"
//					);
//			stmt.setString(1,  req.getParameter("email"));
//			stmt.setString(2,  req.getParameter("password"));
//			stmt.setString(3,  req.getParameter("name"));
//			stmt.executeUpdate();
//			resp.sendRedirect("list");
			
			// 추가 ----------------------------------------
			Member member = new Member();
			member.setEmail(req.getParameter("email"));
			member.setPassword(req.getParameter("password"));
			member.setName(req.getParameter("name"));
			
			MemberDao memberDao = new MemberDao();
			memberDao.insert(member);
			resp.sendRedirect("list");
			
		}catch(Exception e) {
			//throw new ServletException(e);
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
			
		}finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(conn!=null) conn.close();} catch(Exception e) {}
		}
	}

}































