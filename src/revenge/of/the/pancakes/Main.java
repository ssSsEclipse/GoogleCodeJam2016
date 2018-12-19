package revenge.of.the.pancakes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = Integer.parseInt(in.nextLine()); // Scanner has functions to read ints, longs,
								// strings, chars, etc.
		
		for (int i = 1; i <= t; ++i) {
			String s = in.nextLine();
			int result = getResult(s);
			System.out.println("Case #" + i + ": " + result);
		}
	}

	private static int getResult(String s) {
		String simpleS = decouple(s);
		int result = simpleS.length();
		if (simpleS.endsWith("+")) result--;
		return result;
	}

	private static String decouple(String s) {
		return s.replaceAll("\\-+", "-").replaceAll("\\++", "+");
	}
}
