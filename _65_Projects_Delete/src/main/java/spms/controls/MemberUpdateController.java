package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

@Component("/member/update.do")
public class MemberUpdateController implements Controller, DataBinding {
	
	MemberDao memberDao;
	
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class,
				"member", spms.vo.Member.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {

		Member member = (Member)model.get("member");
		
		if(member.getEmail() == null) {
			System.out.println("MemberUpdateController::execute() - get 요청");
			Integer no = (Integer) model.get("no");
			Member deatailInfo = memberDao.selectOne(no);
			model.put("member", deatailInfo);
			return "/member/MemberUpdateForm.jsp";

		} else { // post 요청
			System.out.println("MemberUpdateController::execute() - post 요청");
			memberDao.update(member);
			return "redirect:list.do";
		}
	}
}
