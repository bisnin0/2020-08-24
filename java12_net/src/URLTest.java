import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {

	public URLTest() {
		start();
	}
	public void start() {
		try {
		URL url = new URL("https://www.naver.com");//도메인 포함.. 예외처리
		System.out.println("protocol="+url.getProtocol());//통신 규약
		System.out.println("host="+url.getHost());
		System.out.println("path="+url.getPath());
		System.out.println("port="+url.getPort());
		System.out.println("filename="+url.getFile());

		//Page Header객체 얻어오기 // 한글코드 뭘 쓰고 있는지 본다.
		//1. URL객체를 이용해서 URLConnection생성한다.
		URLConnection connection = url.openConnection(); 
		//2. 통신채널 확보.
		connection.connect(); //통신이 가능해짐
		//3. 헤더 정보 얻어오기
		String contentType = connection.getContentType();
		System.out.println("contentType-->"+contentType);
		//text/html; charset=UTF-8가 나온다. 여기서 UTF-8 앞의 charset=의 위치를 구해서 
		
		//charset=위치 구하기
		int idx=contentType.indexOf("charset=");// indexOf의 이유charset=의위치 알아내려고 c에대한 위치가 구해진다. 단어의 시작위치
		String code = contentType.substring(idx+8); //마지막 글자 다음에 있는게 필요하기때문에 c까지의 길이 idx + charset=의 길이 8 하면 나옴
		System.out.println("code="+code); 
		
		
		InputStream is = url.openStream(); //inputStream은 byte단위라. 문자단위로 읽어오게 처리하자
		InputStreamReader isr = new InputStreamReader(is, code);//한글자씩 읽어오는것. 버퍼로 한줄씩 읽어오게 처리하자 char로 읽어오게한다.
		////////////////////////////////////////////////////////
		//InputStreamReader가 한글을 처음으로 읽어오는곳  위에서 구한 code객체를 넣어주면 깨지던 한글이 정상적으로 보인다. ///
		BufferedReader br = new BufferedReader(isr); //버퍼로 한줄씩 읽게 만듬
		
		while(true) {
			String inData = br.readLine();
			if(inData == null) break;
			System.out.println(inData);
			
		}
		
		
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new URLTest();
	}

}
