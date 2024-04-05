package spms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Vector;

public class DBConnectionPool {
	
	final int PRE_CREATED = 5;

	String url;
	String username;
	String password;

	List<Connection> connList = new Vector<Connection>();
	
	public DBConnectionPool(String driver, String url,
			String username, String password) throws Exception{
		this.url = url;
		this.username = username;
		this.password = password;
		
		Class.forName(driver);
		
		preCreatedConnection();
	}
	
	private void preCreatedConnection() throws Exception {
		for(int i=0;i<PRE_CREATED;i++) {
			Connection conn = DriverManager.getConnection(url, username, password);
			connList.add(conn);
		}
	}
	
	public Connection getConnection() throws Exception{
		if(connList.size() > 0) {			
			Connection conn = connList.remove(0);
			if(conn.isValid(10)) {
				return conn;
			}
		}

		return DriverManager.getConnection(url, username, password);
	}
	
	public void returnConnection(Connection conn) throws Exception{
		if(conn != null && conn.isValid(10))
			connList.add(conn);
	}
	
	public void closeAll() {
		for(Connection conn : connList)
			try {conn.close();} catch(Exception e) {}
	}
}






















