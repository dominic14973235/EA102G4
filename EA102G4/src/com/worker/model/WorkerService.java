package com.worker.model;

import java.util.List;

public class WorkerService {

	private WorkerDAO_interface dao;

	public WorkerService() {
		dao = new WorkerJNDIDAO();
	}

	public WorkerVO addEmp(String w_name, String w_acc, String w_pw,
			String email, java.sql.Date reg_date, byte[] photo) {

		WorkerVO empVO = new WorkerVO();

		empVO.setW_name(w_name);
		empVO.setW_acc(w_acc);
		empVO.setW_pw(w_pw);
		empVO.setEmail(email);
		empVO.setReg_date(reg_date);
		empVO.setPhoto(photo);
		dao.insert(empVO);

		return empVO;
	}

	public WorkerVO updateEmp(String work_id, String w_name, String w_acc, String w_pw,
			String email, java.sql.Date reg_date, byte[] photo) {

		WorkerVO empVO = new WorkerVO();
		
		empVO.setWork_id(work_id);
		empVO.setW_name(w_name);
		empVO.setW_acc(w_acc);
		empVO.setW_pw(w_pw);
		empVO.setEmail(email);
		empVO.setReg_date(reg_date);
		empVO.setPhoto(photo);
		dao.update(empVO);

		return empVO;
	}

	public void updatePw(String work_id, String w_pw) {
		dao.updatePw(work_id, w_pw);
	}
	
	public void deleteEmp(String work_id) {
		dao.delete(work_id);
	}

	public WorkerVO getOneEmp(String work_id) {
		return dao.findByPrimaryKey(work_id);
	}
	
	public WorkerVO getOneByAcc(String w_acc) {
		return dao.findByAcc(w_acc);
	}

	public List<WorkerVO> getAll() {
		return dao.getAll();
	}
}
