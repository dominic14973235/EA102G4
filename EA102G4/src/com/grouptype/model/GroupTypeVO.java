package com.grouptype.model;

public class GroupTypeVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type_no;
	private String type_name;
	
	public GroupTypeVO() {}

	public String getType_no() {
		return type_no;
	}

	public void setType_no(String type_no) {
		this.type_no = type_no;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
}
