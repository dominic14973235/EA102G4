package com.grouphour.model;

import java.util.HashSet;
import java.util.List;

public class GroupHourService {
	private GroupHourDAO_interface dao;

	public GroupHourService() {
		dao = new GroupHourDAO();
	}
	public GroupHourVO addGroupHour(GroupHourVO ghVO) {
		dao.insert(ghVO);
		return ghVO;
	}
	public GroupHourVO updateGroupHour(java.sql.Date c_date,String hr,String g_time_no) {
		GroupHourVO ghVO = dao.findByPrimaryKey(g_time_no);
		ghVO.setC_date(c_date);
		ghVO.setG_time_no(g_time_no);
		ghVO.setHr(hr);
		dao.update(ghVO);
		return ghVO;
	}
	public void delete(String g_time_no) {
		dao.delete(g_time_no);
	}
	public GroupHourVO getOneTimeNo(String g_time_no) {
		return dao.findByPrimaryKey(g_time_no);
	}
	public List<GroupHourVO> getAll(){
		return dao.getAll();
	}
	public HashSet<GroupHourVO> getAllByGroupClassNo(String g_class_no){
		return dao.getAllTimeByGroupClassNo(g_class_no);
	}
}
