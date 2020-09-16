package com.GReport.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GReportJNDIDAO implements GReportDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO G_REPORT (G_REPORT_NO, MEM_ID, G_CLASS_NO, R_CONTENT, R_STATUS)"
			+ "VALUES ('IO'||LPAD(SEQ_G_REPORT_NO.NEXTVAL,7,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM G_REPORT ORDER BY G_REPORT_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM G_REPORT WHERE G_REPORT_NO =?";
	private static final String DELETE = "DELETE FROM G_REPORT WHERE G_REPORT_NO=?";
	private static final String UPDATE = "UPDATE G_REPORT SET  MEM_ID=?, G_CLASS_NO=?, R_CONTENT=?, R_STATUS=? where G_REPORT_NO=?";

	@Override
	public void insert(GReportVO gReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gReportVO.getMem_ID());
			pstmt.setString(2, gReportVO.getG_class_no());
			pstmt.setString(3, gReportVO.getR_content());
			pstmt.setString(4, gReportVO.getR_status());

			pstmt.executeUpdate();

			System.out.println("insert successfully"); // testing

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(GReportVO gReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, gReportVO.getMem_ID());
			pstmt.setString(2, gReportVO.getG_class_no());
			pstmt.setString(3, gReportVO.getR_content());
			pstmt.setString(4, gReportVO.getR_status());
			pstmt.setString(5, gReportVO.getG_report_no());

			pstmt.executeUpdate();

			System.out.println("Update successfully"); // testing

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void delete(String g_report_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, g_report_no);

			pstmt.executeUpdate();

			System.out.println("delete successfully"); // testing

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public GReportVO findPrimaryKey(String g_report_no) {
		GReportVO gReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, g_report_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				gReportVO = new GReportVO();

				gReportVO.setG_report_no(rs.getString("g_report_no"));
				gReportVO.setMem_ID(rs.getString("mem_ID"));
				gReportVO.setG_class_no(rs.getString("g_class_no"));
				gReportVO.setR_content(rs.getString("r_content"));
				gReportVO.setR_status(rs.getString("r_status"));
				gReportVO.setrTime(rs.getDate("rtime"));
			}

			 System.out.println("query successfully"); //testing

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return gReportVO;
	}

	@Override
	public List<GReportVO> getAll() {
		List<GReportVO> list = new ArrayList<GReportVO>();
		GReportVO iReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				iReportVO = new GReportVO();

				iReportVO.setG_report_no(rs.getString("g_report_no"));
				iReportVO.setMem_ID(rs.getString("mem_ID"));
				iReportVO.setG_class_no(rs.getString("g_class_no"));
				iReportVO.setR_content(rs.getString("r_content"));
				iReportVO.setR_status(rs.getString("r_status"));
				iReportVO.setrTime(rs.getDate("rtime"));
				list.add(iReportVO);
			}

			System.out.println("query successfully"); // testing

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}
}
