import cars.BMW;

public class Audi extends BMW {
	@Override
	public void start() {
		startEngine();
		setGear(1);
		System.out.println("start");
	}
}
