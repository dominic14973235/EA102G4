package com.dayoff.model;

import java.sql.Date;

public class DayoffVO implements java.io.Serializable{
	
	private String ctime_no;
	private String pro_ID;
	private String mem_ID;
	private Date close_date;
	private String hr;
	
	public String getCtime_no() {
		return ctime_no;
	}
	public void setCtime_no(String ctime_no) {
		this.ctime_no = ctime_no;
	}
	public String getPro_ID() {
		return pro_ID;
	}
	public void setPro_ID(String pro_ID) {
		this.pro_ID = pro_ID;
	}
	
	public String getMem_ID() {
		return mem_ID;
	}
	public void setMem_ID(String mem_ID) {
		this.mem_ID = mem_ID;
	}
	
	public Date getClose_date() {
		return close_date;
	}
	public void setClose_date(Date close_date) {
		this.close_date = close_date;
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
		result = prime * result + ((close_date == null) ? 0 : close_date.hashCode());
		result = prime * result + ((hr == null) ? 0 : hr.hashCode());
		result = prime * result + ((mem_ID == null) ? 0 : mem_ID.hashCode());
		result = prime * result + ((pro_ID == null) ? 0 : pro_ID.hashCode());
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
		DayoffVO other = (DayoffVO) obj;
		if (close_date == null) {
			if (other.close_date != null)
				return false;
		} else if (!close_date.equals(other.close_date))
			return false;
		if (hr == null) {
			if (other.hr != null)
				return false;
		} else if (!hr.equals(other.hr))
			return false;
		if (mem_ID == null) {
			if (other.mem_ID != null)
				return false;
		} else if (!mem_ID.equals(other.mem_ID))
			return false;
		if (pro_ID == null) {
			if (other.pro_ID != null)
				return false;
		} else if (!pro_ID.equals(other.pro_ID))
			return false;
		return true;
	}
	
	
	
}
