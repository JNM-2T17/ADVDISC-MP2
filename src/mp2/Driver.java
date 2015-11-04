package mp2;

import java.util.Scanner;

import mp2.model.*;

public class Driver {
	public static final int MODULUS = 127;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*int[][] matrix = new int[][] {
			{1,3},
			{2,30}
		};
		Matrix m = new ModularMatrix(matrix);*/
		Cipher c = new Cipher(3);

		System.out.print("Enter ciphertext: ");
		String s1 = sc.nextLine();

		System.out.print("Enter plaintext: ");
		String s2 = sc.nextLine();
		
		Matrix m = c.deriveCipher(s2,s1);
		System.out.println(m);

		String enc = c.encipher(s2,m);
		System.out.println("Enciphered: " + enc);
		String dec = c.decipher(enc,m);
		System.out.println("Deciphered: " + dec);

	}
}