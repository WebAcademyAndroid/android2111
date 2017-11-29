
public class Student {
	 public String mName;
	 private int mAge;
	
	
	final int inn = 123456;
	
	public static final String test;
	
	public int getAge(){
		return mAge;
	}
	public void setAge(int age){
		mAge = age;
	}
	

	public Student() {
	
	}
	
	public Student(String name) {
		mName = name;
	}

	public Student(String name, int age){
		mName = name;
		mAge = age;
	}
	

}
