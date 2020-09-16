package com.gclassreport.model;

import java.util.List;

public interface GroupClassReportDAO_interface {
	public void insert(GroupClassReportVO grVO);
    public void updateStatus(GroupClassReportVO grVO);
    public void updateContent(GroupClassReportVO grVO);
    public GroupClassReportVO findByPrimaryKey(String c_report_no);  
    public List<GroupClassReportVO> getAllByMem_id(String mem_id);  //該會員所有檢舉
    public List<GroupClassReportVO> getAllByG_class_no(String g_class_no);
    public List<GroupClassReportVO> getAll();					//所有檢舉

}
