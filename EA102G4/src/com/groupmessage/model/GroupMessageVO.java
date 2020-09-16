package com.groupmessage.model;

public class GroupMessageVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mes_no;
	private String mem_id;
	private String gro_no;
	private String mes_text;
	private String mes_res;
	private java.sql.Timestamp mes_time;
	
		
	public String getMes_res() {
		return mes_res;
	}
	public void setMes_res(String mes_res) {
		this.mes_res = mes_res;
	}
	public String getMes_no() {
		return mes_no;
	}
	public void setMes_no(String mes_no) {
		this.mes_no = mes_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getGro_no() {
		return gro_no;
	}
	public void setGro_no(String gro_no) {
		this.gro_no = gro_no;
	}
	public String getMes_text() {
		return mes_text;
	}
	public void setMes_text(String mes_text) {
		this.mes_text = mes_text;
	}
	public java.sql.Timestamp getMes_time() {
		return mes_time;
	}
	public void setMes_time(java.sql.Timestamp mes_time) {
		this.mes_time = mes_time;
	}
	@Override
	public String toString() {
		return "GroupMessageVO [mes_no=" + mes_no + ", mem_id=" + mem_id + ", gro_no=" + gro_no + ", mes_text="
				+ mes_text + ", mes_time=" + mes_time + "]";
	}
}
