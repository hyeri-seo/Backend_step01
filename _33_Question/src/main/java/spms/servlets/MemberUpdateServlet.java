package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

@WebServlet("/member/update")
@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberUpdateServlet::doGet() 호출");
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
						sc.getInitParameter("url"),
						sc.getInitParameter("username"),
						sc.getInitParameter("password")
					);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT mno,email,mname,cre_date FROM members" +
					" WHERE mno=" + req.getParameter("no")
					);
			/*
			 mno값은 unique하므로 결과는 1개가 나오므로 while문은 필요없다.
			 ResultSet의 내부에는 row를 가리키는 Cursor가 존재하는데
			 처음에는 1번째 행 이전을 가리키고 있다.
			 그러므로 rs.next()를 해야 1번째 행을 가리키고 값을 꺼낼 수가 있다.
			*/
			
			if (rs.next()) {
				req.setAttribute("member", 
					new Member()
						.setNo(rs.getInt("MNO"))
						.setEmail(rs.getString("EMAIL"))
						.setName(rs.getString("MNAME"))
						.setCreatedDate(rs.getDate("CRE_DATE")));
				
			} else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
			
			RequestDispatcher rd = req.getRequestDispatcher(
					"/member/MemberUpdateForm.jsp");
			rd.forward(req, resp);			
			
			/*
			rs.next();
			
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>회원정보</title></head>");
			out.println("<body><h1>회원정보</h1>");
			out.println("<form action='update' method='post'>");
			out.println("번호: <input type='text' name='no' value='" + 
						req.getParameter("no") + "' readonly><br>");
			out.println("이름: <input type='text' name='name'" +
						" value='" + rs.getString("mname") + "'><br>");
			out.println("이메일: <input type='text' name='email'" + 
						" value='" + rs.getString("email") + "'><br>");
			out.println("가입일: " + rs.getDate("CRE_DATE") + "<br>");
			out.println("<input type='submit' value='저장'>");
			out.println("<input type='button' value='삭제' "
					+ "onclick='location.href=\"delete?no=" + 
					req.getParameter("no") + "\";'>");
			out.println("<input type='button' value='취소'" + 
				" onclick='location.href=\"list\"'>");
			out.println("</form>");
			out.println("</body></html>");
			*/
		}catch(Exception e) {
			//throw new ServletException(e);
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(conn!=null) conn.close();} catch(Exception e) {}			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberUpdateServlet::doPost() 호출");
		
		// CharacterEncodingFilter로 전처리 했으므로 이제 안해도 됨
		//req.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
						sc.getInitParameter("url"),
						sc.getInitParameter("username"),
						sc.getInitParameter("password")
					);
			stmt = conn.prepareStatement(
						"UPDATE members SET email=?, mname=?, mod_date=NOW()"
					+ " WHERE mno=?"
					);
			stmt.setString(1,  req.getParameter("email"));
			stmt.setString(2,  req.getParameter("name"));
			stmt.setInt(3,  Integer.parseInt(req.getParameter("no")));
			stmt.executeUpdate();
			
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












