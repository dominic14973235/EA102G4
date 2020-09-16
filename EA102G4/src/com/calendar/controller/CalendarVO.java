package com.calendar.controller;

import java.sql.Date;

public class CalendarVO {
	private String class_name;
	private String order_no;
	private String class_no;
	private String pro_ID;	
	private String mem_ID;
	private String mem_name;
	private Date rDate;
	private String hr;
	private Integer g_max;
	private Integer quantity;
	private Integer p_coin;
	private Integer io_status;

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}	
	public Integer getG_max() {
		return g_max;
	}
	public void setG_max(Integer g_max) {
		this.g_max = g_max;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getClass_no() {
		return class_no;
	}
	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}
	public String getPro_ID() {
		return pro_ID;
	}
	public void setPro_ID(String pro_ID) {
		this.pro_ID = pro_ID;
	}
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
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
	
	public Integer getP_coin() {
		return p_coin;
	}
	public void setP_coin(Integer p_coin) {
		this.p_coin=p_coin;
	}
	
	public Integer getIo_status() {
		return io_status;
	}
	public void setIo_status(Integer io_status) {
		this.io_status=io_status;
	}
}
