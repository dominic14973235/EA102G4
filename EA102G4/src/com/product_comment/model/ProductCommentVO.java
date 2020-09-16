package com.product_comment.model;

import java.io.Serializable;

public class ProductCommentVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String productCommentNo;
	private String memIdBuyer;
	private String memIdSeller;
	private String productNo;
	private String productComment;
	private String pRatingEach;
	
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductCommentNo() {
		return productCommentNo;
	}
	public void setProductCommentNo(String productCommentNo) {
		this.productCommentNo = productCommentNo;
	}
	public String getProductComment() {
		return productComment;
	}
	public void setProductComment(String productComment) {
		this.productComment = productComment;
	}
	public String getMemIdBuyer() {
		return memIdBuyer;
	}
	public void setMemIdBuyer(String memIdBuyer) {
		this.memIdBuyer = memIdBuyer;
	}
	public String getMemIdSeller() {
		return memIdSeller;
	}
	public void setMemIdSeller(String memIdSeller) {
		this.memIdSeller = memIdSeller;
	}
	public String getpRatingEach() {
		return pRatingEach;
	}
	public void setpRatingEach(String pRatingEach) {
		this.pRatingEach = pRatingEach;
	}

	

}
