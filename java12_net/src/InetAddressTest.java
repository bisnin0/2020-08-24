import java.net.InetAddress;

public class InetAddressTest {

	public InetAddressTest() {
	}
	
	public void start() {
		try {
		//ip, domain을 이용하여 inetAdress객체를 생성하여 통신한다. 프로토콜은 빼야한다.
		
		//내 컴퓨터의 아이피 얻어오기
		InetAddress ia1 = InetAddress.getLocalHost(); //예외발생
		//domain 구하기
		String hostName = ia1.getHostName(); //domain, domain이 없을경우 컴퓨터 이름을 구한다.
		//ip구하기
		String address = ia1.getHostAddress();
		
		System.out.println(hostName + ", "+ address);
		
		//url주소를 이용한 InetAddress객체 구하기
		InetAddress ia2 = InetAddress.getByName("www.naver.com");
		System.out.println("ia2.address="+ia2.getHostAddress()); //ip //주소창에 ip주소치면 네이버 접속가능
		System.out.println("ia2.name="+ia2.getHostName());//domain
		
		//210.89.160.88 ==방금 구한 네이버 ip
		//ip를 이용한 InetAddress객체생성
		InetAddress ia3 = InetAddress.getByName("210.89.160.88"); //ia3를 이용해 저 아이피 주소와 통신 가능
		System.out.println("ia3.address=" + ia3.getHostAddress()); 
		System.out.println("ia3.name="+ ia3.getHostName());//ip를 직접쓰면 domain은 구해지지 않는다.
		
		
		//ip가 여러개일때
		InetAddress ia4[] = InetAddress.getAllByName("www.naver.com");
		System.out.println("-------------------------");
		for(InetAddress ia: ia4) { //오른쪽에 배열이름 왼쪽에 그 배열의 데이터 타입
			System.out.println("ia.name="+ia.getHostName());
			System.out.println("ua.address="+ia.getHostAddress());
		}
		InetAddress ia5[] = InetAddress.getAllByName("www.joara.com");
		System.out.println("-------------------------");
		for(InetAddress ia: ia5) { //오른쪽에 배열이름 왼쪽에 그 배열의 데이터 타입
			System.out.println("ia.name="+ia.getHostName());
			System.out.println("ua.address="+ia.getHostAddress());
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new InetAddressTest().start(); 
	}

}
