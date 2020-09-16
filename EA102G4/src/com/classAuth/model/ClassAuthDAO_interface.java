package com.classAuth.model;

import java.sql.Connection;
import java.util.*;

public interface ClassAuthDAO_interface {

	public void insert(ClassAuthVO classAuthVO);

	public void update(ClassAuthVO classAuthVO);

	public void delete(String auth_no);

	public ClassAuthVO findPrimaryKey(String auth_no);

	public List<ClassAuthVO> findForeignKey(String pro_ID);

	public List<ClassAuthVO> getAll();
	
	public void insertBatch(List<ClassAuthVO> list);
	
	public void updateBatch(List<ClassAuthVO> caList);
}
