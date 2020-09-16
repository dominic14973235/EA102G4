package com.license.model;

import java.util.List;

public class LicenseService {

	private LicenseDAO_interface dao;

	public LicenseService() {
		dao = new LicenseDAO();
	}
		
		public LicenseVO addLicense( String pro_ID, String lic_name, String no_reg,
				byte[] l_pic) {

			LicenseVO licenseVO = new LicenseVO();
			
			licenseVO.setPro_ID(pro_ID);
			licenseVO.setLic_name(lic_name);
			licenseVO.setNo_reg(no_reg);
			licenseVO.setL_pic(l_pic);
			dao.insert(licenseVO);

			return licenseVO;
		}

		public LicenseVO updateLicense(String license_no, String pro_ID, String lic_name, String no_reg,
				byte[] l_pic) {

			LicenseVO licenseVO = new LicenseVO();

			licenseVO.setLicense_no(license_no);
			licenseVO.setPro_ID(pro_ID);
			licenseVO.setLic_name(lic_name);
			licenseVO.setNo_reg(no_reg);
			licenseVO.setL_pic(l_pic);
			dao.insert(licenseVO);

			return licenseVO;

		}

		public void deleteLicense(String license_no) {
			dao.delete(license_no);
		}

		public LicenseVO getOneLicense(String license_no) {
			return dao.findPrimaryKey(license_no);
		}

		public List<LicenseVO> getAll() {
			return dao.getAll();
		}
		
		public List<LicenseVO> findByPro(String pro_ID) {
			return dao.findByPro(pro_ID);
		}

	}