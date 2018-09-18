package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class ReservationDate extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JComboBox combodate;
	private JComboBox combotime;
	private JButton btn1, btn2;
	int year, month, day;
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat fm;
	String strDate; 
	public static String strnum, strname;
	
	ReservationVO vo = new ReservationVO();
	ReservationDAO dao = new ReservationDAO();
	

	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationDate frame = new ReservationDate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the frame.
	public ReservationDate() {
		setTitle("예약");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 337, 182);
		setBackground(Color.WHITE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btn1 = new JButton("예약하기");
		panel_1.add(btn1);
		btn1.addActionListener(this);
		
		btn2 = new JButton("취소");
		panel_1.add(btn2);
		btn2.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		//combodate '오늘날짜+1일 ~ 오늘날짜+7일'까지 나오도록
		//토요일, 일요일 고려하지 않음 -> 나중에 시간남으면 해보기
		combodate = new JComboBox();
		String[] date = new String[8];
		date[0] = "---날짜선택---";
		for(int i=1;i<date.length;i++) {
			cal.add(Calendar.DAY_OF_YEAR, 1);
			fm = new SimpleDateFormat("yyyy-MM-dd");
			strDate = fm.format(cal.getTime());
			date[i]=strDate;
		}
		for(int j=0;j<date.length;j++) {
			combodate.addItem(date[j]);
		}
		panel.add(combodate);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2);
		combotime = new JComboBox();
		panel.add(combotime);
		String[] time = {"---시간선택---","09:00 ~ 10:00", "10:00 ~ 11:00", "11:00 ~ 12:00",
						"13:30 ~ 14:00", "14:00 ~ 15:00", "15:00 ~ 16:00",
							"16:00 ~ 17:00","17:00 ~ 18:00"};
		for(int j=0;j<time.length;j++) {
			combotime.addItem(time[j]);
		}
		
		
		System.out.println(strname);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
		if(btn==btn1) {
			String strid = Login.getIdset();
			vo.setUser_id(strid);
			
			int i = combodate.getSelectedIndex();
			String strdate = combodate.getItemAt(i).toString();
			System.out.println(strdate);
			vo.setReserve_date(strdate);
			
			int j = combotime.getSelectedIndex();
			String strtime = combotime.getItemAt(j).toString();
			System.out.println(strtime);
			vo.setReserve_time(strtime);
			
			System.out.println(strname+strnum);
			vo.setReserve_hos(strname);
			vo.setReserve_pho(strnum);
			dao.insertReservation(vo);
	
			
			
			JOptionPane.showConfirmDialog(this, "예약 되었습니다.", "예약확인", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}else if(btn==btn2) {
			dispose();
		}
	}
}