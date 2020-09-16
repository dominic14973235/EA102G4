package com.classAuth.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassAuthJDBCDAO implements ClassAuthDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO CLASS_AUTH(AUTH_NO,PRO_ID,C_TYPE_NO)VALUES ('AU'||LPAD(SEQ_AUTH_NO.NEXTVAL,3,'0'),?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM CLASS_AUTH ORDER BY PRO_ID";
	private static final String GET_ONE_BY_PRO_ID_STMT = "SELECT * FROM CLASS_AUTH WHERE PRO_ID =?";
	private static final String GET_ONE_BY_AUTH_STMT = "SELECT * FROM CLASS_AUTH WHERE AUTH_NO =?";
	private static final String DELETE = "DELETE FROM CLASS_AUTH WHERE AUTH_NO=?";
	private static final String UPDATE = "UPDATE CLASS_AUTH SET PRO_ID=?,C_TYPE_NO=? CA_STATUS=? where AUTH_NO=?";
	private static final String UPDATE_CA_STATUS = "UPDATE CLASS_AUTH SET CA_STATUS=? where AUTH_NO=?";

	@Override
	public void insert(ClassAuthVO classAuthVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, classAuthVO.getPro_ID());
			pstmt.setString(2, classAuthVO.getC_type_no());

			pstmt.executeUpdate();

			System.out.println("insert successfully"); // testing

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
	public void update(ClassAuthVO classAuthVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, classAuthVO.getPro_ID());
			pstmt.setString(2, classAuthVO.getC_type_no());
			pstmt.setInt(3, classAuthVO.getCa_status());
			pstmt.setString(4, classAuthVO.getAuth_no());

			pstmt.executeUpdate();

			System.out.println("Update successfully"); // testing

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
	public void delete(String auth_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, auth_no);

			pstmt.executeUpdate();

			// testing
			System.out.println("delete successfully");

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
	public ClassAuthVO findPrimaryKey(String auth_no) {

		ClassAuthVO classAuthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_AUTH_STMT);

			pstmt.setString(1, auth_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				classAuthVO = new ClassAuthVO();

				classAuthVO.setPro_ID(rs.getString("pro_id"));
				classAuthVO.setC_type_no(rs.getString("c_type_no"));
				classAuthVO.setCa_status(rs.getInt("ca_status"));
				classAuthVO.setAuth_no(rs.getString("auth_no"));
			}

			System.out.println("query successfully"); // testing

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

		return classAuthVO;
	}

	@Override
	public List<ClassAuthVO> findForeignKey(String pro_ID) {

		List<ClassAuthVO> list = new ArrayList<ClassAuthVO>();
		ClassAuthVO classAuthVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_PRO_ID_STMT);

			pstmt.setString(1, pro_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				classAuthVO = new ClassAuthVO();

				classAuthVO.setPro_ID(rs.getString("pro_id"));
				classAuthVO.setC_type_no(rs.getString("c_type_no"));
				classAuthVO.setCa_status(rs.getInt("ca_status"));
				classAuthVO.setAuth_no(rs.getString("auth_no"));
				list.add(classAuthVO);
			}

//			System.out.println("query successfully"); // testing

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
	public List<ClassAuthVO> getAll() {
		List<ClassAuthVO> list = new ArrayList<ClassAuthVO>();
		ClassAuthVO classAuthVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				classAuthVO = new ClassAuthVO();

				classAuthVO.setPro_ID(rs.getString("pro_id"));
				classAuthVO.setC_type_no(rs.getString("c_type_no"));
				classAuthVO.setCa_status(rs.getInt("ca_status"));
				classAuthVO.setAuth_no(rs.getString("auth_no"));

				list.add(classAuthVO);
			}
			// testing
//			System.out.println("query successfully");

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

	@Override // for new proApplication
	public void insertBatch(List<ClassAuthVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			for (ClassAuthVO classAuthVO : list) {

				pstmt.setString(1, classAuthVO.getPro_ID());
				pstmt.setString(2, classAuthVO.getC_type_no());
				System.out.println(classAuthVO.getPro_ID());
				System.out.println(classAuthVO.getC_type_no());

				pstmt.executeUpdate();
			}
//			System.out.println("insert successfully"); // testing

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

	@Override // update- back-end modify pro authority
	public void updateBatch(List<ClassAuthVO> caList) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_CA_STATUS);
			
			
			for (ClassAuthVO classAuthVO: caList){
			pstmt.setInt(1, classAuthVO.getCa_status());
			pstmt.setString(2, classAuthVO.getAuth_no());
			pstmt.executeUpdate();
			}
			System.out.println("Update successfully"); // testing

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
}

//	public static void main(String[] args) {
//		ClassAuthJDBCDAO dao = new ClassAuthJDBCDAO();
//		ClassAuthVO classAuthVO = new ClassAuthVO();

// ¼W
//		classAuthVO.setPro_ID("PRO0100000");
//		classAuthVO.setC_type_no("CT001");
//		dao.insert(classAuthVO);
//		
//		//§ï
//		classAuthVO.setPro_ID("PRO0100002");
//		classAuthVO.setC_type_no("CT005");
//		classAuthVO.setAuth_no("AU121");
//		dao.update(classAuthVO);

// §R
//		dao.delete("AU121");
//		//¬d
//		classAuthVO = dao.findPrimaryKey("AU119");
//		System.out.println(classAuthVO.getPro_ID());
//		System.out.println(classAuthVO.getC_type_no());
//		System.out.println(classAuthVO.getAuth_no());
//		System.out.println();
//
//		List<ClassAuthVO> list1 = dao.findForeignKey("PRO0100007");
//		for (ClassAuthVO ct : list1) {
//			System.out.println(ct.getPro_ID());
//			System.out.println(ct.getC_type_no());
//			System.out.println(ct.getAuth_no());
//			System.out.println();
//		}
//		//select all
//		List<ClassAuthVO> list = dao.getAll();
//		for (ClassAuthVO ct : list) {
//			System.out.println(ct.getPro_ID());
//			System.out.println(ct.getC_type_no());
//			System.out.println(ct.getAuth_no());
//			System.out.println();
//		}
//	}
//}
