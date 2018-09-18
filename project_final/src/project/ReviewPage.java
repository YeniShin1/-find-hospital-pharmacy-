package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Font;

public class ReviewPage extends JFrame implements ActionListener{

   private JPanel contentPane;
   private JTextField txtRev;
   private JButton btnReserve,btnOk,btnRev;
   private JLabel lbHosname, lbAddr,lbPho, lbTime, lbWeekTime;
   public static String idx;
   private Vector<CommentVO> vec;
   private Vector veclist;
   public static String strnum;
   private JList list;
   private JScrollPane scrollPane;
   
   CommentDAO dao1 = new CommentDAO();
   CommentVO vo1 = new CommentVO();
   private JPanel panel_5;
   private JPanel panel_6;
   private JPanel panel_7;
   private JPanel panel_8;
   private JPanel panel_9;
   /**
    * Launch the application.
    */

   public ReviewPage() {
      setTitle("상세보기");
      
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 570, 560);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      setBackground(Color.WHITE);
      
      JPanel panel = new JPanel();
      contentPane.add(panel, BorderLayout.SOUTH);
      panel.setBackground(Color.WHITE);
      
      btnReserve = new JButton("예약");
      panel.add(btnReserve);
      btnReserve.addActionListener(this);
      
      btnOk = new JButton("확인");
      panel.add(btnOk);
      btnOk.addActionListener(this);
      
      JPanel panel_1 = new JPanel();
      contentPane.add(panel_1, BorderLayout.CENTER);
      panel_1.setLayout(new GridLayout(2, 1, 0, 0));
      panel_1.setBackground(Color.WHITE);
      
