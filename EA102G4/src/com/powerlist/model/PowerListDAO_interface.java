package com.powerlist.model;

import java.util.*;

import com.powerlist.model.PowerListVO;

public interface PowerListDAO_interface {
          public void insert(PowerListVO powerlistVO);
          public void update(PowerListVO powerlistVO);
          public void delete(PowerListVO powerlistVO);   
          public PowerListVO findByPrimaryKey(String power_id);
          public List<PowerListVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
