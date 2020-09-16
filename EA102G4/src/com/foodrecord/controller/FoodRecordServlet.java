package com.foodrecord.controller;

import java.io.*;
import java.util.*;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.foodrecord.model.*;
import com.foods.model.*;
@MultipartConfig
public class FoodRecordServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		if ("All_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String mem_id = req.getParameter("mem_id");
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String work_id = null;
//				try {
//					work_id = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				FoodRecordService foodrecorsSvc = new FoodRecordService();
//				List<FoodRecordVO> list = foodrecorsSvc.getAll();
//				if (list.isEmpty()) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
//				req.setAttribute("mem_id", mem_id); //測試用
//				String url = "/front-end/foodrecord/listAllFoodRecord.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_id = req.getParameter("mem_id");
				
				/***************************2.開始查詢資料*****************************************/
				FoodRecordService foodrecorsSvc = new FoodRecordService();
				List<FoodRecordVO> list = foodrecorsSvc.getAllByMem(mem_id);
				if (list.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
//				req.setAttribute("mem_id", mem_id); //測試用
				String url = "/front-end/foodrecord/listAllFoodRecordByMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("Date_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_id = req.getParameter("mem_id");			
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
//				FoodRecordService foodrecorsSvc = new FoodRecordService();
//				List<FoodRecordVO> list = foodrecorsSvc.getAllByMem(mem_id);
//				TreeSet<Date> set = new TreeSet<Date>();
//
//				if (list.isEmpty()) {
//					errorMsgs.add("查無資料");
//				} else {
//					for(FoodRecordVO i : list ) {
//						set.add(i.getFoodr_date());
//					}
//				}
//				System.out.println(set);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				String url = "/front-end/foodrecord/listAllFoodRecordByDate2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodrecord/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/***************************1.接收請求參數****************************************/
				String foodr_no = new String(req.getParameter("foodr_no"));
//				String mem_id = new String(req.getParameter("mem_id"));
				
				/***************************2.開始查詢資料****************************************/
				FoodRecordService foodrecordSvc = new FoodRecordService();
				FoodRecordVO foodrecordVO = foodrecordSvc.getOneFoodRecord(foodr_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("foodrecordVO", foodrecordVO);// 資料庫取出的empVO物件,存入req
				
				String url = "/front-end/foodrecord/updateFoodRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodrecord/foodRecord.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String foodr_no = new String(req.getParameter("foodr_no"));
				String mem_id = new String(req.getParameter("mem_id"));
				
				StringBuffer sb = new StringBuffer();
				String food_name = req.getParameter("food_name");
				if (food_name == null || food_name.trim().length() == 0) {
					errorMsgs.add("食物請勿空白");
				} else {
				FoodService foodSvc = new FoodService(); 
					try {
						FoodVO foodVO = foodSvc.getOneByName(food_name);
						if(foodVO.getFood_no() != null) {
						sb.append(foodVO.getFood_no());
						}
					} catch (NullPointerException e) {
						errorMsgs.add("無此食物資料");
					}
				}
				String food_no = sb.toString();
							
				String foodr_time = new String(req.getParameter("foodr_time"));
				
				java.sql.Date foodr_date = null;
				try {
					foodr_date = java.sql.Date.valueOf(req.getParameter("foodr_date").trim());
				} catch (IllegalArgumentException e) {
					foodr_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer foodr_weight = null;
				try {
					foodr_weight = new Integer(req.getParameter("foodr_weight"));
					if (foodr_weight < 0 || foodr_weight >= 999) {
						errorMsgs.add("請填三位數以內且不可小於0");
					}
				} catch (NumberFormatException e) {
					foodr_weight = 1;
					errorMsgs.add("請填數字.");
				}
				
				FoodRecordVO foodrecordVO = new FoodRecordVO();
				foodrecordVO.setFoodr_no(foodr_no);
				foodrecordVO.setMem_id(mem_id);
				foodrecordVO.setFood_no(food_no);
				foodrecordVO.setFoodr_time(foodr_time);
				foodrecordVO.setFoodr_date(foodr_date);
				foodrecordVO.setFoodr_weight(foodr_weight);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("foodrecordVO", foodrecordVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foodrecord/updateFoodRecord.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				FoodRecordService foodrecordSvc = new FoodRecordService();
				foodrecordVO = foodrecordSvc.updateFoodRecord(mem_id, food_no, foodr_time, foodr_date, foodr_weight, foodr_no);			
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("foodrecordVO", foodrecordVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				String url = "/front-end/foodrecord/foodRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodrecord/updateFoodRecord.jsp");
				failureView.forward(req, res);
			}
		}
		
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_id = req.getParameter("mem_id");
				
				StringBuffer sb = new StringBuffer();
				String food_name = req.getParameter("food_name");
				if (food_name == null || food_name.trim().length() == 0) {
					errorMsgs.add("食物請勿空白");
				}
				FoodService foodSvc = new FoodService(); 
				try {
					FoodVO foodVO = foodSvc.getOneByName(food_name);
					
					if(foodVO != null) {
						sb.append(foodVO.getFood_no());
					} else {
						errorMsgs.add("無此食物資料");
					}				
				} catch (NullPointerException e) {
					errorMsgs.add("無此食物資料");
				}
				String food_no = sb.toString();
				
				String foodr_time = req.getParameter("foodr_time");
				
				java.sql.Date foodr_date = null;
				try {
					foodr_date = java.sql.Date.valueOf(req.getParameter("foodr_date").trim());
				} catch (IllegalArgumentException e) {
					foodr_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer foodr_weight = null;
				try {
					foodr_weight = new Integer(req.getParameter("foodr_weight"));
					if (foodr_weight < 0 || foodr_weight >= 999) {
						errorMsgs.add("請填三位數以內且不可小於0");
					}
				} catch (NumberFormatException e) {
					foodr_weight = 1;
					errorMsgs.add("請填數字.");
				}
				
				FoodRecordVO foodrecordVO = new FoodRecordVO();
				foodrecordVO.setMem_id(mem_id);
				foodrecordVO.setFood_no(food_no);
				foodrecordVO.setFoodr_time(foodr_time);
				foodrecordVO.setFoodr_date(foodr_date);
				foodrecordVO.setFoodr_weight(foodr_weight);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("foodrecordVO", foodrecordVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foodrecord/addFoodRecord.jsp");
					failureView.forward(req, res);
					return;
				}
			
				/***************************2.開始新增資料***************************************/
				FoodRecordService foodrecordSvc = new FoodRecordService();
				foodrecordVO = foodrecordSvc.addFoodRecord(mem_id, food_no, foodr_time, foodr_date, foodr_weight);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/foodrecord/foodRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodrecord/addFoodRecord.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String foodr_no = new String(req.getParameter("foodr_no"));

				/***************************2.開始刪除資料***************************************/
				FoodRecordService foodrecordSvc = new FoodRecordService();
				foodrecordSvc.deleteFoodRecord(foodr_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/foodrecord/listAllFoodRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foodrecord/listAllFoodRecord.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	
}
