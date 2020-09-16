package com.godetail.model;

import java.util.List;

public class GroupOrderDetailService {
	
	private GroupOrderDetailDAO_interface dao;
	
	public GroupOrderDetailService() {
		dao = new GroupOrderDetailDAO();
	}
	
	public void update (GroupOrderDetailVO godVO) {
		dao.update(godVO);
	}
	
	public GroupOrderDetailVO findByPrimaryKey(String g_order_no,String g_time_no) {
		return dao.findByPrimaryKey(g_order_no, g_time_no);
	}
	
	public  List<GroupOrderDetailVO> getAllByG_class_no(String g_class_no,Integer go_status) {
		return dao.getAllByG_class_no(g_class_no, go_status);	
	}
	
	public List<GroupOrderDetailVO> getAll() {
		return dao.getAll();
	}
	  								
	public  List<GroupOrderDetailVO> getDetailsByOrder(String g_order_no,Integer go_status) {
		return dao.getDetailsByOrder(g_order_no, go_status);
	}
	
	public List<GroupOrderDetailVO> getDetailsByGTimeNo(String g_time_no,Integer go_status) {
		return dao.getDetailsByGTimeNo(g_time_no, go_status);
	}
	
	public List<GroupOrderDetailVO> getAllByG_order_no(String g_order_no){
		return dao.getAllByG_order_no(g_order_no);
	}
}
