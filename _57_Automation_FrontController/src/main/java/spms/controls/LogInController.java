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
		
		// 현재 execute에서 사용하는 객체를 자동생성하는 방식이
		// get/post 가리지 않고 무조건 객체를 생성하는 방식이라
		// 기존의 loginInfo가 존재하는지로는 판단할 수 없어서
		// loginInfo에 emial 정보가 존재하는지로 조건을 변경함
		Member loginInfo = (Member)model.get("loginInfo");
		
		if(model.get("member") == null) {// 입력폼을 요청할 때	// get 요청

			System.out.println("LogInController::execute() - get 요청");
			
			return "/auth/LogInForm.jsp";

		} else { // 회원 등록을 요청할 때
			System.out.println("LogInController::execute() - post 요청");
			
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
