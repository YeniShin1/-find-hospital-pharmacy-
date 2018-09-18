package project;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Card2 extends JFrame implements ActionListener {
/*
 * 로그인 했을 때의 Card프레임 클래스. 마이페이지 접근 가능
*/
	private JPanel contentPane;
	private JPanel panelchange[];
	private JButton btnlogo, btnhospital, btnphar, btnmypage;
	private JPanel subpanel;
	private JPanel mainpanel;

	/**
	 * Create the frame.
	 */
	public Card2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel basepanel = new JPanel();
		basepanel.setBackground(Color.WHITE);
		contentPane.add(basepanel, BorderLayout.CENTER);
		basepanel.setLayout(null);
		
		JPanel buttonpanel = new JPanel();
		buttonpanel.setBackground(Color.WHITE);
		basepanel.add(buttonpanel, BorderLayout.NORTH);
		buttonpanel.setBounds(0, 0, 700, 110);
		buttonpanel.setLayout(null);
		
		btnlogo = new JButton("");
		btnlogo.setIcon(new ImageIcon(Card.class.getResource("/project/btn111.jpg")));
		btnlogo.setBounds(0,0,165,90);
		btnlogo.addActionListener(this);
		buttonpanel.add(btnlogo);
		
		
		btnhospital = new JButton("");
		btnhospital.setIcon(new ImageIcon(Card.class.getResource("/project/btn222.jpg")));
		btnhospital.setBounds(170,0,165,90);
		btnhospital.addActionListener(this);
		buttonpanel.add(btnhospital);
		
		btnphar = new JButton("");
		btnphar.setIcon(new ImageIcon(Card.class.getResource("/project/btn333.jpg")));
		btnphar.setBounds(340,0,165,90);
		btnphar.addActionListener(this);
		buttonpanel.add(btnphar);
		
		btnmypage = new JButton("");
		btnmypage.setIcon(new ImageIcon(Card.class.getResource("/project/btn4444.jpg")));
		btnmypage.setBounds(510,0,165,90);
		btnmypage.addActionListener(this);
		buttonpanel.add(btnmypage);
		
		mainpanel = new JPanel();
		mainpanel.setBackground(Color.WHITE);
		basepanel.add(mainpanel, BorderLayout.CENTER);
		mainpanel.setBounds(0, 110, 700, 630);
		mainpanel.setLayout(new BorderLayout(0, 0));
		
		subpanel = new JPanel();
		mainpanel.add(subpanel);
		subpanel.setLayout(new CardLayout(0, 0));
		subpanel.setBackground(Color.WHITE);
		
		//패널 생성하는 메소드 호출
		getPanel(4);
		
		//CardLayout에 생성된패널 붙이기
		int i=1;
		for(JPanel p : panelchange) {
			subpanel.add(String.valueOf(i),p);
			i++;
		}

		Hospit2 pt = new Hospit2();
		panelchange[1].add(pt);

		Pharmacy phar = new Pharmacy();
		panelchange[2].add(phar);
		
		MyPage mypage = new MyPage();
		panelchange[3].add(mypage);
	}

	public JPanel getSubpanel() {
		return subpanel;
	}

	public void setSubpanel(JPanel subpanel) {
		this.subpanel = subpanel;
	}

	private JPanel[] getPanel(int cnt) {
		panelchange = new JPanel[cnt];
		
		for(int i=0;i<panelchange.length;i++) {
			panelchange[i]=new JPanel();
			panelchange[i].setBackground(Color.WHITE);
		}
		return panelchange;
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//btn 클릭 시 각각의 패널 전환
		JButton btn = (JButton) e.getSource();
		JPanel p = (JPanel) getSubpanel();
		CardLayout card = (CardLayout)p.getLayout();
		
		if(btn==btnlogo) {
			MainScreen2 ms2 = new MainScreen2();
			ms2.setVisible(true);
			dispose();
		}else if(btn==btnhospital) {
			card.show(p, "2");
		}else if(btn==btnphar) {
			card.show(p, "3");
		}else if(btn==btnmypage) {
			card.show(p, "4");
		}
	}
	
	//어느 버튼이 눌러졌는지 확인하는 메소드
	public void btnInfo(String str) {
		if(str.equals("hosp")) {
			btnhospital.doClick();
		}else if(str.equals("phar")) {
			btnphar.doClick();
		}else if(str.equals("mypage")) {
			btnmypage.doClick();
		}
	}
}
