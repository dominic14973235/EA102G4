package com.GReport.model;

import java.util.List;

public interface GReportDAO_interface {

	public void insert(GReportVO iReportVO);

	public void update(GReportVO iReportVO);

	public void delete(String i_report_no);

	public GReportVO findPrimaryKey(String i_report_no);

	public List<GReportVO> getAll();
	

}
