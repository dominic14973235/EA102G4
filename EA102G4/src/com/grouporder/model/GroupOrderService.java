package com.grouporder.model;

import java.util.*;

import com.godetail.model.GroupOrderDetailVO;
import com.mem.model.MemVO;

public class GroupOrderService {
	
	private GroupOrderDAO_interface dao;
	
	public GroupOrderService() {
		dao = new GroupOrderDAO();
	}
	
	public GroupOrderVO getOneGroupOrder(String g_order_no) {				
		return dao.findByPrimaryKey(g_order_no);
	}
	
	public List<GroupOrderVO> getAllByMem_id(String mem_id){
		return dao.getAllByMem_id(mem_id);
	}
	
	public List<GroupOrderVO> getAll(){
		return dao.getAll();
	}
	
	public  String insertWithDetail(GroupOrderVO goVO, List<GroupOrderDetailVO> list,MemVO memVOLogin) {
		return dao.insertWithDetail(goVO, list , memVOLogin);
	}
}
