package com.mem.model;

import java.util.List;


public class MemService {
	
	private MemDAO_interface dao;

	public MemService() {
		dao = new MemDAO();
	}
	
	public MemVO addMem(String MEM_NAME, String MEM_PSW, java.sql.Date MEM_BIR,
			String SEX, String MEM_ADDR, String MEM_EMAIL, String MEM_PHONE) {

		MemVO memVO = new MemVO();

		memVO.setMem_name(MEM_NAME);
		memVO.setMem_psw(MEM_PSW);
		memVO.setMem_bir(MEM_BIR);
		memVO.setSex(SEX);
		memVO.setMem_addr(MEM_ADDR);
		memVO.setMem_email(MEM_EMAIL);
		memVO.setMem_phone(MEM_PHONE);
		dao.insert(memVO);

		return memVO;
	}

	public MemVO updateMem(String MEM_NAME, String MEM_PSW, java.sql.Date MEM_BIR,
			String SEX, String MEM_ADDR, String MEM_EMAIL, String MEM_PHONE, int MEM_ABSENT, 
			int COIN, String MEM_RESUME, java.sql.Timestamp M_REG_DATE, String SEL_AUTH, String ART_AUTH, String COM_AUTH, String MEM_ID) {

		MemVO memVO = new MemVO();

		memVO.setMem_name(MEM_NAME);
		memVO.setMem_psw(MEM_PSW);
		memVO.setMem_bir(MEM_BIR);
		memVO.setSex(SEX);
		memVO.setMem_addr(MEM_ADDR);
		memVO.setMem_email(MEM_EMAIL);
		memVO.setMem_phone(MEM_PHONE);
		memVO.setMem_absent(MEM_ABSENT);
		memVO.setCoin(COIN);
		memVO.setMem_resume(MEM_RESUME);
		memVO.setM_reg_date(M_REG_DATE);
		memVO.setSel_auth(SEL_AUTH);
		memVO.setArt_auth(ART_AUTH);
		memVO.setCom_auth(COM_AUTH);
		memVO.setMem_id(MEM_ID);
		dao.update(memVO);

		return memVO;
	}
	
	public MemVO updateMemAuth(String SEL_AUTH, String ART_AUTH, String COM_AUTH, String MEM_ID) {

		MemVO memVO = new MemVO();

		memVO.setSel_auth(SEL_AUTH);
		memVO.setArt_auth(ART_AUTH);
		memVO.setCom_auth(COM_AUTH);
		memVO.setMem_id(MEM_ID);
		dao.updateMemAuth(memVO);

		return memVO;
	}
	

	public void deleteMem(String mem_id) {
		dao.delete(mem_id);
	}

	public MemVO getOneMem(String mem_id) {
		return dao.findByPrimaryKey(mem_id);
	}
	
	public MemVO getOneMemByMemAcc(String mem_email) {
		return dao.findByPrimaryKeyByMemAcc(mem_email);
	}
	
	public List<String> getAllEmail() {
		return dao.getAllEmail();
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}
	
	
}
