package com.foods.model;

public class FoodVO implements java.io.Serializable{
	private String food_no;
	private String food_name;
	public Double starch;
	public Double protein;
	public Double fat;
	public Double kcal;
	public String getFood_no() {
		return food_no;
	}
	public void setFood_no(String food_no) {
		this.food_no = food_no;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public Double getStarch() {
		return starch;
	}
	public void setStarch(Double starch) {
		this.starch = starch;
	}
	public Double getProtein() {
		return protein;
	}
	public void setProtein(Double protein) {
		this.protein = protein;
	}
	public Double getFat() {
		return fat;
	}
	public void setFat(Double fat) {
		this.fat = fat;
	}
	public Double getKcal() {
		return kcal;
	}
	public void setKcal(Double kcal) {
		this.kcal = kcal;
	}	
}
