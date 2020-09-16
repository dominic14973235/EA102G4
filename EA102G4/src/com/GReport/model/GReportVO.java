package com.GReport.model;

import java.sql.Date;

public class GReportVO implements java.io.Serializable{
	private String g_report_no;
	private String mem_ID;
	private String g_class_no;
	private String r_content;
	private String r_status;
	private Date rTime;

	public String getG_report_no() {
		return g_report_no;
	}
	public void setG_report_no(String g_report_no) {
		this.g_report_no = g_report_no;
	}
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
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
	public Date getrTime() {
		return rTime;
	}
	public void setrTime(Date rTime) {
		this.rTime = rTime;
	}
	
	
}
