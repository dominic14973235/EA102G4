package com.product_tracking.model;

import java.io.Serializable;

public class ProductTrackingVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String memId;
	private String productNo;
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	

}
