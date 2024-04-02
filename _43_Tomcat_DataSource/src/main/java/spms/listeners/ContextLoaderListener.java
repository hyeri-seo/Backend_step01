package spms.listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import spms.dao.MemberDao;

/**
 * 웹 어플리케이션이 실해되었을 때 자동으로 호출되는 클래스
 * ServletContext 영역이 준비되었습니다
 */
//@WebListener
public class ContextLoaderListener implements ServletContextListener {
	
//	Connection conn;
//	DBConnectionPool connPool;
//	BasicDataSource ds;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener::contextInitialized() 호출");
		try {
			ServletContext sc = sce.getServletContext();
			
			// Tomcat이 실행될 때 생성되는 DataSource객체를 찾아서 MemberDao에 주입함
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup(
						"java:comp/env/jdbc/studydb");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setDataSource(ds);
			
			sc.setAttribute("memberDao", memberDao);
			
			/*
			ds = new BasicDataSource();
			ds.setDriverClassName(sc.getInitParameter("driver"));
			ds.setUrl(sc.getInitParameter("url"));
			ds.setUsername(sc.getInitParameter("username"));
			ds.setPassword(sc.getInitParameter("password"));
			
			MemberDao memberDao = new MemberDao();
			memberDao.setDataSource(ds);
			*/
			
			/*
			connPool = new DBConnectionPool(
					sc.getInitParameter("driver"), 
					sc.getInitParameter("url"), 
					sc.getInitParameter("username"), 
					sc.getInitParameter("password")
					);
			MemberDao memberDao = new MemberDao();
			memberDao.setDBConnectionPool(connPool);
			*/
			
			sc.setAttribute("memberDao", memberDao);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener::contextDestoryed() 호출");
		try {
			/**
			 * Tomcat의 context.xml의 설정 중에
			 * closeMethod="close"를 하면
			 * Tomcat이 종료되면 자동으로 
			 * DataSource의 close()를 호출하도록 설정했으므로
			 * 여기서는 close()하면 안 됨
			 * 
			 * 왜냐하면 다른 Application이 DataSource를 사용할 수 있으므로
			 */
			
//			if(ds != null) 
//				ds.close();
			
//			connPool.closeAll();
			
//			if(conn != null && conn.isClosed() ==false)
//				conn.close();
		} catch(Exception e) {
			
		}
	}
}
