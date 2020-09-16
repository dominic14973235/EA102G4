package com.IClassOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mem.model.MemJDBCDAO;
import com.mem.model.MemVO;


public class IClassOrderJNDIDAO implements IClassOrderDAO_interface {

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


	private static final String INSERT_STMT = "INSERT INTO I_CLASS_ORDER (I_ORDER_NO, I_CLASS_NO, MEM_ID, RDATE, HR, P_COIN, IO_STATUS)"
			+ "VALUES ('IO'||LPAD(SEQ_I_ORDER_NO.NEXTVAL,7,'0'),?,?,?,?,?,?)";
	
	private static final String INSERT2_STMT = "INSERT INTO I_CLASS_ORDER (I_ORDER_NO, I_CLASS_NO, MEM_ID, RDATE, HR, P_COIN,IO_STATUS)"
			+ "VALUES ('IO'||LPAD(SEQ_I_ORDER_NO.NEXTVAL,7,'0'),?,?,?,?,?,0)";
	
	private static final String GET_ALL_STMT = "SELECT * FROM I_CLASS_ORDER ORDER BY I_ORDER_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM I_CLASS_ORDER WHERE I_ORDER_NO =?";
	private static final String GET_BY_MEM_STMT = "SELECT * FROM I_CLASS_ORDER WHERE MEM_ID =? ORDER BY RDATE DESC";
	private static final String GET_BY_CLASS_NO_STMT = "SELECT * FROM I_CLASS_ORDER WHERE I_CLASS_NO =?";

	private static final String DELETE = "DELETE FROM I_CLASS_ORDER WHERE I_ORDER_NO=?";
	private static final String UPDATE = "UPDATE I_CLASS_ORDER SET  I_CLASS_NO=?, MEM_ID=?, RDATE=?, HR=?, P_COIN=?, IO_STATUS=? where I_ORDER_NO=?";
	
	@Override
	public void insert(IClassOrderVO iClassOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, iClassOrderVO.getI_class_no());
			pstmt.setString(2, iClassOrderVO.getMem_ID());
			pstmt.setDate(3, iClassOrderVO.getRDate());
			pstmt.setString(4, iClassOrderVO.getHr());
			pstmt.setInt(5, iClassOrderVO.getP_coin());
			pstmt.setInt(6, iClassOrderVO.getIo_status());

			pstmt.executeUpdate();

			System.out.println("insert successfully"); // testing

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
	public void insert2(IClassOrderVO iClassOrderVO, MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(INSERT2_STMT);
			pstmt.setString(1, iClassOrderVO.getI_class_no());
			pstmt.setString(2, iClassOrderVO.getMem_ID());
			pstmt.setDate(3, iClassOrderVO.getRDate());
			pstmt.setString(4, iClassOrderVO.getHr());
			pstmt.setInt(5, iClassOrderVO.getP_coin());
			
			pstmt.executeUpdate();
			
			MemJDBCDAO dao = new MemJDBCDAO();
			dao.updateMemCoin(memVO, con);
			
			//commit
			con.commit();
			con.setAutoCommit(true);
			
			// Handle any SQL errors
		}  catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}

	}
	
	@Override
	public void update(IClassOrderVO iClassOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, iClassOrderVO.getI_class_no());
			pstmt.setString(2, iClassOrderVO.getMem_ID());
			pstmt.setDate(3, iClassOrderVO.getRDate());
			pstmt.setString(4, iClassOrderVO.getHr());
			pstmt.setInt(5, iClassOrderVO.getP_coin());
			pstmt.setInt(6, iClassOrderVO.getIo_status());
			pstmt.setString(7, iClassOrderVO.getI_order_no());

			pstmt.executeUpdate();

//			System.out.println("Update successfully"); // testing

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
	public void delete(String i_order_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, i_order_no);

			pstmt.executeUpdate();

			System.out.println("delete successfully"); // testing

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
	public IClassOrderVO findPrimaryKey(String i_order_no) {
		IClassOrderVO iClassOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, i_order_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				iClassOrderVO = new IClassOrderVO();

				iClassOrderVO.setI_order_no(rs.getString("i_order_no"));
				iClassOrderVO.setI_class_no(rs.getString("i_class_no"));
				iClassOrderVO.setMem_ID(rs.getString("mem_id"));
				iClassOrderVO.setRDate(rs.getDate("rDate"));
				iClassOrderVO.setHr(rs.getString("hr"));
				iClassOrderVO.setP_coin(rs.getInt("P_coin"));
				iClassOrderVO.setIo_status(rs.getInt("io_status"));
			}

			// System.out.println("query successfully"); //testing


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

		return iClassOrderVO;
	}

	@Override
	public List<IClassOrderVO> getAll() {
		List<IClassOrderVO> list = new ArrayList<IClassOrderVO>();
		IClassOrderVO iClassOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				iClassOrderVO = new IClassOrderVO();

				iClassOrderVO.setI_order_no(rs.getString("i_order_no"));
				iClassOrderVO.setI_class_no(rs.getString("i_class_no"));
				iClassOrderVO.setMem_ID(rs.getString("mem_id"));
				iClassOrderVO.setRDate(rs.getDate("rDate"));
				iClassOrderVO.setHr(rs.getString("hr"));
				iClassOrderVO.setP_coin(rs.getInt("P_coin"));
				iClassOrderVO.setIo_status(rs.getInt("io_status"));
				list.add(iClassOrderVO);
			}

			System.out.println("query successfully"); // testing

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
	public List<IClassOrderVO> findByMemId(String mem_ID) {
		List<IClassOrderVO> list = new ArrayList<IClassOrderVO>();
		IClassOrderVO iClassOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEM_STMT);

			pstmt.setString(1, mem_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				iClassOrderVO = new IClassOrderVO();

				iClassOrderVO.setI_order_no(rs.getString("i_order_no"));
				iClassOrderVO.setI_class_no(rs.getString("i_class_no"));
				iClassOrderVO.setMem_ID(rs.getString("mem_id"));
				iClassOrderVO.setRDate(rs.getDate("rDate"));
				iClassOrderVO.setHr(rs.getString("hr"));
				iClassOrderVO.setP_coin(rs.getInt("P_coin"));
				iClassOrderVO.setIo_status(rs.getInt("io_status"));
				list.add(iClassOrderVO);
			}

			System.out.println("query successfully"); // testing

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
	public List<IClassOrderVO> findByClassNo(String i_class_no) {
		List<IClassOrderVO> list = new ArrayList<IClassOrderVO>();
		IClassOrderVO iClassOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_CLASS_NO_STMT);

			pstmt.setString(1, i_class_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				iClassOrderVO = new IClassOrderVO();

				iClassOrderVO.setI_order_no(rs.getString("i_order_no"));
				iClassOrderVO.setI_class_no(rs.getString("i_class_no"));
				iClassOrderVO.setMem_ID(rs.getString("mem_id"));
				iClassOrderVO.setRDate(rs.getDate("rDate"));
				iClassOrderVO.setHr(rs.getString("hr"));
				iClassOrderVO.setP_coin(rs.getInt("P_coin"));
				iClassOrderVO.setIo_status(rs.getInt("io_status"));
				list.add(iClassOrderVO);
			}

			System.out.println("query successfully"); // testing

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
