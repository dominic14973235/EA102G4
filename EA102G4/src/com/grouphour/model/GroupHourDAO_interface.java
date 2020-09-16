package com.grouphour.model;

import java.util.HashSet;
import java.util.List;

public interface GroupHourDAO_interface {
	public void insert(GroupHourVO groupHourVO);
    public void update(GroupHourVO groupHourVO);
    public GroupHourVO findByPrimaryKey(String g_time_no);  
    public HashSet<GroupHourVO> getAllTimeByGroupClassNo(String g_class_no);  //團課所有時間
    public List<GroupHourVO> getAll();					//所有團課
    public void delete(String g_time_no);
}
