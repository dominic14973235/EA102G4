package com.worker.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class WorkerJNDIDAO implements WorkerDAO_interface {

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
		"INSERT INTO worker (work_id,w_name,w_acc,w_pw,email,reg_date,photo) VALUES ('WORK'||LPAD(TO_CHAR(WORKS_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT work_id,w_name,w_acc,w_pw,email,to_char(reg_date,'yyyy-mm-dd') reg_date,photo FROM worker order by work_id";
	private static final String GET_ONE_STMT = 
		"SELECT work_id,w_name,w_acc,w_pw,email,to_char(reg_date,'yyyy-mm-dd') reg_date,photo FROM worker where work_id = ?";
	private static final String GET_ONE_ACC = 
		"SELECT WORK_ID,W_NAME,W_ACC,W_PW,EMAIL,TO_CHAR(REG_DATE,'YYYY-MM-DD') REG_DATE,PHOTO FROM WORKER WHERE W_ACC = ?";
	private static final String DELETE = 
		"DELETE FROM worker where work_id = ?";
	private static final String UPDATE = 
		"UPDATE worker set w_name=?, w_acc=?, w_pw=?, email=?, reg_date=?, photo=? where work_id = ?";
	private static final String UPDATE_PW = 
			"UPDATE worker set w_pw=? where work_id = ?";
		
	@Override
	public void insert(WorkerVO workerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, workerVO.getW_name());
			pstmt.setString(2, workerVO.getW_acc());
			pstmt.setString(3, workerVO.getW_pw());
			pstmt.setString(4, workerVO.getEmail());
			pstmt.setDate(5, workerVO.getReg_date());
			pstmt.setBytes(6, workerVO.getPhoto());

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
	public void update(WorkerVO workerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, workerVO.getW_name());
			pstmt.setString(2, workerVO.getW_acc());
			pstmt.setString(3, workerVO.getW_pw());
			pstmt.setString(4, workerVO.getEmail());
			pstmt.setDate(5, workerVO.getReg_date());			
			pstmt.setBytes(6, workerVO.getPhoto());
			pstmt.setString(7, workerVO.getWork_id());

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
	public void updatePw(String work_id, String w_pw) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PW);
			
			pstmt.setString(1, w_pw);
			pstmt.setString(2, work_id);

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
	public void delete(String work_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, work_id);

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
	public WorkerVO findByPrimaryKey(String work_id)  {

		WorkerVO workerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, work_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				workerVO = new WorkerVO();
				workerVO.setWork_id(rs.getString("work_id"));
				workerVO.setW_name(rs.getString("w_name"));
				workerVO.setW_acc(rs.getString("w_acc"));
				workerVO.setW_pw(rs.getString("w_pw"));
				workerVO.setEmail(rs.getString("email"));
				workerVO.setReg_date(rs.getDate("reg_date"));
				workerVO.setPhoto(rs.getBytes("photo"));
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
		return workerVO;
	}
	
	@Override
	public WorkerVO findByAcc(String w_acc) {
		
		WorkerVO workerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACC);

			pstmt.setString(1, w_acc);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				workerVO = new WorkerVO();
				workerVO.setWork_id(rs.getString("work_id"));
				workerVO.setW_name(rs.getString("w_name"));
				workerVO.setW_acc(rs.getString("w_acc"));
				workerVO.setW_pw(rs.getString("w_pw"));
				workerVO.setEmail(rs.getString("email"));
				workerVO.setReg_date(rs.getDate("reg_date"));
				workerVO.setPhoto(rs.getBytes("photo"));
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
		return workerVO;
	}


	@Override
	public List<WorkerVO> getAll() {
		List<WorkerVO> list = new ArrayList<WorkerVO>();
		WorkerVO workerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				workerVO = new WorkerVO();
				workerVO.setWork_id(rs.getString("work_id"));
				workerVO.setW_name(rs.getString("w_name"));
				workerVO.setW_acc(rs.getString("w_acc"));
				workerVO.setW_pw(rs.getString("w_pw"));
				workerVO.setEmail(rs.getString("email"));
				workerVO.setReg_date(rs.getDate("reg_date"));
				workerVO.setPhoto(rs.getBytes("photo"));
				list.add(workerVO); // Store the row in the list
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