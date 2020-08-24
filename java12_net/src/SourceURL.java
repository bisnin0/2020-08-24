import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JTextArea;

public class SourceURL {
	String ta2;
	public SourceURL() {
	}
	public void Source(String msg) {
		try {
			URL url2 = new URL(msg);
			URLConnection con = url2.openConnection();
			con.connect();
			String ct = con.getContentType();
			int idx = ct.indexOf("charset=");
			String code = ct.substring(idx+8);
			
			InputStream is = url2.openStream();
			InputStreamReader isr = new InputStreamReader(is, code);
			BufferedReader br = new BufferedReader(isr);
			
			while(true) {
				String inData = br.readLine();
				if(inData == null) break;
				ta2+=inData+"\n";
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
}
