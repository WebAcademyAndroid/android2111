import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {

		/*
		 * int salary = 100;
		 * 
		 * if (salary < 500) { int a = 0; System.out.println("NEVER!"); } else {
		 * int a = 0; System.out.println("YES!"); } int b = 0; switch (salary) {
		 * case 100: case 2000: b = 3; { int a = 0;
		 * System.out.println("NEVER!"); } break; case 1000: b = 3; { int a = 0;
		 * System.out.println("YES!"); } break; default: { int a = 0;
		 * System.out.println("DONT KNOW!"); } break; }
		 */

		/*
		 * int salary = 1000; int money = 1;
		 * 
		 * for (int i = 0; i < 3; i++) { // money = money + salary; money *=
		 * salary;
		 * 
		 * System.out.println(i); System.out.println(money); }
		 * 
		 * System.out.println(money);
		 */

		/*
		 * int salary = 500; int count = 0; while(salary < 1000){ //salary +=
		 * 10; System.out.println(salary);
		 * 
		 * count++; if(count > 100000){ break; } }
		 * 
		 * System.out.println(salary);
		 */

		/*
		 * int[] arr = new int[10];
		 * 
		 * for (int i = 0; i < arr.length; i++) { arr[i] = i; }
		 * 
		 * arr[0] = 123; arr[arr.length - 1] = 321;
		 * 
		 * int[] arr2 = new int[10]; for (int i = 0; i < arr.length; i++) {
		 * arr2[arr.length - i - 1] = arr[i]; }
		 * 
		 * for (int i = 0; i < arr2.length; i++) { System.out.print(arr2[i]);
		 * 
		 * if(i < arr2.length - 1){ System.out.print("-"); } }
		 */

		/*ArrayList<String> arr = new ArrayList<>();

		arr.add("AAA");
		arr.add("BBB");
		arr.add("BBB");
		arr.add("BBB");
		arr.add("CCC");
		arr.add("BBB");

		
		 * for (int i = arr.size() - 1; i >= 0; i--) { if
		 * (arr.get(i).equals("BBB")) { arr.remove(i); } }
		 

		int position = remove(arr);
		while (position >= 0) {
			arr.remove(position);
			position = remove(arr);
		}

		for (String str : arr) {
			System.out.println(str);
		}*/
		
		HashMap<String, Integer> map = new HashMap<>();
		
		map.put("A", 1);
		map.put("C", 3);
		map.put("B", 2);
		map.put("D", 4);
		
		for(String key: map.keySet()){
			System.out.println(key + ":" + map.get(key));
		}
		
		String email = "vasya@mail.com";
		String[] parts = email.split("@");
		if(parts.length == 2){
			String login = parts[0];
		}
	}

	/*public static int remove(ArrayList<String> arr) {
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).equals("BBB")) {
				return i;
			}
		}

		return -1;
	}*/
}
