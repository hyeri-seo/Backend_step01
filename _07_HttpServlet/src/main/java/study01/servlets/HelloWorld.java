package study01.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet 인터페이스(서블릿 생태주기 최상위) - 나머지도 모두 구현해야 한다. - service() GenericServlet 추상
 * 클래스: implemented Servlet - service() - service()로 get/post 모두 전달된다.
 * HttpServlet 추상 클래스(F3으로 확인 가능) extends GenericServlet - doGet()은 get 요청 처리 -
 * doPost()는 post 요청 처리
 */

@WebServlet("/Hello")
@SuppressWarnings("serial")
public class HelloWorld extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("init() 호출");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet() 호출");
	}

	@Override
	public void destroy() {
		System.out.println("destory() 호출");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service() 호출");
		super.service(req, resp);		// 내부에서 doGet()과 doPost()로 분기
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost() 호출");
	}
}
