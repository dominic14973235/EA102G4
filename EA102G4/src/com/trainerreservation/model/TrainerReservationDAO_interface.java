package com.trainerreservation.model;

import java.util.*;
public interface TrainerReservationDAO_interface {
	 public HashSet<TrainerReservationVO> getAllTimeForRetainByPro_id(String pro_id);
	 public List<TrainerReservationVO> getAllTimeForShowByPro_id(String pro_id);
}
