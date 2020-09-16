package com.bodyrecord.model;

import java.util.List;


public class BodyRecordService {
	
	private BodyRecordDAO_interface dao;

	public BodyRecordService() {
		dao = new BodyRecordDAO();
	}
	
	public BodyRecordVO addBodyRecord(String MEM_ID, float MEM_HEIGHT, float MEM_WEIGHT,float MEM_BMI, int MEM_OLD, float MEM_BMR, int FREQUENCY, float MEM_TDEE, java.sql.Date BODY_DATE,
			byte[] PHOTO) {

		BodyRecordVO bodyRecordVO = new BodyRecordVO();

		bodyRecordVO.setMem_id(MEM_ID);
		bodyRecordVO.setMem_height(MEM_HEIGHT);
		bodyRecordVO.setMem_weight(MEM_WEIGHT);
		bodyRecordVO.setMem_bmi(MEM_BMI);
		bodyRecordVO.setMem_old(MEM_OLD);
		bodyRecordVO.setMem_bmr(MEM_BMR);
		bodyRecordVO.setFrequency(FREQUENCY);
		bodyRecordVO.setMem_tdee(MEM_TDEE);
		bodyRecordVO.setBody_date(BODY_DATE);
		bodyRecordVO.setPhoto(PHOTO);
		dao.insert(bodyRecordVO);

		return bodyRecordVO;
	}

	public BodyRecordVO updateBodyRecord(String MEM_ID, float MEM_HEIGHT, float MEM_WEIGHT,float MEM_BMI, int MEM_OLD, float MEM_BMR, int FREQUENCY, float MEM_TDEE, java.sql.Date BODY_DATE,
			byte[] PHOTO, String BODY_NO) {

		BodyRecordVO bodyRecordVO = new BodyRecordVO();

		bodyRecordVO.setMem_id(MEM_ID);
		bodyRecordVO.setMem_height(MEM_HEIGHT);
		bodyRecordVO.setMem_weight(MEM_WEIGHT);
		bodyRecordVO.setMem_bmi(MEM_BMI);
		bodyRecordVO.setMem_old(MEM_OLD);
		bodyRecordVO.setMem_bmr(MEM_BMR);
		bodyRecordVO.setFrequency(FREQUENCY);
		bodyRecordVO.setMem_tdee(MEM_TDEE);
		bodyRecordVO.setBody_date(BODY_DATE);
		bodyRecordVO.setPhoto(PHOTO);
		bodyRecordVO.setBody_no(BODY_NO);
		
		dao.update(bodyRecordVO);

		return bodyRecordVO;
	}

	public void deleteBodyRecord(String body_no) {
		dao.delete(body_no);
	}

	public List<BodyRecordVO> getOneBodyRecordByMemId(String mem_id) {
		return dao.findByPrimaryKeyMemId(mem_id);
	}
	
	public BodyRecordVO getOneBodyRecordByMemIdLast(String mem_id) {
		return dao.findByPrimaryKeyMemIdLast(mem_id);
	}
	
	public BodyRecordVO getOneBodyRecordByBodyNo(String body_no) {
		return dao.findByPrimaryKeyBodyNo(body_no);
	}

	public List<BodyRecordVO> getAll() {
		return dao.getAll();
	}
	
	
}
