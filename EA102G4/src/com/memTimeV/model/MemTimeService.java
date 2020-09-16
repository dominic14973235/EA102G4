package com.memTimeV.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;


public class MemTimeService {
	
	private MemTimeDAO_interface dao;

	public MemTimeService() {
		dao = new MemTimeJDBCDAO();
	}
	
	public HashSet<MemTimeVO> getAllTimeForCheck(String mem_ID) {
		return dao.getAllTimeForCheck(mem_ID);
	}	
	
	public List<MemTimeVO> getAllTimeForShow(String mem_ID) {
		return dao.getAllTimeForShow(mem_ID);
	}
	
	 public List<MemTimeVO> getAllTimeByDate(String mem_ID, Date rDate){
		 
		return dao.getAllTimeByDate(mem_ID, rDate);
	}

}
