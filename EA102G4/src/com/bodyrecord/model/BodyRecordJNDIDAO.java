package com.bodyrecord.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class BodyRecordJNDIDAO implements BodyRecordDAO_interface{

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
				"INSERT INTO BODY_RECORD (BODY_NO, MEM_ID, MEM_HEIGHT, MEM_WEIGHT, MEM_BMI, MEM_OLD, MEM_BMR, FREQUENCY, MEM_TDEE, BODY_DATE, PHOTO) "
				+ "VALUES ('BODY'||LPAD(TO_CHAR(BODY_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
				"SELECT BODY_NO, MEM_ID, MEM_HEIGHT, MEM_WEIGHT, MEM_BMI, MEM_OLD, MEM_BMR, FREQUENCY, MEM_TDEE,to_char(BODY_DATE,'yyyy-mm-dd')BODY_DATE,PHOTO FROM BODY_RECORD order by BODY_NO";
		private static final String GET_ONE_STMT_MEM_ID = 
				"SELECT BODY_NO, MEM_ID, MEM_HEIGHT, MEM_WEIGHT, MEM_BMI, MEM_OLD, MEM_BMR, FREQUENCY, MEM_TDEE,to_char(BODY_DATE,'yyyy-mm-dd')BODY_DATE,PHOTO FROM BODY_RECORD where MEM_ID = ? order by BODY_DATE DESC,BODY_NO DESC";
		private static final String GET_ONE_STMT_MEM_ID_LAST = 
				"SELECT BODY_NO, MEM_ID, MEM_HEIGHT, MEM_WEIGHT, MEM_BMI, MEM_OLD, MEM_BMR, FREQUENCY, MEM_TDEE,to_char(BODY_DATE,'yyyy-mm-dd')BODY_DATE,PHOTO FROM BODY_RECORD where MEM_ID = ? AND ROWNUM < 2 order by BODY_NO DESC";
		private static final String GET_ONE_STMT_BODY_NO = 
				"SELECT BODY_NO, MEM_ID, MEM_HEIGHT, MEM_WEIGHT, MEM_BMI, MEM_OLD, MEM_BMR, FREQUENCY, MEM_TDEE,to_char(BODY_DATE,'yyyy-mm-dd')BODY_DATE,PHOTO FROM BODY_RECORD where BODY_NO = ?";
		private static final String DELETE = 
				"DELETE FROM BODY_RECORD where BODY_NO = ?";
		private static final String UPDATE = 
				"UPDATE BODY_RECORD set MEM_ID=?, MEM_HEIGHT=?, MEM_WEIGHT=?, MEM_BMI=?, MEM_OLD=?, MEM_BMR=?, FREQUENCY=?, MEM_TDEE=?, BODY_DATE=?, PHOTO=? where BODY_NO = ?";
		
	@Override
	public BodyRecordVO insert(BodyRecordVO bodyRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, bodyRecordVO.getMem_id());
			pstmt.setFloat(2, bodyRecordVO.getMem_height());
			pstmt.setFloat(3, bodyRecordVO.getMem_weight());
			pstmt.setFloat(4, bodyRecordVO.getMem_bmi());
			pstmt.setInt(5, bodyRecordVO.getMem_old());
			pstmt.setFloat(6, bodyRecordVO.getMem_bmr());
			pstmt.setInt(7, bodyRecordVO.getFrequency());
			pstmt.setFloat(8, bodyRecordVO.getMem_tdee());
			pstmt.setDate(9, bodyRecordVO.getBody_date());
			pstmt.setBytes(10, bodyRecordVO.getPhoto());

			pstmt.executeUpdate();

			BodyRecordService bodyRecordSvc = new BodyRecordService();
			BodyRecordVO lastOne = bodyRecordSvc.getOneBodyRecordByMemIdLast(bodyRecordVO.getMem_id());
			
			bodyRecordVO.setBody_no(lastOne.getBody_no());
			
			return bodyRecordVO;
			
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
	public void update(BodyRecordVO bodyRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, bodyRecordVO.getMem_id());
			pstmt.setFloat(2, bodyRecordVO.getMem_height());
			pstmt.setFloat(3, bodyRecordVO.getMem_weight());
			pstmt.setFloat(4, bodyRecordVO.getMem_bmi());
			pstmt.setInt(5, bodyRecordVO.getMem_old());
			pstmt.setFloat(6, bodyRecordVO.getMem_bmr());
			pstmt.setInt(7, bodyRecordVO.getFrequency());
			pstmt.setFloat(8, bodyRecordVO.getMem_tdee());
			pstmt.setDate(9, bodyRecordVO.getBody_date());
			pstmt.setBytes(10, bodyRecordVO.getPhoto());
			pstmt.setString(11, bodyRecordVO.getBody_no());

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
	public void delete(String body_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, body_no);

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
	public List<BodyRecordVO> findByPrimaryKeyMemId(String mem_id) {
		List<BodyRecordVO> list = new ArrayList<BodyRecordVO>();
		BodyRecordVO bodyRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_MEM_ID);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// bodyRecordVO 也稱為 Domain objects
				bodyRecordVO = new BodyRecordVO();
				bodyRecordVO.setBody_no(rs.getString("body_no"));
				bodyRecordVO.setMem_id(rs.getString("mem_id"));
				bodyRecordVO.setMem_height(rs.getFloat("mem_height"));
				bodyRecordVO.setMem_weight(rs.getFloat("mem_weight"));
				bodyRecordVO.setMem_bmi(rs.getFloat("mem_bmi"));
				bodyRecordVO.setMem_old(rs.getInt("Mem_old"));
				bodyRecordVO.setMem_bmr(rs.getFloat("mem_bmr"));
				bodyRecordVO.setFrequency(rs.getInt("frequency"));
				bodyRecordVO.setMem_tdee(rs.getFloat("mem_tdee"));
				bodyRecordVO.setBody_date(rs.getDate("body_date"));
				bodyRecordVO.setPhoto(rs.getBytes("photo"));
				list.add(bodyRecordVO); // Store the row in the list
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
	public BodyRecordVO findByPrimaryKeyMemIdLast(String mem_id) {
		
		
		BodyRecordVO bodyRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_MEM_ID_LAST);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// bodyRecordVO 也稱為 Domain objects
				bodyRecordVO = new BodyRecordVO();
				bodyRecordVO.setBody_no(rs.getString("body_no"));
				bodyRecordVO.setMem_id(rs.getString("mem_id"));
				bodyRecordVO.setMem_height(rs.getFloat("mem_height"));
				bodyRecordVO.setMem_weight(rs.getFloat("mem_weight"));
				bodyRecordVO.setMem_bmi(rs.getFloat("mem_bmi"));
				bodyRecordVO.setMem_old(rs.getInt("Mem_old"));
				bodyRecordVO.setMem_bmr(rs.getFloat("mem_bmr"));
				bodyRecordVO.setFrequency(rs.getInt("frequency"));
				bodyRecordVO.setMem_tdee(rs.getFloat("mem_tdee"));
				bodyRecordVO.setBody_date(rs.getDate("body_date"));
				bodyRecordVO.setPhoto(rs.getBytes("photo"));
				
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
		return bodyRecordVO;
	}
	
	@Override
	public BodyRecordVO findByPrimaryKeyBodyNo(String body_no) {
		BodyRecordVO bodyRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BODY_NO);

			pstmt.setString(1, body_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// bodyRecordVo 也稱為 Domain objects
				bodyRecordVO = new BodyRecordVO();
				bodyRecordVO.setBody_no(rs.getString("body_no"));
				bodyRecordVO.setMem_id(rs.getString("mem_id"));
				bodyRecordVO.setMem_height(rs.getFloat("mem_height"));
				bodyRecordVO.setMem_weight(rs.getFloat("mem_weight"));
				bodyRecordVO.setMem_bmi(rs.getFloat("mem_bmi"));
				bodyRecordVO.setMem_old(rs.getInt("Mem_old"));
				bodyRecordVO.setMem_bmr(rs.getFloat("mem_bmr"));
				bodyRecordVO.setFrequency(rs.getInt("frequency"));
				bodyRecordVO.setMem_tdee(rs.getFloat("mem_tdee"));
				bodyRecordVO.setBody_date(rs.getDate("body_date"));
				bodyRecordVO.setPhoto(rs.getBytes("photo"));
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
		return bodyRecordVO;
	}

	@Override
	public List<BodyRecordVO> getAll() {
		List<BodyRecordVO> list = new ArrayList<BodyRecordVO>();
		BodyRecordVO bodyRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// bodyRecordVO 也稱為 Domain objects
				bodyRecordVO = new BodyRecordVO();
				bodyRecordVO.setBody_no(rs.getString("body_no"));
				bodyRecordVO.setMem_id(rs.getString("mem_id"));
				bodyRecordVO.setMem_height(rs.getFloat("mem_height"));
				bodyRecordVO.setMem_weight(rs.getFloat("mem_weight"));
				bodyRecordVO.setMem_bmi(rs.getFloat("mem_bmi"));
				bodyRecordVO.setMem_old(rs.getInt("Mem_old"));
				bodyRecordVO.setMem_bmr(rs.getFloat("mem_bmr"));
				bodyRecordVO.setFrequency(rs.getInt("frequency"));
				bodyRecordVO.setMem_tdee(rs.getFloat("mem_tdee"));
				bodyRecordVO.setBody_date(rs.getDate("body_date"));
				bodyRecordVO.setPhoto(rs.getBytes("photo"));
				list.add(bodyRecordVO); // Store the row in the list
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
