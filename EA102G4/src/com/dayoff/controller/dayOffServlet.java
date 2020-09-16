package com.dayoff.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.calendar.controller.CalendarVO;
import com.dayoff.model.*;

public class dayOffServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String ctime_no = req.getParameter("ctime_no");

				/*************************** 2.開始查詢資料 ****************************************/
				DayoffService dayoffSvc = new DayoffService();
				DayoffVO dayoffVO = dayoffSvc.getOneDayoff(ctime_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("dayoffVO", dayoffVO); // 資料庫取出的empVO物件,存入req
				String url = "updateDayoff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllDayoff.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String ctime_no = req.getParameter("ctime_no");
				if (ctime_no == null || ctime_no.trim().length() == 0) {
					errorMsgs.add("時間流水編號: 請勿空白");
				}

				String pro_ID = req.getParameter("pro_ID").trim();
				if (pro_ID == null || pro_ID.trim().length() == 0) {
					errorMsgs.add("PRO_ID請勿空白");
				}

				String mem_ID = req.getParameter("mem_ID").trim();
				if (mem_ID == null || mem_ID.trim().length() == 0) {
					errorMsgs.add("MEM_ID請勿空白");
				}
				System.out.println(mem_ID);

				Date close_date = null;
				try {
					close_date = Date.valueOf(req.getParameter("close_date").trim());
				} catch (IllegalArgumentException e) {
					close_date = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String hr = req.getParameter("hr").trim();
				if (mem_ID == null || mem_ID.trim().length() == 0) {
					errorMsgs.add("hr請勿空白");
				}

				DayoffVO dayoffVO = new DayoffVO();
				dayoffVO.setCtime_no(ctime_no);
				dayoffVO.setPro_ID(pro_ID);
				dayoffVO.setMem_ID(mem_ID);
				dayoffVO.setClose_date(close_date);
				dayoffVO.setHr(hr);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dayoffVO", dayoffVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("updateDayoff.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				DayoffService dayoffSvc = new DayoffService();
				dayoffVO = dayoffSvc.updateDayoff(ctime_no, pro_ID, mem_ID, close_date, hr);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("dayoffVO", dayoffVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "listProDayoff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("updateDayoff.jsp");
				failureView.forward(req, res);
			}
		}

		/*********************** Insert new Dayoff *************************/

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String pro_ID = req.getParameter("pro_ID");
				if (pro_ID == null || pro_ID.trim().length() == 0) {
					errorMsgs.add("PRO_ID: 請勿空白");
				}

				String mem_ID = req.getParameter("mem_ID").trim();
				if (mem_ID == null || mem_ID.trim().length() == 0) {
					errorMsgs.add("MEM_ID請勿空白");
				}

				java.sql.Date close_date = null;
				try {
					close_date = java.sql.Date.valueOf(req.getParameter("close_date").trim());
				} catch (IllegalArgumentException e) {
					close_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String hr = null;
				try {
					hr = req.getParameter("hr").trim();
				} catch (NumberFormatException e) {
					errorMsgs.add("請填數字.");
				}

				DayoffVO dayoffVO = new DayoffVO();
				dayoffVO.setPro_ID(pro_ID);
				dayoffVO.setMem_ID(mem_ID);
				dayoffVO.setClose_date(close_date);
				dayoffVO.setHr(hr);

				/***************************
				 * 2.驗證是否有重複時間
				 ***************************************/
				DayoffService dayoffSvc = new DayoffService();
				DayoffVO dayoffCheck = dayoffSvc.getDateByProAndDate(pro_ID, close_date);

				if (dayoffCheck != null) {
					if (dayoffVO.equals(dayoffCheck)) {
						errorMsgs.add("這個排休日已存在");
					}
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dayoffVO", dayoffVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("addDayoff.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.開始新增資料 ***************************************/
				dayoffVO = dayoffSvc.addDayoff(pro_ID, mem_ID, close_date, hr);
				/*************************** 4.新增完成,準備轉交(Send the Success view) ***********/
				String url = "listAllDayoff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("addDayoff.jsp");
				failureView.forward(req, res);
			}
		}

		/*************************** Delete Dayoff **********************/

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String ctime_no = req.getParameter("ctime_no");

				/*************************** 2.開始刪除資料 ***************************************/
				DayoffService dayoffSvc = new DayoffService();
				dayoffSvc.deleteDayoff(ctime_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/ProAndClass/listAllDayoff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ProAndClass/listAllDayoff.jsp");
				failureView.forward(req, res);
			}
		}

//		/***************************Get Dayoff by PRO_ID　return list**********************/

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("pro_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入專業編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String pro_ID = null;
				try {
					pro_ID = str;
				} catch (Exception e) {
					errorMsgs.add("專業編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("selectDayoff.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				DayoffService dayoffSvc = new DayoffService();
				List<DayoffVO> dayoffVO = dayoffSvc.getOneProDayoff(pro_ID);
				List<CalendarVO> list = new ArrayList<CalendarVO>();

				if (dayoffVO == null) {
					errorMsgs.add("查無資料");
				}

				for (DayoffVO dayTrans : dayoffVO) {
					CalendarVO fCal = new CalendarVO();
					fCal.setClass_no(dayTrans.getMem_ID());
					
					fCal.setClass_no(dayTrans.getCtime_no());
					fCal.setrDate(dayTrans.getClose_date());
					fCal.setHr(dayTrans.getHr());
					list.add(fCal);

				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("selectDayoff.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				JSONObject json = new JSONObject();
				JSONArray jsonarray = new JSONArray(list);
				String events = jsonarray.toString();
				PrintWriter out = res.getWriter();
				out.print(events);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("selectDayoff.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
