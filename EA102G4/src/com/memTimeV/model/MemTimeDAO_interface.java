package com.memTimeV.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;

public interface MemTimeDAO_interface {
	 public HashSet<MemTimeVO> getAllTimeForCheck(String mem_ID);
	 
	 public List<MemTimeVO> getAllTimeForShow(String mem_ID);

	 public List<MemTimeVO> getAllTimeByDate(String mem_ID, Date rDate);

}
