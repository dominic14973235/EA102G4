package com.sports.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class SportsDAO implements SportsDAO_interface {

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

	private static final String INSERT_STMT = 
			"INSERT INTO SPORTS (SPORT_NO, SPORT_NAME, UNIT1, UNIT2) "
			+ "VALUES ('SPORT'||LPAD(TO_CHAR(SPORTS_SEQ.NEXTVAL), 5, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT SPORT_NO, SPORT_NAME, UNIT1, UNIT2 FROM SPORTS order by SPORT_NO";
	private static final String GET_ONE_STMT = 
			"SELECT SPORT_NO, SPORT_NAME, UNIT1, UNIT2 FROM MEMBERS where SPORT_NO = ?";
	private static final String DELETE = 
			"DELETE FROM MEMBERS where SPORT_NO = ?";
	private static final String UPDATE = 
			"UPDATE SPORTS set SPORT_NAME = ?, UNIT1 = ?, UNIT2 = ?  where SPORT_NO = ?";

	@Override
	public void insert(SportsVO sportsVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sportsVO.getSport_name());
			pstmt.setString(2, sportsVO.getUnit1());
			pstmt.setString(3, sportsVO.getUnit2());

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
	public void update(SportsVO sportsVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, sportsVO.getSport_name());
			pstmt.setString(2, sportsVO.getUnit1());
			pstmt.setString(3, sportsVO.getUnit2());
			pstmt.setString(4, sportsVO.getSport_no());
			

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String sport_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, sport_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public SportsVO findByPrimaryKey(String sport_no) {
		SportsVO sportsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, sport_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// sportsVo 也稱為 Domain objects
				sportsVO = new SportsVO();
				sportsVO.setSport_no(rs.getString("sport_no"));
				sportsVO.setSport_name(rs.getString("sport_name"));
				sportsVO.setUnit1(rs.getString("unit1"));
				sportsVO.setUnit2(rs.getString("unit2"));
			}

			// Handle any driver errors
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
		return sportsVO;
	}

	@Override
	public List<SportsVO> getAll() {
		List<SportsVO> list = new ArrayList<SportsVO>();
		SportsVO sportsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// sportsVO 也稱為 Domain objects
				sportsVO = new SportsVO();
				sportsVO.setSport_no(rs.getString("sport_no"));
				sportsVO.setSport_name(rs.getString("sport_name"));
				sportsVO.setUnit1(rs.getString("unit1"));
				sportsVO.setUnit2(rs.getString("unit2"));
				
				list.add(sportsVO); // Store the row in the list
			}

			// Handle any driver errors
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

}
