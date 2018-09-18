package project;

public class HosVO {
	private String h_name;
	private String  h_type;
	private String h_pho;
	private String h_addr;
	private String h_weekend;
	private String friday;
	private String saturday;
	
	public String getH_name() {
		return h_name;
	}
	public void setH_name(String h_name) {
		this.h_name = h_name;
	}
	public String getH_type() {
		return h_type;
	}
	public void setH_type(String h_type) {
		this.h_type = h_type;
	}
	public String getH_pho() {
		return h_pho;
	}
	public void setH_pho(String h_pho) {
		this.h_pho = h_pho;
	}
	public String getH_addr() {
		return h_addr;
	}
	public void setH_addr(String h_addr) {
		this.h_addr = h_addr;
	}
	public String getH_weekend() {
		return h_weekend;
	}
	public void setH_weekend(String h_weekend) {
		this.h_weekend = h_weekend;
	}
	public String getFriday() {
		return friday;
	}
	public void setFriday(String friday) {
		this.friday = friday;
	}
	public String getSaturday() {
		return saturday;
	}
	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}
	
	@Override
	public String toString() {
		return "HosVO [h_name=" + h_name + ", h_type=" + h_type + ", h_pho=" + h_pho + ", h_addr=" + h_addr
				+ ", h_weekend=" + h_weekend + ", friday=" + friday + ", saturday=" + saturday + "]";
	}

	
}
