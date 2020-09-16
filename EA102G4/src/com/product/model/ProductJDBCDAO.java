package com.product.model;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.closeHandle.CloseHandle;
import com.product_photo.model.PhotoVO;

public class ProductJDBCDAO implements ProductDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERID = "INGYM";
	private static final String PASSWD = "123456";


	private static final String INSERT_STMT = "INSERT INTO PRODUCT(product_no,p_name, mem_id, category_no, p_price, p_stock, p_detail , PO_PAYMENT , PO_DELIVERY) VALUES ('P'||LPAD(PRODUCT_NO_SEQ.NEXTVAL,6,'0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT";
	private static final String GET_ONE_STMT = "SELECT * FROM PRODUCT WHERE PRODUCT_NO=?";
	private static final String GET_ONE_BY_MEMID = "SELECT * FROM PRODUCT WHERE MEM_ID=?";
	private static final String DELETE_FROM_FRONT = "UPDATE PRODUCT SET P_STATUS=? WHERE PRODUCT_NO=?";
	
	private static final String UPDATE = "UPDATE PRODUCT SET P_NAME=?, CATEGORY_NO=?, P_PRICE=?, P_STOCK=?, P_DETAIL=?,PO_PAYMENT=?,PO_DELIVERY=?, P_UPLOAD_TIME=SYSTIMESTAMP WHERE PRODUCT_NO = ?";
	
	private static final String INSERT_PHOTO = "INSERT INTO PRODUCT_PHOTO(PHOTO_NO,PRODUCT_NO,P_PHOTO) VALUES (PHOTO_NO_SEQ.NEXTVAL,?, ?)";
	private static final String UPDATE_STOCK ="UPDATE PRODUCT SET P_STOCK = ? WHERE PRODUCT_NO = ?";
	private static final String RATING ="UPDATE PRODUCT SET NUMBER_OF_RATING = NUMBER_OF_RATING + 1,P_RATING = P_RATING + ? WHERE PRODUCT_NO = ?";
	CloseHandle closeHandle = new CloseHandle();
	
	public ProductVO updateRating(double pRating, ProductVO productVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(RATING);
			
			pstmt.setDouble(1, pRating);
			pstmt.setString(2, productVo.getProductNo());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			closeHandle.close(pstmt,con);
		}
		return null;
	}
	
	@Override
	public ProductVO updateStock(ProductVO productVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(UPDATE_STOCK);
			
			pstmt.setDouble(1, productVo.getpStock());
			pstmt.setString(2, productVo.getProductNo());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			closeHandle.close(pstmt,con);
		}
		return productVo;
	}
	@SuppressWarnings("resource")
	public void insertProductAndPhoto(ProductVO productVO,List<PhotoVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			System.out.println("載入成功");
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			con.setAutoCommit(false);
			// TODO新增商品
			String[] str = {"product_no"};
			pstmt = con.prepareStatement(INSERT_STMT,str);

			pstmt.setString(1, productVO.getpName());
			pstmt.setString(2, productVO.getMemId());
			pstmt.setString(3, productVO.getCategoryNo());
			pstmt.setDouble(4, productVO.getpPrice());
			pstmt.setDouble(5, productVO.getpStock());
			pstmt.setString(6, productVO.getpDetail());
			pstmt.setString(7, productVO.getPoPayment());
			pstmt.setString(8, productVO.getPoDelivery());
			
			

			pstmt.executeUpdate();
			rs= pstmt.getGeneratedKeys(); //取得自增主鍵
			String generatedProductNo = null;
			if (rs.next()) {
				generatedProductNo = rs.getString(1);
				// 知其僅有一列,故獲取第一列
				System.out.println("-----預定義SQL模式-----id = " + generatedProductNo);
			}
			// TODO新增圖片
			pstmt = con.prepareStatement(INSERT_PHOTO);
			Iterator<PhotoVO> it = list.iterator();
			while(it.hasNext()) {
				PhotoVO itNext = it.next();
				pstmt.setString(1, generatedProductNo);
				pstmt.setBytes(2, itNext.getpPhoto());
				System.out.println("一張圖片進來了!");
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			
			con.commit();
		
		}catch (Exception ee) {
			ee.printStackTrace();
			try {
				System.out.println("錯誤發生 rollback");
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}finally {
			closeHandle.close(rs, pstmt, con);
		}
	}
	
	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			System.out.println("載入成功");
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productVO.getpName());
			pstmt.setString(2, productVO.getMemId());
			pstmt.setString(3, productVO.getCategoryNo());
			pstmt.setDouble(4, productVO.getpPrice());
			pstmt.setDouble(5, productVO.getpStock());
			pstmt.setString(6, productVO.getpDetail());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeHandle.close(pstmt,con);
		}

	}

	@SuppressWarnings("resource")
	@Override
	public void update(ProductVO productVO,List<PhotoVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getpName());
			pstmt.setString(2, productVO.getCategoryNo());
			pstmt.setDouble(3, productVO.getpPrice());
			pstmt.setDouble(4, productVO.getpStock());
			pstmt.setString(5, productVO.getpDetail());
			pstmt.setString(6, productVO.getPoPayment());
			pstmt.setString(7, productVO.getPoDelivery());
			pstmt.setString(8, productVO.getProductNo());

			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(INSERT_PHOTO);
			Iterator<PhotoVO> it = list.iterator();
			while(it.hasNext()) {
				PhotoVO itNext = it.next();
				pstmt.setString(1, productVO.getProductNo());
				pstmt.setBytes(2, itNext.getpPhoto());
				System.out.println("一張圖片進來了!");
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			con.commit();

			// Handle any driver errors
		} catch (Exception e) {
			System.out.println("發生問題rollback執行");
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			closeHandle.close(pstmt,con);
		}

	}

	@Override
	public void deleteFromFront(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(DELETE_FROM_FRONT);

			pstmt.setString(1, productVO.getpStatus());
			pstmt.setString(2, productVO.getProductNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeHandle.close(pstmt,con);
		}

	}

	/****************************************************************findByMemId-start****************************************************************/
	@Override
	public List<ProductVO> findByMemId(String memId) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ONE_BY_MEMID);

			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
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
				productVO.setNumberOfRating(rs.getDouble("number_of_rating"));
				list.add(productVO);
			}
			System.out.println("載入成功DAO-findByMemId");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeHandle.close(rs,pstmt,con);
		}
		return list;
	}
	/****************************************************************findByMemId-end****************************************************************/
	
	@Override
	public ProductVO findByPrimaryKey(String product_no) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, product_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
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
				productVO.setNumberOfRating(rs.getDouble("number_of_rating"));
				productVO.setPoPayment(rs.getString("PO_PAYMENT"));
				productVO.setPoDelivery(rs.getString("PO_DELIVERY"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeHandle.close(rs,pstmt,con);
		}
		return productVO;
	}

