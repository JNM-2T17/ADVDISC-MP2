package mp2.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mp2.controller.IController;
import mp2.view.layout.AGBLayout;

public class CipherPanel extends JPanel {
    private JPanel encipherPane;
    private JScrollPane textPane;
    private JTextArea textArea;
    private JPanel optionsPane;
    private JPanel choicePane;
    private JLabel choiceLabel;
    private JRadioButton rbtnHill2;
    private JRadioButton rbtnHill3;
    private ButtonGroup whichCipher;
    private JButton btnSetCipher;
    private JButton btnEncipher;

    private IController control;
    boolean inputting;

	public CipherPanel(IController control) {
        super( new AGBLayout() );
        this.control = control;

        inputting = false;

        optionsPane = new JPanel(new AGBLayout());

        // ok wtf austin idk how the fuck to make it horizontal its adding vertical
        // wtffffffffffffff fuck me
        choicePane = new JPanel(new AGBLayout());
        choicePane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        choiceLabel = new JLabel("Cipher Type");
        choiceLabel.setFont(new Font("Verdana",Font.BOLD,14));
        AGBLayout.addComp(choicePane,choiceLabel,0,0,1,1,100,100
                            ,GridBagConstraints.CENTER,GridBagConstraints.NONE);
        
        rbtnHill2 = new JRadioButton("Hill - 2");
        rbtnHill2.setSelected(true);

        rbtnHill3 = new JRadioButton("Hill - 3");

        whichCipher = new ButtonGroup();
        whichCipher.add(rbtnHill2);
        whichCipher.add(rbtnHill3);

        AGBLayout.addComp(choicePane,rbtnHill2,0,1,1,1,100,100
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        AGBLayout.addComp(choicePane,rbtnHill3,0,2,1,1,100,100
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        AGBLayout.addComp(optionsPane,choicePane,0,0,1,1,100,100
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        btnSetCipher = new JButton("Set Cipher");
        btnSetCipher.setFont(new Font("Verdana", Font.PLAIN, 14));
        AGBLayout.addComp(optionsPane,btnSetCipher,0,1,1,1,100,100,30,40,20,40
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        btnEncipher = new JButton("ENCIPHER");
        btnEncipher.setFont(new Font("Verdana", Font.PLAIN, 14));
        AGBLayout.addComp(optionsPane,btnEncipher,0,2,1,1,100,100,20,40,30,40
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        AGBLayout.addComp(this,optionsPane,0,0,1,1,100,100,20,20,20,20
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        encipherPane = new JPanel();
        encipherPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        encipherPane.setLayout(new BorderLayout());
        
        textArea = new JTextArea(20,20);
		textArea.setFont(new Font("Verdana", Font.PLAIN, 12));
        textArea.setText("Enter plain text here...");
        textArea.setBackground(Color.WHITE);
        textArea.setOpaque(true);
        textArea.addFocusListener(new TextAreaListener());
        textArea.addKeyListener(new TextAreaListener());

        textPane = new JScrollPane(textArea,JScrollPane
                                    .VERTICAL_SCROLLBAR_AS_NEEDED
                                    ,JScrollPane
                                    .HORIZONTAL_SCROLLBAR_AS_NEEDED);
		encipherPane.add(textPane,BorderLayout.CENTER);

        AGBLayout.addComp(this,encipherPane,1,0,1,1,100,100,20,20,20,20
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
    }

    private class TextAreaListener implements KeyListener,FocusListener {
        public void keyPressed(KeyEvent e) {
            inputting = textArea.getText().length() > 0;
        }

        public void keyReleased(KeyEvent e) {
            inputting = textArea.getText().length() > 0;
        }

        public void keyTyped(KeyEvent e) {
            inputting = textArea.getText().length() > 0;
        }

        public void focusGained(FocusEvent e) {
            if(!inputting) {
                textArea.setText("");
            }
        }

        public void focusLost(FocusEvent e) {
            if(!inputting) {
                textArea.setText("Enter plain text here...");
            }
        }
    }
}
