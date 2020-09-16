package com.trainerreservation.model;

public class TrainerReservationVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String i_order_no;
	private String i_class_no;
	private String pro_id;
	private java.sql.Date rdate;
	private String hr;
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hr == null) ? 0 : hr.hashCode());
		result = prime * result + ((rdate == null) ? 0 : rdate.hashCode());
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
		TrainerReservationVO other = (TrainerReservationVO) obj;
		if (hr == null) {
			if (other.hr != null)
				return false;
		} else if (!hr.equals(other.hr))
			return false;
		if (rdate == null) {
			if (other.rdate != null)
				return false;
		} else if (!rdate.equals(other.rdate))
			return false;
		return true;
	}
	public String getI_order_no() {
		return i_order_no;
	}
	public void setI_order_no(String i_order_no) {
		this.i_order_no = i_order_no;
	}
	public String getI_class_no() {
		return i_class_no;
	}
	public void setI_class_no(String i_class_no) {
		this.i_class_no = i_class_no;
	}
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	public java.sql.Date getRdate() {
		return rdate;
	}
	public void setRdate(java.sql.Date rdate) {
		this.rdate = rdate;
	}
	public String getHr() {
		return hr;
	}
	public void setHr(String hr) {
		this.hr = hr;
	}
	
	
}
