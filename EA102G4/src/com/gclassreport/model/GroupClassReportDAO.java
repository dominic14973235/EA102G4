package com.gclassreport.model;

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


public class GroupClassReportDAO implements GroupClassReportDAO_interface{

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
			"INSERT INTO G_REPORT(G_REPORT_NO,MEM_ID,G_CLASS_NO,R_CONTENT,RTIME)"
			+"VALUES ('GCR'||LPAD(SEQ_G_REPORT_NO.NEXTVAL,6,'0'),?,?,?,?)";
	private static final String UPDATE_STATUS =
			"UPDATE G_REPORT SET R_STATUS=? WHERE G_REPORT_NO= ?";
	private static final String UPDATE_CONTENT=
			"UPDATE G_REPORT SET R_CONTENT=? WHERE G_REPORT_NO= ?";
	private static final String FIND_BY_PK =
			"SELECT * FROM G_REPORT WHERE G_REPORT_NO= ?";
	private static final String GET_ALL_STMT=
			"SELECT * FROM G_REPORT";
	private static final String GET_ALL_BY_MEM_ID=
			"SELECT * FROM G_REPORT WHERE MEM_ID= ?";
	private static final String  GET_ALL_BY_G_CLASS_NO=
			"SELECT * FROM G_REPORT WHERE G_CLASS_NO";
	
	@Override
	public void insert(GroupClassReportVO grVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			//"INSERT INTO G_REPORT(G_REPORT_NO,MEM_ID,G_CLASS_NO,R_CONTENT,RTIME)"
			//+"VALUES ('GCR'||LPAD(SEQ_G_REPORT_NO.NEXTVAL,6,'0'),?,?,?,?)";
			pstmt.setString(1, grVO.getMem_id());
			pstmt.setString(2, grVO.getG_class_no());
			pstmt.setString(3, grVO.getR_content());
			pstmt.setTimestamp(4, grVO.getRtime());

			
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
	public GroupClassReportVO findByPrimaryKey(String c_report_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GroupClassReportVO grVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setString(1, c_report_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				grVO = new GroupClassReportVO();
				grVO.setG_report_no(rs.getString("G_REPORT_NO"));
				grVO.setG_class_no(rs.getString("G_CLASS_NO"));
				grVO.setMem_id(rs.getString("MEM_ID"));
				grVO.setR_content(rs.getString("R_CONTENT"));
				grVO.setR_status(rs.getString("R_STATUS"));
				grVO.setRtime(rs.getTimestamp("RTIME"));
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
		return grVO;
	}

	@Override
	public List<GroupClassReportVO> getAllByMem_id(String mem_id) {
		// TODO Auto-generated method stub
		List<GroupClassReportVO> list = new ArrayList<GroupClassReportVO>();
		GroupClassReportVO grVO = null;
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
				grVO = new GroupClassReportVO();
				grVO.setG_report_no(rs.getString("G_REPORT_NO"));
				grVO.setG_class_no(rs.getString("G_CLASS_NO"));
				grVO.setMem_id(rs.getString("MEM_ID"));
				grVO.setR_content(rs.getString("R_CONTENT"));
				grVO.setR_status(rs.getString("R_STATUS"));
				grVO.setRtime(rs.getTimestamp("RTIME"));
				
				list.add(grVO); // Store the row in the list
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
	public List<GroupClassReportVO> getAllByG_class_no(String g_class_no) {
		// TODO Auto-generated method stub
		List<GroupClassReportVO> list = new ArrayList<GroupClassReportVO>();
		GroupClassReportVO grVO = null;
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
				grVO = new GroupClassReportVO();
				grVO.setG_report_no(rs.getString("G_REPORT_NO"));
				grVO.setG_class_no(rs.getString("G_CLASS_NO"));
				grVO.setMem_id(rs.getString("MEM_ID"));
				grVO.setR_content(rs.getString("R_CONTENT"));
				grVO.setR_status(rs.getString("R_STATUS"));
				grVO.setRtime(rs.getTimestamp("RTIME"));
				
				list.add(grVO); // Store the row in the list
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
	public List<GroupClassReportVO> getAll() {
		// TODO Auto-generated method stub
		List<GroupClassReportVO> list = new ArrayList<GroupClassReportVO>();
		GroupClassReportVO grVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				grVO = new GroupClassReportVO();
				grVO.setG_report_no(rs.getString("G_REPORT_NO"));
				grVO.setG_class_no(rs.getString("G_CLASS_NO"));
				grVO.setMem_id(rs.getString("MEM_ID"));
				grVO.setR_content(rs.getString("R_CONTENT"));
				grVO.setR_status(rs.getString("R_STATUS"));
				grVO.setRtime(rs.getTimestamp("RTIME"));
				
				list.add(grVO); // Store the row in the list
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
	public void updateStatus(GroupClassReportVO grVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, grVO.getR_status());
			pstmt.setString(2, grVO.getG_report_no());
				
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
	public void updateContent(GroupClassReportVO grVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, grVO.getR_content());
			pstmt.setString(2, grVO.getG_report_no());
				
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

}
