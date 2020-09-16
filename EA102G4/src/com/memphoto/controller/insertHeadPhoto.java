package com.memphoto.controller;

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
public class insertHeadPhoto extends HttpServlet{
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemVO memVOLogin = (MemVO)session.getAttribute("memVOLogin");
		req.setCharacterEncoding("UTF-8");
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
			//會員編號
			String mem_id = memVOLogin.getMem_id();

			//照片
			Part part = req.getPart("photo");
			InputStream in = part.getInputStream();
			byte[] photo = new byte[in.available()];
			if(in.available() == 0) {
				errorMsgs.add("請上傳圖片");
			}
			in.read(photo);
			in.close();
			
			
			
			MemPhotoVO memPhotoVO = new MemPhotoVO();
			
			memPhotoVO.setMem_id(mem_id);
			memPhotoVO.setPhoto(photo);
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memPhotoVO", memPhotoVO); // 含有輸入格式錯誤的memPhotoVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memphoto/changeHeadPhoto.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			MemPhotoService memPhotoSvc = new MemPhotoService();
			
			MemPhotoVO ex_memPhotoVO = memPhotoSvc.getOneMemPhotoByStatus(mem_id);
			
			if(ex_memPhotoVO != null) {
				memPhotoSvc.update(ex_memPhotoVO.getPhoto_no());
			}
			
			memPhotoVO = memPhotoSvc.addMemPhoto(mem_id, photo);
						
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memphoto/changeHeadPhoto.jsp");
			failureView.forward(req, res);
		}
	}
}