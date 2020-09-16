package com.powerlist.model;

import java.util.*;

import com.worker.model.WorkerVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class PowerListJDBCDAO implements PowerListDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";


	private static final String GET_ALL = 
		"SELECT * FROM POWER_LIST";
	private static final String GET_ONE_POWERNAME = 
		"SELECT * FROM POWER_LIST WHERE POWER_ID = ?";
	
	@Override
	public void insert(PowerListVO powerlistvo) {
		
	}
	
	@Override
	public void update(PowerListVO powerlistvo) {
		
	}

	@Override
	public void delete(PowerListVO powerlistvo) {

	}
	
	@Override
	public PowerListVO findByPrimaryKey(String power_id) {

		PowerListVO powerlistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_POWERNAME);

			pstmt.setString(1, power_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				powerlistVO = new PowerListVO();
				powerlistVO.setPower_id(rs.getString("power_id"));
				powerlistVO.setPower_name(rs.getString("power_name"));

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
		return powerlistVO;
	}

	@Override
	public List<PowerListVO> getAll() {
		List<PowerListVO> list = new ArrayList<PowerListVO>();
		PowerListVO powerlistVO = null;

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
				powerlistVO = new PowerListVO();
				powerlistVO.setPower_id(rs.getString("power_id"));
				powerlistVO.setPower_name(rs.getString("power_name"));
				list.add(powerlistVO); // Store the row in the list
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

		PowerListJDBCDAO dao = new PowerListJDBCDAO();

		// 查詢
		List<PowerListVO> list = dao.getAll();
		for (PowerListVO aPowerList : list) {
			System.out.print(aPowerList.getPower_id() + ",");
			System.out.print(aPowerList.getPower_name() + ",");
			System.out.println();
		}
		System.out.println("-------------------------");

		// 查詢
		PowerListVO powerlistVO = dao.findByPrimaryKey("POWER00003");
		System.out.print(powerlistVO.getPower_id() + ",");
		System.out.print(powerlistVO.getPower_name() + ",");
	}
}