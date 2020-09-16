package com.GReport.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GReportJDBCDAO implements GReportDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gReportVO.getMem_ID());
			pstmt.setString(2, gReportVO.getG_class_no());
			pstmt.setString(3, gReportVO.getR_content());
			pstmt.setString(4, gReportVO.getR_status());

			pstmt.executeUpdate();

			System.out.println("insert successfully"); // testing

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, gReportVO.getMem_ID());
			pstmt.setString(2, gReportVO.getG_class_no());
			pstmt.setString(3, gReportVO.getR_content());
			pstmt.setString(4, gReportVO.getR_status());
			pstmt.setString(5, gReportVO.getG_report_no());

			pstmt.executeUpdate();

			System.out.println("Update successfully"); // testing

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, g_report_no);

			pstmt.executeUpdate();

			System.out.println("delete successfully"); // testing

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		GReportVO iReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, g_report_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				iReportVO = new GReportVO();

				iReportVO.setG_report_no(rs.getString("i_report_no"));
				iReportVO.setMem_ID(rs.getString("mem_ID"));
				iReportVO.setG_class_no(rs.getString("i_class_no"));
				iReportVO.setR_content(rs.getString("r_content"));
				iReportVO.setR_status(rs.getString("r_status"));
				iReportVO.setrTime(rs.getDate("rtime"));

			}

			 System.out.println("query successfully"); //testing

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public List<GReportVO> getAll() {
		List<GReportVO> list = new ArrayList<GReportVO>();
		GReportVO gReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				gReportVO = new GReportVO();

				gReportVO.setG_report_no(rs.getString("i_report_no"));
				gReportVO.setMem_ID(rs.getString("mem_ID"));
				gReportVO.setG_class_no(rs.getString("i_class_no"));
				gReportVO.setR_content(rs.getString("r_content"));
				gReportVO.setR_status(rs.getString("r_status"));
				gReportVO.setrTime(rs.getDate("rtime"));
				list.add(gReportVO);
			}

			System.out.println("query successfully"); // testing

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

//Model testing
//	public static void main(String args[]){
//		IReportJDBCDAO dao = new IReportJDBCDAO();
//		IReportVO iReportVO = new IReportVO();
// ¼W 
//		iReportVO.setI_report_no();
//		iReportVO.setMem_ID("MEM0000005");
//		iReportVO.setI_class_no("IC1000000");
//		iReportVO.setR_content("FUCK YOU MOTHERFUCKER");
//		iReportVO.setR_status("R1");
//		dao.insert(iReportVO);
//
//// §ï
//		iReportVO.setI_report_no("IO1000009");
//		iReportVO.setMem_ID("MEM0000005");
//		iReportVO.setI_class_no("IC1000000");
//		iReportVO.setR_content("FUCK YOU BITCH");
//		iReportVO.setR_status("R2");
//		dao.update(iReportVO);
//
//// §R
//		dao.delete("IO1000008");
//
////¬d
//		iReportVO = dao.findPrimaryKey("IO1000011");
//		System.out.println(iReportVO.getI_report_no());
//		System.out.println(iReportVO.getI_class_no());
//		System.out.println(iReportVO.getMem_ID());
//		System.out.println(iReportVO.getR_content());
//		System.out.println(iReportVO.getR_status());
//
//		List<IReportVO> list = dao.getAll();
//		for (IReportVO ct : list) {
//		System.out.println(ct.getI_report_no());
//		System.out.println(ct.getI_class_no());
//		System.out.println(ct.getMem_ID());
//		System.out.println(ct.getR_content());
//		System.out.println(ct.getR_status());
//			System.out.println();
//		}
//	}
}
