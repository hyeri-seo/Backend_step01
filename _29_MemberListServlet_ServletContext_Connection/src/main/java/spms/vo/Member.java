package spms.vo;

import java.util.Date;

/* 테이블의 1개행의 데이터를 모두 묶어서 담는 클래스 객체를
 * Vo(Value Object)라고 한다.
 * 
 * MVC의 역할을 나누면 (테이블)데이터를 묶어서 전달하는 객체 필요하다.
 * 이 때 Vo를 주고 받으면 이 Vo는 Dto(Data Transfer Object)라고 불린다.
 * 
 * Vo와 Dto는 구성이 같지만 가끔 용도에 따라 필드를 약간 달리할 때가 있다.
 * 또 getter/setter도 달리할 때가 있다.
 * 이럴 때는 Vo와 Dto를 따로 만드는 경우도 있다.
 * 그래도 ModelMapper로 Vo <-> Dto를 상호 변환해서 사용하기도 한다.
 * */
public class Member {
	private int no;
	private String name;
	private String email;
	private String password;
	private Date createdDate;
	private Date modifiedDate;
	
	public int getNo() {
		return no;
	}
	public Member setNo(int no) {
		this.no = no;
		return this;
	}
	public String getName() {
		return name;
	}
	public Member setName(String name) {
		this.name = name;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Member setPassword(String password) {
		this.password = password;
		return this;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Member setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public Member setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
	
	@Override
	public String toString() {
		return "Member [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + "]";
	}	
}











