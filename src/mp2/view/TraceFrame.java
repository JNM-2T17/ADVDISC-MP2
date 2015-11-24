package mp2.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TraceFrame extends JFrame implements ITrace {
	private JPanel mainPanel;
	private JTextArea textArea;
	private JScrollPane textPane;
	private boolean blank;

	public TraceFrame() {
		blank = true;

		setTitle("Trace");
		setSize(750,500);
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		setLocation(d.width / 5, d.height / 5);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		mainPanel = new JPanel(new BorderLayout());

		textArea = new JTextArea(10,40);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Verdana",Font.PLAIN,14));
		textArea.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

		textPane = new JScrollPane(textArea
								,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
								,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(textPane,BorderLayout.CENTER);

		add(mainPanel,BorderLayout.CENTER);
	}
	
	public void clear() {
		blank = true;
		textArea.setText("No trace to display");
	}

	public void print(String s) {
		if(blank) {
			textArea.setText(s);
			blank = false;
		} else {
			textArea.setText(textArea.getText() + s);
		}
	}
}