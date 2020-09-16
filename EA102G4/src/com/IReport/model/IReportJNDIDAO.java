package com.IReport.model;

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

public class IReportJNDIDAO implements IReportDAO_interface {
	
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

	private static final String INSERT_STMT = "INSERT INTO I_REPORT (I_REPORT_NO, MEM_ID, I_CLASS_NO, R_CONTENT, R_STATUS)"
			+ "VALUES ('IO'||LPAD(SEQ_I_REPORT_NO.NEXTVAL,7,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM I_REPORT ORDER BY I_REPORT_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM I_REPORT WHERE I_REPORT_NO =?";
	private static final String DELETE = "DELETE FROM I_REPORT WHERE I_REPORT_NO=?";
	private static final String UPDATE = "UPDATE I_REPORT SET  MEM_ID=?, I_CLASS_NO=?, R_CONTENT=?, R_STATUS=? where I_REPORT_NO=?";

	@Override
	public void insert(IReportVO iReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, iReportVO.getMem_ID());
			pstmt.setString(2, iReportVO.getI_class_no());
			pstmt.setString(3, iReportVO.getR_content());
			pstmt.setString(4, iReportVO.getR_status());

			pstmt.executeUpdate();

			System.out.println("insert successfully"); // testing

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
	public void update(IReportVO iReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, iReportVO.getMem_ID());
			pstmt.setString(2, iReportVO.getI_class_no());
			pstmt.setString(3, iReportVO.getR_content());
			pstmt.setString(4, iReportVO.getR_status());
			pstmt.setString(5, iReportVO.getI_report_no());

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
	public void delete(String i_report_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, i_report_no);

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
	public IReportVO findPrimaryKey(String i_report_no) {
		IReportVO iReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, i_report_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				iReportVO = new IReportVO();

				iReportVO.setI_report_no(rs.getString("i_report_no"));
				iReportVO.setMem_ID(rs.getString("mem_ID"));
				iReportVO.setI_class_no(rs.getString("i_class_no"));
				iReportVO.setR_content(rs.getString("r_content"));
				iReportVO.setR_status(rs.getString("r_status"));
				iReportVO.setrTime(rs.getDate("rtime"));

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

		return iReportVO;
	}

	@Override
	public List<IReportVO> getAll() {
		List<IReportVO> list = new ArrayList<IReportVO>();
		IReportVO iReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				iReportVO = new IReportVO();

				iReportVO.setI_report_no(rs.getString("i_report_no"));
				iReportVO.setMem_ID(rs.getString("mem_ID"));
				iReportVO.setI_class_no(rs.getString("i_class_no"));
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
