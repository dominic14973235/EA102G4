package com.article_rep.model;

import java.sql.Timestamp;

public class Article_RepVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 4528037819964346256L;

	private String art_rep_no;
	private String mem_id;
	private String article_no;
	private String rep_content;
	private Timestamp rep_time;
	private String com_status;
	
	public Article_RepVO() {
		
	}
	
	public Article_RepVO(String art_rep_no, String mem_id, String article_no, String rep_content, Timestamp rep_time,
			String com_status) {
		super();
		this.art_rep_no = art_rep_no;
		this.mem_id = mem_id;
		this.article_no = article_no;
		this.rep_content = rep_content;
		this.rep_time = rep_time;
		this.com_status = com_status;
	}
	
	public String getArt_rep_no() {
		return art_rep_no;
	}

	public void setArt_rep_no(String art_rep_no) {
		this.art_rep_no = art_rep_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getArticle_no() {
		return article_no;
	}

	public void setArticle_no(String article_no) {
		this.article_no = article_no;
	}

	public String getRep_content() {
		return rep_content;
	}

	public void setRep_content(String rep_content) {
		this.rep_content = rep_content;
	}

	public Timestamp getRep_time() {
		return rep_time;
	}

	public void setRep_time(Timestamp rep_time) {
		this.rep_time = rep_time;
	}

	public String getCom_status() {
		return com_status;
	}

	public void setCom_status(String com_status) {
		this.com_status = com_status;
	}

}
