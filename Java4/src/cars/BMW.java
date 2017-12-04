package cars;

public class BMW implements ICar{
	@Override
	public void start() {
		startEngine();
		setGear(1);
		System.out.println("start");
	}
	@Override
	public void stop() {
		setGear(0);
		stopEngine();
		System.out.println("stop");
	}

	protected void startEngine(){
		System.out.println("start engine");
	}
	protected void stopEngine(){
		System.out.println("stop engine");
	}
	protected void setGear(int gear){
		System.out.println("set gear " + gear);
	}
}
