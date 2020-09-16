package com.trainerPublicV.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TrainerPublicDAO implements TrainerPublicDAO_interface{
	
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
	
	private static final String GET_ALL =
			"SELECT * FROM TRAINER_PUBLIC WHERE PRO_ID =? ORDER BY RDATE ASC,HR DESC";
	private static final String GET_ONE =
			"SELECT * FROM TRAINER_PUBLIC WHERE I_ORDER_NO=?";
	private static final String GET_DAY =
			"SELECT * FROM TRAINER_PUBLIC WHERE rDate=? AND PRO_ID=?";

	
	
	@Override
	public HashSet<TrainerPublicVO> getAllTimeForCheck(String pro_ID) {		
		HashSet<TrainerPublicVO> set = new HashSet<TrainerPublicVO>();
		TrainerPublicVO trainerPublicVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			
			pstmt.setString(1, pro_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				trainerPublicVO = new TrainerPublicVO();
				
				trainerPublicVO.setPro_ID(rs.getString("PRO_ID"));
				trainerPublicVO.setI_class_no(rs.getString("I_CLASS_NO"));
				trainerPublicVO.setI_order_no(rs.getString("I_ORDER_NO"));
				trainerPublicVO.setrDate(rs.getDate("RDATE"));
				trainerPublicVO.setHr(rs.getString("HR"));
				trainerPublicVO.setStatus(rs.getInt("IO_STATUS"));
				set.add(trainerPublicVO);
			}
			
			// Handle any SQL errors
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return set;
	}

	@Override
	public List<TrainerPublicVO> getAllTimeForShow(String pro_ID) {
		List<TrainerPublicVO> list = new ArrayList<TrainerPublicVO>();
		TrainerPublicVO trainerPublicVO =null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			
			pstmt.setString(1, pro_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				trainerPublicVO = new TrainerPublicVO();
				
				trainerPublicVO.setPro_ID(rs.getString("PRO_ID"));
				trainerPublicVO.setI_class_no(rs.getString("I_CLASS_NO"));
				trainerPublicVO.setI_order_no(rs.getString("I_ORDER_NO"));
				trainerPublicVO.setrDate(rs.getDate("RDATE"));
				trainerPublicVO.setHr(rs.getString("HR"));
				trainerPublicVO.setStatus(rs.getInt("IO_STATUS"));

				
				list.add(trainerPublicVO);
			}
			
			// Handle any SQL errors
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public TrainerPublicVO getOneDay(String i_order_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TrainerPublicVO trainerPublicVO =null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			
			pstmt.setString(1, i_order_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				trainerPublicVO = new TrainerPublicVO();
				trainerPublicVO.setPro_ID(rs.getString("PRO_ID"));
				trainerPublicVO.setI_class_no(rs.getString("I_CLASS_NO"));
				trainerPublicVO.setI_order_no(rs.getString("I_ORDER_NO"));
				trainerPublicVO.setrDate(rs.getDate("RDATE"));
				trainerPublicVO.setHr(rs.getString("HR"));
				trainerPublicVO.setStatus(rs.getInt("IO_STATUS"));

			}
			

			// Handle any SQL errors
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return trainerPublicVO;
	}

	@Override
	public List<TrainerPublicVO> getAllDay(Date rDate, String pro_ID) {
		List<TrainerPublicVO> list = new ArrayList<TrainerPublicVO>();
		TrainerPublicVO trainerPublicVO =null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_DAY);
			
			pstmt.setDate(1, rDate);
			pstmt.setString(2,pro_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				trainerPublicVO = new TrainerPublicVO();
				
				trainerPublicVO.setPro_ID(rs.getString("PRO_ID"));
				trainerPublicVO.setI_class_no(rs.getString("I_CLASS_NO"));
				trainerPublicVO.setI_order_no(rs.getString("I_ORDER_NO"));
				trainerPublicVO.setrDate(rs.getDate("RDATE"));
				trainerPublicVO.setHr(rs.getString("HR"));
				trainerPublicVO.setStatus(rs.getInt("IO_STATUS"));

				list.add(trainerPublicVO);
			}
			
			// Handle any SQL errors
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

}
