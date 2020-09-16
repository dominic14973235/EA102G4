package com.gclassreport.model;

public class GroupClassReportVO implements java.io.Serializable{
	
	private String g_report_no;
	private String mem_id;
	private String g_class_no;
	private String r_content;
	private String r_status;
	private java.sql.Timestamp rtime;

	public String getG_report_no() {
		return g_report_no;
	}
	public void setG_report_no(String g_report_no) {
		this.g_report_no = g_report_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getG_class_no() {
		return g_class_no;
	}
	public void setG_class_no(String g_class_no) {
		this.g_class_no = g_class_no;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public String getR_status() {
		return r_status;
	}
	public void setR_status(String r_status) {
		this.r_status = r_status;
	}
	public java.sql.Timestamp getRtime() {
		return rtime;
	}
	public void setRtime(java.sql.Timestamp rtime) {
		this.rtime = rtime;
	}

	
	
}
