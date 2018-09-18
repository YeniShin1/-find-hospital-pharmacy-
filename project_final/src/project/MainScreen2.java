package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MainScreen2 extends JFrame implements ActionListener {
/*
 * login 되면 띄워줄 메인 화면. 메인화면에서는 버튼 클릭 시 Card2가 생성된다. 
*/
	
	private JPanel contentPane;
	private JButton btnhospital,btnpharmacy,btnmypage;
	public JButton btnlogin;
	public JPanel panel_3;
	public JPanel panel_5;
	UserVO vo = new UserVO();
	UserDAO dao = new UserDAO();
	
	//처음에 동작하는 것이 아니고 로그인 시 불러와서 생성되는 것이므로 main을 없앰
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					MainScreen2 frame = new MainScreen2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public MainScreen2() {
		setTitle("Hospital Search Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(3, 0, 0, 0));
				
		JPanel panel_1 = new JPanel(); //로고, 로그인 버튼 있는 패널
		panel_3.add(panel_1);
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);
		
		panel_5 = new JPanel(); //로그인 버튼 있는 패널
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(480, 10, 200, 37);
		panel_1.add(panel_5);
		
		
		btnlogin = new JButton(); //로그인 버튼
		btnlogin.setBounds(480, 10, 200, 37);
		//System.out.println(Login.getIdset());
		String strid = Login.getIdset();
		vo.setUser_id(strid);
		String strname = dao.getUser(vo).getUser_name();
		btnlogin.setText(strname+"님 로그인");
		
		btnlogin.addActionListener(this);
		btnlogin.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 16));
		panel_5.add(btnlogin);
		
		JPanel panel_6 = new JPanel(); //로고 있는 패널
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(12, 10, 450, 154);
		panel_1.add(panel_6);
		
		JLabel lblNewLabel = new JLabel(""); //로고 이미지 삽입
		lblNewLabel.setIcon(new ImageIcon(MainScreen2.class.getResource("/project/logo.jpg")));
		panel_6.add(lblNewLabel);
		
		JPanel panel = new JPanel(); //병원찾기, 약국찾기, 마이페이지 버튼 있는 패널
		panel_3.add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(1, 5, 0, 0));
		
		//JButton btnhospital = new JButton("병원찾기"); //병원찾기버튼
		btnhospital = new JButton(new ImageIcon(MainScreen2.class.getResource("/project/hos.png")));
		btnhospital.addActionListener(this);
		btnhospital.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 20));
		btnhospital.setContentAreaFilled(false);
		panel.add(btnhospital);
		
		//JButton btnpharmacy = new JButton("약국찾기"); //약국찾기 버튼
		btnpharmacy = new JButton(new ImageIcon(MainScreen2.class.getResource("/project/phar.png")));
		btnpharmacy.addActionListener(this);
		btnpharmacy.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 20));
		btnpharmacy.setContentAreaFilled(false);
		panel.add(btnpharmacy);
		
		//JButton btnmypage = new JButton("마이페이지"); //마이페이지 버튼
		btnmypage = new JButton(new ImageIcon(MainScreen2.class.getResource("/project/mymy.png")));
		panel.add(btnmypage);
		btnmypage.addActionListener(this);
		btnmypage.setContentAreaFilled(false);
		btnmypage.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 20));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn =(JButton)e.getSource();
		
		if(btn == btnlogin) { //로그인 버튼 클릭 시 로그아웃창 뜨도록
			JOptionPane.showMessageDialog(this, "로그아웃 되었습니다.", "로그아웃", JOptionPane.CANCEL_OPTION);
			dispose();
			Login.setIdset(null);
			MainScreen ms = new MainScreen();
			ms.setVisible(true);
		}else if (btn == btnhospital) { //병원버튼 클릭 시 병원 창 뜨도록
			Card2 cd2 = new Card2();
			//버튼 눌러진 값에 대해서 메소드 세팅
			cd2.btnInfo("hosp");
			cd2.setVisible(true);
			validate();
			dispose();
		}else if(btn == btnpharmacy) { //약국버튼 클릭 시 약국 창 뜨도록
			Card2 cd2 = new Card2();
			cd2.btnInfo("phar");
			cd2.setVisible(true);
			validate();
			dispose();
		}else if(btn == btnmypage) { //마이페이지 버튼 클릭 시 마이페이지 창 뜨도록
			Card2 cd2 = new Card2();
			cd2.btnInfo("mypage");
			cd2.setVisible(true);
			validate();
			dispose();
		}
	}
}