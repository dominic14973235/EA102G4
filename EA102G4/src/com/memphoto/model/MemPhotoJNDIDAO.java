package com.memphoto.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemPhotoJNDIDAO implements MemPhotoDAO_interface {

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
			"UPDATE MEMBER_PHOTO set PHOTO_STATUS = 'P0' where PHOTO_NO = ?";

	@Override
	public void insert(MemPhotoVO memPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memPhotoVO.getMem_id());
			pstmt.setBytes(2, memPhotoVO.getPhoto());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memPhotoVO.getPhoto_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String photo_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, photo_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

}
