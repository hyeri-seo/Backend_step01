package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import spms.vo.Member;

/* Dao (Data Access Object)
 * 데이터베이스는 연결하여 데이터를 입출력을 담당하는 클래스
 * 이 클래스로 만들어진 오브젝트를 Dao라고 부른다.
 * */
public class MemberDao {
	Connection connection;
	
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
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	// MemberListServlet에서 필요
	public List<Member> selectList() throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
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
		}
	}
	
	// MemberAddServlet에서 필요
	public int insert(Member member) throws Exception{
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(strInsert);
			stmt.setString(1,  member.getEmail());
			stmt.setString(2,  member.getPassword());
			stmt.setString(3, member.getName());
			
			/*
				DB에서 이 명령이 적용된 row의 개수를 반환한다.
				1개 입력되면 1을 리턴한다.
				1이면 입력 성공,
				0이면 입력 안됨
			*/
			int count = stmt.executeUpdate();
			return count;
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
		}
	}
	
	// MemberDeleteServlet에서 필요
	public int delete(int no) throws Exception{
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(strDelete);
			stmt.setInt(1, no);
			return stmt.executeUpdate();
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
		}
	}
	
	// MemberUpdateServlet에서 get요청시 필요
	public Member selectOne(int no) throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
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
		}
	}
	
	// MemberUpdateServlet에서 post요청시 필요
	public int update(Member member) throws Exception{
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(strUpdate);
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			
			return stmt.executeUpdate();
			
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
		}
	}
	
	// LogInServlet에서 필요
	public Member exist(String email, String password) throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
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
		}
	}
}















