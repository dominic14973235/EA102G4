package com.pro.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.license.model.LicenseJDBCDAO;
import com.license.model.LicenseVO;

public class ProJDBCDAO implements ProDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO PRO (PRO_ID, MEM_ID, T_RATING, P_RATING, PRO_AUTH,EXPR) VALUES ('PRO'||LPAD(SEQ_PRO_ID.nextval,7,'0'),?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM PRO ORDER BY PRO_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM PRO WHERE PRO_ID =?";
	private static final String GET_BY_MEM_STMT = "SELECT * FROM PRO WHERE MEM_ID =?";
	private static final String DELETE = "DELETE FROM PRO WHERE PRO_ID=?";
	private static final String UPDATE = "UPDATE PRO SET T_RATING=?, P_RATING=?, PRO_AUTH=?, EXPR=? where PRO_ID=?";
	private static final String UPDATE_EXPR = "UPDATE PRO SET EXPR=? where PRO_ID=?";
	private static final String UPDATE_PRO_AUTH= "UPDATE PRO SET PRO_AUTH=? where PRO_ID=?";


	public void insert(ProVO proVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, proVO.getMem_ID());
			pstmt.setInt(2, proVO.getT_rating());
			pstmt.setInt(3, proVO.getP_rating());
			pstmt.setInt(4, proVO.getPro_auth());
			pstmt.setString(5, proVO.getExpr());

			pstmt.executeUpdate();
			//testing
//			System.out.println("insert successfully");
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
	public void update(ProVO proVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, proVO.getT_rating());
			pstmt.setInt(2, proVO.getP_rating());
			pstmt.setInt(3, proVO.getPro_auth());
			pstmt.setString(4, proVO.getExpr());
			pstmt.setString(5, proVO.getPro_ID());
			
			pstmt.executeUpdate();
			
			//testing
//			System.out.println("Update successfully"); 
			
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
	public void delete(String pro_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pro_ID);

			pstmt.executeUpdate();
			
			//testing
//			System.out.println("delete successfully");
			
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
	public ProVO findPrimaryKey(String pro_ID) {

		ProVO proVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pro_ID);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				proVO = new ProVO();

				proVO.setPro_ID(rs.getString("pro_id"));
				proVO.setMem_ID(rs.getString("mem_id"));
				proVO.setT_rating(rs.getInt("T_rating"));
				proVO.setP_rating(rs.getInt("P_rating"));
				proVO.setPro_auth(rs.getInt("Pro_auth"));
				proVO.setExpr(rs.getString("expr"));
			}
			//testing
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

		return proVO;
	}

	@Override
	public ProVO findByMem(String mem_ID) {

		ProVO proVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_MEM_STMT);

			pstmt.setString(1, mem_ID);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				proVO = new ProVO();

				proVO.setPro_ID(rs.getString("pro_id"));
				proVO.setMem_ID(rs.getString("mem_id"));
				proVO.setT_rating(rs.getInt("T_rating"));
				proVO.setP_rating(rs.getInt("P_rating"));
				proVO.setPro_auth(rs.getInt("Pro_auth"));
				proVO.setExpr(rs.getString("expr"));
			}
			//testing
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

		return proVO;
	}
	
	@Override
	public List<ProVO> getAll() {
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO proVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			// (PRO_ID, MEM_ID, T_RATING, P_RATING, PRO_AUTH,EXPR
			while (rs.next()) {
				proVO = new ProVO();

				proVO.setPro_ID(rs.getString("pro_id"));
				proVO.setMem_ID(rs.getString("mem_id"));
				proVO.setT_rating(rs.getInt("T_rating"));
				proVO.setP_rating(rs.getInt("P_rating"));
				proVO.setPro_auth(rs.getInt("Pro_auth"));
				proVO.setExpr(rs.getString("expr"));
				list.add(proVO);
				
			}
			//testing
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

	@Override
	public void insertWithLicense(ProVO proVO,  List<LicenseVO> license) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			
			//for insert or update a batch;
			con.setAutoCommit(false);
			
			//insert a new pro first
			String cols[]= {"PRO_ID"};
			
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, proVO.getMem_ID());
			pstmt.setInt(2, proVO.getT_rating());
			pstmt.setInt(3, proVO.getP_rating());
			pstmt.setInt(4, proVO.getPro_auth());
			pstmt.setString(5, proVO.getExpr());
			pstmt.executeUpdate();

			//retrieve AutoGeneratedkey
			String next_pro = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				next_pro = rs.getString(1);
			}else {
				System.out.println("PRO未取得自增主鍵值");
			}
			

			LicenseJDBCDAO dao = new LicenseJDBCDAO();
			for (LicenseVO licenseVO:license) {
				licenseVO.setPro_ID(next_pro);
				dao.insert2(licenseVO,con);
			}
			
			//commit
			con.commit();
			con.setAutoCommit(true);
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
		}catch(SQLException se) {
			if(con!= null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				}catch(SQLException excep) {
					throw new RuntimeException("rollback error occured" + excep.getMessage());
				}
		}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
				if(con!=null) {
					try {
						con.close();
					}catch(Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
		}
		
	}

	@Override
	public void updateExp(ProVO proVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_EXPR);

			pstmt.setString(1, proVO.getExpr());
			pstmt.setString(2, proVO.getPro_ID());
			
			pstmt.executeUpdate();
			
			//testing
//			System.out.println("Update successfully"); 
			
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
	public void updateAuth(String pro_ID, Integer pro_auth) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_PRO_AUTH);

			pstmt.setInt(1, pro_auth);
			pstmt.setString(2, pro_ID);

			pstmt.executeUpdate();

//			testing
//			System.out.println("Update successfully"); 
			
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


//	public static void main(String args[]) {
//		ProJDBCDAO dao = new ProJDBCDAO();
//		ProVO proVO = new ProVO();
//		
//		//增
//		proVO.setMem_ID("MEM0100009");
//		proVO.setT_rating(100);
//		proVO.setP_rating(10);
//		proVO.setPro_auth(0);
//		proVO.setExpr("");
//		dao.insert(proVO);
//		//改
//		proVO.setPro_ID("PRO020008");
//		proVO.setT_rating(10000);
//		proVO.setP_rating(1000);
//		proVO.setPro_auth(1);
//		proVO.setExpr("");
//		dao.update(proVO);
//		//刪
//		dao.delete("PRO0200001");
//		//查
//		dao.findPrimaryKey("PRO0200010");
//		List<ProVO> list = dao.getAll();
//		for (ProVO ct : list) {
//			System.out.print(ct.getPro_ID() + ",");
//			System.out.print(ct.getT_rating() + ",");
//			System.out.print(ct.getP_rating() + ",");
//			System.out.print(ct.getPro_auth() + ",");
//			System.out.print(ct.getExpr() + ",");
//
//			System.out.println();
//		}
//	}
}
