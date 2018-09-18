package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
	private static DBUtils utils;
	private DBUtils() {	}
	
	public static DBUtils getInstance() {
		if(utils==null)
			utils=new DBUtils();
		return utils;
	}
	
	//DBConnection
	public Connection connect() {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/teamdb?useSSL=false";
			con=DriverManager.getConnection(url, "root", "1234");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return con;
	}
	
	public void close(Connection con,PreparedStatement pstmt,ResultSet rs) {
		try {
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	public void close(Connection con,PreparedStatement pstmt) {
		try {			
			pstmt.close();
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
}



