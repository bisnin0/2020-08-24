import java.util.ArrayList;
import java.util.List;


public class MemberDAO extends DBConn {

	public MemberDAO() {
		
	}
	
	public static MemberDAO getInstance() { //이 메소드를 호출하면 객체가 돌아온다. static을 안하면 쓸수가없다. ??이거 왜이런지 확인하기
		return new MemberDAO(); 
	} //이걸 해도 되고 안해도 되는데 보통 하는듯? 객체생성을 어떻게 할건지의 차이인듯
	
	// 레코드 추가
	public int insertRecord(MemberVO vo) {
		int result = 0; //처리결과를 담는 변수
		try {
			getConn();
			String sql = "insert into member(num, username, tel, email, addr, writedate)"
					+ " values(?,?,?,?,?,sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  vo.getNum());
			pstmt.setString(2, vo.getUsername());
			pstmt.setString(3, vo.getTel());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddr());
			
			result= pstmt.executeUpdate(); //업데이트하면 1이 들어간다.
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		
		return result; //0아니면 1이 리턴될것이다.
	}
	// 레코드 수정
	public int updateRecord(MemberVO vo) { //vo의 정보를 받을거기때문에 매게변수는 vo여야한다.
		//vo의 정보를 받아서 그걸 DB로 업데이트.
		//수정되면 int로 리턴된값으로 업데이트 되었는지 아닌지 확인할 수 있기때문에 int로 리턴
		int result=0;
		try {
			getConn();
			String sql = "update member set username=?, tel=?, email=?, addr=?"
					+ " where num=?"; //조건없으면 다 고쳐진다. 주의
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUsername());
			pstmt.setString(2, vo.getTel());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getAddr());
			pstmt.setInt(5, vo.getNum());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return result;
	}
	// 레코드 삭제
	public int deleteRecord(int num) {//레코드 번호 하나만 있으면 되니까 int num
		int result = 0;
		try {
			getConn();
			String sql="delete from member where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
			
		}
		return result;
	}
	
	
	// 레코드 전체 선택  --> 반복적으로 이루어지게 만들기. Jtable 갱신? 이부분 확인하기. 내가한거에서 안됐던거? 이게 그부분이 맞는지 확실치않음.
	public List<MemberVO> getAllMember() {  //ArrayList는 인덱스를 가지고있고 add하는 순서대로 보인다.
		List<MemberVO> list= new ArrayList<MemberVO>(); //보통상위클래스인 List를 쓴다 앞에는
		try {
			getConn();
			
			String sql = "select num, username, tel, email, addr, writedate from member order by num";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(); //데이터가 변하지 않을때.. 수정할때는 update  .. rs는 DBConn에있음
			while(rs.next()) {
/*				
				MemberVO vo = new MemberVO();
				///데이터를 하나씩 넣는법. 게터세터이용.
				vo.setNum(rs.getInt(1)); //rs에있는 첫번째 필드에 있는 데이터를 정수로 읽어서 vo에있는 num에 세팅해라
				vo.setUsername(rs.getString(2));
				vo.setTel(rs.getString(3));
				vo.setEmail(rs.getString(4));
				vo.setAddr(rs.getString(5));
				vo.setWritedate(rs.getString(6));
				////////////////
*/				 
				//VO에 생성자 메소드로 만든걸이용해서 한번에 넣는법.. 순서 정확하게
				MemberVO vo= new MemberVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				//둘중 편한걸로 하면 된다. 보통 아래를 많이 씀
				
				// 반복문이라 위로 돌아가면 vo에 방금 담은게 사라진다. 이걸 저장할게 필요함 //위에 ArrayList 객체만듬
				list.add(vo); //계속해서 데이터를 담음
				//
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return list; //이렇게 리턴 하면 이 메소드에 ArrayList로 저장한 모든 멤버 정보가 들어있게된다.
	}

}

/*
List .. 순서유지 , set.. 순서랜덤, map .. 키와 벨류어 가짐

List = 

vector
ArrayList(이거 제일 많이 씀)
....등등이있음
*/