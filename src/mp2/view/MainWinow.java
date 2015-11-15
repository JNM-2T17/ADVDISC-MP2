import java.awt.BorderLayout;
import java.awt.EventQueue;

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

public class MainWinow extends JFrame {

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


	public MainWinow() {
		setResizable(false);
		setForeground(new Color(51, 0, 153));
		setBackground(Color.WHITE);
		setTitle("ADVDISC MP2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);

		menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		menuBar.setForeground(new Color(0, 0, 255));
		menuBar.setBackground(new Color(0, 51, 102));
		setJMenuBar(menuBar);

		mnActions = new JMenu("Actions");
		menuBar.add(mnActions);

		itemEncipher = new JMenuItem("Encipher");
		mnActions.add(itemEncipher);

		itemDecipher = new JMenuItem("Decipher");
		mnActions.add(itemDecipher);

		itemDerive = new JMenuItem("Derive");
		mnActions.add(itemDerive);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		itemInstructions = new JMenuItem("Instructions");
		mnHelp.add(itemInstructions);

		itemCredits = new JMenuItem("Credits");
		mnHelp.add(itemCredits);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCipher = new JButton("ENCIPHER");
		btnCipher.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnCipher.setBounds(10, 109, 374, 94);
		contentPane.add(btnCipher);

		btnDecipher = new JButton("DECIPHER");
		btnDecipher.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnDecipher.setBounds(10, 319, 374, 94);
		contentPane.add(btnDecipher);

		btnDerive = new JButton("DERIVE");
		btnDerive.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnDerive.setBounds(10, 214, 374, 94);
		contentPane.add(btnDerive);

		lblCredits = new JLabel(" (c) Developed by Amadora, Andres,Fernandez, and Syfu");
		lblCredits.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredits.setBounds(66, 424, 318, 15);
		contentPane.add(lblCredits);

		lblWelcome = new JLabel("WELCOME!");
		lblWelcome.setFont(new Font("Serif", Font.PLAIN, 28));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(10, 11, 374, 46);
		contentPane.add(lblWelcome);

		lblWhatDoYou = new JLabel("What do you wish to do today?");
		lblWhatDoYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhatDoYou.setFont(new Font("Serif", Font.PLAIN, 18));
		lblWhatDoYou.setBounds(10, 59, 374, 39);
		contentPane.add(lblWhatDoYou);
	}
}
