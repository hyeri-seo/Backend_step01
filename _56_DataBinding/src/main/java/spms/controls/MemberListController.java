package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;


public class MemberListController implements Controller{
	
	MemberDao memberDao;
	
	public MemberListController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		System.out.println("MemberListController::execute() 호출");
		
		//MemberDao memberDao = (MemberDao)model.get("memberDao");
		
		model.put("members", memberDao.selectList());
		return "/member/MemberList.jsp";
	}	
	
}











