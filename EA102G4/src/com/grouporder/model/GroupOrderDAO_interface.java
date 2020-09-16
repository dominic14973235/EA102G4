package com.grouporder.model;

import java.util.List;

import com.godetail.model.GroupOrderDetailVO;
import com.mem.model.MemVO;


public interface GroupOrderDAO_interface {
	public void insert(GroupOrderVO groupOrderVO);
    public GroupOrderVO findByPrimaryKey(String g_order_no);  
    public List<GroupOrderVO> getAllByMem_id(String mem_id);  //會員所有團課訂單
    public List<GroupOrderVO> getAll();					//所有團課訂單
    public String insertWithDetail(GroupOrderVO goVO, List<GroupOrderDetailVO> list,MemVO memVOLogin);

}
