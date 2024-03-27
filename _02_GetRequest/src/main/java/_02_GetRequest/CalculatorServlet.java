package _02_GetRequest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
	
	private Hashtable<String, Operator> operatorTable = new Hashtable<>();
	public CalculatorServlet() {
		operatorTable.put("+", new AddOperator());
		operatorTable.put("-", new SubOperator());
		operatorTable.put("*", new MulOperator());
		operatorTable.put("/", new DivOperator());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("CalculatorServlet - doGet......");
		
		// 보내기 전에 UTF-8로 알려줘서 한글이 깨지지 않도록 설정
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();		// 객체 내부에 tcp소켓 스트림이 존재함
		
		// 브라우저가 보낸 파라미터를 꺼낸다.
		String op = req.getParameter("op");
		if(req.getParameter("v1") == null || req.getParameter("v2") == null) {
			return;
		}
		
		double v1 = Double.parseDouble(req.getParameter("v1"));
		double v2 = Double.parseDouble(req.getParameter("v2"));
		
		// 브라우저에 표현될 html
		out.println("<html><body>");
		out.println("<h1>계산 결과</h1>");
		out.println("결과: ");
		
		try {
			// 연산자에 따라 처리할 클래스를 꺼냄: 업무가 복잡해질 때는 분업. 다형성으로 동작함
			Operator operator = operatorTable.get(op);
			if(operator == null)
				out.println("존재하지 않는 연산자입니다.");
			else {
				// 사칙 연산 클래스 모두 Operator의 상속을 받았으므로 해당 클래스의 메소드가 자동으로 호출됨
				// 다형성
				double result = operator.execute(v1, v2);
				out.println(String.format("%.3f", result));
			}
		} catch(Exception e) {
			out.println("연산 오류가 발생했습니다.");
		}
		out.println("</body></html>");
	}
	
	
}
