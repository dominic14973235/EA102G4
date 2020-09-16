package com.grouporder.model;

public class GroupOrderVO {
	
	private String g_order_no;
	private String mem_id;
	private java.sql.Timestamp g_order_time;
	public String getG_order_no() {
		return g_order_no;
	}
	public void setG_order_no(String g_order_no) {
		this.g_order_no = g_order_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public java.sql.Timestamp getG_order_time() {
		return g_order_time;
	}
	public void setG_order_time(java.sql.Timestamp g_order_time) {
		this.g_order_time = g_order_time;
	}
	
	
}
