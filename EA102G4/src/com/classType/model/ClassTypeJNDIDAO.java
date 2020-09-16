package com.classType.model;

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

public class ClassTypeJNDIDAO implements ClassTypeDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO CLASS_TYPE(C_TYPE_NO, T_NAME) VALUES ('CT'||LPAD(SEQ_CTYPE_NO.nextval,3,'0'),?)";
	private static final String GET_ALL_STMT = "SELECT * FROM CLASS_TYPE ORDER BY C_TYPE_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM CLASS_TYPE WHERE C_TYPE_NO =?";
	private static final String DELETE = "DELETE FROM CLASS_TYPE WHERE C_TYPE_NO=?";
	private static final String UPDATE = "UPDATE CLASS_TYPE SET T_NAME=? where C_TYPE_NO=?";

	@Override
	public void insert(ClassTypeVO classTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, classTypeVO.getT_name());

			pstmt.executeUpdate();
			
//			System.out.println("insert successfully");			//testing
			
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
	public void update(ClassTypeVO classTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, classTypeVO.getT_name());
			pstmt.setString(2, classTypeVO.getC_type_no());

			pstmt.executeUpdate();
			
//			System.out.println("update successfully");			//testing

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
	public void delete(String c_type_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, c_type_no);

			pstmt.executeUpdate();
			
//			System.out.println("delete successfully");			//testing
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
	public ClassTypeVO findPrimaryKey(String c_type_no) {

		ClassTypeVO classTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, c_type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				classTypeVO = new ClassTypeVO();

				classTypeVO.setC_type_no(rs.getString("c_type_no"));
				classTypeVO.setT_name(rs.getString("T_name"));
			}
			
//			System.out.println("query successfully");			//testing

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

		return classTypeVO;
	}

	@Override
	public List<ClassTypeVO> getAll() {
		List<ClassTypeVO> list = new ArrayList<ClassTypeVO>();
		ClassTypeVO classTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				classTypeVO = new ClassTypeVO();
				classTypeVO.setC_type_no(rs.getString("C_TYPE_NO"));
				classTypeVO.setT_name(rs.getString("T_NAME"));
				list.add(classTypeVO);
			}
			
//			System.out.println("insert successfully");			//testing

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
}
