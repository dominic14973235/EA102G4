package com.powerlist.model;

public class PowerListVO implements java.io.Serializable{
	private String power_id;
	private String power_name;
	public String getPower_id() {
		return power_id;
	}
	public void setPower_id(String power_id) {
		this.power_id = power_id;
	}
	public String getPower_name() {
		return power_name;
	}
	public void setPower_name(String power_name) {
		this.power_name = power_name;
	}
}
