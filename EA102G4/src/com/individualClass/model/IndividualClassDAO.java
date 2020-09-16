package com.individualClass.model;

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

public class IndividualClassDAO implements IndividualClassDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO INDIVIDUAL_CLASS(I_CLASS_NO,PRO_ID,C_TYPE_NO,C_NAME,LOC,C_DETAIL,C_PIC,C_STATUS, P_COIN)VALUES('IC'||LPAD(SEQ_I_CLASS_NO.NEXTVAL,7,'0'),?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM INDIVIDUAL_CLASS ORDER BY I_CLASS_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM INDIVIDUAL_CLASS WHERE I_CLASS_NO =?";
	private static final String GET_PRO_STMT = "SELECT * FROM INDIVIDUAL_CLASS WHERE PRO_ID =?";
	private static final String GET_TYPE_NO_STMT = "SELECT * FROM INDIVIDUAL_CLASS WHERE C_TYPE_NO =?";
	private static final String DELETE = "DELETE FROM INDIVIDUAL_CLASS WHERE I_CLASS_NO=?";
	private static final String UPDATE = "UPDATE INDIVIDUAL_CLASS SET PRO_ID=?,C_TYPE_NO=? ,C_NAME=?,LOC=?,C_DETAIL=?,C_PIC=?,C_STATUS=?, P_COIN=? where I_CLASS_NO=?";

	@Override
	public void insert(IndividualClassVO individualClassVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			(I_CLASS_NO,PRO_ID,C_TYPE_NO,C_NAME,LOC,C_DETAIL,C_PIC,C_STATUS)VALUES('IC'||LPAD(SEQ_I_CLASS_NO.NEXTVAL,7,'0'),?,?,?,?,?,?,?)
			pstmt.setString(1, individualClassVO.getPro_ID());
			pstmt.setString(2, individualClassVO.getC_type_no());
			pstmt.setString(3, individualClassVO.getC_name());
			pstmt.setString(4, individualClassVO.getLoc());
			pstmt.setString(5, individualClassVO.getC_detail());
			pstmt.setBytes(6, individualClassVO.getC_pic());
			pstmt.setInt(7, individualClassVO.getC_status());
			pstmt.setInt(8, individualClassVO.getP_coin());

			pstmt.executeUpdate();

//			System.out.println("insert successfully"); // testing


			// Handle any SQL errors
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
	public void update(IndividualClassVO individualClassVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, individualClassVO.getPro_ID());
			pstmt.setString(2, individualClassVO.getC_type_no());
			pstmt.setString(3, individualClassVO.getC_name());
			pstmt.setString(4, individualClassVO.getLoc());
			pstmt.setString(5, individualClassVO.getC_detail());
			pstmt.setBytes(6, individualClassVO.getC_pic());
			pstmt.setInt(7, individualClassVO.getC_status());
			pstmt.setInt(8, individualClassVO.getP_coin());
			pstmt.setString(9, individualClassVO.getI_class_no());


			pstmt.executeUpdate();

//			System.out.println("Update successfully"); // testing

			// Handle any SQL errors
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
	public void delete(String i_class_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, i_class_no);

			pstmt.executeUpdate();

//			System.out.println("delete successfully"); // testing

			// Handle any SQL errors
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
	public IndividualClassVO findPrimaryKey(String i_class_no) {
		IndividualClassVO individualClassVO =  new IndividualClassVO();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, i_class_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				individualClassVO.setI_class_no(rs.getString("i_class_no"));
				individualClassVO.setPro_ID(rs.getString("pro_id"));
				individualClassVO.setC_type_no(rs.getString("c_type_no"));
				individualClassVO.setC_name(rs.getString("c_name"));
				individualClassVO.setLoc(rs.getString("loc"));
				individualClassVO.setC_detail(rs.getString("c_detail"));
				individualClassVO.setC_pic(rs.getBytes("c_pic"));
				individualClassVO.setC_status(rs.getInt("c_status"));
				individualClassVO.setP_coin(rs.getInt("p_coin"));
			}
			// System.out.println("query successfully"); //testing

			// Handle any SQL errors
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

		return individualClassVO;
	}

	@Override
	public List<IndividualClassVO> getAll() {
		List<IndividualClassVO> list = new ArrayList<IndividualClassVO>();
		IndividualClassVO individualClassVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				individualClassVO = new IndividualClassVO();

				individualClassVO.setI_class_no(rs.getString("i_class_no"));
				individualClassVO.setPro_ID(rs.getString("pro_id"));
				individualClassVO.setC_type_no(rs.getString("c_type_no"));
				individualClassVO.setC_name(rs.getString("c_name"));
				individualClassVO.setLoc(rs.getString("loc"));
				individualClassVO.setC_detail(rs.getString("c_detail"));
				individualClassVO.setC_pic(rs.getBytes("c_pic"));
				individualClassVO.setC_status(rs.getInt("c_status"));
				individualClassVO.setP_coin(rs.getInt("p_coin"));
				list.add(individualClassVO);
			}

//			System.out.println("query successfully"); // testing
			// Handle any SQL errors
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

		return list;
	}

	@Override
	public List<IndividualClassVO> findByPro(String pro_ID) {
		List<IndividualClassVO> list = new ArrayList<IndividualClassVO>();
		IndividualClassVO individualClassVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_PRO_STMT);

			pstmt.setString(1, pro_ID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				individualClassVO = new IndividualClassVO();

				individualClassVO.setI_class_no(rs.getString("i_class_no"));
				individualClassVO.setPro_ID(rs.getString("pro_id"));
				individualClassVO.setC_type_no(rs.getString("c_type_no"));
				individualClassVO.setC_name(rs.getString("c_name"));
				individualClassVO.setLoc(rs.getString("loc"));
				individualClassVO.setC_detail(rs.getString("c_detail"));
				individualClassVO.setC_pic(rs.getBytes("c_pic"));
				individualClassVO.setC_status(rs.getInt("c_status"));
				individualClassVO.setP_coin(rs.getInt("p_coin"));
				list.add(individualClassVO);
			}

