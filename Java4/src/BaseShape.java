
public abstract class BaseShape implements IShape {

	public ShapeType type;

	public void print() {
		switch (type) {
		case Circle:
			System.out.println("I am circle");
			break;
		case Square:
			System.out.println("I am square");
			break;
		}
	}
}
