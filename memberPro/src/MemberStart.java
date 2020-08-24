

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//���̾ƿ��� ���� ����
public class MemberStart extends JFrame implements ActionListener{
	//��
	JPanel nPane = new JPanel(new BorderLayout()); 
		//�� ��
		JPanel lblPane =  new JPanel(new GridLayout(6,1));
			JLabel numLbl = new JLabel("��ȣ");
			JLabel nameLbl = new JLabel("�̸�");
			JLabel telLbl = new JLabel("����ó");
			JLabel emailLbl = new JLabel("�̸���");
			JLabel addrLbl = new JLabel("�ּ�");
			JLabel writedateLbl = new JLabel("�����"); // ������� �ȳ־ �Ǵ°��ε� �׳� �غ�
		//�� �Է� ������Ʈ
		JPanel tfPane = new JPanel(new GridLayout(6,1)); //�̰� ���ʿ� �ٰ� �Ϸ��� �г��� ���� ���� �ְų� ���̾ƿ��� ������ ��ǥ�� �����ϸ�ȴ�. �Ʒ��� �г� ���� ����
			JTextField numTf = new JTextField(5);			JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JTextField nameTf = new JTextField(10);			JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JTextField telTf = new JTextField(15);			JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JTextField emailTf = new JTextField(30);		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JTextField addrTf = new JTextField(40);			JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JTextField writedateTf = new JTextField(20);	JPanel p6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
		//�޴�, ȸ������Ʈ
		JPanel cPane = new JPanel(new BorderLayout());
			//�޴�
			JPanel menuPane = new JPanel();
				JButton addBtn = new JButton("�߰�");
				JButton editBtn = new JButton("����");
				JButton delBtn = new JButton("����");
				JButton clearBtn = new JButton("�����");
				JButton endBtn = new JButton("����");
			//ȸ�����
			String title[] = {"��ȣ", "�̸�", "����ó", "�̸���", "�ּ�", "�����"};
			DefaultTableModel model = new DefaultTableModel(title,0); //�̷��� ���� �����Ҷ� ���� �ϳ��� ���� ���۵ȴ�.
			JTable table = new JTable(model);
			JScrollPane sp = new JScrollPane(table);
			
			
	public MemberStart() {
		super("ȸ�������ý���");
		// �� �����
		nPane.add(BorderLayout.WEST, lblPane);
			lblPane.add(numLbl); lblPane.add(nameLbl); lblPane.add(telLbl); lblPane.add(emailLbl); lblPane.add(addrLbl); lblPane.add(writedateLbl);
			
		nPane.add(BorderLayout.CENTER, tfPane);
			
			p1.add(numTf); 			tfPane.add(p1);//�������� �ٰ� ����� //�гο��ٰ� �ؽ�Ʈ�ʵ� ��� .. �Ѳ����� �ϴϱ� �ȵǳ� tfPane.add(p1.add(numTf));�ϴϱ� �ȵ�.
			p2.add(nameTf); 		tfPane.add(p2);
			p3.add(telTf); 			tfPane.add(p3);
			p4.add(emailTf); 		tfPane.add(p4);
			p5.add(addrTf); 		tfPane.add(p5);
			p6.add(writedateTf);	tfPane.add(p6);
		
		
		add(BorderLayout.NORTH, nPane);
		
		//�޴�, ȸ������Ʈ
		menuPane.add(addBtn); menuPane.add(editBtn); menuPane.add(delBtn); menuPane.add(clearBtn); menuPane.add(endBtn);
		cPane.add(menuPane, BorderLayout.NORTH);
		cPane.add(sp);
		
		add(cPane);
		
		setSize(600,700);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//��ü ���ڵ� �߰�
		getAllRecord();
		
		//JTable �̺�Ʈ ����ϱ�
		table.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent me) {
				if(me.getButton()== 1) { //���콺�� ���� ��ư Ŭ���� ����
					int row = table.getSelectedRow(); //���缱�õ� ���� index
					numTf.setText(String.valueOf(model.getValueAt(row, 0))); //���� int�ϱ� integer�� �ٲٰ� string���� �ٲ�����Ѵ�.?(Integer) model�տ� �̰� ���� �Ǵµ� Ȯ��
					nameTf.setText((String)model.getValueAt(row, 1));
					telTf.setText((String)model.getValueAt(row, 2));
					emailTf.setText((String)model.getValueAt(row, 3));
					addrTf.setText((String)model.getValueAt(row, 4));
					writedateTf.setText((String)model.getValueAt(row, 5));
				}
			}
		});
		//�޴� �̺�Ʈ���
		addBtn.addActionListener(this);
		editBtn.addActionListener(this);
		delBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		endBtn.addActionListener(this);
	}

	//���ڵ� ��ü �����Ͽ� JTable�� �߰��ϱ�.(�̰ɷ� JTable ����)
	public void getAllRecord() { //�̰Ÿ� �����ϸ� ���̺� �ٽ� ����
		//JTable�� �ִ� ���ڵ� �����... //���� ���ִ°� ����� �ٽý���� �ȱ׷��� ���ִ°�+���ξ��� ������ ���´�.
		model.setRowCount(0); //���� ���� 0���� �ϸ� �� ��������.
		
		//DAO��ü����
		// MemberDAO dao = new MemberDAO(); �Ʒ��� �̰Ŷ� ���� �ϳ� ���� �Ǵµ� �Ʒ��� ��ȣ�ϴµ�?
		MemberDAO dao = MemberDAO.getInstance(); //�̰� ���ؼ� DAO�� �޼ҵ� ����
		
		List<MemberVO> list= dao.getAllMember(); //������ ���ƿͼ� list�� ������ִ�.
		
		for(int i=0; i<list.size(); i++) {
			MemberVO vo = list.get(i); // i��°�� �ִ� VO�� ������� vo�� �����
			Object[] record = {vo.getNum(), vo.getUsername(), vo.getTel(), vo.getEmail(), vo.getAddr(), vo.getWritedate()};
			model.addRow(record);
		}
	}
	
	//���� ���ڿ� �����
	public void formClear() {
		numTf.setText("");
		nameTf.setText("");
		telTf.setText("");
		emailTf.setText("");
		addrTf.setText("");
		writedateTf.setText("");
	}
	
	//���� ���ڵ带 �����ͺ��̽��� �߰��ϱ� .. VO�� �����͸� �߰���. ���� DB�� ���� ����
	public void memberInsert() {
		MemberVO vo = new MemberVO();
		vo.setNum(Integer.parseInt(numTf.getText()));
		vo.setUsername(nameTf.getText());
		vo.setTel(telTf.getText());
		vo.setEmail(emailTf.getText());
		vo.setAddr(addrTf.getText());
		
		//������ �Է� ���� Ȯ��
		if(vo.getNum()==0) { //int�� �����͸� �ȳ����� �ʱⰪ�� 0�̴�
			JOptionPane.showMessageDialog(this, "��ȣ�� �Է��ϼ���..");
		}else if(vo.getUsername()==null || vo.getUsername().equals("")) { //null�� ������ �𸣴� �ڷ��� �׻� null���� Ȯ���� �ؾ��Ѵ�. �׷��� ������ ������(���� null�ε� Ȯ���Ϸ����ϴϱ�)
			JOptionPane.showMessageDialog(this, "�̸��� �Է��ϼ���..");
		}else if(vo.getTel()==null || vo.getTel().equals("")) {
			JOptionPane.showMessageDialog(this, "����ó�� �Է��ϼ���...");
		}else {
			MemberDAO dao = new MemberDAO();
			int result = dao.insertRecord(vo);
			if(result>0) { //���ڵ尡 �߰��Ǿ����� //2�̻��� �ȳ��´�.
				JOptionPane.showMessageDialog(this, "ȸ���� ��ϵǾ����ϴ�.");
				getAllRecord(); //���̺� ����
				formClear(); //���̺� ���� �� �ؽ�Ʈ�ʵ� ����
			}else { //�߰����н�
				JOptionPane.showMessageDialog(this, "ȸ������� �����Ͽ����ϴ�.");
			}
		}
		
	}
	//ȸ������ ����
	public void memberEdit() {
		MemberVO vo = new MemberVO(); //�̰� ��ü�� ����� ���������� vo�� ��°�.
		vo.setNum(Integer.parseInt(numTf.getText()));
		vo.setUsername(nameTf.getText());
		vo.setTel(telTf.getText());
		vo.setEmail(emailTf.getText());
		vo.setAddr(addrTf.getText());
		/////////////////������� vo�� ������Ʈ�� �����͸� ��°�
		if(vo.getNum()<=0) {
			JOptionPane.showMessageDialog(this, "��ȣ�� �ݵ�� �־�� �մϴ�."); //���⼭ this�� ������
		}else if(vo.getUsername()==null || vo.getUsername().equals("")) {
			JOptionPane.showMessageDialog(this, "�̸��� �ݵ�� �־�� �մϴ�.");
		}else if(vo.getTel()==null || vo.getUsername().equals("")) {
			JOptionPane.showMessageDialog(this, "����ó�� �ݵ�� �־�� �մϴ�.");
		}else {
			MemberDAO dao = MemberDAO.getInstance();
			int result = dao.updateRecord(vo);
			if(result>0) {
				JOptionPane.showMessageDialog(this, "ȸ�������� �����Ǿ����ϴ�.");
				getAllRecord(); //���̺� ����
				formClear(); //���̺� ���� �� �ؽ�Ʈ�ʵ� ����
			}else {
				JOptionPane.showMessageDialog(this, "ȸ�������� �������� �ʾҽ��ϴ�.");
			}
		}
	}
	//ȸ������
	public void memberDelete() {
		//YES NO ����â ����
		int okNo = JOptionPane.showConfirmDialog(this, "ȸ�������Ͻðڽ��ϱ�?", "ȸ������Ȯ��", JOptionPane.YES_NO_OPTION); //this�� �θ������̳�.. ���⼭�� fram
																							//yes ������ 0�� ���ƿ��� no ������ 1�� ���ƿ´�.
		if(okNo==JOptionPane.OK_OPTION) { //�̷��� �ϸ� 1���� 0���� ������� Ȯ�ΰ���
			MemberDAO dao = MemberDAO.getInstance();
			int result = dao.deleteRecord(Integer.parseInt(numTf.getText()));
			if(result>0) {
				JOptionPane.showMessageDialog(this, "ȸ���� �����Ǿ����ϴ�.");
				getAllRecord();
				formClear();
			}else {
				JOptionPane.showMessageDialog(this, "ȸ������ �����Ͽ����ϴ�.");
			}
		}		
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object event = ae.getSource();
		if(event == clearBtn) { //���� ���ڿ� �����
			formClear();
		}else if(event == addBtn) { //���� �����ڵ带 �����ͺ��̽��� �߰��ϱ�
			memberInsert(); //�����ͺ��̽� �߰��ϱ�. 
		}else if(event == editBtn) {
			memberEdit();
		}else if(event == delBtn) {
			memberDelete();
		}else if(event == endBtn) {
			dispose();
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new MemberStart();
	}

}
