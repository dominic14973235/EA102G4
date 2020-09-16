package com.memphoto.model;

import java.util.List;


public class MemPhotoService {
	
	private MemPhotoDAO_interface dao;

	public MemPhotoService() {
		dao = new MemPhotoDAO();
	}
	
	public MemPhotoVO addMemPhoto(String MEM_ID, byte[] PHOTO) {

		MemPhotoVO memPhotoVO = new MemPhotoVO();
		
		memPhotoVO.setMem_id(MEM_ID);
		memPhotoVO.setPhoto(PHOTO);
		
		dao.insert(memPhotoVO);

		return memPhotoVO;
	}

	public void update(String PHOTO_NO) {

		MemPhotoVO memPhotoVO = new MemPhotoVO();

		memPhotoVO.setPhoto_no(PHOTO_NO);
		
		dao.update(memPhotoVO);
	}

	public void deleteMemPhoto(String photo_no) {
		dao.delete(photo_no);
	}

	
	public List<MemPhotoVO> getOneMemPhoto(String mem_id) {
		return dao.findByPrimaryKey(mem_id);
	}
	
	public MemPhotoVO getOneMemPhotoByStatus(String mem_id) {
		return dao.findByPrimaryKeyByStatus(mem_id);
	}
	
	public MemPhotoVO getOneMemPhotoPhotoNo(String photo_no) {
		return dao.findByPrimaryKeyPhotoNo(photo_no);
	}

	public List<MemPhotoVO> getAll() {
		return dao.getAll();
	}
	
	
}
