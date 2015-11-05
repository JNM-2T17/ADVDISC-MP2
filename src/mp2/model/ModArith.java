package mp2.model;

/**
 * handles modular arithmetic
 * @author John Joseph Andres
 * @author Jonah Syfu
 */
public class ModArith {

	/**
	 * computes for the formal modulo of a number
	 * @param n number to get modulo of
	 * @param mod modulus
	 * @return n mod `mod`
	 */
	public static int modulo(int n, int mod) {
		if( n >= 0)
			return n % mod;
		else
			return n % mod == 0 ? 0 : mod + n % mod;
	}

	/**
	 * gets the modular inverse of a number
	 * @param a number to get modular inverse of
	 * @param mod modulus
	 * @return modular inverse of a
	 */
	public static int modInverse(int a,int mod)
	{
		int i = 1;
		while( modulo(i * a, mod) != 1 ) {
			i++;
		}

		return i;
	}
}