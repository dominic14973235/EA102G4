package com.product_photo.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.Part;


public class PhotoService {
	private PhotoDAO_interface dao;
	public PhotoService() {
		dao = new PhotoJDBCDAO();
	}

	public PhotoVO addPhoto(String productNo,byte[] pPhoto) {
		PhotoVO vo = new PhotoVO();
		vo.setProductNo(productNo);
		vo.setpPhoto(pPhoto);
		dao.insert(vo);
		return vo;
	}
	public PhotoVO get(String productNo) {
		return dao.get(productNo);
	}
	public PhotoVO getFirstPhoto(String productNo) {
		return dao.getFirstPhoto(productNo);
	}
	public List<PhotoVO> getAll() {
		return dao.getAll();
	}
	public List<PhotoVO> getOneProductAllPhoto(String productNo){
		return dao.getOneProductAllPhoto(productNo);
	}
	

}
