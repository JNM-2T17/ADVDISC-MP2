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
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import mp2.controller.IController;
import mp2.model.Matrix;
import mp2.view.layout.AGBLayout;

public class DecipherPanel extends JPanel {
    private JPanel optionsPane;
    
    private JPanel choicePane;
    private JLabel choiceLabel;
    private JRadioButton rbtnHill2;
    private JRadioButton rbtnHill3;
    private ButtonGroup whichCipher;
    
    private JPanel matrixPanel;
    
    private JButton btnSetCipher;
    private JButton btnSaveCipher;
    private JButton btnLoadCipher;
    private JButton btnClearCipher;
    private JButton btnClearText;
    private JButton btnClearAll;
    private JButton btnDecipher;
    private JButton btnHome;
    
    private JPanel cipherPane;
    private JButton loadButton;
    private JScrollPane textPane;
    private JTextArea textArea;
    
    private JPanel plaintextPane;
    private JButton saveButton;
    private JScrollPane plainPane;
    private JTextArea plainArea;
    
    private IController control;
    boolean inputting;

	public DecipherPanel(IController control) {
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
        AGBLayout.addComp(optionsPane,btnSetCipher,0,2,2,1,100,100,10,40,5,20
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        btnSaveCipher = new JButton("Save Cipher");
        btnSaveCipher.setFont(new Font("Verdana",Font.PLAIN,14));
        btnSaveCipher.addActionListener(new SaveCipherListener());
        AGBLayout.addComp(optionsPane,btnSaveCipher,0,3,1,1,100,100,5,40,5,5
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        btnLoadCipher = new JButton("Load Cipher");
        btnLoadCipher.setFont(new Font("Verdana",Font.PLAIN,14));
        btnLoadCipher.addActionListener(new LoadCipherListener());
        AGBLayout.addComp(optionsPane,btnLoadCipher,1,3,1,1,100,100,5,5,5,20
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
        

        btnClearCipher = new JButton("Clear Cipher");
        btnClearCipher.setFont(new Font("Verdana",Font.PLAIN,14));
        btnClearCipher.addActionListener(new ClearCipherListener());
        AGBLayout.addComp(optionsPane,btnClearCipher,0,4,1,1,100,100,5,40,5,5
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        btnClearText = new JButton("Clear Text");
        btnClearText.setFont(new Font("Verdana",Font.PLAIN,14));
        btnClearText.addActionListener(new ClearTextListener());
        AGBLayout.addComp(optionsPane,btnClearText,1,4,1,1,100,100,5,5,5,20
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        btnClearAll = new JButton("Clear All");
        btnClearAll.setFont(new Font("Verdana",Font.PLAIN,14));
        btnClearAll.addActionListener(new ClearAllListener());
        AGBLayout.addComp(optionsPane,btnClearAll,0,5,2,1,100,100,5,40,5,20
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        btnDecipher = new JButton("Decipher");
        btnDecipher.setFont(new Font("Verdana", Font.BOLD, 14));
        btnDecipher.addActionListener(new DecipherListener());
        AGBLayout.addComp(optionsPane,btnDecipher,0,6,2,1,100,100,5,40,5,20
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        btnHome = new JButton("Home");
        btnHome.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnHome.addActionListener(new HomeListener());
        AGBLayout.addComp(optionsPane,btnHome,0,7,2,1,100,100,5,40,10,20
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        AGBLayout.addComp(this,optionsPane,0,0,1,1,100,100,20,20,20,20
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        cipherPane = new JPanel();
        cipherPane.setLayout(new BorderLayout());
        
        loadButton = new JButton("Load Ciphertext File");
        loadButton.addActionListener(new LoadCiphertextListener());
        loadButton.setFont(new Font("Verdana",Font.PLAIN,14));
        cipherPane.add(loadButton,BorderLayout.NORTH);

        textArea = new JTextArea(20,20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Verdana", Font.PLAIN, 12));
        textArea.setText("Enter ciphertext here...");
        textArea.setBackground(Color.WHITE);
        textArea.setOpaque(true);
        textArea.addFocusListener(new TextAreaListener());
        textArea.addKeyListener(new TextAreaListener());

        textPane = new JScrollPane(textArea,JScrollPane
                                    .VERTICAL_SCROLLBAR_AS_NEEDED
                                    ,JScrollPane
                                    .HORIZONTAL_SCROLLBAR_AS_NEEDED);
		cipherPane.add(textPane,BorderLayout.CENTER);

        AGBLayout.addComp(this,cipherPane,1,0,1,1,100,100,20,0,20,10
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        plaintextPane = new JPanel(new BorderLayout());

        saveButton = new JButton("Save Plaintext File");
        saveButton.addActionListener(new SavePlaintextListener());
        saveButton.setEnabled(false);
        saveButton.setFont(new Font("Verdana",Font.PLAIN,14));
        plaintextPane.add(saveButton,BorderLayout.NORTH);

        plainArea = new JTextArea(20,20);
        plainArea.setLineWrap(true);
        plainArea.setWrapStyleWord(true);
        plainArea.setFont(new Font("Verdana", Font.PLAIN, 12));
        plainArea.setText("Plaintext here...");
        plainArea.setEditable(false);
        plainArea.setBackground(Color.WHITE);
        plainArea.setOpaque(true);

        plainPane = new JScrollPane(plainArea,JScrollPane
                                    .VERTICAL_SCROLLBAR_AS_NEEDED
                                    ,JScrollPane
                                    .HORIZONTAL_SCROLLBAR_AS_NEEDED);
        plaintextPane.add(plainPane,BorderLayout.CENTER);

        AGBLayout.addComp(this,plaintextPane,2,0,1,1,100,100,20,10,20,20
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
        }
        matrixPanel.repaint();
        matrixPanel.revalidate();
    }

    public void setCiphertext(String plain) {
        textArea.setText(plain);
        inputting = true;
    }

    public void setPlaintext(String ciphertext) {
        plainArea.setText(ciphertext);
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

    private class HomeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            control.setScreen(IController.MAIN);
        }
    }

    private class SaveCipherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if( control.isCipherSet()) {
                JFileChooser jfc = new JFileChooser();
                jfc.setAcceptAllFileFilterUsed(false);
                jfc.setCurrentDirectory(
                    new File(System.getProperty("user.dir")));
                jfc.setFileFilter(new FileNameExtensionFilter("Hill Ciphers","hill"));

                if( jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
                    control.saveCipher(jfc.getSelectedFile().getAbsolutePath());
                }
            } else {
                JOptionPane.showMessageDialog(null,"Please input a cipher " 
                                                + "matrix.","No Cipher Matrix"
                                                ,JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class LoadCipherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser jfc = new JFileChooser();
            jfc.setAcceptAllFileFilterUsed(false);
            jfc.setCurrentDirectory(
                new File(System.getProperty("user.dir")));
            jfc.setFileFilter(new FileNameExtensionFilter("Hill Ciphers","hill"));

            if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                control.loadCipher(jfc.getSelectedFile().getAbsolutePath());
            }
        }
    }

    private class SavePlaintextListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setAcceptAllFileFilterUsed(false);
                jfc.setCurrentDirectory(
                    new File(System.getProperty("user.dir")));

                if( jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
                    control.saveText(plainArea.getText(),jfc.getSelectedFile()
                                        .getAbsolutePath());
                }
        }
    }

    private class LoadCiphertextListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser jfc = new JFileChooser();
                jfc.setAcceptAllFileFilterUsed(false);
                jfc.setCurrentDirectory(
                    new File(System.getProperty("user.dir")));

                if( jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    control.loadCiphertext(jfc.getSelectedFile()
                                            .getAbsolutePath());
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
            textArea.setText("Enter ciphertext here...");
            plainArea.setText("Plaintext here...");
            saveButton.setEnabled(false);
            inputting = false;
        }
    }

    private class ClearAllListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            control.clearCipher();
            setMatrix(null);
            textArea.setText("Enter ciphertext here...");
            plainArea.setText("Plaintext here...");
            saveButton.setEnabled(false);
            inputting = false;
        }
    }

    private class DecipherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if( inputting ) {
                try {
                    control.decipher(textArea.getText());
                    saveButton.setEnabled(true);
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage()
                                                    ,"No Cipher Matrix"
                                                    ,JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,"Please input ciphertext"
                                                ,"No Ciphertext"
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
                textArea.setText("Enter ciphertext here...");
            }
        }
    }
}
