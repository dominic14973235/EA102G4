package com.individualClass.model;

import java.util.List;

public class IndividualClassService {
	private IndividualClassDAO_interface dao;

	public IndividualClassService() {
		dao = new IndividualClassDAO();
	}

	public IndividualClassVO addIndividualClass(String pro_ID, String c_type_no, String c_name,
			String loc, String c_detail, byte[] c_pic, Integer c_status, Integer p_coin) {

		IndividualClassVO individualClassVO = new IndividualClassVO();

		individualClassVO.setPro_ID(pro_ID);
		individualClassVO.setC_type_no(c_type_no);
		individualClassVO.setC_name(c_name);
		individualClassVO.setLoc(loc);
		individualClassVO.setC_detail( c_detail);
		individualClassVO.setC_pic(c_pic);
		individualClassVO.setC_status(c_status);
		individualClassVO.setP_coin(p_coin);

		
		dao.insert(individualClassVO);

		return individualClassVO;
	}

	public IndividualClassVO updateIndividualClass(String i_class_no, String pro_ID, String c_type_no, String c_name,
			String loc, String c_detail, byte[] c_pic, Integer c_status, Integer p_coin) {

		IndividualClassVO individualClassVO = new IndividualClassVO();
		
		individualClassVO.setI_class_no(i_class_no);
		individualClassVO.setPro_ID(pro_ID);
		individualClassVO.setC_type_no(c_type_no);
		individualClassVO.setC_name(c_name);
		individualClassVO.setLoc(loc);
		individualClassVO.setC_detail( c_detail);
		individualClassVO.setC_pic(c_pic);
		individualClassVO.setC_status(c_status);
		individualClassVO.setP_coin(p_coin);
		dao.update(individualClassVO);

		return individualClassVO;

	}

	public void deleteIndividualClass(String i_class_no) {
		dao.delete(i_class_no);
	}

	public IndividualClassVO getOneIndividualClass(String i_class_no) {
		return dao.findPrimaryKey(i_class_no);
	}

	public List<IndividualClassVO> getAll() {
		return dao.getAll();
	}

	public List<IndividualClassVO> findByPro(String pro_ID) {
		return dao.findByPro(pro_ID);
	}
	
	public List<IndividualClassVO> findByTypeNo(String c_type_no) {
		return dao.findByTypeNo(c_type_no);
	}

}

