package memberform_Me;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class MemberForm extends JFrame implements ActionListener {
	String t1="", t2="", t3="", t4="", t5="";
	int click=0;
	Font font = new Font("굴림체", Font.BOLD, 15);
	JSplitPane jp = new JSplitPane();
	JPanel top = new JPanel(new BorderLayout()); //위쪽.. 보더로 전체
		JPanel left = new JPanel(new GridLayout(5, 1, 0, 15)); //왼쪽 이름 번호 전화번호 이메일 주소 5등분
			JLabel lbl1 = new JLabel("번  호", JLabel.RIGHT);
			JLabel lbl2 = new JLabel("이  름", JLabel.RIGHT);
			JLabel lbl3 = new JLabel("전화번호", JLabel.RIGHT);
			JLabel lbl4 = new JLabel("이메일", JLabel.RIGHT);
			JLabel lbl5 = new JLabel("주  소", JLabel.RIGHT);
			
		JPanel center = new JPanel(new GridLayout(5, 1, 0, 15)); //센터. 텍스트박스? 4개 그리드로
			JPanel j1 = new JPanel(new BorderLayout());
			JPanel j2 = new JPanel(new BorderLayout());
			JPanel j3 = new JPanel(new BorderLayout());
			JPanel j4 = new JPanel(new BorderLayout());
			JPanel j5 = new JPanel(new BorderLayout());
			JTextField jt1 = new JTextField(t1); //번호
			JTextField jt2 = new JTextField(t2); //이름
			JTextField jt3 = new JTextField(t3); //전화번호
			JTextField jt4 = new JTextField(t4); //이메일
			JTextField jt5 = new JTextField(t5); //주소
			
		JPanel bottom = new JPanel(new FlowLayout()); //SOUTH. 버튼 5개 추가. 추가, 수정, 삭제, 지우기, 종료
			JButton btn1 = new JButton("추가"); //추가
			JButton btn2 = new JButton("수정"); //수정
			JButton btn3 = new JButton("삭제"); //레코드삭제
			JButton btn4 = new JButton("지우기"); // 텍스트 지우기
			JButton btn5 = new JButton("종료"); //창 종료시키기
		JTable table;
		JScrollPane sp;
	MemberTable2 table2 = new MemberTable2();
	public MemberForm() {
		table2.start();
		jt1.setPreferredSize(new Dimension(100, 23));
		jt2.setPreferredSize(new Dimension(250, 23));
		jt3.setPreferredSize(new Dimension(250, 23));
		jt4.setPreferredSize(new Dimension(350, 23));
		jt5.setPreferredSize(new Dimension(450, 23));

		lbl1.setFont(font);lbl2.setFont(font);lbl3.setFont(font);lbl4.setFont(font);lbl5.setFont(font);
		left.add(lbl1);	left.add(lbl2); left.add(lbl3); left.add(lbl4); left.add(lbl5);
		j1.add("West",jt1);j2.add("West",jt2);j3.add("West",jt3);j4.add("West",jt4);j5.add("West",jt5);
		center.add(j1); center.add(j2);center.add(j3); center.add(j4); center.add(j5);
		bottom.add(btn1); bottom.add(btn2); bottom.add(btn3); bottom.add(btn4); bottom.add(btn5);
		top.add(BorderLayout.WEST, left); top.add(BorderLayout.CENTER, center); top.add(BorderLayout.SOUTH, bottom);
		jp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, top, table);
		jp.setDividerLocation(230);
		add(jp);

		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		
		jt1.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent me) {
				click=1;
			}
		});
		jt2.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent me) {
				click=2;
			}
		});
		jt3.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent me) {
				click=3;
			}
		});
		jt4.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent me) {
				click=4;
			}
		});
		jt5.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent me) {
				click=5;
			}
		});

	}
	
	public void actionPerformed(ActionEvent ae) {
		String event = ae.getActionCommand();
		if(event.equals("추가")) {
			MemberInsert mi = new MemberInsert();
			mi.start();
			dispose();
			new MemberForm();
		}else if(event.equals("수정")) {
			MemberEdit me = new MemberEdit();
			me.start();
			dispose();
			new MemberForm();
		}else if(event.equals("삭제")) {
			MemberDelete md = new MemberDelete();
			md.start();
			dispose();
			new MemberForm();
		}else if(event.equals("지우기")) {
			if(click==1) {
				jt1.setText("");
			}else if(click==2) {
				jt2.setText("");
			}else if(click==3) {
				jt3.setText("");
			}else if(click==4) {
				jt4.setText("");
			}else if(click==5) {
				jt5.setText("");
			}
			
			click=0;
		}else if(event.equals("종료")) {
            dispose();
            System.exit(0); 
		}
	}
	
	
	public class MemberTable2 extends Panel {

		MemberSelect db = new MemberSelect();
		int a = db.num;
		Object data[][] = new Object[a][6];
		String title[] = {"번호", "이름", "전화번호", "EMAIL", "주소", "등록일"};
		Object Inserttext[] = new String[6];
		
		String t1="", t2="", t3="", t4="", t5="";
		public Object[][] getData() {
			return data;
		}

		public Object[] getInserttext() {
			return Inserttext;
		}

		
		DefaultTableModel model;
		
		public MemberTable2() {
			
		}
		
		public void start() {
			Object[][] dbData = db.getData();
			for(int q=0; q<dbData.length; q++ ) {
				for(int w=0; w<dbData[q].length; w++) {
					data[q][w] = dbData[q][w];
				}
			}
			setLayout(new BorderLayout());
			model = new DefaultTableModel(data, title); 
			
			table = new JTable(model);
			sp = new JScrollPane(table);
			
			add(sp);
			
			table.addMouseListener(new MouseAdapter(){
				public void mouseReleased(MouseEvent me) {
					int btn = me.getButton();
					if(btn==1) {
						int rowIndex = table.getSelectedRow();
						t1 = (String) table.getValueAt(rowIndex, 0);
						jt1.setText(t1);
						t2 = (String) table.getValueAt(rowIndex, 1);
						jt2.setText(t2);
						t3 = (String) table.getValueAt(rowIndex, 2);
						jt3.setText(t3);
						t4 = (String) table.getValueAt(rowIndex, 3);
						jt4.setText(t4);
						t5 = (String) table.getValueAt(rowIndex, 4);
						jt5.setText(t5);
					}

				}
				
			});
		}
	}
	public class MemberInsert extends DBConn2{
		
		public MemberInsert() {

		}
		
		public void start() {
				try {	
				getConn();
				int miNum = Integer.parseInt(jt1.getText());
				String miUsername = jt2.getText();
				String miTel = jt3.getText();
				String miEmail = jt4.getText();
				String miAddr = jt5.getText();
				conn = DriverManager.getConnection(url, "scott", "tiger");
				String sql = "insert into member(num, username, tel, email, addr, writedate)"
						+ " values(?,?,?,?,?,sysdate)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, miNum);
				pstmt.setString(2, miUsername);
				pstmt.setString(3, miTel);
				pstmt.setString(4, miEmail);
				pstmt.setString(5, miAddr);
				pstmt.executeUpdate();
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if(pstmt!=null) pstmt.close();
						if(conn!=null) conn.close();
					}catch(SQLException s) {
						System.out.println("MemberInsert - DB닫기 에러발생");
						getClose();
					}
				}
		}
	}
	
	public class MemberEdit extends DBConn2{

		public MemberEdit() {
			
		}
		public void start() {
			try {
				getConn();
				String sql = "update member set username=?, tel=?, email=?, addr=? where num=?";
				pstmt = conn.prepareStatement(sql);
				String meUsername = jt2.getText();
				String meTel = jt3.getText();
				String meEmail = jt4.getText();
				String meAddr = jt5.getText();
				
				pstmt.setString(1, meUsername);
				pstmt.setString(2, meTel);
				pstmt.setString(3, meEmail);
				pstmt.setString(4, meAddr);
				int mbNum = Integer.parseInt(jt1.getText());
				pstmt.setInt(5, mbNum);
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getClose();
			}
		}

	}
	
	public class MemberDelete extends DBConn2{
		
		public MemberDelete(){}
		
		public void start() {
			try {
				getConn();
				String sql = "delete from member where num=?";
				pstmt = conn.prepareStatement(sql);
				int mdNum = Integer.parseInt(jt1.getText());
				pstmt.setInt(1, mdNum);
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getClose();
			}
		}
	}
	
	public static void main(String[] args) {
		new MemberForm();
	}

}
