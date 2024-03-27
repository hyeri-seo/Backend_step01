package study01.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * tomcat이 실행될 때 자동으로 서비스에 참여하는 '서블릿 객체'가 되기 위해서는
 * 1) Servlet인 인터페이스를 상속 받아야 한다.
 * 2) @WebServlet 어노테이션이나 web.xml에 서블릿 객체로 등록해야 한다.
 */
public class HelloWorld implements Servlet {

	ServletConfig config;
	
	@Override
	public void destroy() {
		System.out.println("destory() 호출");
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig() 호출");
		return this.config;
	}

	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo() 호출");
		return "ver=1.0;author=albert;copyright=alber 2024";
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("init() 호출");
		this.config = config;
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {

		System.out.println("service() 호출");
	}

}
