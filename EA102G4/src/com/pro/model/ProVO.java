package com.pro.model;

public class ProVO implements java.io.Serializable{
	private String pro_ID;
	private String mem_ID;
	private Integer t_rating;
	private Integer p_rating;
	private Integer pro_auth;
	private String expr;
	
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
	public Integer getT_rating() {
		return t_rating;
	}
	public void setT_rating(Integer t_rating) {
		this.t_rating = t_rating;
	}
	public Integer getP_rating() {
		return p_rating;
	}
	public void setP_rating(Integer p_rating) {
		this.p_rating = p_rating;
	}

	public Integer getPro_auth() {
		return pro_auth;
	}
	public void setPro_auth(Integer pro_auth) {
		this.pro_auth = pro_auth;
	}
	public String getExpr() {
		return expr;
	}
	public void setExpr(String expr) {
		this.expr = expr;
	}

	
}
