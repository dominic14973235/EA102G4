package com.dayoff.model;

import java.sql.Date;
import java.util.List;

import com.pro.model.ProVO;

public interface DayoffDAO_interface {
	public void insert(DayoffVO dayoffVO);
	public void update(DayoffVO dayoffVO);
	public void delete(String ctime_no);
	public DayoffVO findPrimaryKey(String ctime_no);
	public List<DayoffVO> findByProID(String pro_ID);
    public List<DayoffVO> getAll();
    public DayoffVO findByDateWithPro(String pro_ID, Date close_date);
}
