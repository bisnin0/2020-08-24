package memberform_Me;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn2 {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe) {
			System.out.println("JDBC Driver 로딩에러 = " + cnfe.getMessage());
		}
	}
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String password = "tiger";
	public DBConn2() {}

	public void getConn() {
		try {
			conn = DriverManager.getConnection(url, userid, password);
		}catch(SQLException se) {
			System.out.println("DB연결 에러발생 = " + se.getMessage());
		}
	}
	public void getClose() {
		try {
			if(rs != null)rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(SQLException se) {
			System.out.println("DB닫기 에러발생 = " + se.getMessage());
		}
	}

	
}
