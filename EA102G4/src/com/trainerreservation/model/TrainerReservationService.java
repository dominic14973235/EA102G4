package com.trainerreservation.model;

import java.util.*;

public class TrainerReservationService {

	private TrainerReservationDAO_interface dao ;
	
	public TrainerReservationService() {
		dao = new  TrainerReservationDAO();
	}
	
	public HashSet<TrainerReservationVO> getAllForRetainByPro_id(String pro_id){
		return dao.getAllTimeForRetainByPro_id(pro_id);
	}
	public List<TrainerReservationVO> getAllForShowByPro_id(String pro_id){
		return dao.getAllTimeForShowByPro_id(pro_id);
	}
}
