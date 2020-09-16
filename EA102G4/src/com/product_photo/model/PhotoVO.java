package com.product_photo.model;

import java.io.Serializable;

public class PhotoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String photoNo;
	private byte[] pPhoto;
	private String productNo;
	
	public PhotoVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 public String getPhotoNo() {
		return photoNo;
	}

	public void setPhotoNo(String photoNo) {
		this.photoNo = photoNo;
	}

	public byte[] getpPhoto() {
		return pPhoto;
	}

	public void setpPhoto(byte[] pPhoto) {
		this.pPhoto = pPhoto;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	private String base64Image;
     
	    public String getBase64Image() {
	        return base64Image;
	    }
	 
	    public void setBase64Image(String base64Image) {
	        this.base64Image = base64Image;
	    }
	
}
