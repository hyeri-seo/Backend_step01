package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 1. 사용자에게 회원을 등록하는 화면을 클라이언트에게 전송(get)
// 2. 입력한 것을 추가할 때 받아서 처리함(post)
@WebServlet("/member/add")
@SuppressWarnings("serial")
public class MemberAddServlet extends HttpServlet {
	
	// db에서 가져와서 화면에 보여주는 역할
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>회원등록</title></head>");
		out.println("<body><h1>회원 등록</h1>");
		out.println("<form action='add' method='post'>");
		out.println("이름 : <input type='text' name='name'><br>");
		out.println("이메일 : <input type='email' name='email'><br>");
		out.println("암호 : <input type='password' name='password'><br>");
		out.println("<input type='submit' value='추가'>");
		out.println("<input type='reset' value='취소'>");
		out.println("</form>");
		out.println("</body></html>");
	}
	
	// 클라이언트가 보낸 정보를 db에 처리하는 역할
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 이 설정을 안 해주면 한글이 깨짐
		req.setCharacterEncoding("UTF-8");
		Connection conn = null;
		
		/**
		 * Statement
		 * 	- 질의할 때마다 sql을 컴파일한다.
		 * 	- 입력 매개변수가 여러 개 필요할 때 문자열 결합연산자인 +를 이용해서 해야 한다. (불편)
		 * 	- 전송 직전에 sql문을 입력받고, 컴파일 한 후 서버로 전송한다.
		 * 
		 * PreparedStatement
		 * 	- sql문을 미리 입력하여 컴파일한 상태에서 객체를 받는다.
		 * 	- 만약에 sql문 구조가 변경되지 않고, 파라미터값만 바뀌는 경우 Statement보다 훨씬 빠르다.
		 * 	- 입력 매개변수가 여러 개 필요할 때 ?로 sql문의 파라미터를 표시하고, 나중에 전달하므로 편하다.
		 * 	- Statement < PreparedStatement를 사용한다.
		 */
		PreparedStatement pstmt = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:4306/studydb",
					"study",
					"study"
					);
			pstmt = conn.prepareStatement(
					"INSERT INTO members(email, pw, mname, cre_date, mod_date) "
					+ "VALUES(?,?,?,NOW(),NOW())"
					);
			pstmt.setString(1, req.getParameter("email"));
			pstmt.setString(2, req.getParameter("password"));
			pstmt.setString(3, req.getParameter("name"));
			pstmt.executeUpdate();
			
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>회원등록결과</title></head>");
			// 아래와 같은 코드이다. 1초 후에 Refresh
			out.println("<meta http-equiv='Refresh' content='1;url=list'>");
			out.println("<body>");
			out.println("<p>등록 성공입니다</p>");
			out.println("</body></html>");
			
			// 1초 후에 화면이 바뀌면서 상대경로 list로 이동하라. 콜론 아니고 세미콜론
			// 위에처럼 해도 됨
			// resp.addHeader("Refresh", "1;url=list");
		} catch(Exception e) {
			throw new ServletException(e);
		} finally {
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {}
			try {if(conn!=null) conn.close();} catch(Exception e) {}
		}
	}
}
