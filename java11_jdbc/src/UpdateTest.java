
public class UpdateTest extends DBConn { // DBConn으로 만든 클래스 상속
	SelectTest st = new SelectTest();
	
	//////////////////등록한 정보를 수정하기
	public UpdateTest() {
		start();
	}
	public void start() {
		
		try {
			getConn(); //DB연결.. DBConn에 들어가있음
			
			String sql = "update member set tel=?, email=?, addr=? where num=?";
			//조건 필수 where.. 보통 Primary key로 지정한  ID로 만들거나 회언번호를 이용한다.이름은 좀 위험.
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "010-9999-9999");
			pstmt.setString(2, "akaka@nate.com");
			pstmt.setString(3, "서울시 강서구");
			pstmt.setInt(4, 1); //1번을 고칠거라고 설정해놈
			
			//실행
			int cnt = pstmt.executeUpdate(); //업데이트. 
			if(cnt>0) {
				System.out.println("수정되었습니다.");
			}else {
				System.out.println("수정 실패하였습니다.");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose(); //DB닫기
		}
		st.start(); //레코드를 콘솔에 찍어준다.SelectTest에 들어있음
	}
	public static void main(String[] args) {
		new UpdateTest();
	}
}
