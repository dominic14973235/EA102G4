package com.IClassOrder.model;

public class IClassOrderVO implements java.io.Serializable{
	private String i_order_no;
	private String i_class_no;
	private String mem_ID;
	private java.sql.Date rDate;
	private String hr;
	private Integer p_coin;
	private Integer io_status;
	
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
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	
	public java.sql.Date getRDate() {
		return rDate;
	}
	public void setRDate(java.sql.Date rDate) {
		this.rDate = rDate;
	}
	public String getHr() {
		return hr;
	}
	public void setHr(String hr) {
		this.hr = hr;
	}
	public Integer getP_coin() {
		return p_coin;
	}
	public void setP_coin(Integer p_coin) {
		this.p_coin = p_coin;
	}
	public Integer getIo_status() {
		return io_status;
	}
	public void setIo_status(Integer io_status) {
		this.io_status = io_status;
	}
	
	
}
