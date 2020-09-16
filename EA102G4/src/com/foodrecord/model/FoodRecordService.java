package com.foodrecord.model;

import java.sql.Date;
import java.util.List;

public class FoodRecordService {

	private FoodRecordDAO_interface dao;

	public FoodRecordService() {
		dao = new FoodRecordJNDIDAO();
	}

	public FoodRecordVO addFoodRecord(String mem_id, String food_no, String foodr_time,
			Date foodr_date, Integer foodr_weight) {

		FoodRecordVO foodrecordVO = new FoodRecordVO();

		foodrecordVO.setMem_id(mem_id);
		foodrecordVO.setFood_no(food_no);
		foodrecordVO.setFoodr_time(foodr_time);
		foodrecordVO.setFoodr_date(foodr_date);
		foodrecordVO.setFoodr_weight(foodr_weight);
		dao.insert(foodrecordVO);

		return foodrecordVO;
	}

	public FoodRecordVO updateFoodRecord(String mem_id, String food_no, String foodr_time,
			java.sql.Date foodr_date, Integer foodr_weight, String foodr_no) {

		FoodRecordVO foodrecordVO = new FoodRecordVO();
		foodrecordVO.setMem_id(mem_id);
		foodrecordVO.setFood_no(food_no);
		foodrecordVO.setFoodr_time(foodr_time);
		foodrecordVO.setFoodr_date(foodr_date);
		foodrecordVO.setFoodr_weight(foodr_weight);
		foodrecordVO.setFoodr_no(foodr_no);
		dao.update(foodrecordVO);

		return foodrecordVO;
	}

	public FoodRecordVO getOneFoodRecord(String foodr_no) {
		return dao.findByPrimaryKey(foodr_no);
	}
	
	public void deleteFoodRecord(String foodr_no) {
		dao.delete(foodr_no);
	}

	public List<FoodRecordVO> getAllByMem(String mem_id) {
		return dao.getAllByMem(mem_id);
	}

	public List<FoodRecordVO> getAllByDate(Date foodr_date, String mem_id) {
		return dao.getAllByDate(foodr_date, mem_id);
	}
	
	public List<FoodRecordVO> getAll() {
		return dao.getAll();
	}
}
