package com.trainerPublicV.model;

import java.sql.Date;

public class TrainerPublicVO {
	private String i_order_no;
	private String i_class_no;
	private String pro_ID;
	private Date rDate;
	private String hr;
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getI_order_no() {
		return i_order_no;
	}
	public void setI_order_no(String i_order_no) {
		this.i_order_no = i_order_no;
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
	public Date getrDate() {
		return rDate;
	}
	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}
	public String getHr() {
		return hr;
	}
	public void setHr(String hr) {
		this.hr = hr;
	}
}
