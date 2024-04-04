package spms.controls;

import java.util.Map;

import spms.bind.DataBinding;
import spms.dao.MemberDao;

public class MemberDeleteController implements Controller, DataBinding {
	
	MemberDao memberDao;
	
	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		System.out.println("MemberDeleteController::execute() - get 요청");
		
		Integer no = (Integer) model.get("no");
		memberDao.delete(no);

		return "redirect:list.do";
	}

}
