package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
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
public class MemberAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberAddServlet::doGet() 호출");

		// DispatchServlet에 jsp이동을 맡긴다.
		req.setAttribute("viewUrl", "/member/MemberForm.jsp");

		/*
		 * RequestDispatcher rd = req.getRequestDispatcher( "/member/MemberForm.jsp");
		 * rd.forward(req, resp);
		 */
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberAddServlet::doPost() 호출");

		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			Member member = (Member)req.getAttribute("member");
			memberDao.insert(member);
			
			req.setAttribute("viewUrl", "redirect:list.do");
			
			/*
			memberDao.insert(new Member()
					.setEmail(req.getParameter("email"))
					.setPassword(req.getParameter("password"))
					.setName(req.getParameter("name")));

			resp.sendRedirect("list");
			*/
		} catch (Exception e) {
			throw new ServletException(e);
			/*
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
			*/
		}
	}

}
