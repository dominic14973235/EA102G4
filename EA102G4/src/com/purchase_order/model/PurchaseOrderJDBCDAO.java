package com.purchase_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.closeHandle.CloseHandle;
import com.product.model.ProductVO;

public class PurchaseOrderJDBCDAO implements PurchaseOrderDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERID = "INGYM";
	private static final String PASSWD = "123456";

//	private static final String INSERT_STMT = "INSERT INTO PURCHASE_ORDER(" + "        PO_NO, "
//			+ "        PO_PAYMENT," + "        PO_DELIVERY, " + "        DELIVERY_ADDRESS,"
//			+ "        QUANTITY," + "        P_PRICE," + "        PRODUCT_NO," + "        MEM_ID,"
//			+ "        PO_STATUS," + "        PURCHASE_DETAIL" + "        )" + "VALUES("
//			+ "        ('PO'||LPAD(PO_NO_SEQ.NEXTVAL,6,'0'))," + "        ?," + "        ?,"
//			+ "        ?," + "        ?," + "        (SELECT P_PRICE FROM PRODUCT WHERE PRODUCT_NO = ?),"
//			+ "        (SELECT PRODUCT_NO FROM PRODUCT WHERE PRODUCT_NO = ?),"
//			+ "        (SELECT MEM_ID FROM PRODUCT WHERE PRODUCT_NO = ?)," + "        ?," + "        ?"
//			+ ")";
	private static final String INSERT_STMT = "INSERT INTO PURCHASE_ORDER("
			+ "PO_NO, "
			+ "PO_PAYMENT,"
			+ "PO_DELIVERY, "
			+ "DELIVERY_ADDRESS,"
			+ "QUANTITY,"
			+ "P_PRICE,"
			+ "PRODUCT_NO,"
			+ "MEM_ID,"
			+ "PO_STATUS,"
			+ "BUYER_NAME,"
			+ "BUYER_PHONE,"
			+ "PURCHASE_DETAIL"
			+ ")"
			+ "VALUES(('PO'||LPAD(PO_NO_SEQ.NEXTVAL,6,'0')),?,?,?,?,(SELECT P_PRICE FROM PRODUCT WHERE PRODUCT_NO = ?),"
			+ "(SELECT PRODUCT_NO FROM PRODUCT WHERE PRODUCT_NO = ?),"
			+ "?,"
			+ "?,"
			+ "?,"
			+ "?,"
			+ "?"
			+ ")";
	private static final String GET_ALL_STMT = "SELECT * FROM PURCHASE_ORDER";
	private static final String GET_ALL_BY_MEM_ID = "SELECT * FROM PURCHASE_ORDER WHERE MEM_ID=?";
	private static final String GET_ALL_BY_SELLER_MEM_ID = "SELECT * FROM PURCHASE_ORDER PO JOIN PRODUCT P ON PO.PRODUCT_NO=P.PRODUCT_NO WHERE P.MEM_ID=?";
	private static final String GET_ONE_STMT = "SELECT * FROM PURCHASE_ORDER WHERE PO_NO = ?";
	private static final String DELETE_FROM_FRONT = "UPDATE PURCHASE_ORDER SET PO_STATUS=? WHERE PO_NO=?";
	private static final String UPDATE = "UPDATE PURCHASE_ORDER SET PURCHASE_DETAIL=?,BUYER_NAME=?, BUYER_PHONE=?, PO_PAYMENT=?, PO_DELIVERY=?, DELIVERY_ADDRESS=?, QUANTITY=?, PO_STATUS=?, PO_TIME=SYSTIMESTAMP WHERE PO_NO = ?";

	CloseHandle closeHandle = new CloseHandle();

	@Override
	public void insert(PurchaseOrderVO purchaseOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//	-------填寫寄送資料-------
//			顯示商品名稱
//			瀏覽商品頁面填寫數量 --> 顯示數量
//			商品價格
//			總金額
//			寄送方式 (勾選或是清單) 取貨付款-->選擇門市  
//			付款方式 (勾選或是清單) 信用卡-->填寫卡號  匯款-->產生匯款帳號
//			填寫姓名
//			填寫地址
//			確認頁面
//			完成頁面 --> 顯示訂單編號
			Class.forName(DRIVER);
			System.out.println("載入成功");
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, purchaseOrderVO.getPoPayment());
			pstmt.setString(2, purchaseOrderVO.getPoDelivery());
			pstmt.setString(3, purchaseOrderVO.getDeliveryAddress());
			pstmt.setDouble(4, purchaseOrderVO.getQuantity());
			pstmt.setString(5, purchaseOrderVO.getProductNo());
			pstmt.setString(6, purchaseOrderVO.getProductNo());
			pstmt.setString(7, purchaseOrderVO.getMemId());
			pstmt.setString(8, purchaseOrderVO.getPoStatus());
			pstmt.setString(9, purchaseOrderVO.getBuyerName());
			pstmt.setString(10, purchaseOrderVO.getBuyerPhone());
			pstmt.setString(11, purchaseOrderVO.getPurchaseDetail());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeHandle.close(pstmt, con);
		}

	}

	@Override
	public void update(PurchaseOrderVO purchaseOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, purchaseOrderVO.getPurchaseDetail());
			pstmt.setString(2, purchaseOrderVO.getBuyerName());
			pstmt.setString(3, purchaseOrderVO.getBuyerPhone());
			pstmt.setString(4, purchaseOrderVO.getPoPayment());
			pstmt.setString(5, purchaseOrderVO.getPoDelivery());
			pstmt.setString(6, purchaseOrderVO.getDeliveryAddress());
			pstmt.setDouble(7, purchaseOrderVO.getQuantity());
			pstmt.setString(8, purchaseOrderVO.getPoStatus());
			pstmt.setString(9, purchaseOrderVO.getPoNo());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeHandle.close(pstmt, con);
		}

	}

	@Override
	public void deleteFromFront(PurchaseOrderVO purchaseOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(DELETE_FROM_FRONT);

			pstmt.setString(1, purchaseOrderVO.getPoStatus());
			pstmt.setString(2, purchaseOrderVO.getPoNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeHandle.close(pstmt, con);
		}

	}

	@Override
	public PurchaseOrderVO findByPrimaryKey(String poNo) {

		PurchaseOrderVO purchaseOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, poNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				purchaseOrderVO = new PurchaseOrderVO();
				purchaseOrderVO.setPoNo(rs.getString("PO_NO"));
				purchaseOrderVO.setPoPayment(rs.getString("PO_PAYMENT"));
				purchaseOrderVO.setBuyerName(rs.getString("BUYER_NAME"));
				purchaseOrderVO.setBuyerPhone(rs.getString("BUYER_PHONE"));
				purchaseOrderVO.setPoDelivery(rs.getString("PO_DELIVERY"));
				purchaseOrderVO.setDeliveryAddress(rs.getString("DELIVERY_ADDRESS"));
				purchaseOrderVO.setPoStatus(rs.getString("PO_STATUS"));
				purchaseOrderVO.setMemId(rs.getString("MEM_ID"));
				purchaseOrderVO.setPoTime(rs.getTimestamp("PO_TIME"));
				purchaseOrderVO.setQuantity(rs.getDouble("QUANTITY"));
				purchaseOrderVO.setpPrice(rs.getDouble("P_PRICE"));
				purchaseOrderVO.setProductNo(rs.getString("PRODUCT_NO"));
				purchaseOrderVO.setPurchaseDetail(rs.getString("PURCHASE_DETAIL"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			closeHandle.close(rs, pstmt, con);
		}
		return purchaseOrderVO;
	}

//	@Override
	@SuppressWarnings("null")
	public List<PurchaseOrderVO> getAll() {
		List<PurchaseOrderVO> list = new ArrayList<PurchaseOrderVO>();

		PurchaseOrderVO purchaseOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(DRIVER);

			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				purchaseOrderVO = new PurchaseOrderVO();
				purchaseOrderVO.setPoNo(rs.getString("PO_NO"));
				purchaseOrderVO.setPoPayment(rs.getString("PO_PAYMENT"));
				purchaseOrderVO.setBuyerName(rs.getString("BUYER_NAME"));
				purchaseOrderVO.setBuyerPhone(rs.getString("BUYER_PHONE"));
				purchaseOrderVO.setPoDelivery(rs.getString("PO_DELIVERY"));
				purchaseOrderVO.setDeliveryAddress(rs.getString("DELIVERY_ADDRESS"));
				purchaseOrderVO.setPoStatus(rs.getString("PO_STATUS"));
				purchaseOrderVO.setMemId(rs.getString("MEM_ID"));
				purchaseOrderVO.setPoTime(rs.getTimestamp("PO_TIME"));
				purchaseOrderVO.setQuantity(rs.getDouble("QUANTITY"));
				purchaseOrderVO.setpPrice(rs.getDouble("P_PRICE"));
				purchaseOrderVO.setProductNo(rs.getString("PRODUCT_NO"));
				purchaseOrderVO.setPurchaseDetail(rs.getString("PURCHASE_DETAIL"));

				list.add(purchaseOrderVO); // Store the row in the list
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
			closeHandle.close(rs, pstmt, con);
		}

		return list;
	}
