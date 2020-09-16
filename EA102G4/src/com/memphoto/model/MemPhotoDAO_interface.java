package com.memphoto.model;

	import java.util.List;

	public interface MemPhotoDAO_interface {
	          public void insert(MemPhotoVO memPhotoVO);
	          public void update(MemPhotoVO memPhotoVO);
	          public void delete(String photo_no);
	          public List<MemPhotoVO> findByPrimaryKey(String mem_id);
	          public MemPhotoVO findByPrimaryKeyByStatus(String mem_id);
	          public MemPhotoVO findByPrimaryKeyPhotoNo(String photo_no);
	          public List<MemPhotoVO> getAll();
	          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//	        public List<BodyRecordVO> getAll(Map<String, String[]> map); 
	}

