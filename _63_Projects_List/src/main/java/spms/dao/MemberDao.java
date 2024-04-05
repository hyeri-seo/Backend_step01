package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import spms.vo.Member;

public interface MemberDao {

	// interface는 상속을 전제로 하므로 자동으로 public 속성이 부여된다.
	
	List<Member> selectList() throws Exception;
	int insert(Member member) throws Exception;
	int delete(int no) throws Exception;
	Member selectOne(int no) throws Exception;
	int update(Member member) throws Exception;
	Member exist(String email, String password) throws Exception;
}
