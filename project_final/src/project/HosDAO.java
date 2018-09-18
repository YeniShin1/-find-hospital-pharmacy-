package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class HosDAO {
	//데이터베이스 조회
	
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
	private String sql;
	private Vector<HosVO> list;
	
	//콤보박스 시
	public String[] setComboSi() {
		DBUtils utils = DBUtils.getInstance();
		String[] comboBox_name =null;
		try {
			con = utils.connect();
			sql = "select count(*) cnt from (select si from sigudong group by si) cnt";
			pstmt=con.prepareStatement(sql);
			System.out.println("setCombosi "+pstmt);
			rs=pstmt.executeQuery();
			int a =0;
			while(rs.next()) {
				a = Integer.parseInt(rs.getString(1));
			}
			sql = "select distinct si from sigudong order by si asc";			
			pstmt=con.prepareStatement(sql);
			System.out.println("setCombosi "+pstmt);
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
			System.out.println("setComboGu "+pstmt);
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
	
	
		//콤보박스 타입
		public String[] setComboType() {		
			DBUtils utils = DBUtils.getInstance();
			String[] comboBox_name4 =null;
			try {
				con = utils.connect();
				sql = "select count(distinct h_type) from hostbl order by h_type asc";
				pstmt=con.prepareStatement(sql);				
				rs=pstmt.executeQuery();
				int a = 0;
				while(rs.next()) {
					a = Integer.parseInt(rs.getString(1));
				}
				sql="select distinct h_type from hostbl order by h_type asc";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
	
				comboBox_name4 = new String[a];
				
				int i=0;
				while(rs.next()) {
					comboBox_name4[i] = rs.getString(1);
					i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				utils.close(con, pstmt);
			}
			return comboBox_name4;
		}

	
		
	//콤보조회
	public Vector<HosVO> getHosts(String si, String goo, String dong, String type){
		list = new Vector<>();
		DBUtils utils = DBUtils.getInstance();
		try {
			con = utils.connect();
			String sql="select h_name,h_type,h_pho,h_addr,h_weekend from hostbl where locate(?, h_addr) and locate(?, h_addr) and locate(?, h_addr) and h_type=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, si.trim());
			pstmt.setString(2, goo.trim());
			pstmt.setString(3, dong.trim());
			pstmt.setString(4, type.trim());
			rs=pstmt.executeQuery();
			addlist();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt);
		}
		return list;
	}
	
	//콤보조회, 값 입력
	public Vector<HosVO> getHostStr(String si, String goo, String dong, String type, String str){
		list = new Vector<>();
		DBUtils utils = DBUtils.getInstance();
		try {
			con = utils.connect();
			String sql="select h_name,h_type,h_pho,h_addr,h_weekend from hostbl where locate(?, h_addr) and locate(?, h_addr) and locate(?, h_addr) and h_type=? and locate(?, h_name)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, si.trim());
			pstmt.setString(2, goo.trim());
			pstmt.setString(3, dong.trim());
			pstmt.setString(4, type.trim());
			pstmt.setString(5, str.trim());
			rs=pstmt.executeQuery();
			addlist();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt);
		}
		return list;
	}
	
	//콤보조회, 주말진료체크
	public Vector<HosVO> getHostWeek(String si, String goo, String dong, String type){
		list = new Vector<>();
		DBUtils utils = DBUtils.getInstance();
		try {
			con = utils.connect();
			String sql="select h_name,h_type,h_pho,h_addr,h_weekend from hostbl where locate(?, h_addr) and locate(?, h_addr) and locate(?, h_addr) and h_type=? and (h_weekend like '토%' or h_weekend like '%일%')";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, si.trim());
			pstmt.setString(2, goo.trim());
			pstmt.setString(3, dong.trim());
			pstmt.setString(4, type.trim());
			rs=pstmt.executeQuery();
			System.out.println("병원리스트:"+pstmt);
			addlist();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt);
		}
		return list;
	}
	
	//콤보조회, 주말진료체크, 값 입력
	public Vector<HosVO> getHostWeekStr(String si, String goo, String dong, String type, String str){
		list = new Vector<>();
		DBUtils utils = DBUtils.getInstance();
		try {
			con = utils.connect();
			String sql="select h_name,h_type,h_pho,h_addr,h_weekend from hostbl where locate(?, h_addr) and locate(?, h_addr) and locate(?, h_addr) and h_type=? and (h_weekend like '토%' or h_weekend like '%일%') and locate(?, h_name)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, si.trim());
			pstmt.setString(2, goo.trim());
			pstmt.setString(3, dong.trim());
			pstmt.setString(4, type.trim());
			pstmt.setString(5, str.trim());
			rs=pstmt.executeQuery();
			addlist();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt);
		}
		return list;
	}
	
	 //전체병원 조회
	   public Vector<HosVO> getHospit(){
	      Vector<HosVO> list=new Vector<>();
	      DBUtils utils=DBUtils.getInstance();
	      try {
	         con=utils.connect();
	         String sql="select h_name,h_type,h_pho,h_addr,h_weekend from hostbl";
	         pstmt=con.prepareStatement(sql);
	         //select문을 수행할 때 사용
	         rs=pstmt.executeQuery();
	         while(rs.next()) {
	        	 HosVO vo =new HosVO();
	            vo.setH_name(rs.getString(1));
	            vo.setH_type(rs.getString(2));
	            vo.setH_pho(rs.getString(3));
	            vo.setH_addr(rs.getString(4));
	            vo.setH_weekend(rs.getString(5));
	            list.add(vo);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         utils.close(con, pstmt, rs);
	      }
	      return list;
	      
	   }
	   
	 //콤보조회, 타입선택하지 않을 경우
	   public Vector<HosVO> getHostsNoType(String si, String goo, String dong){
	      list = new Vector<>();
	      DBUtils utils = DBUtils.getInstance();
	      try {
	         con = utils.connect();
	         String sql="select h_name,h_type,h_pho,h_addr,h_weekend from hostbl where locate(?, h_addr) and locate(?, h_addr) and locate(?, h_addr)";
	         pstmt=con.prepareStatement(sql);
	         pstmt.setString(1, si.trim());
	         pstmt.setString(2, goo.trim());
	         pstmt.setString(3, dong.trim());
	         rs=pstmt.executeQuery();
	         addlist();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         utils.close(con, pstmt);
	      }
	      return list;
	   }

	   //상세보기(하나만 출력시키는 것)
	   public HosVO getHostDetail(String idx){
			//list = new Vector<>();
			DBUtils utils = DBUtils.getInstance();
			HosVO vo=null;
			try {
				con = utils.connect();
				String sql="select h_name, h_addr, h_pho, friday, saturday from hostbl where h_pho=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, idx.trim());
				rs=pstmt.executeQuery();
				 if(rs.next()) {
		        	vo =new HosVO();
		            vo.setH_name(rs.getString(1));
		            vo.setH_addr(rs.getString(2));
		            vo.setH_pho(rs.getString(3));
		            vo.setFriday(rs.getString(4));
		            vo.setSaturday(rs.getString(5));     
		            
		         }
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				utils.close(con, pstmt);
			}
			return vo;
		}
	
	public void addlist() {
		try {
			while(rs.next()) {
				HosVO vo=new HosVO();
				vo.setH_name(rs.getString(1));
				vo.setH_type(rs.getString(2));
				vo.setH_pho(rs.getString(3));
				vo.setH_addr(rs.getString(4));
				vo.setH_weekend(rs.getString(5));
				list.add(vo); //리스트에 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
