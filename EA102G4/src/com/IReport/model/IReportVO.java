package com.IReport.model;

import java.sql.Date;

public class IReportVO implements java.io.Serializable{
	private String i_report_no;
	private String mem_ID;
	private String i_class_no;
	private String r_content;
	private String r_status;
	private Date rTime;

	public String getI_report_no() {
		return i_report_no;
	}
	public void setI_report_no(String i_report_no) {
		this.i_report_no = i_report_no;
	}
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	public String getI_class_no() {
		return i_class_no;
	}
	public void setI_class_no(String i_class_no) {
		this.i_class_no = i_class_no;
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
