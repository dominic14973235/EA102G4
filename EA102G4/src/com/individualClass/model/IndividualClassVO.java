package com.individualClass.model;

public class IndividualClassVO implements java.io.Serializable{
	private String i_class_no;
	private String pro_ID;
	private String c_name;
	private String c_type_no;
	private Integer p_coin;
	private String loc;
	private String c_detail;
	private byte[] c_pic;
	private Integer c_status;
	

	public  IndividualClassVO() {
	}

	public Integer getP_coin() {
		return p_coin;
	}

	public void setP_coin(Integer p_coin) {
		this.p_coin = p_coin;
	}
	
	public String getI_class_no() {
		return i_class_no;
	}
	public void setI_class_no(String i_class_no) {
		this.i_class_no = i_class_no;
	}
	public String getPro_ID() {
		return pro_ID;
	}
	public void setPro_ID(String pro_ID) {
		this.pro_ID = pro_ID;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_type_no() {
		return c_type_no;
	}
	public void setC_type_no(String c_type_no) {
		this.c_type_no = c_type_no;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getC_detail() {
		return c_detail;
	}
	public void setC_detail(String c_detail) {
		this.c_detail = c_detail;
	}
	public byte[] getC_pic() {
		return c_pic;
	}
	public void setC_pic(byte[] c_pic) {
		this.c_pic = c_pic;
	}
	public Integer getC_status() {
		return c_status;
	}
	public void setC_status(Integer c_status) {
		this.c_status = c_status;
	}
	
}
