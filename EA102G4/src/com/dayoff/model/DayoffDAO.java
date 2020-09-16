package com.dayoff.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//plugin for testing model
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayoffDAO implements DayoffDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO DAYOFF(CTIME_NO, PRO_ID,MEM_ID, CLOSE_DATE,HR) VALUES ('CT'||LPAD(SEQ_CTIME_NO.NEXTVAL,7,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM DAYOFF ORDER BY CTIME_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM DAYOFF WHERE CTIME_NO =?";
	private static final String GET_ONE_PRO_STMT = "SELECT * FROM DAYOFF WHERE PRO_ID =?";
	private static final String GET_ONE_DAY_STMT = "SELECT * FROM DAYOFF WHERE PRO_ID =? AND CLOSE_DATE =?";
	private static final String DELETE = "DELETE FROM DAYOFF WHERE CTIME_NO=?";
	private static final String UPDATE = "UPDATE DAYOFF SET PRO_ID=?,MEM_ID=?, CLOSE_DATE=?, HR=? WHERE CTIME_NO=?";

	@Override
	public void insert(DayoffVO dayoffVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, dayoffVO.getPro_ID());
			pstmt.setString(2, dayoffVO.getMem_ID());
			pstmt.setDate(3, dayoffVO.getClose_date());
			pstmt.setString(4, dayoffVO.getHr());

			pstmt.executeUpdate();

//			System.out.println("insert successfully"); 			// testing
			
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
	public void update(DayoffVO dayoffVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, dayoffVO.getPro_ID());
			pstmt.setString(2, dayoffVO.getMem_ID());
			pstmt.setDate(3, dayoffVO.getClose_date());
			pstmt.setString(4, dayoffVO.getHr());
			pstmt.setString(5, dayoffVO.getCtime_no());

			pstmt.executeUpdate();

//			System.out.println("Update successfully"); //testing

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
	public void delete(String ctime_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ctime_no);

			pstmt.executeUpdate();

//			System.out.println("delete successfully"); 			// testing

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
	public DayoffVO findPrimaryKey(String ctime_no) {
		DayoffVO dayoffVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ctime_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dayoffVO = new DayoffVO();

				dayoffVO.setPro_ID(rs.getString("pro_id"));
				dayoffVO.setMem_ID(rs.getString("mem_id"));
				dayoffVO.setClose_date(rs.getDate("close_date"));
				dayoffVO.setHr(rs.getString("hr"));
				dayoffVO.setCtime_no(rs.getString("Ctime_no"));
			}

			//			System.out.println("query successfully"); //testing

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

		return dayoffVO;
	}
	
	@Override
	public List<DayoffVO> findByProID(String pro_ID) {
		List<DayoffVO> list = new ArrayList<DayoffVO>();

		DayoffVO dayoffVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PRO_STMT);

			pstmt.setString(1, pro_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dayoffVO = new DayoffVO();

				dayoffVO.setPro_ID(rs.getString("pro_id"));
				dayoffVO.setMem_ID(rs.getString("mem_id"));
				dayoffVO.setClose_date(rs.getDate("close_date"));
				dayoffVO.setHr(rs.getString("hr"));
				dayoffVO.setCtime_no(rs.getString("Ctime_no"));
				list.add(dayoffVO);

			}

			//			System.out.println("query successfully"); //testing

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

		return list;
	}
	

	@Override
	public List<DayoffVO> getAll() {
		List<DayoffVO> list = new ArrayList<DayoffVO>();
		DayoffVO dayoffVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				dayoffVO = new DayoffVO();

				dayoffVO.setCtime_no(rs.getString("ctime_no"));
				dayoffVO.setPro_ID(rs.getString("pro_id"));
				dayoffVO.setMem_ID(rs.getString("mem_id"));
				dayoffVO.setClose_date(rs.getDate("close_date"));
				dayoffVO.setHr(rs.getString("hr"));
				list.add(dayoffVO);

			}
			
//			System.out.println("query successfully"); //testing

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

		return list;
	}

	
	@Override
	public DayoffVO findByDateWithPro(String pro_ID, java.sql.Date close_date) {
		DayoffVO dayoffVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_DAY_STMT);

			pstmt.setString(1, pro_ID);
			pstmt.setDate(2, close_date);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dayoffVO = new DayoffVO();

				dayoffVO.setPro_ID(rs.getString("pro_id"));
				dayoffVO.setMem_ID(rs.getString("mem_id"));
				dayoffVO.setClose_date(rs.getDate("close_date"));
				dayoffVO.setHr(rs.getString("hr"));
			}

			//			System.out.println("query successfully"); //testing

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

		return dayoffVO;
	}	
}
