import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest {
	Scanner scan = new Scanner(System.in);
	
	//////////기본적으로 이자리에는 실행문이 올 수 없다. 예를들면 int a = b+c;
	//////////하지만 static을 이용했을때는 멤버에 실행문을 표기할 수 있다.
	
	static { //자동으로 실행되면서 자바가상머신에 jdbc드라이브를 등록한다.
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver"); //이 클래스가 어딨는가 경로를 적는다.
		}catch(ClassNotFoundException c) {
			System.out.println("드라이브 에러발생.."+c.getMessage()); //나중에 개발할때 이런걸 써놓는게 에러찾는데 큰 도움이된다.
		}
	}
	
	Connection conn; //java.sql import
	PreparedStatement pstmt;  //변수선언. 3.번 진행준
	
	public InsertTest() {
		//	jdbc 드라이브 등록  ..................... 멤버변수로 이동
		//	예외처리 반드시 해야한다. 왼쪽 빨간색 x표시 마우스 올리면 예외처리인지 아닌지 구분 가능
//		try {
//		Class.forName("oracle.jdbc.driver.OracleDriver"); //이 클래스가 어딨는가 경로를 적는다.
//		}catch(ClassNotFoundException c) {
//			System.out.println("드라이브 에러발생.."+c.getMessage()); //나중에 개발할때 이런걸 써놓는게 에러찾는데 큰 도움이된다.
//		}
		
		
	}
	
	
	/*
	 *	자바에서 database 사용하기 
	 *  1. 자바가상머신에 jdbc드라이브 등록 Class.forName(ojdbc 드라이브 내부 경로);
	 *	2. 데이터베이스 연결, 등록(아이디 비밀번호 sid등 정보 입력) ...멤버변수에 등록후 start끝에 넣음
	 *	3. 쿼리문을 작성하여 preparedStatement 객체생성
	 * 
	 * 
	 */
	
	
	
	public void start() {
		//// ------------- 1. 자바가상머신에 jdbc드라이브 등록.. 여기도 가능함.
		while(true) {
			//// -----------  1. 자바가상머신에 jdbc드라이브 등록 .. 여기도 가능하지만 추천하지않음(반복문이라)
			try {//혹시 번호를 문자로 넣으면 에러 발생. 예외처리
			//입력
			System.out.print("번호= ");
			int num = Integer.parseInt(scan.nextLine());
			System.out.print("이름= ");
			String username = scan.nextLine();
			System.out.print("연락처= ");
			String tel = scan.nextLine();
			System.out.print("이메일= ");
			String email = scan.nextLine();
			System.out.print("주소= ");
			String addr = scan.nextLine();
			
			//데이터베이스 연결						서버:port:sid
			String url = "jdbc:oracle:thin:@192.168.0.214:1521:xe";//길어서 변수에 만들어 넣음.. mysql은 다르다. 데이터베이스별로 다름.. 
											//내 컴퓨터는 localhost 다른 원격지라고하면 ip주소나 도메인을 쓴다.
			conn = DriverManager.getConnection(url, "scott", "tiger");
			
			//쿼리문 작성
			String sql = "insert into member(num, username, writedate, tel, email, addr)" //db에있는것과 순서 달라도 상관없다. 필드 나열일뿐이니까
					+ "values(?,?,sysdate,?,?,?)"; //변수에 들어갈 데이터는 ?로 넣는다. 나중에 위에서 입력받아 들어올거니까.. 순서는 위에 쓴것과 같아야한다.
			
			pstmt = conn.prepareStatement(sql); //sql쿼리문이 pstmt에 들어가있다는것. pstmt는 이부분에서 멤버변수에 선언해준다.
			//?에 대입될 데이터를 셋팅한다.
			pstmt.setInt(1, num); //parameterIndex가 나오는데 이건 몇번째 ? 인지 지정하면된다. 1을 쓰면 위에 첫번째 ?에 정수로 셋팅한다. 
			pstmt.setString(2, username); // 두번째 ?에 문자로 셋팅한다.
			pstmt.setString(3, tel);
			pstmt.setString(4, email);
			pstmt.setString(5, addr);
			
			//실행
			int cnt = pstmt.executeUpdate(); //몇개의 레코드를 추가했는지 돌아온다. // 결과가 int 로 돌아옴
			if(cnt>0) {
				System.out.println("레코드가 추가 되었습니다."); //gui가 없으니까 콘솔에 출력해서 확인해본
			}else {
				System.out.println("레코드 추가 실패하였습니다.");
			}
			
			///에러가 나던 안나던 무조건 db는 닫아야 한다.. 예를들어 pstmt.setInt(1, num);부분에서 에러가 나면 그 밑은다 실행이 안된다.
			///								 대신 에러가 생기면 catch로 바로 오기때문에 이부분을 이용해서 db를 닫아준다.
			///								에러가 안생겨도 catch문의 끝에서 실행된다. 이걸 finally를 이용해서 둘 다 잡는다.
			///								catch문의 finally를 이용해서 에러가 나도 안나도 db닫는 작업을 실행해준다.
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				//DB닫기
/*				
				if(pstmt!=null) { //pstmt객체가 만들어졌는지 확인.
					try {pstmt.close();}catch(SQLException s) {} //빨간줄 .. 예외처리
				}
				if(conn!=null) { //conn 객체가 만들어졌는지 확인.
					try {conn.close();}catch(SQLException r) {} //예외처리
				}
*/ // 위 아래 둘중 하나를 쓰면 되는데 아래가 더 간편하니까 아래를 쓰자. 예외처리 각자 할건지 둘 다 한번에 할건지				
				//
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close(); //conn이 먼저 만들어진거니까 나중에 닫는다.
				}catch(SQLException s) {
					System.out.println("DB닫기 에러발생..");
				}
			}
		}
	}

	public static void main(String[] args) {
		InsertTest it = new InsertTest();
		it.start();
	}

}
