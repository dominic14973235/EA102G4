package com.workpower.model;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class WorkPowerJDBCDAO implements WorkPowerDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";

	private static final String INSERT = 
		"INSERT INTO WORK_POWER (WORK_ID,POWER_ID) VALUES (?, ?)";
	private static final String GET_ALL = 
		"SELECT * FROM WORK_POWER";
	private static final String GET_BY_WORKER = 
		"SELECT WORK_ID, POWER_ID FROM WORK_POWER WHERE WORK_ID = ?";
	private static final String GET_BY_POWER = 
		"SELECT POWER_ID, WORK_ID FROM WORK_POWER WHERE POWER_ID = ?";
	private static final String DELETE_BY_WORKER = 
		"DELETE FROM WORK_POWER where WORK_ID = ?";

//	private static final String UPDATE = 
//		"UPDATE WORK_POWER set WORK_ID = ?, POWER_ID = ? where WORK_ID = ? AND POWER_ID = ?";

	@Override
	public void insert(WorkPowerVO workpowerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, workpowerVO.getWork_id());
			pstmt.setString(2, workpowerVO.getPower_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	public void update(WorkPowerVO workpowerVO) {
		
	}

	@Override
	public void deleteAllByWork(String work_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_BY_WORKER);

			pstmt.setString(1, work_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<WorkPowerVO> getByWorker(String work_id) {
		List<WorkPowerVO> list = new ArrayList<WorkPowerVO>();
		WorkPowerVO workpowerVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_WORKER);

			pstmt.setString(1, work_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				workpowerVO = new WorkPowerVO();
				workpowerVO.setWork_id(rs.getString("work_id"));
				workpowerVO.setPower_id(rs.getString("power_id"));
				list.add(workpowerVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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

	@Override
	public List<WorkPowerVO> getByPower(String power_id) {
		List<WorkPowerVO> list = new ArrayList<WorkPowerVO>();
		WorkPowerVO workpowerVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_POWER);

			pstmt.setString(1, power_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				workpowerVO = new WorkPowerVO();
				workpowerVO.setPower_id(rs.getString("power_id"));
				workpowerVO.setWork_id(rs.getString("work_id"));

				list.add(workpowerVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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

	@Override
	public List<WorkPowerVO> getAll() {
		List<WorkPowerVO> list = new ArrayList<WorkPowerVO>();
		WorkPowerVO workpowerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				workpowerVO = new WorkPowerVO();
				workpowerVO.setWork_id(rs.getString("work_id"));
				workpowerVO.setPower_id(rs.getString("power_id"));
				list.add(workpowerVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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


	public static void main(String[] args) throws IOException {

		WorkPowerJDBCDAO dao = new WorkPowerJDBCDAO();

		// 新增
//		WorkPowerVO workpowerVO1 = new WorkPowerVO();
//		workpowerVO1.setWork_id("WORK000001");
//		workpowerVO1.setPower_id("POWER00001");
//
//		dao.insert(workerpowerVO1);
//		System.out.println("新增-------------------------");

		// 修改

		// 刪除
		dao.deleteAllByWork("WORK000002");
		System.out.println("刪除-------------------------");

		// 查詢
//		List<WorkPowerVO> list = dao.getByWorker("WORK000010");
//		for (WorkPowerVO aWorkPower : list) {
//			System.out.print(aWorkPower.getWork_id() + ",");
//			System.out.print(aWorkPower.getPower_id() + ",");
//			System.out.println();
//		}
//		System.out.println("查員工-------------------------");

		// 查詢
//		List<WorkPowerVO> list1 = dao.getByPower("POWER00002");
//		for (WorkPowerVO aWorkPower : list1) {
//			System.out.print(aWorkPower.getPower_id() + ",");
//			System.out.print(aWorkPower.getWork_id() + ",");
//			System.out.println();
//		}
//		System.out.println("查權限-------------------------");

		// 查詢
//		List<WorkPowerVO> list2 = dao.getAll();
//		for (WorkPowerVO aWorkPower : list2) {
//			System.out.print(aWorkPower.getWork_id() + ",");
//			System.out.print(aWorkPower.getPower_id() + ",");
//			System.out.println();
//		}
//		System.out.println("查全部-------------------------");

	}
}