package spms.vo;

import java.util.Date;

/* 1번째 작업
 * 
 * Projects 테이블을 설계한 후
 * 1개 행의 정보를 입출력 할 수 있도록
 * Project Vo 클래스를 생성하였다.
 * 
 * setter/getter를 생성하였고
 * 필드의 정보를 확인하기 위하여 ToString도 오버라이딩 하였다.
 * 
 * 연속적인 setter초기화 방식을 사용하기 위해
 * 모든 setter의 리턴형을 vo클래스 자료형으로 하였고
 * return this;를 추가하였다.
 * 
 * */
public class Project {
	
	private int no;				// 프로젝트 일련번호
	private String title;		// 프로젝트명
	private String content;		// 설명
	private Date startDate;		// 시작일
	private Date endDate;		// 종료일
	private int state;			// 상태
	private Date createdDate;	// 생성일
	private String tags;		// 태그들
	
	public int getNo() {
		return no;
	}
	public Project setNo(int no) {
		this.no = no;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public Project setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getContent() {
		return content;
	}
	public Project setContent(String content) {
		this.content = content;
		return this;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Project setStartDate(Date startDate) {
		this.startDate = startDate;
		return this;
	}
	public Date getEndDate() {
		return endDate;
	}
	public Project setEndDate(Date endDate) {
		this.endDate = endDate;
		return this;
	}
	public int getState() {
		return state;
	}
	public Project setState(int state) {
		this.state = state;
		return this;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Project setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	public String getTags() {
		return tags;
	}
	public Project setTags(String tags) {
		this.tags = tags;
		return this;
	}
	@Override
	public String toString() {
		return "Project [no=" + no + ", title=" + title + ", content=" + content + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", state=" + state + ", createdDate=" + createdDate + ", tags=" + tags + "]";
	}
}
