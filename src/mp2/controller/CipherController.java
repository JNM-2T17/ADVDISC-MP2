package mp2.controller;

import mp2.view.*;

public class CipherController implements IController {
	private MainWindow mainWindow;
	private MainPanel mainPanel;
	private CipherPanel cipherPanel;

	public CipherController() {
		mainWindow = new MainWindow(this);
		mainPanel = new MainPanel(this);
		cipherPanel = new CipherPanel(this);
		setScreen(MAIN);
	}

	public void setCipher(int[][] cipher) {

	}

	public void cipher(String plaintext) {

	}

	public void decipher(String ciphertext) {

	}

	public void derive(String plaintext, String ciphertext) {

	}

	public void setScreen(int screen) {
		switch(screen) {
			case ENCIPHER:
				mainWindow.setSize(680,500);
				mainWindow.setMain(cipherPanel);
				break;
			case DECIPHER:
				mainWindow.setMain(null);
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
}