package com.groupmessage.model;

import java.util.List;

public class GroupMessageService {
	
	private GroupMessageDAO_interface dao;
	
	public GroupMessageService() {
		dao = new GroupMessageJDBC();
	}
	
	public void addMes(String gro_no, String mem_id, String mes_text) {
		GroupMessageVO gmVO = new GroupMessageVO();
		
		gmVO.setGro_no(gro_no);
		gmVO.setMem_id(mem_id);
		gmVO.setMes_text(mes_text);
		
		dao.insert(gmVO);
	}
	
	public List<GroupMessageVO> getAll(){
		return dao.getAll();
	}
	
	public List<GroupMessageVO> findMessageByGroNo(String gro_no) {
		return dao.findByPrimaryKey(gro_no);
	}
	
	public void deleteMessage(String mes_no) {
		dao.delete(mes_no);
	}
	
	public void answerMessage(String mes_res, String gro_no, String mem_id, String mes_text) {
		dao.updateReply(mes_res, gro_no, mem_id, mes_text);
	}
}
