
public class DeleteTest extends DBConn {

	public DeleteTest() {
	}
	public void deleteStart() {
		try {
			getConn();//DB����
			
			String sql = "delete from member where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 2);//2���� ����Ŵ�.
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				System.out.println("�����Ǿ����ϴ�.");
			}else {
				System.out.println("���������Ͽ����ϴ�.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();//DB�ݱ�
		}
	}
	
	public static void main(String[] args) {
		new DeleteTest().deleteStart();
	}

}
