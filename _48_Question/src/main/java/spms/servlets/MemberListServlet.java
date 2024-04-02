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

		try {
			// Dao 로부터 필요한 객체 생성 후 request에 저장
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			List<Member> members = memberDao.selectList();
			req.setAttribute("members", members);
			
			// jsp이동을 위한 jsp 페이지 정보를 request에 저장
			req.setAttribute("viewUrl", "/member/MemberList.jsp");
			
			// 이제 jsp로 직접 보내는 것이 아니라
			// DispatchServlet에게 jsp 이동을 맡긴다.
			/*
			RequestDispatcher rd = req.getRequestDispatcher(
						"/member/MemberList.jsp");			
			res.setContentType("text/html;charset=UTF-8");
			rd.include(req, res);
			*/

		}catch(Exception e) {
			throw new ServletException(e);
			// 예외처리를 DispatchServlet에서 처리하도록 일원화하기 위해 아래를 주석처리한다.
			/*
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
			*/
		}
	}

}











