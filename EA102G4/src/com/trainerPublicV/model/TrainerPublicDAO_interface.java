package com.trainerPublicV.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;

public interface TrainerPublicDAO_interface {
	 public HashSet<TrainerPublicVO> getAllTimeForCheck(String pro_ID);
	 public List<TrainerPublicVO> getAllTimeForShow(String pro_ID);
	 public TrainerPublicVO getOneDay(String i_order_no);
	 public List<TrainerPublicVO> getAllDay(Date rDate, String pro_ID);

}
