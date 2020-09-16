package com.worker.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.worker.model.*;
import com.workpower.model.*;
@MultipartConfig
public class WorkerServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("login".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//帳號
				String w_acc = req.getParameter("w_acc");
				if (w_acc == null || (w_acc.trim()).length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				}
				//密碼
				String w_pw = req.getParameter("w_pw");
				if (w_pw == null || (w_pw.trim()).length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/***************************2.開始查詢資料*****************************************/
				WorkerService workerSvc = new WorkerService();
				WorkerVO workerVO = workerSvc.getOneByAcc(w_acc);
				if (workerVO == null || !(workerVO.getW_pw().equals(w_pw))) {
					errorMsgs.add("帳號或密碼錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("workerVOLogin", workerVO);
				
				String url = "/back-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/login.jsp");
				failureView.forward(req, res);
			}
		}
		
		//登出
		if ("logout".equals(action)) { // 來自index.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
										
			HttpSession session = req.getSession();
			session.invalidate();
			String url = "/back-end/worker/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMem.jsp
			successView.forward(req, res);
		}
		
		//修改密碼
		if ("update_pw".equals(action)) { // 來自index.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//會員編號
				String work_id = req.getParameter("work_id");
				//舊密碼
				String w_pw = req.getParameter("w_pw");
				//新密碼
				String w_npw = req.getParameter("w_npw");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/index.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/***************************2.開始查詢資料*****************************************/
				WorkerService workerSvc = new WorkerService();
				workerSvc.updatePw(work_id, w_npw);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				String url = "/back-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/index.jsp");
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
				String str = req.getParameter("work_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String work_id = null;
				try {
					work_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				WorkerService workerSvc = new WorkerService();
				WorkerVO workerVO = workerSvc.getOneEmp(work_id);
				if (workerVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("workerVO", workerVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/worker/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/select_page.jsp");
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
				String work_id = new String(req.getParameter("work_id"));
				
				/***************************2.開始查詢資料****************************************/
				WorkerService workerSvc = new WorkerService();
				WorkerVO workerVO = workerSvc.getOneEmp(work_id);
				
				//取出員工權限,並轉成字串
				WorkPowerService workpowerSvc = new WorkPowerService();
				List<WorkPowerVO> wpVO = workpowerSvc.getByWorker(work_id);
				StringBuffer sb = new StringBuffer();
				for(WorkPowerVO i:wpVO) {
					sb.append(i.getPower_id());
				}
				String wp = sb.toString();
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("workerVO", workerVO);// 資料庫取出的empVO物件,存入req
				req.setAttribute("wp",wp);// 將得到的員工權限字串,存入req
				
				String url = "/back-end/worker/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/listAllEmp.jsp");
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
				String work_id = new String(req.getParameter("work_id").trim());
				
				String w_name = req.getParameter("w_name");
				String w_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (w_name == null || w_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!w_name.trim().matches(w_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String w_acc = req.getParameter("w_acc").trim();
				if (w_acc == null || w_acc.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}
				
				String w_pw = req.getParameter("w_pw").trim();
				if (w_pw == null || w_pw.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}	
				
				java.sql.Date reg_date = null;
				try {
					reg_date = java.sql.Date.valueOf(req.getParameter("reg_date").trim());
				} catch (IllegalArgumentException e) {
					reg_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				byte[] photo = null;				
				Part part = req.getPart("photo");
				InputStream in = part.getInputStream();
				if (in.available() != 0) {
				byte[] buf = new byte[in.available()];
				in.read(buf);
				photo = buf;				
				} else {
					photo = new WorkerService().getOneEmp(work_id).getPhoto();
				}
				
				WorkerVO workerVO = new WorkerVO();
				workerVO.setWork_id(work_id);
				workerVO.setW_name(w_name);
				workerVO.setW_acc(w_acc);
				workerVO.setW_pw(w_pw);
				workerVO.setEmail(email);
				workerVO.setReg_date(reg_date);
				workerVO.setPhoto(photo);
				
				String power[] = req.getParameterValues("POWER");
//				if (power == null) {
//					errorMsgs.add("請選擇權限");
//				}
				
				StringBuffer sb = new StringBuffer();
				if (power == null) {
					sb.append("");
				} else {
					for(String i:power) {
						sb.append(i);
					}
				}
				String wp = sb.toString();

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("workerVO", workerVO); // 含有輸入格式錯誤的empVO物件,也存入req
					req.setAttribute("wp", wp);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				WorkerService workerSvc = new WorkerService();
				workerVO = workerSvc.updateEmp(work_id, w_name, w_acc, w_pw, email, reg_date, photo);
				WorkPowerService workpowerSvc = new WorkPowerService();

				if(power != null) {
					WorkPowerVO workPowerVO = new WorkPowerVO();
					workpowerSvc.delete(work_id);
					for(String i:power){
						workPowerVO = workpowerSvc.addWorkPower(work_id, i);
					}			
				}else {
					workpowerSvc.delete(work_id);
				}
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("workerVO", workerVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				String url = "/back-end/worker/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/update_emp_input.jsp");
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
				String w_name = req.getParameter("w_name");
				String w_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (w_name == null || w_name.trim().length() == 0) {
					errorMsgs.add("員工姓名請勿空白");
				} else if(!w_name.trim().matches(w_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String w_acc = req.getParameter("w_acc").trim();
				if (w_acc == null || w_acc.trim().length() == 0) {
					errorMsgs.add("帳號不能空白");
				}
				
//				String w_pw = req.getParameter("w_pw").trim();
//				if (w_pw == null || w_pw.trim().length() == 0) {
//					errorMsgs.add("密碼不能空白");
//				}
			    String w_pw = getRandomPassword();
				
				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("信箱不能空白");
				}
				
				java.sql.Date reg_date = null;
				try {
					reg_date = java.sql.Date.valueOf(req.getParameter("reg_date").trim());
				} catch (IllegalArgumentException e) {
					reg_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				byte[] photo = null;
				Part part = req.getPart("photo");
			
				InputStream in = part.getInputStream();
				if (in.available() == 0) {
					errorMsgs.add("請選擇圖片");
				} else {
				byte[] buf = new byte[in.available()];
				in.read(buf);
				photo = buf;
				}

				WorkerVO workerVO = new WorkerVO();
				workerVO.setW_name(w_name);
				workerVO.setW_acc(w_acc);
				workerVO.setW_pw(w_pw);
				workerVO.setEmail(email);
				workerVO.setReg_date(reg_date);
				workerVO.setPhoto(photo);

				String power[] = req.getParameterValues("POWER");

				StringBuffer sb = new StringBuffer();
				if (power == null) {
					sb.append("");
				} else {
					for(String i:power) {
						sb.append(i);
					}
				}
				String wp = sb.toString();

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("workerVO", workerVO); // 含有輸入格式錯誤的empVO物件,也存入req
					req.setAttribute("wp", wp); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

//				
				/***************************2.開始新增資料***************************************/
				WorkerService workerSvc = new WorkerService();
				workerVO = workerSvc.addEmp(w_name, w_acc, w_pw, email, reg_date, photo);
				workerVO = workerSvc.getOneByAcc(w_acc);
				
				if (power != null) {
					WorkPowerService workpowerSvc = new WorkPowerService();
					WorkPowerVO workPowerVO = new WorkPowerVO();
					for(String i:power){
						workPowerVO = workpowerSvc.addWorkPower(workerVO.getWork_id(), i);
					}
				}
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
			    //寄送E-Mail
			    String to = email;			      
			    String subject = "密碼通知";			      
			    String ch_name = w_name;
			    String messageText = "Hello! " + ch_name + " 以下是你的帳密" + "\n" + 
			    					 "帳號:" + w_acc+ "\n" +
			    		             "密碼:" + w_pw + "\n" + 
			    					 " (已經啟用)"; 
			       
			    MailService mailService = new MailService();
			    mailService.sendMail(to, subject, messageText);
			    //
				
				String url = "/back-end/worker/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/addEmp.jsp");
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
				String work_id = new String(req.getParameter("work_id"));
				
				/***************************2.開始刪除資料***************************************/
				WorkerService workerSvc = new WorkerService();
				workerSvc.deleteEmp(work_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/worker/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	private String getRandomPassword() {
	    int z;
	    StringBuilder sb = new StringBuilder();
	    int i;
	    for (i = 0; i < 5; i++) {
	      z = (int) ((Math.random() * 7) % 3);
	 
	      if (z == 1) { // 放數字
	        sb.append((int) ((Math.random() * 10) + 48));
	      } else if (z == 2) { // 放大寫英文
	        sb.append((char) (((Math.random() * 26) + 65)));
	      } else {// 放小寫英文
	        sb.append(((char) ((Math.random() * 26) + 97)));
	      }
	    }
	    return sb.toString();
	  }
}
