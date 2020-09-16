package com.foodrecord.model;

import java.sql.Date;
import java.util.*;

public interface FoodRecordDAO_interface {
          public void insert(FoodRecordVO foodrecordVO);
          public void update(FoodRecordVO foodrecordVO);
          public void delete(String foodr_no);
          public FoodRecordVO findByPrimaryKey(String foodr_no);
          public List<FoodRecordVO> getAllByMem(String mem_id);
          public List<FoodRecordVO> getAllByDate(Date foodr_date, String mem_id);
          public List<FoodRecordVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
