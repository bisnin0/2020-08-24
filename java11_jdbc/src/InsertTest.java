import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest {
	Scanner scan = new Scanner(System.in);
	
	//////////�⺻������ ���ڸ����� ���๮�� �� �� ����. ������� int a = b+c;
	//////////������ static�� �̿��������� ����� ���๮�� ǥ���� �� �ִ�.
	
	static { //�ڵ����� ����Ǹ鼭 �ڹٰ���ӽſ� jdbc����̺긦 ����Ѵ�.
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver"); //�� Ŭ������ ����°� ��θ� ���´�.
		}catch(ClassNotFoundException c) {
			System.out.println("����̺� �����߻�.."+c.getMessage()); //���߿� �����Ҷ� �̷��� ����°� ����ã�µ� ū �����̵ȴ�.
		}
	}
	
	Connection conn; //java.sql import
	PreparedStatement pstmt;  //��������. 3.�� ������
	
	public InsertTest() {
		//	jdbc ����̺� ���  ..................... ��������� �̵�
		//	����ó�� �ݵ�� �ؾ��Ѵ�. ���� ������ xǥ�� ���콺 �ø��� ����ó������ �ƴ��� ���� ����
//		try {
//		Class.forName("oracle.jdbc.driver.OracleDriver"); //�� Ŭ������ ����°� ��θ� ���´�.
//		}catch(ClassNotFoundException c) {
//			System.out.println("����̺� �����߻�.."+c.getMessage()); //���߿� �����Ҷ� �̷��� ����°� ����ã�µ� ū �����̵ȴ�.
//		}
		
		
	}
	
	
	/*
	 *	�ڹٿ��� database ����ϱ� 
	 *  1. �ڹٰ���ӽſ� jdbc����̺� ��� Class.forName(ojdbc ����̺� ���� ���);
	 *	2. �����ͺ��̽� ����, ���(���̵� ��й�ȣ sid�� ���� �Է�) ...��������� ����� start���� ����
	 *	3. �������� �ۼ��Ͽ� preparedStatement ��ü����
	 * 
	 * 
	 */
	
	
	
	public void start() {
		//// ------------- 1. �ڹٰ���ӽſ� jdbc����̺� ���.. ���⵵ ������.
		while(true) {
			//// -----------  1. �ڹٰ���ӽſ� jdbc����̺� ��� .. ���⵵ ���������� ��õ��������(�ݺ����̶�)
			try {//Ȥ�� ��ȣ�� ���ڷ� ������ ���� �߻�. ����ó��
			//�Է�
			System.out.print("��ȣ= ");
			int num = Integer.parseInt(scan.nextLine());
			System.out.print("�̸�= ");
			String username = scan.nextLine();
			System.out.print("����ó= ");
			String tel = scan.nextLine();
			System.out.print("�̸���= ");
			String email = scan.nextLine();
			System.out.print("�ּ�= ");
			String addr = scan.nextLine();
			
			//�����ͺ��̽� ����						����:port:sid
			String url = "jdbc:oracle:thin:@192.168.0.214:1521:xe";//�� ������ ����� ����.. mysql�� �ٸ���. �����ͺ��̽����� �ٸ�.. 
											//�� ��ǻ�ʹ� localhost �ٸ� ����������ϸ� ip�ּҳ� �������� ����.
			conn = DriverManager.getConnection(url, "scott", "tiger");
			
			//������ �ۼ�
			String sql = "insert into member(num, username, writedate, tel, email, addr)" //db���ִ°Ͱ� ���� �޶� �������. �ʵ� �����ϻ��̴ϱ�
					+ "values(?,?,sysdate,?,?,?)"; //������ �� �����ʹ� ?�� �ִ´�. ���߿� ������ �Է¹޾� ���ðŴϱ�.. ������ ���� ���Ͱ� ���ƾ��Ѵ�.
			
			pstmt = conn.prepareStatement(sql); //sql�������� pstmt�� ���ִٴ°�. pstmt�� �̺κп��� ��������� �������ش�.
			//?�� ���Ե� �����͸� �����Ѵ�.
			pstmt.setInt(1, num); //parameterIndex�� �����µ� �̰� ���° ? ���� �����ϸ�ȴ�. 1�� ���� ���� ù��° ?�� ������ �����Ѵ�. 
			pstmt.setString(2, username); // �ι�° ?�� ���ڷ� �����Ѵ�.
			pstmt.setString(3, tel);
			pstmt.setString(4, email);
			pstmt.setString(5, addr);
			
			//����
			int cnt = pstmt.executeUpdate(); //��� ���ڵ带 �߰��ߴ��� ���ƿ´�. // ����� int �� ���ƿ�
			if(cnt>0) {
				System.out.println("���ڵ尡 �߰� �Ǿ����ϴ�."); //gui�� �����ϱ� �ֿܼ� ����ؼ� Ȯ���غ�
			}else {
				System.out.println("���ڵ� �߰� �����Ͽ����ϴ�.");
			}
			
			///������ ���� �ȳ��� ������ db�� �ݾƾ� �Ѵ�.. ������� pstmt.setInt(1, num);�κп��� ������ ���� �� ������ ������ �ȵȴ�.
			///								 ��� ������ ����� catch�� �ٷ� ���⶧���� �̺κ��� �̿��ؼ� db�� �ݾ��ش�.
			///								������ �Ȼ��ܵ� catch���� ������ ����ȴ�. �̰� finally�� �̿��ؼ� �� �� ��´�.
			///								catch���� finally�� �̿��ؼ� ������ ���� �ȳ��� db�ݴ� �۾��� �������ش�.
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				//DB�ݱ�
/*				
				if(pstmt!=null) { //pstmt��ü�� ����������� Ȯ��.
					try {pstmt.close();}catch(SQLException s) {} //������ .. ����ó��
				}
				if(conn!=null) { //conn ��ü�� ����������� Ȯ��.
					try {conn.close();}catch(SQLException r) {} //����ó��
				}
*/ // �� �Ʒ� ���� �ϳ��� ���� �Ǵµ� �Ʒ��� �� �����ϴϱ� �Ʒ��� ����. ����ó�� ���� �Ұ��� �� �� �ѹ��� �Ұ���				
				//
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close(); //conn�� ���� ��������Ŵϱ� ���߿� �ݴ´�.
				}catch(SQLException s) {
					System.out.println("DB�ݱ� �����߻�..");
				}
			}
		}
	}

	public static void main(String[] args) {
		InsertTest it = new InsertTest();
		it.start();
	}

}
