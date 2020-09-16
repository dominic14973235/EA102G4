package com.dayoff.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//plugin for testing model
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayoffJDBCDAO implements DayoffDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, dayoffVO.getPro_ID());
			pstmt.setString(2, dayoffVO.getMem_ID());
			pstmt.setDate(3, dayoffVO.getClose_date());
			pstmt.setString(4, dayoffVO.getHr());

			pstmt.executeUpdate();

//			System.out.println("insert successfully"); 			// testing
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, dayoffVO.getPro_ID());
			pstmt.setString(2, dayoffVO.getMem_ID());
			pstmt.setDate(3, dayoffVO.getClose_date());
			pstmt.setString(4, dayoffVO.getHr());
			pstmt.setString(5, dayoffVO.getCtime_no());

			pstmt.executeUpdate();

//			System.out.println("Update successfully"); //testing

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ctime_no);

			pstmt.executeUpdate();

//			System.out.println("delete successfully"); 			// testing

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public DayoffVO findByDateWithPro(String pro_ID, java.sql.Date close_date) {
		DayoffVO dayoffVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

//		Model testing
//		public static void main(String args[]) throws ParseException {
//		DayoffJDBCDAO dao = new DayoffJDBCDAO();
//		DayoffVO dayoffVO = new DayoffVO();
//

		//轉換util. date to SQL.date
//		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS"); //不一定要
//		DateFormat df = DateFormat.getDateInstance();
//		Date date = new Date();
//		date = df.parse("2020/08/17");
//		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//		
//		// 增 
//		dayoffVO.setPro_ID("PRO1000000");
//		dayoffVO.setClose_date(sqlDate);
//		dayoffVO.setMem_ID("MEM0000001");
//		dayoffVO.setHr("111111111111111111110001");
//		dao.insert(dayoffVO);
//		
		// 改
//		dayoffVO.setPro_ID("PRO1000000");
//		dayoffVO.setClose_date(sqlDate);
//		dayoffVO.setMem_ID("MEM0000001");
//		dayoffVO.setHr("111111110000000111111110");
//		dayoffVO.setCtime_no("CT1000001");
//		dao.update(dayoffVO);

//		// 刪
//		dao.delete("CT1000001");

//		dayoffVO =dao.findByProID("PRO1000000");
//		System.out.println(dayoffVO.getMem_ID());
//		
//		//查
//		dayoffVO=dao.findByDateWithPro("PRO1000000",sqlDate);
//		SimpleDateFormat utilDate = new SimpleDateFormat("yyyy-MM-dd HH");
//		df = DateFormat.getDateInstance();
//		List<DayoffVO> list = dao.getAll();
//		for (DayoffVO ct : list) {
//
//		    Calendar calendar = Calendar.getInstance();
//		    calendar.setTime(ct.getClose_date());
//		    calendar.add(Calendar.HOUR_OF_DAY, 16);
//		    
//			System.out.println("------------------------------------");
//			System.out.println(calendar);
//			System.out.println("------------------------------------");
//
//
//		}
//	}
		
}
