package com.foodrecord.model;
import java.sql.Date;

public class FoodRecordVO implements java.io.Serializable{
	private String foodr_no;
	private String mem_id;
	private String food_no;
	private String foodr_time;
	private Date foodr_date;
	private Integer foodr_weight;
	public String getFoodr_no() {
		return foodr_no;
	}
	public void setFoodr_no(String foodr_no) {
		this.foodr_no = foodr_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getFood_no() {
		return food_no;
	}
	public void setFood_no(String food_no) {
		this.food_no = food_no;
	}
	public String getFoodr_time() {
		return foodr_time;
	}
	public void setFoodr_time(String foodr_time) {
		this.foodr_time = foodr_time;
	}
	public Date getFoodr_date() {
		return foodr_date;
	}
	public void setFoodr_date(Date foodr_date) {
		this.foodr_date = foodr_date;
	}
	public Integer getFoodr_weight() {
		return foodr_weight;
	}
	public void setFoodr_weight(Integer foodr_weight) {
		this.foodr_weight = foodr_weight;
	}
	
		
		
		

	
}
