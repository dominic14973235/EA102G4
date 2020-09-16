package com.IReport.model;

import java.util.List;

public class IReportService {
	private IReportDAO_interface dao;

	public IReportService() {
		dao = new IReportDAO();
	}
	public IReportVO addIReport(String mem_ID, String i_class_no, String r_content, String r_status) {

		IReportVO iReportVO = new IReportVO();

		iReportVO.setMem_ID(mem_ID);
		iReportVO.setI_class_no(i_class_no);
		iReportVO.setR_content(r_content);
		iReportVO.setR_status(r_status);
		dao.insert(iReportVO);

		return iReportVO;
	}

	public IReportVO updateIReport(String i_report_no, String mem_ID, String i_class_no, String r_content, String r_status) {

		IReportVO iReportVO = new IReportVO();

		iReportVO.setI_report_no(i_report_no);
		iReportVO.setMem_ID(mem_ID);
		iReportVO.setI_class_no(i_class_no);
		iReportVO.setR_content(r_content);
		iReportVO.setR_status(r_status);
		
		return iReportVO;
	}

	public void deleteIReport(String i_report_no) {
		dao.delete(i_report_no);
	}

	public IReportVO getOneIReport(String i_report_no) {
		return dao.findPrimaryKey(i_report_no);
	}

	public List<IReportVO> getAll() {
		return dao.getAll();
	}
}

