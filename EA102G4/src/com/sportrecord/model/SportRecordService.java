package com.sportrecord.model;

import java.util.List;


public class SportRecordService {
	
	private SportRecordDAO_interface dao;

	public SportRecordService() {
		dao = new SportRecordDAO();
	}
	
	public SportRecordVO addSportRecord(String SPORT_NO, String MEM_ID, java.sql.Date RECORD_DATE, float RECORD1, float RECORD2) {

		SportRecordVO sportRecordVO = new SportRecordVO();

		sportRecordVO.setSport_no(SPORT_NO);
		sportRecordVO.setMem_id(MEM_ID);
		sportRecordVO.setRecord_date(RECORD_DATE);
		sportRecordVO.setRecord1(RECORD1);
		sportRecordVO.setRecord2(RECORD2);
		
		sportRecordVO = dao.insert(sportRecordVO);
		
		return sportRecordVO;
	}

	public SportRecordVO updateSportRecord(String SPORT_NO, String MEM_ID, java.sql.Date RECORD_DATE, float RECORD1, float RECORD2, String RECORD_NO) {

		SportRecordVO sportRecordVO = new SportRecordVO();

		sportRecordVO.setSport_no(SPORT_NO);
		sportRecordVO.setMem_id(MEM_ID);
		sportRecordVO.setRecord_date(RECORD_DATE);
		sportRecordVO.setRecord1(RECORD1);
		sportRecordVO.setRecord2(RECORD2);
		sportRecordVO.setRecord_no(RECORD_NO);
		
		dao.update(sportRecordVO);

		return sportRecordVO;
	}

	public void deleteSportRecord(String record_no) {
		dao.delete(record_no);
	}

	public List<SportRecordVO> getOneSportRecordByMemId(String mem_id) {
		return dao.findByPrimaryKeyMemId(mem_id);
	}
	
	public SportRecordVO getOneSportRecordByMemIdLast(String mem_id) {
		return dao.findByPrimaryKeyMemIdLast(mem_id);
	}
	
	public SportRecordVO getOneSportRecordByRecordNo(String record_no) {
		return dao.findByPrimaryKeyRecordNo(record_no);
	}

	public List<SportRecordVO> getAll() {
		return dao.getAll();
	}
	
	
}
