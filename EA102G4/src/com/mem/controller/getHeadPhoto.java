package com.mem.controller;



import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import javax.sql.DataSource;



import com.mem.model.*;
import com.memphoto.model.*;




@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class getHeadPhoto extends HttpServlet{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/INGYM");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ONE_STMT_PHOTO_STATUS = 
			"SELECT PHOTO_NO, MEM_ID, PHOTO_STATUS, PHOTO FROM MEMBER_PHOTO where (PHOTO_STATUS = 'P1') AND (MEM_ID = ?)";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String mem_id = req.getParameter("mem_id");
		
		MemPhotoService memPhotoSvc = new MemPhotoService();
		memPhotoSvc.getOneMemPhotoByStatus(mem_id);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_PHOTO_STATUS);
			
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			OutputStream out = res.getOutputStream();
			
			if(rs.next()) {
				byte[] photo = rs.getBytes("photo");
				res.setContentType("image/jpeg");
				out.write(photo);
				out.flush();
				out.close();
			}else {
				InputStream in = getServletContext().getResourceAsStream("/NoData/2-1.jpg");
				byte[] photo = new byte[in.available()];
				in.read(photo);
				out.write(photo);
				in.close();
				out.close();
			}
		
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
	}
}