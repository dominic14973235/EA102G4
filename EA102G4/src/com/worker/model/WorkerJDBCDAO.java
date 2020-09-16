package com.worker.model;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class WorkerJDBCDAO implements WorkerDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO WORKER (WORK_ID,W_NAME,W_ACC,W_PW,EMAIL,REG_DATE,PHOTO) VALUES ('WORK'||LPAD(TO_CHAR(WORKS_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT WORK_ID,W_NAME,W_ACC,W_PW,EMAIL,TO_CHAR(REG_DATE,'YYYY-MM-DD') REG_DATE,PHOTO FROM WORKER ORDER BY WORK_ID";
	private static final String GET_ONE_STMT = 
		"SELECT WORK_ID,W_NAME,W_ACC,W_PW,EMAIL,TO_CHAR(REG_DATE,'YYYY-MM-DD') REG_DATE,PHOTO FROM WORKER WHERE WORK_ID = ?";
	private static final String GET_ONE_ACC = 
			"SELECT WORK_ID,W_NAME,W_ACC,W_PW,EMAIL,TO_CHAR(REG_DATE,'YYYY-MM-DD') REG_DATE,PHOTO FROM WORKER WHERE W_ACC = ?";
	private static final String DELETE = 
		"DELETE FROM WORKER WHERE WORK_ID = ?";
	private static final String UPDATE = 
		"UPDATE WORKER SET W_NAME=?, W_ACC=?, W_PW=?, EMAIL=?, REG_DATE=?, PHOTO=? WHERE WORK_ID = ?";
	private static final String UPDATE_PW = 
			"UPDATE WORKER SET W_PW=? WHERE WORK_ID = ?";

	@Override
	public void insert(WorkerVO workerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, workerVO.getW_name());
			pstmt.setString(2, workerVO.getW_acc());
			pstmt.setString(3, workerVO.getW_pw());
			pstmt.setString(4, workerVO.getEmail());
			pstmt.setDate(5, workerVO.getReg_date());
			pstmt.setBytes(6, workerVO.getPhoto());

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
	public void update(WorkerVO workerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, workerVO.getW_name());
			pstmt.setString(2, workerVO.getW_acc());
			pstmt.setString(3, workerVO.getW_pw());
			pstmt.setString(4, workerVO.getEmail());
			pstmt.setDate(5, workerVO.getReg_date());			
			pstmt.setBytes(6, workerVO.getPhoto());
			pstmt.setString(7, workerVO.getWork_id());

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
	public void updatePw(String work_id, String w_pw) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_PW);
			
			pstmt.setString(1, w_pw);
			pstmt.setString(2, work_id);
			
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
	public void delete(String work_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

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
	public WorkerVO findByPrimaryKey(String work_id) {

		WorkerVO workerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, work_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				workerVO = new WorkerVO();
				workerVO.setWork_id(rs.getString("work_id"));
				workerVO.setW_name(rs.getString("w_name"));
				workerVO.setW_acc(rs.getString("w_acc"));
				workerVO.setW_pw(rs.getString("w_pw"));
				workerVO.setEmail(rs.getString("email"));
				workerVO.setReg_date(rs.getDate("reg_date"));
				workerVO.setPhoto(rs.getBytes("photo"));
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
		return workerVO;
	}
	
	@Override
	public WorkerVO findByAcc(String w_acc) {
		
		WorkerVO workerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(GET_ONE_ACC);
			
			pstmt.setString(1, w_acc);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				workerVO = new WorkerVO();
				workerVO.setWork_id(rs.getString("work_id"));
				workerVO.setW_name(rs.getString("w_name"));
				workerVO.setW_acc(rs.getString("w_acc"));
				workerVO.setW_pw(rs.getString("w_pw"));
				workerVO.setEmail(rs.getString("email"));
				workerVO.setReg_date(rs.getDate("reg_date"));
				workerVO.setPhoto(rs.getBytes("photo"));
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
			return workerVO;
		}

	@Override
	public List<WorkerVO> getAll() {
		List<WorkerVO> list = new ArrayList<WorkerVO>();
		WorkerVO workerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				workerVO = new WorkerVO();
				workerVO.setWork_id(rs.getString("work_id"));
				workerVO.setW_name(rs.getString("w_name"));
				workerVO.setW_acc(rs.getString("w_acc"));
				workerVO.setW_pw(rs.getString("w_pw"));
				workerVO.setEmail(rs.getString("email"));
				workerVO.setReg_date(rs.getDate("reg_date"));
				workerVO.setPhoto(rs.getBytes("photo"));
				list.add(workerVO); // Store the row in the list
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
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

	public static void main(String[] args) throws IOException {

		WorkerJDBCDAO dao = new WorkerJDBCDAO();
		
//		dao.updatePw("WORK000008", "ABC123");
		
		// 新增
//		WorkerVO workerVO1 = new WorkerVO();
//		workerVO1.setW_name("寧王");
//		workerVO1.setW_acc("Q123456");
//		workerVO1.setW_pw("WZ123");
//		workerVO1.setEmail("PQQ233@GMAIL.com");
//		workerVO1.setReg_date(java.sql.Date.valueOf("2005-01-01"));
//		byte[] pic1 = getPictureByteArray("D:\\Picture\\1.jpeg");
//		workerVO1.setPhoto(pic1);
//		dao.insert(workerVO1);

		// 修改
//		WorkerVO workerVO2 = new WorkerVO();
//		workerVO2.setWork_id("WORK000009");
//		workerVO2.setW_name("吳永志2");
//		workerVO2.setW_acc("MANAGER2");
//		workerVO2.setW_pw("aaa");
//		workerVO2.setEmail("aaa");
//		workerVO2.setReg_date(java.sql.Date.valueOf("2002-01-01"));
//		byte[] pic2 = getPictureByteArray("D:\\Picture\\2.jpg");
//		workerVO2.setPhoto(pic2);
//		dao.update(workerVO2);

		// 刪除
//		dao.delete("WORK000012");

		// 查詢
//		WorkerVO workerVO3 = dao.findByPrimaryKey("WORK000001");
//		System.out.print(workerVO3.getWork_id() + ",");
//		System.out.print(workerVO3.getW_name() + ",");
//		System.out.print(workerVO3.getW_acc() + ",");
//		System.out.print(workerVO3.getW_pw() + ",");
//		System.out.print(workerVO3.getEmail() + ",");
//		System.out.print(workerVO3.getReg_date() + ",");
//		System.out.println(workerVO3.getPhoto());
//		System.out.println("---------------------");
		
		// 查詢
//		WorkerVO workerVO3 = dao.findByAcc("A123456");
//		System.out.print(workerVO3.getWork_id() + ",");
//		System.out.print(workerVO3.getW_name() + ",");
//		System.out.print(workerVO3.getW_acc() + ",");
//		System.out.print(workerVO3.getW_pw() + ",");
//		System.out.print(workerVO3.getEmail() + ",");
//		System.out.print(workerVO3.getReg_date() + ",");
//		System.out.println(workerVO3.getPhoto());
//		System.out.println("---------------------");

		// 查詢
//		List<WorkerVO> list = dao.getAll();
//		for (WorkerVO aWorker : list) {
//			System.out.print(aWorker.getWork_id() + ",");
//			System.out.print(aWorker.getW_name() + ",");
//			System.out.print(aWorker.getW_acc() + ",");
//			System.out.print(aWorker.getW_pw() + ",");
//			System.out.print(aWorker.getEmail() + ",");
//			System.out.print(aWorker.getReg_date() + ",");
//			System.out.print(aWorker.getPhoto());
//			System.out.println();
//		}
	}
}