package com.tool.hong;


import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class WorkerPhotoUpload {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "INGYM";
	String passwd = "123456";

	private static final String UPDATE_PHOTO = 
			"UPDATE WORKER SET PHOTO=? WHERE WORK_ID = ?";

	public void updatePhoto(String work_id, byte[] photo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_PHOTO);
			
			pstmt.setBytes(1, photo);
			pstmt.setString(2, work_id);
			
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
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

	public static void main(String[] args) throws IOException {

		WorkerPhotoUpload dao = new WorkerPhotoUpload();
		
		// ·s¼W
		byte[] pic1 = getPictureByteArray("C:\\vegetable\\1.jpg");
		dao.updatePhoto("WORK000001",pic1);
		byte[] pic2 = getPictureByteArray("C:\\vegetable\\2.jpg");
		dao.updatePhoto("WORK000002",pic2);
		byte[] pic3 = getPictureByteArray("C:\\vegetable\\3.jpg");
		dao.updatePhoto("WORK000003",pic3);
		byte[] pic4 = getPictureByteArray("C:\\vegetable\\4.jpg");
		dao.updatePhoto("WORK000004",pic4);
		byte[] pic5 = getPictureByteArray("C:\\vegetable\\5.jpg");
		dao.updatePhoto("WORK000005",pic5);
		byte[] pic6 = getPictureByteArray("C:\\vegetable\\6.jpg");
		dao.updatePhoto("WORK000006",pic6);
		byte[] pic7 = getPictureByteArray("C:\\vegetable\\7.jpg");
		dao.updatePhoto("WORK000007",pic7);
		byte[] pic8 = getPictureByteArray("C:\\vegetable\\8.jpg");
		dao.updatePhoto("WORK000008",pic8);
		byte[] pic9 = getPictureByteArray("C:\\vegetable\\9.jpg");
		dao.updatePhoto("WORK000009",pic9);
		byte[] pic10 = getPictureByteArray("C:\\vegetable\\10.jpg");
		dao.updatePhoto("WORK000010",pic10);

	}
}