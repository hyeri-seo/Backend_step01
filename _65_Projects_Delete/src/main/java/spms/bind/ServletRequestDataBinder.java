package spms.bind;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Set;

import javax.servlet.ServletRequest;

public class ServletRequestDataBinder {

	// request로부터 Parameter를 추출해서
	// dataType 클래스 정보를 바탕으로 
	// dataName으로 객체를 생성한다.
	public static Object bind(
			ServletRequest request, 
			Class<?> dataType,
			String dataName) throws Exception{
		
		// --- PrimitiveType인 경우 객체를 생성해서 리턴 ---
		if(isPrimitiveType(dataType)) {
			return createValueObject(dataType, request.getParameter(dataName));
		}
		
		// --- PrimitiveType이 아닌 경우 ---
		
		// request에 저장된 모든 parameter의 이름을 얻는다.
		Set<String> paramNames = request.getParameterMap().keySet();
		// 클래스 타입에 따른 객체 생성
		Object dataObject = dataType.getDeclaredConstructor().newInstance();
		// 메서드 정보를 담을 변수
		Method m = null;
		
		// 브라우저에서 보내온 파라미터 개수만큼 반복
		for(String paramName : paramNames) {
			// parameter값에 대응되는 setter를 찾아서 리턴한다.
			// ex) no : setNo, name : setName
			m = findSetter(dataType, paramName);
			if(m != null) {
				// 메서드 정보를 가지고 메서드를 호출
				// dataObject로부터 m에 저장된 setter메서드를 호출해서 값을 저장한다.
				//m.getParameterTypes()[0] - setter메서드의 매개변수 타입
				//request.getParameter(paramName) - 브라우저가 보내온 매개변수 값
				m.invoke(dataObject,  createValueObject(m.getParameterTypes()[0],
													request.getParameter(paramName)));
			}
		}
		
		return dataObject;
	}
	
	// 클래스 타입이 기본 타입인지, 아니면 사용자 정의 타입인지
	private static boolean isPrimitiveType(Class<?> type) {
		if(type.getName().equals("int") || type == Integer.class ||
		   type.getName().equals("long") || type == Long.class ||
		   type.getName().equals("float") || type == Float.class ||
		   type.getName().equals("double") || type == Double.class ||
		   type.getName().equals("boolean") || type == Boolean.class ||
		   type == Date.class || type == String.class
		   )
			return true;
		
		return false;			
	}
	
	// Primitive 클래스 타입에 따라 객체를 생성하는 메서드
	private static Object createValueObject(Class<?> type, String value) {
		if(type.getName().equals("int") || type == Integer.class)			
			return Integer.valueOf(value);		// Integer class
			//return Integer.parseInt(value);		// int
		else if(type.getName().equals("float") || type == Float.class)
			return Float.valueOf(value);
		else if(type.getName().equals("double") || type == Double.class)
			return Double.valueOf(value);
		else if(type.getName().equals("long") || type == Long.class)
			return Long.valueOf(value);
		else if(type.getName().equals("boolean") || type == Boolean.class)
			return Boolean.valueOf(value);
		else if(type == Date.class)
			return java.sql.Date.valueOf(value);
		else
			return value;
	}
	
	// name에 대한 setter를 찾아서 반환한다.
	private static Method findSetter(Class<?> type, String name) {
		// type 클래스가 가진 모든 메서드 정보를 배열로 추출한다.
		Method[] methods = type.getMethods();
		
		String propName = null;
		for(Method m : methods) {
			// 메서드의 시작이름이 set으로 시작되지 않는 경우
			if(!m.getName().startsWith("set"))
				continue;
			
			// set메서드를 찾았으면, set 글자를 제외한 나머지 이름을 얻어온다.
			// 예를 들면, setNo이면 propName에는 No가 담긴다.
			// setName이면 propName에는 Name이 담긴다.
			propName = m.getName().substring("set".length());
			// 비교를 쉽게 하기위해 이름을 모두 소문자로 변환해서 비교한다.
			/* 메서드 이름의 setNo이고, name변수에 "no"가 저장되어 있다면
			 * setNo에서 프로퍼티명(propName)은 No이고
			 * No를 모두 소문자로 변경하면 no가 되고
			 * 이 이름과 name에 있는 이름이 같다면
			 * name에 대한 setter메서드인 setNo 메서드를 찾은 것이다.
			 * 찾았으면 메서드 정보를 반환한다.
			 * */
			if(propName.toLowerCase().equals(name.toLowerCase())) {
				return m;
			}
		}
		
		return null;
	}
}








