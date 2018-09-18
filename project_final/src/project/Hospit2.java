package project;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import project.ReviewPage;

import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Hospit2 extends JPanel implements ActionListener{
	private JTextField txthospital;
	private JComboBox combosi, combogoo, combodong, combotype;
	private JTable table;
	private DefaultTableModel model;
	private JPanel panel;
	private JButton btnreservation, btnsearch, btndetail;
	private String searchWord1 = null;
	private String searchWord2 = null;
	private String searchWord3 = null;
	private String searchType = null;
	private String searchHos=null;
	private HosDAO dao=new HosDAO();
	private JCheckBox chkweekend;

	private Vector<HosVO> hoslist;
	/**
	 * Create the panel.
	 */
	public Hospit2() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.setBackground(Color.WHITE);
		
		panel = new JPanel();
		panel_2.add(panel, BorderLayout.NORTH);
		panel.setBackground(Color.WHITE);
		
		combosi = new JComboBox();	
		combosi.setBackground(Color.WHITE);
		
		combogoo = new JComboBox();
		combogoo.setBackground(Color.WHITE);
		
		String[] sim = dao.setComboSi();
		for(int i=0;i<sim.length;i++) {
			combosi.insertItemAt(sim[i], i);
		}
		combosi.setSelectedIndex(-1);
		
		combosi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(combosi.getSelectedIndex() != -1) {
					searchWord1 = (String) combosi.getSelectedItem();
					String[] gum= dao.setComboGoo(searchWord1); //시 가 선택되면, 시에 포함된 구,군을 combogoo에 넣음
				    combogoo.removeAllItems();
				    
				    for(int i=0;i<gum.length;i++) {
				    	combogoo.insertItemAt(gum[i], i);
					}
				}
			}
		});
		
		panel.add(combosi);
		
		JLabel lblNewLabelsi = new JLabel("시");
		panel.add(lblNewLabelsi);
		
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		combogoo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("source"+e.getSource());
				
				if(combosi.getSelectedIndex() != -1 && combogoo.getSelectedIndex() != -1) {
					searchWord1 = (String) combosi.getSelectedItem();
					searchWord2 = (String) combogoo.getSelectedItem();
					
					String dongm[] = dao.setComboDong(searchWord1, searchWord2); //시, 구/군 이 선택되면, combobox3에 동을 넣음
					combodong.removeAllItems();
					combodong.setSelectedIndex(-1);
					
					for(int i=0;i<dongm.length;i++) {
						combodong.insertItemAt(dongm[i], i);
					}
				}
			}
		});

		panel.add(combogoo);
		
		JLabel lblNewLabel = new JLabel("구");
		panel.add(lblNewLabel);
		
		
		combodong = new JComboBox();
		combodong.setBackground(Color.WHITE);
		combodong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(combosi.getSelectedIndex() != -1 && combogoo.getSelectedIndex() != -1 && combodong.getSelectedIndex() != -1) {
					
					searchWord1 = (String)combosi.getSelectedItem();
					searchWord2 = (String)combogoo.getSelectedItem();
				    searchWord3 = (String)combodong.getSelectedItem();
				}
			}
		});
		
		panel.add(combodong);
		
		JLabel label = new JLabel("동");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_2.add(panel_1);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		combotype = new JComboBox();
		
		JLabel lblNewLabel_1 = new JLabel("진료과목");
		panel_1.add(lblNewLabel_1);
		panel_1.add(combotype);
		
		String[] typem = dao.setComboType();
		for(int i=0;i<typem.length;i++) {
			combotype.insertItemAt(typem[i], i);
		}
		combotype.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchType = (String) combotype.getSelectedItem();
			}
		});
		
		combotype.setSelectedIndex(-1);
		chkweekend = new JCheckBox("주말진료");
		chkweekend.setBackground(Color.WHITE);
		chkweekend.addActionListener(this);
		panel_1.add(chkweekend);
		
		txthospital = new JTextField();
		panel_1.add(txthospital);
		txthospital.setColumns(10);
		
		btnsearch = new JButton("검색");
		panel_1.add(btnsearch);
		btnsearch.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBackground(Color.WHITE);
		String columnNames[] = {"병원명","진료과목","전화번호","주소","주말진료여부"};
		model = new DefaultTableModel(columnNames, 0);
		
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(190);
		table.getColumnModel().getColumn(4).setPreferredWidth(75);
		scrollPane.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(600, 400));
		table.setBackground(Color.WHITE);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.SOUTH);
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnreservation = new JButton("예약하기");
		panel_3.add(btnreservation);
		btnreservation.addActionListener(this);

		btndetail = new JButton("상세정보");
		panel_3.add(btndetail);
		btndetail.addActionListener(this);
	}
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	@Override
	   public void actionPerformed(ActionEvent e) {
	      AbstractButton btn = (AbstractButton) e.getSource();
	      
	      if(btn==btnsearch) {
	         model.setNumRows(0);
	         if(combosi.getSelectedItem() == null) {
	            hoslist = dao.getHospit();
	            addlist();
	         }else if(combotype.getSelectedItem() == null) {
	            hoslist = dao.getHostsNoType(searchWord1, searchWord2, searchWord3);
	            addlist();
	         }else {
	            if(chkweekend.isSelected()) {
	               if(txthospital.getText().isEmpty()) {
	                  hoslist = dao.getHostWeek(searchWord1, searchWord2, searchWord3,searchType);
	                  addlist();
	               }else {
	                  searchHos = txthospital.getText();
	                  hoslist = dao.getHostWeekStr(searchWord1, searchWord2, searchWord3,searchType,searchHos);
	                  addlist();
	               }
	               
	            }else {
	               if(txthospital.getText().isEmpty()) {
	                  hoslist = dao.getHosts(searchWord1, searchWord2, searchWord3,searchType);
	                  addlist();
	               }else {
	                  searchHos = txthospital.getText();
	                  hoslist = dao.getHostStr(searchWord1, searchWord2, searchWord3,searchType,searchHos);
	                  addlist();
	               }
	            }
	         }
	      }else if(btn==btnreservation) {
	         if(table.getSelectedRow()==-1) {
	            
	            JOptionPane.showConfirmDialog(this, "병원을 선택하세요", "예약하기", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
	         }else if(table.getSelectedRow()!=-1){
	            //Login login= new Login(MainScreen);
	              String str12 = Login.getIdset();
	               if(str12!=null) {
	                  ReservationDate rvd = new ReservationDate();
	                  
	                  String strname = (String) table.getValueAt(table.getSelectedRow(), 0);
	                  String strtel = (String) table.getValueAt(table.getSelectedRow(), 2);
	                  
	                   ReservationDate.strname = strname;
	                    ReservationDate.strnum = strtel;
	                    
	                  rvd.setVisible(true);
	                  validate();
	               }else {
	                  JOptionPane.showConfirmDialog(this, "로그인을 해주세요", "알림", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
	               }
	         }
	      }else if(btn==btndetail) {
	         //HosVO vo = new HosVO();
	         if(table.getSelectedRow()==-1) {
	            JOptionPane.showConfirmDialog(this, "병원을 선택하세요", "상세정보", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
	         }else if(table.getSelectedRow()!=-1){
	            int row = table.getSelectedRow(); //선택된 row
	            String strname = (String) table.getValueAt(table.getSelectedRow(), 0);
	            String strtel = (String) table.getValueAt(table.getSelectedRow(), 2);
	            
	              ReviewPage.strnum = strtel;
	   
	            String idx = (String) model.getValueAt(row, 2);
	            ReviewPage.idx=idx;
	            ReviewPage rvp = new ReviewPage();
	            rvp.setVisible(true);
	            validate();

	         }
	      }
	   }

	public void addlist() {
		for(HosVO hv: hoslist) {
			Vector<String> vec = new Vector<>();
			vec.addElement(hv.getH_name());
			vec.addElement(hv.getH_type());
			vec.addElement(hv.getH_pho());
			vec.addElement(hv.getH_addr());
			vec.addElement(hv.getH_weekend());
			model.addRow(vec);	
		}
	}
}