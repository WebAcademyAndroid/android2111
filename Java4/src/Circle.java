
public class Circle extends BaseShape {

	private int mRadius;

	public Circle(int radius) {
		mRadius = radius;
		type = ShapeType.Circle;
	}

	@Override
	public double calculate() {
		return mRadius * mRadius * 3.14;
	}

	public void test() {

	}

}
