package spms.dao;

import java.util.List;

import spms.vo.Project;

/**
 * 데이터 입출력은 단순한 것은 한 단계(즉, Controller에서 Dao 접근)
 * 
 * 로직이 복잡해지면 두 단계(Controller - Service - Dao)
 * 
 * Controller는 클라이언트의 통신 정보, 데이터 입출력 전달
 * Service는 다양한 비즈니스 로직, 여러 개 테이블 vo에 입출력
 * Dao는 테이블과 보통 1대1 입출력 기능을 담당함
 */

/**
 * Dao의 가장 기본 기능은 CRUD
 * CRUD는 대뷰뷴의 컴퓨터 소프트웨어가 가지는 기본적인 데이터 처리 기능인
 * Create, Read, Update, Delete를 묶어서 일컫는 말
 * 
 * 그 외의 기능은 필요하면 더 넣거나
 * 혹시 복잡한 로직이면 Dao보다 Service에서 구현할 것을 고려할 수 있음
 */

/**
 * Dao를 설계할 때 가장 기본적인 기능인
 * CRUD 메서드는 일단 만듦
 * 
 * 추가로 필요하면 더 만드는데
 * 다만, 단순 입출력인지, 여러 개를 조합하는 비즈니스 로직인지를 고려해서
 * Dao 또는 Service에 추가할지 결정해야 함
 */
public interface ProjectDao {
	List<Project> selectList() throws Exception;
	int insert(Project project) throws Exception;
	int delete(int no) throws Exception;
	Project selectOne(int no) throws Exception;
	int update(Project project) throws Exception;
}
