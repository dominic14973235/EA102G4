package com.coin.model;

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

public class CoinDAO implements CoinDAO_interface{

//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "INGYM";
//	String passwd = "123456";
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
			"INSERT INTO COIN_ORDER(COIN_ID,MEM_ID,DEPOSIT_COIN,AMOUNT,COIN_DATE) VALUES('COINO'||LPAD(COIN_ORDER_SEQ.NEXTVAL, 5, '0'),?,?,?,SYSDATE)";
	private static final String GET_ALL_STMT =
			"SELECT * FROM COIN_ORDER";
	private static final String UPDATE =
			"UPDATE COIN_ORDER SET DEPOSIT_COIN=?,AMOUNT=?,CO_STATUS=? WHERE COIN_ID = ?";
	private static final String UPDATE_COIN_STATUS =
			"UPDATE COIN_ORDER SET CO_STATUS=? WHERE COIN_ID = ?";
	private static final String FIND_BY_PK =
			"SELECT * FROM COIN_ORDER WHERE COIN_ID= ?";
	private static final String FIND_BY_MEM_ID =
			"SELECT * FROM COIN_ORDER WHERE MEM_ID= ?";

	@Override
	public String insert(CoinVO coinvo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		String nextCoin_id = null;
		try {
			
//			"INSERT INTO COIN_ORDER(COIN_ID,MEM_ID,DEPOSIT_COIN,AMOUNT,COIN_DATE) "
//			+ "VALUES('COINO'||LPAD(COIN_ORDER_SEQ.NEXTVAL), 5, '0'),?,?,?,SYSDATE)"
			con = ds.getConnection();
			String cols[]= {"COIN_ID"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setString(1, coinvo.getMem_id());
			pstmt.setInt(2, coinvo.getDeposit_coin());
			pstmt.setInt(3, coinvo.getAmount());
			pstmt.executeUpdate();		
			ResultSet rs =pstmt.getGeneratedKeys();
			if(rs.next()) {
				nextCoin_id = rs.getString(1);
			}else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
		} catch(SQLException se) {
			throw new RuntimeException("資料庫錯誤   "+se.getMessage());
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
		return nextCoin_id;
		
	}
	@Override
	public void update(CoinVO coinvo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		//UPDATE COIN_ORDER SET COIN=?,AMOUNT=? WHERE COIN_ID = ?
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1,coinvo.getDeposit_coin());
			pstmt.setInt(2,coinvo.getAmount());
			pstmt.setInt(3, coinvo.getCo_status());
			pstmt.setString(4,coinvo.getCoin_id());
			pstmt.executeUpdate();

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
	public CoinVO findByPrimaryKey(String coin_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CoinVO coinvo = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setString(1, coin_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				coinvo = new CoinVO();
				coinvo.setCoin_id(rs.getString("coin_id"));
				coinvo.setMem_id(rs.getString("mem_id"));
				coinvo.setDeposit_coin(rs.getInt("deposit_coin"));
				coinvo.setAmount(rs.getInt("amount"));
				coinvo.setCoin_date(rs.getTimestamp("coin_date"));
				coinvo.setCo_status(rs.getInt("co_status"));
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
		return coinvo;
	}
	@Override
	public List<CoinVO> getAllByMem_Id(String mem_id) {
		// TODO Auto-generated method stub
		List<CoinVO> list = new ArrayList<CoinVO>();
		CoinVO coinvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MEM_ID);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				coinvo = new CoinVO();
				coinvo.setAmount(rs.getInt("amount"));
				coinvo.setDeposit_coin((rs.getInt("deposit_coin")));
				coinvo.setCoin_date(rs.getTimestamp("coin_date"));
				coinvo.setMem_id(mem_id);
				coinvo.setCoin_id(rs.getString("coin_id"));
				coinvo.setCo_status(rs.getInt("co_status"));
				list.add(coinvo); // Store the row in the list
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
	public List<CoinVO> getAll() {
		List<CoinVO> list = new ArrayList<CoinVO>();
		CoinVO coinvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				coinvo = new CoinVO();
				coinvo.setAmount(rs.getInt("amount"));
				coinvo.setDeposit_coin((rs.getInt("deposit_coin")));
				coinvo.setCoin_date(rs.getTimestamp("coin_date"));
				coinvo.setMem_id(rs.getString("mem_id"));
				coinvo.setCoin_id(rs.getString("coin_id"));
				coinvo.setCo_status(rs.getInt("co_status"));
				list.add(coinvo); // Store the row in the list
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
