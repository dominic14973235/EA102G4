package com.foods.controller;

import java.io.*;
import java.util.*;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.worker.model.*;
import com.workpower.model.*;
import com.foodrecord.model.*;
import com.foods.model.*;
@MultipartConfig
public class FoodServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String food_no = new String(req.getParameter("food_no"));
				
				/***************************2.開始查詢資料****************************************/
				FoodService foodSvc = new FoodService();
				FoodVO foodVO = foodSvc.getOneFood(food_no);			
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("foodVO", foodVO);// 資料庫取出的empVO物件,存入req
				
				String url = "/back-end/food/update_food_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/food/listAllFoodBackEnd.jsp");
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
				String food_no = req.getParameter("food_no");
				
				String food_name = req.getParameter("food_name");
				if (food_name == null || food_name.trim().length() == 0) {
					errorMsgs.add("名稱請勿空白");
				}
				
				Double starch = null;
				try {
					starch = new Double(req.getParameter("starch"));
					if (starch < 0) {
						errorMsgs.add("不可小於0");
					}
				} catch (NumberFormatException e) {
					starch = 0.0;
					errorMsgs.add("請填數字.");
				}
				
				Double protein = null;
				try {
					protein = new Double(req.getParameter("protein"));
					if (protein < 0) {
						errorMsgs.add("不可小於0");
					}
				} catch (NumberFormatException e) {
					protein = 0.0;
					errorMsgs.add("請填數字.");
				}
				
				Double fat = null;
				try {
					fat = new Double(req.getParameter("fat"));
					if (fat < 0) {
						errorMsgs.add("不可小於0");
					}
				} catch (NumberFormatException e) {
					fat = 0.0;
					errorMsgs.add("請填數字.");
				}
				
				Double kcal = null;
				try {
					kcal = new Double(req.getParameter("kcal"));
					if (kcal < 0) {
						errorMsgs.add("不可小於0");
					}
				} catch (NumberFormatException e) {
					kcal = 0.0;
					errorMsgs.add("請填數字.");
				}
				
				FoodVO foodVO = new FoodVO();
				foodVO.setFood_no(food_no);
				foodVO.setFood_name(food_name);
				foodVO.setStarch(starch);
				foodVO.setProtein(protein);
				foodVO.setFat(fat);
				foodVO.setKcal(kcal);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("foodVO", foodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/food/update_food_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				FoodService foodSvc = new FoodService();
				foodVO = foodSvc.update(food_no, food_name, starch, protein, fat, kcal);		
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("foodVO", foodVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				String url = "/back-end/food/listAllFoodBackEnd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/food/update_food_input.jsp");
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
				String food_name = req.getParameter("food_name");
				if (food_name == null || food_name.trim().length() == 0) {
					errorMsgs.add("名稱請勿空白");
				}
				
				Double starch = null;
				try {
					starch = new Double(req.getParameter("starch"));
					if (starch < 0) {
						errorMsgs.add("不可小於0");
					}
				} catch (NumberFormatException e) {
					starch = 0.0;
					errorMsgs.add("請填數字.");
				}
				
				Double protein = null;
				try {
					protein = new Double(req.getParameter("protein"));
					if (protein < 0) {
						errorMsgs.add("不可小於0");
					}
				} catch (NumberFormatException e) {
					protein = 0.0;
					errorMsgs.add("請填數字.");
				}
				
				Double fat = null;
				try {
					fat = new Double(req.getParameter("fat"));
					if (fat < 0) {
						errorMsgs.add("不可小於0");
					}
				} catch (NumberFormatException e) {
					fat = 0.0;
					errorMsgs.add("請填數字.");
				}
				
				Double kcal = null;
				try {
					kcal = new Double(req.getParameter("kcal"));
					if (kcal < 0) {
						errorMsgs.add("不可小於0");
					}
				} catch (NumberFormatException e) {
					kcal = 0.0;
					errorMsgs.add("請填數字.");
				}
				
				FoodVO foodVO = new FoodVO();
				foodVO.setFood_name(food_name);
				foodVO.setStarch(starch);
				foodVO.setProtein(protein);
				foodVO.setFat(fat);
				foodVO.setKcal(kcal);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("foodVO", foodVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/food/addFood.jsp");
					failureView.forward(req, res);
					return;
				}
			
				/***************************2.開始新增資料***************************************/
				FoodService foodSvc = new FoodService();
				foodVO = foodSvc.addFood(food_name, starch, protein, fat, kcal);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/food/listAllFoodBackEnd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/food/addFood.jsp");
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
				String food_no = new String(req.getParameter("food_no"));
				
				/***************************2.開始刪除資料***************************************/
				FoodService foodSvc = new FoodService();
				foodSvc.delete(food_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/food/listAllFoodBackEnd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/food/listAllFoodBackEnd.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	
}
