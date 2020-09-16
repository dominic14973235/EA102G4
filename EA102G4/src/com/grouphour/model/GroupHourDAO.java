package com.grouphour.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.groupclass.model.GroupClassVO;

public class GroupHourDAO implements GroupHourDAO_interface{
	private static DataSource ds ;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/INGYM");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT =
			"INSERT INTO GROUP_HOUR(G_TIME_NO, G_CLASS_NO, C_DATE,HR) VALUES ('GCT'||LPAD(SEQ_G_TIME_NO.NEXTVAL,7,'0'),?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT * FROM GROUP_HOUR";
	private static final String GET_ALL_BY_G_CLASS_NO =
			"SELECT * FROM GROUP_HOUR WHERE G_CLASS_NO= ?";
	private static final String FIND_BY_PK =
			"SELECT * FROM GROUP_HOUR WHERE G_TIME_NO= ?";
	private static final String UPDATE = 
			"UPDATE GROUP_HOUR SET C_DATE=?,HR=? WHERE G_TIME_NO= ?";
	private static final String DELETE =
			"DELETE FROM GROUP_HOUR WHERE G_TIME_NO=?";
	private static final String GET_ALL_BY_PRO_ID ="";
	
	
	@Override
	public void insert(GroupHourVO groupHourVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			//G_TIME_NO, G_CLASS_NO, C_DATE,HR
			pstmt.setString(1, groupHourVO.getG_class_no());
			pstmt.setDate(2, groupHourVO.getC_date());
			pstmt.setString(3, groupHourVO.getHr());
			pstmt.executeUpdate();		
		
		} catch(SQLException se) {
			throw new RuntimeException("資料庫錯誤"+se.getMessage());
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
				se.printStackTrace(System.err);
				}
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

	@Override
	public void update(GroupHourVO groupHourVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			//UPDATE GROUP_HOUR SET C_DATE=?,HR=? WHERE G_TIME_NO = ?
			pstmt.setDate(1, groupHourVO.getC_date());
			pstmt.setString(2, groupHourVO.getHr());
			pstmt.setString(3, groupHourVO.getG_time_no());
			pstmt.executeUpdate();		
		
		} catch(SQLException se) {
			throw new RuntimeException("資料庫錯誤"+se.getMessage());
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
				se.printStackTrace(System.err);
				}
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
	public void delete(String g_time_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			//DELETE FROM GROUP_HOUR WHERE G_TIME_NO = ?
			pstmt.setString(1, g_time_no);
			pstmt.executeUpdate();		
		
		} catch(SQLException se) {
			throw new RuntimeException("資料庫錯誤"+se.getMessage());
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
				se.printStackTrace(System.err);
				}
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

	@Override
	public GroupHourVO findByPrimaryKey(String g_time_no) {
		// TODO Auto-generated method stub
		//SELECT * FROM GROUP_HOUR WHERE G_TIME_NO= ?
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GroupHourVO ghVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setString(1, g_time_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ghVO = new GroupHourVO();
				ghVO.setG_time_no(g_time_no);
				ghVO.setC_date(rs.getDate("C_DATE"));
				ghVO.setG_class_no(rs.getString("G_CLASS_NO"));
				ghVO.setHr(rs.getString("HR"));						
			}
			// Handle any driver errors
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
		return ghVO;
	}

	@Override
	public HashSet<GroupHourVO> getAllTimeByGroupClassNo(String g_class_no) {
		// TODO Auto-generated method stub
		HashSet<GroupHourVO> set = new HashSet<GroupHourVO>();
		GroupHourVO ghVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_G_CLASS_NO);
			pstmt.setString(1, g_class_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				ghVO = new GroupHourVO();
				ghVO.setG_class_no(g_class_no);
				ghVO.setC_date(rs.getDate("C_DATE"));
				ghVO.setG_time_no(rs.getString("G_TIME_NO"));
				ghVO.setHr(rs.getString("HR"));
				set.add(ghVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}

	@Override
	public List<GroupHourVO> getAll() {
		// TODO Auto-generated method stub
		List<GroupHourVO> list = new ArrayList<GroupHourVO>();
		GroupHourVO ghVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				ghVO = new GroupHourVO();
				ghVO.setG_class_no(rs.getString("G_CLASS_NO"));
				ghVO.setC_date(rs.getDate("C_DATE"));
				ghVO.setG_time_no(rs.getString("G_TIME_NO"));
				ghVO.setHr(rs.getString("HR"));
				list.add(ghVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
