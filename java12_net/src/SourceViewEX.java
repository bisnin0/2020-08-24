import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SourceViewEX extends JFrame implements ActionListener{
	JPanel northPane = new JPanel(new BorderLayout());
		JLabel urlLbl = new JLabel("URL");
		JTextField urlTf = new JTextField();
		JButton okBtn = new JButton("소스보기");
	JTabbedPane tp = new JTabbedPane();
	
	public SourceViewEX() {
		northPane.add(BorderLayout.WEST, urlLbl);
		northPane.add(urlTf);
		northPane.add(BorderLayout.EAST, okBtn);
		add(BorderLayout.NORTH, northPane);
		
		add(tp);
		setSize(600,700);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		urlTf.addActionListener(this);
		okBtn.addActionListener(this);
		
	}

	public void sourceView() {
		String url = urlTf.getText();
		if (!url.equals("")) { //같지않을때
			try {
			//소스보기
			URL urlObj = new URL(url);
			
			URLConnection conn = urlObj.openConnection();
			conn.connect();
			
			String header = conn.getContentType();// text/html; charset=URF=8
			String encode = header.substring(header.indexOf("charset=")+8);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(urlObj.openStream(), encode));
			JTextArea ta = new JTextArea();
			JScrollPane sp = new JScrollPane(ta);
			
			while(true) {
				String inData = br.readLine();
				if(inData == null)break;
				ta.append(inData+"\n");
			}
			
			tp.addTab(url, sp);
			urlTf.setText("https://");
			
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this, "URL주소를 입력후 사용하세요. \n다시입력후 시작하세요.");
			}		
		}else {
			JOptionPane.showMessageDialog(this, "URL주소를 입력후 사용하세요.");
		}
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj == urlTf || obj == okBtn) {
			sourceView();
		}
	}
	
	public static void main(String[] args) {
		new SourceViewEX();
	}

}
