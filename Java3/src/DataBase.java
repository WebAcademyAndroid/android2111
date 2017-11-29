
public class DataBase {

	private DataBase(){
		a = 10;
		getInstance();
	}
	
	private int a;
	
	private static DataBase db;
	
	public static DataBase getInstance(){
		if(db == null){
			db = new DataBase();
		}
		a = 10;
		
		int b = 3;
		
		return db;
	}
	
}
