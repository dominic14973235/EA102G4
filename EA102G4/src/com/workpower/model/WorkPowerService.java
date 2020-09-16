package com.workpower.model;

import java.util.List;

public class WorkPowerService {

	private WorkPowerDAO_interface dao;

	public WorkPowerService() {
		dao = new WorkPowerJDBCDAO();
	}

	public WorkPowerVO addWorkPower(String work_id, String power_id) {

		WorkPowerVO workerpowerVO = new WorkPowerVO();
		workerpowerVO.setWork_id(work_id);
		workerpowerVO.setPower_id(power_id);		
		dao.insert(workerpowerVO);

		return workerpowerVO;
	}

	public void delete(String work_id) {
		dao.deleteAllByWork(work_id);
	}

	public List<WorkPowerVO> getByWorker(String work_id) {
		return dao.getByWorker(work_id);
	}
	
	public List<WorkPowerVO> getByPower(String power_id) {
		return dao.getByPower(power_id);
	}

	public List<WorkPowerVO> getAll() {
		return dao.getAll();
	}
}
