package memberform_Me;


import java.sql.SQLException;
	
public class MemberSelect extends DBConn2 {
	Object data[][];
	int num=0; //레코드 수
	public MemberSelect() {
		try {
			getConn();
			String sql ="SELECT COUNT(*) FROM member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				num = rs.getInt(1);
			}
			data = new String[num][6];
			sql = "select num, username, tel, email, addr, to_char(writedate, 'MM-DD') writedate"
					+ " from member order by num";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int i = 0;
			while(rs.next()) {
				String num2 = String.valueOf(rs.getInt(1));
				data[i][0] = num2;
				String username = rs.getString(2);
				data[i][1] = username; 
				String tel = rs.getString(3);
				data[i][2] = tel; 
				String email = rs.getString(4);
				data[i][3] = email;
				String addr = rs.getString(5);
				data[i][4] = addr;
				String writedate = rs.getString(6);
				data[i][5] = writedate;
				i++;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			getClose();
		}	
	}
	public void start() {

	}
	public Object[][] getData() {
		return data;
	}
	public void setData(Object[][] data) {
		this.data = data;
	}
//	public static void main(String[] args) {
//		new MemberSelect();
//
//	}

}
