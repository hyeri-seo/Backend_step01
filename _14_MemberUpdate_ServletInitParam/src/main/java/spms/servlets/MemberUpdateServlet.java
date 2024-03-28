package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(getInitParameter("driver"));
			conn = DriverManager.getConnection(
					this.getInitParameter("url"),
					this.getInitParameter("username"),
					this.getInitParameter("password")
					);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT mno, email, mname, cre_date FROM members "
					+ "WHERE mno = " + req.getParameter("no")
					);
			// mno 값은 unique하므로 결과는 1개가 나옴 => while문 필요 없음
			// ResultSet의 내부에는 row를 가리키는 Cursor가 존재하는데
			// 처음에는 첫 번째 행 이전을 가리키고 있다.
			// 그러므로 rs.next()를 해야 첫 번째 행을 가리키고 값을 꺼낼 수가 있다.
			rs.next();
			
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>회원정보</title></head>");
			out.println("<body><h1>회원정보</h1>");
			out.println("<form action='update' method='post'>");
			out.println("번호: <input type='text' name='no' value='" +  
					req.getParameter("no") + "' readonly><br>");
			out.println("이메일: <input type='text' name='email'" + 
						" value='" + rs.getString("email") + "'><br>");
			out.println("가입일: "+ rs.getString("cre_date") + "<br>");
			out.println("<input type='submit' value='저장'>");
			out.println("<input type='reset' value='취소' " + 
						"onclick='location.href\"list\"'>");
			out.println("</form>");
			out.println("</body></html>");
		} catch(Exception e) {
			// 톰캣에 예외 던짐
			throw new ServletException(e);
		} finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(conn!=null) conn.close();} catch(Exception e) {}
		}
	}
}
