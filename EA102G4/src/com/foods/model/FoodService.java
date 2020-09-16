package com.foods.model;

import java.util.List;
import com.foodrecord.model.*;

public class FoodService {

	private FoodDAO_interface dao;

	public FoodService() {
		dao = new FoodJNDIDAO();
	}

	public FoodVO addFood(String food_name, Double starch, Double protein, Double fat, Double kcal) {

		FoodVO foodVO = new FoodVO();
		foodVO.setFood_name(food_name);
		foodVO.setStarch(starch);		
		foodVO.setProtein(protein);		
		foodVO.setFat(fat);		
		foodVO.setKcal(kcal);		
		dao.insert(foodVO);

		return foodVO;
	}

	public void delete(String food_no) {
		dao.delete(food_no);
	}
	
	public FoodVO update(String food_no, String food_name, Double starch, Double protein, Double fat, Double kcal) {
		FoodVO foodVO = new FoodVO();
		foodVO.setFood_no(food_no);
		foodVO.setFood_name(food_name);
		foodVO.setStarch(starch);		
		foodVO.setProtein(protein);		
		foodVO.setFat(fat);		
		foodVO.setKcal(kcal);		
		dao.update(foodVO);
		
		return foodVO;
	}
	
	public FoodVO getOneFood(String food_no) {
		return dao.findByPrimaryKey(food_no);
	}
	
	public FoodVO getOneByName(String food_name) {
		return dao.getFood_no(food_name);
	}
	
	public Double[] getAllNu(List<FoodRecordVO> list) {
		return dao.getAllNu(list);
	}

	public List<FoodVO> getAll() {
		return dao.getAll();
	}
}
