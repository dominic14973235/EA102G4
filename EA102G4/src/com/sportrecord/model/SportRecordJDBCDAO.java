package com.sportrecord.model;

import java.util.*;

import com.mem.model.MemVO;

import java.sql.*;

public class SportRecordJDBCDAO implements SportRecordDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sportRecordVO.getSport_no());
			pstmt.setString(2, sportRecordVO.getMem_id());
			pstmt.setDate(3, sportRecordVO.getRecord_date());
			pstmt.setFloat(4, sportRecordVO.getRecord1());
			pstmt.setFloat(5, sportRecordVO.getRecord2());

			pstmt.executeUpdate();
			return null;
			// Handle any driver errors
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
	public void update(SportRecordVO sportRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, sportRecordVO.getSport_no());
			pstmt.setString(2, sportRecordVO.getMem_id());
			pstmt.setDate(3, sportRecordVO.getRecord_date());
			pstmt.setFloat(4, sportRecordVO.getRecord1());
			pstmt.setFloat(5, sportRecordVO.getRecord2());
			pstmt.setString(6, sportRecordVO.getRecord_no());

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
	public void delete(String record_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, record_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public List<SportRecordVO> findByPrimaryKeyMemId(String mem_id) {
		List<SportRecordVO> list = new ArrayList<SportRecordVO>();
		SportRecordVO sportRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public SportRecordVO findByPrimaryKeyMemIdLast(String mem_id) {
		
		SportRecordVO sportRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_MEM_ID);

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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return sportRecordVO;
	}
	
	@Override
	public SportRecordVO findByPrimaryKeyRecordNo(String record_no) {
		
		SportRecordVO sportRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args) {

		SportRecordJDBCDAO dao = new SportRecordJDBCDAO();

		// 新增
		SportRecordVO sportRecordVO1 = new SportRecordVO();
		sportRecordVO1.setSport_no("SPORT00005");
		sportRecordVO1.setMem_id("MEM0000005");
		sportRecordVO1.setRecord_date(java.sql.Date.valueOf("2020-04-20"));
		sportRecordVO1.setRecord1(12);

		dao.insert(sportRecordVO1);

		// 修改
		SportRecordVO sportRecordVO2 = new SportRecordVO();
		sportRecordVO2.setSport_no("SPORT00003");
		sportRecordVO2.setMem_id("MEM0000003");
		sportRecordVO2.setRecord_date(java.sql.Date.valueOf("2020-04-20"));
		sportRecordVO2.setRecord1(20);
		sportRecordVO2.setRecord2(15);
		sportRecordVO2.setRecord_no("SREC000003");
		dao.update(sportRecordVO2);

		// 刪除
		dao.delete("SREC000002");

		// 查詢
		List<SportRecordVO> listOne = dao.findByPrimaryKeyMemId("MEM0000006");
		for (SportRecordVO aSportRecord : listOne) {
			System.out.print(aSportRecord.getRecord_no() + ",");
			System.out.print(aSportRecord.getSport_no() + ",");
			System.out.print(aSportRecord.getMem_id() + ",");
			System.out.print(aSportRecord.getRecord_date() + ",");
			System.out.print(aSportRecord.getRecord1() + ",");
			System.out.print(aSportRecord.getRecord2() + ",");

			System.out.println();
		}

		// 查詢
		List<SportRecordVO> listAll = dao.getAll();
		for (SportRecordVO aSportRecord : listAll) {
			System.out.print(aSportRecord.getRecord_no() + ",");
			System.out.print(aSportRecord.getSport_no() + ",");
			System.out.print(aSportRecord.getMem_id() + ",");
			System.out.print(aSportRecord.getRecord_date() + ",");
			System.out.print(aSportRecord.getRecord1() + ",");
			System.out.print(aSportRecord.getRecord2() + ",");
			System.out.println();
		}
	}

}
