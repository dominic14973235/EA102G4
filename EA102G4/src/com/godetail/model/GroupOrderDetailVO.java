package com.godetail.model;

public class GroupOrderDetailVO implements java.io.Serializable{
	

	private String g_order_no;
	private String g_class_no;
	private java.sql.Date rdate;
	private String hr;
	private Integer p_coin;
	private Integer go_status;
	private String g_time_no;
	
	
	
	public String getG_time_no() {
		return g_time_no;
	}
	public void setG_time_no(String g_time_no) {
		this.g_time_no = g_time_no;
	}
	public String getG_order_no() {
		return g_order_no;
	}
	public void setG_order_no(String g_order_no) {
		this.g_order_no = g_order_no;
	}
	public String getG_class_no() {
		return g_class_no;
	}
	public void setG_class_no(String g_class_no) {
		this.g_class_no = g_class_no;
	}
	public java.sql.Date getRdate() {
		return rdate;
	}
	public void setRdate(java.sql.Date rdate) {
		this.rdate = rdate;
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
	public Integer getGo_status() {
		return go_status;
	}
	public void setGo_status(Integer go_status) {
		this.go_status = go_status;
	}
	
}
