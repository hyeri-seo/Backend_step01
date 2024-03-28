package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/member/update"},
		initParams = {
				@WebInitParam(name="driver", value="com.mysql.cj.jdbc.Driver"),
				@WebInitParam(name="url", value="jdbc:mysql://localhost:4306/studydb"),
				@WebInitParam(name="username", value="study"),
				@WebInitParam(name="password", value="study")
		}
		)
@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(this.getInitParameter("driver"));
			conn = DriverManager.getConnection(
						this.getInitParameter("url"),
						this.getInitParameter("username"),
						this.getInitParameter("password")
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
			out.println("<input type='submit' value='저장'");
			out.println("<input type='button' value='취소'" + 
						" onclick='location.href=\"list\"'>");
			out.println("</form>");
			
			out.println("</body></html>");
			
		}catch(Exception e) {
			throw new ServletException(e);
		}finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(conn!=null) conn.close();} catch(Exception e) {}			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글이 깨지지 않기 위해 값을 꺼내기 전에 설정
		req.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(getInitParameter("driver"));
			conn = DriverManager.getConnection(
					this.getInitParameter("url"),
					this.getInitParameter("username"),
					this.getInitParameter("password")
					);
			pstmt = conn.prepareStatement(
					"UPDATE members SET email=?, mname=?, mod_date=NOW() "
					+ "WHERE mno=?"
					);
			pstmt.setString(1, req.getParameter("email"));
			pstmt.setString(2, req.getParameter("name"));
			pstmt.setInt(3, Integer.parseInt(req.getParameter("no")));
			pstmt.executeUpdate();
			
			resp.sendRedirect("list");
		} catch(Exception e) {
			throw new ServletException();
		} finally {
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {}
			try {if(conn!=null) conn.close();} catch(Exception e) {}
			
		}
	}
}
