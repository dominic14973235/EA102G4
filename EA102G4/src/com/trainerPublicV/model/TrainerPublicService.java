package com.trainerPublicV.model;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TrainerPublicService {
	
	private TrainerPublicDAO_interface dao;

	public TrainerPublicService() {
		dao = new TrainerPublicDAO();
	}
	
	public HashSet<TrainerPublicVO> getAllTimeForCheck(String pro_ID) {
		return dao.getAllTimeForCheck(pro_ID);
	}	
	
	public List<TrainerPublicVO> getAllTimeForShow(String pro_ID) {
		return dao.getAllTimeForShow(pro_ID);
	}
	
	public TrainerPublicVO getOneDay(String i_order_no) {
		return dao.getOneDay(i_order_no);
	}

	public List<TrainerPublicVO> getAllDay(Date rDate, String pro_ID) {
		return dao.getAllDay(rDate, pro_ID);
	}
	
}
