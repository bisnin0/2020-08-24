
public class DeleteTest extends DBConn {

	public DeleteTest() {
	}
	public void deleteStart() {
		try {
			getConn();//DB열기
			
			String sql = "delete from member where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 2);//2번을 지울거다.
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				System.out.println("삭제되었습니다.");
			}else {
				System.out.println("삭제실패하였습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();//DB닫기
		}
	}
	
	public static void main(String[] args) {
		new DeleteTest().deleteStart();
	}

}
