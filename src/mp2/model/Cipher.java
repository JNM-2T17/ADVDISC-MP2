package mp2.model;

import mp2.view.ITrace;

/**
 * Handles enciphering, deciphering, and cipher cracking
 * @author Jonah Syfu
 * @author Austin Fernandez
 */
public class Cipher {
	private int cipherDim;
	private ITrace observer;
	private boolean trace;

	/**
	 * basic constructor
	 * @param x dimension of cipher matrices
	 */
	public Cipher(int x, ITrace observer) {
		cipherDim = x;
		this.observer = observer;
		trace = true;
	}

	/**
	 * sets whether to trace the operations or not
	 * @param trace whether to trace of not.
	 */
	public void setTrace(boolean trace) {
		this.trace = trace;
	}

	/**
	 * enciphers a string using a matrix
	 * @param s string to encipher
	 * @param m matrix to use
	 * @return enciphered string
	 */
	public String encipher(String s, Matrix m) {
		if(trace) {
			observer.clear();
			observer.print("A = cipher matrix\nB = plaintext\nC = " + 
							"ciphertext\n\nC = AB\n");
			observer.print("A = \n" + m);
		}
		
		Matrix b = convertToMatrix(s);
		if(trace) {
			observer.print("\nB = \n" + b);
		}
		
		Matrix c = m.multiply(b);
		if(trace) {
			observer.print("\nC = \n" + c);
		}
		
		return convertToString(c);
	}

	/**
	 * deciphers a string
	 * @param s string to decipher
	 * @param m original enciphering matrix
	 * @return deciphered string
	 */
	public String decipher(String s, Matrix m) {
		Matrix i = m.invert();
		if( trace ) {
			observer.clear();
			observer.print("A = cipher matrix\nB = plaintext\nC = ciphertext" 
							+ "\n\nB = A^-1C\n");
			observer.print("A^-1 = \n" + i);
		}
		
		Matrix c = convertToMatrix(s);
		if( trace ) {
			observer.print("\nC = \n" + c);
		}
		
		Matrix b = i.multiply(c);
		if( trace ) {
			observer.print("\nB = \n" + b);
		}
		
		return convertToString(b);
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
	 * pumps a string to be of length of at least square of dimension
	 */
	private String pump(String s) {
		while( s.length() < cipherDim * cipherDim) {
			s += " ";
		}
		return s;
	}

	/**
	 * derives the enciphering matrix
	 * @param plainText original text
	 * @param cipherText enciphered text
	 * @return enciphering matrix
	 */
	public Matrix deriveCipher(String plainText, String cipherText) {
		if(trace) {
			observer.clear();
		}

		plainText = pump(plainText);
		cipherText = pump(cipherText);

		//pump plaintext
		while(plainText.length() < cipherText.length() 
				|| plainText.length() < cipherDim * cipherDim ) {
			plainText += " ";
		}

		while( cipherText.length() < plainText.length() ) {
			cipherText += " ";
		}

		//convert to matrices
		Matrix m1 = convertToMatrix(cipherText);
		Matrix m2 = convertToMatrix(plainText);

		if(trace) {
			observer.print("Ciphertext vectors\n" + m1 + "\nPlaintext vectors\n" 
						+ m2);
		}


		//reduce plaintext matrix to reduced row echelon
		Matrix rre = m2.reducedRowEchelon();
		if(trace) {
			observer.print("\nReduced Row Echelon of Plaintext Vectors\n" 
							+ rre);
		}


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

			if( c.determinant() == 0 || p.determinant() == 0 ) {
				return null;
			}
			
			c.augment(p);
			
			if(trace) {
				observer.print("\n\nTransposed Linearly Independent Ciphertext " 
							+ "Vectors|Plaintext Vectors\n" + c + "\n");
			}

			Matrix c2 = c.reducedRowEchelon();

			if(trace) {
				observer.print(c.rreTrace());
			}


			Matrix cipherMatrix = c2.getAugment(0).transpose();
			if(trace) {
				observer.print("Deciphering Matrix: \n" + cipherMatrix);
			}

			cipherMatrix = cipherMatrix.invert();
			if(trace) {
				observer.print("\nEnciphering Matrix(inverse): \n" 
								+ cipherMatrix);
			}

						
			return cipherMatrix;
		}
	}

	/**
	 * converts a string into a matrix
	 * @param s string to convert
	 * @return matrix representation of s
	 */
	public Matrix convertToMatrix(String s)
	{
		int[][] matrix = new int[cipherDim][(int)Math.ceil(s.length() 
												* 1.0 / cipherDim)];
		for(int i = 0; i < matrix[0].length; i++) {
			for( int j = 0; j < cipherDim; j++ ) {
				int n;
				if( i * cipherDim + j < s.length() ) { 
					n = (int)s.charAt(i * cipherDim + j) - 32;
					if( n == -22 ) {
						n = 95;
					} else if( n == -23 ) {
						n = 96;
					}
				} else {
					n = 0;
				}
				matrix[j][i] = n;
			}
		}

		Matrix m = new ModularMatrix(matrix);
		return m;
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
 				int n = m.get(j,i);
 				if( n == 95 ) {
 					n = 10;
 				} else if( n == 96) {
 					n = 9;
 				} else {
 					n += 32;
 				}
 				ret += "" + (char)n;
 			}
 		}
 		return ret;
	}
}