package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class PharmacyDAO {
	//데이터베이스 조회
	
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
	private String sql;
	
	//콤보박스 시
	public String[] setComboSi() {
		DBUtils utils = DBUtils.getInstance();
		String[] comboBox_name =null;
		try {
			con = utils.connect();
			sql = "select count(*) cnt from (select si from sigudong group by si) cnt";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int a =0;
			while(rs.next()) {
				a = Integer.parseInt(rs.getString(1));
			}
			
			sql = "select distinct si from sigudong order by si asc";			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			comboBox_name = new String[a];
			
			int i=0;
			while(rs.next()) {
				comboBox_name[i] = rs.getString(1);
				i++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt);
		}
		return comboBox_name;
	}
	
	
	//콤보박스 구
	public String[] setComboGoo(String si) {
		DBUtils utils = DBUtils.getInstance();
		String[] comboBox_name2 =null;
		try {
			con = utils.connect();
			sql = "select count(*) cnt from (select gu from sigudong where si=? group by gu) cnt";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, si.trim());
			rs=pstmt.executeQuery();
			int a = 0;
			while(rs.next()) {
				a = Integer.parseInt(rs.getString(1));
			}
			
			sql = "select distinct gu from sigudong where si = ? order by gu asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, si.trim());
			rs=pstmt.executeQuery();
			
			comboBox_name2 = new String[a];
			
			int i=0;
			while(rs.next()) {
				comboBox_name2[i] = rs.getString(1);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt);
		}
		return comboBox_name2;
	}
	
	//콤보박스 동
		public String[] setComboDong(String si, String goo) {		
			DBUtils utils = DBUtils.getInstance();
			String[] comboBox_name3 =null;
			try {
				con = utils.connect();
				sql = "select count(*) cnt from (select dong from sigudong where si=? and gu=? group by dong) cnt";
				pstmt=con.prepareStatement(sql);				
				pstmt.setString(1, si);
				pstmt.setString(2, goo);
				rs=pstmt.executeQuery();
				int a = 0;
				while(rs.next()) {
					a = Integer.parseInt(rs.getString(1));
				}
				sql="select distinct dong from sigudong where si=? and gu=? order by dong asc";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, si.trim());
				pstmt.setString(2, goo.trim());
				rs=pstmt.executeQuery();
	
				comboBox_name3 = new String[a];
				
				int i=0;
				while(rs.next()) {
					comboBox_name3[i] = rs.getString(1);
					i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				utils.close(con, pstmt);
			}
			return comboBox_name3;
		}

	//콤보조회
	public Vector<PharmacyVO> getphars(String si, String goo, String dong){
		Vector<PharmacyVO> list = new Vector<>();
		DBUtils utils = DBUtils.getInstance();
		try {
			con = utils.connect();
			String sql="select p_name,p_pho,p_addr from phatbl where locate(?, p_addr) and locate(?, p_addr) and locate(?, p_addr)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, si.trim());
			pstmt.setString(2, goo.trim());
			pstmt.setString(3, dong.trim());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				PharmacyVO vo=new PharmacyVO();
				vo.setP_name(rs.getString(1));
				vo.setP_pho(rs.getString(2));
				vo.setP_addr(rs.getString(3));
				list.add(vo); //리스트에 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt);
		}
		return list;
	}
	
	//콤보조회, 값 입력
	public Vector<PharmacyVO> getPharStr(String si, String goo, String dong, String str){
		Vector<PharmacyVO> list = new Vector<>();
		DBUtils utils = DBUtils.getInstance();
		try {
			con = utils.connect();
			String sql="select p_name,p_pho,p_addr from phatbl where locate(?, p_addr) and locate(?, p_addr) and locate(?, p_addr) and locate(?, p_name)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, si.trim());
			pstmt.setString(2, goo.trim());
			pstmt.setString(3, dong.trim());
			pstmt.setString(4, str.trim());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				PharmacyVO vo=new PharmacyVO();
				vo.setP_name(rs.getString(1));
				vo.setP_pho(rs.getString(2));
				vo.setP_addr(rs.getString(3));
				list.add(vo); //리스트에 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt);
		}
		return list;
	}
	
	//전체조회
	 public Vector<PharmacyVO> getPharmacy(){
	      Vector<PharmacyVO> list=new Vector<>();
	      DBUtils utils=DBUtils.getInstance();
	      try {
	         con=utils.connect();
	        // String sql="select * from pharTBL order by cast(p_no as unsigned);";
	         String sql="select p_name,p_pho,p_addr from phatbl";
	         pstmt=con.prepareStatement(sql);
	         rs=pstmt.executeQuery();
	         while(rs.next()) {
	        	 PharmacyVO vo=new PharmacyVO();
	            vo.setP_name(rs.getString(1));
	            vo.setP_pho(rs.getString(2));
	            vo.setP_addr(rs.getString(3));
	            list.add(vo);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         utils.close(con, pstmt, rs);
	      }
	      return list;
	      
	   }
}
