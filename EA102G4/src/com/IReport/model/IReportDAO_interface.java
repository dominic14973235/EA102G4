package com.IReport.model;

import java.util.List;

public interface IReportDAO_interface {

	public void insert(IReportVO iReportVO);

	public void update(IReportVO iReportVO);

	public void delete(String i_report_no);

	public IReportVO findPrimaryKey(String i_report_no);

	public List<IReportVO> getAll();
	

}
