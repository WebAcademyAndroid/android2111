import cars.BMW;
import cars.BMW2;

public class Main {

	public static void main(String[] args) {
		/*
		 * Circle c = new Circle(10); print(c);
		 * 
		 * Square s = new Square(10); print(s);
		 */

		/*
		 * BMW2 bmw = new BMW2(); bmw.test();
		 */
		/*
		 * bmw.start(); bmw.stop();
		 */

		test();
	}

	public static void test() {
		try {
			return;
		} catch (Exception e) {
			System.out.println("ERROR!");
		} finally {
			System.out.println("HELLO");
		}
	}

	/*
	 * public static void print(BaseShape bShape) { bShape.print();
	 * System.out.println(bShape.calculate()); }
	 */
}
