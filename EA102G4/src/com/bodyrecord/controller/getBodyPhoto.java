package com.bodyrecord.controller;

import java.awt.Image;
import java.io.*;

import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import javax.sql.DataSource;



import com.mem.model.*;
import com.bodyrecord.model.*;






@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class getBodyPhoto extends HttpServlet{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/INGYM");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ONE_STMT_BODYPHOTO = 
			"SELECT PHOTO FROM BODY_RECORD where BODY_NO = ?";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String body_no = req.getParameter("body_no");
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BODYPHOTO);
			
			pstmt.setString(1, body_no);
			
			rs = pstmt.executeQuery();
			OutputStream out = res.getOutputStream();
			byte[] photo = null;
			
			if(rs.next()) {
				photo = rs.getBytes("photo");
				if(photo == null) {
					InputStream in = getServletContext().getResourceAsStream("/NoData/2-1.jpg");
					photo = new byte[in.available()];
					in.read(photo);
					out.write(photo);
					in.close();
					out.close();
				}else {
					res.setContentType("image/jpeg");
					out = res.getOutputStream();
					out.write(photo);
					out.flush();
					out.close();
				}
			}else {
				InputStream in = getServletContext().getResourceAsStream("/NoData/2-1.jpg");
				photo = new byte[in.available()];
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