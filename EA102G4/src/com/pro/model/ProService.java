package com.pro.model;

import java.util.List;

import com.classAuth.model.ClassAuthVO;
import com.license.model.LicenseVO;

public class ProService {
	private ProDAO_interface dao;

	public ProService() {
		dao = new ProDAO();
	}

	public void addPro(String mem_ID, Integer t_rating, Integer p_rating, Integer pro_auth,
			String expr) {

		ProVO proVO = new ProVO();

		proVO.setMem_ID(mem_ID);
		proVO.setT_rating(t_rating);
		proVO.setP_rating(p_rating);
		proVO.setPro_auth(pro_auth);
		proVO.setExpr(expr);
		dao.insert(proVO);

	}

	public void updatePro(String pro_ID, String mem_ID, Integer t_rating, Integer p_rating, Integer pro_auth,
			String expr) {

		ProVO proVO = new ProVO();

		proVO.setPro_ID(pro_ID);
		proVO.setMem_ID(mem_ID);
		proVO.setT_rating(t_rating);
		proVO.setP_rating(p_rating);
		proVO.setPro_auth(pro_auth);
		proVO.setExpr(expr);
		dao.update(proVO);

	}
	
	public void updateExp(String pro_ID, String expr) {

		ProVO proVO = new ProVO();

		proVO.setPro_ID(pro_ID);
		proVO.setExpr(expr);
		dao.updateExp(proVO);

	}
	
	public void deletePro(String pro_ID) {
		dao.delete(pro_ID);
	}

	public ProVO getOnePro(String pro_ID) {
		return dao.findPrimaryKey(pro_ID);
	}
	
	public ProVO getProByMem(String mem_ID) {
		return dao.findByMem(mem_ID);
	}

	public List<ProVO> getAll() {
		return dao.getAll();
	}

	public ProVO addPro1(String mem_ID, Integer t_rating, Integer p_rating, Integer pro_auth,
			String expr, List<LicenseVO> license) {

		ProVO proVO = new ProVO();

		proVO.setMem_ID(mem_ID);
		proVO.setT_rating(t_rating);
		proVO.setP_rating(p_rating);
		proVO.setPro_auth(pro_auth);
		proVO.setExpr(expr);
		dao.insertWithLicense(proVO, license);

		return proVO;
	}

	public void updateAuth(String pro_ID, Integer pro_auth) {
		dao.updateAuth(pro_ID,pro_auth);
	}
	
}
