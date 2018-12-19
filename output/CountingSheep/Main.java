import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt(); // Scanner has functions to read ints, longs,
								// strings, chars, etc.
		for (int i = 1; i <= t; ++i) {
			int n = in.nextInt();
			int result = getResult(n);
			System.out.println("Case #" + i + ": " + (result == -1 ? "INSOMNIA" : result));
		}
	}

	private static int getResult(int n) {
		Set<Integer> digits = new HashSet<Integer>();
		int maxAttempt = getMaxAttempt(n);
		for (int i = 1 ; i <= maxAttempt ; i++) {
			addToSet(n*i, digits);
			if (digits.size() == 10) 
				return i*n;
		}
		return -1;
	}

	private static int getMaxAttempt(int n) {
		int basic = 0;
		switch(n%10) {
		case 1: case 3: case 7: case 9: basic = 10; break;
		case 4: basic = 13; break;
		case 5: basic = 18; break;
		case 6: basic = 15; break;
		case 8: basic = 12; break;
		case 2: case 0: basic = 45; break;
		}
		int digitCnt = (int)Math.floor(Math.log10(n)) + 1;
		return (digitCnt + 1) * basic;
	}

	private static void addToSet(int n, Set<Integer> digits) {
		if (n == 0) digits.add(n);
		while (n > 0) {
			int d = n % 10;
			digits.add(d);
			n /= 10;
		}
	}

}