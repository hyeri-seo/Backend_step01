package post.calc;

public class MulOperator implements Operator {

	@Override
	public String getName() {
		return "*";
	}

	@Override
	public double execute(double a, double b) throws Exception {
		return a * b;
	}

}