      JPanel panel_3 = new JPanel();
      panel_3.setBorder(new TitledBorder(null, "\uBCD1\uC6D0\uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      panel_1.add(panel_3);
      panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
      panel_3.setBackground(Color.WHITE);
      
      JPanel panel_2 = new JPanel();
      panel_2.setBorder(new TitledBorder(null, "\uD6C4\uAE30", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      panel_1.add(panel_2);
      panel_2.setLayout(new BorderLayout(0, 0));
      panel_2.setBackground(Color.WHITE);
      
      //comment
      scrollPane = new JScrollPane();
      panel_2.add(scrollPane, BorderLayout.CENTER);
      scrollPane.setBackground(Color.WHITE);
      
      vo1.setH_pho(strnum);
      System.out.println(strnum);
      vec = dao1.getComment(strnum);
      System.out.println(vec);
      
      
      list = new JList(vec);
      scrollPane.setViewportView(list);
      
      JPanel panel_4 = new JPanel();
      panel_2.add(panel_4, BorderLayout.SOUTH);
      panel_4.setBackground(Color.WHITE);
      
      //로그인 유무
      String str12 = Login.getIdset();
         if(str12!=null) {
         txtRev = new JTextField();
         panel_4.add(txtRev);
         txtRev.setColumns(40);
         }else {
            txtRev= new JTextField();
            panel_4.add(txtRev);
            txtRev.setColumns(40);
            txtRev.setEditable(false);
         }
      
      btnRev = new JButton("등록");
      panel_4.add(btnRev);
      btnRev.addActionListener(this);
      
      //상세정보클릭시 바로 정보가 label에 뜨도록
      HosDAO dao = new HosDAO();      
      HosVO vo=dao.getHostDetail(idx);
      
      panel_5 = new JPanel();
      panel_5.setBackground(Color.WHITE);
      panel_3.add(panel_5);
      panel_5.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
      
      JLabel lblHosp = new JLabel("       병    원    명   :      ");
      lblHosp.setFont(new Font("굴림", Font.PLAIN, 13));
      panel_5.add(lblHosp);
      lblHosp.setHorizontalAlignment(SwingConstants.CENTER);
      
      lbHosname = new JLabel("New label");
      panel_5.add(lbHosname);
      lbHosname.setHorizontalAlignment(SwingConstants.CENTER);
      
      lbHosname.setText(vo.getH_name());
      
      panel_6 = new JPanel();
      panel_6.setBackground(Color.WHITE);
      FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
      flowLayout.setAlignment(FlowLayout.LEFT);
      panel_3.add(panel_6);
      
      JLabel lblAddr = new JLabel("       주           소   :      ");
      lblAddr.setFont(new Font("굴림", Font.PLAIN, 13));
      panel_6.add(lblAddr);
      lblAddr.setHorizontalAlignment(SwingConstants.CENTER);
      
      lbAddr = new JLabel("New label");
      panel_6.add(lbAddr);
      lbAddr.setHorizontalAlignment(SwingConstants.CENTER);
      lbAddr.setText(vo.getH_addr());
      
      panel_7 = new JPanel();
      panel_7.setBackground(Color.WHITE);
      FlowLayout flowLayout_1 = (FlowLayout) panel_7.getLayout();
      flowLayout_1.setAlignment(FlowLayout.LEFT);
      panel_3.add(panel_7);
      
      JLabel lblTel = new JLabel("       전  화  번  호   :      ");
      lblTel.setFont(new Font("굴림", Font.PLAIN, 13));
      panel_7.add(lblTel);
      lblTel.setHorizontalAlignment(SwingConstants.CENTER);
      
      lbPho = new JLabel("New label");
      panel_7.add(lbPho);
      lbPho.setHorizontalAlignment(SwingConstants.CENTER);
      lbPho.setText(vo.getH_pho());
      
      panel_8 = new JPanel();
      panel_8.setBackground(Color.WHITE);
      FlowLayout flowLayout_2 = (FlowLayout) panel_8.getLayout();
      flowLayout_2.setAlignment(FlowLayout.LEFT);
      panel_3.add(panel_8);
      
      JLabel lblTime = new JLabel("       진료시간(평일) :     ");
      lblTime.setFont(new Font("굴림", Font.PLAIN, 13));
      panel_8.add(lblTime);
      lblTime.setHorizontalAlignment(SwingConstants.CENTER);
      
      lbTime = new JLabel("New label");
      panel_8.add(lbTime);
      lbTime.setHorizontalAlignment(SwingConstants.CENTER);
      lbTime.setText(vo.getFriday());
      
      panel_9 = new JPanel();
      panel_9.setBackground(Color.WHITE);
      FlowLayout flowLayout_3 = (FlowLayout) panel_9.getLayout();
      flowLayout_3.setAlignment(FlowLayout.LEFT);
      panel_3.add(panel_9);
      
      JLabel lblNewLabel = new JLabel("       진료시간(주말) :     ");
      lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 13));
      panel_9.add(lblNewLabel);
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      lbWeekTime = new JLabel("New label");
      panel_9.add(lbWeekTime);
      lbWeekTime.setHorizontalAlignment(SwingConstants.CENTER);
      lbWeekTime.setText(vo.getSaturday());

   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==btnRev) {
         Calendar today = Calendar.getInstance();
         SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY/MM/dd(E) hh:mm");
         String strToday = sdf1.format(today.getTime());

         String strrev = Login.getIdset() + " : " + txtRev.getText() + "    "+strToday;

         System.out.println(vo1.getH_pho());
         
         vo1.setC_comment(strrev);
         dao1.registComment(vo1);
         
         list.setListData(dao1.getComment(strnum));
         //list.updateUI();
         scrollPane.setViewportView(list);
         
         txtRev.setText("");
         
         System.out.println("성공");
      }   
      if(e.getSource()==btnReserve) {
         String str12 = Login.getIdset();
            if(str12!=null) {
            ReservationDate rvd = new ReservationDate();
            String strname = lbHosname.getText();
            String strtel = lbPho.getText();
            
             ReservationDate.strname = strname;
              ReservationDate.strnum = strtel;
            rvd.setVisible(true);
            validate();
            }else {
               JOptionPane.showConfirmDialog(this, "로그인을 해주세요", "알림", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
      }else if(e.getSource()==btnOk) {
         dispose();
      }   
   }
}
