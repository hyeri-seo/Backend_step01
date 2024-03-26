package _01_FirstWebProject;

import java.io.IOException;
import java.io.PrintWriter;

// javax는 톰캣이 제공하는 애들
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/calc")
public class CalculatorServlet extends GenericServlet {

	// 요청(ServletRequest)과 응답(ServletResponse)
	// request 내에는 calculator.html이 보내는 v1, v2, op의 값이 전달됨
	// 톰캣 서버가 http protocol과 서블릿 규격에 맞춰서 알아서 해줌
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		// 전송된 값을 꺼낸다.
		String operator = request.getParameter("op");
		int v1 = Integer.parseInt(request.getParameter("v1"));
		int v2 = Integer.parseInt(request.getParameter("v2"));
		int result = 0;
		
		// 한글이 깨지지 않도록 설정
		// 서블릿이 최초 만들어진 규격이 정해진 시기의 문자셋이 UTF-8이 아니어서
		// 이렇게 정해줘야 HTML이 보내는 데이터를 UTF-8로 해석해서 깨지지 않음
		response.setContentType("text/html;charset=UTF-8");
		
		// 브라우저로 응답을 전송
		// response 안에 소켓 스트림이 연결되어 있음
		// 그러므로 연결된 Wrapper 클래스를 얻어서 전송함
		// PrintWriter는 buffer, socket 객체까지 연결되는 구조
		PrintWriter out = response.getWriter();
		
		switch(operator) {
		case "+": result = v1 + v2; break;
		case "-": result = v1 - v2; break;
		case "*": result = v1 * v2; break;
		case "/": 
			if(v2 == 0) {
				out.println("0으로 나눌 수 없습니다");
				return;
			}
			result = v1 / v2;
			break;
		}
		
		// out 내부의 소켓 out스트림을 통해서 브라우저까지 전달됨
		out.println(v1 + " " + operator + " " + v2 + " = " + result);
	}

}
