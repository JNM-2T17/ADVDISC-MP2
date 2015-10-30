package mp2.model;

public class Cipher
{
	private int cipherDim;

	public Cipher(int x)
	{
		cipherDim = x;
	}

	public String encipher(String s, Matrix m)
	{
		Matrix i = m.invert();
		return convertToString(m.multiply(convertToMatrix(s)));
	}

	private int[][] findIndependent(String s1, String s2)
	{
return null;
	}

	private boolean isIndependent(int[][] intArray)
	{
return false;
	}

	public String decipher(String s, Matrix m)
	{
		Matrix i = m.invert();
		return convertToString(m.invert().multiply(convertToMatrix(s)));
	}

	public Matrix deriveCipher(String s1, String s2)
	{
return null;
	}

	private Matrix convertToMatrix(String s)
	{
		int[][] matrix = new int[cipherDim][(int)Math.ceil(s.length() 
												* 1.0 / cipherDim)];
		for(int i = 0; i < matrix[0].length; i++) {
			for( int j = 0; j < cipherDim; j++ ) {
				matrix[j][i] = i * cipherDim + j < s.length() 
								? (int)s.charAt(i * cipherDim + j) : 0;
			}
		}

		return new ModularMatrix(matrix);
	}

	public String convertToString(Matrix m)
	{
 		String ret = "";
 		for( int i = 0; i < m.colCount(); i++ ) {
 			for( int j = 0; j < m.rowCount(); j++ ) {
 				ret += "" + (char)m.get(j,i);
 			}
 		}
 		return ret;
	}
}