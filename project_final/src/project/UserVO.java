package project;

import java.sql.Date;

public class UserVO {
	//데이터를 담아서 객체로 만든 후 작업
	//데이터베이스 테이블 필드와 동일하게 생성
	//userTBL
	/*
	 * no int auto_increment not null primary key
	 * user_id char(15)
	 * user_password varchar(20)
	 * user_name char(10)
	 * user_birth date
	 * user_gender char(2)
	 */
	private int no;
	private String user_id;
	private String user_password;
	private String user_name;
	private Date user_birth;
	private String user_gender;
	
	public UserVO(int no, String user_id, String user_password, String user_name, Date user_birth, String user_gender) {
		super();
		this.no = no;
		this.user_id = user_id;
		this.user_password = user_password;
		this.user_name = user_name;
		this.user_birth = user_birth;
		this.user_gender = user_gender;
	}

	public UserVO() {
		super();
	}
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Date getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(Date user_birth) {
		this.user_birth = user_birth;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	
	@Override
	public String toString() {
		return "[no=" + no + ", user_id=" + user_id + ", user_password=" + user_password 
				+ ", user_name=" + user_name + ", user_birth=" + user_birth
				+", user_gender=" + user_gender+ "]";
	}
	
}
