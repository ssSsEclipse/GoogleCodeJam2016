
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt(); // Scanner has functions to read ints, longs,
								// strings, chars, etc.
		for (int i = 1; i <= t; ++i) {
			int n = in.nextInt();
			int k = in.nextInt();
			System.out.println("Case #" + i + ": ");
			getResult(n, k);
		}
	}

	private static void getResult(int n, int k) {
		if (n == 2) ;
		else {
			int count = 0;
			long totalCombination = (long) Math.pow(2, (n-2));
			for (int i=0;i<totalCombination;i++) {
				String candidate = "1" + String.format("%" + (n-2) + "s", Long.toBinaryString(i)).replace(' ', '0') +"1";
				List<BigInteger> baseRespresentations = new ArrayList<>();
				for (int j=2;j<=10;j++) {
					BigInteger baseRes = new BigInteger(candidate, j);
					if (baseRes.isProbablePrime(1)) break; // go on to next candidate
					else baseRespresentations.add(baseRes);
				}
				if (baseRespresentations.size() == 9)  {
					String divisors = baseRespresentations.stream().map((r) -> getDivisor(r)).collect(Collectors.joining(" "));
					System.out.println(candidate + divisors);
					count++;
				}
				if (count==k) break;
			}
		}
	}

	private static String getDivisor(BigInteger r) {
		if (r.getLowestSetBit() != 0) { // even num
			return " 2";
		}else {
			for (long l=3;l<r.longValue();l++) {
				if (r.mod(BigInteger.valueOf(l)) == BigInteger.ZERO) {
					return " " + l;
				}
			}
		}
		return null;
	}
}
