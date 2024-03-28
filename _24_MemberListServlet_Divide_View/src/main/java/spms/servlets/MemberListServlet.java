package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

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
			// DB 테이블 전체 리스트를 가져와서 rs에 저장한 상태
			rs = stmt.executeQuery("SELECT mno, mname, email, cre_date "
					+ "FROM members "
					+ "ORDER BY mno ASC");
			/*
			 * 회원 목록을 list 객체로 생성
			 * MemberList.jsp를 호출하면서 list 객체를 전달
			 */
			List<Member> members = new ArrayList<>();
			while(rs.next()) {
				members.add(new Member()
							.setNo(rs.getInt("mno"))
							.setName(rs.getString("mname"))
							.setEmail(rs.getString("email"))
							.setCreatedDate(rs.getDate("cre_date"))
						);
			}
			
			// request 공간에 members라는 key 값으로 list를 저장함
			// 그러면 MemberList.jsp에서는 members라는 key값으로 꺼낼 수 있음
			req.setAttribute("members", members);
			
			// MemberListServlet 객체에서 MemberList.jsp로
			// reques 객체와 response 객체를 전달함
			RequestDispatcher rd = req.getRequestDispatcher(
						"/member/MemberList.jsp");
			// include 방식으로 전달함
			rd.include(req, res);
			
			/*
			 * html 코드와 html 코드에 java 값을 전달하는 부분
			 */
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<html><head><title>회원 목록</title></head>");
			out.println("<body><h1>회원 목록</h1></body>");
			out.println("<p><a href='add'>신규 회원</a></p>");
			
			while(rs.next()) {
				out.println(
						rs.getInt("mno") + ", " + 
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
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(conn!=null) conn.close();} catch(Exception e) {}
		}
	}
	
}
