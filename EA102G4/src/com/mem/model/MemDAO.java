package com.mem.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MemDAO implements MemDAO_interface {

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

	private static final String INSERT_STMT = 
			"INSERT INTO MEMBERS (MEM_ID,MEM_NAME,MEM_PSW,MEM_BIR,SEX,MEM_ADDR, MEM_EMAIL, MEM_PHONE, MEM_ABSENT, COIN, MEM_RESUME, SEL_AUTH, ART_AUTH, COM_AUTH) "
			+ "VALUES ('MEM'||LPAD(TO_CHAR(MEM_SEQ.NEXTVAL), 7, '0'), ?, ?, ?, ?, ?, ?, ?, 5, 0, '我就爛', 'S1', 'A1', 'C1')";
	private static final String GET_ALL_STMT = 
			"SELECT MEM_ID,MEM_NAME, MEM_PSW,to_char(MEM_BIR,'yyyy-mm-dd')MEM_BIR,SEX,MEM_ADDR,MEM_EMAIL,MEM_PHONE, MEM_ABSENT, COIN, MEM_RESUME, M_REG_DATE, SEL_AUTH, ART_AUTH, COM_AUTH FROM MEMBERS order by MEM_ID";
	private static final String GET_ALL_STMT_EMAIL = 
			"SELECT MEM_EMAIL FROM MEMBERS";
	private static final String GET_ONE_STMT = 
			"SELECT MEM_ID,MEM_NAME, MEM_PSW,to_char(MEM_BIR,'yyyy-mm-dd')MEM_BIR,SEX,MEM_ADDR,MEM_EMAIL,MEM_PHONE, MEM_ABSENT, COIN, MEM_RESUME, M_REG_DATE, SEL_AUTH, ART_AUTH, COM_AUTH FROM MEMBERS where MEM_ID = ?";
	private static final String LOGIN = 
			"SELECT MEM_ID,MEM_NAME, MEM_PSW,to_char(MEM_BIR,'yyyy-mm-dd')MEM_BIR,SEX,MEM_ADDR,MEM_EMAIL,MEM_PHONE, MEM_ABSENT, COIN, MEM_RESUME, M_REG_DATE, SEL_AUTH, ART_AUTH, COM_AUTH FROM MEMBERS where MEM_EMAIL = ?";
	private static final String DELETE = 
			"DELETE FROM MEMBERS where MEM_ID = ?";
	private static final String UPDATE = 
			"UPDATE MEMBERS set MEM_NAME=?, MEM_PSW=?, MEM_BIR=?, SEX=?, MEM_ADDR=?, MEM_EMAIL=?, MEM_PHONE=?, MEM_ABSENT=?, COIN=?, MEM_RESUME=?, M_REG_DATE=?, SEL_AUTH=?, ART_AUTH=?,COM_AUTH=?  where MEM_ID = ?";
	private static final String UPDATE_AUTH = 
			"UPDATE MEMBERS set SEL_AUTH=?, ART_AUTH=?,COM_AUTH=?  where MEM_ID = ?";
	private static final String UPDATE_COIN = 
			"UPDATE MEMBERS set COIN=? where MEM_ID = ?";

	@Override
	public void insert(MemVO memVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_psw());
			pstmt.setDate(3, memVO.getMem_bir());
			pstmt.setString(4, memVO.getSex());
			pstmt.setString(5, memVO.getMem_addr());
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, memVO.getMem_phone());
			


			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_psw());
			pstmt.setDate(3, memVO.getMem_bir());
			pstmt.setString(4, memVO.getSex());
			pstmt.setString(5, memVO.getMem_addr());
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, memVO.getMem_phone());
			pstmt.setInt(8, memVO.getMem_absent());
			pstmt.setInt(9, memVO.getCoin());
			pstmt.setString(10, memVO.getMem_resume());
			pstmt.setTimestamp(11, memVO.getM_reg_date());
			pstmt.setString(12, memVO.getSel_auth());
			pstmt.setString(13, memVO.getArt_auth());
			pstmt.setString(14, memVO.getCom_auth());
			pstmt.setString(15, memVO.getMem_id());

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
	public void updateMemAuth(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_AUTH);

			
			pstmt.setString(1, memVO.getSel_auth());
			pstmt.setString(2, memVO.getArt_auth());
			pstmt.setString(3, memVO.getCom_auth());
			pstmt.setString(4, memVO.getMem_id());

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
	public void updateMemCoin(MemVO memVO, Connection con) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;

		try {

			
			pstmt = con.prepareStatement(UPDATE_COIN);

			
			pstmt.setInt(1, memVO.getCoin());
			pstmt.setString(2, memVO.getMem_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
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
	public void delete(String mem_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_id);

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
	public MemVO findByPrimaryKey(String mem_id) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setMem_bir(rs.getDate("mem_bir"));
				memVO.setSex(rs.getString("sex"));
				memVO.setMem_addr(rs.getString("mem_addr"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_absent(rs.getInt("mem_absent"));
				memVO.setCoin(rs.getInt("coin"));
				memVO.setMem_resume(rs.getString("mem_resume"));
				memVO.setM_reg_date(rs.getTimestamp("m_reg_date"));
				memVO.setSel_auth(rs.getString("sel_auth"));
				memVO.setArt_auth(rs.getString("art_auth"));
				memVO.setCom_auth(rs.getString("com_auth"));
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
		return memVO;
	}

	
	@Override
	public MemVO findByPrimaryKeyByMemAcc(String mem_email) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN);

			pstmt.setString(1, mem_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setMem_bir(rs.getDate("mem_bir"));
				memVO.setSex(rs.getString("sex"));
				memVO.setMem_addr(rs.getString("mem_addr"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_absent(rs.getInt("mem_absent"));
				memVO.setCoin(rs.getInt("coin"));
				memVO.setMem_resume(rs.getString("mem_resume"));
				memVO.setM_reg_date(rs.getTimestamp("m_reg_date"));
				memVO.setSel_auth(rs.getString("sel_auth"));
				memVO.setArt_auth(rs.getString("art_auth"));
				memVO.setCom_auth(rs.getString("com_auth"));
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
		return memVO;
	}
	
	@Override
	public List<String> getAllEmail() {
		List<String> list = new ArrayList<String>();
		String mem_email = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_EMAIL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				mem_email = rs.getString("mem_email");
				list.add(mem_email); // Store the row in the list
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
	
	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setMem_bir(rs.getDate("mem_bir"));
				memVO.setSex(rs.getString("sex"));
				memVO.setMem_addr(rs.getString("mem_addr"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_absent(rs.getInt("mem_absent"));
				memVO.setCoin(rs.getInt("coin"));
				memVO.setMem_resume(rs.getString("mem_resume"));
				memVO.setM_reg_date(rs.getTimestamp("m_reg_date"));
				memVO.setSel_auth(rs.getString("sel_auth"));
				memVO.setArt_auth(rs.getString("art_auth"));
				memVO.setCom_auth(rs.getString("com_auth"));
				list.add(memVO); // Store the row in the list
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
