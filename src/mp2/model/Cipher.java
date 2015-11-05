package mp2.model;

/**
 * Handles enciphering, deciphering, and cipher cracking
 * @author Jonah Syfu
 * @author Austin Fernandez
 */
public class Cipher {
	private int cipherDim;

	/**
	 * basic constructor
	 * @param x dimension of cipher matrices
	 */
	public Cipher(int x) {
		cipherDim = x;
	}

	/**
	 * enciphers a string using a matrix
	 * @param s string to encipher
	 * @param m matrix to use
	 * @return enciphered string
	 */
	public String encipher(String s, Matrix m) {
		Matrix i = m.invert();
		return convertToString(m.multiply(convertToMatrix(s)));
	}

	/**
	 * deciphers a string
	 * @param s string to decipher
	 * @param m original enciphering matrix
	 * @return deciphered string
	 */
	public String decipher(String s, Matrix m) {
		Matrix i = m.invert();
		return convertToString(m.invert().multiply(convertToMatrix(s)));
	}

	/**
	 * sets the dimension of the cipher
	 * @param dimension new dimension
	 * @throws IllegalArgumentException if dimension is less than 2
	 */
	public void setDimension(int dimension) throws IllegalArgumentException {
		if( dimension < 2) {
			throw new IllegalArgumentException("Illegal Dimension: must be at" 
												+ " least 2");
		} else {
			cipherDim = dimension;
		}
	}

	/**
	 * derives the enciphering matrix
	 * @param plainText original text
	 * @param cipherText enciphered text
	 * @return enciphering matrix
	 */
	public Matrix deriveCipher(String plainText, String cipherText) {
		//convert to matrices
		Matrix m1 = convertToMatrix(cipherText);
		Matrix m2 = convertToMatrix(plainText);

		//pump plaintext
		while(plainText.length() < cipherText.length() ) {
			plainText += " ";
		}

		//reduce plaintext of them to reduced row echelon
		Matrix rre = m2.reducedRowEchelon();

		//find leading 1's
		int[] leaders = new int[cipherDim];
		int leaderCtr = 0;


		for( int i = 0; leaderCtr < cipherDim && i < rre.rowCount(); i++ ) {
			for(int j = 0; j < rre.colCount(); j++) {
				// save columns with leading 1's
				if( rre.get(i,j) == 1 ) {
					leaders[leaderCtr] = j;
					leaderCtr++;
					break;
				}
			}
		}

		//if not enough vectors
		if( leaderCtr < cipherDim ) {
			return null;
		} else {
			int[][] cMatrix = new int[cipherDim][cipherDim];
			int[][] pMatrix = new int[cipherDim][cipherDim];

			//save vectors
			for( int i = 0; i < cipherDim; i++ ) {
				for( int j = 0; j < m1.rowCount(); j++ ) {
					cMatrix[i][j] = m1.get(j,leaders[i]);
					pMatrix[i][j] = m2.get(j,leaders[i]);
				}
			}

			Matrix c = new ModularMatrix(cMatrix);
			Matrix p = new ModularMatrix(pMatrix);
			System.out.println(c);
			c.augment(p);
			c = c.reducedRowEchelon();
			return c.getAugment(0).transpose().invert();
		}
	}

	/**
	 * converts a string into a matrix
	 * @param s string to convert
	 * @return matrix representation of s
	 */
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

	/**
	 * converts a matrix to a string
	 * @param m matrix to convert
	 * @return string representation of matrix
	 */
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