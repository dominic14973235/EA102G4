package com.purchase_order.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PurchaseOrderVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String poNo;
	private String poPayment;
	private String poDelivery;
	private String deliveryAddress;
	private String poStatus;
	private String memId;
	private String purchaseDetail;
	private String productNo;
	private String buyerName;
	private String buyerPhone;
	private Double quantity;
	private Double pPrice;
	private Timestamp poTime;
	
	
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerPhone() {
		return buyerPhone;
	}
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public PurchaseOrderVO() {
		super();
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
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
	public String getPoStatus() {
		return poStatus;
	}
	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getPurchaseDetail() {
		return purchaseDetail;
	}
	public void setPurchaseDetail(String purchaseDetail) {
		this.purchaseDetail = purchaseDetail;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getpPrice() {
		return pPrice;
	}
	public void setpPrice(Double pPrice) {
		this.pPrice = pPrice;
	}
	public Timestamp getPoTime() {
		return poTime;
	}
	public void setPoTime(Timestamp poTime) {
		this.poTime = poTime;
	}



}
