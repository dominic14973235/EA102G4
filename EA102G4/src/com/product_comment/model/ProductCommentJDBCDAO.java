package com.product_comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.closeHandle.CloseHandle;

public class ProductCommentJDBCDAO implements ProductCommentDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERID = "INGYM";
	private static final String PASSWD = "123456";

	private static final String INSERT_STMT = "INSERT INTO  PRODUCT_COMMENT VALUES('PC'||LPAD(PRODUCT_COMMENT_NO_SEQ.NEXTVAL,6,'0'),?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT_COMMENT";
	private static final String GET_ALL_STMT_BY_PRODUCT_NO = "SELECT * FROM PRODUCT_COMMENT WHERE PRODUCT_NO=?";
	private static final String GET_ALL_STMT_BY_MEM_ID = "SELECT * FROM PRODUCT_COMMENT WHERE MEM_ID_BUYER=? OR MEM_ID_SELLER=?";

	CloseHandle closeHandle = new CloseHandle();

	@Override
	public void insert(ProductCommentVO productCommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productCommentVO.getMemIdBuyer());
			pstmt.setString(2, productCommentVO.getMemIdSeller());
			pstmt.setString(3, productCommentVO.getProductNo());
			pstmt.setString(4, productCommentVO.getProductComment());
			pstmt.setString(5, productCommentVO.getpRatingEach());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeHandle.close(pstmt, con);
		}

	}

	@Override
	public List<ProductCommentVO> getAll() {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ProductCommentVO productCommentVO = null;
		List<ProductCommentVO> list = new ArrayList<ProductCommentVO>();
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				productCommentVO = new ProductCommentVO();
				
				productCommentVO.setProductCommentNo(rs.getString("PRODUCT_COMMENT_NO"));
				productCommentVO.setProductComment(rs.getString("PRODUCT_COMMENT"));
				productCommentVO.setMemIdBuyer(rs.getString("MEM_ID_BUYER"));
				productCommentVO.setMemIdSeller(rs.getString("MEM_ID_SELLER"));
				productCommentVO.setProductNo(rs.getString("PRODUCT_NO"));
				productCommentVO.setpRatingEach(rs.getString("P_RATING_EACH"));
				list.add(productCommentVO);
			}

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeHandle.close(rs, pstmt, con);
		}
		return list;
	}

	
	 public List<ProductCommentVO> getByProductNo(String productNo){
			// TODO Auto-generated method stub

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			ProductCommentVO productCommentVO = null;
			List<ProductCommentVO> list = new ArrayList<ProductCommentVO>();
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USERID, PASSWD);
				pstmt = con.prepareStatement(GET_ALL_STMT_BY_PRODUCT_NO);
				pstmt.setString(1, productNo);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					productCommentVO = new ProductCommentVO();
					productCommentVO.setMemIdBuyer(rs.getString("MEM_ID_BUYER"));
					productCommentVO.setMemIdSeller(rs.getString("MEM_ID_SELLER"));
					productCommentVO.setProductNo(rs.getString("PRODUCT_NO"));
					productCommentVO.setProductCommentNo(rs.getString("PRODUCT_COMMENT_NO"));
					productCommentVO.setProductComment(rs.getString("PRODUCT_COMMENT"));
					productCommentVO.setpRatingEach(rs.getString("P_RATING_EACH"));
					list.add(productCommentVO);
				}

				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeHandle.close(rs, pstmt, con);
			}
			return list;
	 }
	 
	 public List<ProductCommentVO> getByMemId(String memId){
			// TODO Auto-generated method stub

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			ProductCommentVO productCommentVO = null;
			List<ProductCommentVO> list = new ArrayList<ProductCommentVO>();
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USERID, PASSWD);
				pstmt = con.prepareStatement(GET_ALL_STMT_BY_MEM_ID);
				pstmt.setString(1, memId);
				pstmt.setString(2, memId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					productCommentVO = new ProductCommentVO();
					productCommentVO.setMemIdBuyer(rs.getString("MEM_ID_BUYER"));
					productCommentVO.setMemIdSeller(rs.getString("MEM_ID_SELLER"));
					productCommentVO.setProductNo(rs.getString("PRODUCT_NO"));
					productCommentVO.setProductCommentNo(rs.getString("PRODUCT_COMMENT_NO"));
					productCommentVO.setProductComment(rs.getString("PRODUCT_COMMENT"));
					productCommentVO.setpRatingEach(rs.getString("P_RATING_EACH"));
					list.add(productCommentVO);
				}

				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeHandle.close(rs, pstmt, con);
			}
			return list;
	 }
	 
	public static void main(String[] args) {
		ProductCommentJDBCDAO dao = new ProductCommentJDBCDAO();
		//新增評論
		ProductCommentVO productCommentVO = new ProductCommentVO();
		productCommentVO.setMemIdBuyer("MEM0000001");
		productCommentVO.setMemIdSeller("MEM0000002");
		productCommentVO.setProductNo("P000002");
		productCommentVO.setProductComment("值得推薦的好賣家");
		dao.insert(productCommentVO);
		System.out.println("新增評論-成功");
		
		//取得所有評論
//		List<ProductCommentVO> list = dao.getAll();
//		for(ProductCommentVO aProductComment : list) {
//			System.out.println("評論編號: " + aProductComment.getProductCommentNo());
//			System.out.println("會員編號: " + aProductComment.getMemId());
//			System.out.println("商品編號: " + aProductComment.getProductNo());
//			System.out.println("商品評論: " + aProductComment.getProductComment());
//			System.out.println("----------------------------------------------------");
//		}
//		System.out.println("取得所有評論-成功");
		
		//取得該商品評論
//		List<ProductCommentVO> list2 = dao.getByProductNo("P000001");
		List<ProductCommentVO> list2 = dao.getByMemId("MEM0000052");
		for(ProductCommentVO aProductComment : list2) {
			System.out.println("評論編號: " + aProductComment.getProductCommentNo());
			System.out.println("買家會員編號: " + aProductComment.getMemIdBuyer());
			System.out.println("賣家會員編號: " + aProductComment.getMemIdSeller());
			System.out.println("商品編號: " + aProductComment.getProductNo());
			System.out.println("商品評論: " + aProductComment.getProductComment());
			System.out.println("----------------------------------------------------");
		}
		System.out.println("取得該商品評論-成功");
	}
}
