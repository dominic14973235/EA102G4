package com.IReport.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IReportJDBCDAO implements IReportDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, iReportVO.getMem_ID());
			pstmt.setString(2, iReportVO.getI_class_no());
			pstmt.setString(3, iReportVO.getR_content());
			pstmt.setString(4, iReportVO.getR_status());

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
	public void update(IReportVO iReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, iReportVO.getMem_ID());
			pstmt.setString(2, iReportVO.getI_class_no());
			pstmt.setString(3, iReportVO.getR_content());
			pstmt.setString(4, iReportVO.getR_status());
			pstmt.setString(5, iReportVO.getI_report_no());

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
	public void delete(String i_report_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, i_report_no);

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
	public IReportVO findPrimaryKey(String i_report_no) {
		IReportVO iReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<IReportVO> getAll() {
		List<IReportVO> list = new ArrayList<IReportVO>();
		IReportVO iReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
