package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.annotation.Component;

@Component("/auth/logout.do")
public class LogOutController implements Controller {
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		System.out.println("LogOutController::execute() - get 요청");
		
		HttpSession session = (HttpSession) model.get("session");
		session.removeAttribute("member");
		session.invalidate();

		return "redirect:login.do";
	}
}