/*************************************************getOrderByMemId-start****************************************************/
	public List<PurchaseOrderVO> getOrderByMemId(String memId) {
		List<PurchaseOrderVO> list = new ArrayList<PurchaseOrderVO>();

		PurchaseOrderVO purchaseOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(DRIVER);

			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ALL_BY_MEM_ID);
			
			pstmt.setString(1,memId);
			
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				purchaseOrderVO = new PurchaseOrderVO();
				purchaseOrderVO.setPoNo(rs.getString("PO_NO"));
				purchaseOrderVO.setPoPayment(rs.getString("PO_PAYMENT"));
				purchaseOrderVO.setBuyerName(rs.getString("BUYER_NAME"));
				purchaseOrderVO.setBuyerPhone(rs.getString("BUYER_PHONE"));
				purchaseOrderVO.setPoDelivery(rs.getString("PO_DELIVERY"));
				purchaseOrderVO.setDeliveryAddress(rs.getString("DELIVERY_ADDRESS"));
				purchaseOrderVO.setPoStatus(rs.getString("PO_STATUS"));
				purchaseOrderVO.setMemId(rs.getString("MEM_ID"));
				purchaseOrderVO.setPoTime(rs.getTimestamp("PO_TIME"));
				purchaseOrderVO.setQuantity(rs.getDouble("QUANTITY"));
				purchaseOrderVO.setpPrice(rs.getDouble("P_PRICE"));
				purchaseOrderVO.setProductNo(rs.getString("PRODUCT_NO"));
				purchaseOrderVO.setPurchaseDetail(rs.getString("PURCHASE_DETAIL"));

				list.add(purchaseOrderVO); // Store the row in the list
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
			closeHandle.close(rs, pstmt, con);
		}

		return list;
	}
	
