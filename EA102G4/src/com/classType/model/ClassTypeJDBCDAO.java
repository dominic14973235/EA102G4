package com.classType.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassTypeJDBCDAO implements ClassTypeDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, classTypeVO.getT_name());

			pstmt.executeUpdate();
			
//			System.out.println("insert successfully");			//testing
			
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
	public void update(ClassTypeVO classTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, classTypeVO.getT_name());
			pstmt.setString(2, classTypeVO.getC_type_no());

			pstmt.executeUpdate();
			
//			System.out.println("update successfully");			//testing

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
	public void delete(String c_type_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, c_type_no);

			pstmt.executeUpdate();
			
//			System.out.println("delete successfully");			//testing
			
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
	public ClassTypeVO findPrimaryKey(String c_type_no) {

		ClassTypeVO classTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, c_type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				classTypeVO = new ClassTypeVO();

				classTypeVO.setC_type_no(rs.getString("c_type_no"));
				classTypeVO.setT_name(rs.getString("T_name"));
			}
			
//			System.out.println("query successfully");			//testing

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				classTypeVO = new ClassTypeVO();
				classTypeVO.setC_type_no(rs.getString("C_TYPE_NO"));
				classTypeVO.setT_name(rs.getString("T_NAME"));
				list.add(classTypeVO);
			}
			
//			System.out.println("insert successfully");			//testing

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

//	public static void main(String args[]) {
//		ClassTypeJDBCDAO dao = new ClassTypeJDBCDAO();
//		ClassTypeVO classTypeVO = new ClassTypeVO();
//
////		add
//		classTypeVO.setT_name("JAVA¾i¦¨¯Z11223");
//		dao.insert(classTypeVO);
//
////		update modify
//		classTypeVO.setT_name("12346");
//		classTypeVO.setC_type_no("CT010");
//		dao.update(classTypeVO);
//
////		delete
//		dao.delete("CT022");
//
////		select 1
//		classTypeVO = dao.findPrimaryKey("CT021");
//		System.out.println(classTypeVO.getC_type_no() + "==" + classTypeVO.getT_name());
//
////		select all
//		List<ClassTypeVO> list = dao.getAll();
//		for (ClassTypeVO ct : list) {
//			System.out.print(ct.getC_type_no() + ",");
//			System.out.print(ct.getT_name() + ",");
//			System.out.println();
//		}
//	}
}
