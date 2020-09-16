package com.groupclass.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.grouphour.model.GroupHourService;
import com.grouphour.model.GroupHourVO;

public class GroupClassService {
	
private GroupClassDAO_interface dao;
	
	
	public GroupClassService() {
		dao = new GroupClassDAO();
	}
	
	public GroupClassVO addGroupClass(
	String pro_id,String c_type_no,String g_name,String loc,
	Integer g_max,Integer p_coin,String g_detail,byte[] g_pic) {
		//"VALUES ('GC'||LPAD(SEQ_G_CLASS_NO.NEXTVAL,7,'0'),?,?,?,?,?,?)
		GroupClassVO gcVO = new GroupClassVO();
		gcVO.setPro_id(pro_id);
		gcVO.setP_coin(p_coin);
		gcVO.setLoc(loc);
		gcVO.setG_pic(g_pic);
		gcVO.setG_name(g_name);
		gcVO.setG_max(g_max);
		gcVO.setG_detail(g_detail);
		gcVO.setC_type_no(c_type_no);
		dao.insert(gcVO);
		return gcVO;
	}
	
	public GroupClassVO updateGroupClass(
	String c_type_no,String g_name,String loc,Integer g_max,Integer p_coin,
	String g_detail,byte[] g_pic,Integer c_status,String g_class_no) {
		GroupClassVO gcVO = new GroupClassVO();
		gcVO.setC_type_no(c_type_no);
		gcVO.setG_name(g_name);
		gcVO.setLoc(loc);
		gcVO.setG_max(g_max);
		gcVO.setP_coin(p_coin);
		gcVO.setG_detail(g_detail);
		gcVO.setG_pic(g_pic);
		gcVO.setC_status(c_status);
		gcVO.setG_class_no(g_class_no);
		dao.update(gcVO);
		
		return gcVO;
	}
	
	public GroupClassVO getOneGroupClass(String g_class_no) {
		return dao.findByPrimaryKey(g_class_no);
	}
	
	public List<GroupClassVO> getAll(){
		return dao.getAll();
	}
	
	public List<GroupClassVO> getGroupClassesByProId(String pro_id){
		return dao.getAllByPro_id(pro_id);
	}

	public int getAllGhSize(String g_class_no){
		GroupHourService ghSvc = new GroupHourService();
		HashSet<GroupHourVO> set = ghSvc.getAllByGroupClassNo(g_class_no);
		
		return set.stream()
				.filter(ghVO -> ghVO.getC_date().getTime() > new Date().getTime())
				.collect(Collectors.toSet()).size();
	}
	
	public GroupClassVO getNewGroupClassesByProId(String pro_id){
		return dao.getNewGClassNoByPro_id(pro_id);
	}
	public byte[] getG_picByClassNo(String g_class_no) {
		return dao.getG_picByG_class_no(g_class_no);
	}
	
}
