package com.sports.model;

import java.sql.Date;

public class SportsVO implements java.io.Serializable{
	private String sport_no;
	private String sport_name;
	private String unit1;
	private String unit2;
	
	public String getSport_no() {
		return sport_no;
	}
	public void setSport_no(String sport_no) {
		this.sport_no = sport_no;
	}
	public String getSport_name() {
		return sport_name;
	}
	public void setSport_name(String sport_name) {
		this.sport_name = sport_name;
	}
	public String getUnit1() {
		return unit1;
	}
	public void setUnit1(String unit1) {
		this.unit1 = unit1;
	}
	public String getUnit2() {
		return unit2;
	}
	public void setUnit2(String unit2) {
		this.unit2 = unit2;
	}
	
}
