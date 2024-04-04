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

import spms.bind.DataBinding;
import spms.bind.ServletRequestDataBinder;
import spms.controls.Controller;

@SuppressWarnings("serial")
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	
	private void prepareRequestData(HttpServletRequest req, 	// 브라우저가 보내는 Parameter
									Map<String, Object> model, 	// 자동생성된 객체를 저장할 Model 객체
									DataBinding dataBinding)	// Controller에서 자동 생성 정보 추출을 위한 인터페이스
											throws Exception {
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;		// 생성할 객체 이름
		Class<?> dataType = null;	// 데이터 클래스 타입
		Object dataObj = null;		// 생성된 객체
		
		// MemberUpdateContorller의 getDataBinders를 보면 왜 +=2로 한지 알 수 있음
		for(int i=0; i<dataBinders.length; i+=2) {
			dataName = (String)dataBinders[i];
			dataType = (Class<?>)dataBinders[i+1];
			
			// 데이터 객체 생성
			 dataObj = ServletRequestDataBinder.bind(req, dataType, dataName);
			
			// 생성 데이터 model에 저장
			model.put(dataName, dataObj);
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
				resp.setContentType("text/html; charset=UTF-8");
				String servletPath = req.getServletPath();
				System.out.println("DispatchServlet::service() - servletPath=" + servletPath);
		
				Map<String, Object> model = new ConcurrentHashMap<>();
				// model.put("memberDao", this.getServletContext().getAttribute("memberDao"));
				model.put("session", req.getSession());
		
				// 해당 주소와 일치하는 클래스 객체를 꺼내온다.
				Controller pageController = (Controller) this.getServletContext().getAttribute(servletPath);
					
				if(pageController instanceof DataBinding) {
					prepareRequestData(req, model, (DataBinding)pageController);
				}
				
				System.out.println("DispatchServlet::service() - pageController=" + pageController.getClass().getName());
				String viewUrl = pageController.execute(model);
	
				for (String key : model.keySet()) {
					req.setAttribute(key, model.get(key));
				}
	
				System.out.println("DispatchServlet::service() - viewUrl=" + viewUrl);
				System.out.println("");
	
				if (viewUrl.startsWith("redirect:")) {
					resp.sendRedirect(viewUrl.substring("redirect:".length()));
					return;
				} else {
					RequestDispatcher rd = req.getRequestDispatcher(viewUrl);
					rd.include(req, resp);
				}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
	}
}