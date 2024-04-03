package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

public class LogInController implements Controller, DataBinding {	
	
	MemberDao memberDao;
	
	public LogInController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	/**
	 * LogInController가 execute()를 실행하기 위해서는
	 * loginInfo라는 이름의 Member 클래스 객체가 필요함.
	 * 이 객체를 자동으로 생성해서 담아주기를 바람.
	 */
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"loginInfo", spms.vo.Member.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if (model.get("loginInfo") == null) { // 입력폼을 요청할 때
			System.out.println("LogInController::execute() - get 요청");
			
			return "/auth/LogInForm.jsp";

		} else { // 회원 등록을 요청할 때
			System.out.println("LogInController::execute() - post 요청");
			
			//MemberDao memberDao = (MemberDao) model.get("memberDao");
			Member loginInfo = (Member) model.get("loginInfo");

			Member member = memberDao.exist(loginInfo.getEmail(), loginInfo.getPassword());

			if (member != null) {
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("member", member);
				return "redirect:../member/list.do";
			} else {
				return "/auth/LogInFail.jsp";
			}
		}
	}

}
