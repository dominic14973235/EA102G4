package com.sportrecord.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;


import com.mem.model.*;
import com.sportrecord.model.*;
import com.sports.model.*;



public class SportRecordServlet extends HttpServlet{
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
							.getRequestDispatcher("/front-end/sportrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/sportrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				SportRecordService sportRecordSvc = new SportRecordService();
				List<SportRecordVO> list = sportRecordSvc.getOneSportRecordByMemId(mem_id);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/sportrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的sportRecordVO物件,存入req
				String url = "/front-end/sportrecord/listOneSportRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneSportRecord.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sportrecord/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_From".equals(action)) {

			try {
				// Retrieve form parameters.
				String record_no = req.getParameter("record_no");
				

				SportRecordDAO sportRecorddao = new SportRecordDAO();
				SportRecordVO sportRecordVO = sportRecorddao.findByPrimaryKeyRecordNo(record_no);

				req.setAttribute("sportRecordVO", sportRecordVO); // 資料庫取出的sportRecordVO物件,存入req
				
				//Bootstrap_modal
				String openModal="true";
				req.setAttribute("openModal",openModal );
				
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/sportrecord/sportRecord.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listOneSportRecord.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String record_no = req.getParameter("record_no");
				
				/***************************2.開始查詢資料****************************************/
				SportRecordService sportRecordSvc = new SportRecordService();
				SportRecordVO sportRecordVO = sportRecordSvc.getOneSportRecordByRecordNo(record_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("sportRecordVO", sportRecordVO);         // 資料庫取出的sportRecordVO物件,存入req
				String url = "/front-end/sportrecord/update_sportRecord_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交update_sportRecord_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sportrecord/listOneSportRecord.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_sportRecord_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				//運動紀錄編號
				String record_no = req.getParameter("record_no");
				
				//會員編號
				String mem_id = req.getParameter("mem_id");
				
				//紀錄日期
				java.sql.Date record_date = null;
				try {
					record_date = java.sql.Date.valueOf(req.getParameter("record_date").trim());
				} catch (IllegalArgumentException e) {
					record_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				//運動項目
				String sport_no = req.getParameter("sport_no");
				
				//記錄一
				float record1 = 0;
				String myrecord1 = req.getParameter("record1").trim();
				String record1Reg = "^[1-9]{1}[0-9]{0,3}+.{0,1}[0-9]{0,1}$";
				
				if(!myrecord1.matches(record1Reg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("紀錄:格式錯誤");
	            }else {
	            	record1 = new Float(myrecord1);
	            }
				
				
				//記錄二
				float record2 =0;
				String myrecord2 = req.getParameter("record2").trim();
				String record2Reg = "^[1-9]{0,1}[0-9]{0,3}+.{0,1}[0-9]{0,1}$";
				if(!myrecord1.matches(record2Reg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("紀錄:格式錯誤");
	            }else {
	            	record2 = new Float(myrecord2);
	            }
				
				

				SportRecordVO sportRecordVO = new SportRecordVO();
				sportRecordVO.setRecord_no(record_no);
				sportRecordVO.setMem_id(mem_id);
				sportRecordVO.setRecord_date(record_date);
				sportRecordVO.setSport_no(sport_no);
				sportRecordVO.setRecord1(record1);
				sportRecordVO.setRecord2(record2);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sportRecordVO", sportRecordVO); // 含有輸入格式錯誤的sportRecordVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/sportrecord/updateSportRecord.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				SportRecordService sportRecordSvc = new SportRecordService();
				sportRecordVO = sportRecordSvc.updateSportRecord(sport_no, mem_id, record_date, record1, record2, record_no);
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("record_no", record_no);
//				req.setAttribute("sportRecordVO", sportRecordVO); // 資料庫update成功後,正確的的sportRecordVO物件,存入req
				String url = "/front-end/sportrecord/sportRecord.jsp?whichPage="+req.getParameter("whichPage");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,交listOneBodyRecord.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sportrecord/updateSportRecord.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addSportRecord.jsp的請求        
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				
				//會員編號
				String mem_id = req.getParameter("mem_id");
				
				//紀錄日期
				java.sql.Date record_date = null;
				try {
					record_date = java.sql.Date.valueOf(req.getParameter("record_date").trim());
				} catch (IllegalArgumentException e) {
					record_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				//運動項目
				String sport_no = req.getParameter("sport_no");
				
				//記錄一
				float record1 = 0;
				String myrecord1 = req.getParameter("record1").trim();
				String record1Reg = "^[1-9]{1}[0-9]{0,3}+.{0,1}[0-9]{0,1}$";
				if(myrecord1 == null) {
						errorMsgs.add("紀錄不能為空值!!");
				}else if(!myrecord1.matches(record1Reg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("紀錄:格式錯誤");
	            }else {
	            	record1 = Float.parseFloat(myrecord1);
	            }
	            	
	            
				
				
				//記錄二
				float record2 = 0;
				String myrecord2 = req.getParameter("record2").trim();
				String record2Reg = "^[1-9]{0,1}[0-9]{0,3}+.{0,1}[0-9]{0,1}$";
				if(!myrecord2.matches(record2Reg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("紀錄:格式錯誤");
	            }else {
	            	record2 = Float.parseFloat(myrecord2);
	            }
	            	
	            
				

				SportRecordVO sportRecordVO = new SportRecordVO();
				sportRecordVO.setMem_id(mem_id);
				sportRecordVO.setRecord_date(record_date);
				sportRecordVO.setSport_no(sport_no);
				sportRecordVO.setRecord1(record1);
				sportRecordVO.setRecord2(record2);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sportRecordVO", sportRecordVO); // 含有輸入格式錯誤的sportRecordVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sportrecord/sportRecord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				SportRecordService sportRecordSvc = new SportRecordService();
				sportRecordVO = sportRecordSvc.addSportRecord(sport_no, mem_id, record_date, record1, record2);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/sportrecord/sportRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOneSportRecord.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sportrecord/sportRecord.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listOneSportRecord.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String record_no = req.getParameter("record_no");
				
				/***************************2.開始刪除資料***************************************/
				SportRecordService sportRecordSvc = new SportRecordService();
				sportRecordSvc.deleteSportRecord(record_no);
				
				 
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/sportrecord/listOneSportRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sportrecord/listOneSportRecord.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
