package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/list")
@SuppressWarnings("serial")
public class MemberListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;		// DB 서버와의 연결 객체
		Statement stmt = null;		// SQL문
		ResultSet rs = null;		// Select문의 결과
		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
						sc.getInitParameter("url"),
						sc.getInitParameter("username"),
						sc.getInitParameter("password")
					);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT mno, mname, email, cre_date "
					+ "FROM members "
					+ "ORDER BY mno ASC");
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<html><head><title>회원 목록</title></head>");
			out.println("<body><h1>회원 목록</h1></body>");
			/* 신규 회원 추가 */
			/**
			 * href = '/add' => 절대경로
			 * 	localhost:9999/<contextRoot>/add
			 * href = 'add'	 => 상대경로 
			 * 	localhost:9999/<contextRoot>/member/add
			 */
			out.println("<p><a href='add'>신규 회원</a></p>");
			
			// 더 이상 가져온 데이터가 없을 때 false가 되어 실행문이 멈춤
			while(rs.next()) {
				out.println(
						rs.getInt("mno") + ", " + 
						// /member/update로 get요청을 하는데 mno값을 가지고 이동해라
						"<a href='update?no=" + rs.getInt("mno") + "'>" +
						rs.getString("mname") + "</a>, " + 
						rs.getString("email") + ", " + 
						rs.getDate("cre_date") + " " +
						"<a href='delete?no=" + rs.getInt("mno") + "'>[삭제]</a><br>"
						);
			}
			out.println("</body></html>");
			
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			// 생성한 역순으로 닫아준다.
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(conn!=null) conn.close();} catch(Exception e) {}
		}
	}
	
}
