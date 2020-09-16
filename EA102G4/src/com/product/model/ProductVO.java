package com.product.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProductVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String productNo;
	private String pName;
	private String memId;
	private String categoryNo;
	private String pStatus;
	private String pDetail;
	private Double pPrice;
	private Double pStock;
	private Double pRating;
	private Double numberOfRating;
	private Timestamp pUploadTime;
	private String base64Image;
	private String poPayment ;
	private String poDelivery;
//	private byte[] p_photo;
	
	public String getBase64Image() {
		return base64Image;
	}

	public String getPoPayment() {
		return poPayment;
	}

	public void setPoPayment(String poPayment) {
		this.poPayment = poPayment;
	}

	public String getPoDelivery() {
		return poDelivery;
	}

	public void setPoDelivery(String poDelivery) {
		this.poDelivery = poDelivery;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public ProductVO() {
		super();
	}

	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getpStatus() {
		return pStatus;
	}
	public void setpStatus(String pStatus) {
		this.pStatus = pStatus;
	}
	public String getpDetail() {
		return pDetail;
	}
	public void setpDetail(String pDetail) {
		this.pDetail = pDetail;
	}
	public Double getpPrice() {
		return pPrice;
	}
	public void setpPrice(Double pPrice) {
		this.pPrice = pPrice;
	}
	public Double getpStock() {
		return pStock;
	}
	public void setpStock(Double pStock) {
		this.pStock = pStock;
	}
	public Double getpRating() {
		return pRating;
	}
	public void setpRating(Double pRating) {
		this.pRating = pRating;
	}
	public Double getNumberOfRating() {
		return numberOfRating;
	}
	public void setNumberOfRating(Double numberOfRating) {
		this.numberOfRating = numberOfRating;
	}
	public Timestamp getpUploadTime() {
		return pUploadTime;
	}
	public void setpUploadTime(Timestamp pUploadTime) {
		this.pUploadTime = pUploadTime;
	}



}
