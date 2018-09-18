package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class MakeP extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtname;
	private JComboBox comboyear, combomonth, comboday, combogender;
	private JButton btnMake, btnCancel, btncheck;
	private String user_id, user_name,user_password,user_gender;
	private String user_birth;
	private JPasswordField passwordField, passwordField2;

	/**
	 * Create the frame.
	 */
	public MakeP() {
		setTitle("회원가입");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MakeP.class.getResource("/project/join.png")));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(6, 1, 0, 0));
		panel_1.setBackground(Color.WHITE);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(null);
		panel_6.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(12, 8, 11, 15);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_6.add(lblNewLabel_1);
		
		txtid = new JTextField();
		txtid.setBounds(53, 5, 116, 21);
		panel_6.add(txtid);
		txtid.setColumns(10);
		
		btncheck = new JButton("중복확인");
		btncheck.setBounds(186, 4, 100, 23);
		panel_6.add(btncheck);
		btncheck.addActionListener(this);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(null);
		panel_7.setBackground(Color.WHITE);
		
		JLabel lblPassword = new JLabel("비밀번호");
		lblPassword.setBounds(12, 8, 67, 15);
		panel_7.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(87, 5, 116, 21);
		panel_7.add(passwordField);
		passwordField.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_1.add(panel_10);
		panel_10.setBackground(Color.WHITE);
		
		JLabel label_2 = new JLabel("비밀번호 확인");
		label_2.setBounds(12, 8, 97, 15);
		panel_10.add(label_2);
		
		passwordField2 = new JPasswordField();
		passwordField2.setColumns(10);
		passwordField2.setBounds(110, 5, 116, 21);
		panel_10.add(passwordField2);
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8);
		panel_8.setLayout(null);
		panel_8.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("이름");
		label.setBounds(12, 8, 45, 15);
		panel_8.add(label);
		
		txtname = new JTextField();
		txtname.setBounds(69, 5, 116, 21);
		panel_8.add(txtname);
		txtname.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		panel_5.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_2 = new JLabel("생년월일");
		lblNewLabel_2.setBounds(12, 10, 63, 15);
		panel_5.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(87, 0, 196, 31);
		panel_5.add(panel_2);
		panel_2.setBackground(Color.WHITE);
		
		comboyear = new JComboBox();
		panel_2.add(comboyear);
		String[] stryear = new String[69];
		for(int i=0;i<stryear.length;i++) {
			stryear[i]=(1950+i)+"";
		}
		for(int j=0;j<stryear.length;j++) {
			comboyear.addItem(stryear[j]);
		}

		
		JLabel lblNewLabel_3 = new JLabel("년");
		panel_2.add(lblNewLabel_3);
		
		combomonth = new JComboBox();
		panel_2.add(combomonth);
		String[] strmonth = new String[12];
		for(int i=0;i<strmonth.length;i++) {
			strmonth[i]=(1+i)+"";
		}
		for(int j=0;j<strmonth.length;j++) {
			combomonth.addItem(strmonth[j]);
		}

		
		
		JLabel lblNewLabel_4 = new JLabel("월");
		panel_2.add(lblNewLabel_4);
		
		comboday = new JComboBox();
		panel_2.add(comboday);
		String[] strday = new String[32];
		for(int i=0;i<strday.length;i++) {
			strday[i]=(1+i)+"";
		}
		for(int j=0;j<strday.length;j++) {
			comboday.addItem(strday[j]);
		}
		
		
		JLabel lblNewLabel_5 = new JLabel("일");
		panel_2.add(lblNewLabel_5);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_6 = new JLabel("성별");
		lblNewLabel_6.setBounds(12, 8, 44, 15);
		panel_4.add(lblNewLabel_6);
		
		combogender = new JComboBox();
		combogender.setBounds(68, 5, 70, 21);
		combogender.setModel(new DefaultComboBoxModel(new String[] { "남자","여자" }));
		panel_4.add(combogender);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);
		panel_3.setBackground(Color.WHITE);
		
		btnMake = new JButton("가입");
		panel_3.add(btnMake);
		btnMake.addActionListener(this);
		
		btnCancel = new JButton("취소");
		panel_3.add(btnCancel);
		btnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		String pw1 = String.valueOf(passwordField.getPassword());
		String pw2 = String.valueOf(passwordField2.getPassword());

		if(btn==btncheck) {
			if(!txtid.getText().isEmpty()) {
				if(dao.getidcheck(txtid.getText())==0) {
					JOptionPane.showMessageDialog(this, "해당 ID사용할수 있습니다", "가입에러", JOptionPane.CANCEL_OPTION);
					btncheck.setEnabled(false);
					//System.out.println("사용할수있음");
				}else {
					JOptionPane.showMessageDialog(this, "해당ID는 사용중입니다.", "가입에러", JOptionPane.CANCEL_OPTION);
				}
			}else {
				JOptionPane.showMessageDialog(this, "id를 입력해주세요", "id에러", JOptionPane.CANCEL_OPTION);
			}
		}
		if(btn==btnMake) {
			if(txtid.getText().isEmpty() || pw1==null || pw2==null || txtname.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "빈 항목이 있습니다", "가입에러", JOptionPane.CANCEL_OPTION);
			} else if(btncheck.isEnabled()){
				JOptionPane.showMessageDialog(this, "id중복확인 해주세요", "가입에러", JOptionPane.CANCEL_OPTION);
			} else if(!pw1.equals(pw2)){
				JOptionPane.showMessageDialog(this, "비밀번호를 다시 확인해주세요", "가입에러", JOptionPane.CANCEL_OPTION);
			}else{
				user_id = txtid.getText();
				user_password = pw1;
				user_name = txtname.getText();
				user_birth = comboyear.getSelectedItem()+"-"+combomonth.getSelectedItem()+"-"+comboday.getSelectedItem();
				user_gender = combogender.getSelectedItem().toString();
				
				Date date = Date.valueOf(user_birth);
				
				vo.setUser_id(user_id);
				vo.setUser_password(user_password);
				vo.setUser_name(user_name);
				vo.setUser_birth(date);
				vo.setUser_gender(user_gender);
				
				dao.registUser(vo);
				
				JOptionPane.showConfirmDialog(this, "가입 되었습니다.", "가입축하합니다.", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		}else if(btn==btnCancel) {
			dispose();
		}
	}
}
