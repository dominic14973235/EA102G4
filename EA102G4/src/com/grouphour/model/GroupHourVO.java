package com.grouphour.model;

public class GroupHourVO {

	private String g_time_no;
	private String g_class_no;
	private java.sql.Date c_date;
	private String hr;
	public String getG_time_no() {
		return g_time_no;
	}
	public void setG_time_no(String g_time_no) {
		this.g_time_no = g_time_no;
	}
	public String getG_class_no() {
		return g_class_no;
	}
	public void setG_class_no(String g_class_no) {
		this.g_class_no = g_class_no;
	}
	public java.sql.Date getC_date() {
		return c_date;
	}
	public void setC_date(java.sql.Date c_date) {
		this.c_date = c_date;
	}
	public String getHr() {
		return hr;
	}
	public void setHr(String hr) {
		this.hr = hr;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c_date == null) ? 0 : c_date.hashCode());
		result = prime * result + ((hr == null) ? 0 : hr.hashCode());
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
		GroupHourVO other = (GroupHourVO) obj;
		if (c_date == null) {
			if (other.c_date != null)
				return false;
		} else if (!c_date.equals(other.c_date))
			return false;
		if (hr == null) {
			if (other.hr != null)
				return false;
		} else if (!hr.equals(other.hr))
			return false;
		return true;
	}
	
	
}
