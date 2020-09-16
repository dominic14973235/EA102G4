package com.product_category.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;

public class Product_categoryJDBCDAO implements Product_categoryDAO_interface {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERID = "INGYM";
	private static final String PASSWD = "123456";

	private static final String INSERT_STMT = "INSERT INTO PRODUCT_CATEGORY VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT_CATEGORY";
	private static final String GET_ONE_STMT = "SELECT * FROM PRODUCT_CATEGORY WHERE CATEGORY_NO=?";
//		private static final String DELETE_FROM_FRONT = 
//			"UPDATE PRODUCT SET P_STATUS=? WHERE PRODUCT_NO=?";
	private static final String UPDATE = "UPDATE PRODUCT_CATEGORY SET CATEGORY_NAME=? WHERE CATEGORY_NO = ?";
	private static final String GET_Emps_ByDeptno_STMT = "SELECT * FROM PRODUCT where CATEGORY_NO = ? ";
//	private static final String GET_Emps_ByDeptno_STMT = "SELECT * FROM PRODUCT where CATEGORY_NO = ? order by PRODUCT_NO";

	@Override
	public void insert(Product_categoryVO product_categoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			System.out.println("載入成功");
			con = DriverManager.getConnection(url, USERID, PASSWD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, product_categoryVO.getCategoryName());
			pstmt.setString(2, product_categoryVO.getCategoryNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(Product_categoryVO product_categoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, USERID, PASSWD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, product_categoryVO.getCategoryNo());
			pstmt.setString(2, product_categoryVO.getCategoryName());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
//		public void deleteFromFront(Product_categoryVO product_categoryVO) {
//
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//
//				Class.forName(driver);
//				con = DriverManager.getConnection(url, userid, passwd);
//				pstmt = con.prepareStatement(DELETE_FROM_FRONT);
//				
//				pstmt.setString(1, product_categoryVO.getP_status());
//				pstmt.setString(2, product_categoryVO.getProduct_no());
//
//				pstmt.executeUpdate();
//
//				// Handle any driver errors
//			} catch (ClassNotFoundException e) {
//				throw new RuntimeException("Couldn't load database driver. "
//						+ e.getMessage());
//				// Handle any SQL errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//
//		}

	public Product_categoryVO findByPrimaryKey(String category_no) {

		Product_categoryVO product_categoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, category_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				product_categoryVO = new Product_categoryVO();
				product_categoryVO.setCategoryNo(rs.getString("CATEGORY_NO"));
				product_categoryVO.setCategoryName(rs.getString("CATEGORY_NAME"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return product_categoryVO;
	}

//		@Override
	public List<Product_categoryVO> getAll() {
		List<Product_categoryVO> list = new ArrayList<Product_categoryVO>();
		Product_categoryVO product_categoryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);

			con = DriverManager.getConnection(url, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				product_categoryVO = new Product_categoryVO();
				product_categoryVO.setCategoryNo(rs.getString("CATEGORY_NO"));
				product_categoryVO.setCategoryName(rs.getString("CATEGORY_NAME"));

				list.add(product_categoryVO); // Store the row in the list
//					System.out.println("while");
			}
			System.out.println("載入成功");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Set<ProductVO> getProductsByCategory(String category_no) {
		Set<ProductVO> set = new LinkedHashSet<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_Emps_ByDeptno_STMT);

			pstmt.setString(1, category_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				productVO = new ProductVO();
				productVO.setProductNo(rs.getString("PRODUCT_NO"));
				productVO.setpName(rs.getString("P_NAME"));
				productVO.setMemId(rs.getString("MEM_ID"));
				productVO.setCategoryNo(rs.getString("CATEGORY_NO"));
				productVO.setpPrice(rs.getDouble("P_PRICE"));
				productVO.setpStock(rs.getDouble("P_STOCK"));
				productVO.setpDetail(rs.getString("P_DETAIL"));
				productVO.setpUploadTime(rs.getTimestamp("P_UPLOAD_TIME"));
				productVO.setpRating(rs.getDouble("P_RATING"));
				productVO.setpStatus(rs.getString("P_STATUS"));
				set.add(productVO); // Store the row in the list
//				System.out.println("while");
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public static void main(String[] args) {
		Product_categoryJDBCDAO dao = new Product_categoryJDBCDAO();
//		 修改
//			Product_categoryVO product_categoryVO2 = new Product_categoryVO();
//			product_categoryVO2.setCategory_no("C003");;
//			product_categoryVO2.setCategory_name("修改類別");;
//
//			dao.update(product_categoryVO2);
//			System.out.println("修改成功");
		// 新增
//		Product_categoryVO productVO1 = new Product_categoryVO();
//		productVO1.setCategory_name("分類測試");
//
//		dao.insert(productVO1);
//////			顯示新增商品的編號
//		List<Product_categoryVO> list = dao.getAll();// 跟查詢全部共用
//		String newestProductNum = null;
//		Iterator<Product_categoryVO> iterator = list.iterator();
//		while (iterator.hasNext()) {
//			newestProductNum = iterator.next().getCategory_no();
//		}
//		System.out.println("新增成功!!分類編號為 : " + newestProductNum);
////			顯示新增商品的編號

//			// 查詢
//		Product_categoryVO product_categoryVO4 = dao.findByPrimaryKey("C004");
//		System.out.println("--------------------------------");
//		System.out.println("類別編號 : " + "\t" + product_categoryVO4.getCategoryName());
//		System.out.println("類別名稱 : " + "\t" + product_categoryVO4.getCategoryNo());
//		System.out.println("--------------------------------");
		//
//			// 查詢全部

//				System.out.println("List建立成功");

//		for (Product_categoryVO aProduct_category : list) {
//			System.out.println("--------------------------------");
//			System.out.println("類別編號 : " + "\t" + aProduct_category.getCategory_no());
//			System.out.println("類別名稱 : " + "\t" + aProduct_category.getCategory_name());
//
//		}
		// 查詢某類別的商品
		String category = "C001";
		System.out.println("以下為類別編號" + category + "的所有商品");
		Set<ProductVO> set = dao.getProductsByCategory(category);
		for (ProductVO aProduct : set) {
			Timestamp time = aProduct.getpUploadTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String timeStr = df.format(time);
			System.out.println("--------------------------------");
			System.out.println("商品編號 : " + "\t" + aProduct.getProductNo());
			System.out.println("商品名稱 : " + "\t" + aProduct.getpName());
			System.out.println("會員編號 : " + "\t" + aProduct.getMemId());
			System.out.println("類別編號 : " + "\t" + aProduct.getCategoryNo());
			System.out.println("單價 : " + "\t" + aProduct.getpPrice());
			System.out.println("庫存 : " + "\t" + aProduct.getpStock());
			System.out.println("商品詳情 : " + "\t" + aProduct.getpDetail());
			System.out.println("上架日期 : " + "\t" + timeStr);
			System.out.println("總評分 : " + "\t" + aProduct.getpRating());
			System.out.println("評價人數 : " + "\t" + aProduct.getNumberOfRating());
			System.out.println("狀態 : " + "\t" + aProduct.getpStatus());
			System.out.println();
		}
		System.out.println("--------------------------------");
		System.out.println("顯示成功");
	}
}
