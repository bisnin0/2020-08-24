
////////////////////데이터를 텍스트필드에 입력하면 여기로 보내서 private에 하나의 레코드의 데이터가 셋팅된다. 이걸 가지고 DB에 보내던지 받아오던지 하는것.
public class MemberVO {
	private int num; //번호
	private String username; //이름
	private String tel; //연락처
	private String email; //이메일
	private String addr; //주소
	private String writedate; //등록일
	
	public MemberVO() {}
	//생성자 메소드 생성. 필요에 의해 만들어 쓴다. 필수는 아니다.
	public MemberVO(int num, String username, String tel, String email, String addr) {
		this.num = num;
		this.username = username;
		this.tel = tel;
		this.email = email;
		this.addr = addr;
	}
	public MemberVO(int num, String username, String tel, String email, String addr, String writedate) {
		this(num, username, tel, email, addr);
		this.writedate = writedate;
		
		
	}
	public void print() {
		System.out.printf("%d, %s, %s, %s, %s, %s\n", num, username, tel, email, addr, writedate);
	}
	//////////////////////////////여기까지 없어도 게터세터로 사용할 수 있는데 이렇게 만드는게 편리하기때문에 만드는것. 이부분 확인하기.
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}


}
