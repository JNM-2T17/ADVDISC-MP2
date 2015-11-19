package mp2.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager.*;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JTextField;

import mp2.view.layout.AGBLayout;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	JMenuBar menuBar;
	JMenu mnActions;
	JMenuItem itemEncipher;
	JMenuItem itemDecipher;
	JMenuItem itemDerive;
	JMenu mnHelp;
	JMenuItem itemInstructions;
	JMenuItem itemCredits;
	JButton btnCipher;
	JButton btnDecipher;
	JButton btnDerive;
	JLabel lblCredits;
	JLabel lblWelcome;
	JLabel lblWhatDoYou;


	public MainWindow() {

		setResizable(false);
		setForeground(new Color(51, 0, 153));
		setBackground(Color.WHITE);
		setTitle("ADVDISC MP2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(375,500);
		setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		menuBar.setForeground(new Color(200, 200, 255));
		menuBar.setBackground(new Color(0, 51, 102));
		setJMenuBar(menuBar);

		mnActions = new JMenu("Actions");
		mnActions.setForeground(new Color(200,200,255));
		menuBar.add(mnActions);

		itemEncipher = new JMenuItem("Encipher");
		mnActions.add(itemEncipher);

		itemDecipher = new JMenuItem("Decipher");
		mnActions.add(itemDecipher);

		itemDerive = new JMenuItem("Derive");
		mnActions.add(itemDerive);

		mnHelp = new JMenu("Help");
		mnHelp.setForeground(new Color(200,200,255));
		menuBar.add(mnHelp);

		itemInstructions = new JMenuItem("Instructions");
		mnHelp.add(itemInstructions);

		itemCredits = new JMenuItem("Credits");
		mnHelp.add(itemCredits);

		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new AGBLayout());

		lblWelcome = new JLabel("WELCOME!");
		lblWelcome.setFont(new Font("Verdana", Font.PLAIN, 28));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		AGBLayout.addComp(contentPane,lblWelcome,0,0,1,1,100,100
							,GridBagConstraints.CENTER,GridBagConstraints.NONE);

		lblWhatDoYou = new JLabel("What do you wish to do today?");
		lblWhatDoYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhatDoYou.setFont(new Font("Verdana", Font.PLAIN, 18));
		AGBLayout.addComp(contentPane,lblWhatDoYou,0,1,1,1,100,100
							,GridBagConstraints.CENTER,GridBagConstraints.NONE);

		btnCipher = new JButton("ENCIPHER");
		btnCipher.setFont(new Font("Verdana", Font.PLAIN, 20));
		AGBLayout.addComp(contentPane,btnCipher,0,2,1,2,100,100,10,10,5,10
							,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

		btnDecipher = new JButton("DECIPHER");
		btnDecipher.setFont(new Font("Verdana", Font.PLAIN, 20));
		AGBLayout.addComp(contentPane,btnDecipher,0,4,1,2,100,100,5,10,5,10
							,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

		btnDerive = new JButton("DERIVE");
		btnDerive.setFont(new Font("Verdana", Font.PLAIN, 20));
		AGBLayout.addComp(contentPane,btnDerive,0,6,1,2,100,100,5,10,10,10
							,GridBagConstraints.CENTER,GridBagConstraints.BOTH);


		lblCredits = new JLabel("<html>&copy; Amadora, Andres, Fernandez, Syfu</html>");
		lblCredits.setFont(new Font("Sans Serif", Font.PLAIN, 12));
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		AGBLayout.addComp(contentPane,lblCredits,0,8,1,1,100,100
							,GridBagConstraints.EAST,GridBagConstraints.NONE);




		setVisible(true);
	}
}
