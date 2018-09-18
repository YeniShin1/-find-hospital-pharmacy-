package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import project.DBUtils;

public class UserDAO {
	//데이터베이스 조회,삽입,삭제,수정 
	
	private Connection con=null;
	private ResultSet rs=null;
	private PreparedStatement pstmt=null;
	
	public int registUser(UserVO vo) { //삽입
		String sql="insert into userTBL(user_id,user_password,"
				+ "user_name,user_birth,user_gender) ";
		sql+="values(?,?,?,?,?)";
		int result=0;		
		DBUtils utils=DBUtils.getInstance();
			
		try {
			con=utils.connect();
			 //삽입하다가 이상이 발생하면 쿼리를 반영시키지 않기 위해			  
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getUser_id());
			pstmt.setString(2, vo.getUser_password());
			pstmt.setString(3, vo.getUser_name());
			pstmt.setDate(4, vo.getUser_birth());
			pstmt.setString(5, vo.getUser_gender());
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

	
	public int removeUser(UserVO vo) {
		int result=0;		
		DBUtils utils=DBUtils.getInstance();		
		try {
			con=utils.connect();
			con.setAutoCommit(false);
			String sql="delete from userTBL where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());
			result=pstmt.executeUpdate();
			if(result>0)
				con.commit();			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}			
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt);
		}		
		return result;
	}

	
	//수정
	public int updateUser(String pw, String name, String id) {
		int result=0;
		
		DBUtils utils=DBUtils.getInstance();
		try {
			con=utils.connect();
			con.setAutoCommit(false);
			String sql="update userTBL set user_password=? , user_name=? where user_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, id);
			result=pstmt.executeUpdate();
			if(result>0)
				con.commit();
		} catch (SQLException e) {	
			try {
				con.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}			
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt);
		}		
		return result;
	}
	
	//조회
	public UserVO getUser(UserVO vo) {		
		DBUtils utils=DBUtils.getInstance();		
		try {
			con=utils.connect();
			con.setAutoCommit(false);
			String sql="select * from userTBL where user_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getUser_id());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo.setNo(rs.getInt(1));
				vo.setUser_id(rs.getString(2));
				vo.setUser_password(rs.getString(3));
				vo.setUser_name(rs.getString(4));
				vo.setUser_birth(rs.getDate(5));
				vo.setUser_gender(rs.getString(6));
			}
			con.commit();
		}catch(SQLException e) {
			try {
				con.commit();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}			
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt, rs);
		}
		return vo;
	}
	
	/*
	public ArrayList<UserVO> getUsers(){
		UserVO vo=new UserVO();
		ArrayList<UserVO> list=new ArrayList<>();
		DBUtils utils=DBUtils.getInstance();		
		try {			
			con=utils.connect();
			String sql="select "+ vo.getUser_id() +" from userTBL";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				vo.setNo(rs.getInt(1));
				vo.setUser_id(rs.getString(2));
				vo.setUser_password(rs.getString(3));
				vo.setUser_name(rs.getString(4));
				vo.setUser_birth(rs.getDate(5));
				vo.setUser_gender(rs.getString(6));
				list.add(vo);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt, rs);
		}
		return list;
	}*/
	
	public int getidcheck(String id) { //아이디 중복체크
		UserVO vo=new UserVO();
		DBUtils utils=DBUtils.getInstance();		
		try {			
			con=utils.connect();
			String sql="select * from userTBL where user_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			}
			return 0;
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt);
		}
		return 0;
	}
	
	public int getlogincheck(String id, String pw) { //db에 회원 있는지 확인
		UserVO vo = new UserVO();
		DBUtils utils = DBUtils.getInstance();
		try {			
			con=utils.connect();
			String sql="select * from userTBL where user_id=? and user_password=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			if(rs.next()) {//id,pw있는경우
				return 1;
			}//없는경우
			return 0;
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			utils.close(con, pstmt);
		}
		return 0;
	}
}
