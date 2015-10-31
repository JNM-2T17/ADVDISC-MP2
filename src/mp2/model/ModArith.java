package mp2.model;

public class ModArith
{
	public static int modulo(int n, int mod)
	{
		if( n >= 0)
			return n % mod;
		else
			return n % mod == 0 ? 0 : mod + n % mod;
	}

	public static int modInverse(int x,int y)
	{
		int i = 1;
		while( modulo(i * x, y) != 1 ) {
			i++;
		}

		return i;
	}
}