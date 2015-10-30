package mp2;

import java.util.Scanner;

import mp2.model.*;

public class Driver {
	public static final int MODULUS = 127;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int dim = 0;

		/*System.out.print("Enter dimension: ");
		while(!sc.hasNextInt()) {
			System.out.print("Enter valid no: ");
			sc.nextLine();
		}
		dim = sc.nextInt();
		int[][] matrix = new int[dim][dim];

		for( int i = 0; i < dim; i++ ) {
			for(int j = 0; j < dim; j++ ) {
				System.out.print("Enter Row" + (i + 1) + ", Col " 
									+ (j + 1) + ": ");
				while(!sc.hasNextInt()) {
					System.out.print("Enter valid no: ");
					sc.nextLine();			
				}
				matrix[i][j] = sc.nextInt();
			}
		}
		
		Matrix mm = new ModularMatrix(matrix);
		Matrix m = mm.invert();
		System.out.println("A = \n" + mm);
		System.out.println("|A| = " + mm.determinant());
		System.out.println("|A|^-1 = " + ModArith.modInverse(mm.determinant(),MODULUS));
		System.out.println("A^-1 = \n" + m);
		System.out.println("AA^-1 = \n" + mm.multiply(m));*/
		int[][] matrix = new int[][] {
			{1,3},
			{2,30}
		};
		Matrix m = new ModularMatrix(matrix);
		Cipher c = new Cipher(2);

		System.out.print("Enter string to cipher: ");
		String s = sc.nextLine();

		String enc = c.encipher(s,m);
		System.out.println("Enciphered: " + enc);
		String dec = c.decipher(enc,m);
		System.out.println("Deciphered: " + dec);
	}
}