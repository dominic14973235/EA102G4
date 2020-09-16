package com.grouporder.model;

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

import com.godetail.model.GroupOrderDetailDAO;
import com.godetail.model.GroupOrderDetailVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;


public class GroupOrderDAO implements GroupOrderDAO_interface{
	
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
			"INSERT INTO GROUP_ORDER(G_ORDER_NO,MEM_ID)"
			+"VALUES ('GO'||LPAD(SEQ_G_ORDER_NO.NEXTVAL,7,'0'),?)";
	private static final String FIND_BY_PK =
			"SELECT * FROM GROUP_ORDER WHERE G_ORDER_NO= ?";
	private static final String GET_ALL_STMT=
			"SELECT * FROM GROUP_ORDER";
	private static final String GET_ALL_BY_MEM_ID=
			"SELECT * FROM GROUP_ORDER WHERE MEM_ID= ?";
	
	
	@Override
	public void insert(GroupOrderVO groupOrderVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			//PRO_ID,C_TYPE_NO,G_NAME,LOC,G_MAX,P_COIN,G_DETAIL,G_PIC
			pstmt.setString(1, groupOrderVO.getMem_id());
			
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
	public GroupOrderVO findByPrimaryKey(String g_order_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GroupOrderVO goVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setString(1, g_order_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				goVO = new GroupOrderVO();
				goVO.setG_order_no(g_order_no);;
				goVO.setMem_id(rs.getString("MEM_ID"));		
				goVO.setG_order_time(rs.getTimestamp("g_order_time"));
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
		return goVO;
	}

	@Override
	public List<GroupOrderVO> getAllByMem_id(String mem_id) {
		// TODO Auto-generated method stub
		List<GroupOrderVO> list = new ArrayList<GroupOrderVO>();
		GroupOrderVO goVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEM_ID);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				goVO = new GroupOrderVO();
				goVO.setMem_id(mem_id);
				goVO.setG_order_no(rs.getString("G_ORDER_NO"));
				goVO.setG_order_time(rs.getTimestamp("g_order_time"));
				list.add(goVO); // Store the row in the list
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
	public List<GroupOrderVO> getAll() {
		// TODO Auto-generated method stub
		List<GroupOrderVO> list = new ArrayList<GroupOrderVO>();
		GroupOrderVO goVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				goVO = new GroupOrderVO();
				goVO.setG_order_no(rs.getString("G_ORDER_NO"));
				goVO.setMem_id(rs.getString("MEM_ID"));
				goVO.setG_order_time(rs.getTimestamp("g_order_time"));
				list.add(goVO); // Store the row in the list
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
	public String insertWithDetail(GroupOrderVO goVO, List<GroupOrderDetailVO> list ,MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
			String cols[]= {"G_ORDER_NO"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, goVO.getMem_id());
			pstmt.executeUpdate();
			
			String nextG_order_no = null;
			ResultSet rs =pstmt.getGeneratedKeys();
			if(rs.next()) {
				nextG_order_no = rs.getString(1);
			}else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			GroupOrderDetailDAO dao = new GroupOrderDetailDAO();
			for(GroupOrderDetailVO godVO : list) {
				godVO.setG_order_no(nextG_order_no);
				dao.insertFormOrder(godVO, con);
			}
			MemService memSvc = new MemService();
			memVO = memSvc.updateMem(memVO.getMem_name(), memVO.getMem_psw(),memVO.getMem_bir(), memVO.getSex()
					, memVO.getMem_addr(),memVO.getMem_email(),memVO.getMem_phone(), memVO.getMem_absent(),
					memVO.getCoin(), memVO.getMem_resume(),memVO.getM_reg_date(),memVO.getSel_auth(),
					memVO.getArt_auth(),memVO.getCom_auth(), memVO.getMem_id());
			
			con.commit();
			con.setAutoCommit(true);
			
			return nextG_order_no;
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

}
