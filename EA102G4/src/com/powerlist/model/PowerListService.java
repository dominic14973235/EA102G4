package com.powerlist.model;

import java.util.List;

public class PowerListService {

	private PowerListDAO_interface dao;

	public PowerListService() {
		dao = new PowerListJDBCDAO();
	}

	public PowerListVO getPowername(String power_id) {
		return dao.findByPrimaryKey(power_id);
	}

	public List<PowerListVO> getAll() {
		return dao.getAll();
	}
}
