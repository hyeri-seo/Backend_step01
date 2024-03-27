package post.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * 브라우저가 보내온 데이터가 한글인 겨애우
		 * 값을 꺼내기 전에 문자셋 설정을 해야 한다.
		 * get요청은 tomcat설정파일에
		 * post요청은 이곳에서 처리한다.
		 */
		resp.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String pass = req.getParameter("password");
		
		System.out.println("LoginServlet::doPost => " + id + " : " + pass);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
		out.println("<h1>로그인 결과</h1>");
		out.println(id + "님을 환영합니다.");
		out.println("</body></html>");
	}
	
	
}
