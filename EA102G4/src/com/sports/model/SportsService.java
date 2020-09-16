package com.sports.model;

import java.util.List;


public class SportsService {
	
	private SportsDAO_interface dao;

	public SportsService() {
		dao = new SportsDAO();
	}
	
	public SportsVO addSports(String SPORT_NAME, String UNIT1, String UNIT2) {

		SportsVO sportsVO = new SportsVO();

		sportsVO.setSport_name(SPORT_NAME);
		sportsVO.setUnit1(UNIT1);
		sportsVO.setUnit2(UNIT2);
		
		dao.insert(sportsVO);

		return sportsVO;
	}

	public SportsVO updateSports(String SPORT_NAME, String UNIT1, String UNIT2, String SPORT_NO) {

		SportsVO sportsVO = new SportsVO();

		sportsVO.setSport_name(SPORT_NAME);
		sportsVO.setUnit1(UNIT1);
		sportsVO.setUnit2(UNIT2);
		sportsVO.setSport_no(SPORT_NO);
		
		dao.update(sportsVO);

		return sportsVO;
	}

	public void deleteSports(String sport_no) {
		dao.delete(sport_no);
	}

	public SportsVO getOneSports(String sport_no) {
		return dao.findByPrimaryKey(sport_no);
	}

	public List<SportsVO> getAll() {
		return dao.getAll();
	}
	
	
}
