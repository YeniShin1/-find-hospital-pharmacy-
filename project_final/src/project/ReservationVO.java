package project;

import java.sql.Date;

public class ReservationVO {
	/*no int auto_increment primary key,
	user_id char(15) not null,
	reserve_date varchar(30),
	reserve_time varchar(30),
	reserve_hos varchar(20),
	reserve_pho varchar(20)*/
	
	private int no;
	private String user_id;
	private String reserve_date;
	private String reserve_time;
	private String reserve_hos;
	private String reserve_pho;
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
	public String getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}
	public String getReserve_time() {
		return reserve_time;
	}
	public void setReserve_time(String reserve_time) {
		this.reserve_time = reserve_time;
	}
	public String getReserve_hos() {
		return reserve_hos;
	}
	public void setReserve_hos(String reserve_hos) {
		this.reserve_hos = reserve_hos;
	}
	public String getReserve_pho() {
		return reserve_pho;
	}
	public void setReserve_pho(String reserve_pho) {
		this.reserve_pho = reserve_pho;
	}
	@Override
	public String toString() {
		return "ReservationVO [no=" + no + ", user_id=" + user_id + ", reserve_date=" + reserve_date + ", reserve_time="
				+ reserve_time + ", reserve_hos=" + reserve_hos + ", reserve_pho=" + reserve_pho + "]";
	}
	
	
	
	
	
	
	
}