package com.product_photo.model;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.closeHandle.CloseHandle;
import com.mysql.jdbc.Statement;
import com.product.model.ProductVO;

public class PhotoJDBCDAO implements PhotoDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERID = "INGYM";
	private static final String PASSWD = "123456";

	private static final String INSERT_STMT = "INSERT INTO PRODUCT_PHOTO(PHOTO_NO,PRODUCT_NO,P_PHOTO) VALUES (PHOTO_NO_SEQ.NEXTVAL,?, ?)";
	private static final String SHOW_PIC = "SELECT * FROM PRODUCT_PHOTO WHERE product_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT_PHOTO";
	private static final String UPDATE = "UPDATE PRODUCT_PHOTO SET PRODUCT_NO = ? WHERE PRODUCT_NO = ?";
	private static final String GET_FIRST_PHOTO = "SELECT * FROM\r\n" + 
			"(SELECT * FROM PRODUCT_PHOTO\r\n" + 
			"WHERE PHOTO_NO IN (SELECT MIN(PHOTO_NO)\r\n" + 
			"FROM PRODUCT_PHOTO\r\n" + 
			"GROUP BY PRODUCT_NO))\r\n" + 
			"WHERE PRODUCT_NO = ?";
	
	CloseHandle closeHandle = new CloseHandle();

//	@Override
//	public PhotoVO update(String productNo) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USERID, PASSWD);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, photoVO.getProductNo());
//			pstmt.setString(2, productNo);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			closeHandle.close(pstmt,con);
//		}
//		return photoVO;
//	}
	
	
	
	@Override
	public void insert(PhotoVO photoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			System.out.println("載入成功");
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(INSERT_STMT);
			String[] str = {"product_no"};
			pstmt = con.prepareStatement(INSERT_STMT, str);

			pstmt.setString(1, photoVO.getProductNo());
			pstmt.setBytes(2, photoVO.getpPhoto());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				String id = rs.getString(1);

				// 知其僅有一列,故獲取第一列

//				System.out.println("-----預定義SQL模式-----id = " + id);

			}
			System.out.println("新增成功");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeHandle.close(rs, pstmt, con);
		}

	}

	///////////////////// get-start///////////////////////
	public PhotoVO get(String product_no) {

		PhotoVO photoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {
			Class.forName(DRIVER);
			System.out.println("載入成功");
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(SHOW_PIC);
			pstmt.setString(1, product_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				photoVO = new PhotoVO();
				product_no = rs.getString("product_no");
				Blob pPhoto = rs.getBlob("p_photo");

				// -------------------Blob to String-------------------
				inputStream = pPhoto.getBinaryStream();
				outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				// -------------------Blob to String-------------------

//				photoVO.setPhotoNo(product_no);
				photoVO.setBase64Image(base64Image);
			}

		} catch (Exception ex) {
			System.out.println("第一圈拿不到");
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			closeHandle.close(outputStream, inputStream, rs, pstmt, con);
		}
		return photoVO;
	}
/////////////////////get-end///////////////////////

/////////////////////getFirstPhoto-start///////////////////////
	public PhotoVO getFirstPhoto(String product_no) {

		PhotoVO photoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {
			Class.forName(DRIVER);
			System.out.println("載入成功");
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(GET_FIRST_PHOTO);
			pstmt.setString(1, product_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				photoVO = new PhotoVO();
				product_no = rs.getString("product_no");
				Blob pPhoto = rs.getBlob("p_photo");

				// -------------------Blob to String-------------------
				inputStream = pPhoto.getBinaryStream();
				outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				// -------------------Blob to String-------------------

//				photoVO.setPhotoNo(product_no);
				photoVO.setBase64Image(base64Image);
			}

		} catch (Exception ex) {
			System.out.println("第一圈拿不到");
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			closeHandle.close(outputStream, inputStream, rs, pstmt, con);
		}
		return photoVO;
	}
	
/////////////////////getFirstPhoto-end///////////////////////
	
/////////////////////getOneProductAllPhoto-start///////////////////////
	public List<PhotoVO> getOneProductAllPhoto(String productNo){
		List<PhotoVO> list = new ArrayList<PhotoVO>(); 
		PhotoVO photoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {
			Class.forName(DRIVER);
			System.out.println("載入成功");
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			pstmt = con.prepareStatement(SHOW_PIC);
			pstmt.setString(1, productNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				photoVO = new PhotoVO();
				productNo = rs.getString("product_no");
				Blob pPhoto = rs.getBlob("p_photo");

				// -------------------Blob to String-------------------
				inputStream = pPhoto.getBinaryStream();
				outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				// -------------------Blob to String-------------------

				photoVO.setBase64Image(base64Image);
				list.add(photoVO);
			}
				
		}catch(Exception ee){
			ee.printStackTrace();
		}finally {
			closeHandle.close(outputStream,inputStream,rs,pstmt,con);
		}
		
		
		return list;
	}
	
/////////////////////getOneProductAllPhoto-end///////////////////////
	
///////////////////getAll-start/////////////////////////
	@Override
	public List<PhotoVO> getAll() {
		List<PhotoVO> list = new ArrayList<PhotoVO>();
		PhotoVO photoVO = null;
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
				photoVO = new PhotoVO();
				Blob pPhoto = rs.getBlob("p_photo");
				// -------------------Blob to String-------------------
				inputStream = pPhoto.getBinaryStream();
				outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				// -------------------Blob to String-------------------

				photoVO.setBase64Image(base64Image);
				list.add(photoVO); // Store the row in the list
			}
			System.out.println("載入成功");
			// Handle any driver errors
		} catch (Exception e) {
				e.printStackTrace();
			// Handle any SQL errors
		} finally {
			closeHandle.close(outputStream,inputStream,rs,pstmt,con);
		}
		return list;
	}

///////////////////getAll-end/////////////////////////
///////////////////insertWithParperedSQL-start/////////////////////////
	public PhotoVO insertWithStaticSQL() {
		PhotoVO photoVO = new PhotoVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERID, PASSWD);
			String sql = "insert into book values(BOOK_SEQ.NEXTVAL,?,?)";
			pstmt = con.prepareStatement(sql, Statement.NO_GENERATED_KEYS);
			pstmt.setString(1, "laozhang");
			pstmt.setString(2, "111111");
			pstmt.executeUpdate();
			
			//檢索由於執行此 Statement 對象而創建的所有自動生成的鍵
			rs = pstmt.getGeneratedKeys();

			if (rs.next()) {

				// 知其僅有一列,故獲取第一列

				Integer id = rs.getInt(1);
				System.out.println("-----預定義SQL模式-----id = " + id);

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			closeHandle.close(rs, pstmt, con);
		}
		return photoVO;
	}

///////////////////insertWithParperedSQL-end/////////////////////////

	public static void main(String[] args) {
		PhotoJDBCDAO dao = new PhotoJDBCDAO();
//		PhotoVO vo = new PhotoVO();
//		vo.setProductNo("P000001");
//		List<PhotoVO> list = dao.getOneProductAllPhoto("P000003");
//		for (PhotoVO aPhoto : list) {
//			System.out.println(aPhoto);
//			System.out.println("----------------------");
//		}
//		PhotoVO photoVO = new PhotoVO();
//		photoVO.setProductNo("P000001");
//		
//		dao.insert(photoVO);
		
		//update圖片的商品編號
		PhotoVO vo2 = new PhotoVO();
//		vo2.setProductNo(productNo);
//		dao.update(vo2);
	}

}
