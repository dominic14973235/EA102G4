package com.groupmessage.model;

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


public class GroupMessageJDBC implements GroupMessageDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/INGYM");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "INSERT INTO GROUPMESSAGE(MES_NO, GRO_NO, MEM_ID, MES_TEXT, MES_TIME) VALUES('GM'||LPAD(GROUPMESSAGE_SEQ.NEXTVAL,5,0), ?, ?, ?, SYSDATE)";
	private static final String GETALL = "SELECT * FROM GROUPMESSAGE ORDER BY MES_TIME DESC";
	private static final String DELETE = "DELETE FROM GROUPMESSAGE WHERE MES_NO = ?";
	private static final String UPDATE = "UPDATE GROUPMESSAGE SET MES_RES = ? WHERE GRO_NO = ? AND MEM_ID = ? AND TO_CHAR(MES_TEXT) = ?";
	private static final String GET_ONE_GROUP_ALL = "SELECT * FROM GROUPMESSAGE WHERE GRO_NO = ?";
	
	@Override
	public void insert(GroupMessageVO gmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, gmVO.getGro_no());
			pstmt.setString(2, gmVO.getMem_id());
			pstmt.setString(3, gmVO.getMes_text());
			
			pstmt.executeUpdate();

			System.out.println("成功加入留言");

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
	public void updateReply(String mes_res, String gro_no, String mem_id, String mes_text) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, mes_res);
			pstmt.setString(2, gro_no);
			pstmt.setString(3, mem_id);
			pstmt.setString(4, mes_text);
			
			pstmt.executeUpdate();

			System.out.println("成功回應留言 ");

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
	public void delete(String mes_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mes_no);

			pstmt.executeUpdate();

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
	}
	
	@Override
	public List<GroupMessageVO> findByPrimaryKey(String gro_no) {
		List<GroupMessageVO> list = new ArrayList<GroupMessageVO>();
		GroupMessageVO gmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_GROUP_ALL);
			
			System.out.println(gro_no);
			pstmt.setString(1, gro_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gmVO = new GroupMessageVO();
				gmVO.setMes_no(rs.getString("mes_no"));
				gmVO.setMem_id(rs.getString("mem_id"));
				gmVO.setGro_no(rs.getString("gro_no"));
				gmVO.setMes_text(rs.getString("mes_text"));
				gmVO.setMes_res(rs.getString("mes_res"));
					
				list.add(gmVO);
			}

			System.out.println("查詢所有揪團成功");

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public List<GroupMessageVO> getAll() {
		List<GroupMessageVO> list = new ArrayList<GroupMessageVO>();
		GroupMessageVO gmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gmVO = new GroupMessageVO();
				gmVO.setMes_no(rs.getString("mes_no"));
				gmVO.setMem_id(rs.getString("mem_id"));
				gmVO.setGro_no(rs.getString("gro_no"));
				gmVO.setMes_text(rs.getString("mes_text"));
				gmVO.setMes_res(rs.getString("mes_res"));

				list.add(gmVO);
			}

			System.out.println("查詢所有揪團成功");

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
