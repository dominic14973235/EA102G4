package com.sports.model;

import java.util.*;
import java.sql.*;

public class SportsJDBCDAO implements SportsDAO_interface{

	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";
	
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sportsVO.getSport_name());
			pstmt.setString(2, sportsVO.getUnit1());
			pstmt.setString(3, sportsVO.getUnit2());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, sportsVO.getSport_name());
			pstmt.setString(2, sportsVO.getUnit1());
			pstmt.setString(3, sportsVO.getUnit2());
			pstmt.setString(4, sportsVO.getSport_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String sport_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, sport_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public SportsVO findByPrimaryKey(String sport_no) {
		SportsVO sportsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	
	public static void main(String[] args) {

		SportsJDBCDAO dao = new SportsJDBCDAO();

		// 新增
		SportsVO sportsVO1 = new SportsVO();
		sportsVO1.setSport_name("吳永志1");
		sportsVO1.setUnit1("MAN");
		sportsVO1.setUnit2("MAN");
		
		dao.insert(sportsVO1);

		// 修改
		SportsVO sportsVO2 = new SportsVO();
		sportsVO2.setSport_name("吳永志2");
		sportsVO2.setUnit1("MANAGER");
		sportsVO2.setUnit2("MANAGER");
		sportsVO2.setSport_no("SPORT00002");

		dao.update(sportsVO2);

		// 刪除
		dao.delete("SPORT00005");

		// 查詢
		SportsVO sportsVO3 = dao.findByPrimaryKey("SPORT00006");
		System.out.print(sportsVO3.getSport_no() + ",");
		System.out.print(sportsVO3.getSport_name() + ",");
		System.out.print(sportsVO3.getUnit1() + ",");
		System.out.print(sportsVO3.getUnit2() + ",");
		
		System.out.println("---------------------");

		// 查詢
		List<SportsVO> list = dao.getAll();
		for (SportsVO aSport : list) {
			System.out.print(aSport.getSport_no() + ",");
			System.out.print(aSport.getSport_name() + ",");
			System.out.print(aSport.getUnit1() + ",");
			System.out.print(aSport.getUnit2() + ",");
			
			System.out.println();
		}
	}

}
