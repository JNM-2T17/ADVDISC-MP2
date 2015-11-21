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

public class DerivePanel extends JPanel {
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
    private JButton btnDerive;
    
    private JPanel plaintextPane;
    private JButton loadPlaintextButton;
    private JScrollPane textPane;
    private JTextArea textArea;
    
    private JPanel ciphertextPane;
    private JButton loadCiphertextButton;
    private JScrollPane cipherPane;
    private JTextArea cipherArea;
    
    private IController control;
    boolean inputtingPlain;
    boolean inputtingCipher;

    public DerivePanel(IController control) {
        super( new AGBLayout() );
        this.control = control;

        inputtingPlain = false;
        inputtingCipher = false;

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

        btnSaveCipher = new JButton("Save Cipher");
        btnSaveCipher.setEnabled(false);
        btnSaveCipher.setFont(new Font("Verdana",Font.PLAIN,14));
        btnSaveCipher.addActionListener(new SaveCipherListener());
        AGBLayout.addComp(optionsPane,btnSaveCipher,0,2,1,1,100,100,10,40,5,5
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
        AGBLayout.addComp(optionsPane,btnClearAll,1,3,2,1,100,100,5,5,5,40
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        btnDerive = new JButton("DERIVE CIPHER");
        btnDerive.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnDerive.addActionListener(new DeriveListener());
        AGBLayout.addComp(optionsPane,btnDerive,0,4,2,1,100,100,5,40,10,40
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        AGBLayout.addComp(this,optionsPane,0,0,1,1,100,100,20,20,20,20
                        ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        plaintextPane = new JPanel();
        plaintextPane.setLayout(new BorderLayout());
        
        loadPlaintextButton = new JButton("Load Plaintext File");
        loadPlaintextButton.addActionListener(new LoadPlaintextListener());
        loadPlaintextButton.setFont(new Font("Verdana",Font.PLAIN,14));
        plaintextPane.add(loadPlaintextButton,BorderLayout.NORTH);

        textArea = new JTextArea(20,20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
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
        plaintextPane.add(textPane,BorderLayout.CENTER);

        AGBLayout.addComp(this,plaintextPane,1,0,1,1,100,100,20,0,20,10
                          ,GridBagConstraints.CENTER,GridBagConstraints.BOTH);

        ciphertextPane = new JPanel(new BorderLayout());

        loadCiphertextButton = new JButton("Load Ciphertext File");
        loadCiphertextButton.addActionListener(new LoadCiphertextListener());
        loadCiphertextButton.setFont(new Font("Verdana",Font.PLAIN,14));
        ciphertextPane.add(loadCiphertextButton,BorderLayout.NORTH);

        cipherArea = new JTextArea(20,20);
        cipherArea.setLineWrap(true);
        cipherArea.setWrapStyleWord(true);
        cipherArea.setFont(new Font("Verdana", Font.PLAIN, 12));
        cipherArea.setText("Enter ciphertext here...");
        cipherArea.setBackground(Color.WHITE);
        cipherArea.setOpaque(true);
        cipherArea.addFocusListener(new TextAreaListener2());
        cipherArea.addKeyListener(new TextAreaListener2());

        cipherPane = new JScrollPane(cipherArea,JScrollPane
                                    .VERTICAL_SCROLLBAR_AS_NEEDED
                                    ,JScrollPane
                                    .HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ciphertextPane.add(cipherPane,BorderLayout.CENTER);

        AGBLayout.addComp(this,ciphertextPane,2,0,1,1,100,100,20,10,20,20
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

    public void setPlaintext(String plain) {
        textArea.setText(plain);
        inputtingPlain = plain.length() > 0;
    }

    public void setCiphertext(String ciphertext) {
        cipherArea.setText(ciphertext);
        inputtingCipher = ciphertext.length() > 0;
    }

    private class SaveCipherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser jfc = new JFileChooser();
            jfc.setAcceptAllFileFilterUsed(false);
            jfc.setCurrentDirectory(
                new File(System.getProperty("user.dir")));
            jfc.setFileFilter(new FileNameExtensionFilter("Hill Ciphers","hill"));

            if( jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
                control.saveCipher(jfc.getSelectedFile().getAbsolutePath());
            }
        }
    }

    private class LoadPlaintextListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setAcceptAllFileFilterUsed(false);
                jfc.setCurrentDirectory(
                    new File(System.getProperty("user.dir")));

                if( jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    control.loadPlaintext(jfc.getSelectedFile()
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
            cipherArea.setText("Plaintext here...");
            btnSaveCipher.setEnabled(false);
            inputtingPlain = false;
        }
    }

    private class ClearAllListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            control.clearCipher();
            setMatrix(null);
            textArea.setText("Enter ciphertext here...");
            cipherArea.setText("Plaintext here...");
            btnSaveCipher.setEnabled(false);
            inputtingPlain = false;
        }
    }

    private class DeriveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if( inputtingPlain ) {
                if( inputtingCipher ) {
                    try {
                        if(rbtnHill2.isSelected()) {
                            control.derive(textArea.getText()
                                            ,cipherArea.getText(),2);
                        } else {
                            control.derive(textArea.getText()
                                            ,cipherArea.getText(),3);
                        }
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage()
                                                        ,"No Exact Matrix"
                                                        ,JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Please input ciphertext"
                                                    ,"No Ciphertext"
                                                    ,JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,"Please input plaintext"
                                                ,"No Ciphertext"
                                                ,JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class TextAreaListener implements KeyListener,FocusListener {
        public void keyPressed(KeyEvent e) {
            inputtingPlain = textArea.getText().length() > 0;
        }

        public void keyReleased(KeyEvent e) {
            inputtingPlain = textArea.getText().length() > 0;
        }

        public void keyTyped(KeyEvent e) {
            inputtingPlain = textArea.getText().length() > 0;
        }

        public void focusGained(FocusEvent e) {
            if(!inputtingPlain) {
                textArea.setText("");
            }
        }

        public void focusLost(FocusEvent e) {
            if(!inputtingPlain) {
                textArea.setText("Enter plain text here...");
            }
        }
    }

    private class TextAreaListener2 implements KeyListener,FocusListener {
        public void keyPressed(KeyEvent e) {
            inputtingCipher = cipherArea.getText().length() > 0;
        }

        public void keyReleased(KeyEvent e) {
            inputtingCipher = cipherArea.getText().length() > 0;
        }

        public void keyTyped(KeyEvent e) {
            inputtingCipher = cipherArea.getText().length() > 0;
        }

        public void focusGained(FocusEvent e) {
            if(!inputtingCipher) {
                cipherArea.setText("");
            }
        }

        public void focusLost(FocusEvent e) {
            if(!inputtingCipher) {
                cipherArea.setText("Enter ciphertext here...");
            }
        }
    }
}
