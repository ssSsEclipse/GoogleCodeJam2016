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
		if (n == 2)
			;
		else {
			int count = 0;
			long totalCombination = (long) Math.pow(2, (n - 2));
			for (int i = 0; i < totalCombination; i++) {
				String candidate = "1" + String.format("%" + (n - 2) + "s", Long.toBinaryString(i)).replace(' ', '0')
						+ "1";
				List<BigInteger> baseRespresentations = new ArrayList<>();
				for (int j = 2; j <= 10; j++) {
					BigInteger baseRes = new BigInteger(candidate, j);
					if (baseRes.isProbablePrime(1))
						break; // go on to next candidate
					else
						baseRespresentations.add(baseRes);
				}
				if (baseRespresentations.size() == 9) {
					String divisors = baseRespresentations.stream().map((r) -> getDivisor(r))
							.collect(Collectors.joining(" "));
					System.out.println(candidate + divisors);
					count++;
				}
				if (count == k)
					break;
			}
		}
	}

	private static String getDivisor(BigInteger r) {
		BigInteger two = BigInteger.valueOf(2);
		BigInteger five = BigInteger.valueOf(5);
		if (r.mod(two).equals(BigInteger.ZERO)) {
			return "2";
		} else if (r.mod(five).equals(BigInteger.ZERO)) {
			return "5";
		} else {
			return rhoFactors(r).toString();
		}
	}

	private static BigInteger rhoFactor(BigInteger n, BigInteger c) {
		BigInteger t = BigInteger.valueOf(2);
		BigInteger h = BigInteger.valueOf(2);
		BigInteger d = BigInteger.ONE;

		while (d.equals(BigInteger.ONE)) {
			t = t.multiply(t).add(c).mod(n);
			h = h.multiply(h).add(c).mod(n);
			h = h.multiply(h).add(c).mod(n);
			d = t.subtract(h).gcd(n);
		}

		if (d.equals(n)) {
			return rhoFactor(n, c.add(BigInteger.ONE));
		} else if (d.isProbablePrime(1)) {
			return d;
		} else {
			return rhoFactor(d, c.add(BigInteger.ONE));
		}
	}

	public static BigInteger rhoFactors(BigInteger n) {
		while (!n.isProbablePrime(25)) {
			return rhoFactor(n, BigInteger.ONE);
		}
		return null;

	}
}
