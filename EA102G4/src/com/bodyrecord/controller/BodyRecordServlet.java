package com.bodyrecord.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;


import com.mem.model.*;
import com.bodyrecord.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BodyRecordServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

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
							.getRequestDispatcher("/front-end/bodyrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/bodyrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BodyRecordService bodyRecordSvc = new BodyRecordService();
				List<BodyRecordVO> list = bodyRecordSvc.getOneBodyRecordByMemId(mem_id);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/bodyrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的bodyRecordVO物件,存入req
				String url = "/front-end/bodyrecord/bodyRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBodyRecord.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bodyrecord/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listOneBodyRecord.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String body_no = req.getParameter("body_no");
				
				
				/***************************2.開始查詢資料****************************************/
				BodyRecordService bodyRecordSvc = new BodyRecordService();
				BodyRecordVO bodyRecordVO = bodyRecordSvc.getOneBodyRecordByBodyNo(body_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("bodyRecordVO", bodyRecordVO);         // 資料庫取出的bodyRecordVO物件,存入req
				String url = "/front-end/bodyrecord/update_bodyRecord_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交update_bodyRecord_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bodyrecord/bodyRecord.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_bodyRecord_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				//身體數據紀錄編號
				String body_no = req.getParameter("body_no");
				
				//會員編號
				String mem_id = req.getParameter("mem_id");
				
				//紀錄日期
				java.sql.Date body_date = null;
				try {
					body_date = java.sql.Date.valueOf(req.getParameter("body_date").trim());
				} catch (IllegalArgumentException e) {
					body_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				//身高
				float mem_height=0;
				String myheight = req.getParameter("mem_height").trim();
				String heightReg = "^[1-9]{1}[0-9]{1,2}+.{0,1}+[0-9]{0,1}$";
				if (myheight == null || myheight.length() == 0) {
					errorMsgs.add("身高: 請勿空白");
				} else if(!myheight.matches(heightReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("身高: 請輸入正確的身高");
	            }else {
	            	mem_height = new Float(myheight);
	            }
	            	
	            
				
				
				//體重
				float mem_weight = 0;
				String myweight = req.getParameter("mem_weight").trim();
				String weightReg = "^[1-9]{1}[0-9]{1,2}+.{0,1}+[0-9]{0,1}$";
				if (myweight == null || myweight.length() == 0) {
					errorMsgs.add("體重: 請勿空白");
				} else if(!myweight.matches(weightReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("體重: 請輸入正確的體重");
	            }else {
	            	mem_weight = new Float(myweight);
	            }
	            	
	            
				
				
				//BMI指數
				float mem_bmi = 0;
				String mybmi = req.getParameter("mem_bmi").trim();
				String bmiReg = "^[1-9]{1}[0-9]{0,1}+.{0,1}+[0-9]{0,1}$";
				if (mybmi == null || mybmi.length() == 0) {
					errorMsgs.add("BMI指數: 請勿空白");
				} else if(!mybmi.matches(bmiReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("BMI指數: 請輸入正確的BMI指數");
	            }else {
	            	mem_bmi = new Float(mybmi);
	            }
	            	
	            
				
				
				//年齡
				int mem_old = 0;
				String myold = req.getParameter("mem_old").trim();
				String oldReg = "^[1-9]{1}[0-9]{0,2}$";
				if (myold == null || myold.length() == 0) {
					errorMsgs.add("年齡: 請勿空白");
				} else if(!myold.matches(oldReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("年齡: 你是鬼吧!!");
	            }else {
	            	mem_old = new Integer(myold);
	            }
	            	
	            
				
				
				
				//BMR指數
				float mem_bmr = 0;
				String mybmr = req.getParameter("mem_bmr").trim();
				String bmrReg = "^[1-9]{1}[0-9]{2,3}+.{0,1}+[0-9]{0,1}$";
				if (mybmr == null || mybmr.length() == 0) {
					errorMsgs.add("BMR指數: 請勿空白 ");
				} else if(!mybmr.matches(bmrReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("BMR指數: 請輸入正確的BMR指數");
	            }else {
	            	mem_bmr = new Float(mybmr);
	            }
	            	
	            
				
				
				//運動頻率
				int frequency = new Integer(req.getParameter("frequency").trim());
				
				
				//TDEE指數
				float mem_tdee = 0;
				String mytdee = req.getParameter("mem_tdee").trim();
				String tdeeReg = "^[1-9]{1}[0-9]{2,3}+.{0,1}+[0-9]{0,1}$";
				if (mytdee == null || mytdee.length() == 0) {
					errorMsgs.add("TDEE指數: 請勿空白");
				} else if(!mytdee.matches(tdeeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("TDEE指數: 請輸入正確的TDEE指數");
	            }else {
	            	mem_tdee = new Float(mytdee);
	            }
	            	
	            
				
				
				
				//照片
				BodyRecordService bodyRecordSvc = new BodyRecordService();
				byte[] photo = null;
				Part part = req.getPart("photo");
				InputStream in = part.getInputStream();
				if(in.available() != 0) {
					byte[] buf = new byte[in.available()];
					in.read(buf);
					photo = buf;
					in.close();
				}else {
					photo = bodyRecordSvc.getOneBodyRecordByBodyNo(body_no).getPhoto();
				}
				

				BodyRecordVO bodyRecordVO = new BodyRecordVO();
				bodyRecordVO.setBody_no(body_no);
				bodyRecordVO.setMem_id(mem_id);
				bodyRecordVO.setBody_date(body_date);
				bodyRecordVO.setMem_height(mem_height);
				bodyRecordVO.setMem_weight(mem_weight);
				bodyRecordVO.setMem_bmi(mem_bmi);
				bodyRecordVO.setMem_old(mem_old);
				bodyRecordVO.setMem_bmr(mem_bmr);
				bodyRecordVO.setFrequency(frequency);
				bodyRecordVO.setMem_tdee(mem_tdee);
				bodyRecordVO.setPhoto(photo);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bodyRecordVO", bodyRecordVO); // 含有輸入格式錯誤的bodyRecordVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/bodyrecord/updateBodyRecord.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				bodyRecordVO = bodyRecordSvc.updateBodyRecord(mem_id, mem_height, mem_weight,mem_bmi, mem_old, mem_bmr, frequency, mem_tdee, body_date,photo, body_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("body_no", body_no);
				String url = "/front-end/bodyrecord/bodyRecord.jsp?whichPage="+req.getParameter("whichPage");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,交listOneBodyRecord.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bodyrecord/updateBodyRecord.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addBodyRecord.jsp的請求        
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				//會員編號
				String mem_id = req.getParameter("mem_id").trim();

				
				//紀錄日期
				java.sql.Date body_date = null;
				try {
					body_date = java.sql.Date.valueOf(req.getParameter("body_date").trim());
				} catch (IllegalArgumentException e) {
					body_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
			
				//身高
				float mem_height = 0;
				String myheight = req.getParameter("mem_height").trim();
				String heightReg = "^[1-9]{1}[0-9]{1,2}+.{0,1}+[0-9]{0,1}$";
				if (myheight == null || myheight.length() == 0) {
					errorMsgs.add("身高: 請勿空白");
				} else if(!myheight.matches(heightReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("身高: 請輸入正確的身高");
	            }else {
	            	mem_height = new Float(myheight);
	            }
	            	
	            
				
				
				//體重
				float mem_weight = 0;
				String myweight = req.getParameter("mem_weight").trim();
				String weightReg = "^[1-9]{1}[0-9]{0,2}+.{0,1}+[0-9]{0,1}$";
				if (myweight == null || myweight.length() == 0) {
					errorMsgs.add("體重: 請勿空白");
				} else if(!myweight.matches(weightReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("體重: 請輸入正確的體重");
	            }else {
	            	mem_weight = new Float(myweight);
	            }
	            	
	            
				
				//BMI指數
				float mem_bmi = 0;
				String mybmi = req.getParameter("mem_bmi").trim();
				String bmiReg = "^[1-9]{1}[0-9]{0,1}+.{0,1}+[0-9]{0,1}$";
				if (mybmi == null || mybmi.length() == 0) {
					errorMsgs.add("BMI指數: 請勿空白");
				} else if(!mybmi.matches(bmiReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("BMI指數: 請輸入正確的BMI指數");
	            }else {
	            	mem_bmi = new Float(mybmi);
	            }
	            	
	            
				
				
				//年齡
				int mem_old = 0;
				String myold = req.getParameter("mem_old").trim();
				String oldReg = "^[1-9]{1}[0-9]{0,2}$";
				if (myold == null || myold.length() == 0) {
					errorMsgs.add("年齡: 請勿空白");
				} else if(!myold.matches(oldReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("年齡: 你是鬼吧!!");
	            }else {
	            	mem_old = new Integer(myold);
	            }
	            	
	            
				
				//BMR指數
				float mem_bmr = 0;
				String mybmr = req.getParameter("mem_bmr").trim();
				String bmrReg = "^[1-9]{1}[0-9]{2,3}+.{0,1}+[0-9]{0,1}$";
				if (mybmr == null || mybmr.length() == 0) {
					errorMsgs.add("BMR指數: 請勿空白 ");
				} else if(!mybmr.matches(bmrReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("BMR指數: 請輸入正確的BMR指數");
	            }else {
	            	mem_bmr = new Float(mybmr);
	            }
	            	
	            
				
				
				//運動頻率
				int frequency = new Integer(req.getParameter("frequency").trim());
				
				
				//TDEE指數
				float mem_tdee = 0;
				String mytdee = req.getParameter("mem_tdee").trim();
				String tdeeReg = "^[1-9]{1}[0-9]{2,3}+.{0,1}+[0-9]{0,1}$";
				if (mytdee == null || mytdee.length() == 0) {
					errorMsgs.add("TDEE指數: 請勿空白");
				} else if(!mytdee.matches(tdeeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("TDEE指數: 請輸入正確的TDEE指數");
	            }else {
	            	mem_tdee = new Float(mytdee);
	            }
	            	
	            
				
				
				
				//照片
				Part part = req.getPart("photo");
				InputStream in = part.getInputStream();
				byte[] photo = new byte[in.available()];
				in.read(photo);
				in.close();
				
				

				BodyRecordVO bodyRecordVO = new BodyRecordVO();
				
				bodyRecordVO.setMem_id(mem_id);
				bodyRecordVO.setBody_date(body_date);
				bodyRecordVO.setMem_height(mem_height);
				bodyRecordVO.setMem_weight(mem_weight);
				bodyRecordVO.setMem_bmi(mem_bmi);
				bodyRecordVO.setMem_old(mem_old);
				bodyRecordVO.setMem_bmr(mem_bmr);
				bodyRecordVO.setFrequency(frequency);
				bodyRecordVO.setMem_tdee(mem_tdee);
				bodyRecordVO.setPhoto(photo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bodyRecordVO", bodyRecordVO); // 含有輸入格式錯誤的bodyRecordVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/bodyrecord/insertBodyRecord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				BodyRecordService bodyRecordSvc = new BodyRecordService();
				bodyRecordVO = bodyRecordSvc.addBodyRecord(mem_id, mem_height, mem_weight,mem_bmi, mem_old, mem_bmr, frequency, mem_tdee, body_date, photo);
				
				req.setAttribute("body_no", bodyRecordVO.getBody_no());
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/bodyrecord/bodyRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMem.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bodyrecord/insertBodyRecord.jsp");
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
				String body_no = req.getParameter("body_no");
				
				/***************************2.開始刪除資料***************************************/
				BodyRecordService bodyRecordSvc = new BodyRecordService();
				bodyRecordSvc.deleteBodyRecord(body_no);
				
				 
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/bodyrecord/listOneBodyRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/bodyrecord/listOneBodyRecord.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
