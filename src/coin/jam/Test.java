package coin.jam;

import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Test {
	public static String tdFactors(BigInteger r)
    {
		if (r.getLowestSetBit() != 0) { // even num
			return " 2";
		}else {
	        BigInteger two = BigInteger.valueOf(2);
	        BigInteger five = BigInteger.valueOf(5);
			if (r.mod(two).equals(BigInteger.ZERO)) {
				return " " + 2;
			}else if (r.mod(five).equals(BigInteger.ZERO)) {
				return " " + 5;
			}
			BigInteger f = BigInteger.valueOf(3);
            while (f.multiply(f).compareTo(r) <= 0) {
                if (r.mod(f).equals(BigInteger.ZERO)) {
                	return " " + f;
                }else {
                    f = f.add(two);
                }
            }
		}
		return null;
    }
    public static Boolean isPrime(BigInteger n)
    {
        Random r = new Random();
        BigInteger two = BigInteger.valueOf(2);
        BigInteger n3 = n.subtract(BigInteger.valueOf(3));
        BigInteger a;
        int k = 25;

        if (n.compareTo(two) < 0)
        {
            return false;
        }

        if (n.mod(two).equals(BigInteger.ZERO))
        {
            return n.equals(two);
        }

        while (k > 0)
        {
            a = new BigInteger(n.bitLength(), r).add(two);
            while (a.compareTo(n) >= 0)
            {
                a = new BigInteger(n.bitLength(), r).add(two);
            }

            if (! isSpsp(n, a))
            {
                return false;
            }

            k -= 1;
        }

        return true;
    }
    private static Boolean isSpsp(BigInteger n, BigInteger a)
    {
        BigInteger two = BigInteger.valueOf(2);
        BigInteger n1 = n.subtract(BigInteger.ONE);
        BigInteger d = n1;
        int s = 0;

        while (d.mod(two).equals(BigInteger.ZERO))
        {
            d = d.divide(two);
            s += 1;
        }

        BigInteger t = a.modPow(d, n);

        if (t.equals(BigInteger.ONE) || t.equals(n1))
        {
            return true;
        }

        while (--s > 0)
        {
            t = t.multiply(t).mod(n);
            if (t.equals(n1))
            {
                return true;
            }
        }

        return false;
    }
    private static BigInteger rhoFactor(BigInteger n, BigInteger c)
    {
        BigInteger t = BigInteger.valueOf(2);
        BigInteger h = BigInteger.valueOf(2);
        BigInteger d = BigInteger.ONE;

        while (d.equals(BigInteger.ONE))
        {
            t = t.multiply(t).add(c).mod(n);
            h = h.multiply(h).add(c).mod(n);
            h = h.multiply(h).add(c).mod(n);
            d = t.subtract(h).gcd(n);
        }

        if (d.equals(n)) /* cycle */
        {
            return rhoFactor(n, c.add(BigInteger.ONE));
        }
        else if (isPrime(d)) /* success */
        {
            return d;
        }
        else /* found composite factor */
        {
            return rhoFactor(d, c.add(BigInteger.ONE));
        }
    }

    public static BigInteger rhoFactors(BigInteger n)
    {
        BigInteger f;

        while (! n.isProbablePrime(25))
        {
            f = rhoFactor(n, BigInteger.ONE);
            return f;
        }
        return null;

    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(rhoFactors(new BigInteger("38152042447694583162854323467877777777777777765432655279649")));
	}

}
