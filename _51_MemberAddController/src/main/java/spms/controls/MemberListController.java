package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;


public class MemberListController implements Controller{

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		System.out.println("MemberListController::execute() 호출");
		
		// request 공간 대신 model로부터 꺼내어 사용한다.
		MemberDao memberDao = (MemberDao)model.get("memberDao");
		
		// request 공간 대시 model에 저장한다.
		model.put("members", memberDao.selectList());
		
		// redirect나 이동할 jsp 경로를 리턴한다.
		return "/member/MemberList.jsp";
	}	
	
}











