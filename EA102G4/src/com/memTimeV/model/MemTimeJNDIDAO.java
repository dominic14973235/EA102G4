package com.memTimeV.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MemTimeJNDIDAO implements MemTimeDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALL =
			"SELECT * FROM MEM_TIME WHERE MEM_ID =? ORDER BY RDATE ASC,HR DESC";
	
	private static final String GET_DATE =
			"SELECT * FROM MEM_TIME WHERE MEM_ID =? AND RDATE=? ORDER BY RDATE ASC,HR DESC";

	
	@Override
	public HashSet<MemTimeVO> getAllTimeForCheck(String mem_ID) {		
		HashSet<MemTimeVO> set = new HashSet<MemTimeVO>();
		MemTimeVO memTimeVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			
			pstmt.setString(1, mem_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memTimeVO = new MemTimeVO();
				
				memTimeVO.setMem_ID(rs.getString("MEM_ID"));
				memTimeVO.setI_class_no(rs.getString("I_CLASS_NO"));
				memTimeVO.setI_order_no(rs.getString("I_ORDER_NO"));
				memTimeVO.setrDate(rs.getDate("RDATE"));
				memTimeVO.setHr(rs.getString("HR"));
				memTimeVO.setP_coin(rs.getInt("p_coin"));
				memTimeVO.setIo_status(rs.getInt("io_status"));
				set.add(memTimeVO);
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
	public List<MemTimeVO> getAllTimeForShow(String mem_ID) {
		List<MemTimeVO> list = new ArrayList<MemTimeVO>();
		MemTimeVO memTimeVO =null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			
			pstmt.setString(1, mem_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memTimeVO = new MemTimeVO();
				
				memTimeVO.setMem_ID(rs.getString("MEM_ID"));
				memTimeVO.setI_class_no(rs.getString("I_CLASS_NO"));
				memTimeVO.setI_order_no(rs.getString("I_ORDER_NO"));
				memTimeVO.setrDate(rs.getDate("RDATE"));
				memTimeVO.setHr(rs.getString("HR"));
				memTimeVO.setP_coin(rs.getInt("p_coin"));
				memTimeVO.setIo_status(rs.getInt("io_status"));
				list.add(memTimeVO);
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
	public List<MemTimeVO> getAllTimeByDate(String mem_ID, Date rDate) {
		List<MemTimeVO> list = new ArrayList<MemTimeVO>();
		MemTimeVO memTimeVO =null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_DATE);
			
			pstmt.setString(1, mem_ID);
			pstmt.setDate(2, rDate);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memTimeVO = new MemTimeVO();
				
				memTimeVO.setMem_ID(rs.getString("MEM_ID"));
				memTimeVO.setI_class_no(rs.getString("I_CLASS_NO"));
				memTimeVO.setI_order_no(rs.getString("I_ORDER_NO"));
				memTimeVO.setrDate(rs.getDate("RDATE"));
				memTimeVO.setHr(rs.getString("HR"));
				memTimeVO.setP_coin(rs.getInt("p_coin"));
				memTimeVO.setIo_status(rs.getInt("io_status"));
				list.add(memTimeVO);
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