/*************************************************getOrderByMemId-end******************************************************/	
/*************************************************getOrderBySellerMemId-start******************************************************/
	public List<Object> getOrderBySellerMemId(String memId) {
		List<Object> list = new ArrayList<Object>();
//		List<Object> list2 = new ArrayList<Object>();

//		List<PurchaseOrderVO> purchaseOrderVOList = new ArrayList<PurchaseOrderVO>();
		PurchaseOrderVO purchaseOrderVO = null;
//		List<ProductVO> productVOList =  new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(DRIVER);

			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_ALL_BY_SELLER_MEM_ID);
			
			pstmt.setString(1,memId);
			
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				purchaseOrderVO = new PurchaseOrderVO();
				purchaseOrderVO.setPoNo(rs.getString("PO_NO"));
				purchaseOrderVO.setPoPayment(rs.getString("PO_PAYMENT"));
				purchaseOrderVO.setBuyerName(rs.getString("BUYER_NAME"));
				purchaseOrderVO.setBuyerPhone(rs.getString("BUYER_PHONE"));
				purchaseOrderVO.setPoDelivery(rs.getString("PO_DELIVERY"));
				purchaseOrderVO.setDeliveryAddress(rs.getString("DELIVERY_ADDRESS"));
				purchaseOrderVO.setPoStatus(rs.getString("PO_STATUS"));
				purchaseOrderVO.setMemId(rs.getString("MEM_ID"));
				purchaseOrderVO.setPoTime(rs.getTimestamp("PO_TIME"));
				purchaseOrderVO.setQuantity(rs.getDouble("QUANTITY"));
				purchaseOrderVO.setpPrice(rs.getDouble("P_PRICE"));
				purchaseOrderVO.setProductNo(rs.getString("PRODUCT_NO"));
				purchaseOrderVO.setPurchaseDetail(rs.getString("PURCHASE_DETAIL"));
				
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
				
				
				list.add(purchaseOrderVO); // Store the row in the list
				list.add(productVO);
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
			closeHandle.close(rs, pstmt, con);
		}

		return list;
	}
