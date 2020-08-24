import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {
	
	//	1. -------------
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//문자열로 지정되어있는 파일의 경로와 파일명을 가지고 객체를 만들어서 리턴해주는 기능
		}catch(ClassNotFoundException e) {System.out.println("드라이브 로딩");}
	}
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs; //import.. 실행관련
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String password = "tiger";
	
	public SelectTest() {
	}
	
	public void start() {
		//선택. DB연결
		try {
			
			//2. -------------
		con = DriverManager.getConnection(url, userid, password); //DB연결 DriverManager
		
			//3. -------------
//		String sql = "select num, username, tel, email, addr, writedate from member order by num"; //쿼리문을 쓴것
		String sql = "select num, username, tel, email, addr, to_char(writedate, 'MM-DD') writedate"
				+ " from member order by num"; //월과 일만 가져온다. )뒤의 weitedate는 별명이된다. //길어서 다음줄로한것. 줄을 바꿀때는 반드시 한칸 띄워야 한다. 
		///////////////애초에 테이블을 잘못 생성해서 에러남(date로 해야하는데 varchar2(20)으로 만듬)
		pstmt = con.prepareStatement(sql);
		//?가 없어서 셋팅할 데이터도 없다.
		
			//4. ------------- 실행
			rs = pstmt.executeQuery(); //ResultSet에 출력결과를 집어넣는다. 한줄씩 읽어지는데 이걸 보는 포인트를 이동시키려면 Next를 쓴다.
			
			while(rs.next()){  //한줄 찍고 다음줄 찍고 반복
				int num = rs.getInt(1);// rs.getInt("num")도 가능. 번호를 입력하면 그 번호순번에 맞는 필드를 가져온다.
				String username = rs.getString(2);
				String tel = rs.getString(3);
				String email = rs.getString(4);
				String addr = rs.getString(5);
				String writedate = rs.getString(6); //db에는 date로 되어있지만 자바는 String으로 읽는다.
				
				System.out.printf("%4d %8s %15s %20s %20s %20s\n", num, username, tel, email, addr, writedate);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close(); //시작한 순서의 반대로
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e) {}
		}
	}

	public static void main(String[] args) {
		SelectTest st = new SelectTest();
		st.start();
	}

}
