import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SourceView extends JFrame implements ActionListener{
	String msg= "";
	SourceURL su = new SourceURL();
	JPanel top = new JPanel();
		JLabel lbl = new JLabel("URL");
		JTextField jf = new JTextField("https://www.naver.com",20);
		JButton btn = new JButton("소스보기");
	JTextArea ta = new JTextArea();
	JScrollPane sp = new JScrollPane(ta);
	public SourceView() {
		top.add(lbl); top.add(jf); top.add(btn);
		add(BorderLayout.NORTH, top); add(BorderLayout.CENTER, sp);
		
		setSize(370,500);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		btn.addActionListener(this);
		
		

		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		if(event.equals("소스보기")) {
			String b = jf.getText();
			su.Source(b);
			String a = su.ta2;
			ta.setText(a);
		}
	}

	
	
	public static void main(String[] args) {
		new SourceView();
	}

}
