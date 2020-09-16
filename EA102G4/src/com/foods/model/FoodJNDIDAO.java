package com.foods.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.worker.model.WorkerVO;
import com.foodrecord.model.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class FoodJNDIDAO implements FoodDAO_interface {
	
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

	private static final String INSERT = "INSERT INTO FOODS (FOOD_NO,FOOD_NAME,STARCH,PROTEIN,FAT,KCAL) VALUES ('FOOD'||LPAD(TO_CHAR(FOODS_SEQ.NEXTVAL), 6, '0'),?,?,?,?,?)";
	private static final String GET_ALL = "SELECT * FROM FOODS";
	private static final String GET_ONE = "SELECT * FROM FOODS WHERE FOOD_NO = ?";
	private static final String GET_ONE_BYNAME = "SELECT * FROM FOODS WHERE FOOD_NAME = ?";
	private static final String DELETE = "DELETE FROM FOODS WHERE FOOD_NO = ?";
	private static final String UPDATE = "UPDATE FOODS SET FOOD_NAME=?, STARCH=?, PROTEIN=?, FAT=?, KCAL=? WHERE FOOD_NO = ?";

	@Override
	public void insert(FoodVO foodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, foodVO.getFood_name());
			pstmt.setDouble(2, foodVO.getStarch());
			pstmt.setDouble(3, foodVO.getProtein());
			pstmt.setDouble(4, foodVO.getFat());
			pstmt.setDouble(5, foodVO.getKcal());

			pstmt.executeUpdate();

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
	public List<FoodVO> getAll() {
		List<FoodVO> list = new ArrayList<FoodVO>();
		FoodVO foodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				foodVO = new FoodVO();
				foodVO.setFood_no(rs.getString("food_no"));
				foodVO.setFood_name(rs.getString("food_name"));
				foodVO.setStarch(rs.getDouble("starch"));
				foodVO.setProtein(rs.getDouble("protein"));
				foodVO.setFat(rs.getDouble("fat"));
				foodVO.setKcal(rs.getDouble("kcal"));
				list.add(foodVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public FoodVO findByPrimaryKey(String food_no) {

		FoodVO foodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, food_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				foodVO = new FoodVO();
				foodVO.setFood_no(rs.getString("food_no"));
				foodVO.setFood_name(rs.getString("food_name"));
				foodVO.setStarch(rs.getDouble("starch"));
				foodVO.setProtein(rs.getDouble("protein"));
				foodVO.setFat(rs.getDouble("fat"));
				foodVO.setKcal(rs.getDouble("kcal"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return foodVO;
	}
	
	@Override
	public FoodVO getFood_no(String food_name) {
		
		FoodVO foodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BYNAME);
			
			pstmt.setString(1, food_name);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				foodVO = new FoodVO();
				foodVO.setFood_no(rs.getString("food_no"));
				foodVO.setFood_name(rs.getString("food_name"));
				foodVO.setStarch(rs.getDouble("starch"));
				foodVO.setProtein(rs.getDouble("protein"));
				foodVO.setFat(rs.getDouble("fat"));
				foodVO.setKcal(rs.getDouble("kcal"));
			}
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return foodVO;
	}

	public void update(FoodVO foodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, foodVO.getFood_name());
			pstmt.setDouble(2, foodVO.getStarch());
			pstmt.setDouble(3, foodVO.getProtein());
			pstmt.setDouble(4, foodVO.getFat());
			pstmt.setDouble(5, foodVO.getKcal());
			pstmt.setString(6, foodVO.getFood_no());

			pstmt.executeUpdate();

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

	public void delete(String food_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, food_no);

			pstmt.executeUpdate();

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
	
	public Double[] getAllNu(List<FoodRecordVO> list) {

		Double[] nu = {0.0, 0.0, 0.0, 0.0};
		for(FoodRecordVO i : list) {
			nu[0] += (findByPrimaryKey(i.getFood_no()).getStarch())*(i.getFoodr_weight());
			nu[1] += (findByPrimaryKey(i.getFood_no()).getProtein())*(i.getFoodr_weight());
			nu[2] += (findByPrimaryKey(i.getFood_no()).getFat())*(i.getFoodr_weight());
			nu[3] += (findByPrimaryKey(i.getFood_no()).getKcal())*(i.getFoodr_weight());
		}
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00"); 
		nu[0] = Double.parseDouble(df.format(nu[0]));
		nu[1] = Double.parseDouble(df.format(nu[1]));
		nu[2] = Double.parseDouble(df.format(nu[2]));
		nu[3] = Double.parseDouble(df.format(nu[3]));
		
		return nu;
	}
	


//	public static void main(String[] args) throws IOException {

//		FoodJNDIDAO dao = new FoodJNDIDAO();

		// 新增
//		FoodVO foodVO1 = new FoodVO();
//		foodVO1.setFood_name("飯糰");
//		foodVO1.setStarch(122.2);
//		foodVO1.setProtein(55.5);
//		foodVO1.setFat(66.5);
//		foodVO1.setKcal(778.5);
//
//		dao.insert(foodVO1);
//		System.out.println("新增-------------------------");

		// 修改
//		FoodVO foodVO2 = new FoodVO();
//		foodVO2.setFood_no("FOOD000010");
//		foodVO2.setFood_name("飯糰2號");
//		foodVO2.setStarch(1.2);
//		foodVO2.setProtein(5.5);
//		foodVO2.setFat(6.5);
//		foodVO2.setKcal(1.5);
//		dao.update(foodVO2);
//		System.out.println("修改-------------------------");

		// 刪除
//		dao.delete("FOOD000009");
//		System.out.println("刪除-------------------------");

		// 查詢
//		List<FoodVO> list = dao.getAll();
//		for (FoodVO aFood : list) {
//			System.out.print(aFood.getFood_no() + ",");
//			System.out.print(aFood.getFood_name() + ",");
//			System.out.print(aFood.getStarch() + ",");
//			System.out.print(aFood.getProtein() + ",");
//			System.out.print(aFood.getFat() + ",");
//			System.out.print(aFood.getKcal() + ",");
//			System.out.println();
//		}
//		System.out.println("查食物-------------------------");
			
//		FoodVO foodVO = dao.getFood_no("雞胸肉");
//			System.out.print(foodVO.getFood_no() + ",");
//			System.out.print(foodVO.getFood_name() + ",");
//			System.out.print(foodVO.getStarch() + ",");
//			System.out.print(foodVO.getProtein() + ",");
//			System.out.print(foodVO.getFat() + ",");
//			System.out.print(foodVO.getKcal() + ",");
		
//		FoodRecordService foodrecordSvc = new FoodRecordService();
//		List<FoodRecordVO> list = foodrecordSvc.getAllByDate(java.sql.Date.valueOf("2011-03-04"), "MEM0000003");
//		Double[] nu = dao.getAllNu(list);
//		System.out.println(nu[0]);
//		System.out.println(nu[1]);
//		System.out.println(nu[2]);
//		System.out.println(nu[3]);
//	}
}