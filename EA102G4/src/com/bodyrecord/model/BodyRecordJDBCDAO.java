package com.bodyrecord.model;

import java.util.*;

import com.mem.model.MemVO;

import java.sql.*;

public class BodyRecordJDBCDAO implements BodyRecordDAO_interface{

	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";
	
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
			"UPDATE BODY_RECORD set MEM_ID=?, MEM_HEIGHT=?, MEM_WEIGHT=?, MEM_BMI=?, MEM_OLD=?, MEM_BMR=?, FREQUENCY=?, MEM_TDEE=?, PHOTO=? where BODY_NO = ?";
	
	@Override
	public BodyRecordVO insert(BodyRecordVO bodyRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(BodyRecordVO bodyRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, bodyRecordVO.getMem_id());
			pstmt.setFloat(2, bodyRecordVO.getMem_height());
			pstmt.setFloat(3, bodyRecordVO.getMem_weight());
			pstmt.setFloat(4, bodyRecordVO.getMem_bmi());
			pstmt.setInt(5, bodyRecordVO.getMem_old());
			pstmt.setFloat(6, bodyRecordVO.getMem_bmr());
			pstmt.setInt(7, bodyRecordVO.getFrequency());
			pstmt.setFloat(8, bodyRecordVO.getMem_tdee());
			pstmt.setBytes(9, bodyRecordVO.getPhoto());
			pstmt.setString(10, bodyRecordVO.getBody_no());

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
	public void delete(String body_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, body_no);

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
	public List<BodyRecordVO> findByPrimaryKeyMemId(String mem_id) {
		List<BodyRecordVO> list = new ArrayList<BodyRecordVO>();
		BodyRecordVO bodyRecordVO = null;

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
	
	@Override
	public BodyRecordVO findByPrimaryKeyMemIdLast(String mem_id) {
		
		BodyRecordVO bodyRecordVO = null;

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
		return bodyRecordVO;
	}
	
	@Override
	public BodyRecordVO findByPrimaryKeyBodyNo(String body_no) {
		BodyRecordVO bodyRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		BodyRecordJDBCDAO dao = new BodyRecordJDBCDAO();

		// 新增
		BodyRecordVO bodyRecordVO1 = new BodyRecordVO();
		bodyRecordVO1.setMem_id("MEM0000001");
		bodyRecordVO1.setMem_height((float)180.3);
		bodyRecordVO1.setMem_weight((float)69.9);
		bodyRecordVO1.setMem_bmi((float)20.6);
		bodyRecordVO1.setMem_old(30);
		bodyRecordVO1.setMem_bmr((float)1630.2);
		bodyRecordVO1.setFrequency(1);
		bodyRecordVO1.setMem_tdee((float)1956.3);
		bodyRecordVO1.setBody_date(java.sql.Date.valueOf("2020-01-01"));
//		bodyRecordVO1.setPhoto((byte[]));
		dao.insert(bodyRecordVO1);

		// 修改
		BodyRecordVO bodyRecordVO2 = new BodyRecordVO();
		bodyRecordVO2.setMem_id("MEM0000001");
		bodyRecordVO2.setMem_height((float)180.3);
		bodyRecordVO2.setMem_weight((float)59.9);
		bodyRecordVO2.setMem_bmi((float)19.6);
		bodyRecordVO2.setMem_old(30);
		bodyRecordVO2.setMem_bmr((float)1430.2);
		bodyRecordVO2.setFrequency(0);
		bodyRecordVO2.setMem_tdee((float)1956.3);
		bodyRecordVO2.setBody_date(java.sql.Date.valueOf("2020-03-01"));
//		bodyRecordVO2.setPhoto((byte[]));
		bodyRecordVO2.setBody_no("BODY000005");
		dao.update(bodyRecordVO2);

		// 刪除
		dao.delete("BODY000005");

		// 查詢個人紀錄
		List<BodyRecordVO> list = dao.findByPrimaryKeyMemId("MEM0000006");
		for (BodyRecordVO aBodyRecord : list) {
			System.out.print(aBodyRecord.getBody_no() + ",");
			System.out.print(aBodyRecord.getMem_id() + ",");
			System.out.print(aBodyRecord.getMem_height() + ",");
			System.out.print(aBodyRecord.getMem_weight() + ",");
			System.out.print(aBodyRecord.getMem_bmi() + ",");
			System.out.print(aBodyRecord.getMem_old() + ",");
			System.out.println(aBodyRecord.getMem_bmr());
			System.out.println(aBodyRecord.getFrequency());
			System.out.println(aBodyRecord.getMem_tdee());
			System.out.println(aBodyRecord.getBody_date());
			System.out.println(aBodyRecord.getPhoto());
			
			
			System.out.println();
		}
		
		// 查詢紀錄編號
				BodyRecordVO bodyRecordVO = dao.findByPrimaryKeyBodyNo("BODY000006");
					System.out.print(bodyRecordVO.getBody_no() + ",");
					System.out.print(bodyRecordVO.getMem_id() + ",");
					System.out.print(bodyRecordVO.getMem_height() + ",");
					System.out.print(bodyRecordVO.getMem_weight() + ",");
					System.out.print(bodyRecordVO.getMem_bmi() + ",");
					System.out.print(bodyRecordVO.getMem_old() + ",");
					System.out.println(bodyRecordVO.getMem_bmr());
					System.out.println(bodyRecordVO.getFrequency());
					System.out.println(bodyRecordVO.getMem_tdee());
					System.out.println(bodyRecordVO.getBody_date());
					System.out.println(bodyRecordVO.getPhoto());
					
					
					System.out.println();
				
		// 查詢會員紀錄
		List<BodyRecordVO> listA = dao.getAll();
		for (BodyRecordVO aBodyRecord : listA) {
			System.out.print(aBodyRecord.getBody_no() + ",");
			System.out.print(aBodyRecord.getMem_id() + ",");
			System.out.print(aBodyRecord.getMem_height() + ",");
			System.out.print(aBodyRecord.getMem_weight() + ",");
			System.out.print(aBodyRecord.getMem_bmi() + ",");
			System.out.print(aBodyRecord.getMem_old() + ",");
			System.out.println(aBodyRecord.getMem_bmr());
			System.out.println(aBodyRecord.getFrequency());
			System.out.println(aBodyRecord.getMem_tdee());
			System.out.println(aBodyRecord.getBody_date());
			System.out.println(aBodyRecord.getPhoto());
			
			
			System.out.println();
		}
	}

}
