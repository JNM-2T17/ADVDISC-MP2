package mp2.controller;

import mp2.Driver;
import mp2.model.*;
import mp2.view.*;

public class CipherController implements IController {
	private Cipher hill;
	private Matrix cipher;

	private MainWindow mainWindow;
	private MainPanel mainPanel;
	private CipherPanel cipherPanel;
	private DecipherPanel decipherPanel;
	private CipherInputFrame cipherInputFrame;

	public CipherController() {
		cipher = null;

		mainWindow = new MainWindow(this);
		mainPanel = new MainPanel(this);
		cipherPanel = new CipherPanel(this);
		decipherPanel = new DecipherPanel(this);
		cipherInputFrame = new CipherInputFrame(this);
		setScreen(MAIN);

	}

	public void setCipher(int[][] cipher) throws InvalidCipherException {
		Matrix newCipher = new ModularMatrix(cipher);
		int det = newCipher.determinant();
		if( det == 0 ) {
			throw new InvalidCipherException("Determinant is zero.");
		} else if ( det % Driver.MODULUS == 0 ) {
			throw new InvalidCipherException("Determinant is divisible by " 
												+ "the modulus " 
												+ Driver.MODULUS + ".");
		} else {
			this.cipher = newCipher;
			hill = new Cipher(this.cipher.rowCount());
			cipherPanel.setMatrix(this.cipher);
			decipherPanel.setMatrix(this.cipher);
			cipherInputFrame.setVisible(false);
		}
	}

	public void inputCipher(int dim) throws Exception {
		if( cipherInputFrame.isVisible() ) {
			throw new Exception("Cipher Frame Already Open.");
		} else {
			cipherInputFrame.setDimension(dim);
			cipherInputFrame.setVisible(true);
		}
	}

	public void cipher(String plaintext) throws Exception {
		if( cipher == null ) {
			throw new Exception("Please Input a Cipher Matrix");
		} else {
			cipherPanel.setCiphertext(hill.encipher(plaintext,cipher));
		}
	}

	public void decipher(String ciphertext) throws Exception {
		if( cipher == null ) {
			throw new Exception("Please Input a Cipher Matrix");
		} else {
			decipherPanel.setPlaintext(hill.decipher(ciphertext,cipher));
		}
	}

	public void derive(String plaintext, String ciphertext) {

	}

	public void setScreen(int screen) {
		switch(screen) {
			case ENCIPHER:
				mainWindow.setSize(900,500);
				mainWindow.setMain(cipherPanel);
				break;
			case DECIPHER:
				mainWindow.setSize(900,500);
				mainWindow.setMain(decipherPanel);
				break;
			case CRACK_CIPHER:
				mainWindow.setMain(null);
				break;
			case MAIN:
			default:
				mainWindow.setSize(375,500);
				mainWindow.setMain(mainPanel);
				break;
		}
		mainWindow.setLocationRelativeTo(null);	
	}	

	public void clearCipher() {
		cipher = null;
		cipherPanel.setMatrix(cipher);
		decipherPanel.setMatrix(cipher);
	}
}