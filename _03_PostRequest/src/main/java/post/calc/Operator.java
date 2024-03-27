package post.calc;

public interface Operator {
	String getName();										// 연산자 이름
	double execute(double a, double b) throws Exception;	// 계산 작업
}