/*************************************************getOrderBySellerMemId-end******************************************************/	
	public static void main(String[] args) {
		PurchaseOrderJDBCDAO dao = new PurchaseOrderJDBCDAO();

		// 新增
//		PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
//
//		purchaseOrderVO.setPoPayment("10");
//		purchaseOrderVO.setPoDelivery("10");
//		purchaseOrderVO.setDeliveryAddress("桃園市中壢區北富里1515151號");
//		purchaseOrderVO.setQuantity(4.0);
//		purchaseOrderVO.setProductNo("P000001");
//		purchaseOrderVO.setPoStatus("10");
//		purchaseOrderVO.setPurchaseDetail("東西到了麻煩電話通知~~2");
//		purchaseOrderVO.setCreditCardNumber("4695210221746666");
//		purchaseOrderVO.setLastThreeNumber("456");
//		dao.insert(purchaseOrderVO);
//		System.out.println("新增成功!!");

////		顯示新增商品的編號
//		List<PurchaseOrderVO> list = dao.getAll();// 跟查詢全部共用
//		String newestProductNum = null;
//		Iterator<ProductVO> iterator = list.iterator();
//		while(iterator.hasNext()) {
//			newestProductNum = iterator.next().getProduct_no();
//		}
//		System.out.println("新增成功!!商品編號為 : " + newestProductNum);
//		顯示新增商品的編號

		// 修改
//		Timestamp time = new Timestamp(System.currentTimeMillis());
//		PurchaseOrderVO purchaseOrderVO2 = new PurchaseOrderVO();
//		purchaseOrderVO2.setPoPayment("10");
//		purchaseOrderVO2.setPoDelivery("10");
//		purchaseOrderVO2.setPoNo("PO000011");
//		purchaseOrderVO2.setPoStatus("已突進");
//		purchaseOrderVO2.setQuantity(10.0);
//		purchaseOrderVO2.setDeliveryAddress("台北市");
////		
//		dao.update(purchaseOrderVO2);
		
		System.out.println("修改成功");

//		pstmt.setDouble(1, purchaseOrderVO.getQuantity());
//		pstmt.setString(2, purchaseOrderVO.getPoStatus());
//		pstmt.setString(3, purchaseOrderVO.getPoNo());

		// 刪除(修改狀態)
//		PurchaseOrderVO purchaseOrderVO3 = new PurchaseOrderVO();
//		purchaseOrderVO3.setPoStatus("已取消");
//		purchaseOrderVO3.setPoNo("PO000012");
//		System.out.println(purchaseOrderVO3.getPoNo() + "已下架");
//		dao.deleteFromFront(purchaseOrderVO3);
//		System.out.println("下架成功");

		// 查詢
//		PurchaseOrderVO purchaseOrderVO4 = dao.findByPrimaryKey("PO000011");
//		Timestamp time = purchaseOrderVO4.getPoTime();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String timeStr = df.format(time);
//		System.out.println("--------------------------------");
//		System.out.println("訂單編號 : " + "\t" + purchaseOrderVO4.getPoNo());
//		System.out.println("付款方式 : " + "\t" + purchaseOrderVO4.getPoPayment());
//		System.out.println("寄送方式 : " + "\t" + purchaseOrderVO4.getPoDelivery());
//		System.out.println("寄送地址 : " + "\t" + purchaseOrderVO4.getDeliveryAddress());
//		System.out.println("訂單狀態 : " + "\t" + purchaseOrderVO4.getPoStatus());
//		System.out.println("賣家編號 : " + "\t" + purchaseOrderVO4.getMemId());
//		System.out.println("下單日期 : " + "\t" + timeStr);
//		System.out.println("單價 : " + "\t" + purchaseOrderVO4.getpPrice());
//		System.out.println("數量 : " + "\t" + purchaseOrderVO4.getQuantity());
//		System.out.println("訂單總金額 : " + "\t" + purchaseOrderVO4.getpPrice() * purchaseOrderVO4.getQuantity());
//		System.out.println("商品編號 : " + "\t" + purchaseOrderVO4.getProductNo());
//		System.out.println("給賣家的話 : " + "\t" + purchaseOrderVO4.getPurchaseDetail());
//
//		// 查詢全部

//			System.out.println("List建立成功");
//
//		for (PurchaseOrderVO aOrder : list) {
//			Timestamp time = aOrder.getPoTime();
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			String timeStr = df.format(time);
//			
//			System.out.println("--------------------------------");
//			System.out.println("訂單編號 : " + "\t" + aOrder.getPoNo());
//			System.out.println("付款方式 : " + "\t" + aOrder.getPoPayment());
//			System.out.println("信用卡號 : " + "\t" + aOrder.getCreditCardNumber());
//			System.out.println("末三碼 : " + "\t" + aOrder.getLastThreeNumber());
//			System.out.println("寄送方式 : " + "\t" + aOrder.getPoDelivery());
//			System.out.println("寄送地址 : " + "\t" + aOrder.getDeliveryAddress());
//			System.out.println("訂單狀態 : " + "\t" + aOrder.getPoStatus());
//			System.out.println("賣家編號 : " + "\t" + aOrder.getMemId());
//			System.out.println("下單日期 : " + "\t" + timeStr);
//			System.out.println("單價 : " + "\t" + aOrder.getpPrice());
//			System.out.println("數量 : " + "\t" + aOrder.getQuantity());
//			System.out.println("訂單總金額 : " + "\t" + aOrder.getpPrice()*aOrder.getQuantity());
//			System.out.println("商品編號 : " + "\t" + aOrder.getProductNo());
//			System.out.println("給賣家的話 : " + "\t" + aOrder.getPurchaseDetail());
//		}
//		System.out.println("--------------------------------");
//		List<PurchaseOrderVO> list5 = dao.getOrderByMemId("MEM0000001");
//		for (PurchaseOrderVO aOrder : list5) {
//			Timestamp time = aOrder.getPoTime();
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			String timeStr = df.format(time);
//			
//			System.out.println("--------------------------------");
//			System.out.println("訂單編號 : " + "\t" + aOrder.getPoNo());
//			System.out.println("付款方式 : " + "\t" + aOrder.getPoPayment());
//			System.out.println("信用卡號 : " + "\t" + aOrder.getCreditCardNumber());
//			System.out.println("末三碼 : " + "\t" + aOrder.getLastThreeNumber());
//			System.out.println("寄送方式 : " + "\t" + aOrder.getPoDelivery());
//			System.out.println("寄送地址 : " + "\t" + aOrder.getDeliveryAddress());
//			System.out.println("訂單狀態 : " + "\t" + aOrder.getPoStatus());
//			System.out.println("賣家編號 : " + "\t" + aOrder.getMemId());
//			System.out.println("下單日期 : " + "\t" + timeStr);
//			System.out.println("單價 : " + "\t" + aOrder.getpPrice());
//			System.out.println("數量 : " + "\t" + aOrder.getQuantity());
//			System.out.println("訂單總金額 : " + "\t" + aOrder.getpPrice()*aOrder.getQuantity());
//			System.out.println("商品編號 : " + "\t" + aOrder.getProductNo());
//			System.out.println("給賣家的話 : " + "\t" + aOrder.getPurchaseDetail());
//		}
		
		//根據賣家會員編號顯示所有訂單	
		List<PurchaseOrderVO> purchaseOrderVOList6 = new ArrayList<PurchaseOrderVO>();
		List<Object> list6 = dao.getOrderBySellerMemId("MEM0000001");
		
		for(Object aOrder : list6) {
			if(aOrder.toString().indexOf("PurchaseOrderVO") != -1) {
				System.out.println(aOrder);
				purchaseOrderVOList6.add((PurchaseOrderVO) aOrder);
			}
			
//			List<ProductVO> productVOList6 = null;
//			List<PurchaseOrderVO> purchaseOrderVOList6 = null;
//			try {
//				purchaseOrderVOList6 = new ArrayList<PurchaseOrderVO>();
//				purchaseOrderVOList6.add((PurchaseOrderVO)aOrder);
//				productVOList6 = new ArrayList<ProductVO>();
//				productVOList6.add((ProductVO)aOrder);
//			} catch (Exception e) {
//			} finally {
//				for(PurchaseOrderVO aPurchaseOrderVO : purchaseOrderVOList6) {
//					Timestamp time = aPurchaseOrderVO.getPoTime();
//					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//					String timeStr = df.format(time);
//					
//					System.out.println("--------------------------------");
//					System.out.println("訂單編號 : " + "\t" + aPurchaseOrderVO.getPoNo());
//					System.out.println("付款方式 : " + "\t" + aPurchaseOrderVO.getPoPayment());
//					System.out.println("信用卡號 : " + "\t" + aPurchaseOrderVO.getCreditCardNumber());
//					System.out.println("末三碼 : " + "\t" + aPurchaseOrderVO.getLastThreeNumber());
//					System.out.println("寄送方式 : " + "\t" + aPurchaseOrderVO.getPoDelivery());
//					System.out.println("寄送地址 : " + "\t" + aPurchaseOrderVO.getDeliveryAddress());
//					System.out.println("訂單狀態 : " + "\t" + aPurchaseOrderVO.getPoStatus());
//					System.out.println("賣家編號 : " + "\t" + aPurchaseOrderVO.getMemId());
//					System.out.println("下單日期 : " + "\t" + timeStr);
//					System.out.println("單價 : " + "\t" + aPurchaseOrderVO.getpPrice());
//					System.out.println("數量 : " + "\t" + aPurchaseOrderVO.getQuantity());
//					System.out.println("訂單總金額 : " + "\t" + aPurchaseOrderVO.getpPrice()*aPurchaseOrderVO.getQuantity());
//					System.out.println("商品編號 : " + "\t" + aPurchaseOrderVO.getProductNo());
//					System.out.println("給賣家的話 : " + "\t" + aPurchaseOrderVO.getPurchaseDetail());
//				}
			}
		System.out.println(purchaseOrderVOList6);
//		}
		
		System.out.println("顯示成功");
		
		
	}
}
