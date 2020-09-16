package com.license.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LicenseDAO implements LicenseDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO LICENSE (LICENSE_NO, PRO_ID, LIC_NAME,NO_REG,L_PIC) VALUES ('LIC'||LPAD(SEQ_LIC_NO.nextval,7,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM LICENSE ORDER BY LICENSE_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM LICENSE WHERE LICENSE_NO =?";
	private static final String GET_PRO_STMT = "SELECT * FROM LICENSE WHERE PRO_ID =?";
	private static final String GET_ONE_PHOTO_STMT = "SELECT L_PIC FROM LICENSE WHERE LICENSE_NO =?";
	private static final String DELETE = "DELETE FROM LICENSE WHERE LICENSE_NO=?";
	private static final String UPDATE = "UPDATE LICENSE SET PRO_ID=?, LIC_NAME=?, NO_REG=?, L_PIC=? WHERE LICENSE_NO=?";

	@Override
	public void insert(LicenseVO licenseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			// 需要增加照片的上傳 getL_pic;
			pstmt.setString(1, licenseVO.getPro_ID());
			pstmt.setString(2, licenseVO.getLic_name());
			pstmt.setString(3, licenseVO.getNo_reg());
			pstmt.setBytes(4, licenseVO.getL_pic());

			pstmt.executeUpdate();
			// testing
//			System.out.println("insert successfully");
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
	public void update(LicenseVO licenseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, licenseVO.getPro_ID());
			pstmt.setString(2, licenseVO.getLic_name());
			pstmt.setString(3, licenseVO.getNo_reg());
			pstmt.setBytes(4, licenseVO.getL_pic());
			pstmt.setString(5, licenseVO.getLicense_no());

			pstmt.executeUpdate();

			// testing
//			System.out.println("Update successfully");

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
	public void delete(String license_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, license_no);

			pstmt.executeUpdate();

			// testing
			System.out.println("delete successfully");

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
	public LicenseVO findPrimaryKey(String license_no) {

		LicenseVO licenseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, license_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				licenseVO = new LicenseVO();

				licenseVO.setLicense_no(rs.getString("license_no"));
				licenseVO.setPro_ID(rs.getString("Pro_id"));
				licenseVO.setLic_name(rs.getString("lic_name"));
				licenseVO.setNo_reg(rs.getString("no_reg"));
				licenseVO.setL_pic(rs.getBytes("l_pic"));
			}
			// testing
//			System.out.println("query successfully");

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

		return licenseVO;
	}

	@Override
	public List<LicenseVO> getAll() {
		List<LicenseVO> list = new ArrayList<LicenseVO>();
		LicenseVO licenseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			// LICENSE_NO, PRO_ID, LIC_NAME,NO_REG
			while (rs.next()) {
				licenseVO = new LicenseVO();
				// 需要增加照片的上傳 getL_pic;
				licenseVO.setLicense_no(rs.getString("license_no"));
				licenseVO.setPro_ID(rs.getString("Pro_id"));
				licenseVO.setLic_name(rs.getString("lic_name"));
				licenseVO.setNo_reg(rs.getString("no_reg"));
				licenseVO.setL_pic(rs.getBytes("l_pic"));
				list.add(licenseVO);

			}
			// testing
//			System.out.println("query successfully");

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
	public LicenseVO getPhoto(String license_no) {

		LicenseVO licenseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PHOTO_STMT);

			pstmt.setString(1, license_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				licenseVO = new LicenseVO();
				licenseVO.setL_pic(rs.getBytes("l_pic"));
			}
			// testing
//			System.out.println("query successfully");

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

		return licenseVO;
	}
	
	@Override
	public List<LicenseVO> findByPro(String pro_ID) {
		
		List<LicenseVO> list = new ArrayList<LicenseVO>();
		LicenseVO licenseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_PRO_STMT);

			pstmt.setString(1, pro_ID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				licenseVO = new LicenseVO();
				licenseVO.setLicense_no(rs.getString("license_no"));
				licenseVO.setPro_ID(rs.getString("Pro_id"));
				licenseVO.setLic_name(rs.getString("lic_name"));
				licenseVO.setNo_reg(rs.getString("no_reg"));
				licenseVO.setL_pic(rs.getBytes("l_pic"));
				list.add(licenseVO);

			}
			// testing
//			System.out.println("query successfully");

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
	public void insert2(LicenseVO licenseVO, Connection con) {
		PreparedStatement pstmt = null;
		
		try{
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, licenseVO.getPro_ID());
			pstmt.setString(2, licenseVO.getLic_name());
			pstmt.setString(3, licenseVO.getNo_reg());
			pstmt.setBytes(4, licenseVO.getL_pic());
			
			pstmt.executeUpdate();

		}catch (SQLException se) {
			if(con!=null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-license");
					con.rollback();
				}catch(SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}


//	public static void main(String args[]) {
//		LicenseJDBCDAO dao = new LicenseJDBCDAO();
//		LicenseVO licenseVO = new LicenseVO();

		// 增
//		licenseVO.setPro_ID("PRO1000007");
//		licenseVO.setLic_name("幹你老師不用命");
//		licenseVO.setNo_reg("b123456");
//		dao.insert(licenseVO);
//		
//		//改
//		licenseVO.setPro_ID("PRO1000008");
//		licenseVO.setLic_name("瑜珈2三");
//		licenseVO.setNo_reg("bbc123456");
//		licenseVO.setLicense_no("LIC1000011");
//		dao.update(licenseVO);
//		//刪
//		dao.delete("LIC1000021");
//		//查
//		licenseVO=dao.findPrimaryKey("LIC1000001");
//		
//		System.out.println(licenseVO.getLicense_no());
//		System.out.println(licenseVO.getLic_name());
//		System.out.println(licenseVO.getPro_ID());
//		System.out.println(licenseVO.getNo_reg());
//		System.out.println(licenseVO.getL_pic());
//		List<LicenseVO> list = dao.findByPro("PRO1000008");
//		for(LicenseVO list1:list) {
//			System.out.println(list1.getLicense_no());
//			System.out.println(list1.getLic_name());
//			System.out.println(list1.getPro_ID());
//			System.out.println(list1.getNo_reg());
//			System.out.println(list1.getL_pic());
//		}
//		
//	}

}
