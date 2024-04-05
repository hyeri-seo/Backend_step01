package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

@Component("/member/add.do")
public class MemberAddController implements Controller, DataBinding {
	
	MemberDao memberDao;
	
	public MemberAddController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"member", spms.vo.Member.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Member member = (Member)model.get("member");
		
		if(member.getEmail() == null) {
			System.out.println("MemberAddController::execute() - get 요청");
			
			return "/member/MemberForm.jsp";
			
		}else {										// post 요청
			System.out.println("MemberAddController::execute() - post 요청");

			memberDao.insert(member);
			
			return "redirect:list.do";
		}
	}

}
