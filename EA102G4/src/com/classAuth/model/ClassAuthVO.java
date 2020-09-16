package com.classAuth.model;

public class ClassAuthVO implements java.io.Serializable{
	private String auth_no;
	private String pro_ID;
	private String c_type_no;
	private Integer ca_status;
	

	public String getAuth_no() {
		return auth_no;
	}
	public void setAuth_no(String auth_no) {
		this.auth_no = auth_no;
	}
	public String getPro_ID() {
		return pro_ID;
	}
	public void setPro_ID(String pro_ID) {
		this.pro_ID = pro_ID;
	}
	public String getC_type_no() {
		return c_type_no;
	}
	public void setC_type_no(String c_type_no) {
		this.c_type_no = c_type_no;
	}
	public Integer getCa_status() {
		return ca_status;
	}
	public void setCa_status(Integer ca_status) {
		this.ca_status = ca_status;
	}
	
}
