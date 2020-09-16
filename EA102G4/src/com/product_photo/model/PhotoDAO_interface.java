package com.product_photo.model;

import java.util.List;

public interface PhotoDAO_interface {
	public void insert(PhotoVO photoVO); 
	public PhotoVO get(String productNo);
	public List<PhotoVO> getAll();
	public List<PhotoVO> getOneProductAllPhoto(String productNo);
	public PhotoVO getFirstPhoto(String product_no);
//	public PhotoVO update(PhotoVO photoVO); 
}
