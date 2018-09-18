package project;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

public class Pharmacy extends JPanel implements ActionListener{
	private JTextField txtPharmacy;
	private JComboBox combosi, combogoo, combodong;
	private JButton btnsearch;
	private JTable table;
	private DefaultTableModel model;
	private String searchWord1 = null;
	private String searchWord2 = null;
	private String searchWord3 = null;
	private String searchPhar = null;
	private PharmacyDAO dao=new PharmacyDAO();
	private Vector<PharmacyVO> pharlist;
	
	/**
	 * Create the panel.
	 */
	public Pharmacy() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		combosi = new JComboBox();
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
					String[] gum= dao.setComboGoo(searchWord1);
				    combogoo.removeAllItems();
				    
				    for(int i=0;i<gum.length;i++) {
				    	combogoo.insertItemAt(gum[i], i);
					}
				}
			}
		});
		panel.add(combosi);
		
		combogoo = new JComboBox();
		combogoo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(combosi.getSelectedIndex() != -1 && combogoo.getSelectedIndex() != -1) {
					searchWord1 = (String) combosi.getSelectedItem();
					searchWord2 = (String) combogoo.getSelectedItem();
					
					String dongm[] = dao.setComboDong(searchWord1, searchWord2);
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
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel_1, BorderLayout.CENTER);
		
		txtPharmacy = new JTextField();
		panel_1.add(txtPharmacy);
		txtPharmacy.setColumns(10);
		
		btnsearch = new JButton("검색");
		panel_1.add(btnsearch);
		btnsearch.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane, BorderLayout.SOUTH);
		String columnNames[] = {"약국명","전화번호","주소"};
		model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(320);
		scrollPane.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(600, 400));
		table.setBackground(Color.WHITE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn==btnsearch) {
			model.setNumRows(0);
			if(combosi.getSelectedItem() == null) {
				pharlist = dao.getPharmacy();
				addlist();
			}else {
				pharlist = dao.getphars(searchWord1, searchWord2, searchWord3);
				if(txtPharmacy.getText().isEmpty()) {
					pharlist = dao.getphars(searchWord1, searchWord2, searchWord3);
					addlist();
				}else {
					searchPhar = txtPharmacy.getText();
					pharlist = dao.getPharStr(searchWord1, searchWord2, searchWord3,searchPhar);
					addlist();
				}
			}
		}
	}
	
	public void addlist() {
		for(PharmacyVO hv: pharlist) {
			Vector<String> vec = new Vector<>();
			vec.addElement(hv.getP_name());
			vec.addElement(hv.getP_pho());
			vec.addElement(hv.getP_addr());
			model.addRow(vec);	
		}
	}
}
