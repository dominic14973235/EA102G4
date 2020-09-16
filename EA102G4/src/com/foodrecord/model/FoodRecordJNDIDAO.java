package com.foodrecord.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;

public class FoodRecordJNDIDAO implements FoodRecordDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/INGYM");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
	private static final String INSERT = 
		"INSERT INTO FOOD_RECORD(FOODR_NO, MEM_ID, FOOD_NO, FOODR_TIME, FOODR_DATE, FOODR_WEIGHT)VALUES('FREC'||LPAD(TO_CHAR(FOOD_RECORD_SEQ.NEXTVAL), 6, '0'),?,?,?,?,?)";
	private static final String UPDATE = 
		"UPDATE FOOD_RECORD SET MEM_ID=?, FOOD_NO=?, FOODR_TIME=?, FOODR_DATE=?, FOODR_WEIGHT=? WHERE FOODR_NO = ?";
	private static final String DELETE = 
		"DELETE FROM FOOD_RECORD WHERE FOODR_NO = ?";
	private static final String GET_ONE = 
		"SELECT * FROM FOOD_RECORD WHERE FOODR_NO = ?";
	private static final String GET_ALL_MEM = 
		"SELECT * FROM FOOD_RECORD WHERE MEM_ID = ?";
	private static final String GET_ALL_DATE = 
		"SELECT * FROM FOOD_RECORD WHERE FOODR_DATE = ? AND MEM_ID = ?";
	private static final String GET_ALL = 
			"SELECT * FROM FOOD_RECORD";
		
	@Override
	public void insert(FoodRecordVO foodrecordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, foodrecordVO.getMem_id());
			pstmt.setString(2, foodrecordVO.getFood_no());
			pstmt.setString(3, foodrecordVO.getFoodr_time());
			pstmt.setDate(4, foodrecordVO.getFoodr_date());
			pstmt.setInt(5, foodrecordVO.getFoodr_weight());

			pstmt.executeUpdate();

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
	public void update(FoodRecordVO foodrecordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, foodrecordVO.getMem_id());
			pstmt.setString(2, foodrecordVO.getFood_no());
			pstmt.setString(3, foodrecordVO.getFoodr_time());
			pstmt.setDate(4, foodrecordVO.getFoodr_date());
			pstmt.setInt(5, foodrecordVO.getFoodr_weight());
			pstmt.setString(6, foodrecordVO.getFoodr_no());			

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, work_id);
			pstmt.executeUpdate();

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
	public FoodRecordVO findByPrimaryKey(String foodr_no) {
		
		FoodRecordVO foodrecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();		
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setString(1, foodr_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				foodrecordVO = new FoodRecordVO();
				foodrecordVO.setFoodr_no(rs.getString("foodr_no"));
				foodrecordVO.setMem_id(rs.getString("mem_id"));
				foodrecordVO.setFood_no(rs.getString("food_no"));
				foodrecordVO.setFoodr_time(rs.getString("foodr_time"));
				foodrecordVO.setFoodr_date(rs.getDate("foodr_date"));
				foodrecordVO.setFoodr_weight(rs.getInt("foodr_weight"));
			}

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
		return foodrecordVO;
	}

	@Override
	public List<FoodRecordVO> getAll() {
		List<FoodRecordVO> list = new ArrayList<FoodRecordVO>();
		FoodRecordVO foodrecordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();	
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				foodrecordVO = new FoodRecordVO();
				foodrecordVO.setFoodr_no(rs.getString("foodr_no"));
				foodrecordVO.setMem_id(rs.getString("mem_id"));
				foodrecordVO.setFood_no(rs.getString("food_no"));
				foodrecordVO.setFoodr_time(rs.getString("foodr_time"));
				foodrecordVO.setFoodr_date(rs.getDate("foodr_date"));
				foodrecordVO.setFoodr_weight(rs.getInt("foodr_weight"));
				list.add(foodrecordVO); // Store the row in the list
			}
			
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
	public List<FoodRecordVO> getAllByMem(String mem_id) {
		List<FoodRecordVO> list = new ArrayList<FoodRecordVO>();
		FoodRecordVO foodrecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();		
			pstmt = con.prepareStatement(GET_ALL_MEM);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				foodrecordVO = new FoodRecordVO();
				foodrecordVO.setFoodr_no(rs.getString("foodr_no"));
				foodrecordVO.setMem_id(rs.getString("mem_id"));
				foodrecordVO.setFood_no(rs.getString("food_no"));
				foodrecordVO.setFoodr_time(rs.getString("foodr_time"));
				foodrecordVO.setFoodr_date(rs.getDate("foodr_date"));
				foodrecordVO.setFoodr_weight(rs.getInt("foodr_weight"));
				list.add(foodrecordVO); // Store the row in the list
			}

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
	public List<FoodRecordVO> getAllByDate(Date foodr_date, String mem_id) {
		List<FoodRecordVO> list = new ArrayList<FoodRecordVO>();
		FoodRecordVO foodrecordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DATE);
			pstmt.setDate(1, foodr_date);
			pstmt.setString(2, mem_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				foodrecordVO = new FoodRecordVO();
				foodrecordVO.setFoodr_no(rs.getString("foodr_no"));
				foodrecordVO.setMem_id(rs.getString("mem_id"));
				foodrecordVO.setFood_no(rs.getString("food_no"));
				foodrecordVO.setFoodr_time(rs.getString("foodr_time"));
				foodrecordVO.setFoodr_date(rs.getDate("foodr_date"));
				foodrecordVO.setFoodr_weight(rs.getInt("foodr_weight"));
				list.add(foodrecordVO); // Store the row in the list
			}
			
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

