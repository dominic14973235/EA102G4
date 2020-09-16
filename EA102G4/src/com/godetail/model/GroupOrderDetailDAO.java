package com.godetail.model;

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

import com.groupclass.model.GroupClassVO;

public class GroupOrderDetailDAO implements GroupOrderDetailDAO_interface {

	private static DataSource ds;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/INGYM");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO GO_DETAIL (G_ORDER_NO, G_CLASS_NO,G_TIME_NO, RDATE, HR, P_COIN)"
			+ "VALUES (?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM GO_DETAIL";
	private static final String UPDATE = "UPDATE GO_DETAIL SET GO_STATUS=? WHERE G_ORDER_NO=? AND  G_TIME_NO=?";
	private static final String FIND_BY_PK = "SELECT * FROM GO_DETAIL WHERE G_ORDER_NO=? AND  G_TIME_NO=?";
	private static final String GET_ALL_BY_G_CLASS_NO = "SELECT * FROM GO_DETAIL WHERE G_CLASS_NO =? AND GO_STATUS=? ORDER BY G_TIME_NO";
	private static final String GET_DETAIL_BY_G_ORDER_NO =
			"SELECT * FROM GO_DETAIL WHERE G_ORDER_NO =? AND GO_STATUS=? ORDER BY G_TIME_NO";
	private static final String GET_DETAIL_BY_G_TIME_NO =
			"SELECT * FROM GO_DETAIL WHERE G_TIME_NO =? AND GO_STATUS=? ORDER BY G_TIME_NO";
	private static final String GET_ALL_BY_G_ORDER_NO =
			"SELECT * FROM GO_DETAIL WHERE G_ORDER_NO =?";
	@Override
	public void insert(GroupOrderDetailVO godVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			// G_ORDER_NO, G_CLASS_NO,G_TIME_NO, RDATE, HR, P_COIN, GO_STATUS
			pstmt.setString(1, godVO.getG_order_no());
			pstmt.setString(2, godVO.getG_class_no());
			pstmt.setString(3, godVO.getG_time_no());
			pstmt.setDate(4, godVO.getRdate());
			pstmt.setString(5, godVO.getHr());
			pstmt.setInt(6, godVO.getP_coin());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("資料庫錯誤" + se.getMessage());
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
	public void update(GroupOrderDetailVO godVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// UPDATE GO_DETAIL SET GO_STATUS=? WHERE G_ORDER_NO=? AND G_TIME_NO=?
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, godVO.getGo_status());
			pstmt.setString(2, godVO.getG_order_no());
			pstmt.setString(3, godVO.getG_time_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("資料庫錯誤" + se.getMessage());
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
	public GroupOrderDetailVO findByPrimaryKey(String g_order_no, String  g_time_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GroupOrderDetailVO godVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setString(1, g_order_no);
			pstmt.setString(2, g_time_no);
			rs = pstmt.executeQuery();
			godVO = new GroupOrderDetailVO();
			if(rs.next()) {
				godVO.setG_class_no(rs.getString("G_CLASS_NO"));
				godVO.setG_order_no(rs.getString("G_ORDER_NO"));
				godVO.setG_time_no(rs.getString("G_TIME_NO"));
				godVO.setGo_status(rs.getInt("GO_STATUS"));
				godVO.setHr(rs.getString("HR"));
				godVO.setP_coin(rs.getInt("P_COIN"));
				godVO.setRdate(rs.getDate("RDATE"));
			}
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
		return godVO;
	}

	@Override //該課程所有訂單
	public List<GroupOrderDetailVO> getAllByG_class_no(String g_class_no,Integer go_status) {
		// TODO Auto-generated method stub
		List<GroupOrderDetailVO> list = new ArrayList<GroupOrderDetailVO>();
		GroupOrderDetailVO godVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_G_CLASS_NO);
			pstmt.setString(1, g_class_no);
			pstmt.setInt(2, go_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				godVO = new GroupOrderDetailVO();
				godVO.setG_class_no(g_class_no);
				godVO.setG_order_no(rs.getString("G_ORDER_NO"));
				godVO.setG_time_no(rs.getString("G_TIME_NO"));
				godVO.setGo_status(rs.getInt("GO_STATUS"));
				godVO.setHr(rs.getString("HR"));
				godVO.setP_coin(rs.getInt("P_COIN"));
				godVO.setRdate(rs.getDate("RDATE"));
				list.add(godVO); // Store the row in the list
			}
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
	public List<GroupOrderDetailVO> getAll() {
		// TODO Auto-generated method stub
		List<GroupOrderDetailVO> list = new ArrayList<GroupOrderDetailVO>();
		GroupOrderDetailVO godVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				godVO = new GroupOrderDetailVO();
				godVO.setG_class_no(rs.getString("G_CLASS_NO"));
				godVO.setG_order_no(rs.getString("G_ORDER_NO"));
				godVO.setG_time_no(rs.getString("G_TIME_NO"));
				godVO.setGo_status(rs.getInt("GO_STATUS"));
				godVO.setHr(rs.getString("HR"));
				godVO.setP_coin(rs.getInt("P_COIN"));
				godVO.setRdate(rs.getDate("RDATE"));
				list.add(godVO); // Store the row in the list
			}
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
	public void insertFormOrder(GroupOrderDetailVO godVO, Connection con) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		try {
			pstmt=con.prepareStatement(INSERT_STMT);
			//"INSERT INTO GO_DETAIL 
			//(G_ORDER_NO, G_CLASS_NO,G_TIME_NO, RDATE, HR, P_COIN)
			//VALUES (?,?,?,?,?,?)"
			pstmt.setString(1, godVO.getG_order_no());
			pstmt.setString(2, godVO.getG_class_no());
			pstmt.setString(3, godVO.getG_time_no());
			pstmt.setDate(4, godVO.getRdate());
			pstmt.setString(5, godVO.getHr());
			pstmt.setInt(6,godVO.getP_coin());
			pstmt.executeUpdate();		
		}catch(SQLException se) {
			if(con!=null) {
				try {
					System.err.print("Transaction is being");
					System.err.println("rolled back-由-GroupOrderDetail");
					con.rollback();
				}catch(SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		
	}
	
	//撈出該訂單所有明細
		@Override
		public List<GroupOrderDetailVO> getDetailsByOrder(String g_order_no,Integer go_status) {
			// TODO Auto-generated method stub
			List<GroupOrderDetailVO> list = new ArrayList<GroupOrderDetailVO>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			GroupOrderDetailVO godVO = null;
			
			try {
				con = ds.getConnection();
				pstmt= con.prepareStatement(GET_DETAIL_BY_G_ORDER_NO);
				pstmt.setString(1, g_order_no);
				pstmt.setInt(2, go_status);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					godVO = new GroupOrderDetailVO();
					godVO.setG_class_no(rs.getString("g_class_no"));
					godVO.setG_order_no(g_order_no);
					godVO.setG_time_no(rs.getString("g_time_no"));
					godVO.setGo_status(rs.getInt("go_status"));
					godVO.setHr(rs.getString("hr"));
					godVO.setP_coin(rs.getInt("p_coin"));
					godVO.setRdate(rs.getDate("rdate"));
					list.add(godVO);
				}
			}catch(SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				
			}finally {
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
		public List<GroupOrderDetailVO> getDetailsByGTimeNo(String g_time_no,Integer go_status) {
			// TODO Auto-generated method stub
			List<GroupOrderDetailVO> list = new ArrayList<GroupOrderDetailVO>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			GroupOrderDetailVO godVO = null;
			
			try {
				con = ds.getConnection();
				pstmt= con.prepareStatement(GET_DETAIL_BY_G_TIME_NO);
				pstmt.setString(1, g_time_no);
				pstmt.setInt(2, go_status);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					godVO = new GroupOrderDetailVO();
					godVO.setG_class_no(rs.getString("g_class_no"));
					godVO.setG_order_no(rs.getString("g_order_no"));
					godVO.setG_time_no(g_time_no);
					godVO.setGo_status(rs.getInt("go_status"));
					godVO.setHr(rs.getString("hr"));
					godVO.setP_coin(rs.getInt("p_coin"));
					godVO.setRdate(rs.getDate("rdate"));
					list.add(godVO);
				}
			}catch(SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				
			}finally {
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
		public List<GroupOrderDetailVO> getAllByG_order_no(String g_order_no) {
			// TODO Auto-generated method stub
			List<GroupOrderDetailVO> list = new ArrayList<GroupOrderDetailVO>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			GroupOrderDetailVO godVO = null;
			try {
				con = ds.getConnection();
				pstmt= con.prepareStatement(GET_ALL_BY_G_ORDER_NO);
				pstmt.setString(1, g_order_no);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					godVO = new GroupOrderDetailVO();
					godVO.setG_class_no(rs.getString("g_class_no"));
					godVO.setG_order_no(rs.getString("g_order_no"));
					godVO.setG_time_no(rs.getString("g_time_no"));
					godVO.setGo_status(rs.getInt("go_status"));
					godVO.setHr(rs.getString("hr"));
					godVO.setP_coin(rs.getInt("p_coin"));
					godVO.setRdate(rs.getDate("rdate"));
					list.add(godVO);
				}
			}catch(SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				
			}finally {
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
