package spms.listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import spms.dao.MemberDao;


//@WebListener
public class ContextLoaderListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener::contextInitialized() 호출");
		
		try {
			ServletContext sc = sce.getServletContext();
			

			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup(
						"java:comp/env/jdbc/studydb");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setDataSource(ds);
			
			sc.setAttribute("memberDao", memberDao);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener::contextDestroyed() 호출");

	}
}







