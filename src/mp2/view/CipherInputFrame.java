package mp2.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mp2.controller.IController;
import mp2.view.layout.AGBLayout;

public class CipherInputFrame extends JFrame {
	private JPanel cipherInputPanel;
	private JPanel labelPanel;
	private JLabel inputLabel;
	private JPanel matrixPanel;
	private JTextField[][] inputFields;
	private JPanel cipherPanel;
	private JButton setCipherButton;

	private IController control;

	public CipherInputFrame(IController control) {
		this.control = control;

		setSize(300,300);
		setTitle("Set Cipher");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		cipherInputPanel = new JPanel(new BorderLayout());

		labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		inputLabel = new JLabel("Enter Cipher");
		inputLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelPanel.add(inputLabel);

		cipherInputPanel.add(labelPanel,BorderLayout.NORTH);

		matrixPanel = new JPanel(new AGBLayout());
		cipherInputPanel.add(matrixPanel,BorderLayout.CENTER);

		cipherPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		setCipherButton = new JButton("Set Cipher");
		setCipherButton.setFont(new Font("Verdana", Font.PLAIN, 14));
		setCipherButton.addActionListener(new SetListen());
		cipherPanel.add(setCipherButton);

		cipherInputPanel.add(cipherPanel,BorderLayout.SOUTH);

		add(cipherInputPanel,BorderLayout.CENTER);
	}

	public void setDimension(int n) {
		matrixPanel.removeAll();
		inputFields = new JTextField[n][n];

		for(int i = 0; i < n; i++ ) {
			for( int j = 0; j < n; j++ ) {
				inputFields[i][j] = new JTextField(10);
				inputFields[i][j].setBorder(BorderFactory
											.createEmptyBorder(10,10,10,10));
				AGBLayout.addComp(matrixPanel,inputFields[i][j],j,i,1,1,100,100
									,10,10,10,10,GridBagConstraints.CENTER
									,GridBagConstraints.BOTH);
			}
		}
		matrixPanel.repaint();
		matrixPanel.revalidate();
	}

	private class SetListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int[][] matrix = new int[inputFields.length][inputFields.length];
			String error = "";

			for(int i = 0; i < matrix.length; i++ ) {
				for(int j = 0; j < matrix[0].length; j++ ) {
					try {
						matrix[i][j] = Integer.parseInt(inputFields[i][j]
														.getText());
					} catch(NumberFormatException nfe) {
						error += (error.length() == 0 ? "" : "\n" ) 
									+ "Input A" + (i + 1) + (j + 1) 
									+ " is not a number"; 
					}
				}
			}

			if( error.length() == 0 ) {
				try {
					control.setCipher(matrix);
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null,ex.getMessage()
												,"Invalid Cipher"
												,JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null,error,"Input Format Error"
												,JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}