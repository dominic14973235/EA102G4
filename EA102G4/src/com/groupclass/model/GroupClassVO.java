package com.groupclass.model;

public class GroupClassVO implements java.io.Serializable{
	private String g_class_no;
	private String pro_id;
	private String c_type_no;
	private String g_name;
	private String loc;
	private Integer g_max;
	private Integer p_coin;
	private String g_detail;
	private Integer c_status;
	private byte[] g_pic;
	
	
	public Integer getC_status() {
		return c_status;
	}
	public void setC_status(Integer c_status) {
		this.c_status = c_status;
	}
	public String getG_class_no() {
		return g_class_no;
	}
	public void setG_class_no(String g_class_no) {
		this.g_class_no = g_class_no;
	}
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	public String getC_type_no() {
		return c_type_no;
	}
	public void setC_type_no(String c_type_no) {
		this.c_type_no = c_type_no;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public Integer getG_max() {
		return g_max;
	}
	public void setG_max(Integer g_max) {
		this.g_max = g_max;
	}
	public Integer getP_coin() {
		return p_coin;
	}
	public void setP_coin(Integer p_coin) {
		this.p_coin = p_coin;
	}
	public String getG_detail() {
		return g_detail;
	}
	public void setG_detail(String g_detail) {
		this.g_detail = g_detail;
	}
	public byte[] getG_pic() {
		return g_pic;
	}
	public void setG_pic(byte[] g_pic) {
		this.g_pic = g_pic;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((g_class_no == null) ? 0 : g_class_no.hashCode());
		result = prime * result + ((g_max == null) ? 0 : g_max.hashCode());
		result = prime * result + ((p_coin == null) ? 0 : p_coin.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupClassVO other = (GroupClassVO) obj;
		if (g_class_no == null) {
			if (other.g_class_no != null)
				return false;
		} else if (!g_class_no.equals(other.g_class_no))
			return false;
		if (g_max == null) {
			if (other.g_max != null)
				return false;
		} else if (!g_max.equals(other.g_max))
			return false;
		if (p_coin == null) {
			if (other.p_coin != null)
				return false;
		} else if (!p_coin.equals(other.p_coin))
			return false;
		return true;
	}
	
}
