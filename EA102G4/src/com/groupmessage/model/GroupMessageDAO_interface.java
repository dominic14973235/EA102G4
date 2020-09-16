package com.groupmessage.model;

import java.util.List;

public interface GroupMessageDAO_interface {
	public void insert(GroupMessageVO gmVO);
    public void updateReply(String mes_res, String gro_no, String mem_id, String mes_text);
    public void delete(String mes_no);
    public List<GroupMessageVO> findByPrimaryKey(String gro_no);
    public List<GroupMessageVO> getAll();
}
