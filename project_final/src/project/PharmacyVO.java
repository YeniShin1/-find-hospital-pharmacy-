package project;

public class PharmacyVO {
	private String p_name;
	private String p_pho;
	private String p_addr;
	
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_pho() {
		return p_pho;
	}
	public void setP_pho(String p_pho) {
		this.p_pho = p_pho;
	}
	public String getP_addr() {
		return p_addr;
	}
	public void setP_addr(String p_addr) {
		this.p_addr = p_addr;
	}
	@Override
	public String toString() {
		return "PharmacyVO [p_name=" + p_name + ", p_pho=" + p_pho + ", p_addr=" + p_addr + "]";
	}
	
	
}
