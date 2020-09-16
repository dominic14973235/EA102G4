package com.sportrecord.model;

public class SportRecordVO implements java.io.Serializable{
	private String record_no;
	private String sport_no;
	private String mem_id;
	private java.sql.Date record_date;
	private float record1;
	private float record2;
	
	public String getRecord_no() {
		return record_no;
	}
	public void setRecord_no(String record_no) {
		this.record_no = record_no;
	}
	public String getSport_no() {
		return sport_no;
	}
	public void setSport_no(String sport_no) {
		this.sport_no = sport_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public java.sql.Date getRecord_date() {
		return record_date;
	}
	public void setRecord_date(java.sql.Date record_date) {
		this.record_date = record_date;
	}
	public float getRecord1() {
		return record1;
	}
	public void setRecord1(float record1) {
		this.record1 = record1;
	}
	public float getRecord2() {
		return record2;
	}
	public void setRecord2(float record2) {
		this.record2 = record2;
	}
	
}
