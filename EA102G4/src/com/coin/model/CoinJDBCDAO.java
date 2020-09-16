package com.coin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CoinJDBCDAO implements CoinDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO COIN_ORDER(COIN_ID,MEM_ID,DEPOSIT_COIN,AMOUNT,COIN_DATE) VALUES(COIN_ORDER_SEQ.NEXTVAL,?,?,?,SYSDATE)";
	private static final String GET_ALL_STMT =
			"SELECT * FROM COIN_ORDER";
	private static final String UPDATE =
			"UPDATE COIN_ORDER SET DEPOSIT_COIN=?,AMOUNT=? WHERE COIN_ID = ?";
	private static final String FIND_BY_PK =
			"SELECT * FROM COIN_ORDER WHERE COIN_ID= ?";
	private static final String FIND_BY_MEM_ID =
			"SELECT * FROM COIN_ORDER WHERE MEM_ID= ?";
	
	
	@Override
	public String insert(CoinVO coinvo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, coinvo.getMem_id());
			pstmt.setInt(2, coinvo.getDeposit_coin());
			pstmt.setInt(3, coinvo.getAmount());
			
			pstmt.executeUpdate();		
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("無法載入DB"+e.getMessage());
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
		return null;
	}
	@Override
	public void update(CoinVO coinvo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		//UPDATE COIN_ORDER SET COIN=?,AMOUNT=? WHERE COIN_ID = ?
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1,coinvo.getDeposit_coin());
			pstmt.setInt(2,coinvo.getAmount());
			pstmt.setString(3,coinvo.getCoin_id());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public CoinVO findByPrimaryKey(String coin_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CoinVO coinvo = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			}
			return coinvo;
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public List<CoinVO> getAllByMem_Id(String mem_id) {
		// TODO Auto-generated method stub
		List<CoinVO> list = new ArrayList<CoinVO>();
		CoinVO coinvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				list.add(coinvo); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				coinvo = new CoinVO();
				coinvo.setAmount(rs.getInt("amount"));
				coinvo.setDeposit_coin((rs.getInt("deposit_coin")));
				coinvo.setCoin_date(rs.getTimestamp("coin_date"));
				coinvo.setMem_id(rs.getString("mem_id"));
				coinvo.setCoin_id("coin_id");
				list.add(coinvo); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	public static void main(String[]args) {
		CoinJDBCDAO dao = new CoinJDBCDAO();

		// 新增ASDasdfsadf
		CoinVO coinvo = new CoinVO();
		coinvo.setAmount(2500);
		coinvo.setDeposit_coin(2500);
		
		coinvo.setMem_id("MEM0100000");
		dao.insert(coinvo);
//		coinvo.setCoin_id("1");
//		dao.update(coinvo);
		//UPDATE COIN_ORDER SET DEPOSIT_COIN=?,AMOUNT=? WHERE COIN_ID = ?
//		CoinVO coinvo1 = dao.findByPrimaryKey("1");
//		System.out.print(coinvo1.getCoin_id()+ ",");
//		System.out.print(coinvo1.getMem_id()+",");
//		System.out.print(coinvo1.getAmount()+ ",");
//		System.out.print(coinvo1.getCoin_date()+ ",");
//		System.out.print(coinvo1.getdeposit_coin() + ",");
//		System.out.println("---------------------");

	}
}
