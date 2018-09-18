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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textid;
	private JButton btnMake;
	public JButton btnLogin;
	private JPasswordField pwfield;
	public String idset2;
	private String idset;
	private static String idsetter;
	private MainScreen main;

	public static void setIdset(String idset) {
		idsetter = idset;
	}
	public static String getIdset() {
		return idsetter;
	}

	/**
	 * Create the frame.
	 */
	public Login(MainScreen main) {
		//받아온 메인을 사용할 수 있도록 선언
		this.main=main;
		
		setBounds(100, 100, 270, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0,0,150,150);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/project/login.png")));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		panel_1.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);
		
		textid = new JTextField();
		panel_1.add(textid);
		textid.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("password");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);
		
		pwfield = new JPasswordField();
		panel_1.add(pwfield);
		pwfield.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setBackground(Color.white);
		
		btnMake = new JButton("회원가입");
		panel_2.add(btnMake);
		btnMake.addActionListener(this);
		
		btnLogin = new JButton("로그인");
		panel_2.add(btnLogin);
		btnLogin.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		UserVO vo = new UserVO();
		UserDAO dao = new UserDAO();
		String pw = String.valueOf(pwfield.getPassword());
		
		if(btn==btnMake) {
			MakeP mp = new MakeP();
			mp.setVisible(true);
			validate();
		}else if(btn==btnLogin) {
			if(textid.getText().isEmpty() || pw == null) {
				JOptionPane.showMessageDialog(this, "id와 pw를 기입해 주세요", "로그인 에러", JOptionPane.CANCEL_OPTION);		
			}else {
				if(dao.getlogincheck(textid.getText(), pw)==1) {
					JOptionPane.showMessageDialog(this, "로그인 되었습니다.", "로그인 성공", JOptionPane.CANCEL_OPTION);
					dispose();
					idset = textid.getText();
					vo.setUser_id(idset);
					setIdset(idset);
					
					//로그인 성공시 받아온 메인을 닫기
					main.dispose();
					

					idset2 = dao.getUser(vo).getUser_name();
					System.out.println(idset2);
					MainScreen2 ms2 = new MainScreen2();
					ms2.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(this, "id나 password를 확인해주세요", "로그인 에러", JOptionPane.CANCEL_OPTION);
				}
			}
			
		}	
	}

}