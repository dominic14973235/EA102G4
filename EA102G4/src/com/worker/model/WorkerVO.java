package com.worker.model;
import java.sql.Date;

public class WorkerVO implements java.io.Serializable{
	private String work_id;
	private String w_name;
	private String w_acc;
	private String w_pw;
	private String email;
	private Date reg_date;
	private byte[] photo;
	public String getWork_id() {
		return work_id;
	}
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public String getW_acc() {
		return w_acc;
	}
	public void setW_acc(String w_acc) {
		this.w_acc = w_acc;
	}
	public String getW_pw() {
		return w_pw;
	}
	public void setW_pw(String w_pw) {
		this.w_pw = w_pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}	
	
}
