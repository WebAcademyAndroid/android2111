
public class Square extends BaseShape {

	private int mSide;

	public Square(int side) {
		mSide = side;
		type = ShapeType.Square;
	}

	@Override
	public double calculate() {
		return mSide * mSide;
	}
}
