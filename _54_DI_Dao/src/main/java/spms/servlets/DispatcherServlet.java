package spms.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.controls.Controller;
import spms.controls.LogInController;
import spms.controls.LogOutController;
import spms.controls.MemberAddController;
import spms.controls.MemberDeleteController;
import spms.controls.MemberListController;
import spms.controls.MemberUpdateController;
import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=UTF-8");

		String servletPath = req.getServletPath();
		System.out.println("DispatchServlet::service() - servletPath=" + servletPath);

		Map<String, Object> model = new ConcurrentHashMap<>();
		//model.put("memberDao", this.getServletContext().getAttribute("memberDao"));
		model.put("session", req.getSession());

		// 해당 주소와 일치하는 클래스 객체를 꺼내온다.
		Controller pageController = (Controller)this.getServletContext().getAttribute(servletPath);
		
		try {
			String pageControllerPath = null;

			if("/member/list.do".equals(servletPath)) {
				//pageController = new MemberListController();
			}else if("/member/add.do".equals(servletPath)) {
				//pageController = new MemberAddController();
				if(req.getParameter("email") != null) {
					model.put("member", new Member()
							.setEmail(req.getParameter("email"))
							.setPassword(req.getParameter("password"))
							.setName(req.getParameter("name")));
				}

			}else if("/member/update.do".equals(servletPath)) {
				//pageController = new MemberUpdateController();
				if(req.getParameter("email") != null) {
					model.put("member",  new Member()
								.setNo(Integer.parseInt(req.getParameter("no")))
								.setEmail(req.getParameter("email"))
								.setName(req.getParameter("name")));
				}else {
			          model.put("no", Integer.parseInt(req.getParameter("no")));
		        }
			}else if("/member/delete.do".equals(servletPath)) {
				//pageController = new MemberDeleteController();
				model.put("no", Integer.parseInt(req.getParameter("no")));
			}else if("/auth/login.do".equals(servletPath)) {
				//pageController = new LogInController();
		        if (req.getParameter("email") != null) {
		            model.put("loginInfo", new Member()
		              .setEmail(req.getParameter("email"))
		              .setPassword(req.getParameter("password")));
		        }
			}else if("/auth/logout.do".equals(servletPath)) {
				//pageController = new LogOutController();
			}
			
			String viewUrl = "";		// 다음에 이동할 jsp나 redirect경로
			
			// pageController 객체가 존재한다면
			if(pageController != null) {
				System.out.println("DispatchServlet::service() - pageController=" 
													+ pageController.getClass().getName());
				viewUrl = pageController.execute(model);
				
				for(String key : model.keySet()) {
					req.setAttribute(key, model.get(key));
				}
			}
			// 아직 pageController가 존재하지 않고, Servlet으로 되어 있을 때
			else {
				System.out.println("DispatchServlet::service() - pageController=" + pageControllerPath);
			}		
			
			System.out.println("DispatchServlet::service() - viewUrl=" + viewUrl);
			System.out.println("");
			
			if(viewUrl.startsWith("redirect:")) {
				resp.sendRedirect(viewUrl.substring("redirect:".length()));
				return;
			}else {
				RequestDispatcher rd = req.getRequestDispatcher(viewUrl);
				rd.include(req, resp);
			}
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
	}
}










