package cars;

public class BMW2 extends BMW {
	
	@Override
	protected void startEngine(){
		System.out.println("turn alarm off");
		
		super.startEngine();
	}

	public void test(){
		super.startEngine();
	}
}
