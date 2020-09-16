package com.license.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import com.license.model.LicenseService;
import com.license.model.LicenseVO;

@MultipartConfig()
public class licenseServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("pro_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入教練編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ProAndClass/selectLicense.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String pro_ID = null;
				try {
					pro_ID = str;
				} catch (Exception e) {
					errorMsgs.add("教練編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ProAndClass/selectLicense.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				LicenseService LicenseSvc = new LicenseService();
				List <LicenseVO> list = LicenseSvc.findByPro(pro_ID);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ProAndClass/selectLicense.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/ProAndClass/listLicense.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ProAndClass/selectLicense.jsp");
				failureView.forward(req, res);
			}
		}
		
		/*********************** 
		 * insert
		 **************************/
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String pro_ID = req.getParameter("pro_ID");
				System.out.println(pro_ID);
				if (pro_ID == null || pro_ID.trim().length() == 0) {
					errorMsgs.add("PRO_ID: 請勿空白");
				}
				
				String lic_name = req.getParameter("lic_name").trim();
				if (lic_name == null || lic_name.trim().length() == 0) {
					errorMsgs.add("lic_name請勿空白");
				}

				String no_reg = req.getParameter("no_reg").trim();
				if (no_reg == null || no_reg.trim().length() == 0) {
					errorMsgs.add("no_reg請勿空白");
				}
				

				byte[] l_pic = null;
				Part part = req.getPart("l_pic");
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				l_pic = buf;
				in.close();
				
				LicenseVO licenseVO = new LicenseVO();
				licenseVO.setPro_ID(pro_ID);
				licenseVO.setLic_name(lic_name);
				licenseVO.setNo_reg(no_reg);
				licenseVO.setL_pic(l_pic);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("licenseVO", licenseVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ProAndClass/myClass.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				LicenseService licenseSvc = new LicenseService();
				licenseVO = licenseSvc.addLicense( pro_ID,  lic_name,  no_reg, l_pic);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("licenseVO",licenseVO);
				String url = "/front-end/ProAndClass/myClass.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ProAndClass/myClass.jsp");
//				failureView.forward(req, res);
//			}
		}
		
	}
}
