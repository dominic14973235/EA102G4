package com.classType.model;

import java.util.List;

public class ClassTypeService {
	private ClassTypeDAO_interface dao;

	public ClassTypeService() {
		dao = new ClassTypeDAO();
	}

	public ClassTypeVO addClassType(String t_name) {

		ClassTypeVO classTypeVO = new ClassTypeVO();

		classTypeVO.setT_name(t_name);
		dao.insert(classTypeVO);

		return classTypeVO;
	}

	public ClassTypeVO updateClassType(String c_type_no, String t_name) {

		ClassTypeVO classTypeVO = new ClassTypeVO();
		
		classTypeVO.setC_type_no(c_type_no);
		classTypeVO.setT_name(t_name);
		dao.update(classTypeVO);

		return classTypeVO;

	}

	public void deleteClassType(String c_type_no) {
		dao.delete(c_type_no);
	}

	public ClassTypeVO getOneClassType(String c_type_no) {
		return dao.findPrimaryKey(c_type_no);
	}

	public List<ClassTypeVO> getAll() {
		return dao.getAll();
	}
}
