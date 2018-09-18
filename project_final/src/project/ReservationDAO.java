package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import project.DBUtils;

public class ReservationDAO {
	//데이터베이스 조회,삽입,삭제,수정 
	
	private Connection con=null;
	private ResultSet rs=null;
	private PreparedStatement pstmt=null;
	
	public int insertReservation(ReservationVO vo) { //삽입
		String sql = "insert into reserveTBL(user_id,reserve_date,reserve_time,reserve_hos,reserve_pho) values (?,?,?,?,?)";
		int result=0;		
		DBUtils utils=DBUtils.getInstance();
			
		try {
			con=utils.connect();
			 //삽입하다가 이상이 발생하면 쿼리를 반영시키지 않기 위해			  
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getUser_id());
			pstmt.setString(2, vo.getReserve_date());
			pstmt.setString(3, vo.getReserve_time());
			pstmt.setString(4, vo.getReserve_hos());
			pstmt.setString(5, vo.getReserve_pho());

			result=pstmt.executeUpdate();
			if(result>0)
				con.commit();	//쿼리 반영		
		} catch (SQLException e) {		
			try {
				con.rollback(); //이상 발생시 쿼리 되돌리기
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt);
		}
		return result;
	}
	
	
	public Vector<ReservationVO> getReservation(String str){
		Vector<ReservationVO> list = new Vector<>();
		DBUtils utils=DBUtils.getInstance();		
		try {			
			con=utils.connect();
			System.out.println(str);
			String sql="select reserve_hos, reserve_pho, reserve_date, reserve_time from reserveTBL where user_id=? order by no desc limit 3";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,str);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ReservationVO vo1 = new ReservationVO();
				//vo1.setNo(rs.getInt(1));
				vo1.setReserve_hos(rs.getString(1));
				vo1.setReserve_pho(rs.getString(2));
				vo1.setReserve_date(rs.getString(3));
				vo1.setReserve_time(rs.getString(4));
				list.add(vo1);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt, rs);
		}
		return list;
	}

}
