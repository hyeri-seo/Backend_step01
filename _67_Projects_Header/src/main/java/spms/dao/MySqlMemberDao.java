package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.Member;

@Component("memberDao")
public class MySqlMemberDao implements MemberDao{
		
	private String strSelectList = 
			"SELECT mno,mname,email,cre_date FROM members ORDER BY mno ASC";
	private String strInsert = 
			"INSERT INTO members(email,pwd,mname,cre_date,mod_date) VALUES(?,?,?,NOW(),NOW())";
	private String strDelete = 
			"DELETE FROM members WHERE mno=?";
	private String strSelectOne = 
			"SELECT mno,email,mname,cre_date FROM members WHERE mno=?";
	private String strUpdate =
			"UPDATE members SET email=?, mname=?, mod_date=NOW() WHERE mno=?";
	private String strExist = 
			"SELECT mname, email FROM members WHERE email=? AND pwd=?";

	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public List<Member> selectList() throws Exception{
		Connection connection = null;		// 추가
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {

			connection = this.ds.getConnection();
			
			stmt = connection.prepareStatement(strSelectList);
			rs = stmt.executeQuery();
			
			List<Member> members = new ArrayList<>();
			
			while(rs.next()) {
				members.add(new Member()
						.setNo(rs.getInt("mno"))
						.setName(rs.getString("mname"))
						.setEmail(rs.getString("email"))
						.setCreatedDate(rs.getDate("cre_date"))
						);
			}
			
			return members;
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection != null) connection.close();} catch(Exception e) {}
		}
	}
	
	public int insert(Member member) throws Exception{
		Connection connection = null;		// 추가
		
		PreparedStatement stmt = null;
		
		try {
			connection = this.ds.getConnection();
			
			stmt = connection.prepareStatement(strInsert);
			stmt.setString(1,  member.getEmail());
			stmt.setString(2,  member.getPassword());
			stmt.setString(3, member.getName());

			int count = stmt.executeUpdate();
			return count;
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection != null) connection.close();} catch(Exception e) {}
		}
	}

	public int delete(int no) throws Exception{
		Connection connection = null;		// 추가

		PreparedStatement stmt = null;
		
		try {

			connection = this.ds.getConnection();

			stmt = connection.prepareStatement(strDelete);
			stmt.setInt(1, no);
			return stmt.executeUpdate();
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection != null) connection.close();} catch(Exception e) {}
		}
	}
	

	public Member selectOne(int no) throws Exception{
		Connection connection = null;		// 추가
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = this.ds.getConnection();
			
			stmt = connection.prepareStatement(strSelectOne);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Member()
						.setNo(rs.getInt("mno"))
						.setEmail(rs.getString("email"))
						.setName(rs.getString("mname"))
						.setCreatedDate(rs.getDate("cre_date"));
			}else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}			
			try {if(connection != null) connection.close();} catch(Exception e) {}
		}
	}
	
	public int update(Member member) throws Exception{
		Connection connection = null;		// 추가
		
		PreparedStatement stmt = null;
		
		try {
			connection = this.ds.getConnection();
			
			stmt = connection.prepareStatement(strUpdate);
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			
			return stmt.executeUpdate();
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection != null) connection.close();} catch(Exception e) {}
		}
	}
	
	public Member exist(String email, String password) throws Exception{
		Connection connection = null;		// 추가
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = this.ds.getConnection();
			
			stmt = connection.prepareStatement(strExist);
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Member()
						.setName(rs.getString("mname"))
						.setEmail(rs.getString("email"));
			}else {
				return null;
			}
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(rs!=null) rs.close();} catch(Exception e) {}
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(connection != null) connection.close();} catch(Exception e) {}
		}
	}
}















