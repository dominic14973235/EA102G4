package com.pro.model;

import java.util.*;

import com.classAuth.model.ClassAuthVO;
import com.license.model.LicenseVO;

public interface ProDAO_interface {
	
	public void insert(ProVO proVO);
	
	public void insertWithLicense(ProVO proVO,  List<LicenseVO> license);

	public void update(ProVO proVO);

	public void updateExp(ProVO proVO);
	
	public void updateAuth(String pro_ID, Integer pro_auth);
	
	public void delete(String pro_ID);

	public ProVO findPrimaryKey(String pro_ID);

	public ProVO findByMem(String mem_ID);
	
	public List<ProVO> getAll();

}
