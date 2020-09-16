package com.dayoff.model;

import java.sql.Date;
import java.util.List;

public class DayoffService {
	private DayoffDAO_interface dao;

	public DayoffService() {
		dao = new DayoffDAO();
	}

	public DayoffVO addDayoff( String pro_ID, String mem_ID, Date close_date, String hr) {

		DayoffVO dayoffVO = new DayoffVO();

		dayoffVO.setPro_ID(pro_ID);
		dayoffVO.setMem_ID(mem_ID);
		dayoffVO.setClose_date(close_date);
		dayoffVO.setHr(hr);
		dao.insert(dayoffVO);

		return dayoffVO;
	}

	public DayoffVO updateDayoff(String ctime_no, String pro_ID, String mem_ID, Date close_date, String hr) {

		DayoffVO dayoffVO = new DayoffVO();

		dayoffVO.setCtime_no(ctime_no);
		dayoffVO.setPro_ID(pro_ID);
		dayoffVO.setMem_ID(mem_ID);
		dayoffVO.setClose_date(close_date);
		dayoffVO.setHr(hr);
		dao.update(dayoffVO);

		return dayoffVO;

	}

	public void deleteDayoff(String ctime_no) {
		dao.delete(ctime_no);
	}

	public DayoffVO getOneDayoff(String ctime_no) {
		return dao.findPrimaryKey(ctime_no);
	}
	
	public  List<DayoffVO>  getOneProDayoff(String pro_ID) {
		return dao.findByProID(pro_ID);
	}

	public List<DayoffVO> getAll() {
		return dao.getAll();
	}

	public DayoffVO getDateByProAndDate(String pro_ID, Date close_date) {
		return dao.findByDateWithPro(pro_ID, close_date);
	}
}
