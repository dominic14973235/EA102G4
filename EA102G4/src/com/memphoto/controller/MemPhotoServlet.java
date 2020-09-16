package com.memphoto.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.bodyrecord.model.BodyRecordService;
import com.bodyrecord.model.BodyRecordVO;
import com.mem.model.*;
import com.memphoto.model.*;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemPhotoServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemVO memVOLogin = (MemVO)session.getAttribute("memVOLogin");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_id = req.getParameter("mem_id");
				if (mem_id == null || (mem_id.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemPhotoService memPhotoSvc = new MemPhotoService();
				List<MemPhotoVO> list = memPhotoSvc.getOneMemPhoto(mem_id);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的memPhotoVO物件,存入req
				String url = "/front-end/memphoto/listOneMemPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBodyRecord.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listOneMemPhoto.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String photo_no = req.getParameter("photo_no");
				
				/***************************2.開始查詢資料****************************************/
				MemPhotoService memPhotoSvc = new MemPhotoService();
				MemPhotoVO memPhotoVO = memPhotoSvc.getOneMemPhotoPhotoNo(photo_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memPhotoVO", memPhotoVO);         // 資料庫取出的memPhotoVO物件,存入req
				String url = "/front-end/memphoto/update_memPhoto_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交update_memPhoto_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/memphoto/listOneMemPhoto.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_id = req.getParameter("mem_id");
				if (mem_id == null || (mem_id.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemPhotoService memPhotoSvc = new MemPhotoService();
				List<MemPhotoVO> list = memPhotoSvc.getOneMemPhoto(mem_id);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的memPhotoVO物件,存入req
				String url = "/front-end/memphoto/listOneMemPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBodyRecord.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/memphoto/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("update".equals(action)) { // 來自update_memPhoto_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				//照片編號
				String photo_no = req.getParameter("photo_no");

				MemPhotoVO memPhotoVO = new MemPhotoVO();
				memPhotoVO.setPhoto_no(photo_no);
				
				/***************************2.開始修改資料*****************************************/
				
				MemPhotoService memPhotoSvc = new MemPhotoService();
				memPhotoSvc.update( photo_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String url = "/front-end/memphoto/listOneMemPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,交listOneBodyRecord.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/memphoto/update_memPhoto_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addMemPhoto.jsp的請求        
			
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
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = req.getContextPath()+"/front-end/mem/memDetail.jsp";
				res.sendRedirect(url);		
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memphoto/changeHeadPhoto.jsp");
				failureView.forward(req, res);
			}
		}
		
		
        if ("delete".equals(action)) { // 來自listOneBodyRecord.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String photo_no = req.getParameter("photo_no");
				
				/***************************2.開始刪除資料***************************************/
				MemPhotoService memPhotoSvc = new MemPhotoService();
				memPhotoSvc.deleteMemPhoto(photo_no);
				
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				String data = "success";
				out.write(data);
				out.flush();
				out.close();
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				PrintWriter out = res.getWriter();
				String data = "fail";
				out.write(data);
				out.flush();
				out.close();
				
			}
		}
	}
}
