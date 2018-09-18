package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import project.DBUtils;

public class CommentDAO {
	//데이터베이스 조회,삽입,삭제,수정 
	
	private Connection con=null;
	private ResultSet rs=null;
	private PreparedStatement pstmt=null;
	
	public int registComment(CommentVO vo) { //삽입
		String sql = "insert into commentTBL(h_pho,c_comment) values (?,?)";
		int result=0;		
		DBUtils utils=DBUtils.getInstance();
			
		try {
			con=utils.connect();
			 //삽입하다가 이상이 발생하면 쿼리를 반영시키지 않기 위해			  
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getH_pho());
			pstmt.setString(2, vo.getC_comment());

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
	
	
	public Vector<CommentVO> getComment(String str){
		Vector<CommentVO> list = new Vector<>();
		DBUtils utils=DBUtils.getInstance();		
		try {			
			con=utils.connect();
			System.out.println(str);
			String sql="select * from commentTBL where h_pho=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,str);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CommentVO vo1 = new CommentVO();
				vo1.setNo(rs.getInt(1));
				vo1.setH_pho(rs.getString(2));
				vo1.setC_comment(rs.getString(3));
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
