
public class Main {

	public static void main(String[] args) {

		/*
		 * int a = 4 + 5; int b = 6 * a; int c = a * b;
		 * 
		 * System.out.println(c - 10 + (33 - c));
		 * 
		 * int d = 5; int e = 2; double f = (double) d / e;
		 * 
		 * System.out.println(f);
		 * 
		 * String s1 = "Hello"; String s2 = "World!"; System.out.println(s1 + s2
		 * + (f + a));
		 */

		/*
		 * String s = "abcabc"; int pos = s.indexOf("aB");
		 * System.out.println(pos);
		 * 
		 * System.out.println(s.indexOf("b", 3));
		 */

		/*
		 * String s = "abcabc";
		 * 
		 * s = s.replace("aB", "ABCD");
		 * 
		 * System.out.println(s.replace("aB", "ABCD")); System.out.println(s);
		 */

		/*
		 * String s = "AbcAbc"; System.out.println(s.toLowerCase());
		 * System.out.println(s.toUpperCase());
		 * 
		 * System.out.println(s.toLowerCase().replace("ab",
		 * "bb").toUpperCase());
		 */

		/*
		 * String s = " abc abc "; System.out.println(s.trim().replace(" ",
		 * ""));
		 */

		/*
		 * String s = "abcabc"; System.out.println(s.charAt(0));
		 * System.out.println(s.substring(0, 5));
		 * System.out.println(s.substring(2));
		 */

		/*
		 * String email = "vasya@mail.com"; email = email.trim(); email =
		 * email.toLowerCase(); int pos = email.indexOf("@"); String login =
		 * email.substring(0, pos);
		 * 
		 * System.out.println(login);
		 */

		// test("Hello");

		/*int num = 99;
		String s = "World!";
		num = test(s, num);
		System.out.println(num);
		System.out.println(s);

		// test(66, "World!");*/
		
		
		int a = 8;
		String s = String.valueOf(a);
		
		int b = Integer.parseInt("88.888.888 ".replace(" ", "").replace(".", ""));
		
		System.out.println(b);
		
		double d = Double.parseDouble("2,2");
		System.out.println(d);
	}

	public static void test(String text) {
		System.out.println(text);
	}

	public static int test(String text, int num) {
		num = num + 10;
		text = text + "AAA";
		System.out.println(text + " " + num);
		
		return num;
	}

	public static void test(int num, String text) {
		System.out.println(text + " " + num);
	}
}
