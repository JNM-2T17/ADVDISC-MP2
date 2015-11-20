package mp2.controller;

import mp2.model.InvalidCipherException;

public interface IController {
	public static final int ENCIPHER = 0;
	public static final int DECIPHER = 1;
	public static final int CRACK_CIPHER = 2;
	public static final int MAIN = 3;

	public void setCipher(int[][] cipher) throws InvalidCipherException;
	public void inputCipher(int dim) throws Exception;
	public void cipher(String plaintext);
	public void decipher(String ciphertext);
	public void derive(String plaintext, String ciphertext);
	public void setScreen(int screen);
	public void clearCipher();
}