package project;

import java.sql.Date;

public class CommentVO {
	//데이터를 담아서 객체로 만든 후 작업
	//데이터베이스 테이블 필드와 동일하게 생성
	//commentTBL
	 /*h_pho varchar(20) not null primary key,
	 c_comment varchar(100)*/
	
	private int no;
	private String h_pho;
	private String c_comment;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getH_pho() {
		return h_pho;
	}
	public void setH_pho(String h_pho) {
		this.h_pho = h_pho;
	}
	public String getC_comment() {
		return c_comment;
	}
	public void setC_comment(String c_comment) {
		this.c_comment = c_comment;
	}
	public CommentVO(int no, String h_pho, String c_comment) {
		super();
		this.no = no;
		this.h_pho = h_pho;
		this.c_comment = c_comment;
	}
	public CommentVO() {
		super();
	}

	@Override
	public String toString() {
		return c_comment ;
	}
	
	
}