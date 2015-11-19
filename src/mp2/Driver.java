package mp2;

import javax.swing.UIManager;
import javax.swing.UIManager.*;

import mp2.controller.CipherController;

/**
 * @author Austin Fernandez
 */
public class Driver {
	public static final int MODULUS = 97;

	public static void main(String[] args) {
		// try {
		// 	for(LookAndFeelInfo lafi: UIManager.getInstalledLookAndFeels() ) {
		// 		if(lafi.getName().equals("Nimbus")) {
		// 			UIManager.setLookAndFeel(lafi.getClassName());
		// 			break;
		// 		}
		// 	}
		// } catch( Exception e) {
		// 	e.printStackTrace();
		// }

		new CipherController();
	}
}