package com.workpower.model;

import java.util.*;

public interface WorkPowerDAO_interface {
          public void insert(WorkPowerVO workerpowerVO);
          public void update(WorkPowerVO workerpowerVO);
          public void deleteAllByWork(String work_id);
          public List<WorkPowerVO> getByWorker(String work_id);
          public List<WorkPowerVO> getByPower(String power_id);
          public List<WorkPowerVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
