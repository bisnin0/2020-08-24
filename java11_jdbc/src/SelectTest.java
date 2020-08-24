import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {
	
	//	1. -------------
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//���ڿ��� �����Ǿ��ִ� ������ ��ο� ���ϸ��� ������ ��ü�� ���� �������ִ� ���
		}catch(ClassNotFoundException e) {System.out.println("����̺� �ε�");}
	}
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs; //import.. �������
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String password = "tiger";
	
	public SelectTest() {
	}
	
	public void start() {
		//����. DB����
		try {
			
			//2. -------------
		con = DriverManager.getConnection(url, userid, password); //DB���� DriverManager
		
			//3. -------------
//		String sql = "select num, username, tel, email, addr, writedate from member order by num"; //�������� ����
		String sql = "select num, username, tel, email, addr, to_char(writedate, 'MM-DD') writedate"
				+ " from member order by num"; //���� �ϸ� �����´�. )���� weitedate�� �����̵ȴ�. //�� �����ٷ��Ѱ�. ���� �ٲܶ��� �ݵ�� ��ĭ ����� �Ѵ�. 
		///////////////���ʿ� ���̺��� �߸� �����ؼ� ������(date�� �ؾ��ϴµ� varchar2(20)���� ����)
		pstmt = con.prepareStatement(sql);
		//?�� ��� ������ �����͵� ����.
		
			//4. ------------- ����
			rs = pstmt.executeQuery(); //ResultSet�� ��°���� ����ִ´�. ���پ� �о����µ� �̰� ���� ����Ʈ�� �̵���Ű���� Next�� ����.
			
			while(rs.next()){  //���� ��� ������ ��� �ݺ�
				int num = rs.getInt(1);// rs.getInt("num")�� ����. ��ȣ�� �Է��ϸ� �� ��ȣ������ �´� �ʵ带 �����´�.
				String username = rs.getString(2);
				String tel = rs.getString(3);
				String email = rs.getString(4);
				String addr = rs.getString(5);
				String writedate = rs.getString(6); //db���� date�� �Ǿ������� �ڹٴ� String���� �д´�.
				
				System.out.printf("%4d %8s %15s %20s %20s %20s\n", num, username, tel, email, addr, writedate);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close(); //������ ������ �ݴ��
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
