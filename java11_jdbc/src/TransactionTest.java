import java.sql.SQLException;

public class TransactionTest extends DBConn {

	public TransactionTest() {
	}
	
	//자바에서 데이터베이스 레코드 추가, 수정 삭제는 자동 commit이 실행된다.
	//자동commit을 해제하고 작업이 완료되면 commit을 실행하고
	//에러발생하면 rollback실행하는것을 transaction이라고 한다.
	
	public void start() {
		try {
			getConn();
			conn.setAutoCommit(false);//자동커밋해제 .. 이걸 안하면 순서대로 하는 중간에 에러나도 에러 전까지 했던건 commit되어있고 이후엔 안되어있다. 
			//이 뒤에서 실행하는 업데이트는 자동commit이 되지 않는다. 11111111111111111111111111111번
			
			//////////1.-----------
			String sql = "insert into member(num, username, tel, email, addr) "
					+ " values(15, 'XXXX', '010-1111-1111', 'abcd@nate.com', '서울시 송파구')";
			pstmt = conn.prepareStatement(sql);
			int cnt = pstmt.executeUpdate();//여기까지 레코드 넣기
			if(cnt>0) {
				System.out.println("첫번째 레코드 추가 성공");
			}else {
				System.out.println("첫번째 레코드 추가 실패");
			}//여기까지 결과 출력
			//////////2.-----------
			sql = "insert into member(num, username, tel, email, addr)"
					+ " values(16,'YYYY', '010-2222-2222-555555555555', 'yyyyy@nate.com', '서울시 성동구')";
			
			pstmt = conn.prepareStatement(sql);
			cnt = pstmt.executeUpdate();//이때 insert된것. ?
			if(cnt>0) {
				System.out.println("두번째 레코드 추가 성공");
			}else {
				System.out.println("두번째 레코드 추가 실패");
			}
			
			conn.commit();//commit 커밋 설정 2222222222222222222222222222222222번
			
		}catch(Exception e) {
			try {
			conn.rollback(); //에러가 나면 여기로 오기때문에 여기에 롤백을 쓴다. 33333333333333333333333333333333번
			}catch(SQLException se) {}
			e.printStackTrace(); ///////이걸 안쓰면 에러 안찍힌다. 꼭 써야함
			
		}finally {
			getClose();
		}
	}
	
	public static void main(String[] args) {
		new TransactionTest().start();
	}
}
