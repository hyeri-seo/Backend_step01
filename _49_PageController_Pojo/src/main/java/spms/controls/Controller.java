package spms.controls;

import java.util.Map;

/* 현재 FrontController(DispatchServlet)-PageController(나머지 Servlet클래스)로
 * 구성되어 있지만, DispatchServlet을 제외한 나머지 Servlet클래스는 굳이 Servlet으로 만들
 * 필요가 없다.
 * Servlet으로 만들면 기능동작에는 문제가 없지만, 향후 다른 Framework에 이식할 때
 * 수정할 부분이 많아지므로, Servlet에 종속적이기보다, 일반 java 클래스로 만드는 것이 
 * 나은 선택이다.
 * 이러한 방식을 Pojo(Plain Old Java Object)라고 하고,
 * Spring Framework 역시 이런 구조로 되어 있다.
 * 
 * 그래서 Spring Framework를 모방해서
 * DispatchServlet은 Tomcat과 연결하기 위해서 Servlet으로 만들고
 * 나머지는 일반 클래스로 만든다.
 * 
 * 아래 인터페이스는 기존 Servlet에서 공유 공간으로 사용하던 request를 대신해서
 * 데이터를 주고 받기 위한 규약을 정의한 것이다.
 * 모든 PageController는 아래 Controller 인터페이스를 상속받는다. 
 * */

public interface Controller {
	String execute(Map<String, Object> model) throws Exception;
}
