package com.godetail.model;

import java.util.List;


public interface GroupOrderDetailDAO_interface {
	public void insert(GroupOrderDetailVO godVO);
    public void update(GroupOrderDetailVO godVO);
    public GroupOrderDetailVO findByPrimaryKey(String g_order_no,String g_time_no);  
    public List<GroupOrderDetailVO> getAllByG_order_no(String g_order_no);  //該訂單所有明細
    public List<GroupOrderDetailVO> getAllByG_class_no(String g_class_no,Integer go_status);  //該團課所有明細
    public List<GroupOrderDetailVO> getAll();					//所有團課
    
    public void insertFormOrder (GroupOrderDetailVO godVO , java.sql.Connection con);
    public List<GroupOrderDetailVO> getDetailsByOrder(String g_order_no,Integer go_status);
    public List<GroupOrderDetailVO> getDetailsByGTimeNo(String g_time_no,Integer go_status);

}
