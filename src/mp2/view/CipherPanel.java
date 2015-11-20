package mp2.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mp2.controller.IController;
import mp2.model.Matrix;
import mp2.view.layout.AGBLayout;

public class CipherPanel extends JPanel {
    private JPanel encipherPane;
    private JPanel optionsPane;
    private JPanel choicePane;
    private JLabel choiceLabel;
    private JRadioButton rbtnHill2;
    private JRadioButton rbtnHill3;
    private ButtonGroup whichCipher;
    private JPanel matrixPanel;
    private JButton btnSetCipher;
    private JButton btnClearCipher;
    private JButton btnClearText;
    private JButton btnClearAll;
    private JButton btnEncipher;
    private JScrollPane textPane;
    private JTextArea textArea;
    private JPanel cipherTextPane;
    private JScrollPane cipherPane;
    private JTextArea cipherArea;
    
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

        AGBLayout.addComp(optionsPane,choicePane,0,0,2,1,100,100
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        matrixPanel = new JPanel(new AGBLayout());
        AGBLayout.addComp(optionsPane,matrixPanel,0,1,2,1,100,100
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        btnSetCipher = new JButton("Set Cipher");
        btnSetCipher.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnSetCipher.addActionListener(new SetCipherListener());
        AGBLayout.addComp(optionsPane,btnSetCipher,0,2,1,1,100,100,10,40,5,5
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        btnClearCipher = new JButton("Clear Cipher");
        btnClearCipher.setFont(new Font("Verdana",Font.PLAIN,14));
        btnClearCipher.addActionListener(new ClearCipherListener());
        AGBLayout.addComp(optionsPane,btnClearCipher,1,2,1,1,100,100,10,5,5,40
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        btnClearText = new JButton("Clear Text");
        btnClearText.setFont(new Font("Verdana",Font.PLAIN,14));
        btnClearText.addActionListener(new ClearTextListener());
        AGBLayout.addComp(optionsPane,btnClearText,0,3,1,1,100,100,5,40,5,5
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        btnClearAll = new JButton("Clear All");
        btnClearAll.setFont(new Font("Verdana",Font.PLAIN,14));
        btnClearAll.addActionListener(new ClearAllListener());
        AGBLayout.addComp(optionsPane,btnClearAll,1,3,1,1,100,100,5,5,5,40
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);


        btnEncipher = new JButton("ENCIPHER");
        btnEncipher.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnEncipher.addActionListener(new EncipherListener());
        AGBLayout.addComp(optionsPane,btnEncipher,0,4,2,1,100,100,5,40,10,40
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        AGBLayout.addComp(this,optionsPane,0,0,1,1,100,100,20,20,20,20
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        encipherPane = new JPanel();
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

        AGBLayout.addComp(this,encipherPane,1,0,1,1,100,100,20,0,20,10
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        cipherTextPane = new JPanel(new BorderLayout());

        cipherArea = new JTextArea(20,20);
        cipherArea.setFont(new Font("Verdana", Font.PLAIN, 12));
        cipherArea.setText("Ciphertext here...");
        cipherArea.setEditable(false);
        cipherArea.setBackground(Color.WHITE);
        cipherArea.setOpaque(true);

        cipherPane = new JScrollPane(cipherArea,JScrollPane
                                    .VERTICAL_SCROLLBAR_AS_NEEDED
                                    ,JScrollPane
                                    .HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cipherTextPane.add(cipherPane,BorderLayout.CENTER);

        AGBLayout.addComp(this,cipherTextPane,2,0,1,1,100,100,20,10,20,20
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
    }

    public void setMatrix(Matrix m) {
        matrixPanel.removeAll();
        if(m != null) {
            for(int i = 0; i < m.colCount(); i++) {
                for(int j = 0; j < m.rowCount(); j++) {
                    AGBLayout.addComp(matrixPanel,new JLabel("" + m.get(j,i))
                                        ,i,j,1,1,100,100
                                        ,GridBagConstraints.CENTER
                                        ,GridBagConstraints.BOTH);
                }
            }

            matrixPanel.repaint();
            matrixPanel.revalidate();
        }
    }

    public void setPlaintext(String plain) {
        textArea.setText(plain);
        inputting = true;
    }

    public void setCiphertext(String ciphertext) {
        cipherArea.setText(ciphertext);
    }

    private class SetCipherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if(rbtnHill2.isSelected()) {
                    control.inputCipher(2);
                } else {
                    control.inputCipher(3);
                }
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null,"Please input a cipher in " 
                                                + "the already open input " 
                                                + "screen", "Screen already " 
                                                + "open", JOptionPane
                                                .WARNING_MESSAGE );
            }
        }
    }

    private class ClearCipherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            control.clearCipher();
            setMatrix(null);
        }
    }

    private class ClearTextListener  implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textArea.setText("Enter plain text here...");
            cipherArea.setText("Ciphertext here...");
            inputting = false;
        }
    }

    private class ClearAllListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            control.clearCipher();
            setMatrix(null);
            textArea.setText("Enter plain text here...");
            cipherArea.setText("Ciphertext here...");
            inputting = false;
        }
    }

    private class EncipherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if( inputting ) {
                try {
                    control.cipher(textArea.getText());
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage()
                                                    ,"No Cipher Matrix"
                                                    ,JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,"Please input plaintext"
                                                ,"No Plaintext"
                                                ,JOptionPane.ERROR_MESSAGE);
            }
        }
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
