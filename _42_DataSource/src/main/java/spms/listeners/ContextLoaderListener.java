package spms.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp.BasicDataSource;

import spms.dao.MemberDao;

/**
 * 웹 어플리케이션이 실해되었을 때 자동으로 호출되는 클래스
 * ServletContext 영역이 준비되었습니다
 */
//@WebListener
public class ContextLoaderListener implements ServletContextListener {
	
//	Connection conn;
//	DBConnectionPool connPool;
	BasicDataSource ds;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener::contextInitialized() 호출");
		try {
			ServletContext sc = sce.getServletContext();
			
			ds = new BasicDataSource();
			ds.setDriverClassName(sc.getInitParameter("driver"));
			ds.setUrl(sc.getInitParameter("url"));
			ds.setUsername(sc.getInitParameter("username"));
			ds.setPassword(sc.getInitParameter("password"));
			
			MemberDao memberDao = new MemberDao();
			memberDao.setDataSource(ds);
			
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
			if(ds != null) 
				ds.close();
			
//			connPool.closeAll();
			
//			if(conn != null && conn.isClosed() ==false)
//				conn.close();
		} catch(Exception e) {
			
		}
	}
}
