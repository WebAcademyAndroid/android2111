
public class Main {

	public static void main(String[] args) {

		Student s = new Student();
		System.out.println(s.getAge());

		/*
		 * Student s = new Student(); s.mName = "Ivan"; s.setAge(22);
		 * 
		 * Student s2 = new Student("Ivan"); s2.setAge(22);
		 * 
		 * Student s3 = new Student("Ivan", 22);
		 * 
		 * test(s); test(s2); test(s3);
		 * 
		 * Student s4 = null; test(s4);
		 * 
		 * s = null; s = new Student();
		 * 
		 * int a = 0; int b = a; a = 10;
		 * 
		 * Student ss = new Student(); Student sss = ss; ss.mName = "AAA";
		 * 
		 * int aa = 3; test2(aa);
		 * 
		 * test3(ss);
		 * 
		 * String str = "123";
		 * 
		 * 
		 * int q = 2; int w = 2; if(q == w){
		 * 
		 * }
		 * 
		 * if(s == s2){
		 * 
		 * }
		 * 
		 * 
		 * 
		 * String str1 = "AAA"; String str2 = "AAA";
		 * 
		 * if(str1.equals(str2)){
		 * 
		 * }
		 */

		Integer a = 0;
		Integer b = 0;

		if (a.equals(b)) {

		}

		Student[] arr = new Student[] { new Student(), new Student() };
		arr[0].setAge(22);
		
		DataBase db = DataBase.getInstance();
		Student.test = "BBB";
	}

	public static void test2(int a) {
		a = 10;
	}

	public static void test3(Student st) {
		st.mName = "BBB";
	}

	public static void test(Student student) {
		if (student != null) {
			System.out.println(student.mName);
		}
	}

}