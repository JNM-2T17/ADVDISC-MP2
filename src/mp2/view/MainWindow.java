package mp2.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import mp2.controller.IController;
import mp2.view.layout.AGBLayout;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	JMenuBar menuBar;
	JMenu mnActions;
	JMenuItem itemEncipher;
	JMenuItem itemDecipher;
	JMenuItem itemDerive;
	JMenu mnInfo;
	JMenuItem itemTrace;

	IController control;

	public MainWindow(IController control) {
		this.control = control;

		setResizable(false);
		setForeground(new Color(51, 0, 153));
		setBackground(Color.WHITE);
		setTitle("ADVDISC MP2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		itemEncipher.addActionListener(new SwitchListener());
		mnActions.add(itemEncipher);

		itemDecipher = new JMenuItem("Decipher");
		itemDecipher.addActionListener(new SwitchListener());
		mnActions.add(itemDecipher);

		itemDerive = new JMenuItem("Derive");
		itemDerive.addActionListener(new SwitchListener());
		mnActions.add(itemDerive);

		mnInfo = new JMenu("Info");
		mnInfo.setForeground(new Color(200,200,255));
		menuBar.add(mnInfo);

		itemTrace = new JMenuItem("Trace");
		itemTrace.addActionListener(new TraceListener());
		mnInfo.add(itemTrace);

		contentPane = new JPanel(new BorderLayout());
		add(contentPane,BorderLayout.CENTER);

		setVisible(true);
	}

	public void setMain(JPanel panel) {
		contentPane.removeAll();
		if( panel != null ) {
			contentPane.add(panel);
			contentPane.repaint();
			contentPane.revalidate();
		}
	}

	public class SwitchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JMenuItem b = (JMenuItem)e.getSource();

			switch(b.getText()) {
				case "Encipher":
					control.setScreen(IController.ENCIPHER);
					break;
				case "Decipher":
					control.setScreen(IController.DECIPHER);
					break;
				case "Derive":
					control.setScreen(IController.CRACK_CIPHER);
					break;
				default:
					control.setScreen(IController.MAIN);
			}
		}	
	}

	public class TraceListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			control.setScreen(IController.TRACE);
		}
	}
}
