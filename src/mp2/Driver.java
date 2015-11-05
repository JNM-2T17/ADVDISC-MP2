package mp2;

import java.util.Scanner;

import mp2.model.*;

public class Driver {
	public static final int MODULUS = 27;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*int[][] matrix = new int[][] {
			{43,59,29},
			{117,90,122},
			{42,96,89}
		};*///clarisse
		int[][] matrix = new int[][] {
			{26,115,27},
			{99,66,75},
			{57,74,106}
		};
		System.out.println(ModArith.modInverse(5,27));
		String cipher_filename = "cipher.hill";
		String plain_filename = "plain.txt";
		String ciphertext_filename = "cipher.txt";
		
		Matrix m = new ModularMatrix(matrix);
		
		/*FileManager.writeCipher(m,cipher_filename);*/
		m = FileManager.readCipher(cipher_filename);
		System.out.println(m);
		
		Cipher c = new Cipher(3);

		/*System.out.print("Enter ciphertext: ");
		String s2 = sc.nextLine();

		System.out.print("Enter plaintext: ");
		String s1 = sc.nextLine();*/
		String s1;

		// FileManager.writeText(s1,plain_filename);
		s1 = FileManager.readText(plain_filename);
		// System.out.println("Enciphering " + s1);

		/*m = c.deriveCipher(s1,s2);
		System.out.println(m);
		FileManager.writeCipher(m,cipher_filename);*/

		String enc = c.encipher(s1,m);
		// System.out.println("Enciphered: " + enc);
		
		FileManager.writeText(enc,ciphertext_filename);
		String enc2 = FileManager.readText(ciphertext_filename);
		//System.out.println("System:" + enc.replaceAll("\n","") + "\n\nRead from File: " + enc2.replaceAll("\n","") + "\n\nEqual? " + enc.equals(enc2));
		// System.out.println("Deciphering " + enc);
		
		String dec = c.decipher(enc2,m);
		//System.out.println("Deciphered: " + dec);
		FileManager.writeText(dec,plain_filename);
	}
}