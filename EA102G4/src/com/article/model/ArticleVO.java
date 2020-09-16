package com.article.model;

import java.sql.*;

public class ArticleVO implements java.io.Serializable {  
	
	private static final long serialVersionUID = 1L;
	private String article_no; 	       // 文章編號
	private String mem_id; 		      // 會員編號
	private String work_id; 	     // 員工編號
	private String title; 		    // 文章標題
	private String art_content;    // 文章內容
	private Timestamp a_release;  // 發佈時間
	private Integer link_count;  // 讚數
	private Integer com_count;  // 留言數
	private String a_status;   // 文章狀態

	public ArticleVO() {
		
	}
	
	public ArticleVO(String article_no, String mem_id, String work_id, String title,String art_content, Timestamp a_release,
			Integer link_count,Integer com_count,String a_status) {
		super();
		this.article_no = article_no;
		this.mem_id = mem_id;
		this.work_id = work_id;
		this.title = title;
		this.art_content = art_content;
		this.a_release = a_release;
		this.link_count = link_count;
		this.com_count = com_count;
		this.a_status = a_status;
		
	}
	
	public String getArticle_no() {
		return article_no;
	}

	public  void setArticle_no(String article_no) {
		this.article_no = article_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArt_content() {
		return art_content;
	}

	public void setArt_content(String art_content) {
		this.art_content = art_content;
	}

	public Integer getLink_count() {
		return link_count;
	}

	public void setLink_count(Integer link_count) {
		this.link_count = link_count;
	}

	public Integer getCom_count() {
		return com_count;
	}

	public void setCom_count(Integer com_count) {
		this.com_count = com_count;
	}

	public String getA_status() {
		return a_status;
	}

	public void setA_status(String a_status) {
		this.a_status = a_status;
	}

	public Timestamp getA_release() {
		return a_release;
	}

	public void setA_release(Timestamp a_release) {
		this.a_release = a_release;
	}
	
}
