package com.license.model;

public class LicenseVO implements java.io.Serializable{
	private String license_no;
	private String pro_ID;
	private String lic_name;
	private String no_reg;
	private byte[] l_pic;
	
	public LicenseVO() {};
	
	public String getLicense_no() {
		return license_no;
	}
	public void setLicense_no(String license_no) {
		this.license_no = license_no;
	}
	public String getPro_ID() {
		return pro_ID;
	}
	public void setPro_ID(String pro_ID) {
		this.pro_ID = pro_ID;
	}
	public String getLic_name() {
		return lic_name;
	}
	public void setLic_name(String lic_name) {
		this.lic_name = lic_name;
	}
	public String getNo_reg() {
		return no_reg;
	}
	public void setNo_reg(String no_reg) {
		this.no_reg = no_reg;
	}
	public byte[] getL_pic() {
		return l_pic;
	}
	public void setL_pic(byte[] l_pic) {
		this.l_pic = l_pic;
	}

}
