package com.product_category.model;

import java.io.Serializable;

public class Product_categoryVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String categoryNo;
	private String categoryName;
	
	public Product_categoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
