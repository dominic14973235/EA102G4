package com.mem.model;

import java.sql.Date;

public class MemVO implements java.io.Serializable{
	private String mem_id;
	private String mem_name;
	private String mem_psw;
	private Date mem_bir;
	private String sex;
	private String mem_addr;
	private String mem_email;
	private String mem_phone;
	private Integer mem_absent;
	private Integer coin;
	private String mem_resume;
	private java.sql.Timestamp m_reg_date;
	private String sel_auth;
	private String art_auth;
	private String com_auth;
	
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_psw() {
		return mem_psw;
	}
	public void setMem_psw(String mem_psw) {
		this.mem_psw = mem_psw;
	}
	public Date getMem_bir() {
		return mem_bir;
	}
	public void setMem_bir(Date mem_bir) {
		this.mem_bir = mem_bir;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public Integer getMem_absent() {
		return mem_absent;
	}
	public void setMem_absent(Integer mem_absent) {
		this.mem_absent = mem_absent;
	}
	public Integer getCoin() {
		return coin;
	}
	public void setCoin(Integer coin) {
		this.coin = coin;
	}
	public String getMem_resume() {
		return mem_resume;
	}
	public void setMem_resume(String mem_resume) {
		this.mem_resume = mem_resume;
	}
	public java.sql.Timestamp getM_reg_date() {
		return m_reg_date;
	}
	public void setM_reg_date(java.sql.Timestamp m_reg_date) {
		this.m_reg_date = m_reg_date;
	}
	public String getSel_auth() {
		return sel_auth;
	}
	public void setSel_auth(String sel_auth) {
		this.sel_auth = sel_auth;
	}
	public String getArt_auth() {
		return art_auth;
	}
	public void setArt_auth(String art_auth) {
		this.art_auth = art_auth;
	}
	public String getCom_auth() {
		return com_auth;
	}
	public void setCom_auth(String com_auth) {
		this.com_auth = com_auth;
	}
	
	
}
