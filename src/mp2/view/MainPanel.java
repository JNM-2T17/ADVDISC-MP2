package mp2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import mp2.controller.IController;
import mp2.view.layout.AGBLayout;

public class MainPanel extends JPanel {
	JButton btnCipher;
	JButton btnDecipher;
	JButton btnDerive;
	JLabel lblCredits;
	JLabel lblWelcome;
	JLabel lblWhatDoYou;

	IController control;

	public MainPanel(IController control) {
		super(new AGBLayout());
		this.control = control;

		lblWelcome = new JLabel("WELCOME!");
		lblWelcome.setFont(new Font("Verdana", Font.PLAIN, 28));
		AGBLayout.addComp(this,lblWelcome,0,0,1,1,100,100
							,GridBagConstraints.CENTER,GridBagConstraints.NONE);

		lblWhatDoYou = new JLabel("What do you wish to do today?");
		lblWhatDoYou.setFont(new Font("Verdana", Font.PLAIN, 18));
		AGBLayout.addComp(this,lblWhatDoYou,0,1,1,1,100,100
							,GridBagConstraints.CENTER,GridBagConstraints.NONE);

		btnCipher = new JButton("ENCIPHER");
		btnCipher.addActionListener(new SwitchListener());
		btnCipher.setFont(new Font("Verdana", Font.PLAIN, 20));
		AGBLayout.addComp(this,btnCipher,0,2,1,2,100,100,10,10,5,10
							,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

		btnDecipher = new JButton("DECIPHER");
		btnDecipher.addActionListener(new SwitchListener());
		btnDecipher.setFont(new Font("Verdana", Font.PLAIN, 20));
		AGBLayout.addComp(this,btnDecipher,0,4,1,2,100,100,5,10,5,10
							,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

		btnDerive = new JButton("DERIVE");
		btnDerive.addActionListener(new SwitchListener());
		btnDerive.setFont(new Font("Verdana", Font.PLAIN, 20));
		AGBLayout.addComp(this,btnDerive,0,6,1,2,100,100,5,10,10,10
							,GridBagConstraints.CENTER,GridBagConstraints.BOTH);


		lblCredits = new JLabel("<html>&copy; Amadora, Andres, Fernandez, Syfu</html>");
		lblCredits.setFont(new Font("Sans Serif", Font.PLAIN, 12));
		AGBLayout.addComp(this,lblCredits,0,8,1,1,100,100,10,10,10,10
							,GridBagConstraints.EAST,GridBagConstraints.NONE);
	}

	public class SwitchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();

			switch(b.getText()) {
				case "ENCIPHER":
					control.setScreen(IController.ENCIPHER);
					break;
				case "DECIPHER":
					control.setScreen(IController.DECIPHER);
					break;
				case "DERIVE":
					control.setScreen(IController.CRACK_CIPHER);
					break;
				default:
					control.setScreen(IController.MAIN);
			}
		}	
	}
}