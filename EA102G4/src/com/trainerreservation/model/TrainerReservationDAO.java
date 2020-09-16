package com.trainerreservation.model;

import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class TrainerReservationDAO implements TrainerReservationDAO_interface{

	
	private static DataSource ds;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/INGYM");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALL_BY_PRO_ID =
			"SELECT * FROM TRAINER_RESERVATION WHERE PRO_ID =? ORDER BY RDATE ASC,HR DESC";

	

	@Override
	public HashSet<TrainerReservationVO> getAllTimeForRetainByPro_id(String pro_id) {
		// TODO Auto-generated method stub
		HashSet<TrainerReservationVO> set = new HashSet<TrainerReservationVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_PRO_ID);
			pstmt.setString(1, pro_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TrainerReservationVO trVO  = new TrainerReservationVO();
				trVO.setPro_id(pro_id);
				trVO.setHr(rs.getString("HR"));
				trVO.setI_class_no(rs.getString("I_CLASS_NO"));
				trVO.setI_order_no(rs.getString("I_ORDER_NO"));
				trVO.setRdate(rs.getDate("RDATE"));
				set.add(trVO);
			}
			
		}catch(SQLException se) {
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
	public List<TrainerReservationVO> getAllTimeForShowByPro_id(String pro_id) {
		// TODO Auto-generated method stub
		List<TrainerReservationVO> list = new ArrayList<TrainerReservationVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TrainerReservationVO trVO =null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_PRO_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				trVO.setPro_id(pro_id);
				trVO.setHr(rs.getString("HR"));
				trVO.setI_class_no(rs.getString("I_CLASS_NO"));
				trVO.setI_order_no(rs.getString("I_ORDER_NO"));
				trVO.setRdate(rs.getDate("RDATE"));
				list.add(trVO);
			}
			
		}catch(SQLException se) {
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
