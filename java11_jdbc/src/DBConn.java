import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn { //DB접속 기능 구현을 해놓고 이걸 다른곳에서 상속받아서 이용할것
	//드라이브로딩
		static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe) {
			System.out.println("JDBC Driver 로딩 에러...-->" + cnfe.getMessage());
		}
	}
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "scott";
		String password = "tiger";
	public DBConn() {
		//생성자에 아무것도 안넣고
	}
	//DB연결 메소드
	public void getConn() {
		try {
			conn = DriverManager.getConnection(url, userid, password); //DB연결
		}catch(SQLException se) {
			System.out.println("DB연결 에러발생 -->" + se.getMessage());
		}
	}
	//DB닫기 
	public void getClose() {
		try {
			if(rs != null)rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(SQLException se) {
			System.out.println("DB닫기 에러발생 -->" + se.getMessage());
		}
	}
}
