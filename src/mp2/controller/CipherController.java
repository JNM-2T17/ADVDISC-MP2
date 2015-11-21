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
	private DerivePanel derivePanel;
	private CipherInputFrame cipherInputFrame;

	public CipherController() {
		cipher = null;

		mainWindow = new MainWindow(this);
		mainPanel = new MainPanel(this);
		cipherPanel = new CipherPanel(this);
		decipherPanel = new DecipherPanel(this);
		cipherInputFrame = new CipherInputFrame(this);
		derivePanel = new DerivePanel(this);
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

	public void derive(String plaintext, String ciphertext, int dim) 
		throws Exception {
		hill = new Cipher(dim);
		cipher = hill.deriveCipher(plaintext,ciphertext);
		String result = hill.encipher(plaintext,cipher);
		derivePanel.setMatrix(cipher);
		cipherPanel.setMatrix(cipher);
		decipherPanel.setMatrix(cipher);
		if( !result.trim().equals(ciphertext.trim()) ) {
			throw new Exception("An exact matrix is impossible. The closest" 
								+ " matrix has been computed."); 
		}
	}

	public boolean isCipherSet() {
		return cipher != null;
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
				mainWindow.setSize(900,500);
				mainWindow.setMain(derivePanel);
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
		derivePanel.setMatrix(cipher);
	}

	public void loadCipher(String filename) {
		cipher = FileManager.readCipher(filename);
		hill = new Cipher(cipher.rowCount());
		cipherPanel.setMatrix(cipher);
		decipherPanel.setMatrix(cipher);
	}

	public void loadPlaintext(String filename) {
		cipherPanel.setPlaintext(FileManager.readText(filename));
		derivePanel.setPlaintext(FileManager.readText(filename));
	}

	public void loadCiphertext(String filename) {
		decipherPanel.setCiphertext(FileManager.readText(filename));
		derivePanel.setCiphertext(FileManager.readText(filename));
	}

	public void saveCipher(String filename) {
		if( !filename.endsWith(".hill") ) {
			filename += ".hill";
		}
		FileManager.writeCipher(cipher,filename);
	}

	public void saveText(String text,String filename) {
		FileManager.writeText(text,filename);
	}
}