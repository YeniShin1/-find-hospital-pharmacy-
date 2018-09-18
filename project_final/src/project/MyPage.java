package project;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.ImageIcon;

public class MyPage extends JPanel implements ActionListener{
	private JTable table;
	private DefaultTableModel model;
	private JButton btnupdate,chYear;
	private JPasswordField txtpw;
	private JTextField txtname;
	private JTextField txtid;
	private String stryear;
	private int i;
	UserVO vo = new UserVO();
	UserDAO dao = new UserDAO();
	
	private Vector<ReservationVO> reservelist;
	
	ReservationVO vo1 = new ReservationVO();
	ReservationDAO dao1 = new ReservationDAO();
	
	
	/**
	 * Create the panel.
	 */
	public MyPage() {
		String strid = Login.getIdset();
		vo.setUser_id(strid);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_banner = new JPanel();
		panel_banner.setBackground(Color.WHITE);
		add(panel_banner);
		panel_banner.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		chYear = new JButton("");
		chYear.setIcon(new ImageIcon(MyPage.class.getResource("/project/health.jpg")));
		chYear.setPreferredSize(new Dimension(440, 180));
		chYear.addActionListener(this);
		panel_banner.add(chYear);
		
		JPanel panel_7 = new JPanel();
		add(panel_7);
		panel_7.setBackground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setBorder(new TitledBorder(null, dao.getUser(vo).getUser_name()+"님의 계정정보입니다", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBackground(Color.WHITE);
		
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		panel_4.setBackground(Color.WHITE);
		
		JPanel panel_info = new JPanel();
		panel_4.add(panel_info);
		panel_info.setBackground(Color.WHITE);
		panel_info.setLayout(new BoxLayout(panel_info, BoxLayout.Y_AXIS));
		panel_info.setLayout(new GridLayout(0, 2, 0, 3));
		
		
		JLabel lblNewLabel_id = new JLabel("아이디");
		lblNewLabel_id.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNewLabel_id.setForeground(Color.BLACK);
		lblNewLabel_id.setHorizontalAlignment(SwingConstants.CENTER);
		panel_info.add(lblNewLabel_id);
		
		txtid = new JTextField();
		txtid.setText(dao.getUser(vo).getUser_id());
		panel_info.add(txtid);
		txtid.setColumns(10);
		txtid.setEditable(false);
		
		JLabel lblNewLabel_pwd = new JLabel("비밀번호");
		lblNewLabel_pwd.setHorizontalAlignment(SwingConstants.CENTER);
		panel_info.add(lblNewLabel_pwd);
		
		txtpw = new JPasswordField();
		txtpw.setText(dao.getUser(vo).getUser_password());
		panel_info.add(txtpw);
		txtpw.setColumns(10);
		

		JLabel lblNewLabel_3 = new JLabel("이름");
		lblNewLabel_3.setBackground(Color.ORANGE);
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_info.add(lblNewLabel_3);
		
		txtname = new JTextField();
		txtname.setText(dao.getUser(vo).getUser_name());
		panel_info.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("생년월일");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_info.add(lblNewLabel_4);
		
		JPanel panel_3 = new JPanel();
		panel_info.add(panel_3);
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JComboBox birth_y = new JComboBox();
		for(int h=1950; h<=2018; h++) {
			birth_y.addItem(h+"");
		}
		Date dt = dao.getUser(vo).getUser_birth();
		DateFormat dfy = new SimpleDateFormat("yyyy");
		stryear = dfy.format(dt);
		
		int i = 0;
		for(i=0;i<67;i++) {
			if(birth_y.getItemAt(i).equals(stryear)) {
				birth_y.setSelectedIndex(i);
			}
		}

		panel_3.add(birth_y);
		
		JLabel yy = new JLabel(" 년");
		panel_3.add(yy);
		
		
		JComboBox birth_m = new JComboBox();
		for(int h=1; h<=12; h++) {
			birth_m.addItem(h+"");
		}
		DateFormat dfm = new SimpleDateFormat("MM");
		String strmonth = dfm.format(dt);
		
		int j = 0;
		for(j=0;j<12;j++) {
			if(birth_m.getItemAt(j).equals(strmonth)) {
				birth_m.setSelectedIndex(j);
			}
		}
		panel_3.add(birth_m);
		
		JLabel mm = new JLabel(" 월");
		panel_3.add(mm);
		
		
		JComboBox birth_d = new JComboBox();
		for(int h=1; h<=31; h++) {
			birth_d.addItem(h+"");
		}
		
		DateFormat dfd = new SimpleDateFormat("dd");
		String strday = dfd.format(dt);
		
		int k = 0;
		for(k=0;k<31;k++) {
			if(birth_d.getItemAt(k).equals(strday)) {
				birth_d.setSelectedIndex(k);
			}
		}
		panel_3.add(birth_d);
			
		JLabel dd2 = new JLabel(" 일");
		panel_3.add(dd2);
				
		JLabel lblNewLabel_5 = new JLabel("성별");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_info.add(lblNewLabel_5);
		
		
		
		String strgen = dao.getUser(vo).getUser_gender();
		JComboBox gender = new JComboBox();
		gender.setModel(new DefaultComboBoxModel(new String[] { "남자","여자" }));
		int m = 0;
		for(m=0;m<2;m++) {
			if(gender.getItemAt(m).equals(strgen)) {
				gender.setSelectedIndex(m);
			}
		}
		panel_info.add(gender);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel_4.add(panel);
		panel.setBackground(Color.WHITE);
		
		btnupdate = new JButton("수정");
		panel.add(btnupdate);
		btnupdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnupdate.addActionListener(this);
		
		JPanel panel_6 = new JPanel();
		add(panel_6);
		panel_6.setBackground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(null, "예약현황", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		panel_2.setPreferredSize(new Dimension(460, 100));
		
		JLabel lblNewLabel_8 = new JLabel("예약현황");
		scrollPane.setColumnHeaderView(lblNewLabel_8);
		/*table = new JTable(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"\uBC88\uD638", "\uBCD1\uC6D0\uBA85", "\uC804\uD654\uBC88\uD638", "\uC608\uC57D\uC2DC\uAC04"
			}
		));*/
		String columnNames[] = {"병원명","전화번호","예약날짜","예약시간"};
		model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model);
		table.setBackground(Color.WHITE);
		reservelist = dao1.getReservation(Login.getIdset());
		addlist();
		table.updateUI();
		
		table.setPreferredScrollableViewportSize(new Dimension(450, 48));
		scrollPane.setViewportView(table);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn==chYear) {
			System.out.println(stryear);
			int a =Integer.parseInt(stryear);
			if(a>=1990 && a<=1999) {
				JOptionPane.showConfirmDialog(this, "고혈압, b형간염 검사 대상자 검사를 받으세요",
						"건강검진 대상자", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			}else if(a>=1980) {
				JOptionPane.showConfirmDialog(this, "고지혈증, 자궁경부암 검사 대상자 검사를 받으세요",
						"건강검진 대상자", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showConfirmDialog(this, "위암 검사 대상자 검사를 받으세요",
						"건강검진 대상자", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		if(btn==btnupdate) {
			dao.updateUser(String.valueOf(txtpw.getPassword()),txtname.getText(), txtid.getText());
			JOptionPane.showConfirmDialog(this, "수정 되었습니다.", "수정확인", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void addlist() {
		for(ReservationVO rv : reservelist) {
			Vector<String> vec = new Vector<>();
			vec.addElement(rv.getReserve_hos());
			vec.addElement(rv.getReserve_pho());
			vec.addElement(rv.getReserve_date());
			vec.addElement(rv.getReserve_time());
			model.addRow(vec);
		}
		
	}
}
