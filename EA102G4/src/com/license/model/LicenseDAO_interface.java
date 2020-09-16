package com.license.model;

import java.sql.Connection;
import java.util.List;

public interface LicenseDAO_interface {
	
	public void insert(LicenseVO licenseVO);
	
	public void insert2(LicenseVO licenseVO, Connection con);

	public void update(LicenseVO licenseVO);

	public void delete(String license_no);

	public LicenseVO findPrimaryKey(String license_no);

	public List<LicenseVO> findByPro(String pro_ID);

	public List<LicenseVO> getAll();
	
	public  LicenseVO getPhoto (String license_no);
}
