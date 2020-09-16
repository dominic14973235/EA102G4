package com.bodyrecord.model;

public class BodyRecordVO implements java.io.Serializable{
	private String body_no;
	private String mem_id;
	private float mem_height;
	private float mem_weight;
	private float mem_bmi;
	private int mem_old;
	private float mem_bmr;
	private int frequency;
	private float mem_tdee;
	private java.sql.Date body_date;
	private byte[] photo;
	private String sex;
	
	public String getBody_no() {
		return body_no;
	}
	public void setBody_no(String body_no) {
		this.body_no = body_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public float getMem_height() {
		return mem_height;
	}
	public void setMem_height(float mem_height) {
		this.mem_height = mem_height;
	}
	public float getMem_weight() {
		return mem_weight;
	}
	public void setMem_weight(float mem_weight) {
		this.mem_weight = mem_weight;
	}
	public float getMem_bmi() {
		return mem_bmi;
	}
	public void setMem_bmi(float mem_bmi) {
		this.mem_bmi = mem_bmi;
	}
	public int getMem_old() {
		return mem_old;
	}
	public void setMem_old(int mem_old) {
		this.mem_old = mem_old;
	}
	public float getMem_bmr() {
		return mem_bmr;
	}
	public void setMem_bmr(float mem_bmr) {
		this.mem_bmr = mem_bmr;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public float getMem_tdee() {
		return mem_tdee;
	}
	public void setMem_tdee(float mem_tdee) {
		this.mem_tdee = mem_tdee;
	}
	public java.sql.Date getBody_date() {
		return body_date;
	}
	public void setBody_date(java.sql.Date body_date) {
		this.body_date = body_date;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