//	@Override
	@SuppressWarnings("null")
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {

			Class.forName(DRIVER);

			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
				productVO.setPoPayment(rs.getString("PO_PAYMENT"));
				productVO.setPoDelivery(rs.getString("PO_DELIVERY"));
				
					list.add(productVO); // Store the row in the list
			}
			
			System.out.println("載入成功");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} 
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		 finally {
			closeHandle.close(outputStream,inputStream,rs,pstmt,con);
		}
		
		return list;
	}

	public static void main(String[] args) {
		ProductJDBCDAO dao = new ProductJDBCDAO();

		// 新增
//		ProductVO productVO1 = new ProductVO();
//		productVO1.setpName("健身用品");
//		productVO1.setMemId("MEM000001");
//		productVO1.setpPrice(2000.0);
//		productVO1.setpStock(100.0);
//		productVO1.setCategoryNo("C001");
//		productVO1.setpDetail("來買喔");
//		dao.insert(productVO1);
////		顯示新增商品的編號
//		List<ProductVO> list = dao.getAll();// 跟查詢全部共用
//		String newestProductNum = null;
//		Iterator<ProductVO> iterator = list.iterator();
//		while(iterator.hasNext()) {
//			newestProductNum = iterator.next().getProduct_no();
//		}
//		System.out.println("新增成功!!商品編號為 : " + newestProductNum);
//		顯示新增商品的編號

		// 修改
//		Timestamp time = new Timestamp(System.currentTimeMillis());
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setProductNo("P000001");
//		productVO2.setMem_id("M000001");
//		productVO2.setP_name("d");
//		productVO2.setP_price(2000.0);
//		productVO2.setpStock(100.0);
//		productVO2.setCategory_no("C001");
//		productVO2.setP_rating(1.0);
//		productVO2.setP_status("ddd");
//		productVO2.setP_detail("ddd");
//		productVO2.setP_upload_time(null);
//		dao.updateStock(productVO2);
//		System.out.println("修改成功");
//
		// 刪除(修改狀態)
//		ProductVO productVO3 = new ProductVO();
//		productVO3.setP_status("已下架");
//		productVO3.setProduct_no("P000005");
//		System.out.println(productVO3.getProduct_no() + "已下架");
//		dao.deleteFromFront(productVO3);
//		System.out.println("下架成功");
//		// 查詢
//		ProductVO ProductVO4 = dao.findByPrimaryKey("P000004");
//		Timestamp time= ProductVO4.getP_upload_time();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String timeStr = df.format(time); 
//		System.out.println("--------------------------------");
//		System.out.println("商品編號 : " + "\t" + ProductVO4.getProduct_no());
//		System.out.println("商品名稱 : " + "\t" + ProductVO4.getP_name());
//		System.out.println("會員編號 : " + "\t" + ProductVO4.getMem_id());
//		System.out.println("類別編號 : " + "\t" + ProductVO4.getCategory_no());
//		System.out.println("單價 : " + "\t" + ProductVO4.getP_price());
//		System.out.println("庫存 : " +"\t" + ProductVO4.getP_stock());
//		System.out.println("商品詳情 : " + "\t" + ProductVO4.getP_detail());
//		System.out.println("上架日期 : " + "\t" + timeStr);
//		System.out.println("總評分 : " +"\t" + ProductVO4.getP_rating());
//		System.out.println("評價人數 : " + "\t" + ProductVO4.getNumber_of_rating());
//		System.out.println("狀態 : " + "\t" + ProductVO4.getP_status());

//
//		// 查詢全部

//			System.out.println("List建立成功");

//		for (ProductVO aProduct : list) {
//			Timestamp time = aProduct.getpUploadTime();
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			String timeStr = df.format(time);
//			System.out.println("--------------------------------");
//			System.out.println("商品編號 : " + "\t" + aProduct.getProductNo());
//			System.out.println("商品名稱 : " + "\t" + aProduct.getpName());
//			System.out.println("會員編號 : " + "\t" + aProduct.getMemId());
//			System.out.println("類別編號 : " + "\t" + aProduct.getCategoryNo());
//			System.out.println("單價 : " + "\t" + aProduct.getpPrice());
//			System.out.println("庫存 : " + "\t" + aProduct.getpStock());
//			System.out.println("商品詳情 : " + "\t" + aProduct.getpDetail());
//			System.out.println("上架日期 : " + "\t" + timeStr);
//			System.out.println("總評分 : " + "\t" + aProduct.getpRating());
//			System.out.println("評價人數 : " + "\t" + aProduct.getNumberOfRating());
//			System.out.println("" + "狀態 : " + "\t" + aProduct.getpStatus());
//
//		}
//		System.out.println("--------------------------------");
		
		//新增商品和圖片
//		ProductVO productVO5 = new ProductVO();
//		PhotoVO photoVO5 = new PhotoVO();
//		List<PhotoVO> list5 = new ArrayList<PhotoVO>();
//		productVO5.setpName("健身用品");
//		productVO5.setMemId("MEM0000001");
//		productVO5.setpPrice(2000.0);
//		productVO5.setpStock(100.0);
//		productVO5.setCategoryNo("C001");
//		productVO5.setpDetail("來買喔");
//		productVO5.setPoPayment("20");
//		productVO5.setPoDelivery("20");
//		String s = "cccc";
//		s.getBytes();
//		photoVO5.setpPhoto(s.getBytes());
//		list5.add(photoVO5);
//		photoVO5.setpPhoto(s.getBytes());
//		list5.add(photoVO5);
//		photoVO5.setpPhoto(s.getBytes());
//		list5.add(photoVO5);
//		photoVO5.setpPhoto(s.getBytes());
//		list5.add(photoVO5);
//		dao.insertProductAndPhoto(productVO5,list5);

		//從會員編號找尋商品
//		List<ProductVO> list6 =  dao.findByMemId("MEM0000001");
//		for(ProductVO aProduct : list6) {
//			Timestamp time = aProduct.getpUploadTime();
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			String timeStr = df.format(time);
//			System.out.println("--------------------------------");
//			System.out.println("商品編號 : " + "\t" + aProduct.getProductNo());
//			System.out.println("商品名稱 : " + "\t" + aProduct.getpName());
//			System.out.println("會員編號 : " + "\t" + aProduct.getMemId());
//			System.out.println("類別編號 : " + "\t" + aProduct.getCategoryNo());
//			System.out.println("單價 : " + "\t" + aProduct.getpPrice());
//			System.out.println("庫存 : " + "\t" + aProduct.getpStock());
//			System.out.println("商品詳情 : " + "\t" + aProduct.getpDetail());
//			System.out.println("上架日期 : " + "\t" + timeStr);
//			System.out.println("總評分 : " + "\t" + aProduct.getpRating());
//			System.out.println("評價人數 : " + "\t" + aProduct.getNumberOfRating());
//			System.out.println("" + "狀態 : " + "\t" + aProduct.getpStatus());
//		}
		//給予評分
		ProductVO productVO7 = new ProductVO();
		productVO7.setProductNo("P000001");
		dao.updateRating(5, productVO7);
		
		System.out.println("顯示成功");
	}
		

}