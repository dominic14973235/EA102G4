package com.GReport.model;

import java.util.List;

public class GReportService {
	private GReportDAO_interface dao;

	public GReportService() {
		dao = new GReportJDBCDAO();
	}
	public GReportVO addIReport(String mem_ID, String g_class_no, String r_content, String r_status) {

		GReportVO iReportVO = new GReportVO();

		iReportVO.setMem_ID(mem_ID);
		iReportVO.setG_class_no(g_class_no);
		iReportVO.setR_content(r_content);
		iReportVO.setR_status(r_status);
		dao.insert(iReportVO);

		return iReportVO;
	}

	public GReportVO updateIReport(String g_report_no, String mem_ID, String g_class_no, String r_content, String r_status) {

		GReportVO iReportVO = new GReportVO();

		iReportVO.setG_report_no(g_report_no);
		iReportVO.setMem_ID(mem_ID);
		iReportVO.setG_class_no(g_class_no);
		iReportVO.setR_content(r_content);
		iReportVO.setR_status(r_status);
		
		return iReportVO;
	}

	public void deleteIReport(String g_report_no) {
		dao.delete(g_report_no);
	}

	public GReportVO getOneIReport(String g_report_no) {
		return dao.findPrimaryKey(g_report_no);
	}

	public List<GReportVO> getAll() {
		return dao.getAll();
	}
}

