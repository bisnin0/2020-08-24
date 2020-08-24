
public class UpdateTest extends DBConn { // DBConn���� ���� Ŭ���� ���
	SelectTest st = new SelectTest();
	
	//////////////////����� ������ �����ϱ�
	public UpdateTest() {
		start();
	}
	public void start() {
		
		try {
			getConn(); //DB����.. DBConn�� ������
			
			String sql = "update member set tel=?, email=?, addr=? where num=?";
			//���� �ʼ� where.. ���� Primary key�� ������  ID�� ����ų� ȸ���ȣ�� �̿��Ѵ�.�̸��� �� ����.
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "010-9999-9999");
			pstmt.setString(2, "akaka@nate.com");
			pstmt.setString(3, "����� ������");
			pstmt.setInt(4, 1); //1���� ��ĥ�Ŷ�� �����س�
			
			//����
			int cnt = pstmt.executeUpdate(); //������Ʈ. 
			if(cnt>0) {
				System.out.println("�����Ǿ����ϴ�.");
			}else {
				System.out.println("���� �����Ͽ����ϴ�.");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose(); //DB�ݱ�
		}
		st.start(); //���ڵ带 �ֿܼ� ����ش�.SelectTest�� �������
	}
	public static void main(String[] args) {
		new UpdateTest();
	}
}
