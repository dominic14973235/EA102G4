package com.sportrecord.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class SportRecordJNDIDAO implements SportRecordDAO_interface {

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
			"INSERT INTO SPORT_RECORD (RECORD_NO, SPORT_NO, MEM_ID, RECORD_DATE, RECORD1, RECORD2) "
			+ "VALUES ('SREC'||LPAD(TO_CHAR(SPORT_RECORD_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT RECORD_NO, SPORT_NO, MEM_ID, RECORD_DATE, RECORD1, RECORD2 FROM SPORT_RECORD order by RECORD_NO";
	private static final String GET_ONE_STMT_MEM_ID = 
			"SELECT RECORD_NO, SPORT_NO, MEM_ID, RECORD_DATE, RECORD1, RECORD2 FROM SPORT_RECORD where MEM_ID = ? ORDER BY RECORD_DATE DESC, RECORD_NO DESC";
	private static final String GET_ONE_STMT_MEM_ID_LAST = 
			"SELECT RECORD_NO, SPORT_NO, MEM_ID, RECORD_DATE, RECORD1, RECORD2 FROM SPORT_RECORD where MEM_ID = ? ORDER BY RECORD_NO";
	private static final String GET_ONE_STMT_RECORD_NO = 
			"SELECT RECORD_NO, SPORT_NO, MEM_ID, RECORD_DATE, RECORD1, RECORD2 FROM SPORT_RECORD where RECORD_NO = ?";
	private static final String DELETE = 
			"DELETE FROM SPORT_RECORD where RECORD_NO = ?";
	private static final String UPDATE = 
			"UPDATE SPORT_RECORD set SPORT_NO=?, MEM_ID=? , RECORD_DATE=?, RECORD1 =?, RECORD2 =? where RECORD_NO = ?";
	@Override
	public SportRecordVO insert(SportRecordVO sportRecordVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sportRecordVO.getSport_no());
			pstmt.setString(2, sportRecordVO.getMem_id());
			pstmt.setDate(3, sportRecordVO.getRecord_date());
			pstmt.setFloat(4, sportRecordVO.getRecord1());
			pstmt.setFloat(5, sportRecordVO.getRecord2());
			
			
			pstmt.executeUpdate();
			return null;
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
	public void update(SportRecordVO sportRecordVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, sportRecordVO.getSport_no());
			pstmt.setString(2, sportRecordVO.getMem_id());
			pstmt.setDate(3, sportRecordVO.getRecord_date());
			pstmt.setFloat(4, sportRecordVO.getRecord1());
			pstmt.setFloat(5, sportRecordVO.getRecord2());
			pstmt.setString(6, sportRecordVO.getRecord_no());

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
	public void delete(String record_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, record_no);

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
	public List<SportRecordVO> findByPrimaryKeyMemId(String mem_id) {
		List<SportRecordVO> list = new ArrayList<SportRecordVO>();
		SportRecordVO sportRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_MEM_ID);
			
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// sportRecordVO 也稱為 Domain objects
				sportRecordVO = new SportRecordVO();
				sportRecordVO.setRecord_no(rs.getString("record_no"));
				sportRecordVO.setSport_no(rs.getString("photo_no"));
				sportRecordVO.setMem_id(rs.getString("mem_id"));
				sportRecordVO.setRecord_date(rs.getDate("record_date"));
				sportRecordVO.setRecord1(rs.getFloat("record1"));
				sportRecordVO.setRecord2(rs.getFloat("record2"));
				
				list.add(sportRecordVO); // Store the row in the list
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
	
	@Override
	public SportRecordVO findByPrimaryKeyMemIdLast(String mem_id) {
		
		SportRecordVO sportRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_MEM_ID_LAST);
			
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();

		
			while (rs.next()) {
				// sportRecordVO 也稱為 Domain objects
			sportRecordVO = new SportRecordVO();
			sportRecordVO.setRecord_no(rs.getString("record_no"));
			sportRecordVO.setSport_no(rs.getString("sport_no"));
			sportRecordVO.setMem_id(rs.getString("mem_id"));
			sportRecordVO.setRecord_date(rs.getDate("record_date"));
			sportRecordVO.setRecord1(rs.getFloat("record1"));
			sportRecordVO.setRecord2(rs.getFloat("record2"));
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
		return sportRecordVO;
	}
	
	@Override
	public SportRecordVO findByPrimaryKeyRecordNo(String record_no) {
		
		SportRecordVO sportRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_RECORD_NO);
			
			pstmt.setString(1, record_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// sportRecordVO 也稱為 Domain objects
				sportRecordVO = new SportRecordVO();
				sportRecordVO.setRecord_no(rs.getString("record_no"));
				sportRecordVO.setSport_no(rs.getString("photo_no"));
				sportRecordVO.setMem_id(rs.getString("mem_id"));
				sportRecordVO.setRecord_date(rs.getDate("record_date"));
				sportRecordVO.setRecord1(rs.getFloat("record1"));
				sportRecordVO.setRecord2(rs.getFloat("record2"));
				
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
		return sportRecordVO;
	}

	@Override
	public List<SportRecordVO> getAll() {
		List<SportRecordVO> list = new ArrayList<SportRecordVO>();
		SportRecordVO sportRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// sportRecordVO 也稱為 Domain objects
				sportRecordVO = new SportRecordVO();
				sportRecordVO.setRecord_no(rs.getString("record_no"));
				sportRecordVO.setSport_no(rs.getString("photo_no"));
				sportRecordVO.setMem_id(rs.getString("mem_id"));
				sportRecordVO.setRecord_date(rs.getDate("record_date"));
				sportRecordVO.setRecord1(rs.getFloat("record1"));
				sportRecordVO.setRecord2(rs.getFloat("record2"));
				
				list.add(sportRecordVO); // Store the row in the list
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
