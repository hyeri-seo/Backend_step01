package spms.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import spms.context.ApplicationContext;


@WebListener
public class ContextLoaderListener implements ServletContextListener{
	
	/* ContextLoaderListener는 WebApp이 시작될 때 이벤트 통보를 받는 역할이므로
	 * 이후에는 사용하지 않는다.
	 * 그러므로 객체를 별도로 접근하지 않기 때문에
	 * 객체를 통해 메서드를 호출하지 않고
	 * 클래스를 통해서 어디에서나 호출할 수 있도록
	 * static을 사용한다.
	 * 아래처럼 접근해서 사용하려는 의도
	 * ApplicationContext appContext = ContextLoaderListener.getApplicationContext();
	 * */
	static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener::contextInitialized() 호출");
		
		try {
			ServletContext sc = sce.getServletContext();
			
			String propertiesPath = sc.getRealPath(
							sc.getInitParameter("contextConfigLocation"));
			applicationContext = new ApplicationContext(propertiesPath);
			
			/*
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup(
						"java:comp/env/jdbc/studydb");
			
			MySqlMemberDao memberDao = new MySqlMemberDao();
			memberDao.setDataSource(ds);
			
			sc.setAttribute("/auth/login.do", new LogInController().setMemberDao(memberDao));
			sc.setAttribute("/auth/logout.do", new LogOutController());
			sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));
			sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(memberDao));
			sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberDao(memberDao));
			sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberDao(memberDao));
			*/
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener::contextDestroyed() 호출");

	}
}







