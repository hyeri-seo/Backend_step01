package spms.servlets;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/* 이 서블릿의 목적은 브라우저의 요청을 응답하기 위한 것이 아니다.
 * 이 서블릿은 어플리케이션이 tomcat에 의해 실행될 때
 * 서블릿 컨테이너도 생성되는 데, 이 시점에 자동으로 호출되어서 객체가 생성되도록 한다.
 * 어플리케이션 어느 곳에서든 접근 가능한 ServletContext 공유 공간이 생기는데
 * 이 공간에 데이터베이스 Connection 객체를 생성해서
 * 이후의 요청을 처리하는 서블릿들이 Connection객체를 꺼내어 쓸 수 있도록 하는 역할이다.
 * 
 * 그래서 여기서는 service/doGet/doPost를 사용하지 않고
 * LifeCycle 시작인 init()과 종료인 destory()를 사용한다. 
 * */

@SuppressWarnings("serial")
public class AppInitServlet extends HttpServlet{

	@Override
	public void init() throws ServletException {
		/* 데이터베이스 Connection객체를 생성해서 ServletContext 영역에 저장한다.
		 * 다른 서블릿들이 꺼내어 쓸 수 있도록
		 * */
		System.out.println("AppInitServlet::init() 호출");

		try {
			// ServletContext 영역(Application 전체 공유 영역) 객체 얻기
			ServletContext sc = this.getServletContext();
			
			Class.forName(sc.getInitParameter("driver"));
			Connection conn = DriverManager.getConnection(
							sc.getInitParameter("url"),
							sc.getInitParameter("username"),
							sc.getInitParameter("password")
					);
			
			// ServletContext 공간에 저장
			sc.setAttribute("conn", conn);
			
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	@Override
	public void destroy() {
		/* 데이터베이스 Connection 객체를 close()한다.
		 * */
		System.out.println("AppInitServlet::destroy() 호출");
		Connection conn = (Connection)this.getServletContext().getAttribute("conn");
		try {
			if(conn != null && conn.isClosed()==false)
				conn.close();
			
		}catch(Exception e) {}
	}
}


















