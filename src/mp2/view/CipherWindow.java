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
import javax.swing.*;

import mp2.view.layout.AGBLayout;

public class CipherWindow extends JFrame {

	private JPanel contentPane;

  public CipherWindow(){

    setResizable(false);
    setForeground(new Color(51, 0, 153));
    setBackground(Color.WHITE);
    setTitle("ADVDISC MP2 - Cipher Window");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(375,500);
    setLocationRelativeTo(null);

    JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		menuBar.setForeground(new Color(200, 200, 255));
		menuBar.setBackground(new Color(0, 51, 102));
		setJMenuBar(menuBar);

		JMenu mnActions = new JMenu("Actions");
		mnActions.setForeground(new Color(200,200,255));
		menuBar.add(mnActions);

		JMenuItem itemEncipher = new JMenuItem("Encipher");
		mnActions.add(itemEncipher);

		JMenuItem itemDecipher = new JMenuItem("Decipher");
		mnActions.add(itemDecipher);

		JMenuItem itemDerive = new JMenuItem("Derive");
		mnActions.add(itemDerive);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setForeground(new Color(200,200,255));
		menuBar.add(mnHelp);

		JMenuItem itemInstructions = new JMenuItem("Instructions");
		mnHelp.add(itemInstructions);

		JMenuItem itemCredits = new JMenuItem("Credits");
		mnHelp.add(itemCredits);

    contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
    contentPane.setLayout(new AGBLayout());

    JPanel EncipherPane = new JPanel();
    EncipherPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    EncipherPane.setLayout(new AGBLayout());
    AGBLayout.addComp(contentPane,EncipherPane,0,0,1,2,100,100,120,0,50,0
              ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

    //note for layout purposes only. delete this once you've implemented making
    //an editable JLabel
    JLabel lblEnterText = new JLabel("Enter Text to be Enciphered.");
    lblEnterText.setFont(new Font("Verdana", Font.PLAIN, 12));
    lblEnterText.setHorizontalAlignment(SwingConstants.CENTER);
    AGBLayout.addComp(EncipherPane,lblEnterText,0,0,1,2,100,100,10,0,0,0
              ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

    JLabel lblWelcome = new JLabel("_____________");
		lblWelcome.setFont(new Font("Verdana", Font.PLAIN, 28));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
    AGBLayout.addComp(EncipherPane,lblWelcome,0,2,1,2,100,100,-150,0,0,0
              ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);


    // change this if the user has already loaded a cipher file
    JLabel lblFileLoaded = new JLabel("No file loaded. Data inputted will be enciphered.");
    lblFileLoaded.setFont(new Font("Verdana", Font.PLAIN, 10));
    lblFileLoaded.setHorizontalAlignment(SwingConstants.CENTER);

    AGBLayout.addComp(contentPane,lblFileLoaded,0,2,1,2,100,100,0,0,0,0
              ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

    JRadioButton rbtnHill2 = new JRadioButton("Hill - 2");
    rbtnHill2.setSelected(true);

    JRadioButton rbtnHill3 = new JRadioButton("Hill - 3");

    ButtonGroup whichCipher = new ButtonGroup();
    whichCipher.add(rbtnHill2);
    whichCipher.add(rbtnHill3);

    // ok wtf austin idk how the fuck to make it horizontal its adding vertical
    // wtffffffffffffff fuck me
    JPanel choicePane = new JPanel();
    choicePane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    choicePane.setLayout(new AGBLayout());
    AGBLayout.addComp(contentPane,choicePane,0,4,1,2,100,100
              ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

    AGBLayout.addComp(choicePane,rbtnHill2,0,0,0,1,100,100
              ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

    AGBLayout.addComp(choicePane,rbtnHill3,2,2,2,1,100,100,0,0,0,0
              ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);


    JButton btnEncipher = new JButton("ENCIPHER");
    btnEncipher.setFont(new Font("Verdana", Font.PLAIN, 20));
    AGBLayout.addComp(contentPane,btnEncipher,0,6,1,1,100,100,5,10,5,10
              ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

    JLabel lblCredits = new JLabel("<html>&copy; Amadora, Andres, Fernandez, Syfu</html>");
    lblCredits.setFont(new Font("Sans Serif", Font.PLAIN, 12));
    lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
    AGBLayout.addComp(contentPane,lblCredits,0,8,1,2,100,100
          		,GridBagConstraints.EAST,GridBagConstraints.NONE);

    
    setVisible(true);
  }
}
