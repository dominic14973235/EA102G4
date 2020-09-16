package com.classType.model;

import java.util.List;

import com.pro.model.ProVO;

public interface ClassTypeDAO_interface {
	public void insert(ClassTypeVO classTypeVO);

	public void update(ClassTypeVO classTypeVO);

	public void delete(String c_type_no);

	public ClassTypeVO findPrimaryKey(String c_type_no);

	public List<ClassTypeVO> getAll();

}