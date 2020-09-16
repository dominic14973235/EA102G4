package com.groupclass.model;


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



public class GroupClassDAO implements GroupClassDAO_interface {

	private static DataSource ds;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/INGYM");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO GROUP_CLASS(G_CLASS_NO,PRO_ID,C_TYPE_NO,G_NAME,LOC,G_MAX,P_COIN,G_DETAIL,G_PIC) "
			+ "VALUES ('GC'||LPAD(SEQ_G_CLASS_NO.NEXTVAL,7,'0'),?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM GROUP_CLASS";
//	private static final String GET_ALL_STATUS1_STMT_ = "SELECT * FROM GROUP_CLASS WHERE C_STATUS=1";
	private static final String UPDATE = "UPDATE GROUP_CLASS SET C_TYPE_NO=?,G_NAME=?,LOC=?,G_MAX=?,P_COIN=?,G_DETAIL=?,G_PIC=?,C_STATUS=? WHERE G_CLASS_NO=?";
	private static final String FIND_BY_PK = "SELECT * FROM GROUP_CLASS WHERE G_CLASS_NO= ?";
	private static final String GET_ALL_BY_PRO_ID = "SELECT * FROM GROUP_CLASS WHERE PRO_ID= ?";
	private static final String GET_NEW_BY_PRO_ID = "SELECT * FROM GROUP_CLASS WHERE PRO_ID= ? ORDER BY G_CLASS_NO DESC";
	private static final String GET_G_PIC_BY_G_CLASS_NO = "SELECT G_PIC FROM GROUP_CLASS WHERE G_CLASS_NO=?";

	@Override
	public void insert(GroupClassVO groupClassVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			// PRO_ID,C_TYPE_NO,G_NAME,LOC,G_MAX,P_COIN,G_DETAIL,G_PIC
			pstmt.setString(1, groupClassVO.getPro_id());
			pstmt.setString(2, groupClassVO.getC_type_no());
			pstmt.setString(3, groupClassVO.getG_name());
			pstmt.setString(4, groupClassVO.getLoc());
			pstmt.setInt(5, groupClassVO.getG_max());
			pstmt.setInt(6, groupClassVO.getP_coin());
			pstmt.setString(7, groupClassVO.getG_detail());
			pstmt.setBytes(8, groupClassVO.getG_pic());

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
	public void update(GroupClassVO groupClassVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			// C_TYPE_NO=?,G_NAME=?,LOC=?,G_MAX=?,P_COIN=?,G_DETAIL=?,G_PIC=? WHERE
			// G_CLASS_NO=?
			pstmt.setString(1, groupClassVO.getC_type_no());
			pstmt.setString(2, groupClassVO.getG_name());
			pstmt.setString(3, groupClassVO.getLoc());
			pstmt.setInt(4, groupClassVO.getG_max());
			pstmt.setInt(5, groupClassVO.getP_coin());
			pstmt.setString(6, groupClassVO.getG_detail());
			pstmt.setBytes(7, groupClassVO.getG_pic());
			pstmt.setInt(8, groupClassVO.getC_status());
			pstmt.setString(9, groupClassVO.getG_class_no());
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
	public GroupClassVO findByPrimaryKey(String g_class_no) {
		// TODO Auto-generated method stub
		// SELECT * FROM GROUP_CLASS WHERE G_CLASS_NO= ?
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GroupClassVO gcVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setString(1, g_class_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				gcVO = new GroupClassVO();
				gcVO.setG_class_no(g_class_no);
				gcVO.setC_type_no(rs.getString("C_TYPE_NO"));
				gcVO.setG_detail(rs.getString("G_DETAIL"));
				gcVO.setG_max(rs.getInt("G_MAX"));
				gcVO.setG_name(rs.getString("G_NAME"));
//				gcVO.setG_pic(rs.getBytes("G_PIC"));
				gcVO.setLoc(rs.getString("LOC"));
				gcVO.setP_coin(rs.getInt("P_COIN"));
				gcVO.setPro_id(rs.getString("PRO_ID"));
				gcVO.setC_status(new Integer(rs.getString("c_status")));
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
		return gcVO;
	}

	@Override
	public List<GroupClassVO> getAllByPro_id(String pro_id) {
		// TODO Auto-generated method stub
		// SELECT * FROM GROUP_CLASS WHERE PRO_ID= ?
		List<GroupClassVO> list = new ArrayList<GroupClassVO>();
		GroupClassVO gcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_PRO_ID);
			pstmt.setString(1, pro_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				gcVO = new GroupClassVO();
				gcVO.setG_class_no(rs.getString("G_CLASS_NO"));
				gcVO.setC_type_no(rs.getString("C_TYPE_NO"));
				gcVO.setG_detail(rs.getString("G_DETAIL"));
				gcVO.setG_max(rs.getInt("G_MAX"));
				gcVO.setG_name(rs.getString("G_NAME"));
//				gcVO.setG_pic(rs.getBytes("G_PIC"));
				gcVO.setLoc(rs.getString("LOC"));
				gcVO.setP_coin(rs.getInt("P_COIN"));
				gcVO.setPro_id(pro_id);
				gcVO.setC_status(rs.getInt("C_STATUS"));
				list.add(gcVO); // Store the row in the list
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
	public List<GroupClassVO> getAll() {
		// TODO Auto-generated method stub
		List<GroupClassVO> list = new ArrayList<GroupClassVO>();
		GroupClassVO gcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				gcVO = new GroupClassVO();
				gcVO.setG_class_no(rs.getString("G_CLASS_NO"));
				gcVO.setC_type_no(rs.getString("C_TYPE_NO"));
				gcVO.setG_detail(rs.getString("G_DETAIL"));
				gcVO.setG_max(rs.getInt("G_MAX"));
				gcVO.setG_name(rs.getString("G_NAME"));
//				gcVO.setG_pic(rs.getBytes("G_PIC"));
				gcVO.setLoc(rs.getString("LOC"));
				gcVO.setP_coin(rs.getInt("P_COIN"));
				gcVO.setPro_id(rs.getString("PRO_ID"));
				gcVO.setC_status(rs.getInt("C_STATUS"));
				list.add(gcVO); // Store the row in the list
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
	public byte[] getG_picByG_class_no(String g_class_no) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_G_PIC_BY_G_CLASS_NO);
			pstmt.setString(1, g_class_no);
			rs = pstmt.executeQuery();
			if (rs.next())
				return rs.getBytes("G_PIC");
			else
				return null;
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
	}

	@Override
	public GroupClassVO getNewGClassNoByPro_id(String pro_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GroupClassVO gcVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NEW_BY_PRO_ID);

			pstmt.setString(1, pro_id);
			rs = pstmt.executeQuery();
			rs.next();
			gcVO = new GroupClassVO();
			gcVO.setG_class_no(rs.getString("g_class_no"));
			gcVO.setPro_id(rs.getString("pro_id"));
			gcVO.setC_type_no(rs.getString("C_TYPE_NO"));
			gcVO.setG_detail(rs.getString("G_DETAIL"));
			gcVO.setG_max(rs.getInt("G_MAX"));
			gcVO.setG_name(rs.getString("G_NAME"));
			gcVO.setG_pic(rs.getBytes("G_PIC"));
			gcVO.setLoc(rs.getString("LOC"));
			gcVO.setP_coin(rs.getInt("P_COIN"));

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
		return gcVO;
	}

}
