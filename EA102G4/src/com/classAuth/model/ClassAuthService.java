package com.classAuth.model;

import java.util.List;

public class ClassAuthService {
	private ClassAuthDAO_interface dao;

	public ClassAuthService() {
		dao = new ClassAuthDAO();
	}

	public ClassAuthVO addClassAuth(String pro_ID, String c_type_no) {

		ClassAuthVO classAuthVO = new ClassAuthVO();
		
		classAuthVO.setPro_ID(pro_ID);
		classAuthVO.setC_type_no(c_type_no);
		dao.insert(classAuthVO);

		return classAuthVO;
	}

	public ClassAuthVO updateClassAuth(String auth_no, String pro_ID, String c_type_no) {

		ClassAuthVO classAuthVO = new ClassAuthVO();

		classAuthVO.setPro_ID(pro_ID);
		classAuthVO.setC_type_no(c_type_no);
		classAuthVO.setAuth_no(auth_no);
		dao.update(classAuthVO);

		return classAuthVO;

	}

	public void deleteClassAuth(String auth_no) {
		dao.delete(auth_no);
	}

	public ClassAuthVO getOneClassAuth(String auth_no) {
		return dao.findPrimaryKey(auth_no);
	}
	
	public List<ClassAuthVO> getAllFromOnePro(String pro_ID) {
		return dao.findForeignKey(pro_ID);
	}

	public List<ClassAuthVO> getAll() {
		return dao.getAll();
	}

	//for new proApplication
	public void addAuthBatch(List <ClassAuthVO> list){
		dao.insertBatch(list);
	}
	
	public void modifyAuthBatch(List <ClassAuthVO> caList) {
		dao.updateBatch(caList);
	}
} 