//			System.out.println("query successfully"); // testing

			// Handle any SQL errors
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

		return list;
	}

	@Override
	public List<IndividualClassVO> findByTypeNo(String c_type_no) {
		List<IndividualClassVO> list = new ArrayList<IndividualClassVO>();
		IndividualClassVO individualClassVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_TYPE_NO_STMT);

			pstmt.setString(1, c_type_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				individualClassVO = new IndividualClassVO();

				individualClassVO.setI_class_no(rs.getString("i_class_no"));
				individualClassVO.setPro_ID(rs.getString("pro_id"));
				individualClassVO.setC_type_no(rs.getString("c_type_no"));
				individualClassVO.setC_name(rs.getString("c_name"));
				individualClassVO.setLoc(rs.getString("loc"));
				individualClassVO.setC_detail(rs.getString("c_detail"));
				individualClassVO.setC_pic(rs.getBytes("c_pic"));
				individualClassVO.setC_status(rs.getInt("c_status"));
				individualClassVO.setP_coin(rs.getInt("p_coin"));
				list.add(individualClassVO);
			}

//			System.out.println("query successfully"); // testing

			// Handle any SQL errors
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

		return list;
	}

	//Model testing
//	public static void main(String args[]){
//		IndividualClassJDBCDAO dao = new IndividualClassJDBCDAO();
//		IndividualClassVO individualClassVO = new IndividualClassVO();

		// 增 
//		individualClassVO.setPro_ID("PRO1000004");
//		individualClassVO.setC_type_no("CT003");
//		individualClassVO.setC_name("雞雞養養");
//		individualClassVO.setLoc("花蓮花花健身房");
//		individualClassVO.setC_detail("炒資咬百 草枝搖擺");
////		individualClassVO.setC_pic("C:\\Users\\USER\\Desktop\\pic\\啊ㄕ");
//		individualClassVO.setC_status(1);
//		dao.insert(individualClassVO);

		// 改
//		individualClassVO.setI_class_no("IC1000003");
//		individualClassVO.setPro_ID("PRO1000006");
//		individualClassVO.setC_type_no("CT005");
//		individualClassVO.setC_name("想養雞雞半拉");
//		individualClassVO.setLoc("炒枝炒枝炒枝");
//		individualClassVO.setC_detail("炒資咬百");
//		individualClassVO.setC_status(0);
//		dao.update(individualClassVO);

//		// 刪
//		dao.delete("IC1000012");
//
//		//查
//		individualClassVO =dao.findPrimaryKey("IC1000010");
//		System.out.println(individualClassVO.getI_class_no());
//		System.out.println(individualClassVO.getPro_ID());
//		System.out.println(individualClassVO.getC_type_no());
//		System.out.println(individualClassVO.getC_name());
//		System.out.println(individualClassVO.getLoc());
//		System.out.println(individualClassVO.getC_detail());
//		System.out.println(individualClassVO.getC_status());
//		
//		List<IndividualClassVO> list = dao.findByPro("PRO1000001");
//		for (IndividualClassVO ct : list) {
//		System.out.println(ct.getI_class_no());
//		System.out.println(ct.getPro_ID());
//		System.out.println(ct.getC_type_no());
//		System.out.println(ct.getC_name());
//		System.out.println(ct.getLoc());
//		System.out.println(ct.getC_detail());
//		System.out.println(ct.getC_status());
//		System.out.println();
//		}
//	}
}
