package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/list")
@SuppressWarnings("serial")
public class MemberListServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MemberListServlet::doGet() 호출");
		
		Connection conn = null;	
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			List<Member> members = memberDao.selectList();

			/*
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT mno, mname, email, cre_date" +
								 " FROM members" +
								 " ORDER BY mno ASC");

			List<Member> members = new ArrayList<>();
			while(rs.next()) {
				members.add(new Member()
								.setNo(rs.getInt("mno"))
								.setName(rs.getString("mname"))
								.setEmail(rs.getString("email"))
								.setCreatedDate(rs.getDate("cre_date"))
						);
			}
			*/

			req.setAttribute("members", members);
			RequestDispatcher rd = req.getRequestDispatcher(
						"/member/MemberList.jsp");			
			res.setContentType("text/html;charset=UTF-8");
			rd.include(req, res);

		}catch(Exception e) {
			//throw new ServletException(e);
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
		}finally {
			// 생성한 역순으로 닫아준다.
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			/* ServletContext에 공유하는 Connection는 닫으면 다른 곳에서 사용 못한다.
			 * */
			//try {if(conn!=null) conn.close();} catch(Exception e) {}
		}
	}

}