//	public static void main(String[] args) throws IOException {

//		FoodRecordJNDIDAO dao = new FoodRecordJNDIDAO();

		// 新增
//		FoodRecordVO foodrecordVO1 = new FoodRecordVO();
//		foodrecordVO1.setMem_id("MEM0000002");
//		foodrecordVO1.setFood_no("FOOD000001");
//		foodrecordVO1.setFoodr_time("晚餐");
//		foodrecordVO1.setFoodr_date(java.sql.Date.valueOf("2005-01-01"));
//		foodrecordVO1.setFoodr_weight(2);
//		dao.insert(foodrecordVO1);

		// 修改
//		FoodRecordVO foodrecordVO2 = new FoodRecordVO();
//		foodrecordVO2.setMem_id("MEM0000003");
//		foodrecordVO2.setFood_no("FOOD000001");
//		foodrecordVO2.setFoodr_time("晚餐");
//		foodrecordVO2.setFoodr_date(java.sql.Date.valueOf("2011-03-04"));
//		foodrecordVO2.setFoodr_weight(3);
//		foodrecordVO2.setFoodr_no("FREC000001");
//		dao.update(foodrecordVO2);

		// 刪除
//		dao.delete("FREC000009");
		
		// 查詢
//		FoodRecordVO foodrecordVO = dao.findByPrimaryKey("FREC000002");
//			System.out.print(foodrecordVO.getFoodr_no() + ",");
//			System.out.print(foodrecordVO.getMem_id() + ",");
//			System.out.print(foodrecordVO.getFood_no() + ",");
//			System.out.print(foodrecordVO.getFoodr_time() + ",");
//			System.out.print(foodrecordVO.getFoodr_date() + ",");
//			System.out.print(foodrecordVO.getFoodr_weight() + ",");
//			System.out.println();

		// 查詢
//		List<FoodRecordVO> list = dao.getAllByMem("MEM0000003");
//		for (FoodRecordVO afoodrecord : list) {
//			System.out.print(afoodrecord.getFoodr_no() + ",");
//			System.out.print(afoodrecord.getMem_id() + ",");
//			System.out.print(afoodrecord.getFood_no() + ",");
//			System.out.print(afoodrecord.getFoodr_time() + ",");
//			System.out.print(afoodrecord.getFoodr_date() + ",");
//			System.out.print(afoodrecord.getFoodr_weight() + ",");
//			System.out.println();
//		}
		// 查詢
//		List<FoodRecordVO> list1 = dao.getAllByDate(java.sql.Date.valueOf("2020-08-25"),"MEM0000001");
//		for (FoodRecordVO afoodrecord : list1) {
//			System.out.print(afoodrecord.getFoodr_no() + ",");
//			System.out.print(afoodrecord.getMem_id() + ",");
//			System.out.print(afoodrecord.getFood_no() + ",");
//			System.out.print(afoodrecord.getFoodr_time() + ",");
//			System.out.print(afoodrecord.getFoodr_date() + ",");
//			System.out.print(afoodrecord.getFoodr_weight() + ",");
//			System.out.println();
//		}
		
//		List<FoodRecordVO> list1 = dao.getAll();
//		for (FoodRecordVO afoodrecord : list1) {
//			System.out.print(afoodrecord.getFoodr_no() + ",");
//			System.out.print(afoodrecord.getMem_id() + ",");
//			System.out.print(afoodrecord.getFood_no() + ",");
//			System.out.print(afoodrecord.getFoodr_time() + ",");
//			System.out.print(afoodrecord.getFoodr_date() + ",");
//			System.out.print(afoodrecord.getFoodr_weight() + ",");
//			System.out.println();
//		}

//	}
}