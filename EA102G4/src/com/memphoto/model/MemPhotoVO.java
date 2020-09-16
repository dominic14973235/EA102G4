package com.memphoto.model;

public class MemPhotoVO implements java.io.Serializable{
	private String photo_no;
	private String mem_id;
	private String photo_status;
	private byte[] photo;
	
	
	public String getPhoto_no() {
		return photo_no;
	}
	public void setPhoto_no(String photo_no) {
		this.photo_no = photo_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getPhoto_status() {
		return photo_status;
	}
	public void setPhoto_status(String photo_status) {
		this.photo_status = photo_status;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}
