package com.memphoto.model;

import java.util.*;

import com.mem.model.MemVO;

import java.sql.*;

public class MemPhotoJDBCDAO implements MemPhotoDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO MEMBER_PHOTO (PHOTO_NO, MEM_ID, PHOTO_STATUS, PHOTO) "
			+ "VALUES ('MEMP'||LPAD(TO_CHAR(MEM_PHOTO_SEQ.NEXTVAL), 6, '0'), ?, 'P1', ?)";
	private static final String GET_ALL_STMT = 
			"SELECT PHOTO_NO, MEM_ID, PHOTO_STATUS, PHOTO FROM MEMBER_PHOTO order by PHOTO_NO";
	private static final String GET_ONE_STMT = 
			"SELECT PHOTO_NO, MEM_ID, PHOTO_STATUS, PHOTO FROM MEMBER_PHOTO where MEM_ID = ?";
	private static final String GET_ONE_STMT_PHOTO_STATUS = 
			"SELECT PHOTO FROM MEMBER_PHOTO where MEM_ID = ? AND PHOTO_STATUS = 'P1'";
	private static final String GET_ONE_STMT_PHOTO_NO = 
			"SELECT PHOTO_NO, MEM_ID, PHOTO_STATUS, PHOTO FROM MEMBER_PHOTO where PHOTO_NO = ?";
	private static final String DELETE = 
			"DELETE FROM MEMBER_PHOTO where PHOTO_NO = ?";
	private static final String UPDATE = 
			"UPDATE MEMBER_PHOTO set MEM_ID = ?, PHOTO_STATUS = 'P1', PHOTO = ? where PHOTO_NO = ?";

	@Override
	public void insert(MemPhotoVO memPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memPhotoVO.getMem_id());
			pstmt.setBytes(2, memPhotoVO.getPhoto());

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
	public void update(MemPhotoVO memPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memPhotoVO.getMem_id());
			pstmt.setBytes(2, memPhotoVO.getPhoto());
			pstmt.setString(3, memPhotoVO.getPhoto_no());

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
	public void delete(String photo_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, photo_no);

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
	public List<MemPhotoVO> findByPrimaryKey(String mem_id) {
		List<MemPhotoVO> list = new ArrayList<MemPhotoVO>();
		MemPhotoVO memPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memPhotoVO 也稱為 Domain objects
				memPhotoVO = new MemPhotoVO();
				memPhotoVO.setPhoto_no(rs.getString("photo_no"));
				memPhotoVO.setMem_id(rs.getString("mem_id"));
				memPhotoVO.setPhoto_status(rs.getString("photo_status"));
				memPhotoVO.setPhoto(rs.getBytes("photo"));

				list.add(memPhotoVO); // Store the row in the list
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
	public MemPhotoVO findByPrimaryKeyByStatus(String mem_id) {
		
		MemPhotoVO memPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_PHOTO_STATUS);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memPhotoVO 也稱為 Domain objects
				memPhotoVO = new MemPhotoVO();
				memPhotoVO.setPhoto_no(rs.getString("photo_no"));
				memPhotoVO.setMem_id(rs.getString("mem_id"));
				memPhotoVO.setPhoto_status(rs.getString("photo_status"));
				memPhotoVO.setPhoto(rs.getBytes("photo"));
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
		return memPhotoVO;
	}
	
	@Override
	public MemPhotoVO findByPrimaryKeyPhotoNo(String photo_no) {
		
		MemPhotoVO memPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_PHOTO_NO);

			pstmt.setString(1, photo_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memPhotoVO 也稱為 Domain objects
				memPhotoVO = new MemPhotoVO();
				memPhotoVO.setPhoto_no(rs.getString("photo_no"));
				memPhotoVO.setMem_id(rs.getString("mem_id"));
				memPhotoVO.setPhoto_status(rs.getString("photo_status"));
				memPhotoVO.setPhoto(rs.getBytes("photo"));

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
		return memPhotoVO;
	}

	@Override
	public List<MemPhotoVO> getAll() {
		List<MemPhotoVO> list = new ArrayList<MemPhotoVO>();
		MemPhotoVO memPhotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memPhotoVO 也稱為 Domain objects
				memPhotoVO = new MemPhotoVO();
				memPhotoVO.setPhoto_no(rs.getString("photo_no"));
				memPhotoVO.setMem_id(rs.getString("mem_id"));
				memPhotoVO.setPhoto_status(rs.getString("photo_status"));
				memPhotoVO.setPhoto(rs.getBytes("photo"));
				list.add(memPhotoVO); // Store the row in the list
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

		MemPhotoJDBCDAO dao = new MemPhotoJDBCDAO();

		// 新增
		MemPhotoVO memPhotoVO1 = new MemPhotoVO();
		memPhotoVO1.setMem_id("MEM0000005");
//		memPhotoVO1.setPhoto();

		dao.insert(memPhotoVO1);

		// 修改
		MemPhotoVO memPhotoVO2 = new MemPhotoVO();
		memPhotoVO2.setMem_id("MEM0000003");
		memPhotoVO2.setPhoto_status("P1");
//		memPhotoVO2.setPhoto();
		memPhotoVO2.setPhoto_no("MEMP000007");
//		
//		dao.update(memPhotoVO2);

		// 刪除
		dao.delete("MEMP000006");

		// 查詢
		List<MemPhotoVO> listOne = dao.findByPrimaryKey("MEM0000006");
		for (MemPhotoVO aMemPhoto : listOne) {
			System.out.print(aMemPhoto.getPhoto_no() + ",");
			System.out.print(aMemPhoto.getMem_id() + ",");
			System.out.print(aMemPhoto.getPhoto_status() + ",");
			System.out.print(aMemPhoto.getPhoto() + ",");

			System.out.println();
		}

		// 查詢
		List<MemPhotoVO> listAll = dao.getAll();
		for (MemPhotoVO aMemPhoto : listAll) {
			System.out.print(aMemPhoto.getPhoto_no() + ",");
			System.out.print(aMemPhoto.getMem_id() + ",");
			System.out.print(aMemPhoto.getPhoto_status() + ",");
			System.out.print(aMemPhoto.getPhoto() + ",");

			System.out.println();
		}
	}

}
